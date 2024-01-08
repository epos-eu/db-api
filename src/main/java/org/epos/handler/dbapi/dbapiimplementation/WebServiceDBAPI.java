package org.epos.handler.dbapi.dbapiimplementation;

import org.epos.eposdatamodel.*;
import org.epos.handler.dbapi.model.*;
import org.epos.handler.dbapi.util.EDMUtil;
import org.epos.handler.dbapi.util.LoggerFormat;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static org.epos.handler.dbapi.util.DBUtil.getFromDB;
import static org.epos.handler.dbapi.util.DBUtil.getOneFromDB;

public class WebServiceDBAPI extends AbstractDBAPI<WebService> {

    public WebServiceDBAPI() {
        super("webservice", EDMWebservice.class);
    }

    @Override
    public LinkedEntity save(WebService eposDataModelObject, EntityManager em, String edmInstanceId) {
        if (eposDataModelObject.getState().equals(State.PUBLISHED)
                && isAlreadyPublished(EDMWebservice.class, "webservice.findByUidAndState", em, eposDataModelObject))
            return new LinkedEntity();

        //search for a existing instance placeholder to be populated
        EDMWebservice edmObject = getOneFromDB(em, EDMWebservice.class,
                "webservice.findByUid",
                "UID", eposDataModelObject.getUid());

        //if there's a placeholder for the entity check if is passed a specific metaid
        //only if the metaid is the same of the placeholder merge the two (the placeholder and the passed entity)
        EDMEdmEntityId edmMetaId;

        boolean merged = false;
        if (edmObject != null &&
                (eposDataModelObject.getMetaId() == null || (eposDataModelObject.getMetaId() != null && eposDataModelObject.getMetaId().equals(edmObject.getMetaId())))) {
            //em.merge(edmObject);
            merged = true;
        } else {
            edmObject = new EDMWebservice();
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
        edmObject.setUid(eposDataModelObject.getUid());

        if (Objects.nonNull(eposDataModelObject.getGroups())){
            for (Group group : eposDataModelObject.getGroups()){

                EDMGroup edmGroup =  getOneFromDB(em, EDMGroup.class, "group.findById",
                        "ID", group.getId());

                if (Objects.isNull(edmGroup)){
                    em.getTransaction().rollback();
                    throw new IllegalArgumentException(LoggerFormat.log(eposDataModelObject, "is involved in a non existing group"));
                }

                EDMAuthorization edmAuthorization = getOneFromDB(em, EDMAuthorization.class, "authorization.findByMetaIdAndGroupId",
                        "GROUPID", group.getId(),
                        "METAID", edmObject.getEdmEntityIdByMetaId().getMetaId());

                if (Objects.isNull(edmAuthorization)){
                    edmAuthorization = new EDMAuthorization();
                    edmAuthorization.setEdmEntityIdByMetaId(edmObject.getEdmEntityIdByMetaId());
                    edmAuthorization.setGroupByGroupId(edmGroup);
                    em.persist(edmAuthorization);
                }
            }
        }


        if (eposDataModelObject.getInstanceChangedId() != null) {
            EDMWebservice changedInstance = getOneFromDB(em, EDMWebservice.class, "webservice.findByInstanceId",
                    "INSTANCEID", eposDataModelObject.getInstanceChangedId());
            if (changedInstance == null) {
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: " + edmObject.getUid() + ", state: " + edmObject.getState()
                        + " and instanceId: " + edmObject.getInstanceId() + ", have an invalid 'InstanceChangedId'.");
            }
            edmObject.setWebserviceByInstanceChangedId(changedInstance);
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


        if (eposDataModelObject.getCategory() != null) {
            for (String categoryName : eposDataModelObject.getCategory()) {
                EDMCategory edmCategory = getOneFromDB(em, EDMCategory.class, "EDMCategory.findByUid",
                        "UID", categoryName);

                if (edmCategory == null) {
                    edmCategory = new EDMCategory();
                    edmCategory.setUid(categoryName);
                    edmCategory.setId(UUID.randomUUID().toString());
                    em.persist(edmCategory);
                }

                EDMWebserviceCategory edmWebserviceCategory = new EDMWebserviceCategory();
                edmWebserviceCategory.setCategoryByCategoryId(edmCategory);
                edmWebserviceCategory.setWebserviceByInstanceWebserviceId(edmObject);

                em.persist(edmWebserviceCategory);

                if (edmCategory.getWebserviceCategoriesById() == null)
                    edmCategory.setWebserviceCategoriesById(new ArrayList<>());

                edmCategory.getWebserviceCategoriesById().add(edmWebserviceCategory);
                edmObject.getWebserviceCategoriesByInstanceId().add(edmWebserviceCategory);
            }
        }

        if (eposDataModelObject.getContactPoint() != null) {
            edmObject.setContactpointWebservicesByInstanceId(new ArrayList<>());
            for (LinkedEntity contactpointLinked : eposDataModelObject.getContactPoint()) {

                EDMContactpoint edmContactPoint = null;

                // First check if a instanceId is passed, in that case link the connected contactpoint,
                // Otherwise just use the uid and take an already existing contactpoint (preferably a PUBLISHED one)
                if (contactpointLinked.getInstanceId() != null) {
                    edmContactPoint = getOneFromDB(em, EDMContactpoint.class,
                            "contactpoint.findByInstanceId", "INSTANCEID", contactpointLinked.getInstanceId());
                }
                if (contactpointLinked.getInstanceId() == null || edmContactPoint == null) {
                    List<EDMContactpoint> edmContactPoints = getFromDB(em, EDMContactpoint.class,
                            "contactpoint.findByUid", "UID", contactpointLinked.getUid());

                    edmContactPoints.sort(EDMUtil::compareEntityVersion);

                    edmContactPoint = !edmContactPoints.isEmpty() ? edmContactPoints.get(0) : null;
                }

                if (edmContactPoint == null) {
                    EDMEdmEntityId edmContactPointMetaId = new EDMEdmEntityId();
                    edmContactPointMetaId.setMetaId(UUID.randomUUID().toString());
                    em.persist(edmContactPointMetaId);

                    edmContactPoint = new EDMContactpoint();
                    edmContactPoint.setUid(contactpointLinked.getUid());
                    edmContactPoint.setState(State.PLACEHOLDER.toString());
                    edmContactPoint.setInstanceId(UUID.randomUUID().toString());
                    edmContactPoint.setEdmEntityIdByMetaId(edmContactPointMetaId);
                    em.persist(edmContactPoint);
                }

                EDMContactpointWebservice edmContactpointDataproduct = new EDMContactpointWebservice();
                edmContactpointDataproduct.setWebserviceByInstanceWebserviceId(edmObject);
                edmContactpointDataproduct.setContactpointByInstanceContactpointId(edmContactPoint);

                em.persist(edmContactpointDataproduct);

                edmObject.getContactpointWebservicesByInstanceId().add(edmContactpointDataproduct);
            }
        }

        if (eposDataModelObject.getDatePublished() != null)
            edmObject.setDatapublished(Timestamp.valueOf(eposDataModelObject.getDatePublished()));
        if (eposDataModelObject.getDateModified() != null)
            edmObject.setDatamodified(Timestamp.valueOf(eposDataModelObject.getDateModified()));

        edmObject.setName(eposDataModelObject.getName());
        edmObject.setDescription(eposDataModelObject.getDescription());

        if (eposDataModelObject.getDocumentation() != null) {
            edmObject.setWebserviceDocumentationsByInstanceId(new ArrayList<>());
            for (Documentation documentation : eposDataModelObject.getDocumentation()) {
                EDMWebserviceDocumentation edmWebserviceDocumentation = new EDMWebserviceDocumentation();

                edmWebserviceDocumentation.setId(UUID.randomUUID().toString());
                edmWebserviceDocumentation.setDocumentation(documentation.getUri());
                edmWebserviceDocumentation.setWebserviceByInstanceWebserviceId(edmObject);

                edmObject.getWebserviceDocumentationsByInstanceId().add(edmWebserviceDocumentation);
            }
        }

        edmObject.setEntrypoint(eposDataModelObject.getEntryPoint());
        edmObject.setKeywords(eposDataModelObject.getKeywords());
        edmObject.setLicense(eposDataModelObject.getLicense());

        if (eposDataModelObject.getProvider() != null) {
            List<EDMOrganization> edmOrganizations = getFromDB(em, EDMOrganization.class,
                    "organization.findByUid", "UID", eposDataModelObject.getProvider().getUid());

            edmOrganizations.sort(EDMUtil::compareEntityVersion);

            EDMOrganization edmOrganization = !edmOrganizations.isEmpty() ?
                    edmOrganizations.get(0) : null;

            EDMEdmEntityId edmPersonMetaId;

            if (edmOrganization == null) {
                edmPersonMetaId = new EDMEdmEntityId();
                edmPersonMetaId.setMetaId(UUID.randomUUID().toString());
                em.persist(edmPersonMetaId);

                edmOrganization = new EDMOrganization();
                edmOrganization.setUid(eposDataModelObject.getProvider().getUid());
                edmOrganization.setState(State.PLACEHOLDER.toString());
                edmOrganization.setInstanceId(UUID.randomUUID().toString());
                em.persist(edmOrganization);


                edmOrganization.setEdmEntityIdByMetaId(edmPersonMetaId);
            } else {
                edmPersonMetaId = edmOrganization.getEdmEntityIdByMetaId();
            }

            edmObject.setEdmEntityIdByProvider(edmPersonMetaId);
        }

        if (eposDataModelObject.getSpatialExtent() != null) {
            edmObject.setWebserviceSpatialsByInstanceId(new ArrayList<>());
            for (Location location : eposDataModelObject.getSpatialExtent()) {
                if (location.getLocation() == null)
                    continue;

                EDMWebserviceSpatial edmWebserviceSpatial = new EDMWebserviceSpatial();

                edmWebserviceSpatial.setId(UUID.randomUUID().toString());
                edmWebserviceSpatial.setWebserviceByInstanceWebserviceId(edmObject);
                edmWebserviceSpatial.setLocation(location.getLocation());

                edmObject.getWebserviceSpatialsByInstanceId().add(edmWebserviceSpatial);
            }
        }

        if (eposDataModelObject.getTemporalExtent() != null) {
            edmObject.setWebserviceTemporalsByInstanceId(new ArrayList<>());
            for (PeriodOfTime temporal : eposDataModelObject.getTemporalExtent()) {
                EDMWebserviceTemporal edmWebserviceTemporal = new EDMWebserviceTemporal();
                edmWebserviceTemporal.setId(UUID.randomUUID().toString());
                edmWebserviceTemporal.setStartdate(
                        temporal.getStartDate() != null ?
                                Timestamp.valueOf(temporal.getStartDate())
                                : null
                );
                edmWebserviceTemporal.setEnddate(
                        temporal.getEndDate() != null ?
                                Timestamp.valueOf(temporal.getEndDate())
                                : null
                );
                edmWebserviceTemporal.setWebserviceByInstanceWebserviceId(edmObject);
                edmObject.getWebserviceTemporalsByInstanceId().add(edmWebserviceTemporal);
            }
        }

        if (eposDataModelObject.getSupportedOperation() != null) {
            edmObject.setSupportedOperationByInstanceId(new ArrayList<>());
            for (LinkedEntity linkedEntity : eposDataModelObject.getSupportedOperation()) {
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

                EDMSupportedOperation edmSupportedOperation = getOneFromDB(em, EDMSupportedOperation.class,
                        "supportedOperation.findByinstanceId",
                        "WSINST", edmObject.getInstanceId(),
                        "OPINST", instance.getInstanceId());

                if (Objects.nonNull(edmSupportedOperation)) continue;

                EDMSupportedOperation edmLink = new EDMSupportedOperation();
                edmLink.setWebserviceByInstanceWebserviceId(edmObject);
                edmLink.setOperationByInstanceOperationId(instance);

                em.persist(edmLink);

                edmObject.getSupportedOperationByInstanceId().add(edmLink);
            }
        }

        if (eposDataModelObject.getRelation() != null) {
            edmObject.setWebserviceRelationByInstanceId(new ArrayList<>());
            for (LinkedEntity linkedEntity : eposDataModelObject.getRelation()) {
                EDMWebservice instance = null;

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
                    EDMEdmEntityId edmOpMetaId = new EDMEdmEntityId();
                    edmOpMetaId.setMetaId(UUID.randomUUID().toString());
                    em.persist(edmOpMetaId);

                    instance = new EDMWebservice();
                    instance.setUid(linkedEntity.getUid());
                    instance.setState(State.PLACEHOLDER.toString());
                    instance.setInstanceId(UUID.randomUUID().toString());
                    instance.setEdmEntityIdByMetaId(edmOpMetaId);
                    em.persist(instance);

                }

                EDMWebserviceRelation edmWebserviceRelation = new EDMWebserviceRelation();
                edmWebserviceRelation.setWebserviceByInstanceWebserviceId(edmObject);
                edmWebserviceRelation.setWebserviceByInstanceWebserviceId_0(instance);
                
                edmObject.getWebserviceRelationByInstanceId().add(edmWebserviceRelation);
            }
        }

        edmObject.setAaaitypes(eposDataModelObject.getAaaiTypes());

        return new LinkedEntity().entityType(entityString)
                .instanceId(edmInstanceId)
                .metaId(edmObject.getEdmEntityIdByMetaId().getMetaId())
                .uid(eposDataModelObject.getUid());

    }

