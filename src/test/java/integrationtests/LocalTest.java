package integrationtests;

import metadataapis.CategoryAPI;
import metadataapis.EntityNames;
import model.Category;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.List;

public class LocalTest {

    public static void main(String[] args){

        LinkedEntity broader = new LinkedEntity();
        broader.setUid("BROADERUID");
        broader.setEntityType(EntityNames.CATEGORY.name());

        LinkedEntity narrower = new LinkedEntity();
        narrower.setUid("NARROWERUID");
        broader.setEntityType(EntityNames.CATEGORY.name());

        LinkedEntity scheme = new LinkedEntity();
        scheme.setUid("SCHEMEUID");
        broader.setEntityType(EntityNames.CATEGORYSCHEME.name());

        org.epos.eposdatamodel.Category cat = new org.epos.eposdatamodel.Category();
        cat.setName("CIAO");
        cat.setDescription("TESTING");
        cat.setUid("HOLA/CHICO");
        cat.setBroader(List.of(broader));
        cat.setNarrower(List.of(narrower));
        cat.setInScheme(scheme);
        CategoryAPI catAPI = new CategoryAPI("Category", Category.class);
        LinkedEntity le = catAPI.create(cat);
        System.out.println(le.getInstanceId()+" "+le.getMetaId()+" "+le.getUid()+" "+le.getEntityType());
    }
}
