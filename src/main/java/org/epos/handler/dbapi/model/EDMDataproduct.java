package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "dataproduct")
@NamedQueries({
        @NamedQuery(name = "dataproduct.findAll", query = "SELECT c FROM EDMDataproduct c"),
        @NamedQuery(name = "dataproduct.findAllByState", query = "SELECT c FROM EDMDataproduct c where c.state = :STATE"),
        @NamedQuery(name = "dataproduct.findAllByMetaId", query = "SELECT c FROM EDMDataproduct c where c.metaId = :METAID"),
        @NamedQuery(name = "dataproduct.findByUidAndState", query = "select c from EDMDataproduct c where c.uid = :UID and c.state = :STATE"),
        @NamedQuery(name = "dataproduct.findByUid", query = "select c from EDMDataproduct c where c.uid = :UID"),
        @NamedQuery(name = "dataproduct.findByFileProvenance", query = "select c from EDMDataproduct c where c.fileprovenance = :FPROV"),
        @NamedQuery(name = "dataproduct.findByInstanceId", query = "select c from EDMDataproduct c where c.instanceId = :INSTANCEID")
})
public class EDMDataproduct {
    private String uid;
    private Timestamp created;
    private Timestamp issued;
    private Timestamp modified;
    private String versioninfo;
    private String type;
    private String accrualperiodicity;
    private String keywords;
    private String accessright;
    private String documentation;
    private String qualityassurance;
    private String fileprovenance;
    private String instanceId;
    private String metaId;
    private String instanceChangedId;
    private Timestamp changeTimestamp;
    private String operation;
    private String editorMetaId;
    private String changeComment;
    private String version;
    private String state;
    private Boolean toBeDeleted;
    private String hasQualityAnnotation;
    private String dctidentifier;
    private Collection<EDMContactpointDataproduct> contactpointDataproductsByInstanceId;
    private EDMEdmEntityId edmEntityIdByMetaId;
    private EDMDataproduct dataproductByInstanceChangedId;
    private Collection<EDMDataproduct> dataproductsByInstanceId;
    private EDMEdmEntityId edmEntityIdByEditorMetaId;
    private Collection<EDMDataproductCategory> dataproductCategoriesByInstanceId;
    private Collection<EDMDataproductDescription> dataproductDescriptionsByInstanceId;
    private Collection<EDMDataproductIdentifier> dataproductIdentifiersByInstanceId;
    private Collection<EDMDataproductProvenance> dataproductProvenancesByInstanceId;
    private Collection<EDMDataproductSpatial> dataproductSpatialsByInstanceId;
    private Collection<EDMDataproductTemporal> dataproductTemporalsByInstanceId;
    private Collection<EDMDataproductTitle> dataproductTitlesByInstanceId;
    private Collection<EDMHaspartDataproduct> haspartDataproductsByInstanceId;
    private Collection<EDMHaspartDataproduct> haspartDataproductsByInstanceId_0;
    private Collection<EDMIspartofDataproduct> ispartofDataproductsByInstanceId;
    private Collection<EDMIspartofDataproduct> ispartofDataproductsByInstanceId_0;
    private Collection<EDMPublisher> publishersByInstanceId;
    private Collection<EDMIsDistribution> isDistributionsByInstanceId;

