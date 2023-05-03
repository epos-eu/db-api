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

public class DataProductDBAPI extends AbstractDBAPI<DataProduct> {

    public DataProductDBAPI() {
        super("dataproduct", EDMDataproduct.class);
    }

    @Override
    public LinkedEntity save(DataProduct eposDataModelObject, EntityManager em, String edmInstanceId) {
        if (eposDataModelObject.getState().equals(State.PUBLISHED)
                && isAlreadyPublished(EDMDataproduct.class, "dataproduct.findByUidAndState", em, eposDataModelObject))
            return new LinkedEntity();

        //search for a existing instance placeholder to be populated
        EDMDataproduct edmObject = getOneFromDB(em, EDMDataproduct.class,
                "dataproduct.findByUidAndState",
                "UID", eposDataModelObject.getUid(),
                "STATE", State.PLACEHOLDER.toString());

        //if there's a placeholder for the entity check if is passed a specific metaid
        //only if the metaid is the same of the placeholder merge the two (the placeholder and the passed entity)
        EDMEdmEntityId edmMetaId;

        boolean merged = false;

        if (edmObject != null &&
                (eposDataModelObject.getMetaId() == null || (eposDataModelObject.getMetaId() != null && eposDataModelObject.getMetaId().equals(edmObject.getMetaId())))) {
            merged = true;
            //em.merge(edmObject);
        } else {
            edmObject = new EDMDataproduct();
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
            EDMDataproduct changedInstance = getOneFromDB(em, EDMDataproduct.class, "dataproduct.findByInstanceId",
                    "INSTANCEID", eposDataModelObject.getInstanceChangedId());
            if (changedInstance == null) {
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: " + edmObject.getUid() + ", state: " + eposDataModelObject.getState()
                        + " and instanceId: " + edmObject.getInstanceId() + ", have an invalid 'InstanceChangedId'.");
            }
            edmObject.setDataproductByInstanceChangedId(changedInstance);
        }

        if (eposDataModelObject.getEditorId() == null) {
            em.getTransaction().rollback();
            throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: " + edmObject.getUid() + ", state: " + eposDataModelObject.getState()
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


        edmObject.setAccessright(eposDataModelObject.getAccessRight());
        edmObject.setAccrualperiodicity(eposDataModelObject.getAccrualPeriodicity());

        if (eposDataModelObject.getCategory() != null) {
            edmObject.setDataproductCategoriesByInstanceId(new LinkedList<>());
            for (String categoryName : eposDataModelObject.getCategory()) {
                EDMCategory edmCategory = getOneFromDB(em, EDMCategory.class, "EDMCategory.findByName",
                        "NAME", categoryName);

                if (edmCategory == null) {
                    edmCategory = new EDMCategory();
                    edmCategory.setName(categoryName);
                    edmCategory.setId(UUID.randomUUID().toString());
                    em.persist(edmCategory);
                }

                EDMDataproductCategory edmDataproductCategory = new EDMDataproductCategory();
                edmDataproductCategory.setCategoryByCategoryId(edmCategory);
                edmDataproductCategory.setDataproductByInstanceDataproductId(edmObject);

                em.persist(edmDataproductCategory);

                if (edmCategory.getDataproductCategoriesById() == null)
                    edmCategory.setDataproductCategoriesById(new ArrayList<>());

                edmCategory.getDataproductCategoriesById().add(edmDataproductCategory);
                edmObject.getDataproductCategoriesByInstanceId().add(edmDataproductCategory);
            }
        }

        if (eposDataModelObject.getContactPoint() != null) {
            edmObject.setContactpointDataproductsByInstanceId(new ArrayList<>());
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

                EDMContactpointDataproduct edmContactpointDataproduct = new EDMContactpointDataproduct();
                edmContactpointDataproduct.setDataproductByInstanceDataproductId(edmObject);
                edmContactpointDataproduct.setContactpointByInstanceContactpointId(edmContactPoint);


                edmObject.getContactpointDataproductsByInstanceId().add(edmContactpointDataproduct);
            }
        }

        if (eposDataModelObject.getCreated() != null)
            edmObject.setCreated(Timestamp.valueOf(eposDataModelObject.getCreated()));
        if (eposDataModelObject.getModified() != null)
            edmObject.setModified(Timestamp.valueOf(eposDataModelObject.getModified()));
        if (eposDataModelObject.getIssued() != null)
            edmObject.setIssued(Timestamp.valueOf(eposDataModelObject.getIssued()));

