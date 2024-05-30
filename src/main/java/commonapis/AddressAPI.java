package commonapis;

import abstractapis.AbstractAPI;
import model.Address;
import model.Identifier;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AddressAPI extends AbstractAPI<org.epos.eposdatamodel.Address> {

    public AddressAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.Address obj) {

        List<Address> returnList = getDbaccess().getOneFromDB(
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

        obj = (org.epos.eposdatamodel.Address) VersioningStatusAPI.checkVersion(obj);

        Address edmobj = new Address();
        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setCountry(Optional.ofNullable(obj.getCountry()).orElse(null));
        edmobj.setCountrycode(Optional.ofNullable(obj.getCountryCode()).orElse(null));
        edmobj.setStreet(Optional.ofNullable(obj.getStreet()).orElse(null));
        edmobj.setPostalCode(Optional.ofNullable(obj.getPostalCode()).orElse(null));
        edmobj.setLocality(Optional.ofNullable(obj.getLocality()).orElse(null));

        if(returnList.isEmpty()) getDbaccess().createObject(edmobj);
        else getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                .instanceId(edmobj.getInstanceId())
                .metaId(edmobj.getMetaId())
                .uid(edmobj.getUid());
    }

    @Override
    public org.epos.eposdatamodel.Address retrieve(String instanceId) {
        Address edmobj = (Address) getDbaccess().getOneFromDBByInstanceId(instanceId, Address.class).get(0);
        org.epos.eposdatamodel.Address o = new org.epos.eposdatamodel.Address();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setStreet(edmobj.getStreet());
        o.setCountry(edmobj.getCountry());
        o.setPostalCode(edmobj.getPostalCode());
        o.setCountryCode(edmobj.getCountrycode());
        o.setLocality(edmobj.getLocality());

        return o;
    }


}
