package org.epos.handler.dbapi.dbapiimplementation;

import org.epos.eposdatamodel.*;
import org.epos.handler.dbapi.model.*;
import org.epos.handler.dbapi.service.DBService;
import org.epos.handler.dbapi.util.EDMUtil;
import org.epos.handler.dbapi.util.LoggerFormat;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static org.epos.handler.dbapi.util.DBUtil.getFromDB;
import static org.epos.handler.dbapi.util.DBUtil.getOneFromDB;

public class PersonDBAPI extends AbstractDBAPI<Person> {

    public PersonDBAPI() {
        super("person", EDMPerson.class);
    }

    public Person getByAuthId(String authId) {
        EntityManager em = new DBService().getEntityManager();
        em.getTransaction().begin();

        EDMPerson edmPerson = getOneFromDB(em, EDMPerson.class, "person.findByAuthId", "AUTHID", authId);

        em.getTransaction().commit();
        em.close();

        return edmPerson != null ? mapFromDB(edmPerson) : null;
    }

    @Override
    public LinkedEntity save(Person eposDataModelObject, EntityManager em, String edmInstanceId) {
        if (eposDataModelObject.getState().equals(State.PUBLISHED)
                && isAlreadyPublished(EDMPerson.class, "person.findByUidAndState", em, eposDataModelObject))
            return new LinkedEntity();

        //search for a existing instance placeholder to be populated
        EDMPerson edmObject = getOneFromDB(em, EDMPerson.class,
                "person.findByUid",
                "UID", eposDataModelObject.getUid());

        //if there's a placeholder for the entity check if is passed a specific metaid
        //only if the metaid is the same of the placeholder merge the two (the placeholder and the passed entity)
        EDMEdmEntityId edmMetaId;

        boolean merge = false;

        if (edmObject != null &&
                (eposDataModelObject.getMetaId() == null || (eposDataModelObject.getMetaId() != null && eposDataModelObject.getMetaId().equals(edmObject.getMetaId())))) {
            merge = true;
            edmInstanceId = eposDataModelObject.getInstanceId();
        } else {
            edmObject = new EDMPerson();
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
            EDMPerson changedInstance = getOneFromDB(em, EDMPerson.class, "person.findByInstanceId",
                    "INSTANCEID", eposDataModelObject.getInstanceChangedId());
            if (changedInstance == null) {
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: " + edmObject.getUid() + ", state: " + edmObject.getState()
                        + " and instanceId: " + edmObject.getInstanceId() + ", have an invalid 'InstanceChangedId'.");
            }
            edmObject.setPersonByInstanceChangedId(changedInstance);
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


        edmObject.setPersonIdentifiersByInstanceId(new ArrayList<>());
        if (eposDataModelObject.getIdentifier() != null && !eposDataModelObject.getIdentifier().isEmpty()) {
            for (Identifier identifier : eposDataModelObject.getIdentifier()) {

                EDMPersonIdentifier edmPersonIdentifier = new EDMPersonIdentifier();
                edmPersonIdentifier.setId(UUID.randomUUID().toString());
                edmPersonIdentifier.setPersonByInstancePersonId(edmObject);
                edmPersonIdentifier.setIdentifier(identifier.getIdentifier());
                edmPersonIdentifier.setType(identifier.getType());
                edmObject.getPersonIdentifiersByInstanceId().add(edmPersonIdentifier);
            }
        } else {
            System.err.println("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: " + edmObject.getUid() + ", state: " + edmObject.getState()
                    + " and instanceId: " + edmObject.getInstanceId() + ", the entity doens't have any identifier.");
        }


        edmObject.setAuthIdentifier(eposDataModelObject.getAuthIdentifier());

        edmObject.setFamilyname(eposDataModelObject.getFamilyName());
        edmObject.setGivenname(eposDataModelObject.getGivenName());

        edmObject.setTmpRole(Objects.nonNull(eposDataModelObject.getRole()) ? eposDataModelObject.getRole().toString() : null);

        if (eposDataModelObject.getQualifications() != null) {
            edmObject.setQualifications(String.join(", ", eposDataModelObject.getQualifications()));
        }

        edmObject.setCvurl(eposDataModelObject.getCVURL());

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


        edmObject.setPersonEmailByInstanceId(new ArrayList<>());
        for (String email : eposDataModelObject.getEmail()) {
            EDMPersonEmail edmEmail = new EDMPersonEmail();
            edmEmail.setId(UUID.randomUUID().toString());
            edmEmail.setEmail(email);
            edmEmail.setPersonIdByInstancePersonId(edmObject);
            edmObject.getPersonEmailByInstanceId().add(edmEmail);
        }

        edmObject.setPersonTelephonesByInstanceId(new ArrayList<>());
        if (eposDataModelObject.getTelephone() != null) {
            for (String telephone : eposDataModelObject.getTelephone()) {
                EDMPersonTelephone edmTelephone = new EDMPersonTelephone();
                edmTelephone.setId(UUID.randomUUID().toString());
                edmTelephone.setTelnumber(telephone);
                edmTelephone.setPersonIdByInstancePersonId(edmObject);
                edmObject.getPersonTelephonesByInstanceId().add(edmTelephone);
            }
        }

        edmObject.setAffiliationsByInstanceId(new ArrayList<>());
        if (eposDataModelObject.getAffiliation() != null) {
            for (LinkedEntity linkedEntity : eposDataModelObject.getAffiliation()) {

                List<EDMOrganization> edmOrganizations = getFromDB(em, EDMOrganization.class,
                        "organization.findByUid", "UID", linkedEntity.getUid());

                edmOrganizations.sort(EDMUtil::compareEntityVersion);

                EDMOrganization edmOrganization = !edmOrganizations.isEmpty() ?
                        edmOrganizations.get(0) : null;

                EDMEdmEntityId edmOrganizationMetaId;

                if (edmOrganization == null) {
                    edmOrganization = new EDMOrganization();
                    edmOrganization.setUid(linkedEntity.getUid());
                    edmOrganization.setState(State.PLACEHOLDER.toString());
                    edmOrganization.setInstanceId(UUID.randomUUID().toString());
                    em.persist(edmOrganization);

                    edmOrganizationMetaId = new EDMEdmEntityId();
                    edmOrganizationMetaId.setMetaId(UUID.randomUUID().toString());
                    em.persist(edmOrganizationMetaId);

                    edmOrganization.setEdmEntityIdByMetaId(edmOrganizationMetaId);
                } else {
                    edmOrganizationMetaId = edmOrganization.getEdmEntityIdByMetaId();
                }


                EDMAffiliation edmAffiliation = new EDMAffiliation();
                edmAffiliation.setPersonByInstancePersonId(edmObject);
                edmAffiliation.setEdmEntityIdByMetaOrganizationId(edmOrganizationMetaId);

                em.persist(edmAffiliation);

                if (edmOrganizationMetaId.getAffiliationsByMetaId() == null)
                    edmOrganizationMetaId.setAffiliationsByMetaId(new ArrayList<>());
                edmOrganizationMetaId.getAffiliationsByMetaId().add(edmAffiliation);

                edmObject.getAffiliationsByInstanceId().add(edmAffiliation);
            }
        }

        return new LinkedEntity().entityType(entityString)
                .instanceId(edmInstanceId)
                .metaId(edmObject.getEdmEntityIdByMetaId().getMetaId())
                .uid(eposDataModelObject.getUid());

    }

