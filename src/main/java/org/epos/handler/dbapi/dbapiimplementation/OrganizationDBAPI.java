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

public class OrganizationDBAPI extends AbstractDBAPI<Organization> {

	public OrganizationDBAPI() {
		super("organization", EDMOrganization.class);
	}

	@Override
	public LinkedEntity save(Organization eposDataModelObject, EntityManager em, String edmInstanceId) {
		if (eposDataModelObject.getState().equals(State.PUBLISHED)
				&& isAlreadyPublished(EDMOrganization.class, "organization.findByUidAndState", em, eposDataModelObject))
			return new LinkedEntity();

		//search for a existing instance placeholder to be populated
		EDMOrganization edmObject = getOneFromDB(em, EDMOrganization.class,
				"organization.findByUidAndState",
				"UID", eposDataModelObject.getUid(),
				"STATE", State.PLACEHOLDER.toString());

		if(edmObject==null) {
			edmObject = getOneFromDB(em, EDMOrganization.class,
					"organization.findByInstanceId",
					"INSTANCEID", eposDataModelObject.getInstanceId());
		}

		//if there's a placeholder for the entity check if is passed a specific metaid
		//only if the metaid is the same of the placeholder merge the two (the placeholder and the passed entity)
		EDMEdmEntityId edmMetaId;

		boolean merge = false;

		if (edmObject != null &&
				(eposDataModelObject.getMetaId() == null || (eposDataModelObject.getMetaId() != null && eposDataModelObject.getMetaId().equals(edmObject.getMetaId())))) {
			merge = true;
		} else {
			edmObject = new EDMOrganization();
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
			EDMOrganization changedInstance = getOneFromDB(em, EDMOrganization.class, "organization.findByInstanceId",
					"INSTANCEID", eposDataModelObject.getInstanceChangedId());
			if (changedInstance == null) {
				em.getTransaction().rollback();
				throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: " + edmObject.getUid() + ", state: " + edmObject.getState()
				+ " and instanceId: " + edmObject.getInstanceId() + ", have an invalid 'InstanceChangedId'.");
			}
			edmObject.setOrganizationByInstanceChangedId(changedInstance);
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

		if (eposDataModelObject.getContactPoint() != null) {
			if(edmObject.getContactpointOrganizationsByInstanceId()!=null)
				for(EDMContactpointOrganization obj : edmObject.getContactpointOrganizationsByInstanceId()) {
					em.remove(obj);
				}
			edmObject.setContactpointOrganizationsByInstanceId(new ArrayList<>());
			for (LinkedEntity contactpointLinked : eposDataModelObject.getContactPoint()) {

				EDMContactpoint edmContactPoint = null;

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
					em.persist(edmContactPoint);
					edmContactPoint.setEdmEntityIdByMetaId(edmContactPointMetaId);
				}

				EDMContactpointOrganization edmContactpointDataproduct = new EDMContactpointOrganization();
				edmContactpointDataproduct.setOrganizationByInstanceOrganizationId(edmObject);
				edmContactpointDataproduct.setContactpointByInstanceContactpointId(edmContactPoint);

				edmObject.getContactpointOrganizationsByInstanceId().add(edmContactpointDataproduct);
			}
		}

		if (eposDataModelObject.getEmail() != null) {
			if(edmObject.getOrganizationEmailsByInstanceId()!=null)
				for(EDMOrganizationEmail obj : edmObject.getOrganizationEmailsByInstanceId()) {
					em.remove(obj);
				}
			edmObject.setOrganizationEmailsByInstanceId(new ArrayList<>());
			for (String mail : eposDataModelObject.getEmail()) {
				EDMOrganizationEmail edmOrganizationEmail = new EDMOrganizationEmail();

				edmOrganizationEmail.setId(UUID.randomUUID().toString());
				edmOrganizationEmail.setEmail(mail);
				edmOrganizationEmail.setOrganizationIdByInstanceOrganizationId(edmObject);

				edmObject.getOrganizationEmailsByInstanceId().add(edmOrganizationEmail);
			}
		}

		if (eposDataModelObject.getIdentifier() != null) {
			if(edmObject.getOrganizationIdentifiersByInstanceId()!=null)
				for(EDMOrganizationIdentifier obj : edmObject.getOrganizationIdentifiersByInstanceId()) {
					em.remove(obj);
				}
			edmObject.setOrganizationIdentifiersByInstanceId(new ArrayList<>());
			for (Identifier identifier : eposDataModelObject.getIdentifier()) {

				EDMOrganizationIdentifier edmIdentifier = new EDMOrganizationIdentifier();

				edmIdentifier.setId(UUID.randomUUID().toString());
				edmIdentifier.setType(identifier.getType());
				edmIdentifier.setIdentifier(identifier.getIdentifier());
				edmIdentifier.setOrganizationIdByInstanceOrganizationId(edmObject);

				edmObject.getOrganizationIdentifiersByInstanceId().add(edmIdentifier);
			}

		} else {
			System.err.println(eposDataModelObject.getClass().getSimpleName() + ": " + eposDataModelObject.getUid() +
					" doesn't have any identifier");
			em.getTransaction().rollback();
		}

