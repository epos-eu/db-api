package model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Versioningstatus {
    @Id
    @Column(name = "version_id", nullable = false, length = 100)
    private String versionId;
    @Basic
    @Column(name = "instance_id", nullable = true, length = 100)
    private String instanceId;
    @Basic
    @Column(name = "meta_id", nullable = true, length = 100)
    private String metaId;
    @Basic
    @Column(name = "uid", nullable = true, length = 100)
    private String uid;
    @Basic
    @Column(name = "instance_change_id", nullable = true, length = 1024)
    private String instanceChangeId;
    @Basic
    @Column(name = "provenance", nullable = true, length = 1024)
    private String provenance;
    @Basic
    @Column(name = "editor_id", nullable = true, length = 1024)
    private String editorId;
    @Basic
    @Column(name = "reviewer_id", nullable = true, length = 100)
    private String reviewerId;
    @Basic
    @Column(name = "review_comment", nullable = true, length = 1024)
    private String reviewComment;
    @Basic
    @Column(name = "change_comment", nullable = true, length = -1)
    private String changeComment;
    @Basic
    @Column(name = "change_timestamp", nullable = true)
    private Timestamp changeTimestamp;
    @Basic
    @Column(name = "version", nullable = true, length = 1024)
    private String version;
    @Basic
    @Column(name = "status", nullable = true)
    @Enumerated(EnumType.STRING)
    private StatusType status;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<Address> addressesByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<Category> categoriesByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<CategoryScheme> categorySchemesByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<Contactpoint> contactpointsByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<Dataproduct> dataproductsByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<DataproductDescription> dataproductDescriptionsByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<DataproductProvenance> dataproductProvenancesByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<DataproductTitle> dataproductTitlesByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<Distribution> distributionsByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<DistributionDescription> distributionDescriptionsByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<DistributionTitle> distributionTitlesByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<Element> elementsByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<Equipment> equipmentByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<Facility> facilitiesByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<Identifier> identifiersByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<Mapping> mappingsByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<Operation> operationsByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<Organization> organizationsByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<Person> peopleByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<Publication> publicationsByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<Service> servicesByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<SoftwareApplication> softwareapplicationsByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<SoftwareSourceCode> softwaresourcecodesByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<Spatial> spatialsByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<Temporal> temporalsByVersionId;
    @OneToMany(mappedBy = "versioningstatusByVersionId")
    private Collection<Webservice> webservicesByVersionId;

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getMetaId() {
        return metaId;
    }

    public void setMetaId(String metaId) {
        this.metaId = metaId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getInstanceChangeId() {
        return instanceChangeId;
    }

    public void setInstanceChangeId(String instanceChangeId) {
        this.instanceChangeId = instanceChangeId;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public String getEditorId() {
        return editorId;
    }

    public void setEditorId(String editorId) {
        this.editorId = editorId;
    }

    public String getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public String getChangeComment() {
        return changeComment;
    }

    public void setChangeComment(String changeComment) {
        this.changeComment = changeComment;
    }

    public Timestamp getChangeTimestamp() {
        return changeTimestamp;
    }

    public void setChangeTimestamp(Timestamp changeTimestamp) {
        this.changeTimestamp = changeTimestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Versioningstatus that = (Versioningstatus) o;

        if (versionId != null ? !versionId.equals(that.versionId) : that.versionId != null) return false;
        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(that.metaId) : that.metaId != null) return false;
        if (instanceChangeId != null ? !instanceChangeId.equals(that.instanceChangeId) : that.instanceChangeId != null) return false;
        if (provenance != null ? !provenance.equals(that.provenance) : that.provenance != null) return false;
        if (editorId != null ? !editorId.equals(that.editorId) : that.editorId != null) return false;
        if (reviewerId != null ? !reviewerId.equals(that.reviewerId) : that.reviewerId != null) return false;
        if (reviewComment != null ? !reviewComment.equals(that.reviewComment) : that.reviewComment != null)
            return false;
        if (changeComment != null ? !changeComment.equals(that.changeComment) : that.changeComment != null)
            return false;
        if (changeTimestamp != null ? !changeTimestamp.equals(that.changeTimestamp) : that.changeTimestamp != null)
            return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = versionId != null ? versionId.hashCode() : 0;
        result = 31 * result + (instanceId != null ? instanceId.hashCode() : 0);
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (instanceChangeId != null ? instanceChangeId.hashCode() : 0);
        result = 31 * result + (provenance != null ? provenance.hashCode() : 0);
        result = 31 * result + (editorId != null ? editorId.hashCode() : 0);
        result = 31 * result + (reviewerId != null ? reviewerId.hashCode() : 0);
        result = 31 * result + (reviewComment != null ? reviewComment.hashCode() : 0);
        result = 31 * result + (changeComment != null ? changeComment.hashCode() : 0);
        result = 31 * result + (changeTimestamp != null ? changeTimestamp.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    public Collection<Address> getAddressesByVersionId() {
        return addressesByVersionId;
    }

    public void setAddressesByVersionId(Collection<Address> addressesByVersionId) {
        this.addressesByVersionId = addressesByVersionId;
    }

    public Collection<Category> getCategoriesByVersionId() {
        return categoriesByVersionId;
    }

    public void setCategoriesByVersionId(Collection<Category> categoriesByVersionId) {
        this.categoriesByVersionId = categoriesByVersionId;
    }

    public Collection<CategoryScheme> getCategorySchemesByVersionId() {
        return categorySchemesByVersionId;
    }

    public void setCategorySchemesByVersionId(Collection<CategoryScheme> categorySchemesByVersionId) {
        this.categorySchemesByVersionId = categorySchemesByVersionId;
    }

    public Collection<Contactpoint> getContactpointsByVersionId() {
        return contactpointsByVersionId;
    }

    public void setContactpointsByVersionId(Collection<Contactpoint> contactpointsByVersionId) {
        this.contactpointsByVersionId = contactpointsByVersionId;
    }

    public Collection<Dataproduct> getDataproductsByVersionId() {
        return dataproductsByVersionId;
    }

    public void setDataproductsByVersionId(Collection<Dataproduct> dataproductsByVersionId) {
        this.dataproductsByVersionId = dataproductsByVersionId;
    }

    public Collection<DataproductDescription> getDataproductDescriptionsByVersionId() {
        return dataproductDescriptionsByVersionId;
    }

    public void setDataproductDescriptionsByVersionId(Collection<DataproductDescription> dataproductDescriptionsByVersionId) {
        this.dataproductDescriptionsByVersionId = dataproductDescriptionsByVersionId;
    }

    public Collection<DataproductProvenance> getDataproductProvenancesByVersionId() {
        return dataproductProvenancesByVersionId;
    }

    public void setDataproductProvenancesByVersionId(Collection<DataproductProvenance> dataproductProvenancesByVersionId) {
        this.dataproductProvenancesByVersionId = dataproductProvenancesByVersionId;
    }

    public Collection<DataproductTitle> getDataproductTitlesByVersionId() {
        return dataproductTitlesByVersionId;
    }

    public void setDataproductTitlesByVersionId(Collection<DataproductTitle> dataproductTitlesByVersionId) {
        this.dataproductTitlesByVersionId = dataproductTitlesByVersionId;
    }

    public Collection<Distribution> getDistributionsByVersionId() {
        return distributionsByVersionId;
    }

    public void setDistributionsByVersionId(Collection<Distribution> distributionsByVersionId) {
        this.distributionsByVersionId = distributionsByVersionId;
    }

    public Collection<DistributionDescription> getDistributionDescriptionsByVersionId() {
        return distributionDescriptionsByVersionId;
    }

    public void setDistributionDescriptionsByVersionId(Collection<DistributionDescription> distributionDescriptionsByVersionId) {
        this.distributionDescriptionsByVersionId = distributionDescriptionsByVersionId;
    }

    public Collection<DistributionTitle> getDistributionTitlesByVersionId() {
        return distributionTitlesByVersionId;
    }

    public void setDistributionTitlesByVersionId(Collection<DistributionTitle> distributionTitlesByVersionId) {
        this.distributionTitlesByVersionId = distributionTitlesByVersionId;
    }

    public Collection<Element> getElementsByVersionId() {
        return elementsByVersionId;
    }

    public void setElementsByVersionId(Collection<Element> elementsByVersionId) {
        this.elementsByVersionId = elementsByVersionId;
    }

    public Collection<Equipment> getEquipmentByVersionId() {
        return equipmentByVersionId;
    }

    public void setEquipmentByVersionId(Collection<Equipment> equipmentByVersionId) {
        this.equipmentByVersionId = equipmentByVersionId;
    }

    public Collection<Facility> getFacilitiesByVersionId() {
        return facilitiesByVersionId;
    }

    public void setFacilitiesByVersionId(Collection<Facility> facilitiesByVersionId) {
        this.facilitiesByVersionId = facilitiesByVersionId;
    }

    public Collection<Identifier> getIdentifiersByVersionId() {
        return identifiersByVersionId;
    }

    public void setIdentifiersByVersionId(Collection<Identifier> identifiersByVersionId) {
        this.identifiersByVersionId = identifiersByVersionId;
    }

    public Collection<Mapping> getMappingsByVersionId() {
        return mappingsByVersionId;
    }

    public void setMappingsByVersionId(Collection<Mapping> mappingsByVersionId) {
        this.mappingsByVersionId = mappingsByVersionId;
    }

    public Collection<Operation> getOperationsByVersionId() {
        return operationsByVersionId;
    }

    public void setOperationsByVersionId(Collection<Operation> operationsByVersionId) {
        this.operationsByVersionId = operationsByVersionId;
    }

    public Collection<Organization> getOrganizationsByVersionId() {
        return organizationsByVersionId;
    }

    public void setOrganizationsByVersionId(Collection<Organization> organizationsByVersionId) {
        this.organizationsByVersionId = organizationsByVersionId;
    }

    public Collection<Person> getPeopleByVersionId() {
        return peopleByVersionId;
    }

    public void setPeopleByVersionId(Collection<Person> peopleByVersionId) {
        this.peopleByVersionId = peopleByVersionId;
    }

    public Collection<Publication> getPublicationsByVersionId() {
        return publicationsByVersionId;
    }

    public void setPublicationsByVersionId(Collection<Publication> publicationsByVersionId) {
        this.publicationsByVersionId = publicationsByVersionId;
    }

    public Collection<Service> getServicesByVersionId() {
        return servicesByVersionId;
    }

    public void setServicesByVersionId(Collection<Service> servicesByVersionId) {
        this.servicesByVersionId = servicesByVersionId;
    }

    public Collection<SoftwareApplication> getSoftwareapplicationsByVersionId() {
        return softwareapplicationsByVersionId;
    }

    public void setSoftwareapplicationsByVersionId(Collection<SoftwareApplication> softwareapplicationsByVersionId) {
        this.softwareapplicationsByVersionId = softwareapplicationsByVersionId;
    }

    public Collection<SoftwareSourceCode> getSoftwaresourcecodesByVersionId() {
        return softwaresourcecodesByVersionId;
    }

    public void setSoftwaresourcecodesByVersionId(Collection<SoftwareSourceCode> softwaresourcecodesByVersionId) {
        this.softwaresourcecodesByVersionId = softwaresourcecodesByVersionId;
    }

    public Collection<Spatial> getSpatialsByVersionId() {
        return spatialsByVersionId;
    }

    public void setSpatialsByVersionId(Collection<Spatial> spatialsByVersionId) {
        this.spatialsByVersionId = spatialsByVersionId;
    }

    public Collection<Temporal> getTemporalsByVersionId() {
        return temporalsByVersionId;
    }

    public void setTemporalsByVersionId(Collection<Temporal> temporalsByVersionId) {
        this.temporalsByVersionId = temporalsByVersionId;
    }

    public Collection<Webservice> getWebservicesByVersionId() {
        return webservicesByVersionId;
    }

    public void setWebservicesByVersionId(Collection<Webservice> webservicesByVersionId) {
        this.webservicesByVersionId = webservicesByVersionId;
    }
}
