package apis;

import dao.EposDataModelDAO;
import model.*;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.*;

public class CategoryAPI extends AbstractAPI<org.epos.eposdatamodel.Category> {

    public CategoryAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.Category obj) {
        Category edmobj = null;

        if(obj.getInstanceId()!=null) {
            List<Category> returnList = getDbaccess().getOneFromDBByInstanceId(obj.getInstanceId(), getEdmClass());
            if (!returnList.isEmpty()) {
                return update(obj);
            }
        } else {
            edmobj = new Category();
        }

        edmobj.setInstanceId(UUID.randomUUID().toString());
        edmobj.setMetaId(UUID.randomUUID().toString());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setName(Optional.ofNullable(obj.getName()).orElse(""));
        edmobj.setDescription(Optional.ofNullable(obj.getDescription()).orElse(""));

        getDbaccess().createObject(edmobj);

        if(Objects.nonNull(obj.getBroader())) createBroaders(obj.getBroader(), edmobj);
        if(Objects.nonNull(obj.getNarrower())) createNarrowers(obj.getNarrower(), edmobj);
        if(Objects.nonNull(obj.getInScheme())) createInscheme(obj.getInScheme(), edmobj);

        return new LinkedEntity().entityType(entityName)
                .instanceId(edmobj.getInstanceId())
                .metaId(edmobj.getMetaId())
                .uid(edmobj.getUid());
    }

    private void createInscheme(String inschemeid, Category edmobj){
        CategorySchemeAPI api = new CategorySchemeAPI("CategoryScheme", CategoryScheme.class);
        org.epos.eposdatamodel.CategoryScheme childObj = new org.epos.eposdatamodel.CategoryScheme();
        childObj.setInstanceId(inschemeid);
        LinkedEntity le = api.create(childObj);
        edmobj.setInScheme(inschemeid);

    }

    private void createBroaders(List<String> broaders, Category edmobj){
        CategoryAPI api = new CategoryAPI("Category", Category.class);
        for(String broader : broaders) {
            org.epos.eposdatamodel.Category childObj = new org.epos.eposdatamodel.Category();
            childObj.setInstanceId(broader);
            LinkedEntity le = api.create(childObj);

            CategoryIspartof categoryIspartof = new CategoryIspartof();
            System.out.println(((Category) getDbaccess().getOneFromDBByInstanceId(le.getInstanceId(), Category.class).get(0)).getInstanceId());
            System.out.println(edmobj.getInstanceId());
            categoryIspartof.setCategory1InstanceId(((Category) getDbaccess().getOneFromDBByInstanceId(le.getInstanceId(), Category.class).get(0)).getInstanceId());
            categoryIspartof.setCategory2InstanceId(edmobj.getInstanceId());
            categoryIspartof.setCategoryByCategory1InstanceId((Category) getDbaccess().getOneFromDBByInstanceId(le.getInstanceId(), Category.class).get(0));
            categoryIspartof.setCategoryByCategory2InstanceId(edmobj);

            getDbaccess().createObject(categoryIspartof);
        }
    }

    private void createNarrowers(List<String> narrowers, Category edmobj){
        CategoryAPI api = new CategoryAPI("Category", Category.class);
        for(String narrower : narrowers) {
            org.epos.eposdatamodel.Category childObj = new org.epos.eposdatamodel.Category();
            childObj.setInstanceId(narrower);
            LinkedEntity le = api.create(childObj);

            CategoryIspartof categoryIspartof = new CategoryIspartof();
            categoryIspartof.setCategory1InstanceId(edmobj.getInstanceId());
            categoryIspartof.setCategory2InstanceId(((Category) getDbaccess().getOneFromDBByInstanceId(le.getInstanceId(), Category.class).get(0)).getInstanceId());
            categoryIspartof.setCategoryByCategory1InstanceId(edmobj);
            categoryIspartof.setCategoryByCategory2InstanceId((Category) getDbaccess().getOneFromDBByInstanceId(le.getInstanceId(), Category.class).get(0));

            getDbaccess().createObject(categoryIspartof);
        }
    }

    @Override
    public LinkedEntity update(org.epos.eposdatamodel.Category obj) {

        Category edmobj = null;

        if(obj.getInstanceId()!=null) {
            List<Category> returnList = getDbaccess().getOneFromDBByInstanceId(obj.getInstanceId(), getEdmClass());
            if (!returnList.isEmpty()) {
                edmobj = returnList.get(0);
            }
        } else {
            return create(obj);
        }

        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(obj.getUid());
        edmobj.setName(Optional.ofNullable(obj.getName()).orElse(""));
        edmobj.setDescription(Optional.ofNullable(obj.getDescription()).orElse(""));

        getDbaccess().updateObject(edmobj);

        if(Objects.nonNull(obj.getBroader())) createBroaders(obj.getBroader(), edmobj);
        if(Objects.nonNull(obj.getNarrower())) createNarrowers(obj.getNarrower(), edmobj);
        if(Objects.nonNull(obj.getInScheme())) createInscheme(obj.getInScheme(), edmobj);

        return new LinkedEntity().entityType(entityName)
                .instanceId(edmobj.getInstanceId())
                .metaId(edmobj.getMetaId())
                .uid(edmobj.getUid());
    }

    @Override
    public org.epos.eposdatamodel.Category retrieve(String instanceId) {
        Category edmobj = (Category) getDbaccess().getOneFromDBByInstanceId(instanceId, Category.class).get(0);

        org.epos.eposdatamodel.Category o = new org.epos.eposdatamodel.Category();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setName(edmobj.getName());
        o.setDescription(edmobj.getDescription());
        o.setInScheme(edmobj.getInScheme());


        edmobj.getCategoryIspartofsByInstanceId_0().isEmpty();
        edmobj.getCategoryIspartofsByInstanceId().isEmpty();

        if(edmobj.getCategoryIspartofsByInstanceId_0().size()>0) {
            ArrayList<String> broaders = new ArrayList<>();
            for(CategoryIspartof ed : edmobj.getCategoryIspartofsByInstanceId_0()) {
                broaders.add(ed.getCategory1InstanceId());
            }
            o.setBroader(broaders);
        }

        if(edmobj.getCategoryIspartofsByInstanceId().size()>0) {
            ArrayList<String> narrowers = new ArrayList<>();
            for(CategoryIspartof ed : edmobj.getCategoryIspartofsByInstanceId()) {
                narrowers.add(ed.getCategory2InstanceId());
            }
            o.setNarrower(narrowers);
        }

        return o;
    }

}
