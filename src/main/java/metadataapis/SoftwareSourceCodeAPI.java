package metadataapis;

import abstractapis.AbstractAPI;
import commonapis.*;
import model.*;
import org.epos.eposdatamodel.ContactPoint;
import org.epos.eposdatamodel.LinkedEntity;
import relationsapi.CategoryRelationsAPI;
import relationsapi.ContactPointRelationsAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SoftwareSourceCodeAPI extends AbstractAPI<org.epos.eposdatamodel.SoftwareSourceCode> {

    public SoftwareSourceCodeAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.SoftwareSourceCode obj) {

        List<SoftwareSourceCode> returnList = getDbaccess().getOneFromDB(
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

        SoftwareSourceCode edmobj = new SoftwareSourceCode();

        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());

        getDbaccess().updateObject(edmobj);

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
                }
            }
            IdentifierAPI identifierAPI = new IdentifierAPI(EntityNames.IDENTIFIER.name(), Identifier.class);
            edmobj.setSoftwaresourcecodeIdentifiersByInstanceId(new ArrayList<>());
            for(org.epos.eposdatamodel.Identifier identifier : obj.getIdentifier()){
                LinkedEntity le = identifierAPI.create(identifier);
                SoftwaresourcecodeIdentifier pi = new SoftwaresourcecodeIdentifier();
                pi.setSoftwaresourcecodeBySoftwaresourcecodeInstanceId(edmobj);
                pi.setSoftwaresourcecodeInstanceId(edmobj.getInstanceId());
                pi.setIdentifierInstanceId(le.getInstanceId());
                pi.setIdentifierByIdentifierInstanceId((Identifier) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(),Identifier.class).get(0));

                edmobj.getSoftwaresourcecodeIdentifiersByInstanceId().add(pi);

                dbaccess.updateObject(pi);
            }
        }

        edmobj.setSoftwaresourcecodeElementsByInstanceId(new ArrayList<>());
        /** PROGRAMMING LANGUAGE **/
        if(obj.getProgrammingLanguage()!=null && !obj.getProgrammingLanguage().isEmpty()){
            for(String returns : obj.getProgrammingLanguage()) {
                createInnerElement(ElementType.PROGRAMMINGLANGUAGE, returns, edmobj);
            }
        }

        getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                    .instanceId(edmobj.getInstanceId())
                    .metaId(edmobj.getMetaId())
                    .uid(edmobj.getUid());

    }

    private void createInnerElement(ElementType elementType, String value, SoftwareSourceCode edmobj){
        org.epos.eposdatamodel.Element element = new org.epos.eposdatamodel.Element();
        element.setType(elementType);
        element.setValue(value);
        ElementAPI api = new ElementAPI(EntityNames.ELEMENT.name(), Element.class);
        LinkedEntity le = api.create(element);
        List<Element> el = dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Element.class);
        SoftwaresourcecodeElement ce = new SoftwaresourcecodeElement();
        ce.setSoftwaresourcecodeBySoftwaresourcecodeInstanceId(edmobj);
        ce.setSoftwaresourcecodeInstanceId(edmobj.getInstanceId());
        ce.setElementByElementInstanceId(el.get(0));
        ce.setElementInstanceId(el.get(0).getInstanceId());

        edmobj.getSoftwaresourcecodeElementsByInstanceId().add(ce);

        dbaccess.updateObject(ce);
    }


    @Override
    public org.epos.eposdatamodel.SoftwareSourceCode retrieve(String instanceId) {
        SoftwareSourceCode edmobj = (SoftwareSourceCode) getDbaccess().getOneFromDBByInstanceId(instanceId, SoftwareSourceCode.class).get(0);

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
                CategoryAPI api = new CategoryAPI(EntityNames.CATEGORY.name(), Category.class);
                org.epos.eposdatamodel.Category cp = api.retrieve(ed.getCategoryInstanceId());
                o.addCategory(cp);
            }
        }

        if(edmobj.getSoftwaresourcecodeContactpointsByInstanceId().size()>0) {
            for(SoftwaresourcecodeContactpoint ed : edmobj.getSoftwaresourcecodeContactpointsByInstanceId()) {
                ContactPointAPI api = new ContactPointAPI(EntityNames.CONTACTPOINT.name(), Contactpoint.class);
                ContactPoint cp = api.retrieve(ed.getContactpointInstanceId());
                o.addContactPoint(cp);
            }
        }

        if(edmobj.getSoftwaresourcecodeIdentifiersByInstanceId().size()>0) {
            for(SoftwaresourcecodeIdentifier ed : edmobj.getSoftwaresourcecodeIdentifiersByInstanceId()) {
                IdentifierAPI api = new IdentifierAPI(EntityNames.IDENTIFIER.name(), Identifier.class);
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
        SoftwareSourceCode edmobj = (SoftwareSourceCode) getDbaccess().getOneFromDBByInstanceId(instanceId, SoftwareSourceCode.class).get(0);

        LinkedEntity o = new LinkedEntity();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setEntityType(EntityNames.SOFTWARESOURCECODE.name());

        return o;
    }
}
