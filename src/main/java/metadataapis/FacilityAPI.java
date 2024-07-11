package metadataapis;

import abstractapis.AbstractAPI;
import commonapis.*;
import model.*;
import org.epos.eposdatamodel.LinkedEntity;
import relationsapi.CategoryRelationsAPI;
import relationsapi.ContactPointRelationsAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FacilityAPI extends AbstractAPI<org.epos.eposdatamodel.Facility> {

    public FacilityAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.Facility obj, StatusType overrideStatus) {

        List<Facility> returnList = getDbaccess().getOneFromDB(
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

        obj = (org.epos.eposdatamodel.Facility) VersioningStatusAPI.checkVersion(obj, overrideStatus);

        EposDataModelEntityIDAPI.addEntityToEDMEntityID(obj.getMetaId(), entityName);

        Facility edmobj = new Facility();

        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());

        getDbaccess().updateObject(edmobj);

        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setType(obj.getType());
        edmobj.setIdentifier(obj.getIdentifier());
        edmobj.setDescription(obj.getDescription());
        edmobj.setTitle(obj.getTitle());
        edmobj.setKeywords(obj.getKeywords());

        /** CATEGORY **/
        if (obj.getCategory() != null && !obj.getCategory().isEmpty())
            CategoryRelationsAPI.createRelation(edmobj,obj, overrideStatus);

        /** CONTACTPOINT **/
        if (obj.getContactPoint() != null && !obj.getContactPoint().isEmpty())
            ContactPointRelationsAPI.createRelation(edmobj,obj, overrideStatus);


        /** ADDRESS **/
        if (obj.getAddress() != null && !obj.getAddress().isEmpty()) {
            List<FacilityAddress> facilityAddressList = getDbaccess().getAllFromDB(FacilityAddress.class);
            for(FacilityAddress item : facilityAddressList){
                if(item.getFacilityInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            AddressAPI addressAPI = new AddressAPI(EntityNames.ADDRESS.name(), Address.class);
            edmobj.setFacilityAddressesByInstanceId(new ArrayList<>());
            for(LinkedEntity address : obj.getAddress()){
                List<Address> list = dbaccess.getOneFromDBByInstanceId(address.getInstanceId(),Address.class);
                Address address1 = null;
                if(list.isEmpty()){
                    LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(address, overrideStatus);
                    address1 = (Address) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Address.class).get(0);
                } else {
                    address1 = list.get(0);
                }
                FacilityAddress pi = new FacilityAddress();
                pi.setFacilityByFacilityInstanceId(edmobj);
                pi.setFacilityInstanceId(edmobj.getInstanceId());
                pi.setAddressInstanceId(address1.getInstanceId());
                pi.setAddressByAddressInstanceId(address1);

                edmobj.getFacilityAddressesByInstanceId().add(pi);

                dbaccess.updateObject(pi);
            }
        }

        /** ISPARTOF **/
        if (obj.getIsPartOf() != null && !obj.getIsPartOf().isEmpty()) {
            List<FacilityIspartof> facilityIspartofList = getDbaccess().getAllFromDB(FacilityIspartof.class);
            for(FacilityIspartof item : facilityIspartofList){
                if(item.getFacility1InstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            edmobj.setFacilityIspartofsByInstanceId(new ArrayList<>());
            for(LinkedEntity facility : obj.getIsPartOf()){
                List<Facility> list = dbaccess.getOneFromDBByInstanceId(facility.getInstanceId(),Facility.class);
                Facility facility1 = null;
                if(list.isEmpty()){
                    LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(facility, overrideStatus);
                    facility1 = (Facility) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Facility.class).get(0);
                } else {
                    facility1 = list.get(0);
                }
                FacilityIspartof pi = new FacilityIspartof();
                pi.setFacilityByFacility1InstanceId(edmobj);
                pi.setFacility1InstanceId(edmobj.getInstanceId());
                pi.setFacility2InstanceId(facility1.getInstanceId());
                pi.setFacilityByFacility2InstanceId(facility1);

                edmobj.getFacilityIspartofsByInstanceId().add(pi);

                dbaccess.updateObject(pi);
            }
        }

        /** SPATIAL **/
        if (obj.getSpatialExtent() != null && !obj.getSpatialExtent().isEmpty()) {
            List<FacilitySpatial> facilitySpatialList = getDbaccess().getAllFromDB(FacilitySpatial.class);
            for(FacilitySpatial item : facilitySpatialList){
                if(item.getFacilityInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            SpatialAPI spatialAPI = new SpatialAPI(EntityNames.LOCATION.name(), Spatial.class);
            edmobj.setFacilitySpatialsByInstanceId(new ArrayList<>());
            for(org.epos.eposdatamodel.LinkedEntity location : obj.getSpatialExtent()){
                List<Spatial> list = dbaccess.getOneFromDBByInstanceId(location.getInstanceId(),Spatial.class);
                Spatial spatial = null;
                if(list.isEmpty()){
                    LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(location, overrideStatus);
                    spatial = (Spatial) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Spatial.class).get(0);
                } else {
                    spatial = list.get(0);
                }
                FacilitySpatial pi = new FacilitySpatial();
                pi.setFacilityByFacilityInstanceId(edmobj);
                pi.setFacilityInstanceId(edmobj.getInstanceId());
                pi.setSpatialInstanceId(spatial.getInstanceId());
                pi.setSpatialBySpatialInstanceId(spatial);

                edmobj.getFacilitySpatialsByInstanceId().add(pi);

                dbaccess.updateObject(pi);
            }
        }

        List<FacilityElement> elementslist = getDbaccess().getAllFromDB(FacilityElement.class);
        edmobj.setFacilityElementsByInstanceId(new ArrayList<>());
        for(FacilityElement item : elementslist){
            if(item.getFacilityInstanceId().equals(obj.getInstanceId())){
                getDbaccess().deleteObject(item);
                List<Element> list2 = getDbaccess().getOneFromDBByInstanceId(item.getElementInstanceId(), Element.class);
                if(list2.size()>0) getDbaccess().deleteObject(list2.get(0));
            }
        }
        /* PAGEURL */
        if(obj.getPageURL()!=null && !obj.getPageURL().isEmpty()){
            for(String pageurl : obj.getPageURL()) {
                createInnerElement(ElementType.PAGEURL, pageurl, edmobj, overrideStatus);
            }
        }

        getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                    .instanceId(edmobj.getInstanceId())
                    .metaId(edmobj.getMetaId())
                    .uid(edmobj.getUid());

    }

    private void createInnerElement(ElementType elementType, String value, Facility edmobj, StatusType overrideStatus){
        org.epos.eposdatamodel.Element element = new org.epos.eposdatamodel.Element();
        element.setType(elementType);
        element.setValue(value);
        ElementAPI api = new ElementAPI(EntityNames.ELEMENT.name(), Element.class);
        LinkedEntity le = api.create(element, overrideStatus);
        List<Element> el = dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Element.class);
        FacilityElement ce = new FacilityElement();
        ce.setFacilityByFacilityInstanceId(edmobj);
        ce.setFacilityInstanceId(edmobj.getInstanceId());
        ce.setElementByElementInstanceId(el.get(0));
        ce.setElementInstanceId(el.get(0).getInstanceId());

        edmobj.getFacilityElementsByInstanceId().add(ce);

        dbaccess.updateObject(ce);
    }


    @Override
    public org.epos.eposdatamodel.Facility retrieve(String instanceId) {
        List<Facility> elementList = getDbaccess().getOneFromDBByInstanceId(instanceId, Facility.class);
        if(elementList!=null && !elementList.isEmpty()) {
            Facility edmobj = elementList.get(0);
            org.epos.eposdatamodel.Facility o = new org.epos.eposdatamodel.Facility();
            o.setInstanceId(edmobj.getInstanceId());
            o.setMetaId(edmobj.getMetaId());
            o.setUid(edmobj.getUid());
            o.setType(edmobj.getType());
            o.setIdentifier(edmobj.getIdentifier());
            o.setDescription(edmobj.getDescription());
            o.setTitle(edmobj.getTitle());
            o.setKeywords(edmobj.getKeywords());

            if (edmobj.getFacilityCategoriesByInstanceId().size() > 0) {
                for (FacilityCategory ed : edmobj.getFacilityCategoriesByInstanceId()) {
                    CategoryAPI api = new CategoryAPI(EntityNames.CATEGORY.name(), Category.class);
                    LinkedEntity cp = api.retrieveLinkedEntity(ed.getCategoryInstanceId());
                    o.addCategory(cp);
                }
            }

            if (edmobj.getFacilityContactpointsByInstanceId().size() > 0) {
                for (FacilityContactpoint ed : edmobj.getFacilityContactpointsByInstanceId()) {
                    ContactPointAPI api = new ContactPointAPI(EntityNames.CONTACTPOINT.name(), Contactpoint.class);
                    LinkedEntity cp = api.retrieveLinkedEntity(ed.getContactpointInstanceId());
                    o.addContactPoint(cp);
                }
            }

            if (edmobj.getFacilityAddressesByInstanceId().size() > 0) {
                for (FacilityAddress ed : edmobj.getFacilityAddressesByInstanceId()) {
                    AddressAPI api = new AddressAPI(EntityNames.ADDRESS.name(), Address.class);
                    LinkedEntity cp = api.retrieveLinkedEntity(ed.getAddressInstanceId());
                    o.addAddress(cp);
                }
            }

            if (edmobj.getFacilityIspartofsByInstanceId().size() > 0) {
                for (FacilityIspartof ed : edmobj.getFacilityIspartofsByInstanceId()) {
                    LinkedEntity cp = retrieveLinkedEntity(ed.getFacility2InstanceId());
                    o.addIsPartOf(cp);
                }
            }

            if (edmobj.getFacilitySpatialsByInstanceId().size() > 0) {
                for (FacilitySpatial ed : edmobj.getFacilitySpatialsByInstanceId()) {
                    SpatialAPI api = new SpatialAPI(EntityNames.LOCATION.name(), Spatial.class);
                    org.epos.eposdatamodel.LinkedEntity cp = api.retrieveLinkedEntity(ed.getSpatialInstanceId());
                    o.addSpatialExtentItem(cp);
                }
            }

            if (edmobj.getFacilityElementsByInstanceId().size() > 0) {
                for (FacilityElement ed : edmobj.getFacilityElementsByInstanceId()) {
                    Element el = ed.getElementByElementInstanceId();
                    if (el.getType().equals(ElementType.PAGEURL)) o.addPageURL(el.getValue());
                }
            }

            o = (org.epos.eposdatamodel.Facility) VersioningStatusAPI.retrieveVersion(o);

            return o;
        }
        return null;
    }

    @Override
    public List<org.epos.eposdatamodel.Facility> retrieveAll() {
        List<Facility> list = getDbaccess().getAllFromDB(Facility.class);
        List<org.epos.eposdatamodel.Facility> returnList = new ArrayList<>();
        list.parallelStream().forEach(item -> {
            returnList.add(retrieve(item.getInstanceId()));
        });
        return returnList;
    }


    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        List<Facility> elementList = getDbaccess().getOneFromDBByInstanceId(instanceId, Facility.class);
        if(elementList!=null && !elementList.isEmpty()) {
            Facility edmobj = elementList.get(0);
            LinkedEntity o = new LinkedEntity();
            o.setInstanceId(edmobj.getInstanceId());
            o.setMetaId(edmobj.getMetaId());
            o.setUid(edmobj.getUid());
            o.setEntityType(EntityNames.FACILITY.name());

            return o;
        }
        return null;
    }

}
