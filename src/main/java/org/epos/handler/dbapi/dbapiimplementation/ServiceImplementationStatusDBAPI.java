package org.epos.handler.dbapi.dbapiimplementation;

import org.epos.eposdatamodel.Group;
import org.epos.eposdatamodel.LinkedEntity;
import org.epos.eposdatamodel.ServiceImplementationStatus;
import org.epos.eposdatamodel.State;
import org.epos.handler.dbapi.model.*;
import org.epos.handler.dbapi.util.EDMUtil;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.epos.handler.dbapi.util.DBUtil.getFromDB;
import static org.epos.handler.dbapi.util.DBUtil.getOneFromDB;

public class ServiceImplementationStatusDBAPI extends AbstractDBAPI<ServiceImplementationStatus> {


    public ServiceImplementationStatusDBAPI() {
        super("serviceimplementationstatus", EDMServiceImplementationStatus.class);
    }

    @Override
    public LinkedEntity save(ServiceImplementationStatus eposDataModelObject, EntityManager em, String edmInstanceId) {
        //if the instace need to be public check if is correct (duplicate)
        if (eposDataModelObject.getState().equals(State.PUBLISHED)) {
            EDMServiceImplementationStatus published = getOneFromDB(em, EDMServiceImplementationStatus.class,
                    "serviceimplementationstatus.findByServiceUidAndServiceProviderUidAndState",
                    "METASERVICEPROVIDE", eposDataModelObject.getService(),
                    "METASERVICEPROVIDER", eposDataModelObject.getServiceProvider(),
                    "STATE", State.PUBLISHED.toString());

            if (published != null) {
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with metaid: " + eposDataModelObject.getMetaId() + ", is already published");
            }
        }

        //if there's a placeholder for the entity check if is passed a specific metaid
        //only if the metaid is the same of the placeholder merge the two (the placeholder and the passed entity)
        EDMEdmEntityId edmMetaId;


        EDMServiceImplementationStatus edmObject = new EDMServiceImplementationStatus();
        edmObject.setInstanceId(edmInstanceId);
        em.persist(edmObject);

        if (eposDataModelObject.getMetaId() == null) {
            edmMetaId = new EDMEdmEntityId();
            edmMetaId.setMetaId(UUID.randomUUID().toString());
            em.persist(edmMetaId);
        } else {
            edmMetaId = getOneFromDB(em, EDMEdmEntityId.class,
                    "edmentityid.findByMetaId",
                    "METAID", eposDataModelObject.getMetaId());
            if (edmMetaId == null) {
                edmMetaId = new EDMEdmEntityId();
                edmMetaId.setMetaId(eposDataModelObject.getMetaId());
                em.persist(edmMetaId);
            }

        }

        edmObject.setEdmEntityIdByMetaId(edmMetaId);

        if (eposDataModelObject.getInstanceChangedId() != null) {
            EDMServiceImplementationStatus changedInstance = getOneFromDB(em, EDMServiceImplementationStatus.class, "dataproduct.findByInstanceId",
                    "INSTANCEID", eposDataModelObject.getInstanceChangedId());
            if (changedInstance == null) {
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] withmetaid: " + edmObject.getMetaId() + ", have an invalid 'InstanceChangedId'.");
            }
            edmObject.setServiceImplementationStatusByInstanceChangedId(changedInstance);
        }

        if (eposDataModelObject.getEditorId() == null) {
            em.getTransaction().rollback();
            throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with metaid: " + edmObject.getMetaId() + ", doesn't have the editorid.");
        }
        EDMEdmEntityId edmMetaIdEditor = getOneFromDB(em, EDMEdmEntityId.class,
                "edmentityid.findByMetaId",
                "METAID", eposDataModelObject.getEditorId());

        if (edmMetaIdEditor == null) {
            em.getTransaction().rollback();
            throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with  metaid: " + edmObject.getMetaId() + ", the editor doesn't exist.");
        }

        edmObject.setChangeTimestamp(new Timestamp(System.currentTimeMillis()));
        edmObject.setOperation(eposDataModelObject.getOperation());
        edmObject.setChangeComment(eposDataModelObject.getChangeComment());
        edmObject.setVersion(eposDataModelObject.getVersion());
        edmObject.setState(eposDataModelObject.getState().toString());
        edmObject.setToBeDeleted(Boolean.valueOf(eposDataModelObject.getToBeDelete()));