    @Override
    protected Person mapFromDB(Object edmObject) {
        Person o = new Person();

        EDMPerson edm = (EDMPerson) edmObject;

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
                edm.getPersonIdentifiersByInstanceId() != null ?
                        edm.getPersonIdentifiersByInstanceId().stream()
                                .map(elem -> {
                                    Identifier identifier = new Identifier();
                                    identifier.setIdentifier(elem.getIdentifier());
                                    identifier.setType(elem.getType());
                                    return identifier;
                                }).collect(Collectors.toList())
                        : new ArrayList<>());

        o.setFamilyName(edm.getFamilyname());
        o.setGivenName(edm.getGivenname());
        o.setAuthIdentifier(edm.getAuthIdentifier());
        o.setRole(Objects.nonNull(edm.getTmpRole()) ? Role.valueOf(edm.getTmpRole()) : null);

        o.setAuthorizedGroup(
                edm.getEdmEntityIdByMetaId() != null && edm.getEdmEntityIdByMetaId().getGroupUsersByMetaId() != null ?
                        edm.getEdmEntityIdByMetaId().getGroupUsersByMetaId().stream()
                                .map(e -> {
                                    Group group = new Group();
                                    group.setRole(Role.valueOf(e.getRoleId()));

                                    EDMGroup groupByGroupId = e.getGroupByGroupId();

                                    group.setName(groupByGroupId.getName());
                                    group.setDescription(groupByGroupId.getDescription());
                                    group.setId(groupByGroupId.getId());
                                    return group;
                                })
                                .collect(Collectors.toList())
                        : null
        );

        if (edm.getAddressByAddressId() != null) {
            EDMAddress edmAddress = edm.getAddressByAddressId();
            Address address = new Address();
            address.setCountry(edmAddress.getCountry());
            address.setLocality(edmAddress.getLocality());
            address.setStreet(edmAddress.getStreet());
            address.setPostalCode(edmAddress.getPostalCode());
            address.setCountryCode(edmAddress.getCountrycode());
            o.setAddress(address);
        } else o.setAddress(null);

        o.setEmail(edm.getPersonEmailByInstanceId() != null ?
                edm.getPersonEmailByInstanceId().stream().map(EDMPersonEmail::getEmail).collect(Collectors.toList())
                : new ArrayList<>());
        o.setQualifications(edm.getQualifications() != null ?
                Arrays.stream(edm.getQualifications().split(", ")).collect(Collectors.toList())
                : new ArrayList<>());
        o.setTelephone(edm.getPersonTelephonesByInstanceId() != null ?
                edm.getPersonTelephonesByInstanceId().stream().map(EDMPersonTelephone::getTelnumber).collect(Collectors.toList())
                : new ArrayList<>());
        o.setCVURL(edm.getCvurl());

        if (edm.getAffiliationsByInstanceId() != null) {
            o.setAffiliation(new LinkedList<>());
            for (EDMEdmEntityId edmMetaId : edm.getAffiliationsByInstanceId().stream().map(EDMAffiliation::getEdmEntityIdByMetaOrganizationId).collect(Collectors.toList())) {
                if (edmMetaId.getOrganizationsByMetaId() != null && !edmMetaId.getOrganizationsByMetaId().isEmpty()) {
                    ArrayList<EDMOrganization> listOrg = new ArrayList<>(edmMetaId.getOrganizationsByMetaId());
                    listOrg.sort(EDMUtil::compareEntityVersion);
                    o.getAffiliation().add(new LinkedEntity().uid(listOrg.get(0).getUid()).metaId(listOrg.get(0).getMetaId()).instanceId(listOrg.get(0).getInstanceId()).entityType("Organization"));
                }
            }
        }


        return o;
    }

}
