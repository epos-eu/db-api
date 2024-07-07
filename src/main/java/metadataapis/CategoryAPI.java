package metadataapis;

import abstractapis.AbstractAPI;
import commonapis.EposDataModelEntityIDAPI;
import commonapis.LinkedEntityAPI;
import commonapis.VersioningStatusAPI;
import model.*;
import org.eclipse.persistence.internal.jpa.rs.metadata.model.Link;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.*;

public class CategoryAPI extends AbstractAPI<org.epos.eposdatamodel.Category> {

    public CategoryAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.Category obj) {

        List<Category> returnList = getDbaccess().getOneFromDB(
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

        obj = (org.epos.eposdatamodel.Category) VersioningStatusAPI.checkVersion(obj);

        EposDataModelEntityIDAPI.addEntityToEDMEntityID(obj.getMetaId(), entityName);

        Category edmobj = new Category();

        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());

        getDbaccess().updateObject(edmobj);

        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setName(Optional.ofNullable(obj.getName()).orElse(""));
        edmobj.setDescription(Optional.ofNullable(obj.getDescription()).orElse(""));

        if (Objects.nonNull(obj.getInScheme())) createInscheme(obj.getInScheme(), edmobj);

        edmobj.setCategoryIspartofsByInstanceId(new ArrayList<>());

        if (Objects.nonNull(obj.getBroader())) createBroaders(obj.getBroader(), edmobj);
        if (Objects.nonNull(obj.getNarrower())) createNarrowers(obj.getNarrower(), edmobj);

        getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                    .instanceId(edmobj.getInstanceId())
                    .metaId(edmobj.getMetaId())
                    .uid(edmobj.getUid());

    }

    private void createInscheme(LinkedEntity inscheme, Category edmobj){
        CategorySchemeAPI api = new CategorySchemeAPI(EntityNames.CATEGORYSCHEME.name(), CategoryScheme.class);
        org.epos.eposdatamodel.CategoryScheme childObj = new org.epos.eposdatamodel.CategoryScheme();
        childObj.setInstanceId(inscheme.getInstanceId());
        childObj.setMetaId(inscheme.getMetaId());
        childObj.setUid(inscheme.getUid());
        LinkedEntity le = api.create(childObj);
        edmobj.setInScheme(le.getInstanceId());

    }

    private void createBroaders(List<LinkedEntity> broaders, Category edmobj){
        for(LinkedEntity broader : broaders) {

            LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(broader);

            List<CategoryIspartof> list = getDbaccess().getAllFromDB(CategoryIspartof.class);
            for(CategoryIspartof item : list){
                if(item.getCategory1InstanceId().equals(le.getInstanceId())
                        && item.getCategory2InstanceId().equals(edmobj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }

            CategoryIspartof categoryIspartof = new CategoryIspartof();
            categoryIspartof.setCategory1InstanceId(((Category) getDbaccess().getOneFromDBByInstanceId(le.getInstanceId(), Category.class).get(0)).getInstanceId());
            categoryIspartof.setCategory2InstanceId(edmobj.getInstanceId());
            categoryIspartof.setCategoryByCategory1InstanceId((Category) getDbaccess().getOneFromDBByInstanceId(le.getInstanceId(), Category.class).get(0));
            categoryIspartof.setCategoryByCategory2InstanceId(edmobj);

            edmobj.getCategoryIspartofsByInstanceId().add(categoryIspartof);

            getDbaccess().updateObject(categoryIspartof);
        }
    }

    private void createNarrowers(List<LinkedEntity> narrowers, Category edmobj){
        CategoryAPI api = new CategoryAPI(EntityNames.CATEGORY.name(), Category.class);
        for(LinkedEntity narrower : narrowers) {

            LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(narrower);

            List<CategoryIspartof> list = getDbaccess().getAllFromDB(CategoryIspartof.class);
            for(CategoryIspartof item : list){
                if(item.getCategory2InstanceId().equals(le.getInstanceId())
                        && item.getCategory1InstanceId().equals(edmobj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }

            CategoryIspartof categoryIspartof = new CategoryIspartof();
            categoryIspartof.setCategory1InstanceId(edmobj.getInstanceId());
            categoryIspartof.setCategory2InstanceId(((Category) getDbaccess().getOneFromDBByInstanceId(le.getInstanceId(), Category.class).get(0)).getInstanceId());
            categoryIspartof.setCategoryByCategory1InstanceId(edmobj);
            categoryIspartof.setCategoryByCategory2InstanceId((Category) getDbaccess().getOneFromDBByInstanceId(le.getInstanceId(), Category.class).get(0));

            edmobj.getCategoryIspartofsByInstanceId().add(categoryIspartof);

            getDbaccess().updateObject(categoryIspartof);
        }
    }

    @Override
    public org.epos.eposdatamodel.Category retrieve(String instanceId) {
        List<Category> elementList = getDbaccess().getOneFromDBByInstanceId(instanceId, Category.class);
        if(elementList!=null && !elementList.isEmpty()) {
            Category edmobj = elementList.get(0);

            org.epos.eposdatamodel.Category o = new org.epos.eposdatamodel.Category();
            o.setInstanceId(edmobj.getInstanceId());
            o.setMetaId(edmobj.getMetaId());
            o.setUid(edmobj.getUid());
            o.setName(edmobj.getName());
            o.setDescription(edmobj.getDescription());
            if (edmobj.getInScheme() != null) {
                CategorySchemeAPI csapi = new CategorySchemeAPI(EntityNames.CATEGORYSCHEME.name(), CategoryScheme.class);
                o.setInScheme(csapi.retrieveLinkedEntity(edmobj.getInScheme()));
            }

            edmobj.getCategoryIspartofsByInstanceId_0().isEmpty();
            edmobj.getCategoryIspartofsByInstanceId().isEmpty();

            if (edmobj.getCategoryIspartofsByInstanceId_0().size() > 0) {
                ArrayList<LinkedEntity> broaders = new ArrayList<>();
                for (CategoryIspartof ed : edmobj.getCategoryIspartofsByInstanceId_0()) {
                    broaders.add(retrieveLinkedEntity(ed.getCategory1InstanceId()));
                }
                o.setBroader(broaders);
            }

            if (edmobj.getCategoryIspartofsByInstanceId().size() > 0) {
                ArrayList<LinkedEntity> narrowers = new ArrayList<>();
                for (CategoryIspartof ed : edmobj.getCategoryIspartofsByInstanceId()) {
                    narrowers.add(retrieveLinkedEntity(ed.getCategory2InstanceId()));
                }
                o.setNarrower(narrowers);
            }

            o = (org.epos.eposdatamodel.Category) VersioningStatusAPI.retrieveVersion(o);

            return o;
        }
        return null;
    }

    @Override
    public List<org.epos.eposdatamodel.Category> retrieveAll() {
        List<Category> list = getDbaccess().getAllFromDB(Category.class);
        List<org.epos.eposdatamodel.Category> returnList = new ArrayList<>();
        for(Category item : list){
            returnList.add(retrieve(item.getInstanceId()));
        }
        return returnList;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        List<Category> elementList = getDbaccess().getOneFromDBByInstanceId(instanceId, Category.class);
        if(elementList!=null && !elementList.isEmpty()) {
            Category edmobj = elementList.get(0);

            LinkedEntity o = new LinkedEntity();
            o.setInstanceId(edmobj.getInstanceId());
            o.setMetaId(edmobj.getMetaId());
            o.setUid(edmobj.getUid());
            o.setEntityType(EntityNames.CATEGORY.name());

            return o;
        }
        return null;
    }

}
