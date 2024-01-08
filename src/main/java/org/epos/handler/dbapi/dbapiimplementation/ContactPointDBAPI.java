package org.epos.handler.dbapi.dbapiimplementation;

import org.epos.eposdatamodel.ContactPoint;
import org.epos.eposdatamodel.Group;
import org.epos.eposdatamodel.LinkedEntity;
import org.epos.eposdatamodel.State;
import org.epos.handler.dbapi.model.*;
import org.epos.handler.dbapi.util.EDMUtil;
import org.epos.handler.dbapi.util.LoggerFormat;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.epos.handler.dbapi.util.DBUtil.getFromDB;
import static org.epos.handler.dbapi.util.DBUtil.getOneFromDB;

public class ContactPointDBAPI extends AbstractDBAPI<ContactPoint> {

    public ContactPointDBAPI() {
        super("contactpoint", EDMContactpoint.class);
    }

    @Override
    public LinkedEntity save(ContactPoint eposDataModelObject, EntityManager em, String edmInstanceId) {

        if (eposDataModelObject.getState().equals(State.PUBLISHED)
                && isAlreadyPublished(EDMContactpoint.class, "contactpoint.findByUidAndState", em, eposDataModelObject))
            return new LinkedEntity();

        //search for a existing instance placeholder to be populated
        EDMContactpoint edmObject = getOneFromDB(em, EDMContactpoint.class,
                "contactpoint.findByUid",
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
            edmObject = new EDMContactpoint();
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
            EDMContactpoint changedInstance = getOneFromDB(em, EDMContactpoint.class, "contactpoint.findByInstanceId",
                    "INSTANCEID", eposDataModelObject.getInstanceChangedId());
            if (changedInstance == null) {
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: " + edmObject.getUid() + ", state: " + edmObject.getState()
                        + " and instanceId: " + edmObject.getInstanceId() + ", have an invalid 'InstanceChangedId'.");
            }
            edmObject.setContactpointByInstanceChangedId(changedInstance);
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


        edmObject.setContactpointEmailsByInstanceId(new ArrayList<>());
        if (eposDataModelObject.getEmail() != null)
            for (String email : eposDataModelObject.getEmail()) {
                EDMContactpointEmail edmEmail = new EDMContactpointEmail();
                edmEmail.setId(UUID.randomUUID().toString());
                edmEmail.setEmail(email);
                edmEmail.setContactpointByInstanceContactpointId(edmObject);
                edmObject.getContactpointEmailsByInstanceId().add(edmEmail);
            }

        edmObject.setContactpointLanguageByInstanceId(new ArrayList<>());
        if (eposDataModelObject.getLanguage() != null)
            for (String ln : eposDataModelObject.getLanguage()) {
                EDMContactpointLanguage edmContactpointLanguage = new EDMContactpointLanguage();
                edmContactpointLanguage.setId(UUID.randomUUID().toString());
                edmContactpointLanguage.setLanguage(ln);
                edmContactpointLanguage.setContactpointByInstanceContactpointId(edmObject);
                edmObject.getContactpointLanguageByInstanceId().add(edmContactpointLanguage);
            }

        edmObject.setRole(eposDataModelObject.getRole());

        edmObject.setContactpointTelephonesByInstanceId(new ArrayList<>());
        if (eposDataModelObject.getTelephone() != null) {
            for (String telephone : eposDataModelObject.getTelephone()) {
                EDMContactpointTelephone edmTelephone = new EDMContactpointTelephone();
                edmTelephone.setId(UUID.randomUUID().toString());
                edmTelephone.setTelnumber(telephone);
                edmTelephone.setContactpointByInstanceContactpointId(edmObject);
                edmObject.getContactpointTelephonesByInstanceId().add(edmTelephone);
            }
        }

        if (eposDataModelObject.getPerson() != null) {
            List<EDMPerson> edmPeoples = getFromDB(em, EDMPerson.class,
                    "person.findByUid", "UID", eposDataModelObject.getPerson().getUid());

            edmPeoples.sort(EDMUtil::compareEntityVersion);

            EDMPerson edmPerson = !edmPeoples.isEmpty() ?
                    edmPeoples.get(0) : null;

            EDMEdmEntityId edmPersonMetaId;

            if (edmPerson == null) {
                edmPersonMetaId = new EDMEdmEntityId();
                edmPersonMetaId.setMetaId(UUID.randomUUID().toString());
                em.persist(edmPersonMetaId);

                edmPerson = new EDMPerson();
                edmPerson.setUid(eposDataModelObject.getPerson().getUid());
                edmPerson.setState(State.PLACEHOLDER.toString());
                edmPerson.setInstanceId(UUID.randomUUID().toString());
                em.persist(edmPerson);


                edmPerson.setEdmEntityIdByMetaId(edmPersonMetaId);
            } else {
                edmPersonMetaId = edmPerson.getEdmEntityIdByMetaId();
            }

            edmObject.setEdmEntityIdByMetaPersonId(edmPersonMetaId);
        }

        if (eposDataModelObject.getOrganization() != null) {
            List<EDMOrganization> edmOrganizations = getFromDB(em, EDMOrganization.class,
                    "organization.findByUid", "UID", eposDataModelObject.getOrganization().getUid());

            edmOrganizations.sort(EDMUtil::compareEntityVersion);

            EDMOrganization edmOrganization = !edmOrganizations.isEmpty() ?
                    edmOrganizations.get(0) : null;

            EDMEdmEntityId edmMetaOrganization;

            if (edmOrganization == null) {
                edmOrganization = new EDMOrganization();
                edmOrganization.setUid(eposDataModelObject.getOrganization().getUid());
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

            edmObject.setEdmEntityIdByMetaOrganizationId(edmMetaOrganization);
        }


        return new LinkedEntity().entityType(entityString)
                .instanceId(edmInstanceId)
                .metaId(edmObject.getEdmEntityIdByMetaId().getMetaId())
                .uid(edmObject.getUid());
    }


    @Override
    protected ContactPoint mapFromDB(Object edmObject) {
        ContactPoint o = new ContactPoint();

        EDMContactpoint edm = (EDMContactpoint) edmObject;

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
        o.setEmail(
                edm.getContactpointEmailsByInstanceId() != null ?
                        edm.getContactpointEmailsByInstanceId().stream()
                                .map(EDMContactpointEmail::getEmail).collect(Collectors.toList()) :
                        null
        );
        o.setLanguage(
                edm.getContactpointLanguageByInstanceId() != null ?
                        edm.getContactpointLanguageByInstanceId().stream()
                                .map(EDMContactpointLanguage::getLanguage).collect(Collectors.toList()) :
                        null
        );
        o.setRole(edm.getRole());
        o.setTelephone(
                edm.getContactpointTelephonesByInstanceId() != null ?
                        edm.getContactpointTelephonesByInstanceId().stream()
                                .map(EDMContactpointTelephone::getTelnumber).collect(Collectors.toList()) :
                        null
        );
        if (edm.getEdmEntityIdByMetaOrganizationId() != null && edm.getEdmEntityIdByMetaOrganizationId().getOrganizationsByMetaId() != null
                && !edm.getEdmEntityIdByMetaOrganizationId().getOrganizationsByMetaId().isEmpty()) {
            List<EDMOrganization> so = new ArrayList<>(edm.getEdmEntityIdByMetaOrganizationId().getOrganizationsByMetaId());
            so.sort(EDMUtil::compareEntityVersion);
            o.setOrganization(new LinkedEntity().metaId(so.get(0).getMetaId()).entityType("Organization").instanceId(so.get(0).getInstanceId()).uid(so.get(0).getUid()));
        }
        if (edm.getEdmEntityIdByMetaPersonId() != null && edm.getEdmEntityIdByMetaPersonId().getPeopleByMetaId() != null
                && !edm.getEdmEntityIdByMetaPersonId().getPeopleByMetaId().isEmpty()) {
            List<EDMPerson> so = new ArrayList<>(edm.getEdmEntityIdByMetaPersonId().getPeopleByMetaId());
            so.sort(EDMUtil::compareEntityVersion);
            o.setPerson(new LinkedEntity().metaId(so.get(0).getMetaId()).entityType("Person").instanceId(so.get(0).getInstanceId()).uid(so.get(0).getUid()));
        }

        return o;
    }


}













