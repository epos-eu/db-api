package metadataapis;

import abstractapis.AbstractAPI;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import commonapis.ElementAPI;
import commonapis.SpatialAPI;
import commonapis.TemporalAPI;
import commonapis.VersioningStatusAPI;
import model.*;
import org.epos.eposdatamodel.ContactPoint;
import org.epos.eposdatamodel.Documentation;
import org.epos.eposdatamodel.LinkedEntity;
import relationsapi.CategoryRelationsAPI;
import relationsapi.ContactPointRelationsAPI;

import java.sql.Timestamp;
import java.util.*;

public class OperationAPI extends AbstractAPI<org.epos.eposdatamodel.Operation> {

    public OperationAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.Operation obj) {

        List<Operation> returnList = getDbaccess().getOneFromDB(
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

        obj = (org.epos.eposdatamodel.Operation) VersioningStatusAPI.checkVersion(obj);

        Operation edmobj = new Operation();

        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setMethod(obj.getMethod());
        edmobj.setTemplate(obj.getTemplate());

        /** MAPPING TODO: CHECK IF DELETE **/
        if (obj.getMapping() != null && !obj.getMapping().isEmpty()) {
            MappingAPI mappingAPI = new MappingAPI("Mapping", Mapping.class);
            List<Mapping> mappingList = new ArrayList<>();
            for(org.epos.eposdatamodel.Mapping mapping : obj.getMapping()){
                List<Mapping> list = dbaccess.getOneFromDBByInstanceId(mapping.getInstanceId(),Mapping.class);
                Mapping mapping1 = null;
                if(list.isEmpty()){
                    LinkedEntity le = mappingAPI.create(mapping);
                    mapping1 = (Mapping) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Mapping.class).get(0);
                } else {
                    mapping1 = list.get(0);
                }
                mappingList.add(mapping1);
            }
            edmobj.setMappingsByInstanceId(mappingList);
        }


        if(returnList.isEmpty()) getDbaccess().createObject(edmobj);
        else getDbaccess().updateObject(edmobj);

        if (obj.getWebservice() != null && !obj.getWebservice().isEmpty()) {
            List<OperationWebservice> operationWebserviceList = getDbaccess().getAllFromDB(OperationWebservice.class);
            for(OperationWebservice item : operationWebserviceList){
                if(item.getOperationInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            WebServiceAPI webServiceAPI = new WebServiceAPI("WebService", Webservice.class);
            for(org.epos.eposdatamodel.WebService webService : obj.getWebservice()){
                List<Webservice> list = dbaccess.getOneFromDBByInstanceId(webService.getInstanceId(),Webservice.class);
                Webservice webservice = null;
                if(list.isEmpty()){
                    LinkedEntity le = webServiceAPI.create(webService);
                    webservice = (Webservice) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Webservice.class).get(0);
                } else {
                    webservice = list.get(0);
                }
                OperationWebservice pi = new OperationWebservice();
                pi.setOperationByOperationInstanceId(edmobj);
                pi.setOperationInstanceId(edmobj.getInstanceId());
                pi.setWebserviceInstanceId(webservice.getInstanceId());
                pi.setWebserviceByWebserviceInstanceId(webservice);
            }
        }

        /** RETURNS **/
        if(!obj.getReturns().isEmpty()){
            for(String returns : obj.getReturns()) {
                createInnerElement(ElementType.RETURNS, returns, edmobj);
            }
        }

        return new LinkedEntity().entityType(entityName)
                    .instanceId(edmobj.getInstanceId())
                    .metaId(edmobj.getMetaId())
                    .uid(edmobj.getUid());

    }

    private void createInnerElement(ElementType elementType, String value, Operation edmobj){
        org.epos.eposdatamodel.Element element = new org.epos.eposdatamodel.Element();
        element.setType(elementType);
        element.setValue(value);
        ElementAPI api = new ElementAPI("Element", Element.class);
        LinkedEntity le = api.create(element);
        List<Element> el = dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Element.class);
        OperationElement ce = new OperationElement();
        ce.setOperationByOperationInstanceId(edmobj);
        ce.setOperationInstanceId(edmobj.getInstanceId());
        ce.setElementByElementInstanceId(el.get(0));
        ce.setElementInstanceId(el.get(0).getInstanceId());
    }


    @Override
    public org.epos.eposdatamodel.Operation retrieve(String instanceId) {
        Operation edmobj = (Operation) getDbaccess().getOneFromDBByInstanceId(instanceId, Operation.class).get(0);

        org.epos.eposdatamodel.Operation o = new org.epos.eposdatamodel.Operation();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setMethod(edmobj.getMethod());
        o.setTemplate(edmobj.getTemplate());

        if(edmobj.getOperationElementsByInstanceId().size()>0) {
            for(OperationElement ed : edmobj.getOperationElementsByInstanceId()) {
                Element el = ed.getElementByElementInstanceId();
                if(el.getType().equals(ElementType.RETURNS)) {
                    o.addReturns(el.getValue());
                }
            }
        }

        if(edmobj.getMappingsByInstanceId().size()>0) {
            for(Mapping ed : edmobj.getMappingsByInstanceId()) {

                MappingAPI api = new MappingAPI("Mapping", Mapping.class);
                o.addMapping(api.retrieve(ed.getInstanceId()));
            }
        }

        return o;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        Operation edmobj = (Operation) getDbaccess().getOneFromDBByInstanceId(instanceId, Operation.class).get(0);

        LinkedEntity o = new LinkedEntity();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setEntityType(EntityNames.OPERATION.name());

        return o;
    }

}
