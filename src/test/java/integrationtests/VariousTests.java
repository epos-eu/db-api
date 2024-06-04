package integrationtests;

import com.google.gson.Gson;
import metadataapis.*;
import model.Dataproduct;
import model.StatusType;
import org.epos.eposdatamodel.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VariousTests {

    public static void datasetTest(){
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
        dp.setDescription(List.of("Test dataproduct description"));
        dp.addTitle("DataProductTitle");
        dp.setIdentifier(List.of(identifier));
        dp.setStatus(StatusType.DRAFT);

        DataProductAPI api = new DataProductAPI(EntityNames.DATAPRODUCT.name(), Dataproduct.class);
        LinkedEntity le = api.create(dp);

        System.out.println(api.retrieve(le.getInstanceId()));
    }

    public static void softwaresTest(){
        Category cat = new Category();
        cat.setName("SoftwareCategory1");
        cat.setUid("SoftwareCategory1");
        cat.setDescription("SoftwareCategory1Descrsiption");

        Identifier identifier = new Identifier();
        identifier.setIdentifier("SoftwareAPplicationIdentifier");
        identifier.setType("DOS");
        identifier.setUid("SoftwareAPplicationIdentifierDOS");

        Parameter parameter = new Parameter();
        parameter.setEncodingFormat("application/json");
        parameter.setAction(Parameter.ActionEnum.OBJECT);
        parameter.setConformsTo("nothing");

        ContactPoint cp = new ContactPoint();
        cp.setRole("Manager");
        cp.setUid("ManagerUID");
        cp.setEmail(List.of("email@email.email"));
        cp.setLanguage(List.of("en"));
        cp.setTelephone(List.of("293214124"));
        cp.setStatus(StatusType.DRAFT);

        Operation op = new Operation();
        op.setUid("Operation/UI");
        op.setTemplate("template");
        op.setMethod("GET");
        op.setReturns(List.of("application/json"));
        op.setStatus(StatusType.DRAFT);

        OperationAPI api3 = new OperationAPI(EntityNames.OPERATION.name(), model.Operation.class);
        api3.create(op);

        LinkedEntity leop = new LinkedEntity();
        leop.setUid("Operation/UI");
        leop.setEntityType(EntityNames.OPERATION.name());

        SoftwareApplication sa = new SoftwareApplication();
        sa.setUid("SoftwareApplicationUID");
        sa.setCategory(List.of(cat));
        sa.setDescription("SoftwareApplicationDescription");
        sa.setName("SoftwareApplicationName");
        sa.setIdentifier(List.of(identifier));
        sa.setStatus(StatusType.DRAFT);
        sa.setParameter(new ArrayList<>());
        sa.getParameter().add(parameter);
        sa.setRelation(List.of(leop));
        sa.setContactPoint(List.of(cp));


        SoftwareSourceCode ssc = new SoftwareSourceCode();
        ssc.setUid("SoftwareSourceCodeUID");
        ssc.setCategory(List.of(cat));
        ssc.setDescription("SoftwareApplicationDescription");
        ssc.setName("SoftwareApplicationName");
        ssc.setIdentifier(List.of(identifier));
        ssc.setStatus(StatusType.DRAFT);
        ssc.setSoftwareVersion("1.0");
        ssc.keywords("key1,key2,key3");
        ssc.setContactPoint(List.of(cp));

        SoftwareApplicationAPI api = new SoftwareApplicationAPI(EntityNames.SOFTWAREAPPLICATION.name(), SoftwareApplication.class);
        LinkedEntity le = api.create(sa);

        SoftwareSourceCodeAPI api2 = new SoftwareSourceCodeAPI(EntityNames.SOFTWARESOURCECODE.name(), SoftwareSourceCode.class);
        LinkedEntity le2 = api2.create(ssc);

        System.out.println(api.retrieve(le.getInstanceId()));
        System.out.println(api2.retrieve(le2.getInstanceId()));
    }

    public static void main(String[] args) {
        softwaresTest();
        //datasetTest();
    }
}
