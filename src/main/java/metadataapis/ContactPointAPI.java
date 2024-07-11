package metadataapis;

import abstractapis.AbstractAPI;
import commonapis.ElementAPI;
import commonapis.EposDataModelEntityIDAPI;
import commonapis.LinkedEntityAPI;
import commonapis.VersioningStatusAPI;
import model.*;
import org.epos.eposdatamodel.ContactPoint;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.*;

public class ContactPointAPI extends AbstractAPI<ContactPoint> {

    public ContactPointAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.ContactPoint obj, StatusType overrideStatus) {

        List<Contactpoint> returnList = getDbaccess().getOneFromDB(
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

        obj = (org.epos.eposdatamodel.ContactPoint) VersioningStatusAPI.checkVersion(obj, overrideStatus);

        EposDataModelEntityIDAPI.addEntityToEDMEntityID(obj.getMetaId(), entityName);

        Contactpoint edmobj = new Contactpoint();

        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());

        getDbaccess().updateObject(edmobj);

        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setRole(obj.getRole());


        List<ContactpointElement> elementslist = getDbaccess().getAllFromDB(ContactpointElement.class);
        for(ContactpointElement item : elementslist){
            if(item.getContactpointInstanceId().equals(obj.getInstanceId())){
                getDbaccess().deleteObject(item);
            }
        }

        edmobj.setContactpointElementsByInstanceId(new ArrayList<>());

        /* LANGUAGE */
        if(obj.getLanguage()!=null && !obj.getLanguage().isEmpty()){
            for(String lang : obj.getLanguage()) {
                createInnerElement(ElementType.LANGUAGE, lang, edmobj, overrideStatus);
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

    private void createInnerElement(ElementType elementType, String value, Contactpoint edmobj, StatusType overrideStatus){
        org.epos.eposdatamodel.Element element = new org.epos.eposdatamodel.Element();
        element.setType(elementType);
        element.setValue(value);
        ElementAPI api = new ElementAPI(EntityNames.ELEMENT.name(), Element.class);
        LinkedEntity le = api.create(element, overrideStatus);
        List<Element> el = dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Element.class);
        ContactpointElement ce = new ContactpointElement();
        ce.setContactpointByContactpointInstanceId(edmobj);
        ce.setContactpointInstanceId(edmobj.getInstanceId());
        ce.setElementByElementInstanceId(el.get(0));
        ce.setElementInstanceId(el.get(0).getInstanceId());

        edmobj.getContactpointElementsByInstanceId().add(ce);

        dbaccess.updateObject(ce);
    }


    @Override
    public org.epos.eposdatamodel.ContactPoint retrieve(String instanceId) {
        List<Contactpoint> elementList = getDbaccess().getOneFromDBByInstanceId(instanceId, Contactpoint.class);
        if(elementList!=null && !elementList.isEmpty()) {
            Contactpoint edmobj = elementList.get(0);
            org.epos.eposdatamodel.ContactPoint o = new org.epos.eposdatamodel.ContactPoint();
            o.setInstanceId(edmobj.getInstanceId());
            o.setMetaId(edmobj.getMetaId());
            o.setUid(edmobj.getUid());
            o.setRole(edmobj.getRole());

            if (edmobj.getContactpointElementsByInstanceId().size() > 0) {
                for (ContactpointElement ed : edmobj.getContactpointElementsByInstanceId()) {
                    Element el = ed.getElementByElementInstanceId();
                    if (el.getType().equals(ElementType.TELEPHONE)) o.addTelephone(el.getValue());
                    if (el.getType().equals(ElementType.EMAIL)) o.addEmail(el.getValue());
                    if (el.getType().equals(ElementType.LANGUAGE)) o.addLanguage(el.getValue());
                }
            }

            for (OrganizationContactpoint organizationContactpoint : edmobj.getOrganizationContactpointsByInstanceId()) {
                OrganizationAPI organizationAPI = new OrganizationAPI(EntityNames.ORGANIZATION.name(), Organization.class);
                o.setOrganization(organizationAPI.retrieveLinkedEntity(organizationContactpoint.getOrganizationInstanceId()));
            }

            for (Object personContactpoint : dbaccess.getAllFromDB(PersonContactpoint.class)) {
                if(((PersonContactpoint)personContactpoint).getContactpointInstanceId().equals(o.getInstanceId())) {
                    PersonAPI personAPI = new PersonAPI(EntityNames.PERSON.name(), Person.class);
                    o.setPerson(personAPI.retrieveLinkedEntity(((PersonContactpoint) personContactpoint).getPersonInstanceId()));
                }
            }

            o = (org.epos.eposdatamodel.ContactPoint) VersioningStatusAPI.retrieveVersion(o);

            return o;
        }
        return null;
    }

    @Override
    public List<org.epos.eposdatamodel.ContactPoint> retrieveAll() {
        List<Contactpoint> list = getDbaccess().getAllFromDB(Contactpoint.class);
        List<org.epos.eposdatamodel.ContactPoint> returnList = new ArrayList<>();
        list.parallelStream().forEach(item -> {
            returnList.add(retrieve(item.getInstanceId()));
        });
        return returnList;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        List<Contactpoint> elementList = getDbaccess().getOneFromDBByInstanceId(instanceId, Contactpoint.class);
        if(elementList!=null && !elementList.isEmpty()) {
            Contactpoint edmobj = elementList.get(0);
            LinkedEntity o = new LinkedEntity();
            o.setInstanceId(edmobj.getInstanceId());
            o.setMetaId(edmobj.getMetaId());
            o.setUid(edmobj.getUid());
            o.setEntityType(EntityNames.CONTACTPOINT.name());

            return o;
        }
        return null;
    }

}
