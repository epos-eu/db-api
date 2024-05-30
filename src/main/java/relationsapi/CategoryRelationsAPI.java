package relationsapi;

import abstractapis.AbstractRelationsAPI;
import metadataapis.CategoryAPI;
import model.*;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.List;

public class CategoryRelationsAPI extends AbstractRelationsAPI {

    public static void createRelation(Equipment edmobj, org.epos.eposdatamodel.Equipment obj){
        List<EquipmentCategory> facilityCategoryList = getDbaccess().getAllFromDB(EquipmentCategory.class);
        for(EquipmentCategory item : facilityCategoryList){
            if(item.getEquipmentInstanceId().equals(obj.getInstanceId())){
                getDbaccess().deleteObject(item);
            }
        }
        CategoryAPI categoryAPI = new CategoryAPI("Category", Category.class);
        for(org.epos.eposdatamodel.Category category : obj.getCategory()){
            List<Category> list = dbaccess.getOneFromDBByInstanceId(category.getInstanceId(),Category.class);
            Category category1 = null;
            if(list.isEmpty()){
                LinkedEntity le = categoryAPI.create(category);
                category1 = (Category) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Category.class).get(0);
            } else {
                category1 = list.get(0);
            }
            EquipmentCategory pi = new EquipmentCategory();
            pi.setEquipmentByEquipmentInstanceId(edmobj);
            pi.setEquipmentInstanceId(edmobj.getInstanceId());
            pi.setCategoryInstanceId(category1.getInstanceId());
            pi.setCategoryByCategoryInstanceId(category1);
        }
    }

    public static void createRelation(Facility edmobj, org.epos.eposdatamodel.Facility obj) {
        List<FacilityCategory> facilityCategoryList = getDbaccess().getAllFromDB(FacilityCategory.class);
        for (FacilityCategory item : facilityCategoryList) {
            if (item.getFacilityInstanceId().equals(obj.getInstanceId())) {
                getDbaccess().deleteObject(item);
            }
        }
        CategoryAPI categoryAPI = new CategoryAPI("Category", Category.class);
        for (org.epos.eposdatamodel.Category category : obj.getCategory()) {
            List<Category> list = dbaccess.getOneFromDBByInstanceId(category.getInstanceId(), Category.class);
            Category category1 = null;
            if (list.isEmpty()) {
                LinkedEntity le = categoryAPI.create(category);
                category1 = (Category) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Category.class).get(0);
            } else {
                category1 = list.get(0);
            }
            FacilityCategory pi = new FacilityCategory();
            pi.setFacilityByFacilityInstanceId(edmobj);
            pi.setFacilityInstanceId(edmobj.getInstanceId());
            pi.setCategoryInstanceId(category1.getInstanceId());
            pi.setCategoryByCategoryInstanceId(category1);
        }
    }

    public static void createRelation(Dataproduct edmobj, org.epos.eposdatamodel.DataProduct obj) {
        List<DataproductCategory> dataproductCategoryList = getDbaccess().getAllFromDB(DataproductCategory.class);
        for (DataproductCategory item : dataproductCategoryList) {
            if (item.getDataproductInstanceId().equals(obj.getInstanceId())) {
                getDbaccess().deleteObject(item);
            }
        }
        CategoryAPI categoryAPI = new CategoryAPI("Category", Category.class);
        for (org.epos.eposdatamodel.Category category : obj.getCategory()) {
            List<Category> list = dbaccess.getOneFromDBByInstanceId(category.getInstanceId(), Category.class);
            Category category1 = null;
            if (list.isEmpty()) {
                LinkedEntity le = categoryAPI.create(category);
                category1 = (Category) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Category.class).get(0);
            } else {
                category1 = list.get(0);
            }
            DataproductCategory pi = new DataproductCategory();
            pi.setDataproductByDataproductInstanceId(edmobj);
            pi.setDataproductInstanceId(edmobj.getInstanceId());
            pi.setCategoryInstanceId(category1.getInstanceId());
            pi.setCategoryByCategoryInstanceId(category1);
        }
    }
}
