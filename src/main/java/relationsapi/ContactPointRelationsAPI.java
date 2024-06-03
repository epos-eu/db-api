package relationsapi;

import abstractapis.AbstractRelationsAPI;
import metadataapis.ContactPointAPI;
import metadataapis.EntityNames;
import model.*;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.ArrayList;
import java.util.List;

public class ContactPointRelationsAPI extends AbstractRelationsAPI {

    public static void createRelation(Equipment edmobj, org.epos.eposdatamodel.Equipment obj){
        List<EquipmentContactpoint> equipmentContactpointList = getDbaccess().getAllFromDB(EquipmentContactpoint.class);
        for(EquipmentContactpoint item : equipmentContactpointList){
            if(item.getEquipmentInstanceId().equals(obj.getInstanceId())){
                getDbaccess().deleteObject(item);
            }
        }
        ContactPointAPI contactPointAPI = new ContactPointAPI(EntityNames.CONTACTPOINT.name(), Contactpoint.class);
        edmobj.setEquipmentContactpointsByInstanceId(new ArrayList<>());
        for(org.epos.eposdatamodel.ContactPoint contactPoint : obj.getContactPoint()){
            List<Contactpoint> list = dbaccess.getOneFromDBByInstanceId(contactPoint.getInstanceId(),Contactpoint.class);
            Contactpoint contactPoint1 = null;
            if(list.isEmpty()){
                LinkedEntity le = contactPointAPI.create(contactPoint);
                contactPoint1 = (Contactpoint) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Contactpoint.class).get(0);
            } else {
                contactPoint1 = list.get(0);
            }
            EquipmentContactpoint pi = new EquipmentContactpoint();
            pi.setEquipmentByEquipmentInstanceId(edmobj);
            pi.setEquipmentInstanceId(edmobj.getInstanceId());
            pi.setContactpointInstanceId(contactPoint1.getInstanceId());
            pi.setContactpointByContactpointInstanceId(contactPoint1);

            edmobj.getEquipmentContactpointsByInstanceId().add(pi);

            dbaccess.createObject(pi);
        }
    }

    public static void createRelation(Facility edmobj, org.epos.eposdatamodel.Facility obj) {
        List<FacilityContactpoint> facilityContactpointList = getDbaccess().getAllFromDB(FacilityContactpoint.class);
        for(FacilityContactpoint item : facilityContactpointList){
            if(item.getFacilityInstanceId().equals(obj.getInstanceId())){
                getDbaccess().deleteObject(item);
            }
        }
        ContactPointAPI contactPointAPI = new ContactPointAPI(EntityNames.CONTACTPOINT.name(), Contactpoint.class);
        edmobj.setFacilityContactpointsByInstanceId(new ArrayList<>());
        for(org.epos.eposdatamodel.ContactPoint contactPoint : obj.getContactPoint()){
            List<Contactpoint> list = dbaccess.getOneFromDBByInstanceId(contactPoint.getInstanceId(),Contactpoint.class);
            Contactpoint contactPoint1 = null;
            if(list.isEmpty()){
                LinkedEntity le = contactPointAPI.create(contactPoint);
                contactPoint1 = (Contactpoint) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Contactpoint.class).get(0);
            } else {
                contactPoint1 = list.get(0);
            }
            FacilityContactpoint pi = new FacilityContactpoint();
            pi.setFacilityByFacilityInstanceId(edmobj);
            pi.setFacilityInstanceId(edmobj.getInstanceId());
            pi.setContactpointInstanceId(contactPoint1.getInstanceId());
            pi.setContactpointByContactpointInstanceId(contactPoint1);

            edmobj.getFacilityContactpointsByInstanceId().add(pi);

            dbaccess.createObject(pi);
        }
    }

    public static void createRelation(Dataproduct edmobj, org.epos.eposdatamodel.DataProduct obj) {
        List<DataproductContactpoint> dataproductContactpointList = getDbaccess().getAllFromDB(DataproductContactpoint.class);
        for(DataproductContactpoint item : dataproductContactpointList){
            if(item.getDataproductInstanceId().equals(obj.getInstanceId())){
                getDbaccess().deleteObject(item);
            }
        }
        ContactPointAPI contactPointAPI = new ContactPointAPI(EntityNames.CONTACTPOINT.name(), Contactpoint.class);
        edmobj.setDataproductContactpointsByInstanceId(new ArrayList<>());
        for(org.epos.eposdatamodel.ContactPoint contactPoint : obj.getContactPoint()){
            List<Contactpoint> list = dbaccess.getOneFromDBByInstanceId(contactPoint.getInstanceId(),Contactpoint.class);
            Contactpoint contactPoint1 = null;
            if(list.isEmpty()){
                LinkedEntity le = contactPointAPI.create(contactPoint);
                contactPoint1 = (Contactpoint) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Contactpoint.class).get(0);
            } else {
                contactPoint1 = list.get(0);
            }
            DataproductContactpoint pi = new DataproductContactpoint();
            pi.setDataproductByDataproductInstanceId(edmobj);
            pi.setDataproductInstanceId(edmobj.getInstanceId());
            pi.setContactpointInstanceId(contactPoint1.getInstanceId());
            pi.setContactpointByContactpointInstanceId(contactPoint1);

            edmobj.getDataproductContactpointsByInstanceId().add(pi);

            dbaccess.createObject(pi);
        }
    }

    public static void createRelation(Webservice edmobj, org.epos.eposdatamodel.WebService obj){
        List<WebserviceContactpoint> webserviceContactpointList = getDbaccess().getAllFromDB(WebserviceContactpoint.class);
        for(WebserviceContactpoint item : webserviceContactpointList){
            if(item.getWebserviceInstanceId().equals(obj.getInstanceId())){
                getDbaccess().deleteObject(item);
            }
        }
        ContactPointAPI contactPointAPI = new ContactPointAPI(EntityNames.CONTACTPOINT.name(), Contactpoint.class);
        edmobj.setWebserviceContactpointsByInstanceId(new ArrayList<>());
        for(org.epos.eposdatamodel.ContactPoint contactPoint : obj.getContactPoint()){
            List<Contactpoint> list = dbaccess.getOneFromDBByInstanceId(contactPoint.getInstanceId(),Contactpoint.class);
            Contactpoint contactPoint1 = null;
            if(list.isEmpty()){
                LinkedEntity le = contactPointAPI.create(contactPoint);
                contactPoint1 = (Contactpoint) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Contactpoint.class).get(0);
            } else {
                contactPoint1 = list.get(0);
            }
            WebserviceContactpoint pi = new WebserviceContactpoint();
            pi.setWebserviceByWebserviceInstanceId(edmobj);
            pi.setWebserviceInstanceId(edmobj.getInstanceId());
            pi.setContactpointInstanceId(contactPoint1.getInstanceId());
            pi.setContactpointByContactpointInstanceId(contactPoint1);

            edmobj.getWebserviceContactpointsByInstanceId().add(pi);

            dbaccess.createObject(pi);
        }
    }

    public static void createRelation(SoftwareSourceCode edmobj, org.epos.eposdatamodel.SoftwareSourceCode obj){
        List<SoftwaresourcecodeContactpoint> softwaresourcecodeContactpointList = getDbaccess().getAllFromDB(SoftwaresourcecodeContactpoint.class);
        for(SoftwaresourcecodeContactpoint item : softwaresourcecodeContactpointList){
            if(item.getSoftwaresourcecodeInstanceId().equals(obj.getInstanceId())){
                getDbaccess().deleteObject(item);
            }
        }
        ContactPointAPI contactPointAPI = new ContactPointAPI(EntityNames.CONTACTPOINT.name(), Contactpoint.class);
        edmobj.setSoftwaresourcecodeContactpointsByInstanceId(new ArrayList<>());
        for(org.epos.eposdatamodel.ContactPoint contactPoint : obj.getContactPoint()){
            List<Contactpoint> list = dbaccess.getOneFromDBByInstanceId(contactPoint.getInstanceId(),Contactpoint.class);
            Contactpoint contactPoint1 = null;
            if(list.isEmpty()){
                LinkedEntity le = contactPointAPI.create(contactPoint);
                contactPoint1 = (Contactpoint) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Contactpoint.class).get(0);
            } else {
                contactPoint1 = list.get(0);
            }
            SoftwaresourcecodeContactpoint pi = new SoftwaresourcecodeContactpoint();
            pi.setSoftwaresourcecodeBySoftwaresourcecodeInstanceId(edmobj);
            pi.setSoftwaresourcecodeInstanceId(edmobj.getInstanceId());
            pi.setContactpointInstanceId(contactPoint1.getInstanceId());
            pi.setContactpointByContactpointInstanceId(contactPoint1);

            edmobj.getSoftwaresourcecodeContactpointsByInstanceId().add(pi);

            dbaccess.createObject(pi);
        }
    }

    public static void createRelation(SoftwareApplication edmobj, org.epos.eposdatamodel.SoftwareApplication obj){
        List<SoftwareapplicationContactpoint> softwaresourcecodeContactpointList = getDbaccess().getAllFromDB(SoftwareapplicationContactpoint.class);
        for(SoftwareapplicationContactpoint item : softwaresourcecodeContactpointList){
            if(item.getSoftwareapplicationInstanceId().equals(obj.getInstanceId())){
                getDbaccess().deleteObject(item);
            }
        }
        ContactPointAPI contactPointAPI = new ContactPointAPI(EntityNames.CONTACTPOINT.name(), Contactpoint.class);
        edmobj.setSoftwareapplicationContactpointsByInstanceId(new ArrayList<>());
        for(org.epos.eposdatamodel.ContactPoint contactPoint : obj.getContactPoint()){
            List<Contactpoint> list = dbaccess.getOneFromDBByInstanceId(contactPoint.getInstanceId(),Contactpoint.class);
            Contactpoint contactPoint1 = null;
            if(list.isEmpty()){
                LinkedEntity le = contactPointAPI.create(contactPoint);
                contactPoint1 = (Contactpoint) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Contactpoint.class).get(0);
            } else {
                contactPoint1 = list.get(0);
            }
            SoftwareapplicationContactpoint pi = new SoftwareapplicationContactpoint();
            pi.setSoftwareapplicationBySoftwareapplicationInstanceId(edmobj);
            pi.setSoftwareapplicationInstanceId(edmobj.getInstanceId());
            pi.setContactpointInstanceId(contactPoint1.getInstanceId());
            pi.setContactpointByContactpointInstanceId(contactPoint1);

            edmobj.getSoftwareapplicationContactpointsByInstanceId().add(pi);

            dbaccess.createObject(pi);
        }
    }
}
