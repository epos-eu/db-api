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

public class FacilityDBAPI extends AbstractDBAPI<Facility> {

    public FacilityDBAPI() {
        super("facility", EDMFacility.class);
    }

    @Override
    public LinkedEntity save(Facility eposDataModelObject, EntityManager em, String edmInstanceId) {
        if (eposDataModelObject.getState().equals(State.PUBLISHED)
                && isAlreadyPublished(EDMFacility.class, "facility.findByUidAndState", em, eposDataModelObject))
            return new LinkedEntity();

        //search for a existing instance placeholder to be populated
        EDMFacility edmObject = getOneFromDB(em, EDMFacility.class,
                "facility.findByUidAndState",
                "UID", eposDataModelObject.getUid(),
                "STATE", State.PLACEHOLDER.toString());

        //if there's a placeholder for the entity check if is passed a specific metaid
        //only if the metaid is the same of the placeholder merge the two (the placeholder and the passed entity)
        EDMEdmEntityId edmMetaId;

        if (edmObject != null &&
                (eposDataModelObject.getMetaId() == null || (eposDataModelObject.getMetaId() != null && eposDataModelObject.getMetaId().equals(edmObject.getMetaId())))) {
            em.merge(edmObject);
        } else {
            edmObject = new EDMFacility();
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
            EDMFacility changedInstance = getOneFromDB(em, EDMFacility.class, "facility.findByInstanceId",
                    "INSTANCEID", eposDataModelObject.getInstanceChangedId());
            if (changedInstance == null) {
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: " + edmObject.getUid() + ", state: " + edmObject.getState()
                        + " and instanceId: " + edmObject.getInstanceId() + ", have an invalid 'InstanceChangedId'.");
            }
            edmObject.setFacilityByInstanceChangedId(changedInstance);
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

        if (eposDataModelObject.getAddress() != null) {
            EDMAddress edmAddress = new EDMAddress();
            edmAddress.setId(UUID.randomUUID().toString());
            edmAddress.setStreet(eposDataModelObject.getAddress().getStreet());
            edmAddress.setPostalCode(eposDataModelObject.getAddress().getPostalCode());
            edmAddress.setCountry(eposDataModelObject.getAddress().getCountry());
            edmAddress.setCountrycode(eposDataModelObject.getAddress().getCountryCode());
            edmAddress.setLocality(eposDataModelObject.getAddress().getLocality());
            edmObject.setAddressByAddressId(edmAddress);
        }

        if (eposDataModelObject.getCategory() != null) {
            edmObject.setFacilityCategoriesByInstanceId(new LinkedList<>());
            for (String categoryName : eposDataModelObject.getCategory()) {
                EDMCategory edmCategory = getOneFromDB(em, EDMCategory.class, "EDMCategory.findByUid",
                        "NAME", categoryName);

                if (edmCategory == null) {
                    edmCategory = new EDMCategory();
                    edmCategory.setUid(categoryName);
                    edmCategory.setId(UUID.randomUUID().toString());
                    em.persist(edmCategory);
                }

                EDMFacilityCategory edmFacilityCategory = new EDMFacilityCategory();
                edmFacilityCategory.setCategoryByCategoryId(edmCategory);
                edmFacilityCategory.setFacilityByInstanceFacilityId(edmObject);

                em.persist(edmFacilityCategory);

                if (edmCategory.getFacilityCategoriesById() == null)
                    edmCategory.setFacilityCategoriesById(new ArrayList<>());

                edmCategory.getFacilityCategoriesById().add(edmFacilityCategory);
                edmObject.getFacilityCategoriesByInstanceId().add(edmFacilityCategory);
            }
        }

        if (eposDataModelObject.getContactPoint() != null) {
            edmObject.setContactpointFacilitiesByInstanceId(new ArrayList<>());
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


                EDMContactpointFacility edmContactpointFacility = new EDMContactpointFacility();
                edmContactpointFacility.setFacilityByInstanceFacilityId(edmObject);
                edmContactpointFacility.setContactpointByInstanceContactpointId(edmContactPoint);

                edmObject.getContactpointFacilitiesByInstanceId().add(edmContactpointFacility);
            }
        }

        edmObject.setDescription(eposDataModelObject.getDescription());

