package relationsapi;

import abstractapis.AbstractRelationsAPI;
import commonapis.LinkedEntityAPI;
import metadataapis.CategoryAPI;
import model.*;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRelationsAPI extends AbstractRelationsAPI {

    public static void createRelation(Equipment edmobj, org.epos.eposdatamodel.Equipment obj){
        List<EquipmentCategory> facilityCategoryList = getDbaccess().getAllFromDB(EquipmentCategory.class);
        for(EquipmentCategory item : facilityCategoryList){
            if(item.getCategoryInstanceId().equals(obj.getInstanceId())){
                getDbaccess().deleteObject(item);
            }
        }
        edmobj.setEquipmentCategoriesByInstanceId(new ArrayList<>());
        for(LinkedEntity category : obj.getCategory()){
            List<Category> list = dbaccess.getOneFromDB(
                    Optional.ofNullable(category.getInstanceId()).orElse(null),
                    Optional.ofNullable(category.getMetaId()).orElse(null),
                    Optional.ofNullable(category.getUid()).orElse(null),
                    null,
                    Category.class);
            Category category1 = null;
            if(list.isEmpty()){
                LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(category);
                category1 = (Category) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Category.class).get(0);
            } else {
                category1 = list.get(0);
            }
            EquipmentCategory pi = new EquipmentCategory();
            pi.setEquipmentByEquipmentInstanceId(edmobj);
            pi.setEquipmentInstanceId(edmobj.getInstanceId());
            pi.setCategoryInstanceId(category1.getInstanceId());
            pi.setCategoryByCategoryInstanceId(category1);

            edmobj.getEquipmentCategoriesByInstanceId().add(pi);

            dbaccess.createObject(pi);
        }
    }

    public static void createRelation(Facility edmobj, org.epos.eposdatamodel.Facility obj) {
        List<FacilityCategory> facilityCategoryList = getDbaccess().getAllFromDB(FacilityCategory.class);
        for (FacilityCategory item : facilityCategoryList) {
            if (item.getFacilityInstanceId().equals(obj.getInstanceId())) {
                getDbaccess().deleteObject(item);
            }
        }
        edmobj.setFacilityCategoriesByInstanceId(new ArrayList<>());
        for (LinkedEntity category : obj.getCategory()) {
            List<Category> list = dbaccess.getOneFromDB(
                    Optional.ofNullable(category.getInstanceId()).orElse(null),
                    Optional.ofNullable(category.getMetaId()).orElse(null),
                    Optional.ofNullable(category.getUid()).orElse(null),
                    null,
                    Category.class);
            Category category1 = null;
            if (list.isEmpty()) {
                LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(category);
                category1 = (Category) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Category.class).get(0);
            } else {
                category1 = list.get(0);
            }
            FacilityCategory pi = new FacilityCategory();
            pi.setFacilityByFacilityInstanceId(edmobj);
            pi.setFacilityInstanceId(edmobj.getInstanceId());
            pi.setCategoryInstanceId(category1.getInstanceId());
            pi.setCategoryByCategoryInstanceId(category1);

            edmobj.getFacilityCategoriesByInstanceId().add(pi);

            dbaccess.createObject(pi);
        }
    }

    public static void createRelation(Dataproduct edmobj, org.epos.eposdatamodel.DataProduct obj) {
        List<DataproductCategory> dataproductCategoryList = getDbaccess().getAllFromDB(DataproductCategory.class);
        for (DataproductCategory item : dataproductCategoryList) {
            if (item.getDataproductInstanceId().equals(obj.getInstanceId())) {
                getDbaccess().deleteObject(item);
            }
        }
        edmobj.setDataproductCategoriesByInstanceId(new ArrayList<>());
        for (LinkedEntity category : obj.getCategory()) {
            List<Category> list = dbaccess.getOneFromDB(
                    Optional.ofNullable(category.getInstanceId()).orElse(null),
                    Optional.ofNullable(category.getMetaId()).orElse(null),
                    Optional.ofNullable(category.getUid()).orElse(null),
                    null,
                    Category.class);
            Category category1 = null;
            if (list.isEmpty()) {
                LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(category);
                category1 = (Category) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Category.class).get(0);
            } else {
                category1 = list.get(0);
            }
            DataproductCategory pi = new DataproductCategory();
            pi.setDataproductByDataproductInstanceId(edmobj);
            pi.setDataproductInstanceId(edmobj.getInstanceId());
            pi.setCategoryInstanceId(category1.getInstanceId());
            pi.setCategoryByCategoryInstanceId(category1);
            edmobj.getDataproductCategoriesByInstanceId().add(pi);
            dbaccess.createObject(pi);
        }
    }

    public static void createRelation(Webservice edmobj, org.epos.eposdatamodel.WebService obj) {
        List<WebserviceCategory> webserviceCategoryList = getDbaccess().getAllFromDB(WebserviceCategory.class);
        for (WebserviceCategory item : webserviceCategoryList) {
            if (item.getWebserviceInstanceId().equals(obj.getInstanceId())) {
                getDbaccess().deleteObject(item);
            }
        }
        CategoryAPI categoryAPI = new CategoryAPI("Category", Category.class);
        edmobj.setWebserviceCategoriesByInstanceId(new ArrayList<>());
        for (LinkedEntity category : obj.getCategory()) {
            List<Category> list = dbaccess.getOneFromDB(
                    Optional.ofNullable(category.getInstanceId()).orElse(null),
                    Optional.ofNullable(category.getMetaId()).orElse(null),
                    Optional.ofNullable(category.getUid()).orElse(null),
                    null,
                    Category.class);
            Category category1 = null;
            if (list.isEmpty()) {
                LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(category);
                category1 = (Category) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Category.class).get(0);
            } else {
                category1 = list.get(0);
            }
            WebserviceCategory pi = new WebserviceCategory();
            pi.setWebserviceByWebserviceInstanceId(edmobj);
            pi.setWebserviceInstanceId(edmobj.getInstanceId());
            pi.setCategoryInstanceId(category1.getInstanceId());
            pi.setCategoryByCategoryInstanceId(category1);
            edmobj.getWebserviceCategoriesByInstanceId().add(pi);
            dbaccess.createObject(pi);
        }
    }

    public static void createRelation(SoftwareSourceCode edmobj, org.epos.eposdatamodel.SoftwareSourceCode obj) {
        List<SoftwaresourcecodeCategory> softwaresourcecodeCategoryList = getDbaccess().getAllFromDB(SoftwaresourcecodeCategory.class);
        for (SoftwaresourcecodeCategory item : softwaresourcecodeCategoryList) {
            if (item.getSoftwaresourcecodeInstanceId().equals(obj.getInstanceId())) {
                getDbaccess().deleteObject(item);
            }
        }
        edmobj.setSoftwaresourcecodeCategoriesByInstanceId(new ArrayList<>());
        for (LinkedEntity category : obj.getCategory()) {
            List<Category> list = dbaccess.getOneFromDB(
                    Optional.ofNullable(category.getInstanceId()).orElse(null),
                    Optional.ofNullable(category.getMetaId()).orElse(null),
                    Optional.ofNullable(category.getUid()).orElse(null),
                    null,
                    Category.class);
            Category category1 = null;
            if (list.isEmpty()) {
                LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(category);
                category1 = (Category) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Category.class).get(0);
            } else {
                category1 = list.get(0);
            }
            SoftwaresourcecodeCategory pi = new SoftwaresourcecodeCategory();
            pi.setSoftwaresourcecodeBySoftwaresourcecodeInstanceId(edmobj);
            pi.setSoftwaresourcecodeInstanceId(edmobj.getInstanceId());
            pi.setCategoryInstanceId(category1.getInstanceId());
            pi.setCategoryByCategoryInstanceId(category1);
            edmobj.getSoftwaresourcecodeCategoriesByInstanceId().add(pi);
            dbaccess.createObject(pi);
        }
    }

    public static void createRelation(SoftwareApplication edmobj, org.epos.eposdatamodel.SoftwareApplication obj) {
        List<SoftwareapplicationCategory> softwareapplicationCategoryList = getDbaccess().getAllFromDB(SoftwareapplicationCategory.class);
        for (SoftwareapplicationCategory item : softwareapplicationCategoryList) {
            if (item.getSoftwareapplicationInstanceId().equals(obj.getInstanceId())) {
                getDbaccess().deleteObject(item);
            }
        }
        CategoryAPI categoryAPI = new CategoryAPI("Category", Category.class);
        edmobj.setSoftwareapplicationCategoriesByInstanceId(new ArrayList<>());
        for (LinkedEntity category : obj.getCategory()) {
            List<Category> list = dbaccess.getOneFromDB(
                    Optional.ofNullable(category.getInstanceId()).orElse(null),
                    Optional.ofNullable(category.getMetaId()).orElse(null),
                    Optional.ofNullable(category.getUid()).orElse(null),
                    null,
                    Category.class);
            Category category1 = null;
            if (list.isEmpty()) {
                LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(category);
                category1 = (Category) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Category.class).get(0);
            } else {
                category1 = list.get(0);
            }
            SoftwareapplicationCategory pi = new SoftwareapplicationCategory();
            pi.setSoftwareapplicationBySoftwareapplicationInstanceId(edmobj);
            pi.setSoftwareapplicationInstanceId(edmobj.getInstanceId());
            pi.setCategoryInstanceId(category1.getInstanceId());
            pi.setCategoryByCategoryInstanceId(category1);
            edmobj.getSoftwareapplicationCategoriesByInstanceId().add(pi);
            dbaccess.createObject(pi);
        }
    }
}
