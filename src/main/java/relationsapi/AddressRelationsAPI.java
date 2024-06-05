package relationsapi;

import abstractapis.AbstractRelationsAPI;
import commonapis.LinkedEntityAPI;
import metadataapis.ContactPointAPI;
import model.*;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.List;

public class AddressRelationsAPI extends AbstractRelationsAPI {

    public static void createRelation(Equipment edmobj, org.epos.eposdatamodel.Equipment obj){
        List<EquipmentContactpoint> equipmentContactpointList = getDbaccess().getAllFromDB(EquipmentContactpoint.class);
        for(EquipmentContactpoint item : equipmentContactpointList){
            if(item.getEquipmentInstanceId().equals(obj.getInstanceId())){
                getDbaccess().deleteObject(item);
            }
        }
        ContactPointAPI contactPointAPI = new ContactPointAPI("ContactPoint", Contactpoint.class);
        for(LinkedEntity contactPoint : obj.getContactPoint()){
            List<Contactpoint> list = dbaccess.getOneFromDBByInstanceId(contactPoint.getInstanceId(),Contactpoint.class);
            Contactpoint contactPoint1 = null;
            if(list.isEmpty()){
                LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(contactPoint);
                contactPoint1 = (Contactpoint) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Contactpoint.class).get(0);
            } else {
                contactPoint1 = list.get(0);
            }
            EquipmentContactpoint pi = new EquipmentContactpoint();
            pi.setEquipmentByEquipmentInstanceId(edmobj);
            pi.setEquipmentInstanceId(edmobj.getInstanceId());
            pi.setContactpointInstanceId(contactPoint1.getInstanceId());
            pi.setContactpointByContactpointInstanceId(contactPoint1);
        }
    }

    public static void createRelation(Facility edmobj, org.epos.eposdatamodel.Facility obj) {
        List<FacilityContactpoint> facilityContactpointList = getDbaccess().getAllFromDB(FacilityContactpoint.class);
        for(FacilityContactpoint item : facilityContactpointList){
            if(item.getFacilityInstanceId().equals(obj.getInstanceId())){
                getDbaccess().deleteObject(item);
            }
        }
        ContactPointAPI contactPointAPI = new ContactPointAPI("ContactPoint", Contactpoint.class);
        for(LinkedEntity contactPoint : obj.getContactPoint()){
            List<Contactpoint> list = dbaccess.getOneFromDBByInstanceId(contactPoint.getInstanceId(),Contactpoint.class);
            Contactpoint contactPoint1 = null;
            if(list.isEmpty()){
                LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(contactPoint);
                contactPoint1 = (Contactpoint) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Contactpoint.class).get(0);
            } else {
                contactPoint1 = list.get(0);
            }
            FacilityContactpoint pi = new FacilityContactpoint();
            pi.setFacilityByFacilityInstanceId(edmobj);
            pi.setFacilityInstanceId(edmobj.getInstanceId());
            pi.setContactpointInstanceId(contactPoint1.getInstanceId());
            pi.setContactpointByContactpointInstanceId(contactPoint1);
        }
    }
}