        if (eposDataModelObject.getIsPartOf() != null) {
            edmObject.setFacilityFacilitiesByInstanceId(new ArrayList<>());
            for (LinkedEntity linkedEntity : eposDataModelObject.getIsPartOf()) {

                EDMFacility instance = null;

                if (linkedEntity.getInstanceId() != null) {
                    instance = getOneFromDB(em, EDMFacility.class,
                            "facility.findByInstanceId", "INSTANCEID", linkedEntity.getInstanceId());
                }
                if (linkedEntity.getInstanceId() == null || instance == null) {
                    List<EDMFacility> instanceList = getFromDB(em, EDMFacility.class,
                            "facility.findByUid", "UID", linkedEntity.getUid());

                    instanceList.sort(EDMUtil::compareEntityVersion);

                    instance = !instanceList.isEmpty() ? instanceList.get(0) : null;
                }

                if (instance == null) {
                    EDMEdmEntityId edmInstaceMetaId = new EDMEdmEntityId();
                    edmInstaceMetaId.setMetaId(UUID.randomUUID().toString());
                    em.persist(edmInstaceMetaId);

                    instance = new EDMFacility();
                    instance.setUid(linkedEntity.getUid());
                    instance.setState(State.PLACEHOLDER.toString());
                    instance.setInstanceId(UUID.randomUUID().toString());
                    instance.setEdmEntityIdByMetaId(edmInstaceMetaId);
                    em.persist(instance);

                }

                EDMFacilityFacility edmFacilityFacility = new EDMFacilityFacility();
                edmFacilityFacility.setFacilityByInstanceFacility1Id(edmObject);
                edmFacilityFacility.setFacilityByInstanceFacility2Id(instance);

                edmObject.getFacilityFacilitiesByInstanceId().add(edmFacilityFacility);

            }
        }


        if (eposDataModelObject.getPageURL() != null) {
            edmObject.setFacilityPageurlsByInstanceId(new LinkedList<>());
            for (String item : eposDataModelObject.getPageURL()) {
                EDMFacilityPageurl edmFacilityPageurl = new EDMFacilityPageurl();

                edmFacilityPageurl.setId(UUID.randomUUID().toString());
                edmFacilityPageurl.setPageurl(item);
                edmFacilityPageurl.setFacilityByInstanceFacilityId(edmObject);

                edmObject.getFacilityPageurlsByInstanceId().add(edmFacilityPageurl);
            }
        }


        if (eposDataModelObject.getRelation() != null) {
            edmObject.setFacilityServicesByInstanceId(new ArrayList<>());
            for (LinkedEntity linkedEntity : eposDataModelObject.getRelation()) {

                EDMService instance = null;

                if (linkedEntity.getInstanceId() != null) {
                    instance = getOneFromDB(em, EDMService.class,
                            "service.findByInstanceId", "INSTANCEID", linkedEntity.getInstanceId());
                }
                if (linkedEntity.getInstanceId() == null || instance == null) {
                    List<EDMService> instanceList = getFromDB(em, EDMService.class,
                            "service.findByUid", "UID", linkedEntity.getUid());

                    instanceList.sort(EDMUtil::compareEntityVersion);

                    instance = !instanceList.isEmpty() ? instanceList.get(0) : null;
                }

                if (instance == null) {
                    EDMEdmEntityId edmMetaService = new EDMEdmEntityId();
                    edmMetaService.setMetaId(UUID.randomUUID().toString());
                    em.persist(edmMetaService);

                    instance = new EDMService();
                    instance.setIdentifier(linkedEntity.getUid());
                    instance.setUid(linkedEntity.getUid());
                    instance.setState(State.PLACEHOLDER.toString());
                    instance.setInstanceId(UUID.randomUUID().toString());
                    instance.setEdmEntityIdByMetaId(edmMetaService);
                    em.persist(instance);

                }

                EDMFacilityService edmContactpointDataproduct = new EDMFacilityService();
                edmContactpointDataproduct.setFacilityByInstanceFacilityId(edmObject);
                edmContactpointDataproduct.setServiceByInstanceServiceId(instance);


                edmObject.getFacilityServicesByInstanceId().add(edmContactpointDataproduct);
            }
        }

