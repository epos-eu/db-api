package org.epos.handler.dbapi.dbapiimplementation;

import org.epos.eposdatamodel.CategoryScheme;
import org.epos.eposdatamodel.DataProductImplementationStatus;
import org.epos.eposdatamodel.Group;
import org.epos.eposdatamodel.LinkedEntity;
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

public class DataProductImplementationStatusDBAPI extends AbstractDBAPI<DataProductImplementationStatus> {

    public DataProductImplementationStatusDBAPI() {
        super("dataproductimplementationstatus", EDMDataproductImplementationStatus.class);
    }
    
    @Override
    public LinkedEntity save(DataProductImplementationStatus eposDataModelObject, EntityManager em, String edmInstanceId) {
        if (eposDataModelObject.getState().equals(State.PUBLISHED)) {
            //if the instace need to be public check if is correct (duplicate)
            EDMDataproductImplementationStatus published = getOneFromDB(em, EDMDataproductImplementationStatus.class,
                    "dataproductimplementationstatus.findByDataProviderUidAndDataProductUidAndState",
                    "METADATAPRODUCTID", eposDataModelObject.getDataProduct(),
                    "METADATAPROVIDER", eposDataModelObject.getDataProvider(),
                    "STATE", State.PUBLISHED.toString());

            if (published != null) {
                em.getTransaction().rollback();
                System.out.println("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with metaid: " + published.getMetaId() + ", is already published");
                return new LinkedEntity();
            }
        }

        //if there's a placeholder for the entity check if is passed a specific metaid
        //only if the metaid is the same of the placeholder merge the two (the placeholder and the passed entity)
        EDMEdmEntityId edmMetaId;

        EDMDataproductImplementationStatus edmObject = new EDMDataproductImplementationStatus();


        edmObject.setInstanceId(edmInstanceId);

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
            EDMDataproductImplementationStatus changedInstance = getOneFromDB(em, EDMDataproductImplementationStatus.class, "dataproductimplementationstatus.findByInstanceId",
                    "INSTANCEID", eposDataModelObject.getInstanceChangedId());
            if (changedInstance == null) {
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] withmetaid: " + edmObject.getMetaId() + ", have an invalid 'InstanceChangedId'.");
            }
            edmObject.setDataproductImplementationStatusByInstanceChangedId(changedInstance);
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

        if (eposDataModelObject.getDataProduct() != null) {
            List<EDMDataproduct> edmDataproducts = getFromDB(em, EDMDataproduct.class,
                    "dataproduct.findByUid", "UID", eposDataModelObject.getDataProduct());

            edmDataproducts.sort(EDMUtil::compareEntityVersion);

            EDMDataproduct edmDataproduct = !edmDataproducts.isEmpty() ?
                    edmDataproducts.get(0) : null;

            EDMEdmEntityId edmMetaDataproduct;

            if (edmDataproduct == null) {
                edmMetaDataproduct = new EDMEdmEntityId();
                edmMetaDataproduct.setMetaId(UUID.randomUUID().toString());
                em.persist(edmMetaDataproduct);

                edmDataproduct = new EDMDataproduct();
                edmDataproduct.setUid(eposDataModelObject.getDataProduct());
                edmDataproduct.setState(State.PLACEHOLDER.toString());
                edmDataproduct.setInstanceId(UUID.randomUUID().toString());
                edmDataproduct.setEdmEntityIdByMetaId(edmMetaDataproduct);

                em.persist(edmDataproduct);

            } else {
                edmMetaDataproduct = edmDataproduct.getEdmEntityIdByMetaId();
            }

            edmObject.setEdmEntityIdByMetaDataproviderId_0(edmMetaDataproduct);
        }

        if (eposDataModelObject.getDataProvider() != null) {
            List<EDMOrganization> edmOrganizations = getFromDB(em, EDMOrganization.class,
                    "organization.findByUid", "UID", eposDataModelObject.getDataProvider());

            edmOrganizations.sort(EDMUtil::compareEntityVersion);

            EDMOrganization edmOrganization = !edmOrganizations.isEmpty() ?
                    edmOrganizations.get(0) : null;

            EDMEdmEntityId edmMetaOrganization;

            if (edmOrganization == null) {
                edmMetaOrganization = new EDMEdmEntityId();
                edmMetaOrganization.setMetaId(UUID.randomUUID().toString());
                em.persist(edmMetaOrganization);

                edmOrganization = new EDMOrganization();
                edmOrganization.setUid(eposDataModelObject.getDataProvider());
                edmOrganization.setState(State.PLACEHOLDER.toString());
                edmOrganization.setInstanceId(UUID.randomUUID().toString());
                edmOrganization.setEdmEntityIdByMetaId(edmMetaOrganization);

                em.persist(edmOrganization);

            } else {
                edmMetaOrganization = edmOrganization.getEdmEntityIdByMetaId();
            }

            edmObject.setEdmEntityIdByMetaDataproviderId(edmMetaOrganization);
        }

        edmObject.setStatus(eposDataModelObject.getStatus());

        em.persist(edmObject);


        return new LinkedEntity().entityType(entityString)
                .instanceId(edmInstanceId)
                .metaId(edmObject.getEdmEntityIdByMetaId().getMetaId())
                .uid(eposDataModelObject.getUid());

    }

    @Override
    protected DataProductImplementationStatus mapFromDB(Object edmObject) {
        DataProductImplementationStatus o = new DataProductImplementationStatus();

        EDMDataproductImplementationStatus edm = (EDMDataproductImplementationStatus) edmObject;

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
                                    group.setId(e.getId());
                                    return group;
                                })
                                .collect(Collectors.toList())
                        : null
        );

        if (edm.getEdmEntityIdByMetaDataproviderId() != null && edm.getEdmEntityIdByMetaDataproviderId().getOrganizationsByMetaId() != null
                && !edm.getEdmEntityIdByMetaDataproviderId().getOrganizationsByMetaId().isEmpty()) {
            List<EDMOrganization> so = new ArrayList<>(edm.getEdmEntityIdByMetaDataproviderId().getOrganizationsByMetaId());
            so.sort(EDMUtil::compareEntityVersion);
            o.setDataProvider(so.get(0).getUid());
        }

        if (edm.getEdmEntityIdByMetaDataproviderId_0() != null && edm.getEdmEntityIdByMetaDataproviderId_0().getDataproductsByMetaId() != null
                && !edm.getEdmEntityIdByMetaDataproviderId_0().getDataproductsByMetaId().isEmpty()) {
            List<EDMDataproduct> so = new ArrayList<>(edm.getEdmEntityIdByMetaDataproviderId_0().getDataproductsByMetaId());
            so.sort(EDMUtil::compareEntityVersion);
            o.setDataProduct(so.get(0).getUid());
        }

        o.setStatus(edm.getStatus());
        return o;
    }

}
