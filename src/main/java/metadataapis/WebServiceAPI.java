package metadataapis;

import abstractapis.AbstractAPI;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import commonapis.*;
import model.*;
import org.epos.eposdatamodel.ContactPoint;
import org.epos.eposdatamodel.Documentation;
import org.epos.eposdatamodel.LinkedEntity;
import relationsapi.CategoryRelationsAPI;
import relationsapi.ContactPointRelationsAPI;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class WebServiceAPI extends AbstractAPI<org.epos.eposdatamodel.WebService> {

    public WebServiceAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.WebService obj) {

        List<Webservice> returnList = getDbaccess().getOneFromDB(
                obj.getInstanceId(),
                obj.getMetaId(),
                obj.getUid(),
                obj.getVersionId(),
                getEdmClass());

        if(!returnList.isEmpty()){
            obj.setInstanceId(returnList.get(0).getInstanceId());
            obj.setMetaId(returnList.get(0).getMetaId());
            obj.setUid(returnList.get(0).getUid());
            obj.setVersionId(returnList.get(0).getVersionId());
        }

        obj = (org.epos.eposdatamodel.WebService) VersioningStatusAPI.checkVersion(obj);

        Webservice edmobj = new Webservice();

        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setName(obj.getName());
        edmobj.setDescription(obj.getDescription());
        edmobj.setEntrypoint(obj.getEntryPoint());
        edmobj.setKeywords(obj.getKeywords());
        edmobj.setLicense(obj.getLicense());
        edmobj.setAaaitypes(obj.getAaaiTypes());

        if (obj.getDatePublished() != null)
            edmobj.setDatapublished(Timestamp.valueOf(obj.getDatePublished()));
        if (obj.getDateModified() != null)
            edmobj.setDatamodified(Timestamp.valueOf(obj.getDateModified()));

        /** PUBLISHER **/
        if (obj.getProvider() != null) {
            List<DataproductPublisher> dataproductPublisherList = getDbaccess().getAllFromDB(DataproductPublisher.class);
            OrganizationAPI organizationAPI = new OrganizationAPI("Organization", Organization.class);
            List<Organization> list = dbaccess.getOneFromDBByInstanceId(obj.getProvider().getInstanceId(),Organization.class);
            Organization organization1 = null;
            if(list.isEmpty()){
                LinkedEntity le = organizationAPI.create(obj.getProvider());
                organization1 = (Organization) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Organization.class).get(0);
            } else {
                organization1 = list.get(0);
            }

            edmobj.setProvider(organization1.getInstanceId());
        }


        if(returnList.isEmpty()) getDbaccess().createObject(edmobj);
        else getDbaccess().updateObject(edmobj);

        /** CATEGORY **/
        if (obj.getCategory() != null && !obj.getCategory().isEmpty())
            CategoryRelationsAPI.createRelation(edmobj,obj);

        /** CONTACTPOINT **/
        if (obj.getContactPoint() != null && !obj.getContactPoint().isEmpty())
            ContactPointRelationsAPI.createRelation(edmobj,obj);

        /** DOCUMENTATION **/
        if(!obj.getDocumentation().isEmpty()){
            for(Documentation documentation : obj.getDocumentation()) {
                JsonObject documentationObj = new JsonObject();
                documentationObj.addProperty("Title", documentation.getTitle());
                documentationObj.addProperty("Description", documentation.getDescription());
                documentationObj.addProperty("Uri", documentation.getUri());
                String doc = new Gson().toJson(documentationObj);
                createInnerElement(ElementType.DOCUMENTATION, doc, edmobj);
            }
        }

        /** SPATIAL **/
        if (obj.getSpatialExtent() != null && !obj.getSpatialExtent().isEmpty()) {
            List<WebserviceSpatial> webserviceSpatialList = getDbaccess().getAllFromDB(WebserviceSpatial.class);
            for(WebserviceSpatial item : webserviceSpatialList){
                if(item.getWebserviceInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            SpatialAPI spatialAPI = new SpatialAPI("Spatial", Spatial.class);
            for(org.epos.eposdatamodel.Location location : obj.getSpatialExtent()){
                List<Spatial> list = dbaccess.getOneFromDBByInstanceId(location.getInstanceId(),Spatial.class);
                Spatial spatial = null;
                if(list.isEmpty()){
                    LinkedEntity le = spatialAPI.create(location);
                    spatial = (Spatial) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Spatial.class).get(0);
                } else {
                    spatial = list.get(0);
                }
                WebserviceSpatial pi = new WebserviceSpatial();
                pi.setWebserviceByWebserviceInstanceId(edmobj);
                pi.setWebserviceInstanceId(edmobj.getInstanceId());
                pi.setSpatialInstanceId(spatial.getInstanceId());
                pi.setSpatialBySpatialInstanceId(spatial);
                dbaccess.createObject(pi);
            }
        }

        /** TEMPORAL **/
        if (obj.getTemporalExtent() != null && !obj.getTemporalExtent().isEmpty()) {
            List<WebserviceTemporal> equipmentTemporalList = getDbaccess().getAllFromDB(WebserviceTemporal.class);
            for(WebserviceTemporal item : equipmentTemporalList){
                if(item.getWebserviceInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            TemporalAPI temporalAPI = new TemporalAPI("Temporal", Temporal.class);
            for(org.epos.eposdatamodel.PeriodOfTime periodOfTime : obj.getTemporalExtent()){
                List<Temporal> list = dbaccess.getOneFromDBByInstanceId(periodOfTime.getInstanceId(),Temporal.class);
                Temporal temporal = null;
                if(list.isEmpty()){
                    LinkedEntity le = temporalAPI.create(periodOfTime);
                    temporal = (Temporal) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Temporal.class).get(0);
                } else {
                    temporal = list.get(0);
                }
                WebserviceTemporal pi = new WebserviceTemporal();
                pi.setWebserviceByWebserviceInstanceId(edmobj);
                pi.setWebserviceInstanceId(edmobj.getInstanceId());
                pi.setTemporalInstanceId(temporal.getInstanceId());
                pi.setTemporalByTemporalInstanceId(temporal);
                dbaccess.createObject(pi);
            }
        }

        /** TODO: SUPPORTEDOPERATION **/
        if (obj.getSupportedOperation() != null && !obj.getSupportedOperation().isEmpty()) {
            List<OperationWebservice> operationWebserviceList = getDbaccess().getAllFromDB(OperationWebservice.class);
            for(OperationWebservice item : operationWebserviceList){
                if(item.getOperationInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            OperationAPI webServiceAPI = new OperationAPI("Operation", Operation.class);
            for(org.epos.eposdatamodel.Operation operation : obj.getSupportedOperation()){
                List<Operation> list = dbaccess.getOneFromDBByInstanceId(operation.getInstanceId(),Operation.class);
                Operation operation1 = null;
                if(list.isEmpty()){
                    LinkedEntity le = webServiceAPI.create(operation);
                    operation1 = (Operation) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Operation.class).get(0);
                } else {
                    operation1 = list.get(0);
                }
                OperationWebservice pi = new OperationWebservice();
                pi.setOperationByOperationInstanceId(operation1);
                pi.setOperationInstanceId(operation1.getInstanceId());
                pi.setWebserviceInstanceId(edmobj.getInstanceId());
                pi.setWebserviceByWebserviceInstanceId(edmobj);

                dbaccess.createObject(pi);
            }
        }

        if (obj.getRelation() != null && !obj.getRelation().isEmpty()) {
            for(LinkedEntity le : obj.getRelation()){
                WebserviceRelation pi = new WebserviceRelation();
                pi.setResourceEntity(EntityNames.valueOf(le.getEntityType()).name());
                pi.setEntityInstanceId(le.getInstanceId());
                pi.setWebserviceByWebserviceInstanceId(edmobj);
                pi.setWebserviceInstanceId(edmobj.getInstanceId());

                dbaccess.createObject(pi);
            }
        }


        return new LinkedEntity().entityType(entityName)
                    .instanceId(edmobj.getInstanceId())
                    .metaId(edmobj.getMetaId())
                    .uid(edmobj.getUid());

    }

    private void createInnerElement(ElementType elementType, String value, Webservice edmobj){
        org.epos.eposdatamodel.Element element = new org.epos.eposdatamodel.Element();
        element.setType(elementType);
        element.setValue(value);
        ElementAPI api = new ElementAPI("Element", Element.class);
        LinkedEntity le = api.create(element);
        List<Element> el = dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Element.class);
        WebserviceElement ce = new WebserviceElement();
        ce.setWebserviceByWebserviceInstanceId(edmobj);
        ce.setWebserviceInstanceId(edmobj.getInstanceId());
        ce.setElementByElementInstanceId(el.get(0));
        ce.setElementInstanceId(el.get(0).getInstanceId());
        dbaccess.createObject(ce);
    }


    @Override
    public org.epos.eposdatamodel.WebService retrieve(String instanceId) {
        Webservice edmobj = (Webservice) getDbaccess().getOneFromDBByInstanceId(instanceId, Webservice.class).get(0);

        org.epos.eposdatamodel.WebService o = new org.epos.eposdatamodel.WebService();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setDateModified(
                edmobj.getDatamodified() != null ? edmobj.getDatamodified().toLocalDateTime() : null
        );
        o.setDatePublished(
                edmobj.getDatapublished() != null ? edmobj.getDatapublished().toLocalDateTime() : null
        );
        o.setDescription(edmobj.getDescription());
        o.setEntryPoint(edmobj.getEntrypoint());
        o.setLicense(edmobj.getLicense());
        o.setName(edmobj.getName());
        o.setAaaiTypes(edmobj.getAaaitypes());

        if(edmobj.getWebserviceCategoriesByInstanceId().size()>0) {
            for(WebserviceCategory ed : edmobj.getWebserviceCategoriesByInstanceId()) {
                CategoryAPI api = new CategoryAPI("Category", Category.class);
                org.epos.eposdatamodel.Category cp = api.retrieve(ed.getCategoryInstanceId());
                o.addCategory(cp);
            }
        }

        if(edmobj.getWebserviceContactpointsByInstanceId().size()>0) {
            for(WebserviceContactpoint ed : edmobj.getWebserviceContactpointsByInstanceId()) {
                ContactPointAPI api = new ContactPointAPI("ContactPoint", Contactpoint.class);
                ContactPoint cp = api.retrieve(ed.getContactpointInstanceId());
                o.addContactPoint(cp);
            }
        }

        if(edmobj.getProvider()!=null) {
            OrganizationAPI api = new OrganizationAPI("Organization", Organization.class);
            o.setProvider(api.retrieve(edmobj.getProvider()));
        }

        if(edmobj.getWebserviceElementsByInstanceId().size()>0) {
            for(WebserviceElement ed : edmobj.getWebserviceElementsByInstanceId()) {
                Element el = ed.getElementByElementInstanceId();
                if(el.getType().equals(ElementType.PAGEURL)) {
                    JsonObject doc = new Gson().fromJson(el.getValue(), JsonObject.class);
                    Documentation documentation = new Documentation();
                    documentation.setTitle(doc.get("Title").getAsString());
                    documentation.setDescription(doc.get("Description").getAsString());
                    documentation.setUri(doc.get("Uri").getAsString());
                    o.addDocumentation(documentation);
                }
            }
        }

        if(edmobj.getWebserviceSpatialsByInstanceId().size()>0) {
            for(WebserviceSpatial ed : edmobj.getWebserviceSpatialsByInstanceId()) {
                SpatialAPI api = new SpatialAPI("Spatial", Spatial.class);
                org.epos.eposdatamodel.Location cp = api.retrieve(ed.getSpatialInstanceId());
                o.addSpatialExtentItem(cp);
            }
        }

        if(edmobj.getWebserviceTemporalsByInstanceId().size()>0) {
            for(WebserviceTemporal ed : edmobj.getWebserviceTemporalsByInstanceId()) {
                TemporalAPI api = new TemporalAPI("Temporal", Temporal.class);
                org.epos.eposdatamodel.PeriodOfTime cp = api.retrieve(ed.getTemporalInstanceId());
                o.addTemporalExtent(cp);
            }
        }

        if(edmobj.getOperationWebservicesByInstanceId().size()>0) {
            for(OperationWebservice ed : edmobj.getOperationWebservicesByInstanceId()) {
                OperationAPI api = new OperationAPI("Operation", Operation.class);
                org.epos.eposdatamodel.Operation cp = api.retrieve(ed.getOperationInstanceId());
                o.addSupportedOperation(cp);
            }
        }
        /** TODO: RELATION **/

        return o;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        Webservice edmobj = (Webservice) getDbaccess().getOneFromDBByInstanceId(instanceId, Webservice.class).get(0);

        LinkedEntity o = new LinkedEntity();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setEntityType(EntityNames.WEBSERVICE.name());

        return o;
    }

}
