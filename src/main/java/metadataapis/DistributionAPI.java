package metadataapis;

import abstractapis.AbstractAPI;
import commonapis.*;
import model.*;
import org.epos.eposdatamodel.ContactPoint;
import org.epos.eposdatamodel.LinkedEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DistributionAPI extends AbstractAPI<org.epos.eposdatamodel.Distribution> {

    public DistributionAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.Distribution obj) {

        List<Distribution> returnList = getDbaccess().getOneFromDB(
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

        obj = (org.epos.eposdatamodel.Distribution) VersioningStatusAPI.checkVersion(obj);

        Distribution edmobj = new Distribution();

        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());

        getDbaccess().updateObject(edmobj);

        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setFormat(obj.getFormat());
        edmobj.setLicense(obj.getLicence());
        edmobj.setType(obj.getType());
        edmobj.setDatapolicy(obj.getDataPolicy());

        if (obj.getModified() != null)
            edmobj.setModified(Timestamp.valueOf(obj.getModified()));
        if (obj.getIssued() != null)
            edmobj.setIssued(Timestamp.valueOf(obj.getIssued()));

        /** TITLE **/
        if (obj.getTitle() != null && !obj.getTitle().isEmpty()) {
            List<DistributionTitle> distributionTitleList = getDbaccess().getAllFromDB(DistributionTitle.class);
            for(DistributionTitle item : distributionTitleList){
                if(item.getDistributionInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            edmobj.setDistributionTitlesByInstanceId(new ArrayList<>());
            for(String title : obj.getTitle()){
                DistributionTitle pi = new DistributionTitle();
                pi.setInstanceId(UUID.randomUUID().toString());
                pi.setMetaId(UUID.randomUUID().toString());
                pi.setUid("Title/"+UUID.randomUUID().toString());
                pi.setVersionId(null);
                pi.setTitle(title);
                pi.setDistributionByDistributionInstanceId(edmobj);
                pi.setDistributionInstanceId(edmobj.getInstanceId());
                pi.setLang(null);

                edmobj.getDistributionTitlesByInstanceId().add(pi);

                dbaccess.updateObject(pi);
            }
        }

        /** DESCRIPTION **/
        if (obj.getDescription() != null && !obj.getDescription().isEmpty()) {
            List<DistributionDescription> distributionDescriptionList = getDbaccess().getAllFromDB(DistributionDescription.class);
            for(DistributionDescription item : distributionDescriptionList){
                if(item.getDistributionInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            edmobj.setDistributionDescriptionsByInstanceId(new ArrayList<>());
            for(String description : obj.getDescription()){
                DistributionDescription pi = new DistributionDescription();
                pi.setInstanceId(UUID.randomUUID().toString());
                pi.setMetaId(UUID.randomUUID().toString());
                pi.setUid("Description/"+UUID.randomUUID().toString());
                pi.setVersionId(null);
                pi.setDescription(description);
                pi.setDistributionByDistributionInstanceId(edmobj);
                pi.setDistributionInstanceId(edmobj.getInstanceId());
                pi.setLang(null);

                edmobj.getDistributionDescriptionsByInstanceId().add(pi);

                dbaccess.updateObject(pi);
            }
        }


        /** DATAPRODUCT **/
        if (obj.getDataProduct() != null && !obj.getDataProduct().isEmpty()) {
            List<DistributionDataproduct> distributionDataproductList = getDbaccess().getAllFromDB(DistributionDataproduct.class);
            for(DistributionDataproduct item : distributionDataproductList){
                if(item.getDistributionInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            DataProductAPI dataProductAPI = new DataProductAPI(EntityNames.DATAPRODUCT.name(), Dataproduct.class);
            edmobj.setDistributionDataproductsByInstanceId(new ArrayList<>());
            for(org.epos.eposdatamodel.DataProduct dataProduct : obj.getDataProduct()){
                List<Dataproduct> list = dbaccess.getOneFromDBByInstanceId(dataProduct.getInstanceId(),Dataproduct.class);
                Dataproduct dataproduct = null;
                if(list.isEmpty()){
                    LinkedEntity le = dataProductAPI.create(dataProduct);
                    dataproduct = (Dataproduct) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Dataproduct.class).get(0);
                } else {
                    dataproduct = list.get(0);
                }
                DistributionDataproduct pi = new DistributionDataproduct();
                pi.setDistributionByDistributionInstanceId(edmobj);
                pi.setDistributionInstanceId(edmobj.getInstanceId());
                pi.setDataproductInstanceId(dataproduct.getInstanceId());
                pi.setDataproductByDataproductInstanceId(dataproduct);

                edmobj.getDistributionDataproductsByInstanceId().add(pi);

                dbaccess.updateObject(pi);
            }
        }

        edmobj.setDistributionElementsByInstanceId(new ArrayList<>());

        if(!obj.getAccessURL().isEmpty()){
            for(String accessurl : obj.getAccessURL()) {
                createInnerElement(ElementType.ACCESSURL, accessurl, edmobj);
            }
        }

        if(!obj.getDownloadURL().isEmpty()) {
            for (String downloadurl : obj.getAccessURL()) {
                createInnerElement(ElementType.DOWNLOADURL, downloadurl, edmobj);
            }
        }

        getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                    .instanceId(edmobj.getInstanceId())
                    .metaId(edmobj.getMetaId())
                    .uid(edmobj.getUid());

    }

    private void createInnerElement(ElementType elementType, String value, Distribution edmobj){
        org.epos.eposdatamodel.Element element = new org.epos.eposdatamodel.Element();
        element.setType(elementType);
        element.setValue(value);
        ElementAPI api = new ElementAPI(EntityNames.ELEMENT.name(), Element.class);
        LinkedEntity le = api.create(element);
        List<Element> el = dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Element.class);
        DistributionElement ce = new DistributionElement();
        ce.setDistributionByDistributionInstanceId(edmobj);
        ce.setDistributionInstanceId(edmobj.getInstanceId());
        ce.setElementByElementInstanceId(el.get(0));
        ce.setElementInstanceId(el.get(0).getInstanceId());

        edmobj.getDistributionElementsByInstanceId().add(ce);

        dbaccess.updateObject(ce);
    }


    @Override
    public org.epos.eposdatamodel.Distribution retrieve(String instanceId) {
        Distribution edmobj = (Distribution) getDbaccess().getOneFromDBByInstanceId(instanceId, Distribution.class).get(0);

        org.epos.eposdatamodel.Distribution o = new org.epos.eposdatamodel.Distribution();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setType(edmobj.getType());
        o.setFormat(edmobj.getFormat());
        o.setLicence(edmobj.getLicense());
        o.setDataPolicy(edmobj.getDatapolicy());
        o.setIssued(
                edmobj.getIssued() != null ? edmobj.getIssued().toLocalDateTime() : null
        );
        o.setModified(
                edmobj.getModified() != null ? edmobj.getModified().toLocalDateTime() : null
        );
        o.setType(edmobj.getType());

        if(edmobj.getDistributionTitlesByInstanceId().size()>0) {
            for(DistributionTitle ed : edmobj.getDistributionTitlesByInstanceId()) {
                o.addTitle(ed.getTitle());
            }
        }

        if(edmobj.getDistributionDescriptionsByInstanceId().size()>0) {
            for(DistributionDescription ed : edmobj.getDistributionDescriptionsByInstanceId()) {
                o.addDescription(ed.getDescription());
            }
        }

        if(edmobj.getDistributionDataproductsByInstanceId().size()>0) {
            for(DistributionDataproduct ed : edmobj.getDistributionDataproductsByInstanceId()) {
                DataProductAPI api = new DataProductAPI(EntityNames.DATAPRODUCT.name(), Dataproduct.class);
                org.epos.eposdatamodel.DataProduct cp = api.retrieve(ed.getDataproductInstanceId());
                o.addDataproduct(cp);
            }
        }

        if(edmobj.getDistributionElementsByInstanceId().size()>0) {
            for(DistributionElement ed : edmobj.getDistributionElementsByInstanceId()) {
                Element el = ed.getElementByElementInstanceId();
                if(el.getType().equals(ElementType.ACCESSURL)) o.addAccessURL(el.getValue());
                if(el.getType().equals(ElementType.DOWNLOADURL)) o.addDownloadURL(el.getValue());
            }
        }

        return o;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        Distribution edmobj = (Distribution) getDbaccess().getOneFromDBByInstanceId(instanceId, Distribution.class).get(0);

        LinkedEntity o = new LinkedEntity();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setEntityType(EntityNames.DISTRIBUTION.name());

        return o;
    }

}
