package commonapis;

import abstractapis.AbstractAPI;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import metadataapis.EntityNames;
import model.*;
import org.epos.eposdatamodel.Documentation;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DocumentationAPI extends AbstractAPI<org.epos.eposdatamodel.Documentation> {

    public DocumentationAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.Documentation obj) {

        List<Element> returnList = getDbaccess().getOneFromDB(
                obj.getInstanceId(),
                obj.getMetaId(),
                obj.getUid(),
                obj.getVersionId(),
                Element.class);

        if(!returnList.isEmpty()){
            obj.setInstanceId(returnList.get(0).getInstanceId());
            obj.setMetaId(returnList.get(0).getMetaId());
            obj.setUid(returnList.get(0).getUid());
            obj.setVersionId(returnList.get(0).getVersionId());
        }

        obj = (org.epos.eposdatamodel.Documentation) VersioningStatusAPI.checkVersion(obj);

        Element edmobj = new Element();
        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setType(ElementType.DOCUMENTATION);
        JsonObject documentationObj = new JsonObject();
        documentationObj.addProperty("Title", obj.getTitle());
        documentationObj.addProperty("Description", obj.getDescription());
        documentationObj.addProperty("Uri", obj.getUri());
        String doc = new Gson().toJson(documentationObj);
        edmobj.setValue(doc);

        getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                .instanceId(edmobj.getInstanceId())
                .metaId(edmobj.getMetaId())
                .uid(edmobj.getUid());
    }

    @Override
    public org.epos.eposdatamodel.Documentation retrieve(String instanceId) {
        Element edmobj = (Element) getDbaccess().getOneFromDBByInstanceId(instanceId, Element.class).get(0);
        org.epos.eposdatamodel.Documentation o = new org.epos.eposdatamodel.Documentation();

        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        JsonObject doc = new Gson().fromJson(edmobj.getValue(), JsonObject.class);
        o.setTitle(doc.has("Title")? doc.get("Title").getAsString() : null);
        o.setDescription(doc.has("Description")? doc.get("Description").getAsString() : null);
        o.setUri(doc.has("Uri")? doc.get("Uri").getAsString() : null);

        return o;
    }

    @Override
    public List<org.epos.eposdatamodel.Documentation> retrieveAll() {
        List<Element> list = getDbaccess().getAllFromDB(Element.class);
        List<org.epos.eposdatamodel.Documentation> returnList = new ArrayList<>();
        for(Element item : list){
            returnList.add(retrieve(item.getInstanceId()));
        }
        return returnList;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        Element edmobj = (Element) getDbaccess().getOneFromDBByInstanceId(instanceId, Element.class).get(0);

        LinkedEntity o = new LinkedEntity();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setEntityType(EntityNames.DOCUMENTATION.name());

        return o;
    }


}