        if (eposDataModelObject.getSpatialExtent() != null) {
            edmObject.setFacilitySpatialsByInstanceId(new ArrayList<>());
            for (Location location : eposDataModelObject.getSpatialExtent()) {
                if (location.getLocation() == null)
                    continue;

                EDMFacilitySpatial spatial = new EDMFacilitySpatial();

                spatial.setId(UUID.randomUUID().toString());
                spatial.setFacilityByInstanceFacilityId(edmObject);
                spatial.setLocation(location.getLocation());

                edmObject.getFacilitySpatialsByInstanceId().add(spatial);
            }
        }

        edmObject.setTitle(eposDataModelObject.getTitle());
        edmObject.setType(eposDataModelObject.getType());

        return new LinkedEntity().entityType(entityString)
                .instanceId(edmInstanceId)
                .metaId(edmObject.getEdmEntityIdByMetaId().getMetaId())
                .uid(eposDataModelObject.getUid());

    }

    @Override
    protected Facility mapFromDB(Object edmObject) {
        Facility o = new Facility();

        EDMFacility edm = (EDMFacility) edmObject;

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

        o.setTitle(edm.getTitle());
        o.setUid(edm.getUid());
        o.setAddress(
                edm.getAddressByAddressId() != null ?
                        new Address()
                                .country(edm.getAddressByAddressId().getCountry())
                                .countryCode(edm.getAddressByAddressId().getCountrycode())
                                .locality(edm.getAddressByAddressId().getLocality())
                                .postalCode(edm.getAddressByAddressId().getPostalCode())
                                .street(edm.getAddressByAddressId().getStreet())
                        : null
        );
        o.setCategory(
                edm.getFacilityCategoriesByInstanceId() != null ?
                        edm.getFacilityCategoriesByInstanceId().stream()
                                .map(EDMFacilityCategory::getCategoryByCategoryId)
                                .map(EDMCategory::getName)
                                .collect(Collectors.toList())
                        : null
        );
        if (edm.getContactpointFacilitiesByInstanceId() != null) {
            o.setContactPoint(new LinkedList<>());
            edm.getContactpointFacilitiesByInstanceId().stream()
                    .map(EDMContactpointFacility::getContactpointByInstanceContactpointId)
                    .forEach(e -> o.getContactPoint().add(
                            new LinkedEntity()
                                    .metaId(e.getMetaId())
                                    .instanceId(e.getInstanceId())
                                    .uid(e.getUid())
                                    .entityType("ContactPoint")));
        }
        o.setDescription(edm.getDescription());

        if (edm.getFacilityFacilitiesByInstanceId() != null) {
            o.setIsPartOf(new LinkedList<>());
            edm.getFacilityFacilitiesByInstanceId().stream()
                    .map(EDMFacilityFacility::getFacilityByInstanceFacility2Id)
                    .forEach(e -> o.getIsPartOf().add(
                            new LinkedEntity()
                                    .metaId(e.getMetaId())
                                    .instanceId(e.getInstanceId())
                                    .uid(e.getUid())
                                    .entityType("Facility")));
        }


        o.setPageURL(
                edm.getFacilityPageurlsByInstanceId() != null ?
                        edm.getFacilityPageurlsByInstanceId().stream()
                                .map(EDMFacilityPageurl::getPageurl)
                                .collect(Collectors.toList()) : null
        );
        if (edm.getFacilityServicesByInstanceId() != null) {
            o.setRelation(new LinkedList<>());
            edm.getFacilityServicesByInstanceId().stream()
                    .map(EDMFacilityService::getServiceByInstanceServiceId)
                    .forEach(e -> o.getRelation().add(
                            new LinkedEntity()
                                    .metaId(e.getMetaId())
                                    .instanceId(e.getInstanceId())
                                    .uid(e.getUid())
                                    .entityType("Service")));
        }
        o.setSpatialExtent(
                edm.getFacilitySpatialsByInstanceId() != null ?
                        new ArrayList<>(edm.getFacilitySpatialsByInstanceId().stream()
                                .map(s -> {
                                    Location l = new Location();
                                    l.setLocation(s.getLocation());
                                    return l;
                                }).collect(Collectors.toList()))
                        : null
        );
        return o;
    }

}