    @Override
    protected WebService mapFromDB(Object edmObject) {

        EDMWebservice edm = (EDMWebservice) edmObject;

        WebService o = new WebService().keywords(edm.getKeywords());
        if (!metadataMode) {
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
            o.setChangeComment(edm.getChangeComment());
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

            o.setDistribution(
                    edm.getDistributionByInstanceId() != null ?
                            edm.getDistributionByInstanceId().stream()
                                    .map(d -> new LinkedEntity()
                                            .uid(d.getUid())
                                            .instanceId(d.getInstanceId())
                                            .entityType("Distribution")
                                            .metaId(d.getMetaId()))
                                    .collect(Collectors.toList()) : null
            );
        }

        o.setUid(edm.getUid());
        o.setCategory(
                edm.getWebserviceCategoriesByInstanceId() != null ?
                        edm.getWebserviceCategoriesByInstanceId().stream()
                                .map(EDMWebserviceCategory::getCategoryByCategoryId)
                                .map(EDMCategory::getName)
                                .collect(Collectors.toList())
                        : null
        );
        if (edm.getContactpointWebservicesByInstanceId() != null) {
            o.setContactPoint(new LinkedList<>());
            edm.getContactpointWebservicesByInstanceId().stream()
                    .map(EDMContactpointWebservice::getContactpointByInstanceContactpointId)
                    .forEach(e -> o.getContactPoint().add(
                            new LinkedEntity()
                                    .metaId(e.getMetaId())
                                    .instanceId(e.getInstanceId())
                                    .uid(e.getUid())
                                    .entityType("ContactPoint")));
        }
        o.setDateModified(
                edm.getDatamodified() != null ? edm.getDatamodified().toLocalDateTime() : null
        );
        o.setDatePublished(
                edm.getDatapublished() != null ? edm.getDatapublished().toLocalDateTime() : null
        );
        o.setDescription(edm.getDescription());
        if (edm.getWebserviceDocumentationsByInstanceId() != null) {
            o.setDocumentation(edm.getWebserviceDocumentationsByInstanceId().stream()
                    .map(EDMWebserviceDocumentation::getDocumentation)
                    .map(x -> new Documentation().uri(x)).collect(Collectors.toCollection(ArrayList::new)));
        } else o.setDocumentation(
                null
        );
        o.setEntryPoint(edm.getEntrypoint());
        o.setLicense(edm.getLicense());
        o.setName(edm.getName());
        if (edm.getEdmEntityIdByProvider() != null && edm.getEdmEntityIdByProvider().getOrganizationsByMetaId() != null
                && !edm.getEdmEntityIdByProvider().getOrganizationsByMetaId().isEmpty()) {
            List<EDMOrganization> so = new ArrayList<>(edm.getEdmEntityIdByProvider().getOrganizationsByMetaId());
            so.sort(EDMUtil::compareEntityVersion);
            o.setProvider(new LinkedEntity().uid(so.get(0).getUid()).entityType("Organization").instanceId(so.get(0).getInstanceId()).metaId(so.get(0).getMetaId()));
        }
        o.setSpatialExtent(
                edm.getWebserviceSpatialsByInstanceId() != null ?
                        new ArrayList<>(edm.getWebserviceSpatialsByInstanceId().stream()
                                .map(s -> {
                                    Location l = new Location();
                                    l.setLocation(s.getLocation());
                                    return l;
                                }).collect(Collectors.toList()))
                        : null
        );
        o.setTemporalExtent(
                edm.getWebserviceTemporalsByInstanceId() != null ?
                        edm.getWebserviceTemporalsByInstanceId().stream()
                                .map(elem -> {
                                    PeriodOfTime periodOfTime = new PeriodOfTime();
                                    periodOfTime.setStartDate(
                                            elem.getStartdate() != null ?
                                                    elem.getStartdate().toLocalDateTime() : null
                                    );
                                    periodOfTime.setEndDate(
                                            elem.getEnddate() != null ?
                                                    elem.getEnddate().toLocalDateTime() : null
                                    );
                                    return periodOfTime;
                                }).collect(Collectors.toList())
                        : new ArrayList<>()
        );
        o.setAaaiTypes(edm.getAaaitypes());
        if (edm.getSupportedOperationByInstanceId() != null) {
            o.setSupportedOperation(new LinkedList<>());
            edm.getSupportedOperationByInstanceId().stream()
                    .map(EDMSupportedOperation::getOperationByInstanceOperationId)
                    .forEach(e -> o.getSupportedOperation().add(
                            new LinkedEntity()
                                    .metaId(e.getMetaId())
                                    .instanceId(e.getInstanceId())
                                    .uid(e.getUid())
                                    .entityType("Operation")));
        }
        if(edm.getWebserviceRelationByInstanceId()!=null) {
            o.setRelation(new LinkedList<>());
            edm.getWebserviceRelationByInstanceId().stream()
                    .map(EDMWebserviceRelation::getWebserviceByInstanceWebserviceId_0)
                    .forEach(e -> o.getRelation().add(
                            new LinkedEntity()
                                    .metaId(e.getMetaId())
                                    .instanceId(e.getInstanceId())
                                    .uid(e.getUid())
                                    .entityType("Webservice")));
        }

        return o;
    }

}
