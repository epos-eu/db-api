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

public class ServiceDBAPI extends AbstractDBAPI<Service> {


    public ServiceDBAPI() {
        super("service", EDMService.class);
    }

    @Override
    public LinkedEntity save(Service eposDataModelObject, EntityManager em, String edmInstanceId) {
        if (eposDataModelObject.getState().equals(State.PUBLISHED)
                && isAlreadyPublished(EDMService.class, "service.findByUidAndState", em, eposDataModelObject))
            return new LinkedEntity();

        //search for a existing instance placeholder to be populated
        EDMService edmObject = getOneFromDB(em, EDMService.class,
                "service.findByUid",
                "UID", eposDataModelObject.getUid());

        //if there's a placeholder for the entity check if is passed a specific metaid
        //only if the metaid is the same of the placeholder merge the two (the placeholder and the passed entity)
        EDMEdmEntityId edmMetaId;

        if (edmObject != null &&
                (eposDataModelObject.getMetaId() == null || (eposDataModelObject.getMetaId() != null && eposDataModelObject.getMetaId().equals(edmObject.getMetaId())))) {
            em.merge(edmObject);
        } else {
            edmObject = new EDMService();
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
            EDMService changedInstance = getOneFromDB(em, EDMService.class, "service.findByInstanceId",
                    "INSTANCEID", eposDataModelObject.getInstanceChangedId());
            if (changedInstance == null) {
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: " + edmObject.getUid() + ", state: " + edmObject.getState()
                        + " and instanceId: " + edmObject.getInstanceId() + ", have an invalid 'InstanceChangedId'.");
            }
            edmObject.setServiceByInstanceChangedId(changedInstance);
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
        }

        edmObject.setFileprovenance(eposDataModelObject.getFileProvenance());
        edmObject.setChangeTimestamp(new Timestamp(System.currentTimeMillis()));
        edmObject.setOperation(eposDataModelObject.getOperation());
        edmObject.setChangeComment(eposDataModelObject.getChangeComment());
        edmObject.setVersion(eposDataModelObject.getVersion());
        edmObject.setState(eposDataModelObject.getState().toString());
        edmObject.setToBeDeleted(Boolean.valueOf(eposDataModelObject.getToBeDelete()));

        if (eposDataModelObject.getCategory() != null) {
            edmObject.setServiceCategoriesByInstanceId(new LinkedList<>());
            for (String categoryName : eposDataModelObject.getCategory()) {
                EDMCategory edmCategory = getOneFromDB(em, EDMCategory.class, "EDMCategory.findByUid",
                        "UID", categoryName);

                if (edmCategory == null) {
                    edmCategory = new EDMCategory();
                    edmCategory.setUid(categoryName);
                    edmCategory.setId(UUID.randomUUID().toString());
                    em.persist(edmCategory);
                }

                EDMServiceCategory edmServiceCategory = new EDMServiceCategory();
                edmServiceCategory.setCategoryByCategoryId(edmCategory);
                edmServiceCategory.setServiceByInstanceServiceId(edmObject);

                em.persist(edmServiceCategory);

                if (edmCategory.getServiceCategoriesById() == null)
                    edmCategory.setServiceCategoriesById(new ArrayList<>());

                edmCategory.getServiceCategoriesById().add(edmServiceCategory);
                edmObject.getServiceCategoriesByInstanceId().add(edmServiceCategory);
            }
        }

