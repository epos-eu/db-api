package abstractapis;

import commonapis.*;
import dao.EposDataModelDAO;
import metadataapis.*;
import model.*;
import org.epos.eposdatamodel.EPOSDataModelEntity;
import org.epos.eposdatamodel.LinkedEntity;

import javax.sound.sampled.Line;
import java.util.List;

public abstract class AbstractAPI<T> {

    protected Class<?> edmClass;
    protected String entityName;

    protected EposDataModelDAO dbaccess = new EposDataModelDAO();

    public AbstractAPI(String entityName, Class<?> edmClass){
        this.edmClass = edmClass;
        this.entityName = entityName;
    }

    public EposDataModelDAO getDbaccess(){
        return dbaccess;
    }

    public void setEdmClass(Class<?> edmClass){
        this.edmClass = edmClass;
    }

    public Class<?> getEdmClass(){
        return edmClass;
    }

    public void setEntityName(String entityName){
        this.entityName = entityName;
    }

    public  String getEntityName() {
        return entityName;
    }

    public abstract LinkedEntity create(T obj);

    public abstract T retrieve(String instanceId);

    public abstract LinkedEntity retrieveLinkedEntity(String instanceId);

    public static AbstractAPI retrieveAPI(String entityType, Class<?> edmClass){
        AbstractAPI api = null;

        switch(EntityNames.valueOf(entityType)){
            case PERSON:
                edmClass = Person.class;
                api = new PersonAPI(entityType, edmClass);
                break;
            case MAPPING:
                edmClass = Mapping.class;
                api = new MappingAPI(entityType, edmClass);
                break;
            case CATEGORY:
                edmClass = Category.class;
                api = new CategoryAPI(entityType, edmClass);
                break;
            case FACILITY:
                edmClass = Facility.class;
                api = new FacilityAPI(entityType, edmClass);
                break;
            case EQUIPMENT:
                edmClass = Equipment.class;
                api = new EquipmentAPI(entityType, edmClass);
                break;
            case OPERATION:
                edmClass = Operation.class;
                api = new OperationAPI(entityType, edmClass);
                break;
            case WEBSERVICE:
                edmClass = Webservice.class;
                api = new WebServiceAPI(entityType, edmClass);
                break;
            case DATAPRODUCT:
                edmClass = Dataproduct.class;
                api = new DataProductAPI(entityType, edmClass);
                break;
            case CONTACTPOINT:
                edmClass = Contactpoint.class;
                api = new ContactPointAPI(entityType, edmClass);
                break;
            case DISTRIBUTION:
                edmClass = Distribution.class;
                api = new DistributionAPI(entityType, edmClass);
                break;
            case ORGANIZATION:
                edmClass = Organization.class;
                api = new OrganizationAPI(entityType, edmClass);
                break;
            case CATEGORYSCHEME:
                edmClass = CategoryScheme.class;
                api = new CategorySchemeAPI(entityType, edmClass);
                break;
            case SOFTWARESOURCECODE:
                edmClass = SoftwareSourceCode.class;
                api = new SoftwareSourceCodeAPI(entityType, edmClass);
                break;
            case SOFTWAREAPPLICATION:
                edmClass = SoftwareApplication.class;
                api = new SoftwareApplicationAPI(entityType, edmClass);
                break;
            case ADDRESS:
                edmClass = Address.class;
                api = new AddressAPI(entityType, edmClass);
                break;
            case ELEMENT:
                edmClass = Element.class;
                api = new ElementAPI(entityType, edmClass);
                break;
            case LOCATION:
                edmClass = Spatial.class;
                api = new SpatialAPI(entityType, edmClass);
                break;
            case PERIODOFTIME:
                edmClass = Temporal.class;
                api = new TemporalAPI(entityType, edmClass);
                break;
            case IDENTIFIER:
                edmClass = Identifier.class;
                api = new IdentifierAPI(entityType, edmClass);
                break;
            case QUANTITATIVEVALUE:
                edmClass = QuantitativeValue.class;
                api = new QuantitativeValueAPI(entityType, edmClass);
                break;
            case DOCUMENTATION:
                edmClass = Element.class;
                api = new DocumentationAPI(entityType, edmClass);
                break;
        }
        return api;
    }



}
