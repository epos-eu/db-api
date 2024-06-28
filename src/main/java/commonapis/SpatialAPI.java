package commonapis;

import abstractapis.AbstractAPI;
import metadataapis.EntityNames;
import model.Element;
import model.QuantitativeValue;
import model.SoftwareapplicationParameters;
import model.Spatial;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SpatialAPI extends AbstractAPI<org.epos.eposdatamodel.Location> {

    public SpatialAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.Location obj) {

        List<Spatial> returnList = getDbaccess().getOneFromDB(
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

        obj = (org.epos.eposdatamodel.Location) VersioningStatusAPI.checkVersion(obj);

        EposDataModelEntityIDAPI.addEntityToEDMEntityID(obj.getMetaId(), entityName);

        Spatial edmobj = new Spatial();
        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setLocation(Optional.ofNullable(obj.getLocation()).orElse(null));

        getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                .instanceId(edmobj.getInstanceId())
                .metaId(edmobj.getMetaId())
                .uid(edmobj.getUid());
    }

    @Override
    public org.epos.eposdatamodel.Location retrieve(String instanceId) {
        List<Spatial> elementList = getDbaccess().getOneFromDBByInstanceId(instanceId, Spatial.class);
        if(elementList!=null && !elementList.isEmpty()) {
            Spatial edmobj = elementList.get(0);
            org.epos.eposdatamodel.Location o = new org.epos.eposdatamodel.Location();

            o.setInstanceId(edmobj.getInstanceId());
            o.setMetaId(edmobj.getMetaId());
            o.setUid(edmobj.getUid());
            o.setLocation(edmobj.getLocation());

            o = (org.epos.eposdatamodel.Location) VersioningStatusAPI.retrieveVersion(o);

            return o;
        }
        return null;
    }

    @Override
    public List<org.epos.eposdatamodel.Location> retrieveAll() {
        List<Spatial> list = getDbaccess().getAllFromDB(Spatial.class);
        List<org.epos.eposdatamodel.Location> returnList = new ArrayList<>();
        for(Spatial item : list){
            returnList.add(retrieve(item.getInstanceId()));
        }
        return returnList;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        List<Spatial> elementList = getDbaccess().getOneFromDBByInstanceId(instanceId, Spatial.class);
        if(elementList!=null && !elementList.isEmpty()) {
            Spatial edmobj = elementList.get(0);
            LinkedEntity o = new LinkedEntity();
            o.setInstanceId(edmobj.getInstanceId());
            o.setMetaId(edmobj.getMetaId());
            o.setUid(edmobj.getUid());
            o.setEntityType(EntityNames.LOCATION.name());

            return o;
        }
        return null;
    }

}
