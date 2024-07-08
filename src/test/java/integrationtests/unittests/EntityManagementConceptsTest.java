package integrationtests.unittests;

import abstractapis.AbstractAPI;
import integrationtests.TestcontainersLifecycle;
import metadataapis.EntityNames;
import model.StatusType;
import org.epos.eposdatamodel.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EntityManagementConceptsTest extends TestcontainersLifecycle {

    @Test
    @Order(1)
    public void testCreateAndGet() {

        List<EPOSDataModelEntity> classes = new ArrayList<>();


        LinkedEntity linkedEntity = new LinkedEntity();
        linkedEntity.setEntityType(EntityNames.CATEGORYSCHEME.name());
        linkedEntity.setUid("schemetest");

        LinkedEntity narrowerLe1 = new LinkedEntity();
        narrowerLe1.setEntityType(EntityNames.CATEGORY.name());
        narrowerLe1.setUid("narrower1");

        LinkedEntity narrowerLe2 = new LinkedEntity();
        narrowerLe2.setEntityType(EntityNames.CATEGORY.name());
        narrowerLe2.setUid("narrower2");

        LinkedEntity broaderLe = new LinkedEntity();
        broaderLe.setEntityType(EntityNames.CATEGORY.name());
        broaderLe.setUid("category1");


        Category narrower1 = new Category();
        narrower1.setName("narrower1");
        narrower1.setUid("narrower1");
        narrower1.setStatus(StatusType.PUBLISHED);
        narrower1.setInScheme(linkedEntity);
        narrower1.addBroader(broaderLe);
        classes.add(narrower1);

        Category narrower2 = new Category();
        narrower2.setName("narrower2");
        narrower2.setUid("narrower2");
        narrower2.setStatus(StatusType.PUBLISHED);
        narrower2.setInScheme(linkedEntity);
        narrower1.addBroader(broaderLe);
        classes.add(narrower2);

        Category category1 = new Category();
        category1.setName("Test1");
        category1.setUid("category1");
        category1.setStatus(StatusType.PUBLISHED);
        category1.setInScheme(linkedEntity);
        category1.addNarrower(narrowerLe1);
        category1.addNarrower(narrowerLe2);
        classes.add(category1);


        Category category2 = new Category();
        category2.setName("Test2");
        category2.setUid("category2");
        category2.setStatus(StatusType.PUBLISHED);
        category2.setInScheme(linkedEntity);
        classes.add(category2);

        CategoryScheme categoryScheme = new CategoryScheme();
        categoryScheme.setTitle("SchemeTest");
        categoryScheme.setUid("schemetest");
        categoryScheme.setStatus(StatusType.PUBLISHED);
        classes.add(categoryScheme);


        for(EPOSDataModelEntity entity : classes){
            AbstractAPI api = AbstractAPI.retrieveAPI(entity.getClass().getSimpleName().toUpperCase());
            api.create(entity);
        }



        AbstractAPI api = AbstractAPI.retrieveAPI(EntityNames.CATEGORY.name());
        List<Category> categories = api.retrieveAll();
        assertEquals(4,categories.size());

    }


}
