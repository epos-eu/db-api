package integrationtests;

import apis.CategoryAPI;
import model.Category;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.List;

public class LocalTest {

    public static void main(String[] args){
        org.epos.eposdatamodel.Category cat = new org.epos.eposdatamodel.Category();
        cat.setName("CIAO");
        cat.setDescription("TESTING");
        cat.setUid("HOLA/CHICO");
        cat.setBroader(List.of("notexistingcatidbroader"));
        cat.setNarrower(List.of("notexistingcatidnarrower"));
        cat.setInScheme("notexistingcatidinscheme");
        CategoryAPI catAPI = new CategoryAPI("Category", Category.class);
        LinkedEntity le = catAPI.create(cat);
        System.out.println(le.getInstanceId()+" "+le.getMetaId()+" "+le.getUid()+" "+le.getEntityType());
    }
}