		if (eposDataModelObject.getLegalName() != null) {
			if(edmObject.getOrganizationLegalnameByInstanceId()!=null)
				for(EDMOrganizationLegalname obj : edmObject.getOrganizationLegalnameByInstanceId()) {
					em.remove(obj);
				}
			edmObject.setOrganizationLegalnameByInstanceId(new ArrayList<>());
			for (String legalname : eposDataModelObject.getLegalName()) {
				EDMOrganizationLegalname edmOrganizationLegalname = new EDMOrganizationLegalname();

				edmOrganizationLegalname.setId(UUID.randomUUID().toString());
				edmOrganizationLegalname.setLegalname(legalname);
				edmOrganizationLegalname.setLanguage(null);
				edmOrganizationLegalname.setOrganizationIdByInstanceOrganizationId(edmObject);

				edmObject.getOrganizationLegalnameByInstanceId().add(edmOrganizationLegalname);
			}
		}

		edmObject.setAcronym(eposDataModelObject.getAcronym());
		edmObject.setLeicode(eposDataModelObject.getLeiCode());
		edmObject.setLogo(eposDataModelObject.getLogo());

		if (eposDataModelObject.getMemberOf() != null) {
			if(edmObject.getFather()!=null)
				for(EDMOrganization obj : edmObject.getFather()) {
					em.remove(obj);
				}
			edmObject.setFather(new ArrayList<>());
			for (LinkedEntity linkedEntity : eposDataModelObject.getMemberOf()) {

				EDMOrganization instance = null;

				if (linkedEntity.getInstanceId() != null) {
					instance = getOneFromDB(em, EDMOrganization.class,
							"organization.findByInstanceId", "INSTANCEID", linkedEntity.getInstanceId());
				}
				if (linkedEntity.getInstanceId() == null || instance == null) {
					List<EDMOrganization> instanceList = getFromDB(em, EDMOrganization.class,
							"organization.findByUid", "UID", linkedEntity.getUid());

					instanceList.sort(EDMUtil::compareEntityVersion);

					instance = !instanceList.isEmpty() ? instanceList.get(0) : null;
				}

				if (instance == null) {
					EDMEdmEntityId edmInstaceMetaId = new EDMEdmEntityId();
					edmInstaceMetaId.setMetaId(UUID.randomUUID().toString());
					em.persist(edmInstaceMetaId);

					instance = new EDMOrganization();
					instance.setUid(linkedEntity.getUid());
					instance.setState(State.PLACEHOLDER.toString());
					instance.setInstanceId(UUID.randomUUID().toString());
					instance.setEdmEntityIdByMetaId(edmInstaceMetaId);
					em.persist(instance);

				}

				edmObject.getFather().add(instance);

			}
		}

		edmObject.setUrl(eposDataModelObject.getURL());

		if (eposDataModelObject.getTelephone() != null) {
			if(edmObject.getOrganizationTelephonesByInstanceId()!=null)
				for(EDMOrganizationTelephone obj : edmObject.getOrganizationTelephonesByInstanceId()) {
					em.remove(obj);
				}
			edmObject.setOrganizationTelephonesByInstanceId(new ArrayList<>());
			for (String telephone : eposDataModelObject.getTelephone()) {
				EDMOrganizationTelephone edmOrganizationTelephone = new EDMOrganizationTelephone();

				edmOrganizationTelephone.setId(UUID.randomUUID().toString());
				edmOrganizationTelephone.setNumber(telephone);
				edmOrganizationTelephone.setOrganizationIdByInstanceOrganizationId(edmObject);

				edmObject.getOrganizationTelephonesByInstanceId().add(edmOrganizationTelephone);
			}
		}

		if (eposDataModelObject.getOwns() != null) {
			if(edmObject.getOwnsByInstanceId()!=null)
				for(EDMOrganizationOwner obj : edmObject.getOwnsByInstanceId()) {
					em.remove(obj);
				}
			edmObject.setOwnsByInstanceId(new ArrayList<>());
			for (LinkedEntity el : eposDataModelObject.getOwns()) {
				List<EDMFacility> instaceList = getFromDB(em, EDMFacility.class,
						"facility.findByUid", "UID", el.getUid());

				instaceList.sort(EDMUtil::compareEntityVersion);

				EDMFacility instance = !instaceList.isEmpty() ? instaceList.get(0) : null;

				EDMEdmEntityId edmInstaceMetaId;

				if (instance == null) {
					edmInstaceMetaId = new EDMEdmEntityId();
					edmInstaceMetaId.setMetaId(UUID.randomUUID().toString());
					em.persist(edmInstaceMetaId);

					instance = new EDMFacility();
					instance.setUid(el.getUid());
					instance.setState(State.PLACEHOLDER.toString());
					instance.setInstanceId(UUID.randomUUID().toString());
					instance.setEdmEntityIdByMetaId(edmInstaceMetaId);
					em.persist(instance);

				} else {
					edmInstaceMetaId = instance.getEdmEntityIdByMetaId();
				}

				EDMOrganizationOwner edmLink = new EDMOrganizationOwner();
				edmLink.setOrganizationByInstanceOrganizationId(edmObject);
				edmLink.setEdmEntityIdByMetaEntityId(edmInstaceMetaId);

				edmObject.getOwnsByInstanceId().add(edmLink);

			}
		}