        if (eposDataModelObject.getServiceProvider() != null) {
            List<EDMOrganization> edmOrganizations = getFromDB(em, EDMOrganization.class,
                    "organization.findByUid", "UID", eposDataModelObject.getServiceProvider());

            edmOrganizations.sort(EDMUtil::compareEntityVersion);

            EDMOrganization edmOrganization = !edmOrganizations.isEmpty() ?
                    edmOrganizations.get(0) : null;

            EDMEdmEntityId edmMetaOrganization;

            if (edmOrganization == null) {
                edmMetaOrganization = new EDMEdmEntityId();
                edmMetaOrganization.setMetaId(UUID.randomUUID().toString());
                em.persist(edmMetaOrganization);

                edmOrganization = new EDMOrganization();
                edmOrganization.setUid(eposDataModelObject.getServiceProvider());
                edmOrganization.setState(State.PLACEHOLDER.toString());
                edmOrganization.setInstanceId(UUID.randomUUID().toString());
                edmOrganization.setEdmEntityIdByMetaId(edmMetaOrganization);

                em.persist(edmOrganization);

            } else {
                edmMetaOrganization = edmOrganization.getEdmEntityIdByMetaId();
            }

            edmObject.setEdmEntityIdByMetaServiceproviderId(edmMetaOrganization);
        }

        if (eposDataModelObject.getService() != null) {
            List<EDMService> edmServices = getFromDB(em, EDMService.class,
                    "service.findByUid", "UID", eposDataModelObject.getService());

            edmServices.sort(EDMUtil::compareEntityVersion);

            EDMService edmService = !edmServices.isEmpty() ?
                    edmServices.get(0) : null;

            EDMEdmEntityId edmMetaService;

            if (edmService == null) {
                edmMetaService = new EDMEdmEntityId();
                edmMetaService.setMetaId(UUID.randomUUID().toString());
                em.persist(edmMetaService);

                edmService = new EDMService();
                edmService.setIdentifier(eposDataModelObject.getService());
                edmService.setState(State.PLACEHOLDER.toString());
                edmService.setInstanceId(UUID.randomUUID().toString());
                edmService.setEdmEntityIdByMetaId(edmMetaService);

                em.persist(edmService);

            } else {
                edmMetaService = edmService.getEdmEntityIdByMetaId();
            }

            edmObject.setEdmEntityIdByMetaServiceId(edmMetaService);
        }

        edmObject.setStatus(eposDataModelObject.getStatus());

        return new LinkedEntity().entityType(entityString)
                .instanceId(edmInstanceId)
                .metaId(edmObject.getEdmEntityIdByMetaId().getMetaId())
                .uid(eposDataModelObject.getUid());

    }

    @Override
    protected ServiceImplementationStatus mapFromDB(Object edmObject) {
        ServiceImplementationStatus o = new ServiceImplementationStatus();

        EDMServiceImplementationStatus edm = (EDMServiceImplementationStatus) edmObject;

        o.setInstanceId(edm.getInstanceId());
        o.setMetaId(edm.getMetaId());
        o.setState(State.valueOf(edm.getState()));
        o.setOperation(edm.getOperation());
        if (edm.getEdmEntityIdByEditorMetaId() != null ) {
            o.setEditorId(edm.getEdmEntityIdByEditorMetaId().getMetaId());
        }
        o.setVersion(edm.getVersion());
        o.setChangeTimestamp(
                edm.getChangeTimestamp() != null ? edm.getChangeTimestamp().toLocalDateTime() : null
        );
        o.setToBeDelete(edm.getToBeDeleted() != null ? edm.getToBeDeleted().toString() : "false");
        o.setInstanceChangedId(edm.getInstanceChangedId());
        o.setGroups(
                edm.getEdmEntityIdByMetaId() != null && edm.getEdmEntityIdByMetaId().getAuthorizationsByMetaId() != null ?
                        edm.getEdmEntityIdByMetaId().getAuthorizationsByMetaId().stream()
                                .map(EDMAuthorization::getGroupByGroupId)
                                .map(e -> {
                                    Group group = new Group();
                                    group.setName(e.getName());
                                    group.setDescription(e.getDescription());
                                    return group;
                                })
                                .collect(Collectors.toList())
                        : null
        );

        if (edm.getEdmEntityIdByMetaServiceproviderId() != null && edm.getEdmEntityIdByMetaServiceproviderId().getOrganizationsByMetaId() != null
                && !edm.getEdmEntityIdByMetaServiceproviderId().getOrganizationsByMetaId().isEmpty()) {
            List<EDMOrganization> so = new ArrayList<>(edm.getEdmEntityIdByMetaServiceproviderId().getOrganizationsByMetaId());
            so.sort(EDMUtil::compareEntityVersion);
            o.setServiceProvider(so.get(0).getUid());
        }

        if (edm.getEdmEntityIdByMetaServiceId() != null && edm.getEdmEntityIdByMetaServiceId().getServicesByMetaId_1() != null
                && !edm.getEdmEntityIdByMetaServiceId().getServicesByMetaId_1().isEmpty()) {
            List<EDMService> so = new ArrayList<>(edm.getEdmEntityIdByMetaServiceId().getServicesByMetaId_1());
            so.sort(EDMUtil::compareEntityVersion);
            o.setService(so.get(0).getIdentifier());
        }

        o.setStatus(edm.getStatus());
        return o;
    }

}
