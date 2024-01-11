package org.epos.handler.dbapi.dbapiimplementation;


import org.epos.eposdatamodel.CategoryScheme;
import org.epos.eposdatamodel.Distribution;
import org.epos.eposdatamodel.Group;
import org.epos.eposdatamodel.LinkedEntity;
import org.epos.eposdatamodel.State;
import org.epos.handler.dbapi.model.*;
import org.epos.handler.dbapi.util.EDMUtil;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.epos.handler.dbapi.util.DBUtil.getFromDB;
import static org.epos.handler.dbapi.util.DBUtil.getOneFromDB;

public class DistributionDBAPI extends AbstractDBAPI<Distribution> {

    public DistributionDBAPI() {
        super("distribution", EDMDistribution.class);
    }

    @Override
    public LinkedEntity save(Distribution eposDataModelObject, EntityManager em, String edmInstanceId) {
        if (eposDataModelObject.getState().equals(State.PUBLISHED)
                && isAlreadyPublished(EDMDistribution.class, "distribution.findByUidAndState", em, eposDataModelObject))
            return new LinkedEntity();
        
        System.out.println("OBJECT DISTRIBUTION: "+eposDataModelObject);

        //search for a existing instance placeholder to be populated
        EDMDistribution edmObject = getOneFromDB(em, EDMDistribution.class,
                "distribution.findByUidAndState",
                "UID", eposDataModelObject.getUid(),
                "STATE", State.PLACEHOLDER.toString());
        
        if(edmObject==null) {
			edmObject = getOneFromDB(em, EDMDistribution.class,
					"distribution.findByInstanceId",
					"INSTANCEID", eposDataModelObject.getInstanceId());
		}
		
		System.out.println("EDM OBJECT DISTRIBUTION: "+edmObject);

        //if there's a placeholder for the entity check if is passed a specific metaid
        //only if the metaid is the same of the placeholder merge the two (the placeholder and the passed entity)
        EDMEdmEntityId edmMetaId;

        boolean merged = false;

        if (edmObject != null &&
                (eposDataModelObject.getMetaId() == null || (eposDataModelObject.getMetaId() != null && eposDataModelObject.getMetaId().equals(edmObject.getMetaId())))) {
            //em.merge(edmObject);
            merged = true;

        } else {
            edmObject = new EDMDistribution();
            edmObject.setInstanceId(edmInstanceId);
            //em.persist(edmObject);

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

        }

        edmObject.setUid(eposDataModelObject.getUid() != null ? eposDataModelObject.getUid().replace("file:///app/", "") : null);

        if (eposDataModelObject.getInstanceChangedId() != null) {
            EDMDistribution changedInstance = getOneFromDB(em, EDMDistribution.class, "distribution.findByInstanceId",
                    "INSTANCEID", eposDataModelObject.getInstanceChangedId());
            if (changedInstance == null) {
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: " + edmObject.getUid() + ", state: " + edmObject.getState()
                        + " and instanceId: " + edmObject.getInstanceId() + ", have an invalid 'InstanceChangedId'.");
            }
            edmObject.setDistributionByInstanceChangedId(changedInstance);
        }

