package apis;

import model.Category;
import model.CategoryIspartof;
import model.CategoryScheme;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class CategorySchemeAPI extends AbstractAPI<org.epos.eposdatamodel.CategoryScheme> {

    public CategorySchemeAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.CategoryScheme obj) {
        CategoryScheme edmobj = null;

        if(obj.getInstanceId()!=null) {
            List<CategoryScheme> returnList = getDbaccess().getOneFromDBByInstanceId(obj.getInstanceId(), getEdmClass());

            if (!returnList.isEmpty()) {
                return update(obj);
            }
            else {
                edmobj = new CategoryScheme();
            }
        } else {
            edmobj = new CategoryScheme();
        }

        edmobj.setInstanceId(UUID.randomUUID().toString());
        edmobj.setMetaId(UUID.randomUUID().toString());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setName(Optional.ofNullable(obj.getTitle()).orElse(""));
        edmobj.setDescription(Optional.ofNullable(obj.getDescription()).orElse(""));
        edmobj.setCode(Optional.ofNullable(obj.getCode()).orElse(""));
        edmobj.setColor(Optional.ofNullable(obj.getColor()).orElse(""));
        edmobj.setHomepage(Optional.ofNullable(obj.getHomepage()).orElse(""));
        edmobj.setOrderitemnumber(Optional.ofNullable(obj.getOrderitemnumber()).orElse(""));
        edmobj.setLogo(Optional.ofNullable(obj.getLogo()).orElse(""));

        getDbaccess().createObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                .instanceId(edmobj.getInstanceId())
                .metaId(edmobj.getMetaId())
                .uid(edmobj.getUid());
    }

    @Override
    public LinkedEntity update(org.epos.eposdatamodel.CategoryScheme obj) {
        CategoryScheme edmobj = null;

        if(obj.getInstanceId()!=null) {
            List<CategoryScheme> returnList = getDbaccess().getOneFromDBByInstanceId(obj.getInstanceId(), getEdmClass());

            if (!returnList.isEmpty()) {
                edmobj = returnList.get(0);
            }
            else {
                edmobj = new CategoryScheme();
            }
        } else {
            edmobj = new CategoryScheme();
        }

        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(obj.getUid());
        edmobj.setName(Optional.ofNullable(obj.getTitle()).orElse(""));
        edmobj.setDescription(Optional.ofNullable(obj.getDescription()).orElse(""));
        edmobj.setCode(Optional.ofNullable(obj.getCode()).orElse(""));
        edmobj.setColor(Optional.ofNullable(obj.getColor()).orElse(""));
        edmobj.setHomepage(Optional.ofNullable(obj.getHomepage()).orElse(""));
        edmobj.setOrderitemnumber(Optional.ofNullable(obj.getOrderitemnumber()).orElse(""));
        edmobj.setLogo(Optional.ofNullable(obj.getLogo()).orElse(""));

        getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                .instanceId(edmobj.getInstanceId())
                .metaId(edmobj.getMetaId())
                .uid(edmobj.getUid());
    }

    @Override
    public org.epos.eposdatamodel.CategoryScheme retrieve(String instanceId) {
        CategoryScheme edmobj = (CategoryScheme) getDbaccess().getOneFromDBByInstanceId(instanceId, CategoryScheme.class).get(0);
        org.epos.eposdatamodel.CategoryScheme o = new org.epos.eposdatamodel.CategoryScheme();


        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setTitle(edmobj.getName());
        o.setDescription(edmobj.getDescription());
        o.setCode(edmobj.getCode());
        o.setHomepage(edmobj.getHomepage());
        o.setLogo(edmobj.getLogo());
        o.setColor(edmobj.getColor());
        o.setOrderitemnumber(edmobj.getOrderitemnumber());

        return o;
    }


}
