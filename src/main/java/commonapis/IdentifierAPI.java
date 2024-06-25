package commonapis;

import abstractapis.AbstractAPI;
import metadataapis.EntityNames;
import model.Address;
import model.Element;
import model.Identifier;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class IdentifierAPI extends AbstractAPI<org.epos.eposdatamodel.Identifier> {

    public IdentifierAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.Identifier obj) {

        List<Identifier> returnList = getDbaccess().getOneFromDB(
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

        obj = (org.epos.eposdatamodel.Identifier) VersioningStatusAPI.checkVersion(obj);

        Identifier edmobj = new Identifier();
        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setType(Optional.ofNullable(obj.getType()).orElse(null));
        edmobj.setValue(Optional.ofNullable(obj.getIdentifier()).orElse(null));

        getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                .instanceId(edmobj.getInstanceId())
                .metaId(edmobj.getMetaId())
                .uid(edmobj.getUid());
    }

    @Override
    public org.epos.eposdatamodel.Identifier retrieve(String instanceId) {
        Identifier edmobj = (Identifier) getDbaccess().getOneFromDBByInstanceId(instanceId, Identifier.class).get(0);
        org.epos.eposdatamodel.Identifier o = new org.epos.eposdatamodel.Identifier();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setType(edmobj.getType());
        o.setIdentifier(edmobj.getValue());

        o = (org.epos.eposdatamodel.Identifier) VersioningStatusAPI.retrieveVersion(o);

        return o;
    }

    @Override
    public List<org.epos.eposdatamodel.Identifier> retrieveAll() {
        List<Identifier> list = getDbaccess().getAllFromDB(Identifier.class);
        List<org.epos.eposdatamodel.Identifier> returnList = new ArrayList<>();
        for(Identifier item : list){
            returnList.add(retrieve(item.getInstanceId()));
        }
        return returnList;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        Identifier edmobj = (Identifier) getDbaccess().getOneFromDBByInstanceId(instanceId, Identifier.class).get(0);

        LinkedEntity o = new LinkedEntity();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setEntityType(EntityNames.IDENTIFIER.name());

        return o;
    }

}