        if (eposDataModelObject.getEditorId() == null) {
            em.getTransaction().rollback();
            throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: " + edmObject.getUid() + ", state: " + edmObject.getState()
                    + " and instanceId: " + edmObject.getInstanceId() + ", doesn't have the editorid.");
        }
        EDMEdmEntityId edmMetaIdEditor = getOneFromDB(em, EDMEdmEntityId.class,
                "edmentityid.findByMetaId",
                "METAID", eposDataModelObject.getEditorId());

        if (edmMetaIdEditor == null) {
            em.getTransaction().rollback();
            throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: " + edmObject.getUid() + ", state: " + eposDataModelObject.getState()
                    + " and instanceId: " + edmObject.getInstanceId() + ", the editor doesn't exist.");
        } else {
            edmObject.setEdmEntityIdByEditorMetaId(edmMetaIdEditor);
        }

        edmObject.setFileprovenance(eposDataModelObject.getFileProvenance());
        edmObject.setChangeTimestamp(new Timestamp(System.currentTimeMillis()));
        edmObject.setOperation(eposDataModelObject.getOperation());
        edmObject.setChangeComment(eposDataModelObject.getChangeComment());
        edmObject.setVersion(eposDataModelObject.getVersion());
        edmObject.setState(eposDataModelObject.getState().toString());
        edmObject.setToBeDeleted(Boolean.valueOf(eposDataModelObject.getToBeDelete()));
        if (!merged)
            em.persist(edmObject);


        if (eposDataModelObject.getAccessService() != null) {
            EDMWebservice instance = null;

            LinkedEntity linkedEntity = eposDataModelObject.getAccessService();

            if (linkedEntity.getInstanceId() != null) {
                instance = getOneFromDB(em, EDMWebservice.class,
                        "webservice.findByInstanceId", "INSTANCEID", linkedEntity.getInstanceId());
            }
            if (linkedEntity.getInstanceId() == null || instance == null) {
                List<EDMWebservice> instanceList = getFromDB(em, EDMWebservice.class,
                        "webservice.findByUid", "UID", linkedEntity.getUid());

                instanceList.sort(EDMUtil::compareEntityVersion);

                instance = !instanceList.isEmpty() ? instanceList.get(0) : null;
            }


            if (instance == null) {
                EDMEdmEntityId edmInstanceMetaId = new EDMEdmEntityId();
                edmInstanceMetaId.setMetaId(UUID.randomUUID().toString());
                em.persist(edmInstanceMetaId);

                instance = new EDMWebservice();
                instance.setUid(eposDataModelObject.getAccessService().getUid());
                instance.setState(State.PLACEHOLDER.toString());
                instance.setInstanceId(UUID.randomUUID().toString());
                em.persist(instance);

                instance.setEdmEntityIdByMetaId(edmInstanceMetaId);
            }

            if (instance.getDistributionByInstanceId() == null)
                instance.setDistributionByInstanceId(new LinkedList<>());
            instance.getDistributionByInstanceId().add(edmObject);

            edmObject.setWebserviceByAccessService(instance);
        }

        if (eposDataModelObject.getAccessURL() != null) {
            edmObject.setAccessURLByInstanceId(new ArrayList<>());
            for (LinkedEntity linkedEntity : eposDataModelObject.getAccessURL()) {
                EDMOperation instance = null;

                if (linkedEntity.getInstanceId() != null) {
                    instance = getOneFromDB(em, EDMOperation.class,
                            "operation.findByInstanceId", "INSTANCEID", linkedEntity.getInstanceId());
                }
                if (linkedEntity.getInstanceId() == null || instance == null) {
                    List<EDMOperation> instanceList = getFromDB(em, EDMOperation.class,
                            "operation.findByUid", "UID", linkedEntity.getUid());

                    instanceList.sort(EDMUtil::compareEntityVersion);

                    instance = !instanceList.isEmpty() ? instanceList.get(0) : null;
                }

                if (instance == null) {
                    EDMEdmEntityId edmOpMetaId = new EDMEdmEntityId();
                    edmOpMetaId.setMetaId(UUID.randomUUID().toString());
                    em.persist(edmOpMetaId);

                    instance = new EDMOperation();
                    instance.setUid(linkedEntity.getUid());
                    instance.setState(State.PLACEHOLDER.toString());
                    instance.setInstanceId(UUID.randomUUID().toString());
                    instance.setEdmEntityIdByMetaId(edmOpMetaId);
                    em.persist(instance);

                }

                EDMDistributionAccessURL edmSupportedOperation = getOneFromDB(em, EDMDistributionAccessURL.class,
                        "accessurl.findByinstanceId",
                        "DISTINST", edmObject.getInstanceId(),
                        "OPINST", instance.getInstanceId());

                if (Objects.nonNull(edmSupportedOperation)) continue;

                EDMDistributionAccessURL edmLink = new EDMDistributionAccessURL();
                edmLink.setDistributionByInstanceDistributionId(edmObject);
                edmLink.setOperationByInstanceOperationId(instance);

                em.persist(edmLink);

                edmObject.getAccessURLByInstanceId().add(edmLink);
            }
        }

        if (eposDataModelObject.getDescription() != null) {
            edmObject.setDistributionDescriptionsByInstanceId(new ArrayList<>());
            for (String description : eposDataModelObject.getDescription()) {
                EDMDistributionDescription edmDistributionDescription = new EDMDistributionDescription();

                edmDistributionDescription.setId(UUID.randomUUID().toString());
                edmDistributionDescription.setDescription(description);
                edmDistributionDescription.setDistributionByInstanceDistributionId(edmObject);

                edmObject.getDistributionDescriptionsByInstanceId().add(edmDistributionDescription);
            }
        }

        if (eposDataModelObject.getDownloadURL() != null) {
            edmObject.setDistributionDownloadurlsByInstanceId(new ArrayList<>());
            for (String downloadUrl : eposDataModelObject.getDownloadURL()) {
                EDMDistributionDownloadurl edmDistributionDownloadURL = new EDMDistributionDownloadurl();

                edmDistributionDownloadURL.setId(UUID.randomUUID().toString());
                edmDistributionDownloadURL.setDownloadurl(downloadUrl);
                edmDistributionDownloadURL.setDistributionByInstanceDistributionId(edmObject);

                edmObject.getDistributionDownloadurlsByInstanceId().add(edmDistributionDownloadURL);
            }
        }

        edmObject.setFormat(eposDataModelObject.getFormat());

        if (eposDataModelObject.getModified() != null)
            edmObject.setModified(Timestamp.valueOf(eposDataModelObject.getModified()));
        if (eposDataModelObject.getIssued() != null)
            edmObject.setIssued(Timestamp.valueOf(eposDataModelObject.getIssued()));

        edmObject.setLicense(eposDataModelObject.getLicence());

        if (eposDataModelObject.getTitle() != null) {
            edmObject.setDistributionTitlesByInstanceId(new ArrayList<>());
            for (String description : eposDataModelObject.getTitle()) {
                EDMDistributionTitle edmDistributionTitle = new EDMDistributionTitle();

                edmDistributionTitle.setId(UUID.randomUUID().toString());
                edmDistributionTitle.setTitle(description);
                edmDistributionTitle.setDistributionByInstanceDistributionId(edmObject);

                edmObject.getDistributionTitlesByInstanceId().add(edmDistributionTitle);
            }
        }

        edmObject.setType(eposDataModelObject.getType());
        edmObject.setDatapolicy(eposDataModelObject.getDataPolicy());
        edmObject.setConformsto(eposDataModelObject.getConformsTo());

        return new LinkedEntity().entityType(entityString)
                .instanceId(edmInstanceId)
                .metaId(edmObject.getEdmEntityIdByMetaId().getMetaId())
                .uid(eposDataModelObject.getUid());

    }

    @Override
    protected Distribution mapFromDB(Object edmObject) {
        Distribution o = new Distribution();

        EDMDistribution edm = (EDMDistribution) edmObject;
        if (!metadataMode) {
            o.setInstanceId(edm.getInstanceId());
            o.setMetaId(edm.getMetaId());
            o.setState(State.valueOf(edm.getState()));
            o.setOperation(edm.getOperation());
            if (edm.getEdmEntityIdByEditorMetaId() != null ) {
                o.setEditorId(edm.getEdmEntityIdByEditorMetaId().getMetaId());
            }
            o.setChangeComment(edm.getChangeComment());
            o.setVersion(edm.getVersion());
            o.setChangeTimestamp(
                    edm.getChangeTimestamp() != null ? edm.getChangeTimestamp().toLocalDateTime() : null
            );
            o.setToBeDelete(edm.getToBeDeleted() != null ? edm.getToBeDeleted().toString() : "false");
            o.setInstanceChangedId(edm.getInstanceChangedId());
            o.setFileProvenance(edm.getFileprovenance());

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


            o.setDataProduct(
                    edm.getIsDistributionsByInstanceId() != null ?
                            edm.getIsDistributionsByInstanceId().stream()
                                    .map(EDMIsDistribution::getDataproductByInstanceDataproductId)
                                    .map(d -> new LinkedEntity()
                                            .uid(d.getUid())
                                            .instanceId(d.getInstanceId())
                                            .entityType("dataproduct")
                                            .metaId(d.getMetaId()))
                                    .collect(Collectors.toList()) : null
            );

        }

        o.setUid(edm.getUid());
        if (edm.getWebserviceByAccessService() != null) {
            o.setAccessService(
                    new LinkedEntity()
                            .metaId(edm.getWebserviceByAccessService().getMetaId())
                            .uid(edm.getWebserviceByAccessService().getUid())
                            .instanceId(edm.getWebserviceByAccessService().getInstanceId())
                            .entityType("webservice")
            );
        }
        if (edm.getAccessURLByInstanceId() != null) {
            o.setAccessURL(new LinkedList<>());
            edm.getAccessURLByInstanceId().stream()
                    .map(EDMDistributionAccessURL::getOperationByInstanceOperationId)
                    .forEach(e -> o.getAccessURL().add(
                            new LinkedEntity()
                                    .metaId(e.getMetaId())
                                    .instanceId(e.getInstanceId())
                                    .uid(e.getUid())
                                    .entityType("operation")));
        }
        o.setDescription(
                edm.getDistributionDescriptionsByInstanceId() != null ?
                        edm.getDistributionDescriptionsByInstanceId().stream()
                                .map(EDMDistributionDescription::getDescription)
                                .collect(Collectors.toList())
                        : null
        );
        o.setDownloadURL(
                edm.getDistributionDownloadurlsByInstanceId() != null ?
                        edm.getDistributionDownloadurlsByInstanceId().stream()
                                .map(EDMDistributionDownloadurl::getDownloadurl).collect(Collectors.toList()) :
                        null
        );
        o.setFormat(edm.getFormat());
        o.setIssued(
                edm.getIssued() != null ? edm.getIssued().toLocalDateTime() : null
        );
        o.setModified(
                edm.getModified() != null ? edm.getModified().toLocalDateTime() : null
        );
        o.setTitle(
                edm.getDistributionTitlesByInstanceId() != null ?
                        edm.getDistributionTitlesByInstanceId().stream()
                                .map(EDMDistributionTitle::getTitle)
                                .collect(Collectors.toList())
                        : null
        );
        o.setType(edm.getType());
        o.setLicence(edm.getLicense());
        o.setDataPolicy(edm.getDatapolicy());
        o.setConformsTo(edm.getConformsto());
        return o;
    }

}
