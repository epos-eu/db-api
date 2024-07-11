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
import java.util.stream.Collectors;

public class EquipmentAPI extends AbstractAPI<org.epos.eposdatamodel.Equipment> {

    public EquipmentAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.Equipment obj, StatusType overrideStatus) {

        List<Equipment> returnList = getDbaccess().getOneFromDB(
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

        obj = (org.epos.eposdatamodel.Equipment) VersioningStatusAPI.checkVersion(obj, overrideStatus);

        EposDataModelEntityIDAPI.addEntityToEDMEntityID(obj.getMetaId(), entityName);

        Equipment edmobj = new Equipment();

        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());

        getDbaccess().updateObject(edmobj);

        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setType(obj.getType());
        edmobj.setIdentifier(obj.getIdentifier());
        edmobj.setDescription(obj.getDescription());
        edmobj.setName(obj.getName());
        edmobj.setKeywords(obj.getKeywords());
        edmobj.setDynamicrange(obj.getDynamicRange());
        edmobj.setFilter(obj.getFilter());
        edmobj.setOrientation(obj.getOrientation());
        edmobj.setResolution(obj.getResolution());
        edmobj.setSampleperiod(obj.getSamplePeriod());
        edmobj.setSerialnumber(obj.getSerialNumber());

        /** TODO: creator **/

        /** CATEGORY **/
        if (obj.getCategory() != null && !obj.getCategory().isEmpty())
            CategoryRelationsAPI.createRelation(edmobj,obj, overrideStatus);

        /** CONTACTPOINT **/
        if (obj.getContactPoint() != null && !obj.getContactPoint().isEmpty())
            ContactPointRelationsAPI.createRelation(edmobj,obj, overrideStatus);

        /** ISPARTOF EQUIPMENT **/
        if (obj.getIsPartOf() != null && !obj.getIsPartOf().isEmpty()) {
            List<EquipmentIspartof> equipmentIspartofList = getDbaccess().getAllFromDB(EquipmentIspartof.class);
            equipmentIspartofList = equipmentIspartofList.stream().filter(item -> item.getResourceEntity().equals(EntityNames.EQUIPMENT.name())).collect(Collectors.toList());
            for(EquipmentIspartof item : equipmentIspartofList){
                if(item.getEquipmentInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            for(LinkedEntity equipment : obj.getIsPartOf()){
                List<Equipment> list = dbaccess.getOneFromDBByInstanceId(equipment.getInstanceId(),Equipment.class);
                Equipment equipment1 = null;
                if(list.isEmpty()){
                    LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(equipment, overrideStatus);
                    List<Equipment> list1 = dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Equipment.class);
                    if(list1.size()>0) equipment1 = list1.get(0);
                } else {
                    equipment1 = list.get(0);
                }
                if(equipment1!=null) {
                    EquipmentIspartof pi = new EquipmentIspartof();
                    pi.setEquipmentByEquipmentInstanceId(edmobj);
                    pi.setEquipmentInstanceId(edmobj.getInstanceId());
                    pi.setEntityInstanceId(equipment1.getInstanceId());
                    pi.setResourceEntity(EntityNames.EQUIPMENT.name());
                    edmobj.setEquipmentIspartofByInstanceId(pi);
                    dbaccess.updateObject(pi);
                }
            }
        }

        /** ISPARTOF FACILITY **/
        if (obj.getIsPartOf() != null && !obj.getIsPartOf().isEmpty()) {
            List<EquipmentIspartof> equipmentIspartofList = getDbaccess().getAllFromDB(EquipmentIspartof.class);
            equipmentIspartofList = equipmentIspartofList.stream().filter(item -> item.getResourceEntity().equals(EntityNames.FACILITY.name())).collect(Collectors.toList());
            for(EquipmentIspartof item : equipmentIspartofList){
                if(item.getEquipmentInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            for(LinkedEntity facility : obj.getIsPartOf()){
                List<Facility> list = dbaccess.getOneFromDBByInstanceId(facility.getInstanceId(),Facility.class);
                Facility facility1 = null;
                if(list.isEmpty()){
                    LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(facility, overrideStatus);
                    List<Facility> list1 = dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Facility.class);
                    facility1 = list1.size()>0? list1.get(0) : null;
                } else {
                    facility1 = list.get(0);
                }
                if(facility1!=null) {
                    EquipmentIspartof pi = new EquipmentIspartof();
                    pi.setEquipmentByEquipmentInstanceId(edmobj);
                    pi.setEquipmentInstanceId(edmobj.getInstanceId());
                    pi.setEntityInstanceId(facility1.getInstanceId());
                    pi.setResourceEntity(EntityNames.FACILITY.name());
                    edmobj.setEquipmentIspartofByInstanceId(pi);
                    dbaccess.updateObject(pi);
                }
            }
        }

        /** SPATIAL **/
        if (obj.getSpatialExtent() != null && !obj.getSpatialExtent().isEmpty()) {
            List<EquipmentSpatial> equipmentSpatialList = getDbaccess().getAllFromDB(EquipmentSpatial.class);
            for(EquipmentSpatial item : equipmentSpatialList){
                if(item.getEquipmentInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            SpatialAPI spatialAPI = new SpatialAPI(EntityNames.LOCATION.name(), Spatial.class);
            edmobj.setEquipmentSpatialsByInstanceId(new ArrayList<>());
            for(org.epos.eposdatamodel.LinkedEntity location : obj.getSpatialExtent()){
                List<Spatial> list = dbaccess.getOneFromDBByInstanceId(location.getInstanceId(),Spatial.class);
                Spatial spatial = null;
                if(list.isEmpty()){
                    LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(location, overrideStatus);
                    spatial = (Spatial) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Spatial.class).get(0);
                } else {
                    spatial = list.get(0);
                }
                EquipmentSpatial pi = new EquipmentSpatial();
                pi.setEquipmentByEquipmentInstanceId(edmobj);
                pi.setEquipmentInstanceId(edmobj.getInstanceId());
                pi.setSpatialInstanceId(spatial.getInstanceId());
                pi.setSpatialBySpatialInstanceId(spatial);

                edmobj.getEquipmentSpatialsByInstanceId().add(pi);
                dbaccess.updateObject(pi);
            }
        }

        /** TEMPORAL **/
        if (obj.getTemporalExtent() != null && !obj.getTemporalExtent().isEmpty()) {
            List<EquipmentTemporal> equipmentTemporalList = getDbaccess().getAllFromDB(EquipmentTemporal.class);
            for(EquipmentTemporal item : equipmentTemporalList){
                if(item.getEquipmentInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            TemporalAPI temporalAPI = new TemporalAPI(EntityNames.PERIODOFTIME.name(), Temporal.class);
            edmobj.setEquipmentTemporalsByInstanceId(new ArrayList<>());
            for(org.epos.eposdatamodel.LinkedEntity periodOfTime : obj.getTemporalExtent()){
                List<Temporal> list = dbaccess.getOneFromDBByInstanceId(periodOfTime.getInstanceId(),Temporal.class);
                Temporal temporal = null;
                if(list.isEmpty()){
                    LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(periodOfTime, overrideStatus);
                    temporal = (Temporal) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Temporal.class).get(0);
                } else {
                    temporal = list.get(0);
                }
                EquipmentTemporal pi = new EquipmentTemporal();
                pi.setEquipmentByEquipmentInstanceId(edmobj);
                pi.setEquipmentInstanceId(edmobj.getInstanceId());
                pi.setTemporalInstanceId(temporal.getInstanceId());
                pi.setTemporalByTemporalInstanceId(temporal);

                edmobj.getEquipmentTemporalsByInstanceId().add(pi);
                dbaccess.updateObject(pi);
            }
        }

        List<EquipmentElement> elementslist = getDbaccess().getAllFromDB(EquipmentElement.class);
        edmobj.setEquipmentElementsByInstanceId(new ArrayList<>());

        for(EquipmentElement item : elementslist){
            if(item.getEquipmentInstanceId().equals(obj.getInstanceId())){
                getDbaccess().deleteObject(item);
                List<Element> list2 = getDbaccess().getOneFromDBByInstanceId(item.getElementInstanceId(), Element.class);
                if(list2.size()>0) getDbaccess().deleteObject(list2.get(0));
            }
        }
        /* PAGEURL */
        if(obj.getPageURL()!=null && !obj.getPageURL().isEmpty()){
            createInnerElement(ElementType.PAGEURL, obj.getPageURL(), edmobj, overrideStatus);
        }

        getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                    .instanceId(edmobj.getInstanceId())
                    .metaId(edmobj.getMetaId())
                    .uid(edmobj.getUid());

    }

    private void createInnerElement(ElementType elementType, String value, Equipment edmobj, StatusType overrideStatus){
        org.epos.eposdatamodel.Element element = new org.epos.eposdatamodel.Element();
        element.setType(elementType);
        element.setValue(value);
        ElementAPI api = new ElementAPI(EntityNames.ELEMENT.name(), Element.class);
        LinkedEntity le = api.create(element, overrideStatus);
        List<Element> el = dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Element.class);
        EquipmentElement ce = new EquipmentElement();
        ce.setEquipmentByEquipmentInstanceId(edmobj);
        ce.setEquipmentInstanceId(edmobj.getInstanceId());
        ce.setElementByElementInstanceId(el.get(0));
        ce.setElementInstanceId(el.get(0).getInstanceId());

        edmobj.getEquipmentElementsByInstanceId().add(ce);

        dbaccess.updateObject(ce);
    }


    @Override
    public org.epos.eposdatamodel.Equipment retrieve(String instanceId) {
        List<Equipment> elementList = getDbaccess().getOneFromDBByInstanceId(instanceId, Equipment.class);
        if(elementList!=null && !elementList.isEmpty()) {
            Equipment edmobj = elementList.get(0);
            org.epos.eposdatamodel.Equipment o = new org.epos.eposdatamodel.Equipment();
            o.setInstanceId(edmobj.getInstanceId());
            o.setMetaId(edmobj.getMetaId());
            o.setUid(edmobj.getUid());
            o.setType(edmobj.getType());
            o.setResolution(edmobj.getResolution());
            o.setDescription(edmobj.getDescription());
            o.setDynamicRange(edmobj.getDynamicrange());
            o.setFilter(edmobj.getFilter());
            o.setIdentifier(edmobj.getIdentifier());
            o.setName(edmobj.getName());
            o.setPageURL(edmobj.getPageurl());
            o.setOrientation(edmobj.getOrientation());
            o.setSamplePeriod(edmobj.getSampleperiod());
            o.setSerialNumber(edmobj.getSerialnumber());
            o.addKeywords(edmobj.getKeywords());

            if (edmobj.getEquipmentCategoriesByInstanceId().size() > 0) {
                for (EquipmentCategory ed : edmobj.getEquipmentCategoriesByInstanceId()) {
                    CategoryAPI api = new CategoryAPI(EntityNames.CATEGORY.name(), Category.class);
                    LinkedEntity cp = api.retrieveLinkedEntity(ed.getCategoryInstanceId());
                    o.addCategory(cp);
                }
            }

            if (edmobj.getEquipmentContactpointsByInstanceId().size() > 0) {
                for (EquipmentContactpoint ed : edmobj.getEquipmentContactpointsByInstanceId()) {
                    ContactPointAPI api = new ContactPointAPI(EntityNames.CONTACTPOINT.name(), Contactpoint.class);
                    LinkedEntity cp = api.retrieveLinkedEntity(ed.getContactpointInstanceId());
                    o.addContactPoint(cp);
                }
            }

            List<EquipmentIspartof> equipmentIspartofList = dbaccess.getOneFromDBByInstanceId(edmobj.getInstanceId(), EquipmentIspartof.class);
            if (equipmentIspartofList.size() > 0) {
                for (EquipmentIspartof ed : equipmentIspartofList) {
                    if (ed.getResourceEntity().equals(EntityNames.FACILITY.name())) {
                        FacilityAPI api = new FacilityAPI(EntityNames.FACILITY.name(), Facility.class);
                        o.addIsPartOf(api.retrieveLinkedEntity(ed.getEntityInstanceId()));
                    }
                    if (ed.getResourceEntity().equals(EntityNames.EQUIPMENT.name())) {
                        EquipmentAPI api = new EquipmentAPI(EntityNames.EQUIPMENT.name(), Equipment.class);
                        o.addIsPartOf(api.retrieveLinkedEntity(ed.getEntityInstanceId()));
                    }
                }
            }

            if (edmobj.getEquipmentSpatialsByInstanceId().size() > 0) {
                for (EquipmentSpatial ed : edmobj.getEquipmentSpatialsByInstanceId()) {
                    SpatialAPI api = new SpatialAPI(EntityNames.LOCATION.name(), Spatial.class);
                    org.epos.eposdatamodel.LinkedEntity cp = api.retrieveLinkedEntity(ed.getSpatialInstanceId());
                    o.addSpatialExtentItem(cp);
                }
            }

            if (edmobj.getEquipmentTemporalsByInstanceId().size() > 0) {
                for (EquipmentTemporal ed : edmobj.getEquipmentTemporalsByInstanceId()) {
                    TemporalAPI api = new TemporalAPI(EntityNames.PERIODOFTIME.name(), Temporal.class);
                    org.epos.eposdatamodel.LinkedEntity cp = api.retrieveLinkedEntity(ed.getTemporalInstanceId());
                    o.addTemporalExtent(cp);
                }
            }

            if (edmobj.getEquipmentElementsByInstanceId().size() > 0) {
                for (EquipmentElement ed : edmobj.getEquipmentElementsByInstanceId()) {
                    Element el = ed.getElementByElementInstanceId();
                    if (el.getType().equals(ElementType.PAGEURL)) o.setPageURL(el.getValue());
                }
            }

            o = (org.epos.eposdatamodel.Equipment) VersioningStatusAPI.retrieveVersion(o);

            return o;
        }
        return null;
    }

    @Override
    public List<org.epos.eposdatamodel.Equipment> retrieveAll() {
        List<Equipment> list = getDbaccess().getAllFromDB(Equipment.class);
        List<org.epos.eposdatamodel.Equipment> returnList = new ArrayList<>();
        list.parallelStream().forEach(item -> {
            returnList.add(retrieve(item.getInstanceId()));
        });
        return returnList;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        List<Equipment> elementList = getDbaccess().getOneFromDBByInstanceId(instanceId, Equipment.class);
        if(elementList!=null && !elementList.isEmpty()) {
            Equipment edmobj = elementList.get(0);
            LinkedEntity o = new LinkedEntity();
            o.setInstanceId(edmobj.getInstanceId());
            o.setMetaId(edmobj.getMetaId());
            o.setUid(edmobj.getUid());
            o.setEntityType(EntityNames.EQUIPMENT.name());

            return o;
        }
        return null;
    }

}
