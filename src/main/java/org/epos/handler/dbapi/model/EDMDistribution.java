package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "distribution")
@NamedQueries({
        @NamedQuery(name = "distribution.findAll", query = "SELECT c FROM EDMDistribution c"),
        @NamedQuery(name = "distribution.findAllByState", query = "SELECT c FROM EDMDistribution c where c.state = :STATE"),
        @NamedQuery(name = "distribution.findByUidAndState", query = "select c from EDMDistribution c where c.uid = :UID and c.state = :STATE"),
        @NamedQuery(name = "distribution.findByUid", query = "select c from EDMDistribution c where c.uid = :UID"),
        @NamedQuery(name = "distribution.findByInstanceId", query = "select c from EDMDistribution c where c.instanceId = :INSTANCEID"),
        @NamedQuery(name = "distribution.findByFileProvenance", query = "select c from EDMDistribution c where c.fileprovenance = :FPROV"),
        @NamedQuery(name = "distribution.findAllByMetaId", query = "select c from EDMDistribution c where c.metaId = :METAID")
})
public class EDMDistribution {
    private String uid;
    private Timestamp issued;
    private Timestamp modified;
    private String type;
    private String format;
    private String license;
    private String accessService;
    private String datapolicy;
    private String conformsto;
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
    private EDMWebservice webserviceByAccessService;
    private EDMEdmEntityId edmEntityIdByMetaId;
    private EDMDistribution distributionByInstanceChangedId;
    private Collection<EDMDistribution> distributionsByInstanceId;
    private EDMEdmEntityId edmEntityIdByEditorMetaId;
    private Collection<EDMDistributionAccessurl> distributionAccessurlsByInstanceId;
    private Collection<EDMDistributionDescription> distributionDescriptionsByInstanceId;
    private Collection<EDMDistributionDownloadurl> distributionDownloadurlsByInstanceId;
    private Collection<EDMDistributionTitle> distributionTitlesByInstanceId;
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "format")
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Basic
    @Column(name = "license")
    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @Basic
    @Column(name = "access_service", insertable = false, updatable = false)
    public String getAccessService() {
        return accessService;
    }

    public void setAccessService(String accessService) {
        this.accessService = accessService;
    }

    @Basic
    @Column(name = "datapolicy")
    public String getDatapolicy() {
        return datapolicy;
    }

    public void setDatapolicy(String datapolicy) {
        this.datapolicy = datapolicy;
    }

    @Basic
    @Column(name = "conformsto")
    public String getConformsto() {
        return conformsto;
    }

    public void setConformsto(String conformsto) {
        this.conformsto = conformsto;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMDistribution that = (EDMDistribution) o;

        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (issued != null ? !issued.equals(that.issued) : that.issued != null) return false;
        if (modified != null ? !modified.equals(that.modified) : that.modified != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (format != null ? !format.equals(that.format) : that.format != null) return false;
        if (license != null ? !license.equals(that.license) : that.license != null) return false;
        if (accessService != null ? !accessService.equals(that.accessService) : that.accessService != null)
            return false;
        if (datapolicy != null ? !datapolicy.equals(that.datapolicy) : that.datapolicy != null) return false;
        if (conformsto != null ? !conformsto.equals(that.conformsto) : that.conformsto != null) return false;
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
        result = 31 * result + (issued != null ? issued.hashCode() : 0);
        result = 31 * result + (modified != null ? modified.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (format != null ? format.hashCode() : 0);
        result = 31 * result + (license != null ? license.hashCode() : 0);
        result = 31 * result + (accessService != null ? accessService.hashCode() : 0);
        result = 31 * result + (datapolicy != null ? datapolicy.hashCode() : 0);
        result = 31 * result + (conformsto != null ? conformsto.hashCode() : 0);
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

    @ManyToOne
    @JoinColumn(name = "access_service", referencedColumnName = "instance_id")
    public EDMWebservice getWebserviceByAccessService() {
        return webserviceByAccessService;
    }

    public void setWebserviceByAccessService(EDMWebservice webserviceByAccessService) {
        this.webserviceByAccessService = webserviceByAccessService;
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
    public EDMDistribution getDistributionByInstanceChangedId() {
        return distributionByInstanceChangedId;
    }

    public void setDistributionByInstanceChangedId(EDMDistribution distributionByInstanceChangedId) {
        this.distributionByInstanceChangedId = distributionByInstanceChangedId;
    }

    @OneToMany(mappedBy = "distributionByInstanceChangedId")
    public Collection<EDMDistribution> getDistributionsByInstanceId() {
        return distributionsByInstanceId;
    }

    public void setDistributionsByInstanceId(Collection<EDMDistribution> distributionsByInstanceId) {
        this.distributionsByInstanceId = distributionsByInstanceId;
    }

    @ManyToOne
    @JoinColumn(name = "editor_meta_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByEditorMetaId() {
        return edmEntityIdByEditorMetaId;
    }

    public void setEdmEntityIdByEditorMetaId(EDMEdmEntityId edmEntityIdByEditorMetaId) {
        this.edmEntityIdByEditorMetaId = edmEntityIdByEditorMetaId;
    }

    @OneToMany(mappedBy = "distributionByInstanceDistributionId", cascade = CascadeType.ALL)
    public Collection<EDMDistributionAccessurl> getDistributionAccessurlsByInstanceId() {
        return distributionAccessurlsByInstanceId;
    }

    public void setDistributionAccessurlsByInstanceId(Collection<EDMDistributionAccessurl> distributionAccessurlsByInstanceId) {
        this.distributionAccessurlsByInstanceId = distributionAccessurlsByInstanceId;
    }

    @OneToMany(mappedBy = "distributionByInstanceDistributionId", cascade = CascadeType.ALL)
    public Collection<EDMDistributionDescription> getDistributionDescriptionsByInstanceId() {
        return distributionDescriptionsByInstanceId;
    }

    public void setDistributionDescriptionsByInstanceId(Collection<EDMDistributionDescription> distributionDescriptionsByInstanceId) {
        this.distributionDescriptionsByInstanceId = distributionDescriptionsByInstanceId;
    }

    @OneToMany(mappedBy = "distributionByInstanceDistributionId", cascade = CascadeType.ALL)
    public Collection<EDMDistributionDownloadurl> getDistributionDownloadurlsByInstanceId() {
        return distributionDownloadurlsByInstanceId;
    }

    public void setDistributionDownloadurlsByInstanceId(Collection<EDMDistributionDownloadurl> distributionDownloadurlsByInstanceId) {
        this.distributionDownloadurlsByInstanceId = distributionDownloadurlsByInstanceId;
    }

    @OneToMany(mappedBy = "distributionByInstanceDistributionId", cascade = CascadeType.ALL)
    public Collection<EDMDistributionTitle> getDistributionTitlesByInstanceId() {
        return distributionTitlesByInstanceId;
    }

    public void setDistributionTitlesByInstanceId(Collection<EDMDistributionTitle> distributionTitlesByInstanceId) {
        this.distributionTitlesByInstanceId = distributionTitlesByInstanceId;
    }

    @OneToMany(mappedBy = "distributionByInstanceDistributionId", cascade = CascadeType.ALL)
    public Collection<EDMIsDistribution> getIsDistributionsByInstanceId() {
        return isDistributionsByInstanceId;
    }

    public void setIsDistributionsByInstanceId(Collection<EDMIsDistribution> isDistributionsByInstanceId) {
        this.isDistributionsByInstanceId = isDistributionsByInstanceId;
    }
}
