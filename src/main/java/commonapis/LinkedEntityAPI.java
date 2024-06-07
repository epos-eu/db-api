package commonapis;

import abstractapis.AbstractAPI;
import dao.EposDataModelDAO;
import metadataapis.*;
import model.*;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.List;
import java.util.Optional;

public class LinkedEntityAPI {

    public static LinkedEntity createFromLinkedEntity(LinkedEntity obj){
        AbstractAPI api = null;
        Class<?> edmClass = null;
        switch(EntityNames.valueOf(obj.getEntityType())){
            case PERSON:
                edmClass = Person.class;
                api = new PersonAPI(obj.getEntityType(), edmClass);
                break;
            case MAPPING:
                edmClass = Mapping.class;
                api = new MappingAPI(obj.getEntityType(), edmClass);
                break;
            case CATEGORY:
                edmClass = Category.class;
                api = new CategoryAPI(obj.getEntityType(), edmClass);
                break;
            case FACILITY:
                edmClass = Facility.class;
                api = new FacilityAPI(obj.getEntityType(), edmClass);
                break;
            case EQUIPMENT:
                edmClass = Equipment.class;
                api = new EquipmentAPI(obj.getEntityType(), edmClass);
                break;
            case OPERATION:
                edmClass = Operation.class;
                api = new OperationAPI(obj.getEntityType(), edmClass);
                break;
            case WEBSERVICE:
                edmClass = Webservice.class;
                api = new WebServiceAPI(obj.getEntityType(), edmClass);
                break;
            case DATAPRODUCT:
                edmClass = Dataproduct.class;
                api = new DataProductAPI(obj.getEntityType(), edmClass);
                break;
            case CONTACTPOINT:
                edmClass = Contactpoint.class;
                api = new ContactPointAPI(obj.getEntityType(), edmClass);
                break;
            case DISTRIBUTION:
                edmClass = Distribution.class;
                api = new DistributionAPI(obj.getEntityType(), edmClass);
                break;
            case ORGANIZATION:
                edmClass = Organization.class;
                api = new OrganizationAPI(obj.getEntityType(), edmClass);
                break;
            case CATEGORYSCHEME:
                edmClass = CategoryScheme.class;
                api = new CategorySchemeAPI(obj.getEntityType(), edmClass);
                break;
            case SOFTWARESOURCECODE:
                edmClass = SoftwareSourceCode.class;
                api = new SoftwareSourceCodeAPI(obj.getEntityType(), edmClass);
                break;
            case SOFTWAREAPPLICATION:
                edmClass = SoftwareApplication.class;
                api = new SoftwareApplicationAPI(obj.getEntityType(), edmClass);
                break;
            case ADDRESS:
                edmClass = Address.class;
                api = new AddressAPI(obj.getEntityType(), edmClass);
                break;
            case ELEMENT:
                edmClass = Element.class;
                api = new ElementAPI(obj.getEntityType(), edmClass);
                break;
            case LOCATION:
                edmClass = Spatial.class;
                api = new SpatialAPI(obj.getEntityType(), edmClass);
                break;
            case PERIODOFTIME:
                edmClass = Temporal.class;
                api = new TemporalAPI(obj.getEntityType(), edmClass);
                break;
            case IDENTIFIER:
                edmClass = Identifier.class;
                api = new IdentifierAPI(obj.getEntityType(), edmClass);
                break;
        }

        List<Versioningstatus> returnList = getDbaccess().getOneFromDB(
                Optional.ofNullable(obj.getInstanceId()).orElse(null),
                Optional.ofNullable(obj.getMetaId()).orElse(null),
                Optional.ofNullable(obj.getUid()).orElse(null),
                null,
                Versioningstatus.class
        );

        if(!returnList.isEmpty() && api!=null){
            Versioningstatus edmobj = returnList.get(0);
            return api.create(edmobj.getInstanceId());
        }
        else{
            return null;
        }

    }


    public static Object retrieveFromLinkedEntity(LinkedEntity obj) {

        AbstractAPI api = null;
        Class<?> edmClass = null;
        switch(EntityNames.valueOf(obj.getEntityType())){
            case PERSON:
                edmClass = Person.class;
                api = new PersonAPI(obj.getEntityType(), edmClass);
                break;
            case MAPPING:
                edmClass = Mapping.class;
                api = new MappingAPI(obj.getEntityType(), edmClass);
                break;
            case CATEGORY:
                edmClass = Category.class;
                api = new CategoryAPI(obj.getEntityType(), edmClass);
                break;
            case FACILITY:
                edmClass = Facility.class;
                api = new FacilityAPI(obj.getEntityType(), edmClass);
                break;
            case EQUIPMENT:
                edmClass = Equipment.class;
                api = new EquipmentAPI(obj.getEntityType(), edmClass);
                break;
            case OPERATION:
                edmClass = Operation.class;
                api = new OperationAPI(obj.getEntityType(), edmClass);
                break;
            case WEBSERVICE:
                edmClass = Webservice.class;
                api = new WebServiceAPI(obj.getEntityType(), edmClass);
                break;
            case DATAPRODUCT:
                edmClass = Dataproduct.class;
                api = new DataProductAPI(obj.getEntityType(), edmClass);
                break;
            case CONTACTPOINT:
                edmClass = Contactpoint.class;
                api = new ContactPointAPI(obj.getEntityType(), edmClass);
                break;
            case DISTRIBUTION:
                edmClass = Distribution.class;
                api = new DistributionAPI(obj.getEntityType(), edmClass);
                break;
            case ORGANIZATION:
                edmClass = Organization.class;
                api = new OrganizationAPI(obj.getEntityType(), edmClass);
                break;
            case CATEGORYSCHEME:
                edmClass = CategoryScheme.class;
                api = new CategorySchemeAPI(obj.getEntityType(), edmClass);
                break;
            case SOFTWARESOURCECODE:
                edmClass = SoftwareSourceCode.class;
                api = new SoftwareSourceCodeAPI(obj.getEntityType(), edmClass);
                break;
            case SOFTWAREAPPLICATION:
                edmClass = SoftwareApplication.class;
                api = new SoftwareApplicationAPI(obj.getEntityType(), edmClass);
                break;
            case ADDRESS:
                edmClass = Address.class;
                api = new AddressAPI(obj.getEntityType(), edmClass);
                break;
            case ELEMENT:
                edmClass = Element.class;
                api = new ElementAPI(obj.getEntityType(), edmClass);
                break;
            case LOCATION:
                edmClass = Spatial.class;
                api = new SpatialAPI(obj.getEntityType(), edmClass);
                break;
            case PERIODOFTIME:
                edmClass = Temporal.class;
                api = new TemporalAPI(obj.getEntityType(), edmClass);
                break;
            case IDENTIFIER:
                edmClass = Identifier.class;
                api = new IdentifierAPI(obj.getEntityType(), edmClass);
                break;
        }

        List<Versioningstatus> returnList = getDbaccess().getOneFromDB(
                Optional.ofNullable(obj.getInstanceId()).orElse(null),
                Optional.ofNullable(obj.getMetaId()).orElse(null),
                Optional.ofNullable(obj.getUid()).orElse(null),
                null,
                Versioningstatus.class
        );

        if(!returnList.isEmpty() && api!=null){
            Versioningstatus edmobj = returnList.get(0);
            return api.retrieve(edmobj.getInstanceId());
        }
        else{
            return null;
        }
    }

    private static EposDataModelDAO getDbaccess() {
        return new EposDataModelDAO();
    }


}
