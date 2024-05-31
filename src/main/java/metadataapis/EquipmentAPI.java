package metadataapis;

import abstractapis.AbstractAPI;
import commonapis.*;
import model.*;
import org.epos.eposdatamodel.ContactPoint;
import org.epos.eposdatamodel.LinkedEntity;
import relationsapi.CategoryRelationsAPI;
import relationsapi.ContactPointRelationsAPI;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class EquipmentAPI extends AbstractAPI<org.epos.eposdatamodel.Equipment> {

    public EquipmentAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.Equipment obj) {

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

        obj = (org.epos.eposdatamodel.Equipment) VersioningStatusAPI.checkVersion(obj);

        Equipment edmobj = new Equipment();

        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
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

        if(returnList.isEmpty()) getDbaccess().createObject(edmobj);
        else getDbaccess().updateObject(edmobj);

        /** CATEGORY **/
        if (obj.getCategory() != null && !obj.getCategory().isEmpty())
            CategoryRelationsAPI.createRelation(edmobj,obj);

        /** CONTACTPOINT **/
        if (obj.getContactPoint() != null && !obj.getContactPoint().isEmpty())
            ContactPointRelationsAPI.createRelation(edmobj,obj);

        /** ISPARTOF EQUIPMENT **/
        if (obj.getIsPartOfEquipment() != null && !obj.getIsPartOfEquipment().isEmpty()) {
            List<EquipmentIspartof> equipmentIspartofList = getDbaccess().getAllFromDB(EquipmentIspartof.class);
            equipmentIspartofList = equipmentIspartofList.stream().filter(item -> item.getResourceEntity().equals("Equipment")).collect(Collectors.toList());
            for(EquipmentIspartof item : equipmentIspartofList){
                if(item.getEquipmentInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            for(org.epos.eposdatamodel.Equipment equipment : obj.getIsPartOfEquipment()){
                List<Equipment> list = dbaccess.getOneFromDBByInstanceId(equipment.getInstanceId(),Equipment.class);
                Equipment equipment1 = null;
                if(list.isEmpty()){
                    LinkedEntity le = create(equipment);
                    equipment1 = (Equipment) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Equipment.class).get(0);
                } else {
                    equipment1 = list.get(0);
                }
                EquipmentIspartof pi = new EquipmentIspartof();
                pi.setEquipmentByEquipmentInstanceId(edmobj);
                pi.setEquipmentInstanceId(edmobj.getInstanceId());
                pi.setEntityInstanceId(equipment1.getInstanceId());
                pi.setResourceEntity("Equipment");
            }
        }

        /** ISPARTOF FACILITY **/
        if (obj.getIsPartOfFacility() != null && !obj.getIsPartOfFacility().isEmpty()) {
            List<EquipmentIspartof> equipmentIspartofList = getDbaccess().getAllFromDB(EquipmentIspartof.class);
            equipmentIspartofList = equipmentIspartofList.stream().filter(item -> item.getResourceEntity().equals("Facility")).collect(Collectors.toList());
            for(EquipmentIspartof item : equipmentIspartofList){
                if(item.getEquipmentInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            FacilityAPI facilityAPI = new FacilityAPI("Facility", Facility.class);
            for(org.epos.eposdatamodel.Facility facility : obj.getIsPartOfFacility()){
                List<Facility> list = dbaccess.getOneFromDBByInstanceId(facility.getInstanceId(),Facility.class);
                Facility facility1 = null;
                if(list.isEmpty()){
                    LinkedEntity le = facilityAPI.create(facility);
                    facility1 = (Facility) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Equipment.class).get(0);
                } else {
                    facility1 = list.get(0);
                }
                EquipmentIspartof pi = new EquipmentIspartof();
                pi.setEquipmentByEquipmentInstanceId(edmobj);
                pi.setEquipmentInstanceId(edmobj.getInstanceId());
                pi.setEntityInstanceId(facility1.getInstanceId());
                pi.setResourceEntity("Facility");
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
            SpatialAPI spatialAPI = new SpatialAPI("Spatial", Spatial.class);
            for(org.epos.eposdatamodel.Location location : obj.getSpatialExtent()){
                List<Spatial> list = dbaccess.getOneFromDBByInstanceId(location.getInstanceId(),Spatial.class);
                Spatial spatial = null;
                if(list.isEmpty()){
                    LinkedEntity le = spatialAPI.create(location);
                    spatial = (Spatial) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Spatial.class).get(0);
                } else {
                    spatial = list.get(0);
                }
                EquipmentSpatial pi = new EquipmentSpatial();
                pi.setEquipmentByEquipmentInstanceId(edmobj);
                pi.setEquipmentInstanceId(edmobj.getInstanceId());
                pi.setSpatialInstanceId(spatial.getInstanceId());
                pi.setSpatialBySpatialInstanceId(spatial);
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
            TemporalAPI temporalAPI = new TemporalAPI("Temporal", Temporal.class);
            for(org.epos.eposdatamodel.PeriodOfTime periodOfTime : obj.getTemporalExtent()){
                List<Temporal> list = dbaccess.getOneFromDBByInstanceId(periodOfTime.getInstanceId(),Temporal.class);
                Temporal temporal = null;
                if(list.isEmpty()){
                    LinkedEntity le = temporalAPI.create(periodOfTime);
                    temporal = (Temporal) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Temporal.class).get(0);
                } else {
                    temporal = list.get(0);
                }
                EquipmentTemporal pi = new EquipmentTemporal();
                pi.setEquipmentByEquipmentInstanceId(edmobj);
                pi.setEquipmentInstanceId(edmobj.getInstanceId());
                pi.setTemporalInstanceId(temporal.getInstanceId());
                pi.setTemporalByTemporalInstanceId(temporal);
            }
        }

        List<EquipmentElement> elementslist = getDbaccess().getAllFromDB(EquipmentElement.class);
        for(EquipmentElement item : elementslist){
            if(item.getEquipmentInstanceId().equals(obj.getInstanceId())){
                getDbaccess().deleteObject(item);
                List<Element> list2 = getDbaccess().getOneFromDBByInstanceId(item.getElementInstanceId(), Element.class);
                getDbaccess().deleteObject(list2.get(0));
            }
        }
        /* PAGEURL */
        if(!obj.getPageURL().isEmpty()){
            createInnerElement(ElementType.PAGEURL, obj.getPageURL(), edmobj);
        }

        return new LinkedEntity().entityType(entityName)
                    .instanceId(edmobj.getInstanceId())
                    .metaId(edmobj.getMetaId())
                    .uid(edmobj.getUid());

    }

    private void createInnerElement(ElementType elementType, String value, Equipment edmobj){
        org.epos.eposdatamodel.Element element = new org.epos.eposdatamodel.Element();
        element.setType(elementType);
        element.setValue(value);
        ElementAPI api = new ElementAPI("Element", Element.class);
        LinkedEntity le = api.create(element);
        List<Element> el = dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Element.class);
        EquipmentElement ce = new EquipmentElement();
        ce.setEquipmentByEquipmentInstanceId(edmobj);
        ce.setEquipmentInstanceId(edmobj.getInstanceId());
        ce.setElementByElementInstanceId(el.get(0));
        ce.setElementInstanceId(el.get(0).getInstanceId());
    }


    @Override
    public org.epos.eposdatamodel.Equipment retrieve(String instanceId) {
        Equipment edmobj = (Equipment) getDbaccess().getOneFromDBByInstanceId(instanceId, Equipment.class).get(0);

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

        if(edmobj.getEquipmentCategoriesByInstanceId().size()>0) {
            for(EquipmentCategory ed : edmobj.getEquipmentCategoriesByInstanceId()) {
                CategoryAPI api = new CategoryAPI("Category", Category.class);
                org.epos.eposdatamodel.Category cp = api.retrieve(ed.getCategoryInstanceId());
                o.addCategory(cp);
            }
        }

        if(edmobj.getEquipmentContactpointsByInstanceId().size()>0) {
            for(EquipmentContactpoint ed : edmobj.getEquipmentContactpointsByInstanceId()) {
                ContactPointAPI api = new ContactPointAPI("ContactPoint", Contactpoint.class);
                ContactPoint cp = api.retrieve(ed.getContactpointInstanceId());
                o.addContactPoint(cp);
            }
        }

        List<EquipmentIspartof> equipmentIspartofList =dbaccess.getOneFromDBByInstanceId(edmobj.getInstanceId(),EquipmentIspartof.class);
        if(equipmentIspartofList.size()>0) {
            for(EquipmentIspartof ed : equipmentIspartofList) {
                if(ed.getResourceEntity().equals("Facility")){
                    FacilityAPI api = new FacilityAPI("Facility", Facility.class);
                    o.addIsPartOfFacility(api.retrieve(ed.getEntityInstanceId()));
                }
                if(ed.getResourceEntity().equals("Equipment")){
                    EquipmentAPI api = new EquipmentAPI("Equipment", Equipment.class);
                    o.addIsPartOfEquipment(api.retrieve(ed.getEntityInstanceId()));
                }
            }
        }

        if(edmobj.getEquipmentSpatialsByInstanceId().size()>0) {
            for(EquipmentSpatial ed : edmobj.getEquipmentSpatialsByInstanceId()) {
                SpatialAPI api = new SpatialAPI("Spatial", Spatial.class);
                org.epos.eposdatamodel.Location cp = api.retrieve(ed.getSpatialInstanceId());
                o.addSpatialExtentItem(cp);
            }
        }

        if(edmobj.getEquipmentTemporalsByInstanceId().size()>0) {
            for(EquipmentTemporal ed : edmobj.getEquipmentTemporalsByInstanceId()) {
                TemporalAPI api = new TemporalAPI("Temporal", Temporal.class);
                org.epos.eposdatamodel.PeriodOfTime cp = api.retrieve(ed.getTemporalInstanceId());
                o.addTemporalExtent(cp);
            }
        }

        if(edmobj.getEquipmentElementsByInstanceId().size()>0) {
            for(EquipmentElement ed : edmobj.getEquipmentElementsByInstanceId()) {
                Element el = ed.getElementByElementInstanceId();
                if(el.getType().equals(ElementType.PAGEURL)) o.setPageURL(el.getValue());
            }
        }

        return o;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        Equipment edmobj = (Equipment) getDbaccess().getOneFromDBByInstanceId(instanceId, Equipment.class).get(0);

        LinkedEntity o = new LinkedEntity();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setEntityType(EntityNames.EQUIPMENT.name());

        return o;
    }

}
