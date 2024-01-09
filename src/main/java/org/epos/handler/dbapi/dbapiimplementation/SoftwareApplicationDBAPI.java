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

public class SoftwareApplicationDBAPI extends AbstractDBAPI<SoftwareApplication> {

    public SoftwareApplicationDBAPI() {
        super("softwareapplication", EDMSoftwareapplication.class);
    }
    
    @Override
   	public void hardUpdate(String instanceId, SoftwareApplication eposDataModelObject, EntityManager em) {
    	EDMSoftwareapplication edmObject = getOneFromDB(em, EDMSoftwareapplication.class,
   				"softwareapplication.findByInstanceId",
   				"INSTANCEID", instanceId);
   		if(edmObject.getInstanceId().equals(eposDataModelObject.getInstanceId())) {
   			generateEntity(edmObject, eposDataModelObject, em,instanceId,true);
   			em.merge(edmObject);
   		}
   	}

	@Override
    public LinkedEntity save(SoftwareApplication eposDataModelObject, EntityManager em, String edmInstanceId) {
        if (eposDataModelObject.getState().equals(State.PUBLISHED)
                && isAlreadyPublished(EDMSoftwareapplication.class, "softwareapplication.findByUidAndState", em, eposDataModelObject))
            return new LinkedEntity();

        //search for a existing instance placeholder to be populated
        EDMSoftwareapplication edmObject = getOneFromDB(em, EDMSoftwareapplication.class,
                "softwareapplication.findByUidAndState",
                "UID", eposDataModelObject.getUid(),
                "STATE", State.PLACEHOLDER.toString());

        //if there's a placeholder for the entity check if is passed a specific metaid
        //only if the metaid is the same of the placeholder merge the two (the placeholder and the passed entity)
        EDMEdmEntityId edmMetaId;

        boolean merge = false;

        if (edmObject != null &&
                (eposDataModelObject.getMetaId() == null || (eposDataModelObject.getMetaId() != null && eposDataModelObject.getMetaId().equals(edmObject.getMetaId())))) {
            merge = true;
        } else {
            edmObject = new EDMSoftwareapplication();
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

        }

        edmObject.setUid(eposDataModelObject.getUid());

		generateEntity(edmObject, eposDataModelObject, em,edmInstanceId,merge);

        return new LinkedEntity().entityType(entityString)
                .instanceId(edmInstanceId)
                .metaId(edmObject.getEdmEntityIdByMetaId().getMetaId())
                .uid(eposDataModelObject.getUid());

    }
	
