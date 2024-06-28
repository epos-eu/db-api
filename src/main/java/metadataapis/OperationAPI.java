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

        EposDataModelEntityIDAPI.addEntityToEDMEntityID(obj.getMetaId(), entityName);

        Operation edmobj = new Operation();

        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());

        getDbaccess().updateObject(edmobj);

        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setMethod(obj.getMethod());
        edmobj.setTemplate(obj.getTemplate());

        /** MAPPING TODO: CHECK IF DELETE **/
        if (obj.getMapping() != null && !obj.getMapping().isEmpty()) {
            MappingAPI mappingAPI = new MappingAPI(EntityNames.MAPPING.name(), Mapping.class);
            List<Mapping> mappingList = new ArrayList<>();
            for(LinkedEntity mapping : obj.getMapping()){
                List<Mapping> list = dbaccess.getOneFromDBByInstanceId(mapping.getInstanceId(),Mapping.class);
                Mapping mapping1 = null;
                if(list.isEmpty()){
                    LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(mapping);
                    mapping1 = (Mapping) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Mapping.class).get(0);
                } else {
                    mapping1 = list.get(0);
                }
                mappingList.add(mapping1);
            }
            edmobj.setMappingsByInstanceId(mappingList);
        }

        if (obj.getWebservice() != null && !obj.getWebservice().isEmpty()) {
            List<OperationWebservice> operationWebserviceList = getDbaccess().getAllFromDB(OperationWebservice.class);
            for(OperationWebservice item : operationWebserviceList){
                if(item.getOperationInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            WebServiceAPI webServiceAPI = new WebServiceAPI(EntityNames.WEBSERVICE.name(), Webservice.class);
            edmobj.setOperationWebservicesByInstanceId(new ArrayList<>());
            for(LinkedEntity webService : obj.getWebservice()){
                List<Webservice> list = dbaccess.getOneFromDBByInstanceId(webService.getInstanceId(),Webservice.class);
                Webservice webservice = null;
                if(list.isEmpty()){
                    LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(webService);
                    webservice = (Webservice) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Webservice.class).get(0);
                } else {
                    webservice = list.get(0);
                }
                OperationWebservice pi = new OperationWebservice();
                pi.setOperationByOperationInstanceId(edmobj);
                pi.setOperationInstanceId(edmobj.getInstanceId());
                pi.setWebserviceInstanceId(webservice.getInstanceId());
                pi.setWebserviceByWebserviceInstanceId(webservice);

                edmobj.getOperationWebservicesByInstanceId().add(pi);

                dbaccess.updateObject(pi);
            }
        }

        edmobj.setOperationElementsByInstanceId(new ArrayList<>());
        /** RETURNS **/
        if(obj.getReturns()!=null && !obj.getReturns().isEmpty()){
            for(String returns : obj.getReturns()) {
                createInnerElement(ElementType.RETURNS, returns, edmobj);
            }
        }

        getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                    .instanceId(edmobj.getInstanceId())
                    .metaId(edmobj.getMetaId())
                    .uid(edmobj.getUid());

    }

    private void createInnerElement(ElementType elementType, String value, Operation edmobj){
        org.epos.eposdatamodel.Element element = new org.epos.eposdatamodel.Element();
        element.setType(elementType);
        element.setValue(value);
        ElementAPI api = new ElementAPI(EntityNames.ELEMENT.name(), Element.class);
        LinkedEntity le = api.create(element);
        List<Element> el = dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Element.class);
        OperationElement ce = new OperationElement();
        ce.setOperationByOperationInstanceId(edmobj);
        ce.setOperationInstanceId(edmobj.getInstanceId());
        ce.setElementByElementInstanceId(el.get(0));
        ce.setElementInstanceId(el.get(0).getInstanceId());

        edmobj.getOperationElementsByInstanceId().add(ce);

        dbaccess.updateObject(ce);
    }


    @Override
    public org.epos.eposdatamodel.Operation retrieve(String instanceId) {
        List<Operation> elementList = getDbaccess().getOneFromDBByInstanceId(instanceId, Operation.class);
        if(elementList!=null && !elementList.isEmpty()) {
            Operation edmobj = elementList.get(0);
            org.epos.eposdatamodel.Operation o = new org.epos.eposdatamodel.Operation();
            o.setInstanceId(edmobj.getInstanceId());
            o.setMetaId(edmobj.getMetaId());
            o.setUid(edmobj.getUid());
            o.setMethod(edmobj.getMethod());
            o.setTemplate(edmobj.getTemplate());

            if (edmobj.getOperationElementsByInstanceId().size() > 0) {
                for (OperationElement ed : edmobj.getOperationElementsByInstanceId()) {
                    Element el = ed.getElementByElementInstanceId();
                    if (el.getType().equals(ElementType.RETURNS)) {
                        o.addReturns(el.getValue());
                    }
                }
            }

            if (edmobj.getMappingsByInstanceId().size() > 0) {
                for (Mapping ed : edmobj.getMappingsByInstanceId()) {

                    MappingAPI api = new MappingAPI(EntityNames.MAPPING.name(), Mapping.class);
                    o.addMapping(api.retrieveLinkedEntity(ed.getInstanceId()));
                }
            }

            o = (org.epos.eposdatamodel.Operation) VersioningStatusAPI.retrieveVersion(o);

            return o;
        }
        return null;
    }

    @Override
    public List<org.epos.eposdatamodel.Operation> retrieveAll() {
        List<Operation> list = getDbaccess().getAllFromDB(Operation.class);
        List<org.epos.eposdatamodel.Operation> returnList = new ArrayList<>();
        for(Operation item : list){
            returnList.add(retrieve(item.getInstanceId()));
        }
        return returnList;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        List<Operation> elementList = getDbaccess().getOneFromDBByInstanceId(instanceId, Operation.class);
        if(elementList!=null && !elementList.isEmpty()) {
            Operation edmobj = elementList.get(0);
            LinkedEntity o = new LinkedEntity();
            o.setInstanceId(edmobj.getInstanceId());
            o.setMetaId(edmobj.getMetaId());
            o.setUid(edmobj.getUid());
            o.setEntityType(EntityNames.OPERATION.name());

            return o;
        }
        return null;
    }

}
