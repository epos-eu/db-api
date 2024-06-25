package metadataapis;

import abstractapis.AbstractAPI;
import commonapis.IdentifierAPI;
import commonapis.LinkedEntityAPI;
import commonapis.ParameterAPI;
import commonapis.VersioningStatusAPI;
import model.*;
import model.Category;
import model.Identifier;
import model.Operation;
import model.Person;
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
                    if(list2.size()>0) getDbaccess().deleteObject(list2.get(0));
                }
            }
            edmobj.setSoftwareapplicationIdentifiersByInstanceId(new ArrayList<>());
            for(org.epos.eposdatamodel.LinkedEntity identifier : obj.getIdentifier()){
                LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(identifier);
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
            ParameterAPI api = new ParameterAPI(EntityNames.PARAMETER.name(), SoftwareapplicationParameters.class);
            for(org.epos.eposdatamodel.LinkedEntity parameter : obj.getParameter()){
                LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(parameter);

                edmobj.getSoftwareapplicationParametersByInstanceId().add((SoftwareapplicationParameters) getDbaccess().getOneFromDBByInstanceId(le.getInstanceId(),SoftwareapplicationParameters.class));
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
            IdentifierAPI api = new IdentifierAPI(EntityNames.IDENTIFIER.name(), Identifier.class);
            for(SoftwareapplicationIdentifier ed : edmobj.getSoftwareapplicationIdentifiersByInstanceId()) {
                org.epos.eposdatamodel.LinkedEntity cp = api.retrieveLinkedEntity(ed.getIdentifierInstanceId());
                o.addIdentifier(cp);
            }
        }

        if(edmobj.getSoftwareapplicationParametersByInstanceId().size()>0) {
            ParameterAPI api = new ParameterAPI(EntityNames.PARAMETER.name(), SoftwareapplicationParameters.class);
            for(SoftwareapplicationParameters ed : edmobj.getSoftwareapplicationParametersByInstanceId()) {
                o.addParameter(api.retrieveLinkedEntity(ed.getInstanceId()));
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

        o = (org.epos.eposdatamodel.SoftwareApplication) VersioningStatusAPI.retrieveVersion(o);

        return o;
    }

    @Override
    public List<org.epos.eposdatamodel.SoftwareApplication> retrieveAll() {
        List<SoftwareApplication> list = getDbaccess().getAllFromDB(SoftwareApplication.class);
        List<org.epos.eposdatamodel.SoftwareApplication> returnList = new ArrayList<>();
        for(SoftwareApplication item : list){
            returnList.add(retrieve(item.getInstanceId()));
        }
        return returnList;
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