	private void generateEntity(EDMSoftwareapplication edmObject, SoftwareApplication eposDataModelObject, EntityManager em, String instanceId, boolean merge) {
		if (merge) em.merge(edmObject);
        else em.persist(edmObject);

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
            EDMSoftwareapplication changedInstance = getOneFromDB(em, EDMSoftwareapplication.class, "softwareapplication.findByInstanceId",
                    "INSTANCEID", eposDataModelObject.getInstanceChangedId());
            if (changedInstance == null) {
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: " + edmObject.getUid() + ", state: " + edmObject.getState()
                        + " and instanceId: " + edmObject.getInstanceId() + ", have an invalid 'InstanceChangedId'.");
            }
            edmObject.setSoftwareapplicationByInstanceChangedId(changedInstance);
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
            edmObject.setSoftwareapplicationCategoriesByInstanceId(new ArrayList<>());
            for (String categoryName : eposDataModelObject.getCategory()) {
                EDMCategory edmCategory = getOneFromDB(em, EDMCategory.class, "EDMCategory.findByUid",
                        "UID", categoryName);

                if (edmCategory == null) {
                    edmCategory = new EDMCategory();
                    edmCategory.setUid(categoryName);
                    edmCategory.setId(UUID.randomUUID().toString());
                    em.persist(edmCategory);
                }

                EDMSoftwareapplicationCategory edmSoftwareapplicationCategory = new EDMSoftwareapplicationCategory();
                edmSoftwareapplicationCategory.setCategoryByCategoryId(edmCategory);
                edmSoftwareapplicationCategory.setSoftwareapplicationByInstanceSoftwareapplicationId(edmObject);

                em.persist(edmSoftwareapplicationCategory);

                if (edmCategory.getSoftwareapplicationCategoriesById() == null)
                    edmCategory.setSoftwareapplicationCategoriesById(new ArrayList<>());

                edmCategory.getSoftwareapplicationCategoriesById().add(edmSoftwareapplicationCategory);
                edmObject.getSoftwareapplicationCategoriesByInstanceId().add(edmSoftwareapplicationCategory);
            }
        }

        if (eposDataModelObject.getContactPoint() != null) {
            edmObject.setContactpointSoftwareapplicationsByInstanceId(new ArrayList<>());
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

                EDMContactpointSoftwareapplication edmContactpointSoftwareapplication = new EDMContactpointSoftwareapplication();
                edmContactpointSoftwareapplication.setSoftwareapplicationByInstanceSoftwareapplicationId(edmObject);
                edmContactpointSoftwareapplication.setContactpointByInstanceContactpointId(edmContactPoint);

                edmObject.getContactpointSoftwareapplicationsByInstanceId().add(edmContactpointSoftwareapplication);
            }

        }

        edmObject.setDescription(eposDataModelObject.getDescription());
        edmObject.setDownloadurl(eposDataModelObject.getDownloadURL());

        if (eposDataModelObject.getIdentifier() != null) {

            edmObject.setSoftwareapplicationIdentifiersByInstanceId(new ArrayList<>());
            for (Identifier identifier : eposDataModelObject.getIdentifier()) {

                EDMSoftwareapplicationIdentifier edmSoftwareapplicationIdentifier = new EDMSoftwareapplicationIdentifier();

                edmSoftwareapplicationIdentifier.setId(UUID.randomUUID().toString());
                edmSoftwareapplicationIdentifier.setType(identifier.getType());
                edmSoftwareapplicationIdentifier.setIdentifier(identifier.getIdentifier());
                edmSoftwareapplicationIdentifier.setSoftwareapplicationByInstanceSoftwareapplicationId(edmObject);

                edmObject.getSoftwareapplicationIdentifiersByInstanceId().add(edmSoftwareapplicationIdentifier);
            }

        } else {
            System.err.println(eposDataModelObject.getClass().getSimpleName() + ": " + eposDataModelObject.getUid() +
                    " doesn't have any identifier");
            em.getTransaction().rollback();
        }

        edmObject.setInstallurl(eposDataModelObject.getInstallURL());
        edmObject.setKeywords(eposDataModelObject.getKeywords());
        edmObject.setLicenseurl(eposDataModelObject.getLicenseURL());
        edmObject.setMainentityofpage(eposDataModelObject.getMainEntityOfPage());
        edmObject.setName(eposDataModelObject.getName());

        if (eposDataModelObject.getParameter() != null) {
            edmObject.setSoftwareapplicationParametersByInstanceId(new ArrayList<>());
            for (Parameter parameter : eposDataModelObject.getParameter()) {
                EDMSoftwareapplicationParameters edmSoftwareapplicationParameters = new EDMSoftwareapplicationParameters();

                edmSoftwareapplicationParameters.setId(UUID.randomUUID().toString());
                edmSoftwareapplicationParameters.setAction(parameter.getAction().toString());
                edmSoftwareapplicationParameters.setConformsto(parameter.getConformsTo());
                edmSoftwareapplicationParameters.setEncodingformat(parameter.getEncodingFormat());

                edmSoftwareapplicationParameters.setSoftwareapplicationByInstanceSoftwareapplicationId(edmObject);

                edmObject.getSoftwareapplicationParametersByInstanceId().add(edmSoftwareapplicationParameters);
            }
        }

        if (eposDataModelObject.getRelation() != null) {
            edmObject.setSoftwareapplicationOperationsByInstanceId(new ArrayList<>());
            for (LinkedEntity linkedEntity : eposDataModelObject.getRelation()) {
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

                EDMSoftwareapplicationOperation edmSoftwareapplicationOperation = new EDMSoftwareapplicationOperation();
                edmSoftwareapplicationOperation.setSoftwareapplicationByInstanceSoftwareapplicationId(edmObject);
                edmSoftwareapplicationOperation.setOperationByInstanceOperationId(instance);

                edmObject.getSoftwareapplicationOperationsByInstanceId().add(edmSoftwareapplicationOperation);
            }
        }

        edmObject.setRequirements(eposDataModelObject.getRequirements());
        edmObject.setSoftwareversion(eposDataModelObject.getSoftwareVersion());
		
	}

    @Override
    protected SoftwareApplication mapFromDB(Object edmObject) {

        EDMSoftwareapplication edm = (EDMSoftwareapplication) edmObject;

        SoftwareApplication o = new SoftwareApplication().keywords(edm.getKeywords());
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
                edm.getSoftwareapplicationCategoriesByInstanceId() != null ?
                        edm.getSoftwareapplicationCategoriesByInstanceId().stream()
                                .map(EDMSoftwareapplicationCategory::getCategoryByCategoryId)
                                .map(EDMCategory::getName)
                                .collect(Collectors.toList())
                        : null
        );
        if (edm.getContactpointSoftwareapplicationsByInstanceId() != null) {
            o.setContactPoint(new LinkedList<>());
            edm.getContactpointSoftwareapplicationsByInstanceId().stream()
                    .map(EDMContactpointSoftwareapplication::getContactpointByInstanceContactpointId)
                    .forEach(e -> o.getContactPoint().add(
                            new LinkedEntity()
                                    .metaId(e.getMetaId())
                                    .instanceId(e.getInstanceId())
                                    .uid(e.getUid())
                                    .entityType("ContactPoint")));
        }
        if (edm.getSoftwareapplicationOperationsByInstanceId() != null) {
            o.setRelation(new LinkedList<>());
            edm.getSoftwareapplicationOperationsByInstanceId().stream()
                    .map(EDMSoftwareapplicationOperation::getOperationByInstanceOperationId)
                    .forEach(e -> o.getRelation().add(
                            new LinkedEntity()
                                    .metaId(e.getMetaId())
                                    .instanceId(e.getInstanceId())
                                    .uid(e.getUid())
                                    .entityType("Operation")));
        }

        //only one description?
        o.setDescription(edm.getDescription());
        o.setDownloadURL(edm.getDownloadurl());
        o.setIdentifier(
                edm.getSoftwareapplicationIdentifiersByInstanceId() != null ?
                        edm.getSoftwareapplicationIdentifiersByInstanceId().stream()
                                .map(i -> {
                                    Identifier identifier = new Identifier();
                                    identifier.setIdentifier(i.getIdentifier());
                                    identifier.setType(i.getType());
                                    return identifier;
                                })
                                .collect(Collectors.toList())
                        : null
        );
        o.setLicenseURL(edm.getLicenseurl());
        o.setMainEntityOfPage(edm.getMainentityofpage());
        o.setName(edm.getName());
        ArrayList<Parameter> parameterList = new ArrayList<>();
        if (edm.getSoftwareapplicationParametersByInstanceId() != null) {
            edm.getSoftwareapplicationParametersByInstanceId().stream()
                    .map(elem -> {
                        Parameter parameter = new Parameter();
                        parameter.setEncodingFormat(elem.getEncodingformat());
                        parameter.setAction(Parameter.ActionEnum.fromValue(elem.getAction()));
                        parameter.setConformsTo(elem.getConformsto());
                        return parameter;
                    })
                    .forEach(parameterList::add);
        }
        o.setParameter(parameterList);
        o.setRequirements(edm.getRequirements());
        o.setSoftwareVersion(edm.getSoftwareversion());
        o.setInstallURL(edm.getInstallurl());

        return o;
    }

}
