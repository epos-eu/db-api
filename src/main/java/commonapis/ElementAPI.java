package commonapis;

import abstractapis.AbstractAPI;
import metadataapis.EntityNames;
import model.Address;
import model.Element;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ElementAPI extends AbstractAPI<org.epos.eposdatamodel.Element> {

    public ElementAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.Element obj) {

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

        obj = (org.epos.eposdatamodel.Element) VersioningStatusAPI.checkVersion(obj);

        Element edmobj = new Element();
        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setType(Optional.ofNullable(obj.getType()).orElse(null));
        edmobj.setValue(Optional.ofNullable(obj.getValue()).orElse(null));

        if(returnList.isEmpty()) getDbaccess().createObject(edmobj);
        else getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                .instanceId(edmobj.getInstanceId())
                .metaId(edmobj.getMetaId())
                .uid(edmobj.getUid());
    }

    @Override
    public org.epos.eposdatamodel.Element retrieve(String instanceId) {
        Element edmobj = (Element) getDbaccess().getOneFromDBByInstanceId(instanceId, Element.class).get(0);
        org.epos.eposdatamodel.Element o = new org.epos.eposdatamodel.Element();


        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setType(edmobj.getType());
        o.setValue(edmobj.getValue());

        return o;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        Address edmobj = (Address) getDbaccess().getOneFromDBByInstanceId(instanceId, Address.class).get(0);

        LinkedEntity o = new LinkedEntity();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setEntityType(EntityNames.ELEMENT.name());

        return o;
    }


}
