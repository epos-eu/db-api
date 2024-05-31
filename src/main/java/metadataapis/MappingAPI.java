package metadataapis;

import abstractapis.AbstractAPI;
import commonapis.ElementAPI;
import commonapis.VersioningStatusAPI;
import model.*;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MappingAPI extends AbstractAPI<org.epos.eposdatamodel.Mapping> {

    public MappingAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.Mapping obj) {

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

        obj = (org.epos.eposdatamodel.Mapping) VersioningStatusAPI.checkVersion(obj);

        Mapping edmobj = new Mapping();

        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setLabel(obj.getLabel());
        edmobj.setValuepattern(obj.getValuePattern());
        edmobj.setDefaultvalue(obj.getDefaultValue());
        edmobj.setMaxvalue(obj.getMaxValue());
        edmobj.setMinvalue(obj.getMinValue());
        edmobj.setMultipleValues(obj.getMultipleValues());
        edmobj.setReadOnlyValue(obj.getReadOnlyValue());
        edmobj.setRequired(Boolean.getBoolean(obj.getRequired()));
        edmobj.setRange(obj.getRange());
        edmobj.setProperty(obj.getProperty());


        if(returnList.isEmpty()) getDbaccess().createObject(edmobj);
        else getDbaccess().updateObject(edmobj);


        /** RETURNS **/
        if(!obj.getParamValue().isEmpty()){
            for(String paramvalue : obj.getParamValue()) {
                createInnerElement(ElementType.PARAMVALUE, paramvalue, edmobj);
            }
        }

        return new LinkedEntity().entityType(entityName)
                    .instanceId(edmobj.getInstanceId())
                    .metaId(edmobj.getMetaId())
                    .uid(edmobj.getUid());

    }

    private void createInnerElement(ElementType elementType, String value, Mapping edmobj){
        org.epos.eposdatamodel.Element element = new org.epos.eposdatamodel.Element();
        element.setType(elementType);
        element.setValue(value);
        ElementAPI api = new ElementAPI("Element", Element.class);
        LinkedEntity le = api.create(element);
        List<Element> el = dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Element.class);
        MappingElement ce = new MappingElement();
        ce.setMappingByMappingInstanceId(edmobj);
        ce.setMappingInstanceId(edmobj.getInstanceId());
        ce.setElementByElementInstanceId(el.get(0));
        ce.setElementInstanceId(el.get(0).getInstanceId());
    }


    @Override
    public org.epos.eposdatamodel.Mapping retrieve(String instanceId) {
        Mapping edmobj = (Mapping) getDbaccess().getOneFromDBByInstanceId(instanceId, Mapping.class).get(0);

        org.epos.eposdatamodel.Mapping o = new org.epos.eposdatamodel.Mapping();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setLabel(edmobj.getLabel());
        o.setValuePattern(edmobj.getValuepattern());
        o.setDefaultValue(edmobj.getDefaultvalue());
        o.setMaxValue(edmobj.getMaxvalue());
        o.setMinValue(edmobj.getMinvalue());
        o.setMultipleValues(edmobj.getMultipleValues());
        o.setReadOnlyValue(edmobj.getReadOnlyValue());
        o.setRequired(Boolean.toString(edmobj.isRequired()));
        o.setRange(edmobj.getRange());
        o.setProperty(edmobj.getProperty());

        if(edmobj.getMappingElementsByInstanceId().size()>0) {
            for(MappingElement ed : edmobj.getMappingElementsByInstanceId()) {
                Element el = ed.getElementByElementInstanceId();
                if(el.getType().equals(ElementType.PARAMVALUE)) {
                    o.addParamValue(el.getValue());
                }
            }
        }

        return o;
    }

}