        if (eposDataModelObject.getTitle() != null) {
            edmObject.setDataproductTitlesByInstanceId(new ArrayList<>());
            for (String title : eposDataModelObject.getTitle()) {
                EDMDataproductTitle edmDataproductTitle = new EDMDataproductTitle();

                edmDataproductTitle.setId(UUID.randomUUID().toString());
                edmDataproductTitle.setTitle(title);
                edmDataproductTitle.setDataproductByInstanceDataproductId(edmObject);

                edmObject.getDataproductTitlesByInstanceId().add(edmDataproductTitle);
            }
        }

        if (eposDataModelObject.getDescription() != null) {
            edmObject.setDataproductDescriptionsByInstanceId(new ArrayList<>());
            for (String description : eposDataModelObject.getDescription()) {
                EDMDataproductDescription edmDataproductDescription = new EDMDataproductDescription();

                edmDataproductDescription.setId(UUID.randomUUID().toString());
                edmDataproductDescription.setDescription(description);
                edmDataproductDescription.setDataproductByInstanceDataproductId(edmObject);

                edmObject.getDataproductDescriptionsByInstanceId().add(edmDataproductDescription);
            }
        }

        if (eposDataModelObject.getDistribution() != null) {
            edmObject.setIsDistributionsByInstanceId(new ArrayList<>());
            for (LinkedEntity linkedEntity : eposDataModelObject.getDistribution()) {

                EDMDistribution instance = null;

                if (linkedEntity.getInstanceId() != null) {
                    instance = getOneFromDB(em, EDMDistribution.class,
                            "distribution.findByInstanceId", "INSTANCEID", linkedEntity.getInstanceId());
                }
                if (linkedEntity.getInstanceId() == null || instance == null) {
                    List<EDMDistribution> instanceList = getFromDB(em, EDMDistribution.class,
                            "distribution.findByUid", "UID", linkedEntity.getUid());

                    instanceList.sort(EDMUtil::compareEntityVersion);

                    instance = !instanceList.isEmpty() ? instanceList.get(0) : null;
                }

                if (instance == null) {
                    EDMEdmEntityId edmInstaceMetaId = new EDMEdmEntityId();
                    edmInstaceMetaId.setMetaId(UUID.randomUUID().toString());
                    em.persist(edmInstaceMetaId);

                    instance = new EDMDistribution();
                    instance.setUid(linkedEntity.getUid());
                    instance.setState(State.PLACEHOLDER.toString());
                    instance.setInstanceId(UUID.randomUUID().toString());
                    instance.setEdmEntityIdByMetaId(edmInstaceMetaId);
                    em.persist(instance);
                }


                EDMIsDistribution isDistribution = getOneFromDB(em, EDMIsDistribution.class, "isDistribution.findByinstanceId",
                        "DPINST", edmObject.getInstanceId(), "DINST", instance.getInstanceId());

                if (isDistribution != null) continue;

                EDMIsDistribution edmIsDistribution = new EDMIsDistribution();
                edmIsDistribution.setDataproductByInstanceDataproductId(edmObject);
                edmIsDistribution.setDistributionByInstanceDistributionId(instance);

                edmObject.getIsDistributionsByInstanceId().add(edmIsDistribution);

            }
        }

        if (eposDataModelObject.getHasPart() != null) {
            edmObject.setHaspartDataproductsByInstanceId(new ArrayList<>());
            for (LinkedEntity linkedEntity : eposDataModelObject.getHasPart()) {
                EDMDataproduct instance = null;

                if (linkedEntity.getInstanceId() != null) {
                    instance = getOneFromDB(em, EDMDataproduct.class,
                            "dataproduct.findByInstanceId", "INSTANCEID", linkedEntity.getInstanceId());
                }
                if (linkedEntity.getInstanceId() == null || instance == null) {
                    List<EDMDataproduct> instanceList = getFromDB(em, EDMDataproduct.class,
                            "dataproduct.findByUid", "UID", linkedEntity.getUid());

                    instanceList.sort(EDMUtil::compareEntityVersion);

                    instance = !instanceList.isEmpty() ? instanceList.get(0) : null;
                }

                if (instance == null) {
                    EDMEdmEntityId edmInstaceMetaId = new EDMEdmEntityId();
                    edmInstaceMetaId.setMetaId(UUID.randomUUID().toString());
                    em.persist(edmInstaceMetaId);

                    instance = new EDMDataproduct();
                    instance.setUid(linkedEntity.getUid());
                    instance.setState(State.PLACEHOLDER.toString());
                    instance.setInstanceId(UUID.randomUUID().toString());
                    instance.setEdmEntityIdByMetaId(edmInstaceMetaId);
                    em.persist(instance);
                }

                EDMHaspartDataproduct edmHasPart = new EDMHaspartDataproduct();
                edmHasPart.setDataproductByInstanceDataproduct1Id(edmObject);
                edmHasPart.setDataproductByInstanceDataproduct2Id(instance);

                edmObject.getHaspartDataproductsByInstanceId().add(edmHasPart);
            }
        }