		edmObject.setType(eposDataModelObject.getType());
		edmObject.setMaturity(eposDataModelObject.getMaturity());

		return new LinkedEntity().entityType(entityString)
				.instanceId(edmInstanceId)
				.metaId(edmObject.getEdmEntityIdByMetaId().getMetaId())
				.uid(eposDataModelObject.getUid());

	}

	@Override
	protected Organization mapFromDB(Object edmObject) {
		Organization o = new Organization();

		EDMOrganization edm = (EDMOrganization) edmObject;

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
		o.setIdentifier(
				edm.getOrganizationIdentifiersByInstanceId() != null ?
						edm.getOrganizationIdentifiersByInstanceId().stream()
						.map(i -> {
							Identifier identifier = new Identifier();
							identifier.setIdentifier(i.getIdentifier());
							identifier.setType(i.getType());
							return identifier;
						})
						.collect(Collectors.toList())
						: null
				);
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
		if (edm.getContactpointOrganizationsByInstanceId() != null) {
			o.setContactPoint(new LinkedList<>());
			edm.getContactpointOrganizationsByInstanceId().stream()
			.map(EDMContactpointOrganization::getContactpointByInstanceContactpointId)
			.forEach(e -> o.getContactPoint().add(
					new LinkedEntity()
					.metaId(e.getMetaId())
					.instanceId(e.getInstanceId())
					.uid(e.getUid())
					.entityType("ContactPoint")));
		}
		o.setEmail(
				edm.getOrganizationEmailsByInstanceId() != null ?
						edm.getOrganizationEmailsByInstanceId().stream().map(EDMOrganizationEmail::getEmail).collect(Collectors.toList())
						: new ArrayList<>()
				);
		o.setTelephone(
				edm.getOrganizationTelephonesByInstanceId() != null ?
						edm.getOrganizationTelephonesByInstanceId().stream().map(EDMOrganizationTelephone::getNumber).collect(Collectors.toList())
						: new ArrayList<>()
				);
		o.setLegalName(
				edm.getOrganizationLegalnameByInstanceId() != null ?
						edm.getOrganizationLegalnameByInstanceId().stream().map(EDMOrganizationLegalname::getLegalname).collect(Collectors.toList())
						: new ArrayList<>()
				);
		o.setAcronym(edm.getAcronym());
		o.setLeiCode(edm.getLeicode());
		o.setLogo(edm.getLogo());
		if (edm.getFather() != null) {
			o.setMemberOf(new LinkedList<>());
			edm.getFather()
			.forEach(e -> o.getContactPoint().add(
					new LinkedEntity()
					.metaId(e.getMetaId())
					.instanceId(e.getInstanceId())
					.uid(e.getUid())
					.entityType("Organization")));
		}
		o.setURL(edm.getUrl());
		o.setType(edm.getType());
		o.setMaturity(edm.getMaturity());
		if (edm.getOwnsByInstanceId() != null) {
			o.setOwns(new ArrayList<LinkedEntity>());
			for (EDMEdmEntityId edmMetaId : edm.getOwnsByInstanceId().stream().map(EDMOrganizationOwner::getEdmEntityIdByMetaEntityId).collect(Collectors.toList())) {
				if (edmMetaId.getFacilitiesByMetaId() != null && !edmMetaId.getFacilitiesByMetaId().isEmpty()) {
					ArrayList<EDMFacility> list = new ArrayList<>(edmMetaId.getFacilitiesByMetaId());
					list.sort(EDMUtil::compareEntityVersion);
					o.getOwns().add(new LinkedEntity().entityType("facility").instanceId(list.get(0).getInstanceId()).metaId(list.get(0).getMetaId()).uid(list.get(0).getUid()));
				}
				if (edmMetaId.getEquipmentByMetaId() != null && !edmMetaId.getEquipmentByMetaId().isEmpty()) {
					ArrayList<EDMEquipment> list = new ArrayList<>(edmMetaId.getEquipmentByMetaId());
					list.sort(EDMUtil::compareEntityVersion);
					o.getOwns().add(new LinkedEntity().entityType("equipment").instanceId(list.get(0).getInstanceId()).metaId(list.get(0).getMetaId()).uid(list.get(0).getUid()));
				}
			}
		}

		return o;
	}

}