    @Basic
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "created", nullable = true)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "issued", nullable = true)
    public Timestamp getIssued() {
        return issued;
    }

    public void setIssued(Timestamp issued) {
        this.issued = issued;
    }

    @Basic
    @Column(name = "modified", nullable = true)
    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    @Basic
    @Column(name = "versioninfo")
    public String getVersioninfo() {
        return versioninfo;
    }

    public void setVersioninfo(String versioninfo) {
        this.versioninfo = versioninfo;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "accrualperiodicity")
    public String getAccrualperiodicity() {
        return accrualperiodicity;
    }

    public void setAccrualperiodicity(String accrualperiodicity) {
        this.accrualperiodicity = accrualperiodicity;
    }

    @Basic
    @Column(name = "keywords")
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Basic
    @Column(name = "accessright")
    public String getAccessright() {
        return accessright;
    }

    public void setAccessright(String accessright) {
        this.accessright = accessright;
    }

    @Basic
    @Column(name = "documentation")
    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    @Basic
    @Column(name = "qualityassurance")
    public String getQualityassurance() {
        return qualityassurance;
    }

    public void setQualityassurance(String qualityassurance) {
        this.qualityassurance = qualityassurance;
    }

    @Basic
    @Column(name = "fileprovenance")
    public String getFileprovenance() {
        return fileprovenance;
    }

    public void setFileprovenance(String fileprovenance) {
        this.fileprovenance = fileprovenance;
    }

    @Id
    @Column(name = "instance_id")
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Basic
    @Column(name = "meta_id", insertable = false, updatable = false)
    public String getMetaId() {
        return metaId;
    }

    public void setMetaId(String metaId) {
        this.metaId = metaId;
    }

    @Basic
    @Column(name = "instance_changed_id", insertable = false, updatable = false)
    public String getInstanceChangedId() {
        return instanceChangedId;
    }

    public void setInstanceChangedId(String instanceChangedId) {
        this.instanceChangedId = instanceChangedId;
    }

    @Basic
    @Column(name = "change_timestamp", nullable = true)
    public Timestamp getChangeTimestamp() {
        return changeTimestamp;
    }

    public void setChangeTimestamp(Timestamp changeTimestamp) {
        this.changeTimestamp = changeTimestamp;
    }

    @Basic
    @Column(name = "operation")
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Basic
    @Column(name = "editor_meta_id", insertable = false, updatable = false)
    public String getEditorMetaId() {
        return editorMetaId;
    }

    public void setEditorMetaId(String editorMetaId) {
        this.editorMetaId = editorMetaId;
    }

    @Basic
    @Column(name = "change_comment")
    public String getChangeComment() {
        return changeComment;
    }

    public void setChangeComment(String changeComment) {
        this.changeComment = changeComment;
    }

    @Basic
    @Column(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "to_be_deleted", nullable = true)
    public Boolean getToBeDeleted() {
        return toBeDeleted;
    }

    public void setToBeDeleted(Boolean toBeDeleted) {
        this.toBeDeleted = toBeDeleted;
    }

    @Basic
    @Column(name = "has_quality_annotation")
    public String getHasQualityAnnotation() {
        return hasQualityAnnotation;
    }

    public void setHasQualityAnnotation(String hasQualityAnnotation) {
        this.hasQualityAnnotation = hasQualityAnnotation;
    }

    @Basic
    @Column(name = "dctidentifier")
    public String getDctidentifier() {
        return dctidentifier;
    }

    public void setDctidentifier(String dctidentifier) {
        this.dctidentifier = dctidentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMDataproduct that = (EDMDataproduct) o;

        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (issued != null ? !issued.equals(that.issued) : that.issued != null) return false;
        if (modified != null ? !modified.equals(that.modified) : that.modified != null) return false;
        if (versioninfo != null ? !versioninfo.equals(that.versioninfo) : that.versioninfo != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (accrualperiodicity != null ? !accrualperiodicity.equals(that.accrualperiodicity) : that.accrualperiodicity != null)
            return false;
        if (keywords != null ? !keywords.equals(that.keywords) : that.keywords != null) return false;
        if (accessright != null ? !accessright.equals(that.accessright) : that.accessright != null) return false;
        if (documentation != null ? !documentation.equals(that.documentation) : that.documentation != null)
            return false;
        if (qualityassurance != null ? !qualityassurance.equals(that.qualityassurance) : that.qualityassurance != null)
            return false;
        if (fileprovenance != null ? !fileprovenance.equals(that.fileprovenance) : that.fileprovenance != null)
            return false;
        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(that.metaId) : that.metaId != null) return false;
        if (instanceChangedId != null ? !instanceChangedId.equals(that.instanceChangedId) : that.instanceChangedId != null)
            return false;
        if (changeTimestamp != null ? !changeTimestamp.equals(that.changeTimestamp) : that.changeTimestamp != null)
            return false;
        if (operation != null ? !operation.equals(that.operation) : that.operation != null) return false;
        if (editorMetaId != null ? !editorMetaId.equals(that.editorMetaId) : that.editorMetaId != null) return false;
        if (changeComment != null ? !changeComment.equals(that.changeComment) : that.changeComment != null)
            return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        return toBeDeleted != null ? toBeDeleted.equals(that.toBeDeleted) : that.toBeDeleted == null;
    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (issued != null ? issued.hashCode() : 0);
        result = 31 * result + (modified != null ? modified.hashCode() : 0);
        result = 31 * result + (versioninfo != null ? versioninfo.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (accrualperiodicity != null ? accrualperiodicity.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (accessright != null ? accessright.hashCode() : 0);
        result = 31 * result + (documentation != null ? documentation.hashCode() : 0);
        result = 31 * result + (qualityassurance != null ? qualityassurance.hashCode() : 0);
        result = 31 * result + (fileprovenance != null ? fileprovenance.hashCode() : 0);
        result = 31 * result + (instanceId != null ? instanceId.hashCode() : 0);
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (instanceChangedId != null ? instanceChangedId.hashCode() : 0);
        result = 31 * result + (changeTimestamp != null ? changeTimestamp.hashCode() : 0);
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        result = 31 * result + (editorMetaId != null ? editorMetaId.hashCode() : 0);
        result = 31 * result + (changeComment != null ? changeComment.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (toBeDeleted != null ? toBeDeleted.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "dataproductByInstanceDataproductId", cascade = CascadeType.ALL)
    public Collection<EDMContactpointDataproduct> getContactpointDataproductsByInstanceId() {
        return contactpointDataproductsByInstanceId;
    }

    public void setContactpointDataproductsByInstanceId(Collection<EDMContactpointDataproduct> contactpointDataproductsByInstanceId) {
        this.contactpointDataproductsByInstanceId = contactpointDataproductsByInstanceId;
    }

    @ManyToOne
    @JoinColumn(name = "meta_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByMetaId() {
        return edmEntityIdByMetaId;
    }

    public void setEdmEntityIdByMetaId(EDMEdmEntityId edmEntityIdByMetaId) {
        this.edmEntityIdByMetaId = edmEntityIdByMetaId;
    }

    @ManyToOne
    @JoinColumn(name = "instance_changed_id", referencedColumnName = "instance_id")
    public EDMDataproduct getDataproductByInstanceChangedId() {
        return dataproductByInstanceChangedId;
    }

    public void setDataproductByInstanceChangedId(EDMDataproduct dataproductByInstanceChangedId) {
        this.dataproductByInstanceChangedId = dataproductByInstanceChangedId;
    }

    @OneToMany(mappedBy = "dataproductByInstanceChangedId")
    public Collection<EDMDataproduct> getDataproductsByInstanceId() {
        return dataproductsByInstanceId;
    }

    public void setDataproductsByInstanceId(Collection<EDMDataproduct> dataproductsByInstanceId) {
        this.dataproductsByInstanceId = dataproductsByInstanceId;
    }

    @ManyToOne
    @JoinColumn(name = "editor_meta_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByEditorMetaId() {
        return edmEntityIdByEditorMetaId;
    }

    public void setEdmEntityIdByEditorMetaId(EDMEdmEntityId edmEntityIdByEditorMetaId) {
        this.edmEntityIdByEditorMetaId = edmEntityIdByEditorMetaId;
    }

    @OneToMany(mappedBy = "dataproductByInstanceDataproductId", cascade = CascadeType.ALL)
    public Collection<EDMDataproductCategory> getDataproductCategoriesByInstanceId() {
        return dataproductCategoriesByInstanceId;
    }

    public void setDataproductCategoriesByInstanceId(Collection<EDMDataproductCategory> dataproductCategoriesByInstanceId) {
        this.dataproductCategoriesByInstanceId = dataproductCategoriesByInstanceId;
    }

    @OneToMany(mappedBy = "dataproductByInstanceDataproductId", cascade = CascadeType.ALL)
    public Collection<EDMDataproductDescription> getDataproductDescriptionsByInstanceId() {
        return dataproductDescriptionsByInstanceId;
    }

    public void setDataproductDescriptionsByInstanceId(Collection<EDMDataproductDescription> dataproductDescriptionsByInstanceId) {
        this.dataproductDescriptionsByInstanceId = dataproductDescriptionsByInstanceId;
    }

    @OneToMany(mappedBy = "dataproductByInstanceDataproductId", cascade = CascadeType.ALL)
    public Collection<EDMDataproductIdentifier> getDataproductIdentifiersByInstanceId() {
        return dataproductIdentifiersByInstanceId;
    }

    public void setDataproductIdentifiersByInstanceId(Collection<EDMDataproductIdentifier> dataproductIdentifiersByInstanceId) {
        this.dataproductIdentifiersByInstanceId = dataproductIdentifiersByInstanceId;
    }

    @OneToMany(mappedBy = "dataproductByInstanceDataproductId", cascade = CascadeType.ALL)
    public Collection<EDMDataproductProvenance> getDataproductProvenancesByInstanceId() {
        return dataproductProvenancesByInstanceId;
    }

    public void setDataproductProvenancesByInstanceId(Collection<EDMDataproductProvenance> dataproductProvenancesByInstanceId) {
        this.dataproductProvenancesByInstanceId = dataproductProvenancesByInstanceId;
    }

    @OneToMany(mappedBy = "dataproductByInstanceDataproductId", cascade = CascadeType.ALL)
    public Collection<EDMDataproductSpatial> getDataproductSpatialsByInstanceId() {
        return dataproductSpatialsByInstanceId;
    }

    public void setDataproductSpatialsByInstanceId(Collection<EDMDataproductSpatial> dataproductSpatialsByInstanceId) {
        this.dataproductSpatialsByInstanceId = dataproductSpatialsByInstanceId;
    }

    @OneToMany(mappedBy = "dataproductByInstanceDataproductId", cascade = CascadeType.ALL)
    public Collection<EDMDataproductTemporal> getDataproductTemporalsByInstanceId() {
        return dataproductTemporalsByInstanceId;
    }

    public void setDataproductTemporalsByInstanceId(Collection<EDMDataproductTemporal> dataproductTemporalsByInstanceId) {
        this.dataproductTemporalsByInstanceId = dataproductTemporalsByInstanceId;
    }

    @OneToMany(mappedBy = "dataproductByInstanceDataproductId", cascade = CascadeType.ALL)
    public Collection<EDMDataproductTitle> getDataproductTitlesByInstanceId() {
        return dataproductTitlesByInstanceId;
    }

    public void setDataproductTitlesByInstanceId(Collection<EDMDataproductTitle> dataproductTitlesByInstanceId) {
        this.dataproductTitlesByInstanceId = dataproductTitlesByInstanceId;
    }

    @OneToMany(mappedBy = "dataproductByInstanceDataproduct1Id", cascade = CascadeType.ALL)
    public Collection<EDMHaspartDataproduct> getHaspartDataproductsByInstanceId() {
        return haspartDataproductsByInstanceId;
    }

    public void setHaspartDataproductsByInstanceId(Collection<EDMHaspartDataproduct> haspartDataproductsByInstanceId) {
        this.haspartDataproductsByInstanceId = haspartDataproductsByInstanceId;
    }

    @OneToMany(mappedBy = "dataproductByInstanceDataproduct1Id", cascade = CascadeType.ALL)
    public Collection<EDMIspartofDataproduct> getIspartofDataproductsByInstanceId() {
        return ispartofDataproductsByInstanceId;
    }

    public void setIspartofDataproductsByInstanceId(Collection<EDMIspartofDataproduct> ispartofDataproductsByInstanceId) {
        this.ispartofDataproductsByInstanceId = ispartofDataproductsByInstanceId;
    }

    @OneToMany(mappedBy = "dataproductByInstanceDataproductId", cascade = CascadeType.ALL)
    public Collection<EDMPublisher> getPublishersByInstanceId() {
        return publishersByInstanceId;
    }

    public void setPublishersByInstanceId(Collection<EDMPublisher> publishersByInstanceId) {
        this.publishersByInstanceId = publishersByInstanceId;
    }

    @OneToMany(mappedBy = "dataproductByInstanceDataproductId", cascade = CascadeType.ALL)
    public Collection<EDMIsDistribution> getIsDistributionsByInstanceId() {
        return isDistributionsByInstanceId;
    }

    public void setIsDistributionsByInstanceId(Collection<EDMIsDistribution> isDistributionsByInstanceId) {
        this.isDistributionsByInstanceId = isDistributionsByInstanceId;
    }

    @OneToMany(mappedBy = "dataproductByInstanceDataproduct2Id", cascade = CascadeType.ALL)
    public Collection<EDMHaspartDataproduct> getHaspartDataproductsByInstanceId_0() {
        return haspartDataproductsByInstanceId_0;
    }

    public void setHaspartDataproductsByInstanceId_0(Collection<EDMHaspartDataproduct> haspartDataproductsByInstanceId_0) {
        this.haspartDataproductsByInstanceId_0 = haspartDataproductsByInstanceId_0;
    }

    @OneToMany(mappedBy = "dataproductByInstanceDataproduct2Id", cascade = CascadeType.ALL)
    public Collection<EDMIspartofDataproduct> getIspartofDataproductsByInstanceId_0() {
        return ispartofDataproductsByInstanceId_0;
    }

    public void setIspartofDataproductsByInstanceId_0(Collection<EDMIspartofDataproduct> ispartofDataproductsByInstanceId_0) {
        this.ispartofDataproductsByInstanceId_0 = ispartofDataproductsByInstanceId_0;
    }
}
