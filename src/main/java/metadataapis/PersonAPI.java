package metadataapis;

import abstractapis.AbstractAPI;
import commonapis.*;
import model.*;
import org.epos.eposdatamodel.ContactPoint;
import org.epos.eposdatamodel.LinkedEntity;
import relationsapi.RelationChecker;

import java.util.*;
import java.util.stream.Collectors;

public class PersonAPI extends AbstractAPI<org.epos.eposdatamodel.Person> {

    public PersonAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.Person obj, StatusType overrideStatus) {

        List<Person> returnList = getDbaccess().getOneFromDB(
                obj.getInstanceId(),
                obj.getMetaId(),
                obj.getUid(),
                obj.getVersionId(),
                getEdmClass());

        if(!returnList.isEmpty()){
            obj.setInstanceId(returnList.get(0).getInstanceId());
            obj.setMetaId(returnList.get(0).getMetaId());
            obj.setUid(returnList.get(0).getUid());
            obj.setVersionId(returnList.get(0).getVersionId());
        }

        obj = (org.epos.eposdatamodel.Person) VersioningStatusAPI.checkVersion(obj, overrideStatus);

        EposDataModelEntityIDAPI.addEntityToEDMEntityID(obj.getMetaId(), entityName);

        Person edmobj = new Person();

        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());

        getDbaccess().updateObject(edmobj);

        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setFamilyname(obj.getFamilyName());
        edmobj.setGivenname(obj.getGivenName());
        edmobj.setCvurl(obj.getCVURL());
        edmobj.setQualifications(obj.getQualifications()!=null? String.join(", ", obj.getQualifications()) : null);

        /** ADDRESS **/
        if (obj.getAddress() != null) {
            List<Address> addressList = getDbaccess().getAllFromDB(Address.class);
            for(Address item : addressList){
                if(item.getInstanceId().equals(obj.getAddress().getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(obj.getAddress(), overrideStatus);
            addressList = getDbaccess().getOneFromDBByInstanceId(le.getInstanceId(),Address.class);
            edmobj.setAddressId(addressList.get(0).getInstanceId());
            edmobj.setAddressByAddressId(addressList.get(0));
        }

        /** IDENTIFIER **/
        if (obj.getIdentifier() != null && !obj.getIdentifier().isEmpty()) {
            List<PersonIdentifier> identifierList = getDbaccess().getAllFromDB(PersonIdentifier.class);
            for(PersonIdentifier item : identifierList){
                if(item.getPersonByPersonInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                    List<Identifier> list2 = getDbaccess().getOneFromDBByInstanceId(item.getIdentifierInstanceId(), Identifier.class);
                    if(list2.size()>0) getDbaccess().deleteObject(list2.get(0));
                }
            }
            IdentifierAPI identifierAPI = new IdentifierAPI(EntityNames.IDENTIFIER.name(), Identifier.class);
            edmobj.setPersonIdentifiersByInstanceId(new ArrayList<>());
            for(org.epos.eposdatamodel.LinkedEntity identifier : obj.getIdentifier()){
                LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(identifier, overrideStatus);
                List<Identifier> identifierList1 = dbaccess.getOneFromDBByInstanceId(le.getInstanceId(),Identifier.class);
                if(!identifierList1.isEmpty()) {
                    PersonIdentifier pi = new PersonIdentifier();
                    pi.setPersonByPersonInstanceId(edmobj);
                    pi.setPersonInstanceId(edmobj.getInstanceId());
                    pi.setIdentifierInstanceId(le.getInstanceId());
                    pi.setIdentifierByIdentifierInstanceId(identifierList1.get(0));

                    edmobj.getPersonIdentifiersByInstanceId().add(pi);

                    dbaccess.updateObject(pi);
                }
            }
        }

        /** AFFILIATION **/
        if (obj.getAffiliation() != null && !obj.getAffiliation().isEmpty()) {
            OrganizationAPI organizationAPI = new OrganizationAPI(EntityNames.ORGANIZATION.name(), Organization.class);
            edmobj.setOrganizationAffiliationsByInstanceId(new ArrayList<>());
            for(LinkedEntity organization : obj.getAffiliation()){

                Organization organization1 = (Organization) RelationChecker.checkRelation(organization, overrideStatus, Organization.class);

                OrganizationAffiliation pi = new OrganizationAffiliation();
                pi.setPersonByPersonInstanceId(edmobj);
                pi.setPersonInstanceId(edmobj.getInstanceId());
                pi.setOrganizationInstanceId(organization1.getInstanceId());
                pi.setOrganizationByOrganizationInstanceId(organization1);

                edmobj.getOrganizationAffiliationsByInstanceId().add(pi);

                dbaccess.updateObject(pi);
            }
        }

        /** CONTACTPOINT **/
        if (obj.getContactPoint() != null && !obj.getContactPoint().isEmpty()) {
            edmobj.setPersonContactpointsByInstanceId(new ArrayList<>());
            for(LinkedEntity contactpoint : obj.getContactPoint()){

                Contactpoint contactpoint1 = (Contactpoint) RelationChecker.checkRelation(contactpoint, overrideStatus, Contactpoint.class);

                PersonContactpoint pi = new PersonContactpoint();
                pi.setPersonByPersonInstanceId(edmobj);
                pi.setPersonInstanceId(edmobj.getInstanceId());
                pi.setContactpointInstanceId(contactpoint1.getInstanceId());
                pi.setContactpointByContactpointInstanceId(contactpoint1);

                edmobj.getPersonContactpointsByInstanceId().add(pi);

                dbaccess.updateObject(pi);
            }
        }

        List<PersonElement> elementslist = getDbaccess().getAllFromDB(PersonElement.class);
        edmobj.setPersonElementsByInstanceId(new ArrayList<>());
        for(PersonElement item : elementslist){
            if(item.getPersonInstanceId().equals(obj.getInstanceId())){
                getDbaccess().deleteObject(item);
                List<Element> list2 = getDbaccess().getOneFromDBByInstanceId(item.getElementInstanceId(), Element.class);
                if(list2.size()>0) getDbaccess().deleteObject(list2.get(0));
            }
        }
        /* TELEPHONE */
        if(obj.getTelephone()!=null && !obj.getTelephone().isEmpty()){
            for(String tel : obj.getTelephone()) {
                createInnerElement(ElementType.TELEPHONE, tel, edmobj, overrideStatus);
            }
        }

        /* EMAIL */
        if(obj.getEmail()!=null && !obj.getEmail().isEmpty()){
            for(String email : obj.getEmail()) {
                createInnerElement(ElementType.EMAIL, email, edmobj, overrideStatus);
            }
        }

        getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                    .instanceId(edmobj.getInstanceId())
                    .metaId(edmobj.getMetaId())
                    .uid(edmobj.getUid());

    }

    private void createInnerElement(ElementType elementType, String value, Person edmobj, StatusType overrideStatus){
        org.epos.eposdatamodel.Element element = new org.epos.eposdatamodel.Element();
        element.setType(elementType);
        element.setValue(value);
        ElementAPI api = new ElementAPI(EntityNames.ELEMENT.name(), Element.class);
        LinkedEntity le = api.create(element, overrideStatus);
        List<Element> el = dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Element.class);
        PersonElement ce = new PersonElement();
        ce.setPersonByPersonInstanceId(edmobj);
        ce.setPersonInstanceId(edmobj.getInstanceId());
        ce.setElementByElementInstanceId(el.get(0));
        ce.setElementInstanceId(el.get(0).getInstanceId());

        edmobj.getPersonElementsByInstanceId().add(ce);

        dbaccess.updateObject(ce);
    }


    @Override
    public org.epos.eposdatamodel.Person retrieve(String instanceId) {
        List<Person> elementList = getDbaccess().getOneFromDBByInstanceId(instanceId, Person.class);
        if(elementList!=null && !elementList.isEmpty()) {
            Person edmobj = elementList.get(0);
            org.epos.eposdatamodel.Person o = new org.epos.eposdatamodel.Person();
            o.setInstanceId(edmobj.getInstanceId());
            o.setMetaId(edmobj.getMetaId());
            o.setUid(edmobj.getUid());

            if (edmobj.getPersonIdentifiersByInstanceId().size() > 0) {
                IdentifierAPI api = new IdentifierAPI(EntityNames.IDENTIFIER.name(), Identifier.class);
                for (PersonIdentifier ed : edmobj.getPersonIdentifiersByInstanceId()) {
                    Identifier el = ed.getIdentifierByIdentifierInstanceId();
                    o.addIdentifier(api.retrieveLinkedEntity(el.getInstanceId()));
                }
            }

            o.setFamilyName(edmobj.getFamilyname());
            o.setGivenName(edmobj.getGivenname());


            if (edmobj.getAddressByAddressId() != null) {
                AddressAPI api = new AddressAPI(EntityNames.ADDRESS.name(), Address.class);
                o.setAddress(api.retrieveLinkedEntity(edmobj.getAddressByAddressId().getInstanceId()));
            }


            if (edmobj.getPersonElementsByInstanceId().size() > 0) {
                for (PersonElement ed : edmobj.getPersonElementsByInstanceId()) {
                    Element el = ed.getElementByElementInstanceId();
                    if (el.getType().equals(ElementType.TELEPHONE)) o.addTelephone(el.getValue());
                    if (el.getType().equals(ElementType.EMAIL)) o.addEmail(el.getValue());
                }
            }

            o.setQualifications(edmobj.getQualifications() != null ?
                    Arrays.stream(edmobj.getQualifications().split(", ")).collect(Collectors.toList())
                    : new ArrayList<>());

            o.setCVURL(edmobj.getCvurl());

            if (edmobj.getOrganizationAffiliationsByInstanceId() != null) {
                o.setAffiliation(new LinkedList<>());
                for (OrganizationAffiliation organizationAffiliation : edmobj.getOrganizationAffiliationsByInstanceId()) {
                    OrganizationAPI organizationAPI = new OrganizationAPI(EntityNames.ORGANIZATION.name(), Organization.class);
                    o.addAffiliation(organizationAPI.retrieveLinkedEntity(organizationAffiliation.getOrganizationInstanceId()));
                }
            }

            if (edmobj.getPersonContactpointsByInstanceId() != null) {
                o.setContactPoint(new LinkedList<>());
                for (PersonContactpoint personContactpoint : edmobj.getPersonContactpointsByInstanceId()) {
                    ContactPointAPI contactPointAPI = new ContactPointAPI(EntityNames.CONTACTPOINT.name(), Contactpoint.class);
                    o.addContactPoint(contactPointAPI.retrieveLinkedEntity(personContactpoint.getContactpointInstanceId()));
                }
            }

            o = (org.epos.eposdatamodel.Person) VersioningStatusAPI.retrieveVersion(o);

            return o;
        }
        return null;
    }

    @Override
    public List<org.epos.eposdatamodel.Person> retrieveAll() {
        List<Person> list = getDbaccess().getAllFromDB(Person.class);
        List<org.epos.eposdatamodel.Person> returnList = new ArrayList<>();
        list.parallelStream().forEach(item -> {
            returnList.add(retrieve(item.getInstanceId()));
        });
        return returnList;
    }


    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        List<Person> elementList = getDbaccess().getOneFromDBByInstanceId(instanceId, Person.class);
        if(elementList!=null && !elementList.isEmpty()) {
            Person edmobj = elementList.get(0);
            LinkedEntity o = new LinkedEntity();
            o.setInstanceId(edmobj.getInstanceId());
            o.setMetaId(edmobj.getMetaId());
            o.setUid(edmobj.getUid());
            o.setEntityType(EntityNames.PERSON.name());

            return o;
        }
        return null;
    }

}
