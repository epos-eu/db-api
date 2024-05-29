package integrationtests;

import apis.CategoryAPI;
import model.Category;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.List;

public class LocalTest {

    public static void main(String[] args){

        org.epos.eposdatamodel.Category broader = new org.epos.eposdatamodel.Category();
        broader.setUid("BROADERUID");

        org.epos.eposdatamodel.Category narrower = new org.epos.eposdatamodel.Category();
        narrower.setUid("NARROWERUID");

        org.epos.eposdatamodel.CategoryScheme scheme = new org.epos.eposdatamodel.CategoryScheme();
        scheme.setUid("SCHEMEUID");

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
