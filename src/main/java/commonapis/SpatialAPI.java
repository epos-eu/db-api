package commonapis;

import abstractapis.AbstractAPI;
import metadataapis.EntityNames;
import model.Address;
import model.Element;
import model.Spatial;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SpatialAPI extends AbstractAPI<org.epos.eposdatamodel.Location> {

    public SpatialAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.Location obj) {

        List<Element> returnList = getDbaccess().getOneFromDB(
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

        Spatial edmobj = new Spatial();
        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setLocation(Optional.ofNullable(obj.getLocation()).orElse(null));

        if(returnList.isEmpty()) getDbaccess().createObject(edmobj);
        else getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                .instanceId(edmobj.getInstanceId())
                .metaId(edmobj.getMetaId())
                .uid(edmobj.getUid());
    }

    @Override
    public org.epos.eposdatamodel.Location retrieve(String instanceId) {
        Spatial edmobj = (Spatial) getDbaccess().getOneFromDBByInstanceId(instanceId, Spatial.class).get(0);
        org.epos.eposdatamodel.Location o = new org.epos.eposdatamodel.Location();

        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setLocation(edmobj.getLocation());

        return o;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        Spatial edmobj = (Spatial) getDbaccess().getOneFromDBByInstanceId(instanceId, Spatial.class).get(0);

        LinkedEntity o = new LinkedEntity();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setEntityType(EntityNames.SPATIAL.name());

        return o;
    }

}