        if (eposDataModelObject.getIdentifier() != null) {

            edmObject.setDataproductIdentifiersByInstanceId(new ArrayList<>());
            for (Identifier identifier : eposDataModelObject.getIdentifier()) {

                EDMDataproductIdentifier edmDataproductIdentifier = new EDMDataproductIdentifier();

                edmDataproductIdentifier.setId(UUID.randomUUID().toString());
                edmDataproductIdentifier.setType(identifier.getType());
                edmDataproductIdentifier.setIdentifier(identifier.getIdentifier());
                edmDataproductIdentifier.setDataproductByInstanceDataproductId(edmObject);

                edmObject.getDataproductIdentifiersByInstanceId().add(edmDataproductIdentifier);
            }

        } else {
            eposDataModelObject.setMetaId(edmObject.getMetaId());
            eposDataModelObject.setInstanceId(edmInstanceId);
            System.err.println(LoggerFormat.log(eposDataModelObject, "doens't have any identifier"));
            //em.getTransaction().rollback();
        }

        if (eposDataModelObject.getIsPartOf() != null) {
            edmObject.setIspartofDataproductsByInstanceId(new ArrayList<>());
            for (LinkedEntity linkedEntity : eposDataModelObject.getIsPartOf()) {
                EDMDataproduct instance = null;

                if (linkedEntity.getInstanceId() != null) {
                    instance = getOneFromDB(em, EDMDataproduct.class,
                            "dataproduct.findByInstanceId", "INSTANCEID", linkedEntity.getInstanceId());
                }
                if (linkedEntity.getInstanceId() == null || instance == null) {
                    List<EDMDataproduct> instanceList = getFromDB(em, EDMDataproduct.class,
                            "dataproduct.findByUid", "UID", linkedEntity.getUid());

                    instanceList.sort(EDMUtil::compareEntityVersion);

                    instance = !instanceList.isEmpty() ? instanceList.get(0) : null;
                }

                if (instance == null) {
                    EDMEdmEntityId edmInstaceMetaId = new EDMEdmEntityId();
                    edmInstaceMetaId.setMetaId(UUID.randomUUID().toString());
                    em.persist(edmInstaceMetaId);

                    instance = new EDMDataproduct();
                    instance.setUid(linkedEntity.getUid());
                    instance.setState(State.PLACEHOLDER.toString());
                    instance.setInstanceId(UUID.randomUUID().toString());
                    instance.setEdmEntityIdByMetaId(edmInstaceMetaId);
                    em.persist(instance);

                }

                EDMIspartofDataproduct edmIsPartOf = new EDMIspartofDataproduct();
                edmIsPartOf.setDataproductByInstanceDataproduct1Id(edmObject);
                edmIsPartOf.setDataproductByInstanceDataproduct2Id(instance);

                edmObject.getIspartofDataproductsByInstanceId().add(edmIsPartOf);
            }
        }

        edmObject.setKeywords(eposDataModelObject.getKeywords());

        if (eposDataModelObject.getProvenance() != null) {
            edmObject.setDataproductProvenancesByInstanceId(new ArrayList<>());
            for (String provenance : eposDataModelObject.getProvenance()) {
                EDMDataproductProvenance EDMDataproductProvenance = new EDMDataproductProvenance();

                EDMDataproductProvenance.setId(UUID.randomUUID().toString());
                EDMDataproductProvenance.setProvenance(provenance);
                EDMDataproductProvenance.setDataproductByInstanceDataproductId(edmObject);

                edmObject.getDataproductProvenancesByInstanceId().add(EDMDataproductProvenance);
            }
        }

        if (eposDataModelObject.getPublisher() != null) {
            edmObject.setPublishersByInstanceId(new ArrayList<>());
            for (String orgUid : eposDataModelObject.getPublisher().stream().map(LinkedEntity::getUid).collect(Collectors.toList())) {
                List<EDMOrganization> instaceList = getFromDB(em, EDMOrganization.class,
                        "organization.findByUid", "UID", orgUid);

                instaceList.sort(EDMUtil::compareEntityVersion);

                EDMOrganization instance = !instaceList.isEmpty() ? instaceList.get(0) : null;

                EDMEdmEntityId edmInstaceMetaId;

                if (instance == null) {
                    edmInstaceMetaId = new EDMEdmEntityId();
                    edmInstaceMetaId.setMetaId(UUID.randomUUID().toString());
                    em.persist(edmInstaceMetaId);

                    instance = new EDMOrganization();
                    instance.setUid(orgUid);
                    instance.setState(State.PLACEHOLDER.toString());
                    instance.setInstanceId(UUID.randomUUID().toString());
                    instance.setEdmEntityIdByMetaId(edmInstaceMetaId);
                    em.persist(instance);

                } else {
                    edmInstaceMetaId = instance.getEdmEntityIdByMetaId();
                }

                EDMPublisher edmLink = new EDMPublisher();
                edmLink.setDataproductByInstanceDataproductId(edmObject);
                edmLink.setEdmEntityIdByMetaOrganizationId(edmInstaceMetaId);

                edmObject.getPublishersByInstanceId().add(edmLink);
            }
        }

