package metadataapis;

import abstractapis.AbstractAPI;
import commonapis.*;
import model.*;
import org.epos.eposdatamodel.LinkedEntity;
import relationsapi.CategoryRelationsAPI;
import relationsapi.ContactPointRelationsAPI;
import relationsapi.RelationChecker;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DataProductAPI extends AbstractAPI<org.epos.eposdatamodel.DataProduct> {

    public DataProductAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.DataProduct obj, StatusType overrideStatus) {

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

        obj = (org.epos.eposdatamodel.DataProduct) VersioningStatusAPI.checkVersion(obj, overrideStatus);

        EposDataModelEntityIDAPI.addEntityToEDMEntityID(obj.getMetaId(), entityName);

        Dataproduct edmobj = new Dataproduct();

        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());

        getDbaccess().updateObject(edmobj);

        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setKeywords(String.join("\\|", Optional.ofNullable(obj.getKeywords()).orElse("")));
        edmobj.setAccessright(obj.getAccessRight());
        edmobj.setAccrualperiodicity(obj.getAccrualPeriodicity());
        edmobj.setType(obj.getType());
        edmobj.setVersioninfo(obj.getVersionInfo());
        edmobj.setDocumentation(obj.getDocumentation());
        edmobj.setQualityassurance(obj.getQualityAssurance());
        edmobj.setHasQualityAnnotation(obj.getHasQualityAnnotation());
        edmobj.setAccessright(obj.getAccessRight());

        if (obj.getCreated() != null)
            edmobj.setCreated(Timestamp.valueOf(obj.getCreated()));
        if (obj.getModified() != null)
            edmobj.setModified(Timestamp.valueOf(obj.getModified()));
        if (obj.getIssued() != null)
            edmobj.setIssued(Timestamp.valueOf(obj.getIssued()));

        /** CATEGORY **/
        if (obj.getCategory() != null && !obj.getCategory().isEmpty())
            CategoryRelationsAPI.createRelation(edmobj,obj, overrideStatus);

        /** CONTACTPOINT **/
        if (obj.getContactPoint() != null && !obj.getContactPoint().isEmpty())
            ContactPointRelationsAPI.createRelation(edmobj,obj, overrideStatus);

        /** TITLE **/
        if (obj.getTitle() != null && !obj.getTitle().isEmpty()) {
            List<DataproductTitle> dataproductTitleList = getDbaccess().getAllFromDB(DataproductTitle.class);
            for(DataproductTitle item : dataproductTitleList){
                if(item.getDataproductInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            edmobj.setDataproductTitlesByInstanceId(new ArrayList<>());
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
                edmobj.getDataproductTitlesByInstanceId().add(pi);

                dbaccess.updateObject(pi);
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
            edmobj.setDataproductDescriptionsByInstanceId(new ArrayList<>());
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
                edmobj.getDataproductDescriptionsByInstanceId().add(pi);
                dbaccess.updateObject(pi);
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
            edmobj.setDataproductHaspartsByInstanceId(new ArrayList<>());
            for(LinkedEntity dataProduct : obj.getHasPart()){

                Dataproduct dataproduct = (Dataproduct) RelationChecker.checkRelation(dataProduct, overrideStatus, Dataproduct.class);

                DataproductHaspart pi = new DataproductHaspart();
                pi.setDataproductByDataproduct1InstanceId(edmobj);
                pi.setDataproduct1InstanceId(edmobj.getInstanceId());
                pi.setDataproduct2InstanceId(dataproduct.getInstanceId());
                pi.setDataproductByDataproduct2InstanceId(dataproduct);
                edmobj.getDataproductHaspartsByInstanceId().add(pi);
                dbaccess.updateObject(pi);
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
            edmobj.setDataproductIspartofsByInstanceId(new ArrayList<>());
            for(LinkedEntity dataProduct : obj.getIsPartOf()){
                Dataproduct dataproduct = (Dataproduct) RelationChecker.checkRelation(dataProduct, overrideStatus, Dataproduct.class);

                if(dataproduct!=null) {
                    DataproductIspartof pi = new DataproductIspartof();
                    pi.setDataproductByDataproduct1InstanceId(edmobj);
                    pi.setDataproduct1InstanceId(edmobj.getInstanceId());
                    pi.setDataproduct2InstanceId(dataproduct.getInstanceId());
                    pi.setDataproductByDataproduct2InstanceId(dataproduct);
                    edmobj.getDataproductIspartofsByInstanceId().add(pi);
                    dbaccess.updateObject(pi);
                }
            }
        }

        /** IDENTIFIER **/
        if (obj.getIdentifier() != null && !obj.getIdentifier().isEmpty()) {
            List<DataproductIdentifier> dataproductIdentifierList = getDbaccess().getAllFromDB(DataproductIdentifier.class);
            for(DataproductIdentifier item : dataproductIdentifierList){
                if(item.getDataproductInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            edmobj.setDataproductIdentifiersByInstanceId(new ArrayList<>());
            for(org.epos.eposdatamodel.LinkedEntity identifier : obj.getIdentifier()){
                LinkedEntity le = LinkedEntityAPI.createFromLinkedEntity(identifier, overrideStatus);
                List<Identifier> identifierList = dbaccess.getOneFromDBByInstanceId(le.getInstanceId(),Identifier.class);
                if(!identifierList.isEmpty()) {
                    DataproductIdentifier pi = new DataproductIdentifier();
                    pi.setDataproductByDataproductInstanceId(edmobj);
                    pi.setDataproductInstanceId(edmobj.getInstanceId());
                    pi.setIdentifierInstanceId(le.getInstanceId());
                    pi.setIdentifierByIdentifierInstanceId(identifierList.get(0));
                    edmobj.getDataproductIdentifiersByInstanceId().add(pi);
                    dbaccess.updateObject(pi);
                }
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
            edmobj.setDataproductProvenancesByInstanceId(new ArrayList<>());
            for(String provenance : obj.getProvenance()){
                DataproductProvenance pi = new DataproductProvenance();
                pi.setInstanceId(UUID.randomUUID().toString());
                pi.setMetaId(UUID.randomUUID().toString());
                pi.setUid("Title/"+UUID.randomUUID().toString());
                pi.setVersionId(null);
                pi.setProvenance(provenance);
                pi.setDataproductByDataproductInstanceId(edmobj);
                pi.setDataproductInstanceId(edmobj.getInstanceId());
                edmobj.getDataproductProvenancesByInstanceId().add(pi);

                dbaccess.updateObject(pi);
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
            edmobj.setDataproductPublishersByInstanceId(new ArrayList<>());
            for(LinkedEntity organization : obj.getPublisher()){

                Organization organization1 = (Organization) RelationChecker.checkRelation(organization, overrideStatus, Organization.class);

                DataproductPublisher pi = new DataproductPublisher();
                pi.setDataproductByDataproductInstanceId(edmobj);
                pi.setDataproductInstanceId(edmobj.getInstanceId());
                pi.setOrganizationInstanceId(organization1.getInstanceId());
                pi.setOrganizationByOrganizationInstanceId(organization1);
                edmobj.getDataproductPublishersByInstanceId().add(pi);

                dbaccess.updateObject(pi);
            }
        }

        /** DISTRIBUTION **/
        if (obj.getDistribution() != null && !obj.getDistribution().isEmpty()) {
            List<DistributionDataproduct> distributionDataproductList = getDbaccess().getAllFromDB(DistributionDataproduct.class);
            for(DistributionDataproduct item : distributionDataproductList){
                if(item.getDataproductInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }

            for(LinkedEntity distribution : obj.getDistribution()){
                System.out.println("DISTRIBUTION LE :"+distribution);

                Distribution distribution1 = (Distribution) RelationChecker.checkRelation(distribution, overrideStatus, Distribution.class);
                System.out.println("DISTRIBUTION OBJ :"+distribution1);

                if(distribution1!=null) {
                    DistributionDataproduct pi = new DistributionDataproduct();
                    pi.setDataproductByDataproductInstanceId(edmobj);
                    pi.setDataproductInstanceId(edmobj.getInstanceId());
                    pi.setDistributionInstanceId(distribution1.getInstanceId());
                    pi.setDistributionByDistributionInstanceId(distribution1);

                    dbaccess.updateObject(pi);
                }
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
            edmobj.setDataproductSpatialsByInstanceId(new ArrayList<>());
            for(org.epos.eposdatamodel.LinkedEntity location : obj.getSpatialExtent()){

                Spatial spatial = (Spatial) RelationChecker.checkRelation(location, overrideStatus, Spatial.class);

                DataproductSpatial pi = new DataproductSpatial();
                pi.setDataproductByDataproductInstanceId(edmobj);
                pi.setDataproductInstanceId(edmobj.getInstanceId());
                pi.setSpatialInstanceId(spatial.getInstanceId());
                pi.setSpatialBySpatialInstanceId(spatial);
                edmobj.getDataproductSpatialsByInstanceId().add(pi);
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
            TemporalAPI temporalAPI = new TemporalAPI(EntityNames.PERIODOFTIME.name(), Temporal.class);
            edmobj.setDataproductTemporalsByInstanceId(new ArrayList<>());
            for(org.epos.eposdatamodel.LinkedEntity periodOfTime : obj.getTemporalExtent()){

                Temporal temporal = (Temporal) RelationChecker.checkRelation(periodOfTime, overrideStatus, Temporal.class);

                DataproductTemporal pi = new DataproductTemporal();
                pi.setDataproductByDataproductInstanceId(edmobj);
                pi.setDataproductInstanceId(edmobj.getInstanceId());
                pi.setTemporalInstanceId(temporal.getInstanceId());
                pi.setTemporalByTemporalInstanceId(temporal);
                edmobj.getDataproductTemporalsByInstanceId().add(pi);
            }
        }

        getDbaccess().updateObject(edmobj);

        return new LinkedEntity().entityType(entityName)
                    .instanceId(edmobj.getInstanceId())
                    .metaId(edmobj.getMetaId())
                    .uid(edmobj.getUid());

    }

    @Override
    public org.epos.eposdatamodel.DataProduct retrieve(String instanceId) {
        List<Dataproduct> elementList = getDbaccess().getOneFromDBByInstanceId(instanceId, Dataproduct.class);
        if(elementList!=null && !elementList.isEmpty()) {
            Dataproduct edmobj = elementList.get(0);
            org.epos.eposdatamodel.DataProduct o = new org.epos.eposdatamodel.DataProduct();
            o.setInstanceId(edmobj.getInstanceId());
            o.setMetaId(edmobj.getMetaId());
            o.setUid(edmobj.getUid());
            o.setType(edmobj.getType());
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
            o.setAccessRight(edmobj.getAccessright());

            if(edmobj.getKeywords()!=null && !edmobj.getKeywords().isBlank())
                for(String item : edmobj.getKeywords().split("\\|"))
                    o.addKeywords(item);

            if (edmobj.getDataproductCategoriesByInstanceId().size() > 0) {
                for (DataproductCategory ed : edmobj.getDataproductCategoriesByInstanceId()) {
                    CategoryAPI api = new CategoryAPI(EntityNames.CATEGORY.name(), Category.class);
                    LinkedEntity cp = api.retrieveLinkedEntity(ed.getCategoryInstanceId());
                    o.addCategory(cp);
                }
            }

            if (edmobj.getDataproductContactpointsByInstanceId().size() > 0) {
                for (DataproductContactpoint ed : edmobj.getDataproductContactpointsByInstanceId()) {
                    ContactPointAPI api = new ContactPointAPI(EntityNames.CONTACTPOINT.name(), Contactpoint.class);
                    LinkedEntity cp = api.retrieveLinkedEntity(ed.getContactpointInstanceId());
                    o.addContactPoint(cp);
                }
            }

            if (edmobj.getDataproductDescriptionsByInstanceId().size() > 0) {
                for (DataproductDescription ed : edmobj.getDataproductDescriptionsByInstanceId()) {
                    o.addDescription(ed.getDescription());
                }
            }
            if (edmobj.getDataproductTitlesByInstanceId().size() > 0) {
                for (DataproductTitle ed : edmobj.getDataproductTitlesByInstanceId()) {
                    o.addTitle(ed.getTitle());
                }
            }

            if (edmobj.getDataproductIdentifiersByInstanceId().size() > 0) {
                for (DataproductIdentifier ed : edmobj.getDataproductIdentifiersByInstanceId()) {
                    IdentifierAPI api = new IdentifierAPI(EntityNames.IDENTIFIER.name(), Identifier.class);
                    org.epos.eposdatamodel.LinkedEntity cp = api.retrieveLinkedEntity(ed.getIdentifierInstanceId());
                    o.addIdentifier(cp);
                }
            }

            if (edmobj.getDataproductHaspartsByInstanceId().size() > 0) {
                for (DataproductHaspart ed : edmobj.getDataproductHaspartsByInstanceId()) {
                    LinkedEntity cp = retrieveLinkedEntity(ed.getDataproduct2InstanceId());
                    o.addHasPart(cp);
                }
            }

            if (edmobj.getDataproductIspartofsByInstanceId().size() > 0) {
                for (DataproductIspartof ed : edmobj.getDataproductIspartofsByInstanceId()) {
                    LinkedEntity cp = retrieveLinkedEntity(ed.getDataproduct2InstanceId());
                    o.addIsPartOf(cp);
                }
            }

            if (edmobj.getDataproductProvenancesByInstanceId().size() > 0) {
                for (DataproductProvenance ed : edmobj.getDataproductProvenancesByInstanceId()) {
                    o.addProvenance(ed.getProvenance());
                }
            }

            if (edmobj.getDataproductPublishersByInstanceId().size() > 0) {
                for (DataproductPublisher ed : edmobj.getDataproductPublishersByInstanceId()) {
                    OrganizationAPI api = new OrganizationAPI(EntityNames.ORGANIZATION.name(), Organization.class);
                    LinkedEntity cp = api.retrieveLinkedEntity(ed.getOrganizationInstanceId());
                    o.addPublisher(cp);
                }
            }

            List<DistributionDataproduct> distributionDataproductList = getDbaccess().getAllFromDB(DistributionDataproduct.class);
            DistributionAPI distributionAPI = new DistributionAPI(EntityNames.DISTRIBUTION.name(), Distribution.class);
            if (!distributionDataproductList.isEmpty()) {
                for (DistributionDataproduct ed : distributionDataproductList) {
                    if(ed.getDataproductInstanceId().equals(o.getInstanceId())) {
                        LinkedEntity cp = distributionAPI.retrieveLinkedEntity(ed.getDistributionInstanceId());
                        o.addDistribution(cp);
                    }
                }
            }

            if (edmobj.getDataproductSpatialsByInstanceId().size() > 0) {
                for (DataproductSpatial ed : edmobj.getDataproductSpatialsByInstanceId()) {
                    SpatialAPI api = new SpatialAPI(EntityNames.LOCATION.name(), Spatial.class);
                    org.epos.eposdatamodel.LinkedEntity cp = api.retrieveLinkedEntity(ed.getSpatialInstanceId());
                    o.addSpatialExtentItem(cp);
                }
            }

            if (edmobj.getDataproductTemporalsByInstanceId().size() > 0) {
                for (DataproductTemporal ed : edmobj.getDataproductTemporalsByInstanceId()) {
                    TemporalAPI api = new TemporalAPI(EntityNames.PERIODOFTIME.name(), Temporal.class);
                    org.epos.eposdatamodel.LinkedEntity cp = api.retrieveLinkedEntity(ed.getTemporalInstanceId());
                    o.addTemporalExtent(cp);
                }
            }

            o = (org.epos.eposdatamodel.DataProduct) VersioningStatusAPI.retrieveVersion(o);

            return o;
        }
        return null;
    }

    @Override
    public List<org.epos.eposdatamodel.DataProduct> retrieveAll() {
        List<Dataproduct> list = getDbaccess().getAllFromDB(Dataproduct.class);
        List<org.epos.eposdatamodel.DataProduct> returnList = new ArrayList<>();
        list.parallelStream().forEach(item -> {
            returnList.add(retrieve(item.getInstanceId()));
        });
        return returnList;
    }


    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        List<Dataproduct> elementList = getDbaccess().getOneFromDBByInstanceId(instanceId, Dataproduct.class);
        if(elementList!=null && !elementList.isEmpty()) {
            Dataproduct edmobj = elementList.get(0);
            LinkedEntity o = new LinkedEntity();
            o.setInstanceId(edmobj.getInstanceId());
            o.setMetaId(edmobj.getMetaId());
            o.setUid(edmobj.getUid());
            o.setEntityType(EntityNames.DATAPRODUCT.name());

            return o;
        }
        return null;
    }

}
