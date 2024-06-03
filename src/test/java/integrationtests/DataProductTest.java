package integrationtests;

import com.google.gson.Gson;
import metadataapis.CategoryAPI;
import metadataapis.DataProductAPI;
import metadataapis.EntityNames;
import model.Dataproduct;
import model.StatusType;
import org.epos.eposdatamodel.Category;
import org.epos.eposdatamodel.DataProduct;
import org.epos.eposdatamodel.Identifier;
import org.epos.eposdatamodel.LinkedEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DataProductTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        Category cat = new Category();
        cat.setName("DataProductCategory");
        cat.setUid("DataProductCategory1");
        cat.setDescription("DataProductCategoryDescription");

        Identifier identifier = new Identifier();
        identifier.setIdentifier("DataProductIdentifier");
        identifier.setType("DOI");
        identifier.setUid("DataProductIdentifierDOI");

        DataProduct dp = new DataProduct();
        dp.setType("Dataproduct");
        dp.setAccessRight("Dataproduct accessrights");
        dp.setUid("DataProductUID");
        dp.setCategory(List.of(cat));
        dp.setDescription(List.of("Test dataproduct description"));
        dp.addTitle("DataProductTitle");
        dp.setIdentifier(List.of(identifier));
        dp.setStatus(StatusType.DRAFT);

        DataProductAPI api = new DataProductAPI(EntityNames.DATAPRODUCT.name(), Dataproduct.class);
        LinkedEntity le = api.create(dp);

        Gson gson = new Gson();

        System.out.println(api.retrieve(le.getInstanceId()));

    }
}
