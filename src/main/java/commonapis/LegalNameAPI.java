package commonapis;

import abstractapis.AbstractAPI;
import metadataapis.EntityNames;
import model.OrganizationLegalname;
import model.Temporal;
import org.epos.eposdatamodel.LinkedEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LegalNameAPI extends AbstractAPI<org.epos.eposdatamodel.LegalName> {

    public LegalNameAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.LegalName obj) {

        List<OrganizationLegalname> returnList = getDbaccess().getOneFromDB(
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

        obj = (org.epos.eposdatamodel.LegalName) VersioningStatusAPI.checkVersion(obj);

        OrganizationLegalname edmobj = new OrganizationLegalname();
        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setLanguage(Optional.ofNullable(obj.getLanguage()).orElse(null));
        edmobj.setLegalname(Optional.ofNullable(obj.getLegalname()).orElse(null));

        getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                .instanceId(edmobj.getInstanceId())
                .metaId(edmobj.getMetaId())
                .uid(edmobj.getUid());
    }

    @Override
    public org.epos.eposdatamodel.LegalName retrieve(String instanceId) {
        OrganizationLegalname edmobj = (OrganizationLegalname) getDbaccess().getOneFromDBByInstanceId(instanceId, OrganizationLegalname.class).get(0);
        org.epos.eposdatamodel.LegalName o = new org.epos.eposdatamodel.LegalName();

        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setLanguage(Optional.ofNullable(edmobj.getLanguage()).orElse(null));
        o.setLegalname(Optional.ofNullable(edmobj.getLegalname()).orElse(null));

        o = (org.epos.eposdatamodel.LegalName) VersioningStatusAPI.retrieveVersion(o);

        return o;
    }

    @Override
    public List<org.epos.eposdatamodel.LegalName> retrieveAll() {
        List<OrganizationLegalname> list = getDbaccess().getAllFromDB(OrganizationLegalname.class);
        List<org.epos.eposdatamodel.LegalName> returnList = new ArrayList<>();
        for(OrganizationLegalname item : list){
            returnList.add(retrieve(item.getInstanceId()));
        }
        return returnList;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        OrganizationLegalname edmobj = (OrganizationLegalname) getDbaccess().getOneFromDBByInstanceId(instanceId, OrganizationLegalname.class).get(0);

        LinkedEntity o = new LinkedEntity();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setEntityType(EntityNames.LEGALNAME.name());

        return o;
    }


}
