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

public class EquipmentDBAPI extends AbstractDBAPI<Equipment> {

    public EquipmentDBAPI() {
        super("equipment", EDMEquipment.class);
    }

    @Override
    public LinkedEntity save(Equipment eposDataModelObject, EntityManager em, String edmInstanceId) {
        if (eposDataModelObject.getState().equals(State.PUBLISHED)
                && isAlreadyPublished(EDMEquipment.class, "equipment.findByUidAndState", em, eposDataModelObject))
            return new LinkedEntity();

        //search for a existing instance placeholder to be populated
        EDMEquipment edmObject = getOneFromDB(em, EDMEquipment.class,
                "equipment.findByUidAndState",
                "UID", eposDataModelObject.getUid(),
                "STATE", State.PLACEHOLDER.toString());

        if(edmObject==null) {
			edmObject = getOneFromDB(em, EDMEquipment.class,
					"equipment.findByInstanceId",
					"INSTANCEID", eposDataModelObject.getInstanceId());
		}
        
        //if there's a placeholder for the entity check if is passed a specific metaid
        //only if the metaid is the same of the placeholder merge the two (the placeholder and the passed entity)
        EDMEdmEntityId edmMetaId;

        if (edmObject != null &&
                (eposDataModelObject.getMetaId() == null || (eposDataModelObject.getMetaId() != null && eposDataModelObject.getMetaId().equals(edmObject.getMetaId())))) {
            em.merge(edmObject);
        } else {
            edmObject = new EDMEquipment();
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
            EDMEquipment changedInstance = getOneFromDB(em, EDMEquipment.class, "equipment.findByInstanceId",
                    "INSTANCEID", eposDataModelObject.getInstanceChangedId());
            if (changedInstance == null) {
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: " + edmObject.getUid() + ", state: " + edmObject.getState()
                        + " and instanceId: " + edmObject.getInstanceId() + ", have an invalid 'InstanceChangedId'.");
            }
            edmObject.setEquipmentByInstanceChangedId(changedInstance);
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
            edmObject.setEquipmentCategoriesByInstanceId(new LinkedList<>());
            for (String categoryName : eposDataModelObject.getCategory()) {
                EDMCategory edmCategory = getOneFromDB(em, EDMCategory.class, "EDMCategory.findByUid",
                        "UID", categoryName);

                if (edmCategory == null) {
                    edmCategory = new EDMCategory();
                    edmCategory.setUid(categoryName);
                    edmCategory.setId(UUID.randomUUID().toString());
                    em.persist(edmCategory);
                }

                EDMEquipmentCategory edmEquipmentCategory = new EDMEquipmentCategory();
                edmEquipmentCategory.setCategoryByCategoryId(edmCategory);
                edmEquipmentCategory.setEquipmentByInstanceEquipmentId(edmObject);

                em.persist(edmEquipmentCategory);

                if (edmCategory.getEquipmentCategoriesById() == null)
                    edmCategory.setEquipmentCategoriesById(new ArrayList<>());

                edmCategory.getEquipmentCategoriesById().add(edmEquipmentCategory);
                edmObject.getEquipmentCategoriesByInstanceId().add(edmEquipmentCategory);
            }
        }