        if (eposDataModelObject.getContactPoint() != null) {
            edmObject.setContactpointServicesByInstanceId(new ArrayList<>());
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


                EDMContactpointService edmContactpointService = new EDMContactpointService();
                edmContactpointService.setServiceByInstanceServiceId(edmObject);
                edmContactpointService.setContactpointByInstanceContactpointId(edmContactPoint);


                edmObject.getContactpointServicesByInstanceId().add(edmContactpointService);
            }
        }

        edmObject.setDescription(eposDataModelObject.getDescription());
        edmObject.setIdentifier(eposDataModelObject.getIdentifier());
        edmObject.setKeywords(eposDataModelObject.getKeywords());
        edmObject.setName(eposDataModelObject.getName());
        edmObject.setPageurl(eposDataModelObject.getPageURL());

        if (eposDataModelObject.getProvider() != null) {
            List<EDMOrganization> edmOrganizations = getFromDB(em, EDMOrganization.class,
                    "organization.findByUid", "UID", eposDataModelObject.getProvider());

            edmOrganizations.sort(EDMUtil::compareEntityVersion);

            EDMOrganization edmOrganization = !edmOrganizations.isEmpty() ?
                    edmOrganizations.get(0) : null;

            EDMEdmEntityId edmMetaOrganization;

            if (edmOrganization == null) {
                edmOrganization = new EDMOrganization();
                edmOrganization.setUid(eposDataModelObject.getProvider());
                edmOrganization.setState(State.PLACEHOLDER.toString());
                edmOrganization.setInstanceId(UUID.randomUUID().toString());
                em.persist(edmOrganization);

                edmMetaOrganization = new EDMEdmEntityId();
                edmMetaOrganization.setMetaId(UUID.randomUUID().toString());
                em.persist(edmMetaOrganization);

                edmOrganization.setEdmEntityIdByMetaId(edmMetaOrganization);
            } else {
                edmMetaOrganization = edmOrganization.getEdmEntityIdByMetaId();
            }

            edmObject.setEdmEntityIdByProvider(edmMetaOrganization);
        }

        if (eposDataModelObject.getSpatialExtent() != null) {
            edmObject.setServiceSpatialsByInstanceId(new ArrayList<>());
            for (Location location : eposDataModelObject.getSpatialExtent()) {
                if (location.getLocation() == null)
                    continue;

                EDMServiceSpatial edmServiceSpatial = new EDMServiceSpatial();

                edmServiceSpatial.setId(UUID.randomUUID().toString());
                edmServiceSpatial.setServiceByInstanceServiceId(edmObject);
                edmServiceSpatial.setLocation(location.getLocation());

                edmObject.getServiceSpatialsByInstanceId().add(edmServiceSpatial);
            }
        }

        if (eposDataModelObject.getTemporalExtent() != null) {
            edmObject.setServiceTemporalsByInstanceId(new ArrayList<>());
            for (PeriodOfTime temporal : eposDataModelObject.getTemporalExtent()) {
                EDMServiceTemporal edmServiceTemporal = new EDMServiceTemporal();
                edmServiceTemporal.setId(UUID.randomUUID().toString());
                edmServiceTemporal.setStartdate(
                        temporal.getStartDate() != null ?
                                Timestamp.valueOf(temporal.getStartDate())
                                : null
                );
                edmServiceTemporal.setEnddate(
                        temporal.getEndDate() != null ?
                                Timestamp.valueOf(temporal.getEndDate())
                                : null
                );
                edmServiceTemporal.setServiceByInstanceServiceId(edmObject);
                edmObject.getServiceTemporalsByInstanceId().add(edmServiceTemporal);
            }
        }

        edmObject.setType(eposDataModelObject.getType());
        //TODO come fare?

        return new LinkedEntity().entityType(entityString)
                .instanceId(edmInstanceId)
                .metaId(edmObject.getEdmEntityIdByMetaId().getMetaId())
                .uid(eposDataModelObject.getUid());

    }

    @Override
    protected Service mapFromDB(Object edmObject) {

        EDMService edm = (EDMService) edmObject;

        Service o = new Service().keywords(edm.getKeywords());
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
        }

        o.setUid(edm.getUid());
        o.setIdentifier(edm.getIdentifier());
        o.setCategory(
                edm.getServiceCategoriesByInstanceId() != null ?
                        edm.getServiceCategoriesByInstanceId().stream()
                                .map(EDMServiceCategory::getCategoryByCategoryId)
                                .map(EDMCategory::getName)
                                .collect(Collectors.toList())
                        : null
        );
        if (edm.getContactpointServicesByInstanceId() != null) {
            o.setContactPoint(new LinkedList<>());
            edm.getContactpointServicesByInstanceId().stream()
                    .map(EDMContactpointService::getContactpointByInstanceContactpointId)
                    .forEach(e -> o.getContactPoint().add(
                            new LinkedEntity()
                                    .metaId(e.getMetaId())
                                    .instanceId(e.getInstanceId())
                                    .uid(e.getUid())
                                    .entityType("ContactPoint")));
        }
        o.setDescription(edm.getDescription());
        o.setName(edm.getName());
        o.setPageURL(edm.getPageurl());
        if (edm.getEdmEntityIdByProvider() != null && edm.getEdmEntityIdByProvider().getOrganizationsByMetaId() != null
                && !edm.getEdmEntityIdByProvider().getOrganizationsByMetaId().isEmpty()) {
            List<EDMOrganization> so = new ArrayList<>(edm.getEdmEntityIdByProvider().getOrganizationsByMetaId());
            so.sort(EDMUtil::compareEntityVersion);
            o.setProvider(so.get(0).getUid());
        }
        o.setSpatialExtent(
                edm.getServiceSpatialsByInstanceId() != null ?
                        new ArrayList<>(edm.getServiceSpatialsByInstanceId().stream()
                                .map(s -> {
                                    Location l = new Location();
                                    l.setLocation(s.getLocation());
                                    return l;
                                }).collect(Collectors.toList()))
                        : null
        );
        o.setTemporalExtent(
                edm.getServiceTemporalsByInstanceId() != null ?
                        edm.getServiceTemporalsByInstanceId().stream()
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
        o.setType(edm.getType());

        return o;
    }

}