        if (eposDataModelObject.getSpatialExtent() != null) {
            edmObject.setDataproductSpatialsByInstanceId(new ArrayList<>());
            for (Location location : eposDataModelObject.getSpatialExtent()) {
                if (location.getLocation() == null)
                    continue;

                EDMDataproductSpatial edmDataproductSpatial = new EDMDataproductSpatial();

                edmDataproductSpatial.setId(UUID.randomUUID().toString());
                edmDataproductSpatial.setDataproductByInstanceDataproductId(edmObject);
                edmDataproductSpatial.setLocation(location.getLocation());

                edmObject.getDataproductSpatialsByInstanceId().add(edmDataproductSpatial);
            }
        }

        if (eposDataModelObject.getTemporalExtent() != null) {
            edmObject.setDataproductTemporalsByInstanceId(new ArrayList<>());
            for (PeriodOfTime temporal : eposDataModelObject.getTemporalExtent()) {
                EDMDataproductTemporal edmDataproductTemporal = new EDMDataproductTemporal();
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
                edmDataproductTemporal.setDataproductByInstanceDataproductId(edmObject);
                edmObject.getDataproductTemporalsByInstanceId().add(edmDataproductTemporal);
            }
        }

        edmObject.setType(eposDataModelObject.getType());
        edmObject.setVersioninfo(eposDataModelObject.getVersionInfo());
        edmObject.setDocumentation(eposDataModelObject.getDocumentation());
        edmObject.setQualityassurance(eposDataModelObject.getQualityAssurance());
        edmObject.setHasQualityAnnotation(eposDataModelObject.getHasQualityAnnotation());

