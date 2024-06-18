package commonapis;

import abstractapis.AbstractAPI;
import metadataapis.EntityNames;
import model.QuantitativeValue;
import model.SoftwareapplicationParameters;
import org.epos.eposdatamodel.LinkedEntity;
import org.epos.eposdatamodel.Parameter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ParameterAPI extends AbstractAPI<org.epos.eposdatamodel.Parameter> {

    public ParameterAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.Parameter obj) {

        List<SoftwareapplicationParameters> returnList = getDbaccess().getOneFromDB(
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

        obj = (org.epos.eposdatamodel.Parameter) VersioningStatusAPI.checkVersion(obj);

        SoftwareapplicationParameters edmobj = new SoftwareapplicationParameters();
        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setEncodingformat(obj.getEncodingFormat());
        edmobj.setConformsto(obj.getConformsTo());
        edmobj.setAction(obj.getAction().name());

        getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                .instanceId(edmobj.getInstanceId())
                .metaId(edmobj.getMetaId())
                .uid(edmobj.getUid());
    }

    @Override
    public org.epos.eposdatamodel.Parameter retrieve(String instanceId) {
        SoftwareapplicationParameters edmobj = (SoftwareapplicationParameters) getDbaccess().getOneFromDBByInstanceId(instanceId, QuantitativeValue.class).get(0);
        org.epos.eposdatamodel.Parameter o = new org.epos.eposdatamodel.Parameter();

        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setEncodingFormat(edmobj.getEncodingformat());
        o.setConformsTo(edmobj.getConformsto());
        o.setAction(Parameter.ActionEnum.fromValue(edmobj.getAction()));

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
