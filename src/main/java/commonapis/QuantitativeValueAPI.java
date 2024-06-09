package commonapis;

import abstractapis.AbstractAPI;
import metadataapis.EntityNames;
import model.QuantitativeValue;
import model.Temporal;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class QuantitativeValueAPI extends AbstractAPI<org.epos.eposdatamodel.QuantitativeValue> {

    public QuantitativeValueAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.QuantitativeValue obj) {

        List<QuantitativeValue> returnList = getDbaccess().getOneFromDB(
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

        obj = (org.epos.eposdatamodel.QuantitativeValue) VersioningStatusAPI.checkVersion(obj);

        QuantitativeValue edmobj = new QuantitativeValue();
        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setUnicode(obj.getUnit());
        edmobj.setValue(obj.getValue());

        getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                .instanceId(edmobj.getInstanceId())
                .metaId(edmobj.getMetaId())
                .uid(edmobj.getUid());
    }

    @Override
    public org.epos.eposdatamodel.QuantitativeValue retrieve(String instanceId) {
        QuantitativeValue edmobj = (QuantitativeValue) getDbaccess().getOneFromDBByInstanceId(instanceId, Temporal.class).get(0);
        org.epos.eposdatamodel.QuantitativeValue o = new org.epos.eposdatamodel.QuantitativeValue();

        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setUnit(edmobj.getUnicode());
        o.setValue(edmobj.getValue());

        return o;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        QuantitativeValue edmobj = (QuantitativeValue) getDbaccess().getOneFromDBByInstanceId(instanceId, QuantitativeValue.class).get(0);

        LinkedEntity o = new LinkedEntity();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setEntityType(EntityNames.QUANTITATIVEVALUE.name());

        return o;
    }


}
