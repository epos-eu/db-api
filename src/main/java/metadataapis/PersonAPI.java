package metadataapis;

import abstractapis.AbstractAPI;
import commonapis.*;
import model.*;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.*;
import java.util.stream.Collectors;

public class PersonAPI extends AbstractAPI<org.epos.eposdatamodel.Person> {

    public PersonAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.Person obj) {

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

        obj = (org.epos.eposdatamodel.Person) VersioningStatusAPI.checkVersion(obj);

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
            AddressAPI addressAPI = new AddressAPI(EntityNames.ADDRESS.name(), Address.class);
            LinkedEntity le = addressAPI.create(obj.getAddress());
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
            for(org.epos.eposdatamodel.Identifier identifier : obj.getIdentifier()){
                LinkedEntity le = identifierAPI.create(identifier);
                PersonIdentifier pi = new PersonIdentifier();
                pi.setPersonByPersonInstanceId(edmobj);
                pi.setPersonInstanceId(edmobj.getInstanceId());
                pi.setIdentifierInstanceId(le.getInstanceId());
                pi.setIdentifierByIdentifierInstanceId((Identifier) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(),Identifier.class).get(0));

                edmobj.getPersonIdentifiersByInstanceId().add(pi);

                dbaccess.updateObject(pi);
            }
        }

        /** AFFILIATION **/
        if (obj.getAffiliation() != null && !obj.getAffiliation().isEmpty()) {
            OrganizationAPI organizationAPI = new OrganizationAPI(EntityNames.ORGANIZATION.name(), Organization.class);
            edmobj.setOrganizationAffiliationsByInstanceId(new ArrayList<>());
            for(LinkedEntity organization : obj.getAffiliation()){
                List<Organization> list = dbaccess.getOneFromDBByInstanceId(organization.getInstanceId(),Organization.class);
                Organization organization1 = null;
                if(list.isEmpty()){
                    LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(organization);
                    organization1 = (Organization) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Organization.class).get(0);
                } else {
                    organization1 = list.get(0);
                }
                OrganizationAffiliation pi = new OrganizationAffiliation();
                pi.setPersonByPersonInstanceId(edmobj);
                pi.setPersonInstanceId(edmobj.getInstanceId());
                pi.setOrganizationInstanceId(organization1.getInstanceId());
                pi.setOrganizationByOrganizationInstanceId(organization1);

                edmobj.getOrganizationAffiliationsByInstanceId().add(pi);

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
                createInnerElement(ElementType.TELEPHONE, tel, edmobj);
            }
        }

        /* EMAIL */
        if(obj.getEmail()!=null && !obj.getEmail().isEmpty()){
            for(String email : obj.getEmail()) {
                createInnerElement(ElementType.EMAIL, email, edmobj);
            }
        }

        getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                    .instanceId(edmobj.getInstanceId())
                    .metaId(edmobj.getMetaId())
                    .uid(edmobj.getUid());

    }

    private void createInnerElement(ElementType elementType, String value, Person edmobj){
        org.epos.eposdatamodel.Element element = new org.epos.eposdatamodel.Element();
        element.setType(elementType);
        element.setValue(value);
        ElementAPI api = new ElementAPI(EntityNames.ELEMENT.name(), Element.class);
        LinkedEntity le = api.create(element);
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
        Person edmobj = (Person) getDbaccess().getOneFromDBByInstanceId(instanceId, Person.class).get(0);

        org.epos.eposdatamodel.Person o = new org.epos.eposdatamodel.Person();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());

        if(edmobj.getPersonIdentifiersByInstanceId().size()>0) {
            for(PersonIdentifier ed : edmobj.getPersonIdentifiersByInstanceId()) {
                Identifier el = ed.getIdentifierByIdentifierInstanceId();
                org.epos.eposdatamodel.Identifier id = new org.epos.eposdatamodel.Identifier();
                id.setIdentifier(el.getValue());
                id.setType(el.getType());
                o.addIdentifier(id);
            }
        }

        o.setFamilyName(edmobj.getFamilyname());
        o.setGivenName(edmobj.getGivenname());


        if(edmobj.getAddressByAddressId()!=null) {
            Address address = edmobj.getAddressByAddressId();
            org.epos.eposdatamodel.Address address1 = new org.epos.eposdatamodel.Address();
            address1.setLocality(address.getLocality());
            address1.setCountryCode(address.getCountrycode());
            address1.setCountry(address.getCountry());
            address1.setPostalCode(address.getPostalCode());
            address1.setStreet(address.getStreet());
            o.setAddress(address1);
        }

        if(edmobj.getPersonElementsByInstanceId().size()>0) {
            for(PersonElement ed : edmobj.getPersonElementsByInstanceId()) {
                Element el = ed.getElementByElementInstanceId();
                if(el.getType().equals(ElementType.TELEPHONE)) o.addTelephone(el.getValue());
                if(el.getType().equals(ElementType.EMAIL)) o.addEmail(el.getValue());
            }
        }

        o.setQualifications(edmobj.getQualifications() != null ?
                Arrays.stream(edmobj.getQualifications().split(", ")).collect(Collectors.toList())
                : new ArrayList<>());

        o.setCVURL(edmobj.getCvurl());

        if (edmobj.getOrganizationAffiliationsByInstanceId() != null) {
            o.setAffiliation(new LinkedList<>());
            for(OrganizationAffiliation organizationAffiliation : edmobj.getOrganizationAffiliationsByInstanceId()){
                OrganizationAPI organizationAPI = new OrganizationAPI(EntityNames.ORGANIZATION.name(), Organization.class);
                o.addAffiliation(organizationAPI.retrieveLinkedEntity(organizationAffiliation.getOrganizationInstanceId()));
            }
        }

        return o;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        Person edmobj = (Person) getDbaccess().getOneFromDBByInstanceId(instanceId, Person.class).get(0);

        LinkedEntity o = new LinkedEntity();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setEntityType(EntityNames.PERSON.name());

        return o;
    }

}
