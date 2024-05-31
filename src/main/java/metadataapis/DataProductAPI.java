package metadataapis;

import abstractapis.AbstractAPI;
import commonapis.*;
import model.*;
import org.epos.eposdatamodel.ContactPoint;
import org.epos.eposdatamodel.LinkedEntity;
import relationsapi.CategoryRelationsAPI;
import relationsapi.ContactPointRelationsAPI;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class DataProductAPI extends AbstractAPI<org.epos.eposdatamodel.DataProduct> {

    public DataProductAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.DataProduct obj) {

        List<Dataproduct> returnList = getDbaccess().getOneFromDB(
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

        obj = (org.epos.eposdatamodel.DataProduct) VersioningStatusAPI.checkVersion(obj);

        Dataproduct edmobj = new Dataproduct();

        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setKeywords(obj.getKeywords());
        edmobj.setAccessright(obj.getAccessRight());
        edmobj.setAccrualperiodicity(obj.getAccrualPeriodicity());
        edmobj.setType(obj.getType());
        edmobj.setVersioninfo(obj.getVersionInfo());
        edmobj.setDocumentation(obj.getDocumentation());
        edmobj.setQualityassurance(obj.getQualityAssurance());
        edmobj.setHasQualityAnnotation(obj.getHasQualityAnnotation());

        if (obj.getCreated() != null)
            edmobj.setCreated(Timestamp.valueOf(obj.getCreated()));
        if (obj.getModified() != null)
            edmobj.setModified(Timestamp.valueOf(obj.getModified()));
        if (obj.getIssued() != null)
            edmobj.setIssued(Timestamp.valueOf(obj.getIssued()));

        if(returnList.isEmpty()) getDbaccess().createObject(edmobj);
        else getDbaccess().updateObject(edmobj);

        /** CATEGORY **/
        if (obj.getCategory() != null && !obj.getCategory().isEmpty())
            CategoryRelationsAPI.createRelation(edmobj,obj);

        /** CONTACTPOINT **/
        if (obj.getContactPoint() != null && !obj.getContactPoint().isEmpty())
            ContactPointRelationsAPI.createRelation(edmobj,obj);

        /** TITLE **/
        if (obj.getTitle() != null && !obj.getTitle().isEmpty()) {
            List<DataproductTitle> dataproductTitleList = getDbaccess().getAllFromDB(DataproductTitle.class);
            for(DataproductTitle item : dataproductTitleList){
                if(item.getDataproductInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            for(String title : obj.getTitle()){
                DataproductTitle pi = new DataproductTitle();
                pi.setInstanceId(UUID.randomUUID().toString());
                pi.setMetaId(UUID.randomUUID().toString());
                pi.setUid("Title/"+UUID.randomUUID().toString());
                pi.setVersionId(null);
                pi.setTitle(title);
                pi.setDataproductByDataproductInstanceId(edmobj);
                pi.setDataproductInstanceId(edmobj.getInstanceId());
                pi.setLang(null);

                dbaccess.createObject(pi);
            }
        }

        /** DESCRIPTION **/
        if (obj.getDescription() != null && !obj.getDescription().isEmpty()) {
            List<DataproductDescription> dataproductDescriptionList = getDbaccess().getAllFromDB(DataproductDescription.class);
            for(DataproductDescription item : dataproductDescriptionList){
                if(item.getDataproductInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            for(String description : obj.getDescription()){
                DataproductDescription pi = new DataproductDescription();
                pi.setInstanceId(UUID.randomUUID().toString());
                pi.setMetaId(UUID.randomUUID().toString());
                pi.setUid("Description/"+UUID.randomUUID().toString());
                pi.setVersionId(null);
                pi.setDescription(description);
                pi.setDataproductByDataproductInstanceId(edmobj);
                pi.setDataproductInstanceId(edmobj.getInstanceId());
                pi.setLang(null);

                dbaccess.createObject(pi);
            }
        }

        /** HASPART **/
        if (obj.getHasPart() != null && !obj.getHasPart().isEmpty()) {
            List<DataproductHaspart> dataproductHaspartsList = getDbaccess().getAllFromDB(DataproductHaspart.class);
            for(DataproductHaspart item : dataproductHaspartsList){
                if(item.getDataproduct1InstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            for(org.epos.eposdatamodel.DataProduct dataProduct : obj.getHasPart()){
                List<Dataproduct> list = dbaccess.getOneFromDBByInstanceId(dataProduct.getInstanceId(),Dataproduct.class);
                Dataproduct dataproduct = null;
                if(list.isEmpty()){
                    LinkedEntity le = create(dataProduct);
                    dataproduct = (Dataproduct) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Dataproduct.class).get(0);
                } else {
                    dataproduct = list.get(0);
                }
                DataproductHaspart pi = new DataproductHaspart();
                pi.setDataproductByDataproduct1InstanceId(edmobj);
                pi.setDataproduct1InstanceId(edmobj.getInstanceId());
                pi.setDataproduct2InstanceId(dataproduct.getInstanceId());
                pi.setDataproductByDataproduct2InstanceId(dataproduct);
                dbaccess.createObject(pi);
            }
        }

        /** ISPARTOF **/
        if (obj.getIsPartOf() != null && !obj.getIsPartOf().isEmpty()) {
            List<DataproductIspartof> dataproductIspartofList = getDbaccess().getAllFromDB(DataproductIspartof.class);
            for(DataproductIspartof item : dataproductIspartofList){
                if(item.getDataproduct1InstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            for(org.epos.eposdatamodel.DataProduct dataProduct : obj.getIsPartOf()){
                List<Dataproduct> list = dbaccess.getOneFromDBByInstanceId(dataProduct.getInstanceId(),Dataproduct.class);
                Dataproduct dataproduct = null;
                if(list.isEmpty()){
                    LinkedEntity le = create(dataProduct);
                    dataproduct = (Dataproduct) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Dataproduct.class).get(0);
                } else {
                    dataproduct = list.get(0);
                }
                DataproductIspartof pi = new DataproductIspartof();
                pi.setDataproductByDataproduct1InstanceId(edmobj);
                pi.setDataproduct1InstanceId(edmobj.getInstanceId());
                pi.setDataproduct2InstanceId(dataproduct.getInstanceId());
                pi.setDataproductByDataproduct2InstanceId(dataproduct);
                dbaccess.createObject(pi);
            }
        }

        /** IDENTIFIER **/
        if (obj.getIdentifier() != null && !obj.getIdentifier().isEmpty()) {
            List<DataproductIdentifier> dataproductIdentifierList = getDbaccess().getAllFromDB(DataproductIdentifier.class);
            for(DataproductIdentifier item : dataproductIdentifierList){
                if(item.getDataproductInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                    List<Identifier> list2 = getDbaccess().getOneFromDBByInstanceId(item.getIdentifierInstanceId(), Identifier.class);
                    getDbaccess().deleteObject(list2.get(0));
                }
            }
            IdentifierAPI identifierAPI = new IdentifierAPI("Identifier", Identifier.class);
            for(org.epos.eposdatamodel.Identifier identifier : obj.getIdentifier()){
                LinkedEntity le = identifierAPI.create(identifier);
                DataproductIdentifier pi = new DataproductIdentifier();
                pi.setDataproductByDataproductInstanceId(edmobj);
                pi.setDataproductInstanceId(edmobj.getInstanceId());
                pi.setIdentifierInstanceId(le.getInstanceId());
                pi.setIdentifierByIdentifierInstanceId((Identifier) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(),Identifier.class).get(0));
                dbaccess.createObject(pi);
            }
        }
        /** PROVENANCE **/
        if (obj.getProvenance() != null && !obj.getProvenance().isEmpty()) {
            List<DataproductProvenance> dataproductProvenanceList = getDbaccess().getAllFromDB(DataproductProvenance.class);
            for(DataproductProvenance item : dataproductProvenanceList){
                if(item.getDataproductInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            for(String provenance : obj.getProvenance()){
                DataproductProvenance pi = new DataproductProvenance();
                pi.setInstanceId(UUID.randomUUID().toString());
                pi.setMetaId(UUID.randomUUID().toString());
                pi.setUid("Title/"+UUID.randomUUID().toString());
                pi.setVersionId(null);
                pi.setProvenance(provenance);
                pi.setDataproductByDataproductInstanceId(edmobj);
                pi.setDataproductInstanceId(edmobj.getInstanceId());

                dbaccess.createObject(pi);
            }
        }

        /** PUBLISHER **/
        if (obj.getPublisher() != null && !obj.getPublisher().isEmpty()) {
            List<DataproductPublisher> dataproductPublisherList = getDbaccess().getAllFromDB(DataproductPublisher.class);
            for(DataproductPublisher item : dataproductPublisherList){
                if(item.getDataproductInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            OrganizationAPI organizationAPI = new OrganizationAPI("Organization", Organization.class);
            for(org.epos.eposdatamodel.Organization organization : obj.getPublisher()){
                List<Organization> list = dbaccess.getOneFromDBByInstanceId(organization.getInstanceId(),Organization.class);
                Organization organization1 = null;
                if(list.isEmpty()){
                    LinkedEntity le = organizationAPI.create(organization);
                    organization1 = (Organization) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Organization.class).get(0);
                } else {
                    organization1 = list.get(0);
                }
                DataproductPublisher pi = new DataproductPublisher();
                pi.setDataproductByDataproductInstanceId(edmobj);
                pi.setDataproductInstanceId(edmobj.getInstanceId());
                pi.setOrganizationInstanceId(organization1.getInstanceId());
                pi.setOrganizationByOrganizationInstanceId(organization1);

                dbaccess.createObject(pi);
            }
        }

        /** SPATIAL **/
        if (obj.getSpatialExtent() != null && !obj.getSpatialExtent().isEmpty()) {
            List<DataproductSpatial> dataproductSpatialList = getDbaccess().getAllFromDB(DataproductSpatial.class);
            for(DataproductSpatial item : dataproductSpatialList){
                if(item.getDataproductInstanceId().equals(obj.getInstanceId())){
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
                DataproductSpatial pi = new DataproductSpatial();
                pi.setDataproductByDataproductInstanceId(edmobj);
                pi.setDataproductInstanceId(edmobj.getInstanceId());
                pi.setSpatialInstanceId(spatial.getInstanceId());
                pi.setSpatialBySpatialInstanceId(spatial);
            }
        }

        /** TEMPORAL **/
        if (obj.getTemporalExtent() != null && !obj.getTemporalExtent().isEmpty()) {
            List<DataproductTemporal> equipmentTemporalList = getDbaccess().getAllFromDB(DataproductTemporal.class);
            for(DataproductTemporal item : equipmentTemporalList){
                if(item.getDataproductInstanceId().equals(obj.getInstanceId())){
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
                DataproductTemporal pi = new DataproductTemporal();
                pi.setDataproductByDataproductInstanceId(edmobj);
                pi.setDataproductInstanceId(edmobj.getInstanceId());
                pi.setTemporalInstanceId(temporal.getInstanceId());
                pi.setTemporalByTemporalInstanceId(temporal);
            }
        }


        return new LinkedEntity().entityType(entityName)
                    .instanceId(edmobj.getInstanceId())
                    .metaId(edmobj.getMetaId())
                    .uid(edmobj.getUid());

    }

    @Override
    public org.epos.eposdatamodel.DataProduct retrieve(String instanceId) {
        Dataproduct edmobj = (Dataproduct) getDbaccess().getOneFromDBByInstanceId(instanceId, Dataproduct.class).get(0);

        org.epos.eposdatamodel.DataProduct o = new org.epos.eposdatamodel.DataProduct();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setType(edmobj.getType());
        o.setAccessRight(edmobj.getAccessright());
        o.setAccrualPeriodicity(edmobj.getAccrualperiodicity());
        o.setHasQualityAnnotation(edmobj.getHasQualityAnnotation());
        o.setCreated(
                edmobj.getCreated() != null ? edmobj.getCreated().toLocalDateTime() : null
        );
        o.setIssued(
                edmobj.getIssued() != null ? edmobj.getIssued().toLocalDateTime() : null
        );
        o.setModified(
                edmobj.getModified() != null ? edmobj.getModified().toLocalDateTime() : null
        );
        o.setType(edmobj.getType());
        o.setVersionInfo(edmobj.getVersioninfo());
        o.setDocumentation(edmobj.getDocumentation());
        o.setQualityAssurance(edmobj.getQualityassurance());

        if(edmobj.getDataproductCategoriesByInstanceId().size()>0) {
            for(DataproductCategory ed : edmobj.getDataproductCategoriesByInstanceId()) {
                CategoryAPI api = new CategoryAPI("Category", Category.class);
                org.epos.eposdatamodel.Category cp = api.retrieve(ed.getCategoryInstanceId());
                o.addCategory(cp);
            }
        }

        if(edmobj.getDataproductContactpointsByInstanceId().size()>0) {
            for(DataproductContactpoint ed : edmobj.getDataproductContactpointsByInstanceId()) {
                ContactPointAPI api = new ContactPointAPI("ContactPoint", Contactpoint.class);
                ContactPoint cp = api.retrieve(ed.getContactpointInstanceId());
                o.addContactPoint(cp);
            }
        }

        if(edmobj.getDataproductDescriptionsByInstanceId().size()>0) {
            for(DataproductDescription ed : edmobj.getDataproductDescriptionsByInstanceId()) {
                o.addDescription(ed.getDescription());
            }
        }
        if(edmobj.getDataproductTitlesByInstanceId().size()>0) {
            for(DataproductTitle ed : edmobj.getDataproductTitlesByInstanceId()) {
                o.addTitle(ed.getTitle());
            }
        }

        if(edmobj.getDataproductIdentifiersByInstanceId().size()>0) {
            for(DataproductIdentifier ed : edmobj.getDataproductIdentifiersByInstanceId()) {
                IdentifierAPI api = new IdentifierAPI("Identifier", Identifier.class);
                org.epos.eposdatamodel.Identifier cp = api.retrieve(ed.getIdentifierInstanceId());
                o.addIdentifier(cp);
            }
        }

        if(edmobj.getDataproductHaspartsByInstanceId().size()>0) {
            for(DataproductHaspart ed : edmobj.getDataproductHaspartsByInstanceId()) {
                org.epos.eposdatamodel.DataProduct cp = retrieve(ed.getDataproduct2InstanceId());
                o.addHasPart(cp);
            }
        }

        if(edmobj.getDataproductIspartofsByInstanceId().size()>0) {
            for(DataproductIspartof ed : edmobj.getDataproductIspartofsByInstanceId()) {
                org.epos.eposdatamodel.DataProduct cp = retrieve(ed.getDataproduct2InstanceId());
                o.addIsPartOf(cp);
            }
        }

        if(edmobj.getDataproductProvenancesByInstanceId().size()>0) {
            for(DataproductProvenance ed : edmobj.getDataproductProvenancesByInstanceId()) {
                o.addProvenance(ed.getProvenance());
            }
        }

        if(edmobj.getDataproductPublishersByInstanceId().size()>0) {
            for(DataproductPublisher ed : edmobj.getDataproductPublishersByInstanceId()) {
                OrganizationAPI api = new OrganizationAPI("Organization", Organization.class);
                org.epos.eposdatamodel.Organization cp = api.retrieve(ed.getOrganizationInstanceId());
                o.addPublisher(cp);
            }
        }

        if(edmobj.getDataproductSpatialsByInstanceId().size()>0) {
            for(DataproductSpatial ed : edmobj.getDataproductSpatialsByInstanceId()) {
                SpatialAPI api = new SpatialAPI("Spatial", Spatial.class);
                org.epos.eposdatamodel.Location cp = api.retrieve(ed.getSpatialInstanceId());
                o.addSpatialExtentItem(cp);
            }
        }

        if(edmobj.getDataproductTemporalsByInstanceId().size()>0) {
            for(DataproductTemporal ed : edmobj.getDataproductTemporalsByInstanceId()) {
                TemporalAPI api = new TemporalAPI("Temporal", Temporal.class);
                org.epos.eposdatamodel.PeriodOfTime cp = api.retrieve(ed.getTemporalInstanceId());
                o.addTemporalExtent(cp);
            }
        }


        return o;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        Dataproduct edmobj = (Dataproduct) getDbaccess().getOneFromDBByInstanceId(instanceId, Dataproduct.class).get(0);

        LinkedEntity o = new LinkedEntity();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setEntityType(EntityNames.DATAPRODUCT.name());

        return o;
    }

}
