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

public class SoftwareSourceCodeDBAPI extends AbstractDBAPI<SoftwareSourceCode> {


	public SoftwareSourceCodeDBAPI() {
		super("softwaresourcecode", EDMSoftwaresourcecode.class);
	}

	@Override
	public void hardUpdate(String instanceId, SoftwareSourceCode eposDataModelObject, EntityManager em) {
		EDMSoftwaresourcecode edmObject = getOneFromDB(em, EDMSoftwaresourcecode.class,
				"softwaresourcecode.findByInstanceId",
				"INSTANCEID", instanceId);
		delete(instanceId, em);
		if(edmObject.getInstanceId().equals(eposDataModelObject.getInstanceId())) {
			generateEntity(edmObject, eposDataModelObject, em,instanceId,true);
			em.persist(edmObject);
		}
	}

	@Override
	public LinkedEntity save(SoftwareSourceCode eposDataModelObject, EntityManager em, String edmInstanceId) {
		if (eposDataModelObject.getState().equals(State.PUBLISHED)
				&& isAlreadyPublished(EDMSoftwaresourcecode.class, "softwaresourcecode.findByUidAndState", em, eposDataModelObject))
			return new LinkedEntity();

		//search for a existing instance placeholder to be populated
		EDMSoftwaresourcecode edmObject = getOneFromDB(em, EDMSoftwaresourcecode.class,
				"softwaresourcecode.findByUidAndState",
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
			edmObject = new EDMSoftwaresourcecode();
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

	private void generateEntity(EDMSoftwaresourcecode edmObject, SoftwareSourceCode eposDataModelObject, EntityManager em, String instanceId, boolean merge) {
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
			EDMSoftwaresourcecode changedInstance = getOneFromDB(em, EDMSoftwaresourcecode.class, "softwaresourcecode.findByInstanceId",
					"INSTANCEID", eposDataModelObject.getInstanceChangedId());
			if (changedInstance == null) {
				em.getTransaction().rollback();
				throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: " + edmObject.getUid() + ", state: " + edmObject.getState()
				+ " and instanceId: " + edmObject.getInstanceId() + ", have an invalid 'InstanceChangedId'.");
			}
			edmObject.setSoftwaresourcecodeByInstanceChangedId(changedInstance);
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
			edmObject.setSoftwaresourcecodeCategoriesByInstanceId(new ArrayList<>());
			for (String categoryName : eposDataModelObject.getCategory()) {
				EDMCategory edmCategory = getOneFromDB(em, EDMCategory.class, "EDMCategory.findByUid",
						"UID", categoryName);

				if (edmCategory == null) {
					edmCategory = new EDMCategory();
					edmCategory.setUid(categoryName);
					edmCategory.setId(UUID.randomUUID().toString());
					em.persist(edmCategory);
				}

				EDMSoftwaresourcecodeCategory edmSoftwaresourcecodeCategory = new EDMSoftwaresourcecodeCategory();
				edmSoftwaresourcecodeCategory.setCategoryByCategoryId(edmCategory);
				edmSoftwaresourcecodeCategory.setSoftwaresourcecodeByInstanceSoftwaresourcecodeId(edmObject);

				em.persist(edmSoftwaresourcecodeCategory);

				if (edmCategory.getSoftwaresourcecodeCategoriesById() == null)
					edmCategory.setSoftwaresourcecodeCategoriesById(new ArrayList<>());

				edmCategory.getSoftwaresourcecodeCategoriesById().add(edmSoftwaresourcecodeCategory);
				edmObject.getSoftwaresourcecodeCategoriesByInstanceId().add(edmSoftwaresourcecodeCategory);
			}
		}

		if (eposDataModelObject.getContactPoint() != null) {
			edmObject.setContactpointSoftwaresourcecodesByInstanceId(new ArrayList<>());
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

				EDMContactpointSoftwaresourcecode edmContactpointSoftwaresourcecode = new EDMContactpointSoftwaresourcecode();
				edmContactpointSoftwaresourcecode.setSoftwaresourcecodeByInstanceSoftwaresourcecodeId(edmObject);
				edmContactpointSoftwaresourcecode.setContactpointByInstanceContactpointId(edmContactPoint);

				edmObject.getContactpointSoftwaresourcecodesByInstanceId().add(edmContactpointSoftwaresourcecode);
			}

		}

		edmObject.setDescription(eposDataModelObject.getDescription());
		edmObject.setDownloadurl(eposDataModelObject.getDownloadURL());

