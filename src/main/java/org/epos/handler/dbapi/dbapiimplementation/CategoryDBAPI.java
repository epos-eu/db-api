package org.epos.handler.dbapi.dbapiimplementation;

import org.epos.eposdatamodel.Category;
import org.epos.eposdatamodel.CategoryScheme;
import org.epos.eposdatamodel.LinkedEntity;
import org.epos.handler.dbapi.model.*;
import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import static org.epos.handler.dbapi.util.DBUtil.getOneFromDB;

public class CategoryDBAPI extends AbstractDBAPI<Category> {


	public CategoryDBAPI() {
		super("EDMCategory", EDMCategory.class);
	}

	@Override
	public LinkedEntity save(Category eposDataModelObject, EntityManager em, String edmInstanceId) {

		//search for a existing instance placeholder to be populated
		EDMCategory edmObject = getOneFromDB(em, EDMCategory.class,
				"EDMCategory.findByUid", "UID", eposDataModelObject.getUid());

		if (edmObject != null &&
				(eposDataModelObject.getUid() != null && eposDataModelObject.getUid().equals(edmObject.getUid()))) {
			em.merge(edmObject);
		} else {
			edmObject = new EDMCategory();
			edmObject.setId(edmInstanceId);
			em.persist(edmObject);
		}
		edmObject.setUid(eposDataModelObject.getUid());
		edmObject.setName(eposDataModelObject.getName());
		edmObject.setDescription(eposDataModelObject.getDescription());

		EDMCategoryScheme edmCategoryScheme = null;

		if (eposDataModelObject.getInScheme() != null) {
			edmCategoryScheme = getOneFromDB(em, EDMCategoryScheme.class, "EDMCategoryScheme.findByUid",
					"UID", eposDataModelObject.getInScheme());
			if (edmCategoryScheme == null) {
				CategoryScheme scheme = new CategoryScheme();
				scheme.setUid(eposDataModelObject.getInScheme());
				CategorySchemeDBAPI schemeAPI = new CategorySchemeDBAPI();
				schemeAPI.save(scheme);

				edmCategoryScheme = getOneFromDB(em, EDMCategoryScheme.class, "EDMCategoryScheme.findByUid",
						"UID", eposDataModelObject.getInScheme());
			}
			edmObject.setScheme(edmCategoryScheme.getId());
		}

		if (eposDataModelObject.getBroader() != null) {
			for (String categoryName : eposDataModelObject.getBroader()) {
				EDMCategory edmCategory = getOneFromDB(em, EDMCategory.class, "EDMCategory.findByUid",
						"UID", categoryName);

				if (edmCategory == null) {
					edmCategory = new EDMCategory();
					edmCategory.setScheme(edmCategoryScheme.getId());
					edmCategory.setUid(categoryName);
					edmCategory.setId(UUID.randomUUID().toString());
					em.persist(edmCategory);
					
					EDMIspartofCategory edmIspartOfCategory = new EDMIspartofCategory();
					edmIspartOfCategory.setCategory1Id(edmCategory.getId());
					edmIspartOfCategory.setCategoryByCategory1Id(edmCategory);
					edmIspartOfCategory.setCategory2Id(edmInstanceId);
					edmIspartOfCategory.setCategoryByCategory2Id(edmObject);
					
					em.persist(edmIspartOfCategory);
				} else {
					EDMIspartofCategory edmIspartOfCategory = new EDMIspartofCategory();
					edmIspartOfCategory.setCategory1Id(edmCategory.getId());
					edmIspartOfCategory.setCategoryByCategory1Id(edmCategory);
					edmIspartOfCategory.setCategory2Id(edmInstanceId);
					edmIspartOfCategory.setCategoryByCategory2Id(edmObject);
					
					em.merge(edmIspartOfCategory);
				}

				
			}
		}

		if (eposDataModelObject.getNarrower() != null) {
			for (String categoryName : eposDataModelObject.getNarrower()) {
				EDMCategory edmCategory = getOneFromDB(em, EDMCategory.class, "EDMCategory.findByUid",
						"UID", categoryName);

				if (edmCategory == null) {
					edmCategory = new EDMCategory();
					edmCategory.setScheme(edmCategoryScheme.getId());
					edmCategory.setUid(categoryName);
					edmCategory.setId(UUID.randomUUID().toString());
					em.persist(edmCategory);
					
					EDMIspartofCategory edmIspartOfCategory = new EDMIspartofCategory();
					edmIspartOfCategory.setCategory2Id(edmCategory.getId());
					edmIspartOfCategory.setCategoryByCategory2Id(edmCategory);
					edmIspartOfCategory.setCategory1Id(edmInstanceId);
					edmIspartOfCategory.setCategoryByCategory1Id(edmObject);

					em.persist(edmIspartOfCategory);
				}else {
					EDMIspartofCategory edmIspartOfCategory = new EDMIspartofCategory();
					edmIspartOfCategory.setCategory2Id(edmCategory.getId());
					edmIspartOfCategory.setCategoryByCategory2Id(edmCategory);
					edmIspartOfCategory.setCategory1Id(edmInstanceId);
					edmIspartOfCategory.setCategoryByCategory1Id(edmObject);

					em.merge(edmIspartOfCategory);
				}


				
			}

		}

		return new LinkedEntity().entityType(entityString)
				.instanceId(edmInstanceId)
				.uid(eposDataModelObject.getUid());
	}

	@Override
	protected Category mapFromDB(Object edmObject) {
		Category o = new Category();

		EDMCategory edm = (EDMCategory) edmObject;

		o.setInstanceId(edm.getId());
		o.setUid(edm.getUid());
		o.setInScheme(edm.getScheme());
		o.setName(edm.getName());
		o.setDescription(edm.getDescription());

		edm.getIspartofCategoriesById_0().isEmpty();
		edm.getIspartofCategoriesById().isEmpty();

		if(edm.getIspartofCategoriesById_0().size()>0) {
			ArrayList<String> broaders = new ArrayList<>();
			for(EDMIspartofCategory ed : ((List<EDMIspartofCategory>)edm.getIspartofCategoriesById_0())) {
				broaders.add(ed.getCategory1Id());
			}
			o.setBroader(broaders);
		}

		if(edm.getIspartofCategoriesById().size()>0) {
			ArrayList<String> narrowers = new ArrayList<>();
			for(EDMIspartofCategory ed : ((List<EDMIspartofCategory>)edm.getIspartofCategoriesById())) {
				narrowers.add(ed.getCategory2Id());
			}
			o.setNarrower(narrowers);
		}

		return o;
	}

}
