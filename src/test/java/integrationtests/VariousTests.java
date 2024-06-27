package integrationtests;

import com.google.gson.Gson;
import commonapis.AddressAPI;
import commonapis.DocumentationAPI;
import commonapis.QuantitativeValueAPI;
import metadataapis.*;
import model.*;
import org.eclipse.persistence.internal.jpa.rs.metadata.model.Link;
import org.epos.eposdatamodel.*;
import org.epos.eposdatamodel.Address;
import org.epos.eposdatamodel.Category;
import org.epos.eposdatamodel.Identifier;
import org.epos.eposdatamodel.Operation;
import org.epos.eposdatamodel.Person;
import org.epos.eposdatamodel.QuantitativeValue;
import org.epos.eposdatamodel.SoftwareApplication;
import org.epos.eposdatamodel.SoftwareSourceCode;
import org.epos.eposdatamodel.User;
import usermanagementapis.UserGroupManagementAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VariousTests {


    public static void checkGroups(){

        //System.out.println(UserGroupManagementAPI.addUserToGroup("07b998a8-7598-47e4-ae94-b4d21d9591d5", "admin", RoleType.ADMIN, RequestStatusType.PENDING));
        System.out.println(UserGroupManagementAPI.retrieveUserById("admin"));
    }

    public static void checkUsers(){
        User user = new User();
        user.setLastName("test");
        user.setFirstName("test");
        user.setEmail("test");
        user.setAuthIdentifier("test");
        user.setIsAdmin(true);

        UserGroupManagementAPI.createUser(user);

        System.out.println(UserGroupManagementAPI.retrieveUserById("test"));
    }

    public static void datasetTest(){
        Category cat = new Category();
        cat.setName("DataProductCategory");
        cat.setUid("DataProductCategory1");
        cat.setDescription("DataProductCategoryDescription");

        LinkedEntity catLe = new LinkedEntity();
        catLe.setUid("DataProductCategory1");
        catLe.setEntityType(EntityNames.CATEGORY.name());

        Identifier identifier = new Identifier();
        identifier.setIdentifier("DataProductIdentifier");
        identifier.setType("DOI");
        identifier.setUid("DataProductIdentifierDOI");

        DataProduct dp = new DataProduct();
        dp.setType("Dataproduct");
        dp.setAccessRight("Dataproduct accessrights");
        dp.setCategory(List.of(catLe));
        dp.setUid("DataProductUID");
        dp.setDescription(List.of("Test dataproduct description"));
        dp.addTitle("DataProductTitle");
        dp.setStatus(StatusType.DRAFT);

        DataProductAPI api = new DataProductAPI(EntityNames.DATAPRODUCT.name(), Dataproduct.class);
        LinkedEntity le = api.create(dp);

        System.out.println(api.retrieve(le.getInstanceId()));
    }

    public static void softwaresTest(){
        CategoryAPI categoryAPI = new CategoryAPI(EntityNames.CATEGORY.name(), model.Category.class);
        ContactPointAPI contactPointAPI = new ContactPointAPI(EntityNames.CONTACTPOINT.name(), model.Contactpoint.class);


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
        sa.setCategory(List.of(categoryAPI.create(cat)));
        sa.setDescription("SoftwareApplicationDescription");
        sa.setName("SoftwareApplicationName");
        sa.setStatus(StatusType.DRAFT);
        sa.setParameter(new ArrayList<>());
        sa.setRelation(List.of(leop));
        sa.setContactPoint(List.of(contactPointAPI.create(cp)));


        SoftwareSourceCode ssc = new SoftwareSourceCode();
        ssc.setUid("SoftwareSourceCodeUID");
        ssc.setCategory(List.of(categoryAPI.create(cat)));
        ssc.setDescription("SoftwareApplicationDescription");
        ssc.setName("SoftwareApplicationName");
        ssc.setStatus(StatusType.DRAFT);
        ssc.setSoftwareVersion("1.0");
        ssc.keywords("key1,key2,key3");
        ssc.setContactPoint(List.of(contactPointAPI.create(cp)));

        SoftwareApplicationAPI api = new SoftwareApplicationAPI(EntityNames.SOFTWAREAPPLICATION.name(), SoftwareApplication.class);
        LinkedEntity le = api.create(sa);

        SoftwareSourceCodeAPI api2 = new SoftwareSourceCodeAPI(EntityNames.SOFTWARESOURCECODE.name(), SoftwareSourceCode.class);
        LinkedEntity le2 = api2.create(ssc);

        System.out.println(api.retrieve(le.getInstanceId()));
        System.out.println(api2.retrieve(le2.getInstanceId()));
    }

    public static void quantitativeValue(){
        QuantitativeValue qv = new QuantitativeValue();
        qv.setValue("val");
        qv.setUnit("unit");
        qv.setStatus(StatusType.DRAFT);

        QuantitativeValueAPI api = new QuantitativeValueAPI(EntityNames.QUANTITATIVEVALUE.name(), model.QuantitativeValue.class);
        LinkedEntity le = api.create(qv);

        System.out.println(api.retrieve(le.getInstanceId()));
    }

    public static void checkProblems(){

        Address address = new Address();
        address.setUid("test");

        Person p = new Person();
        p.setUid("TEST");

        PersonAPI personAPI = new PersonAPI(EntityNames.PERSON.name(), model.Person.class);
        LinkedEntity le2 = personAPI.create(p);

        AddressAPI addressAPI = new AddressAPI(EntityNames.ADDRESS.name(), model.Address.class);
        LinkedEntity le = addressAPI.create(address);
        System.out.println(addressAPI.retrieve(le.getInstanceId()));

        address.setStreet("test");
        address.setPostalCode("test");
        address.setLocality("test");
        address.setCountry("test");

        le = addressAPI.create(address);

        System.out.println(personAPI.retrieve(le2.getInstanceId()));
        System.out.println(addressAPI.retrieve(le.getInstanceId()));

    }

    public static void checkWebServiceExample(){
        WebService webService = new WebService();
        webService.setStatus(StatusType.DRAFT);
        webService.setDescription("My description of webservice");
        webService.setName("My name of webservice");
        webService.setEditorId("admin");

        WebServiceAPI api = new WebServiceAPI(EntityNames.WEBSERVICE.name(), Webservice.class);
        LinkedEntity le = api.create(webService);

        System.out.println(api.retrieve(le.getInstanceId()));
    }

    public static void checkWebServiceExampleUpdate(){
        WebService webService = new WebService();
        webService.setStatus(StatusType.DRAFT);
        webService.setDescription("My description of webservice update");
        webService.setName("My name of webservice");
        webService.setEditorId("admin");
        webService.setUid("Webservice/ab9a8b83-4476-470e-87d9-599b97547981");
        webService.setInstanceId("00463a24-b0c4-43f5-a166-2afe6f66179e");
        webService.setMetaId("8140b55d-4381-4c93-abcc-4cc1caaa2744");

        WebServiceAPI api = new WebServiceAPI(EntityNames.WEBSERVICE.name(), Webservice.class);
        LinkedEntity le = api.create(webService);

        System.out.println(api.retrieve(le.getInstanceId()));
    }

    public static void addUserToGroupCheck(){
        String userid = "admin";
        String groupid = "0da052a0-34ab-4aaa-8c6d-61358a60f35d";
        RoleType role = RoleType.ADMIN;
        RequestStatusType statusType = RequestStatusType.ACCEPTED;

        System.out.println(UserGroupManagementAPI.addUserToGroup(groupid,userid,role,statusType));
    }

    public static void main(String[] args) {
        //softwaresTest();
        //datasetTest();
        //quantitativeValue();
        //checkProblems();
        //checkUsers();
        //checkGroups();
        //checkWebServiceExample();
        //checkWebServiceExampleUpdate();
        addUserToGroupCheck();
    }
}
