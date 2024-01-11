package org.epos.handler.dbapi.dbapiimplementation;

import org.epos.eposdatamodel.Category;
import org.epos.eposdatamodel.CategoryScheme;
import org.epos.eposdatamodel.LinkedEntity;
import org.epos.handler.dbapi.model.*;
import javax.persistence.EntityManager;
import java.util.UUID;
import static org.epos.handler.dbapi.util.DBUtil.getOneFromDB;

public class CategorySchemeDBAPI extends AbstractDBAPI<CategoryScheme> {


    public CategorySchemeDBAPI() {
        super("EDMCategoryScheme", EDMCategoryScheme.class);
    }
    
    @Override
    public LinkedEntity save(CategoryScheme eposDataModelObject, EntityManager em, String edmInstanceId) {

        //search for a existing instance placeholder to be populated
        EDMCategoryScheme edmObject = getOneFromDB(em, EDMCategoryScheme.class,
                "EDMCategoryScheme.findByUid", "UID", eposDataModelObject.getUid());

        //if there's a placeholder for the entity check if is passed a specific metaid
        //only if the metaid is the same of the placeholder merge the two (the placeholder and the passed entity)
        EDMEdmEntityId edmMetaId;
        if (edmObject != null &&
                (eposDataModelObject.getUid() != null && eposDataModelObject.getUid().equals(edmObject.getUid()))) {
            em.merge(edmObject);
        } else {
            edmObject = new EDMCategoryScheme();
            edmObject.setId(edmInstanceId);
            em.persist(edmObject);

            if (eposDataModelObject.getMetaId() == null) {
                edmMetaId = new EDMEdmEntityId();
                edmMetaId.setMetaId(UUID.randomUUID().toString());
                em.persist(edmMetaId);
            } else {
                edmMetaId = getOneFromDB(em, EDMEdmEntityId.class,
                        "edmentityid.findByMetaId",
                        "METAID", eposDataModelObject.getMetaId());
                if (edmMetaId == null) {
                    edmMetaId = new EDMEdmEntityId();
                    edmMetaId.setMetaId(eposDataModelObject.getMetaId());
                    em.persist(edmMetaId);
                }
            }

        }
        edmObject.setUid(eposDataModelObject.getUid());
        edmObject.setName(eposDataModelObject.getTitle());
        edmObject.setDescription(eposDataModelObject.getDescription());

        System.out.println(edmObject);

        return new LinkedEntity().entityType(entityString)
                .instanceId(edmInstanceId)
                .uid(eposDataModelObject.getUid());
    }

    @Override
    protected CategoryScheme mapFromDB(Object edmObject) {
    	CategoryScheme o = new CategoryScheme();

    	EDMCategoryScheme edm = (EDMCategoryScheme) edmObject;


        o.setInstanceId(edm.getId());
        o.setUid(edm.getUid());
        o.setTitle(edm.getName());
        o.setDescription(edm.getDescription());

        return o;
    }

}
