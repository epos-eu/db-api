package commonapis;

import abstractapis.AbstractAPI;
import metadataapis.EntityNames;
import model.Temporal;
import org.epos.eposdatamodel.LinkedEntity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TemporalAPI extends AbstractAPI<org.epos.eposdatamodel.PeriodOfTime> {

    public TemporalAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.PeriodOfTime obj) {

        List<Temporal> returnList = getDbaccess().getOneFromDB(
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

        obj = (org.epos.eposdatamodel.PeriodOfTime) VersioningStatusAPI.checkVersion(obj);

        Temporal edmobj = new Temporal();
        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setStartdate(Timestamp.valueOf(Optional.ofNullable(obj.getStartDate()).orElse(null)));
        edmobj.setEnddate(Timestamp.valueOf(Optional.ofNullable(obj.getEndDate()).orElse(null)));

        getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                .instanceId(edmobj.getInstanceId())
                .metaId(edmobj.getMetaId())
                .uid(edmobj.getUid());
    }

    @Override
    public org.epos.eposdatamodel.PeriodOfTime retrieve(String instanceId) {
        Temporal edmobj = (Temporal) getDbaccess().getOneFromDBByInstanceId(instanceId, Temporal.class).get(0);
        org.epos.eposdatamodel.PeriodOfTime o = new org.epos.eposdatamodel.PeriodOfTime();

        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setStartDate(edmobj.getStartdate().toLocalDateTime());
        o.setEndDate(edmobj.getEnddate().toLocalDateTime());

        return o;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        Temporal edmobj = (Temporal) getDbaccess().getOneFromDBByInstanceId(instanceId, Temporal.class).get(0);

        LinkedEntity o = new LinkedEntity();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setEntityType(EntityNames.PERIODOFTIME.name());

        return o;
    }


}
