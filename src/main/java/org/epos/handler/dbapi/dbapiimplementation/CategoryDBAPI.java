package org.epos.handler.dbapi.dbapiimplementation;

import org.epos.eposdatamodel.Category;
import org.epos.eposdatamodel.CategoryScheme;
import org.epos.eposdatamodel.LinkedEntity;
import org.epos.handler.dbapi.model.*;
import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;
import static org.epos.handler.dbapi.util.DBUtil.getOneFromDB;

public class CategoryDBAPI extends AbstractDBAPI<Category> {


	public CategoryDBAPI() {
		super("categoryscheme", EDMContract.class);
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
			System.out.println("Persisting "+edmCategoryScheme);
			if (edmCategoryScheme == null) {
				CategoryScheme scheme = new CategoryScheme();
				scheme.setUid(eposDataModelObject.getInScheme());
				CategorySchemeDBAPI schemeAPI = new CategorySchemeDBAPI();
				schemeAPI.save(scheme);

				edmCategoryScheme = getOneFromDB(em, EDMCategoryScheme.class, "EDMCategoryScheme.findByUid",
						"UID", eposDataModelObject.getInScheme());
				//edmCategoryScheme = new EDMCategoryScheme();
				//edmCategoryScheme.setUid(eposDataModelObject.getInScheme());
				//edmCategoryScheme.setId(UUID.randomUUID().toString());
				//em.persist(edmCategoryScheme);
			}
			edmObject.setScheme(edmCategoryScheme.getId());
		}

		if (eposDataModelObject.getBroader() != null) {
			EDMCategory edmCategory = getOneFromDB(em, EDMCategory.class, "EDMCategory.findByUid",
					"UID", eposDataModelObject.getBroader());
			if (edmCategory == null) {
				edmCategory = new EDMCategory();
				edmCategory.setScheme(edmCategoryScheme.getId());
				edmCategory.setUid(eposDataModelObject.getBroader());
				edmCategory.setId(UUID.randomUUID().toString());
				em.persist(edmCategory);
			}

			EDMIspartofCategory edmIspartOfCategory = new EDMIspartofCategory();
			edmIspartOfCategory.setCategory1Id(edmCategory.getId());
			edmIspartOfCategory.setCategoryByCategory1Id(edmCategory);
			edmIspartOfCategory.setCategory2Id(edmInstanceId);
			edmIspartOfCategory.setCategoryByCategory2Id(edmObject);

			em.persist(edmIspartOfCategory);
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
				}


				EDMIspartofCategory edmIspartOfCategory = new EDMIspartofCategory();
				edmIspartOfCategory.setCategory2Id(edmCategory.getId());
				edmIspartOfCategory.setCategoryByCategory2Id(edmCategory);
				edmIspartOfCategory.setCategory1Id(edmInstanceId);
				edmIspartOfCategory.setCategoryByCategory1Id(edmObject);

				em.persist(edmIspartOfCategory);
			}

		}

		System.out.println(edmObject);

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

		return o;
	}

}
