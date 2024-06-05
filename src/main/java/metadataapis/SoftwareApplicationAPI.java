package metadataapis;

import abstractapis.AbstractAPI;
import commonapis.IdentifierAPI;
import commonapis.LinkedEntityAPI;
import commonapis.VersioningStatusAPI;
import model.*;
import model.Category;
import model.Identifier;
import model.Operation;
import model.SoftwareApplication;
import org.epos.eposdatamodel.*;
import relationsapi.CategoryRelationsAPI;
import relationsapi.ContactPointRelationsAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SoftwareApplicationAPI extends AbstractAPI<org.epos.eposdatamodel.SoftwareApplication> {

    public SoftwareApplicationAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.SoftwareApplication obj) {

        List<SoftwareApplication> returnList = getDbaccess().getOneFromDB(
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

        obj = (org.epos.eposdatamodel.SoftwareApplication) VersioningStatusAPI.checkVersion(obj);

        SoftwareApplication edmobj = new SoftwareApplication();

        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());

        getDbaccess().updateObject(edmobj);

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
            IdentifierAPI identifierAPI = new IdentifierAPI(EntityNames.IDENTIFIER.name(), Identifier.class);
            edmobj.setSoftwareapplicationIdentifiersByInstanceId(new ArrayList<>());
            for(org.epos.eposdatamodel.Identifier identifier : obj.getIdentifier()){
                LinkedEntity le = identifierAPI.create(identifier);
                SoftwareapplicationIdentifier pi = new SoftwareapplicationIdentifier();
                pi.setSoftwareapplicationBySoftwareapplicationInstanceId(edmobj);
                pi.setSoftwareapplicationInstanceId(edmobj.getInstanceId());
                pi.setIdentifierInstanceId(le.getInstanceId());
                pi.setIdentifierByIdentifierInstanceId((Identifier) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(),Identifier.class).get(0));

                edmobj.getSoftwareapplicationIdentifiersByInstanceId().add(pi);

                dbaccess.updateObject(pi);
            }
        }

        if (obj.getParameter() != null && !obj.getParameter().isEmpty()) {
            List<SoftwareapplicationParameters> softwareapplicationParametersList = getDbaccess().getAllFromDB(SoftwareapplicationParameters.class);
            for(SoftwareapplicationParameters item : softwareapplicationParametersList){
                if(item.getSoftwareapplicationBySoftwareapplicationInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            edmobj.setSoftwareapplicationParametersByInstanceId(new ArrayList<>());
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

                edmobj.getSoftwareapplicationParametersByInstanceId().add(pi);

                dbaccess.updateObject(pi);
            }
        }

        edmobj.setSoftwareapplicationOperationsByInstanceId(new ArrayList<>());
        if (obj.getRelation() != null && !obj.getRelation().isEmpty()) {
            for(LinkedEntity le : obj.getRelation()){
                Object object = LinkedEntityAPI.retrieveFromLinkedEntity(le);
                if(object instanceof org.epos.eposdatamodel.Operation){
                    List<Operation> operation = dbaccess.getOneFromDBByInstanceId(((org.epos.eposdatamodel.Operation) object).getInstanceId(), Operation.class);
                    SoftwareapplicationOperation pi = new SoftwareapplicationOperation();
                    pi.setSoftwareapplicationBySoftwareapplicationInstanceId(edmobj);
                    pi.setSoftwareapplicationInstanceId(edmobj.getInstanceId());
                    pi.setOperationInstanceId(operation.get(0).getInstanceId());
                    pi.setOperationByOperationInstanceId(operation.get(0));

                    edmobj.getSoftwareapplicationOperationsByInstanceId().add(pi);

                    dbaccess.updateObject(pi);
                }
            }
        }

        getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                    .instanceId(edmobj.getInstanceId())
                    .metaId(edmobj.getMetaId())
                    .uid(edmobj.getUid());

    }



    @Override
    public org.epos.eposdatamodel.SoftwareApplication retrieve(String instanceId) {
        SoftwareApplication edmobj = (SoftwareApplication) getDbaccess().getOneFromDBByInstanceId(instanceId, SoftwareApplication.class).get(0);

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
                CategoryAPI api = new CategoryAPI(EntityNames.CATEGORY.name(), Category.class);
                LinkedEntity cp = api.retrieveLinkedEntity(ed.getCategoryInstanceId());
                o.addCategory(cp);
            }
        }

        if(edmobj.getSoftwareapplicationContactpointsByInstanceId().size()>0) {
            for(SoftwareapplicationContactpoint ed : edmobj.getSoftwareapplicationContactpointsByInstanceId()) {
                ContactPointAPI api = new ContactPointAPI(EntityNames.CONTACTPOINT.name(), Contactpoint.class);
                LinkedEntity cp = api.retrieveLinkedEntity(ed.getContactpointInstanceId());
                o.addContactPoint(cp);
            }
        }

        if(edmobj.getSoftwareapplicationIdentifiersByInstanceId().size()>0) {
            for(SoftwareapplicationIdentifier ed : edmobj.getSoftwareapplicationIdentifiersByInstanceId()) {
                IdentifierAPI api = new IdentifierAPI(EntityNames.IDENTIFIER.name(), Identifier.class);
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

        if(edmobj.getSoftwareapplicationOperationsByInstanceId().size()>0) {
            for(SoftwareapplicationOperation ed : edmobj.getSoftwareapplicationOperationsByInstanceId()) {
                Operation op = ed.getOperationByOperationInstanceId();
                LinkedEntity le = new LinkedEntity();
                le.setInstanceId(op.getInstanceId());
                le.setUid(op.getUid());
                le.setMetaId(op.getMetaId());
                le.setEntityType(EntityNames.OPERATION.name());
                o.addRelation(le);
            }
        }
        return o;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        SoftwareApplication edmobj = (SoftwareApplication) getDbaccess().getOneFromDBByInstanceId(instanceId, SoftwareApplication.class).get(0);

        LinkedEntity o = new LinkedEntity();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setEntityType(EntityNames.SOFTWAREAPPLICATION.name());

        return o;
    }

}