        if (eposDataModelObject.getContactPoint() != null) {
            edmObject.setContactpointEquipmentsByInstanceId(new ArrayList<>());
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


                EDMContactpointEquipment edmContactpointEquipment = new EDMContactpointEquipment();
                edmContactpointEquipment.setEquipmentByInstanceEquipmentId(edmObject);
                edmContactpointEquipment.setContactpointByInstanceContactpointId(edmContactPoint);


                edmObject.getContactpointEquipmentsByInstanceId().add(edmContactpointEquipment);
            }
        }

        edmObject.setDescription(eposDataModelObject.getDescription());
        edmObject.setDynamicrange(eposDataModelObject.getDynamicRange());
        edmObject.setFilter(eposDataModelObject.getFilter());

        if (eposDataModelObject.getIsPartOf() != null) {
            edmObject.setEquipmentFacilitiesByInstanceId(new ArrayList<>());
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

                EDMEquipmentFacility edmEquipmentFacility = new EDMEquipmentFacility();
                edmEquipmentFacility.setEquipmentByInstanceEquipmentId(edmObject);
                edmEquipmentFacility.setFacilityByInstanceFacilityId(instance);

                edmObject.getEquipmentFacilitiesByInstanceId().add(edmEquipmentFacility);
            }
        }

        if (eposDataModelObject.getManufacturer() != null) {
            List<EDMOrganization> edmOrganizations = getFromDB(em, EDMOrganization.class,
                    "organization.findByUid", "UID", eposDataModelObject.getManufacturer().getUid());

            edmOrganizations.sort(EDMUtil::compareEntityVersion);

            EDMOrganization edmOrganization = !edmOrganizations.isEmpty() ?
                    edmOrganizations.get(0) : null;

            EDMEdmEntityId edmMetaOrganization;

            if (edmOrganization == null) {
                edmOrganization = new EDMOrganization();
                edmOrganization.setUid(eposDataModelObject.getManufacturer().getUid());
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

            edmObject.setEdmEntityIdByManufacturer(edmMetaOrganization);
        }

        edmObject.setName(eposDataModelObject.getName());
        edmObject.setPageurl(eposDataModelObject.getPageURL());
        edmObject.setOrientation(eposDataModelObject.getOrientation());
        edmObject.setResolution(eposDataModelObject.getResolution());
        //TODO fix this
        //edmObject.setSampleperiod(eposDataModelObject.getSamplePeriod());
        edmObject.setSerialnumber(eposDataModelObject.getSerialNumber());

        if (eposDataModelObject.getSpatialExtent() != null) {
            edmObject.setEquipmentSpatialsByInstanceId(new ArrayList<>());
            for (Location location : eposDataModelObject.getSpatialExtent()) {
                if (location.getLocation() == null)
                    continue;

                EDMEquipmentSpatial spatial = new EDMEquipmentSpatial();

                spatial.setId(UUID.randomUUID().toString());
                spatial.setEquipmentByInstanceEquipmentId(edmObject);
                spatial.setLocation(location.getLocation());

                edmObject.getEquipmentSpatialsByInstanceId().add(spatial);
            }
        }

        if (eposDataModelObject.getTemporalExtent() != null) {
            edmObject.setEquipmentTemporalsByInstanceId(new ArrayList<>());
            for (PeriodOfTime temporal : eposDataModelObject.getTemporalExtent()) {
                EDMEquipmentTemporal edmDataproductTemporal = new EDMEquipmentTemporal();
                edmDataproductTemporal.setId(UUID.randomUUID().toString());
                edmDataproductTemporal.setStartdate(
                        temporal.getStartDate() != null ?
                                Timestamp.valueOf(temporal.getStartDate())
                                : null
                );
                edmDataproductTemporal.setEnddate(
                        temporal.getEndDate() != null ?
                                Timestamp.valueOf(temporal.getEndDate())
                                : null
                );
                edmDataproductTemporal.setEquipmentByInstanceEquipmentId(edmObject);
                edmObject.getEquipmentTemporalsByInstanceId().add(edmDataproductTemporal);
            }
        }

        edmObject.setType(eposDataModelObject.getType());

        return new LinkedEntity().entityType(entityString)
                .instanceId(edmInstanceId)
                .metaId(edmObject.getEdmEntityIdByMetaId().getMetaId())
                .uid(eposDataModelObject.getUid());


    }

    @Override
    protected Equipment mapFromDB(Object edmObject) {
        Equipment o = new Equipment();

        EDMEquipment edm = (EDMEquipment) edmObject;

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
        o.setCategory(
                edm.getEquipmentCategoriesByInstanceId() != null ?
                        edm.getEquipmentCategoriesByInstanceId().stream()
                                .map(EDMEquipmentCategory::getCategoryByCategoryId)
                                .map(EDMCategory::getName)
                                .collect(Collectors.toList())
                        : null
        );

        if (edm.getContactpointEquipmentsByInstanceId() != null) {
            o.setContactPoint(new LinkedList<>());
            edm.getContactpointEquipmentsByInstanceId().stream()
                    .map(EDMContactpointEquipment::getContactpointByInstanceContactpointId)
                    .forEach(e -> o.getContactPoint().add(
                            new LinkedEntity()
                                    .metaId(e.getMetaId())
                                    .instanceId(e.getInstanceId())
                                    .uid(e.getUid())
                                    .entityType("ContactPoint")));
        }

        o.setResolution(edm.getResolution());
        o.setDescription(edm.getDescription());
        o.setDynamicRange(edm.getDynamicrange());
        o.setFilter(edm.getFilter());

        if (edm.getEquipmentFacilitiesByInstanceId() != null) {
            o.setIsPartOf(new LinkedList<>());
            edm.getEquipmentFacilitiesByInstanceId().stream()
                    .map(EDMEquipmentFacility::getFacilityByInstanceFacilityId)
                    .forEach(e -> o.getIsPartOf().add(
                            new LinkedEntity()
                                    .metaId(e.getMetaId())
                                    .instanceId(e.getInstanceId())
                                    .uid(e.getUid())
                                    .entityType("Facility")));

        }

        if (edm.getEdmEntityIdByManufacturer() != null && edm.getEdmEntityIdByManufacturer().getOrganizationsByMetaId() != null
                && !edm.getEdmEntityIdByManufacturer().getOrganizationsByMetaId().isEmpty()) {
            List<EDMOrganization> so = new ArrayList<>(edm.getEdmEntityIdByManufacturer().getOrganizationsByMetaId());
            so.sort(EDMUtil::compareEntityVersion);
            o.setManufacturer(new LinkedEntity().uid(so.get(0).getUid()).entityType("Organization").instanceId(so.get(0).getInstanceId()).metaId(so.get(0).getMetaId()));
        }
        o.setName(edm.getName());
        o.setPageURL(edm.getPageurl());
        o.setOrientation(edm.getOrientation());
        o.setSamplePeriod(edm.getSampleperiod());
        o.setSerialNumber(edm.getSerialnumber());
        o.setSpatialExtent(
                edm.getEquipmentSpatialsByInstanceId() != null ?
                        new ArrayList<>(edm.getEquipmentSpatialsByInstanceId().stream()
                                .map(s -> {
                                    Location l = new Location();
                                    l.setLocation(s.getLocation());
                                    return l;
                                }).collect(Collectors.toList()))
                        : null
        );
        o.setTemporalExtent(
                edm.getEquipmentTemporalsByInstanceId() != null ?
                        edm.getEquipmentTemporalsByInstanceId().stream()
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