		if (eposDataModelObject.getIdentifier() != null) {

			edmObject.setSoftwaresourcecodeIdentifiersByInstanceId(new ArrayList<>());
			for (Identifier identifier : eposDataModelObject.getIdentifier()) {

				EDMSoftwaresourcecodeIdentifier edmSoftwaresourcecodeIdentifier = new EDMSoftwaresourcecodeIdentifier();

				edmSoftwaresourcecodeIdentifier.setId(UUID.randomUUID().toString());
				edmSoftwaresourcecodeIdentifier.setType(identifier.getType());
				edmSoftwaresourcecodeIdentifier.setIdentifier(identifier.getIdentifier());
				edmSoftwaresourcecodeIdentifier.setSoftwaresourcecodeByInstanceSoftwaresourcecodeId(edmObject);

				edmObject.getSoftwaresourcecodeIdentifiersByInstanceId().add(edmSoftwaresourcecodeIdentifier);
			}

		} else {
			System.err.println(eposDataModelObject.getClass().getSimpleName() + ": " + eposDataModelObject.getUid() +
					" doesn't have any identifier");
			em.getTransaction().rollback();
		}

		edmObject.setKeywords(eposDataModelObject.getKeywords());
		edmObject.setLicenseurl(eposDataModelObject.getLicenseURL());
		edmObject.setMainentityofpage(eposDataModelObject.getMainEntityofPage());
		edmObject.setName(eposDataModelObject.getName());

		if (eposDataModelObject.getProgrammingLanguage() != null) {
			edmObject.setSoftwaresourcecodeProgramminglanguagesByInstanceId(new ArrayList<>());
			for (String pl : eposDataModelObject.getProgrammingLanguage()) {
				EDMSoftwaresourcecodeProgramminglanguage edmSoftwaresourcecodeProgramminglanguage = new EDMSoftwaresourcecodeProgramminglanguage();

				edmSoftwaresourcecodeProgramminglanguage.setId(UUID.randomUUID().toString());
				edmSoftwaresourcecodeProgramminglanguage.setLanguage(pl);

				edmSoftwaresourcecodeProgramminglanguage.setSoftwaresourcecodeByInstanceSoftwaresourcecodeId(edmObject);

				edmObject.getSoftwaresourcecodeProgramminglanguagesByInstanceId().add(edmSoftwaresourcecodeProgramminglanguage);
			}
		}

		edmObject.setRuntimeplatform(eposDataModelObject.getRuntimePlatform());
		edmObject.setSoftwareversion(eposDataModelObject.getSoftwareVersion());
		edmObject.setCoderepository(eposDataModelObject.getCodeRepository());
	}

	@Override
	protected SoftwareSourceCode mapFromDB(Object edmObject) {

		EDMSoftwaresourcecode edm = (EDMSoftwaresourcecode) edmObject;

		SoftwareSourceCode o = new SoftwareSourceCode().keywords(edm.getKeywords());
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
				edm.getSoftwaresourcecodeCategoriesByInstanceId() != null ?
						edm.getSoftwaresourcecodeCategoriesByInstanceId().stream()
						.map(EDMSoftwaresourcecodeCategory::getCategoryByCategoryId)
						.map(EDMCategory::getName)
						.collect(Collectors.toList())
						: null
				);
		if (edm.getContactpointSoftwaresourcecodesByInstanceId() != null) {
			o.setContactPoint(new LinkedList<>());
			edm.getContactpointSoftwaresourcecodesByInstanceId().stream()
			.map(EDMContactpointSoftwaresourcecode::getContactpointByInstanceContactpointId)
			.forEach(e -> o.getContactPoint().add(
					new LinkedEntity()
					.metaId(e.getMetaId())
					.instanceId(e.getInstanceId())
					.uid(e.getUid())
					.entityType("ContactPoint")));
		}
		//only one description
		o.setDescription(edm.getDescription());
		o.setDownloadURL(edm.getDownloadurl());
		o.setIdentifier(
				edm.getSoftwaresourcecodeIdentifiersByInstanceId() != null ?
						edm.getSoftwaresourcecodeIdentifiersByInstanceId().stream()
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
		o.setMainEntityofPage(edm.getMainentityofpage());
		o.setName(edm.getName());
		o.setProgrammingLanguage(
				edm.getSoftwaresourcecodeProgramminglanguagesByInstanceId() != null ?
						edm.getSoftwaresourcecodeProgramminglanguagesByInstanceId().stream()
						.map(EDMSoftwaresourcecodeProgramminglanguage::getLanguage).collect(Collectors.toList()) :
							null
				);
		o.setRuntimePlatform(edm.getRuntimeplatform());
		o.setSoftwareVersion(edm.getSoftwareversion());
		o.setCodeRepository(edm.getCoderepository());

		return o;
	}

}
