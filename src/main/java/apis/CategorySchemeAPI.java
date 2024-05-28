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
                edmobj = returnList.get(0);
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
        return null;
    }

    @Override
    public org.epos.eposdatamodel.CategoryScheme retrieve(String instanceId) {
        return null;
    }


}
