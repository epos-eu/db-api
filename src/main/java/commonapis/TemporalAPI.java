package commonapis;

import abstractapis.AbstractAPI;
import metadataapis.EntityNames;
import model.Spatial;
import model.Temporal;
import org.epos.eposdatamodel.LinkedEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
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

        EposDataModelEntityIDAPI.addEntityToEDMEntityID(obj.getMetaId(), entityName);

        Temporal edmobj = new Temporal();
        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setStartdate(obj.getStartDate()!=null? Timestamp.valueOf(obj.getStartDate()) : null);
        edmobj.setEnddate(obj.getEndDate()!=null? Timestamp.valueOf(obj.getEndDate()) : null);

        getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                .instanceId(edmobj.getInstanceId())
                .metaId(edmobj.getMetaId())
                .uid(edmobj.getUid());
    }

    @Override
    public org.epos.eposdatamodel.PeriodOfTime retrieve(String instanceId) {
        List<Temporal> elementList = getDbaccess().getOneFromDBByInstanceId(instanceId, Temporal.class);
        if(elementList!=null && !elementList.isEmpty()) {
            Temporal edmobj = elementList.get(0);
            org.epos.eposdatamodel.PeriodOfTime o = new org.epos.eposdatamodel.PeriodOfTime();

            o.setInstanceId(edmobj.getInstanceId());
            o.setMetaId(edmobj.getMetaId());
            o.setUid(edmobj.getUid());
            o.setStartDate(edmobj.getStartdate() != null ? edmobj.getStartdate().toLocalDateTime() : null);
            o.setEndDate(edmobj.getEnddate() != null ? edmobj.getEnddate().toLocalDateTime() : null);

            o = (org.epos.eposdatamodel.PeriodOfTime) VersioningStatusAPI.retrieveVersion(o);

            return o;
        }
        return null;
    }

    @Override
    public List<org.epos.eposdatamodel.PeriodOfTime> retrieveAll() {
        List<Temporal> list = getDbaccess().getAllFromDB(Temporal.class);
        List<org.epos.eposdatamodel.PeriodOfTime> returnList = new ArrayList<>();
        for(Temporal item : list){
            returnList.add(retrieve(item.getInstanceId()));
        }
        return returnList;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        List<Temporal> elementList = getDbaccess().getOneFromDBByInstanceId(instanceId, Temporal.class);
        if(elementList!=null && !elementList.isEmpty()) {
            Temporal edmobj = elementList.get(0);
            LinkedEntity o = new LinkedEntity();
            o.setInstanceId(edmobj.getInstanceId());
            o.setMetaId(edmobj.getMetaId());
            o.setUid(edmobj.getUid());
            o.setEntityType(EntityNames.PERIODOFTIME.name());

            return o;
        }
        return null;
    }


}
