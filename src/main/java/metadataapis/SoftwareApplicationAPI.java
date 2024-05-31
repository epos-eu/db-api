package metadataapis;

import abstractapis.AbstractAPI;
import commonapis.ElementAPI;
import commonapis.IdentifierAPI;
import commonapis.VersioningStatusAPI;
import model.*;
import model.Category;
import model.Identifier;
import org.epos.eposdatamodel.*;
import relationsapi.CategoryRelationsAPI;
import relationsapi.ContactPointRelationsAPI;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SoftwareApplicationAPI extends AbstractAPI<org.epos.eposdatamodel.SoftwareApplication> {

    public SoftwareApplicationAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(SoftwareApplication obj) {

        List<Softwareapplication> returnList = getDbaccess().getOneFromDB(
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

        obj = (SoftwareApplication) VersioningStatusAPI.checkVersion(obj);

        Softwareapplication edmobj = new Softwareapplication();

        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setName(obj.getName());
        edmobj.setDescription(obj.getDescription());
        edmobj.setDownloadurl(obj.getDownloadURL());
        edmobj.setInstallurl(obj.getInstallURL());
        edmobj.setKeywords(obj.getKeywords());
        edmobj.setLicenseurl(obj.getLicenseURL());
        edmobj.setMainentityofpage(obj.getMainEntityOfPage());
        edmobj.setRequirements(obj.getRequirements());
        edmobj.setSoftwareversion(obj.getSoftwareVersion());



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
            List<SoftwareapplicationIdentifier> softwareapplicationIdentifierList = getDbaccess().getAllFromDB(SoftwareapplicationIdentifier.class);
            for(SoftwareapplicationIdentifier item : softwareapplicationIdentifierList){
                if(item.getSoftwareapplicationBySoftwareapplicationInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                    List<Identifier> list2 = getDbaccess().getOneFromDBByInstanceId(item.getIdentifierInstanceId(), Identifier.class);
                    getDbaccess().deleteObject(list2.get(0));
                }
            }
            IdentifierAPI identifierAPI = new IdentifierAPI("Identifier", Identifier.class);
            for(org.epos.eposdatamodel.Identifier identifier : obj.getIdentifier()){
                LinkedEntity le = identifierAPI.create(identifier);
                SoftwareapplicationIdentifier pi = new SoftwareapplicationIdentifier();
                pi.setSoftwareapplicationBySoftwareapplicationInstanceId(edmobj);
                pi.setSoftwareapplicationInstanceId(edmobj.getInstanceId());
                pi.setIdentifierInstanceId(le.getInstanceId());
                pi.setIdentifierByIdentifierInstanceId((Identifier) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(),Identifier.class).get(0));
                dbaccess.createObject(pi);
            }
        }

        if (obj.getParameter() != null && !obj.getParameter().isEmpty()) {
            List<SoftwareapplicationParameters> softwareapplicationParametersList = getDbaccess().getAllFromDB(SoftwareapplicationParameters.class);
            for(SoftwareapplicationParameters item : softwareapplicationParametersList){
                if(item.getSoftwareapplicationBySoftwareapplicationInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            for(org.epos.eposdatamodel.Parameter parameter : obj.getParameter()){
                SoftwareapplicationParameters pi = new SoftwareapplicationParameters();
                pi.setInstanceId(UUID.randomUUID().toString());
                pi.setMetaId(UUID.randomUUID().toString());
                pi.setUid("Parameter/"+UUID.randomUUID().toString());
                pi.setVersionId(UUID.randomUUID().toString());
                pi.setAction(parameter.getAction().name());
                pi.setConformsto(parameter.getConformsTo());
                pi.setEncodingformat(parameter.getEncodingFormat());
                pi.setSoftwareapplicationBySoftwareapplicationInstanceId(edmobj);
                pi.setSoftwareapplicationInstanceId(edmobj.getInstanceId());
                dbaccess.createObject(pi);
            }
        }



        /** TODO: RELATION **/

        return new LinkedEntity().entityType(entityName)
                    .instanceId(edmobj.getInstanceId())
                    .metaId(edmobj.getMetaId())
                    .uid(edmobj.getUid());

    }



    @Override
    public org.epos.eposdatamodel.SoftwareApplication retrieve(String instanceId) {
        Softwareapplication edmobj = (Softwareapplication) getDbaccess().getOneFromDBByInstanceId(instanceId, Softwareapplication.class).get(0);

        org.epos.eposdatamodel.SoftwareApplication o = new org.epos.eposdatamodel.SoftwareApplication();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setVersionId(edmobj.getVersionId());
        o.setName(edmobj.getName());
        o.setDescription(edmobj.getDescription());
        o.setDownloadURL(edmobj.getDownloadurl());
        o.setInstallURL(edmobj.getInstallurl());
        o.addKeywords(edmobj.getKeywords());
        o.setLicenseURL(edmobj.getLicenseurl());
        o.setMainEntityOfPage(edmobj.getMainentityofpage());
        o.setRequirements(edmobj.getRequirements());
        o.setSoftwareVersion(edmobj.getSoftwareversion());


        if(edmobj.getSoftwareapplicationCategoriesByInstanceId().size()>0) {
            for(SoftwareapplicationCategory ed : edmobj.getSoftwareapplicationCategoriesByInstanceId()) {
                CategoryAPI api = new CategoryAPI("Category", Category.class);
                org.epos.eposdatamodel.Category cp = api.retrieve(ed.getCategoryInstanceId());
                o.addCategory(cp);
            }
        }

        if(edmobj.getSoftwareapplicationContactpointsByInstanceId().size()>0) {
            for(SoftwareapplicationContactpoint ed : edmobj.getSoftwareapplicationContactpointsByInstanceId()) {
                ContactPointAPI api = new ContactPointAPI("ContactPoint", Contactpoint.class);
                ContactPoint cp = api.retrieve(ed.getContactpointInstanceId());
                o.addContactPoint(cp);
            }
        }

        if(edmobj.getSoftwareapplicationIdentifiersByInstanceId().size()>0) {
            for(SoftwareapplicationIdentifier ed : edmobj.getSoftwareapplicationIdentifiersByInstanceId()) {
                IdentifierAPI api = new IdentifierAPI("Identifier", Identifier.class);
                org.epos.eposdatamodel.Identifier cp = api.retrieve(ed.getIdentifierInstanceId());
                o.addIdentifier(cp);
            }
        }

        if(edmobj.getSoftwareapplicationParametersByInstanceId().size()>0) {
            for(SoftwareapplicationParameters ed : edmobj.getSoftwareapplicationParametersByInstanceId()) {
                Parameter p = new Parameter();
                p.setAction(Parameter.ActionEnum.fromValue(ed.getAction()));
                p.setConformsTo(ed.getConformsto());
                p.setEncodingFormat(ed.getEncodingformat());
                o.addParameter(p);
            }
        }
        return o;
    }

}