        return new LinkedEntity().entityType(entityString)
                .instanceId(edmInstanceId)
                .metaId(edmObject.getEdmEntityIdByMetaId().getMetaId())
                .uid(eposDataModelObject.getUid());

    }

    @Override
    protected DataProduct mapFromDB(Object edmObject) {

        EDMDataproduct edm = (EDMDataproduct) edmObject;

        DataProduct o = new DataProduct().keywords(edm.getKeywords());
        if (!metadataMode) {
            o.setInstanceId(edm.getInstanceId());
            o.setMetaId(edm.getMetaId());
            o.setState(State.valueOf(edm.getState()));
            o.setOperation(edm.getOperation());
            if (edm.getEdmEntityIdByEditorMetaId() != null ) {
                o.setEditorId(edm.getEdmEntityIdByEditorMetaId().getMetaId());
            }
            o.setVersion(edm.getVersion());
            o.setChangeComment(edm.getChangeComment());
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
        }

        o.setUid(edm.getUid());
        o.setAccessRight(edm.getAccessright());
        o.setAccrualPeriodicity(edm.getAccrualperiodicity());
        o.setCategory(
                edm.getDataproductCategoriesByInstanceId() != null ?
                        edm.getDataproductCategoriesByInstanceId().stream()
                                .map(EDMDataproductCategory::getCategoryByCategoryId)
                                .map(EDMCategory::getName)
                                .collect(Collectors.toList())
                        : null
        );
        o.setHasQualityAnnotation(edm.getHasQualityAnnotation());
        if (edm.getContactpointDataproductsByInstanceId() != null) {
            o.setContactPoint(new LinkedList<>());
            edm.getContactpointDataproductsByInstanceId().stream()
                    .map(EDMContactpointDataproduct::getContactpointByInstanceContactpointId)
                    .forEach(e -> o.getContactPoint().add(
                            new LinkedEntity()
                                    .metaId(e.getMetaId())
                                    .instanceId(e.getInstanceId())
                                    .uid(e.getUid())
                                    .entityType("ContactPoint")));
        }
        o.setCreated(
                edm.getCreated() != null ? edm.getCreated().toLocalDateTime() : null
        );
        o.setIssued(
                edm.getIssued() != null ? edm.getIssued().toLocalDateTime() : null
        );
        o.setModified(
                edm.getModified() != null ? edm.getModified().toLocalDateTime() : null
        );
        o.setDescription(
                edm.getDataproductDescriptionsByInstanceId() != null ?
                        edm.getDataproductDescriptionsByInstanceId().stream()
                                .map(EDMDataproductDescription::getDescription)
                                .collect(Collectors.toList())
                        : null
        );
        if (edm.getIsDistributionsByInstanceId() != null) {
            o.setDistribution(new LinkedList<>());
            edm.getIsDistributionsByInstanceId().stream()
                    .map(EDMIsDistribution::getDistributionByInstanceDistributionId)
                    .forEach(e -> o.getDistribution().add(
                            new LinkedEntity()
                                    .metaId(e.getMetaId())
                                    .instanceId(e.getInstanceId())
                                    .uid(e.getUid())
                                    .entityType("Distribution")));
        }
        if (edm.getHaspartDataproductsByInstanceId() != null) {
            o.setHasPart(new LinkedList<>());
            edm.getHaspartDataproductsByInstanceId().stream()
                    .map(EDMHaspartDataproduct::getDataproductByInstanceDataproduct2Id)
                    .forEach(e -> o.getHasPart().add(
                            new LinkedEntity()
                                    .metaId(e.getMetaId())
                                    .instanceId(e.getInstanceId())
                                    .uid(e.getUid())
                                    .entityType("DataProduct")));
        }
        o.setIdentifier(
                edm.getDataproductIdentifiersByInstanceId() != null ?
                        edm.getDataproductIdentifiersByInstanceId().stream()
                                .map(i -> {
                                    Identifier identifier = new Identifier();
                                    identifier.setIdentifier(i.getIdentifier());
                                    identifier.setType(i.getType());
                                    return identifier;
                                })
                                .collect(Collectors.toList())
                        : null
        );
        if (edm.getIspartofDataproductsByInstanceId() != null) {
            o.setIsPartOf(new LinkedList<>());
            o.setHasPart(new LinkedList<>());
            edm.getIspartofDataproductsByInstanceId().stream()
                    .map(EDMIspartofDataproduct::getDataproductByInstanceDataproduct2Id)
                    .forEach(e -> o.getIsPartOf().add(
                            new LinkedEntity()
                                    .metaId(e.getMetaId())
                                    .instanceId(e.getInstanceId())
                                    .uid(e.getUid())
                                    .entityType("DataProduct")));
        }
        o.setProvenance(
                edm.getDataproductProvenancesByInstanceId() != null ?
                        edm.getDataproductProvenancesByInstanceId().stream()
                                .map(EDMDataproductProvenance::getProvenance)
                                .collect(Collectors.toList())
                        : null
        );
        if (edm.getPublishersByInstanceId() != null) {
            o.setPublisher(new LinkedList<>());
            for (EDMEdmEntityId edmMetaId : edm.getPublishersByInstanceId().stream().map(EDMPublisher::getEdmEntityIdByMetaOrganizationId).collect(Collectors.toList())) {
                if (edmMetaId.getOrganizationsByMetaId() != null && !edmMetaId.getOrganizationsByMetaId().isEmpty()) {
                    ArrayList<EDMOrganization> list = new ArrayList<>(edmMetaId.getOrganizationsByMetaId());
                    list.sort(EDMUtil::compareEntityVersion);
                    o.getPublisher().add(new LinkedEntity().entityType("Organization").instanceId(list.get(0).getInstanceId()).metaId(list.get(0).getMetaId()).uid(list.get(0).getUid()));
                }
            }
        }
        o.setSpatialExtent(
                edm.getDataproductSpatialsByInstanceId() != null ?
                        new ArrayList<>(edm.getDataproductSpatialsByInstanceId().stream()
                                .map(s -> {
                                    Location l = new Location();
                                    l.setLocation(s.getLocation());
                                    return l;
                                }).collect(Collectors.toList()))
                        : null
        );
        o.setTemporalExtent(
                edm.getDataproductTemporalsByInstanceId() != null ?
                        edm.getDataproductTemporalsByInstanceId().stream()
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
        o.setTitle(
                edm.getDataproductTitlesByInstanceId() != null ?
                        edm.getDataproductTitlesByInstanceId().stream()
                                .map(EDMDataproductTitle::getTitle)
                                .collect(Collectors.toList())
                        : null
        );
        o.setType(edm.getType());
        o.setVersionInfo(edm.getVersioninfo());
        o.setDocumentation(edm.getDocumentation());
        o.setQualityAssurance(edm.getQualityassurance());

        return o;
    }

}
