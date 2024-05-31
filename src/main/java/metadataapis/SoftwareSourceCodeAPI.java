package metadataapis;

import abstractapis.AbstractAPI;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import commonapis.*;
import model.*;
import org.epos.eposdatamodel.ContactPoint;
import org.epos.eposdatamodel.Documentation;
import org.epos.eposdatamodel.LinkedEntity;
import org.epos.eposdatamodel.SoftwareSourceCode;
import relationsapi.CategoryRelationsAPI;
import relationsapi.ContactPointRelationsAPI;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SoftwareSourceCodeAPI extends AbstractAPI<org.epos.eposdatamodel.SoftwareSourceCode> {

    public SoftwareSourceCodeAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.SoftwareSourceCode obj) {

        List<Softwaresourcecode> returnList = getDbaccess().getOneFromDB(
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

        obj = (org.epos.eposdatamodel.SoftwareSourceCode) VersioningStatusAPI.checkVersion(obj);

        Softwaresourcecode edmobj = new Softwaresourcecode();

        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setName(obj.getName());
        edmobj.setDescription(obj.getDescription());
        edmobj.setDownloadurl(obj.getDownloadURL());
        edmobj.setKeywords(obj.getKeywords());
        edmobj.setLicenseurl(obj.getLicenseURL());
        edmobj.setMainentityofpage(obj.getMainEntityofPage());
        edmobj.setRuntimeplatform(obj.getRuntimePlatform());
        edmobj.setSoftwareversion(obj.getSoftwareVersion());
        edmobj.setCoderepository(obj.getCodeRepository());


        if(returnList.isEmpty()) getDbaccess().createObject(edmobj);
        else getDbaccess().updateObject(edmobj);

        /** CATEGORY **/
        if (obj.getCategory() != null && !obj.getCategory().isEmpty())
            CategoryRelationsAPI.createRelation(edmobj,obj);

        /** CONTACTPOINT **/
        if (obj.getContactPoint() != null && !obj.getContactPoint().isEmpty())
            ContactPointRelationsAPI.createRelation(edmobj,obj);

        /** IDENTIFIER **/
        if (obj.getIdentifier() != null && !obj.getIdentifier().isEmpty()) {
            List<SoftwaresourcecodeIdentifier> softwaresourcecodeIdentifierList = getDbaccess().getAllFromDB(SoftwaresourcecodeIdentifier.class);
            for(SoftwaresourcecodeIdentifier item : softwaresourcecodeIdentifierList){
                if(item.getSoftwaresourcecodeInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                    List<Identifier> list2 = getDbaccess().getOneFromDBByInstanceId(item.getIdentifierInstanceId(), Identifier.class);
                    getDbaccess().deleteObject(list2.get(0));
                }
            }
            IdentifierAPI identifierAPI = new IdentifierAPI("Identifier", Identifier.class);
            for(org.epos.eposdatamodel.Identifier identifier : obj.getIdentifier()){
                LinkedEntity le = identifierAPI.create(identifier);
                SoftwaresourcecodeIdentifier pi = new SoftwaresourcecodeIdentifier();
                pi.setSoftwaresourcecodeBySoftwaresourcecodeInstanceId(edmobj);
                pi.setSoftwaresourcecodeInstanceId(edmobj.getInstanceId());
                pi.setIdentifierInstanceId(le.getInstanceId());
                pi.setIdentifierByIdentifierInstanceId((Identifier) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(),Identifier.class).get(0));
                dbaccess.createObject(pi);
            }
        }

        /** PROGRAMMING LANGUAGE **/
        if(!obj.getProgrammingLanguage().isEmpty()){
            for(String returns : obj.getProgrammingLanguage()) {
                createInnerElement(ElementType.PROGRAMMINGLANGUAGE, returns, edmobj);
            }
        }

        return new LinkedEntity().entityType(entityName)
                    .instanceId(edmobj.getInstanceId())
                    .metaId(edmobj.getMetaId())
                    .uid(edmobj.getUid());

    }

    private void createInnerElement(ElementType elementType, String value, Softwaresourcecode edmobj){
        org.epos.eposdatamodel.Element element = new org.epos.eposdatamodel.Element();
        element.setType(elementType);
        element.setValue(value);
        ElementAPI api = new ElementAPI("Element", Element.class);
        LinkedEntity le = api.create(element);
        List<Element> el = dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Element.class);
        SoftwaresourcecodeElement ce = new SoftwaresourcecodeElement();
        ce.setSoftwaresourcecodeBySoftwaresourcecodeInstanceId(edmobj);
        ce.setSoftwaresourcecodeInstanceId(edmobj.getInstanceId());
        ce.setElementByElementInstanceId(el.get(0));
        ce.setElementInstanceId(el.get(0).getInstanceId());
        dbaccess.createObject(ce);
    }


    @Override
    public org.epos.eposdatamodel.SoftwareSourceCode retrieve(String instanceId) {
        Softwaresourcecode edmobj = (Softwaresourcecode) getDbaccess().getOneFromDBByInstanceId(instanceId, Softwaresourcecode.class).get(0);

        org.epos.eposdatamodel.SoftwareSourceCode o = new org.epos.eposdatamodel.SoftwareSourceCode();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setName(edmobj.getName());
        o.setDescription(edmobj.getDescription());
        o.setDownloadURL(edmobj.getDownloadurl());
        o.addKeywords(edmobj.getKeywords());
        o.setLicenseURL(edmobj.getLicenseurl());
        o.setMainEntityofPage(edmobj.getMainentityofpage());
        o.setRuntimePlatform(edmobj.getRuntimeplatform());
        o.setSoftwareVersion(edmobj.getSoftwareversion());
        o.setCodeRepository(edmobj.getCoderepository());


        if(edmobj.getSoftwaresourcecodeCategoriesByInstanceId().size()>0) {
            for(SoftwaresourcecodeCategory ed : edmobj.getSoftwaresourcecodeCategoriesByInstanceId()) {
                CategoryAPI api = new CategoryAPI("Category", Category.class);
                org.epos.eposdatamodel.Category cp = api.retrieve(ed.getCategoryInstanceId());
                o.addCategory(cp);
            }
        }

        if(edmobj.getSoftwaresourcecodeContactpointsByInstanceId().size()>0) {
            for(SoftwaresourcecodeContactpoint ed : edmobj.getSoftwaresourcecodeContactpointsByInstanceId()) {
                ContactPointAPI api = new ContactPointAPI("ContactPoint", Contactpoint.class);
                ContactPoint cp = api.retrieve(ed.getContactpointInstanceId());
                o.addContactPoint(cp);
            }
        }

        if(edmobj.getSoftwaresourcecodeIdentifiersByInstanceId().size()>0) {
            for(SoftwaresourcecodeIdentifier ed : edmobj.getSoftwaresourcecodeIdentifiersByInstanceId()) {
                IdentifierAPI api = new IdentifierAPI("Identifier", Identifier.class);
                org.epos.eposdatamodel.Identifier cp = api.retrieve(ed.getIdentifierInstanceId());
                o.addIdentifier(cp);
            }
        }
        if(edmobj.getSoftwaresourcecodeElementsByInstanceId().size()>0) {
            for(SoftwaresourcecodeElement ed : edmobj.getSoftwaresourcecodeElementsByInstanceId()) {
                Element el = ed.getElementByElementInstanceId();
                if(el.getType().equals(ElementType.PROGRAMMINGLANGUAGE)) {
                    o.addProgrammingLanguage(el.getValue());
                }
            }
        }
        return o;
    }
    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        Softwaresourcecode edmobj = (Softwaresourcecode) getDbaccess().getOneFromDBByInstanceId(instanceId, Softwaresourcecode.class).get(0);

        LinkedEntity o = new LinkedEntity();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setEntityType(EntityNames.SOFTWARESOURCECODE.name());

        return o;
    }
}
