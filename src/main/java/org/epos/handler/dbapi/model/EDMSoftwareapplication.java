package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "softwareapplication")
@NamedQueries({
        @NamedQuery(name = "softwareapplication.findAll", query = "SELECT c FROM EDMSoftwareapplication c"),
        @NamedQuery(name = "softwareapplication.findAllByMetaId", query = "SELECT c FROM EDMSoftwareapplication c where c.metaId = :METAID"),
        @NamedQuery(name = "softwareapplication.findAllByState", query = "SELECT c FROM EDMSoftwareapplication c where c.state = :STATE"),
        @NamedQuery(name = "softwareapplication.findByUid", query = "select c from EDMSoftwareapplication c where c.uid = :UID"),
        @NamedQuery(name = "softwareapplication.findByUidAndState", query = "select c from EDMSoftwareapplication c where c.uid = :UID and c.state = :STATE"),
        @NamedQuery(name = "softwareapplication.findByFileProvenance", query = "select c from EDMSoftwareapplication c where c.fileprovenance = :FPROV"),
        @NamedQuery(name = "softwareapplication.findByInstanceId", query = "select c from EDMSoftwareapplication c where c.instanceId = :INSTANCEID")
})
public class EDMSoftwareapplication {
    private String uid;
    private String name;
    private String description;
    private String downloadurl;
    private String licenseurl;
    private String softwareversion;
    private String keywords;
    private String requirements;
    private String installurl;
    private String mainentityofpage;
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
    private Collection<EDMContactpointSoftwareapplication> contactpointSoftwareapplicationsByInstanceId;
    private EDMEdmEntityId edmEntityIdByMetaId;
    private EDMSoftwareapplication softwareapplicationByInstanceChangedId;
    private Collection<EDMSoftwareapplication> softwareapplicationsByInstanceId;
    private EDMEdmEntityId edmEntityIdByEditorMetaId;
    private Collection<EDMSoftwareapplicationCategory> softwareapplicationCategoriesByInstanceId;
    private Collection<EDMSoftwareapplicationIdentifier> softwareapplicationIdentifiersByInstanceId;
    private Collection<EDMSoftwareapplicationOperation> softwareapplicationOperationsByInstanceId;
    private Collection<EDMSoftwareapplicationParameters> softwareapplicationParametersByInstanceId;

    @Basic
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "downloadurl")
    public String getDownloadurl() {
        return downloadurl;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl;
    }

    @Basic
    @Column(name = "licenseurl")
    public String getLicenseurl() {
        return licenseurl;
    }

    public void setLicenseurl(String licenseurl) {
        this.licenseurl = licenseurl;
    }

    @Basic
    @Column(name = "softwareversion")
    public String getSoftwareversion() {
        return softwareversion;
    }

    public void setSoftwareversion(String softwareversion) {
        this.softwareversion = softwareversion;
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
    @Column(name = "requirements")
    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    @Basic
    @Column(name = "installurl")
    public String getInstallurl() {
        return installurl;
    }

    public void setInstallurl(String installurl) {
        this.installurl = installurl;
    }

    @Basic
    @Column(name = "mainentityofpage")
    public String getMainentityofpage() {
        return mainentityofpage;
    }

    public void setMainentityofpage(String mainentityofpage) {
        this.mainentityofpage = mainentityofpage;
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

        EDMSoftwareapplication that = (EDMSoftwareapplication) o;

        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (downloadurl != null ? !downloadurl.equals(that.downloadurl) : that.downloadurl != null) return false;
        if (licenseurl != null ? !licenseurl.equals(that.licenseurl) : that.licenseurl != null) return false;
        if (softwareversion != null ? !softwareversion.equals(that.softwareversion) : that.softwareversion != null)
            return false;
        if (keywords != null ? !keywords.equals(that.keywords) : that.keywords != null) return false;
        if (requirements != null ? !requirements.equals(that.requirements) : that.requirements != null) return false;
        if (installurl != null ? !installurl.equals(that.installurl) : that.installurl != null) return false;
        if (mainentityofpage != null ? !mainentityofpage.equals(that.mainentityofpage) : that.mainentityofpage != null)
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
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (downloadurl != null ? downloadurl.hashCode() : 0);
        result = 31 * result + (licenseurl != null ? licenseurl.hashCode() : 0);
        result = 31 * result + (softwareversion != null ? softwareversion.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (requirements != null ? requirements.hashCode() : 0);
        result = 31 * result + (installurl != null ? installurl.hashCode() : 0);
        result = 31 * result + (mainentityofpage != null ? mainentityofpage.hashCode() : 0);
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

    @OneToMany(mappedBy = "softwareapplicationByInstanceSoftwareapplicationId", cascade = CascadeType.ALL)
    public Collection<EDMContactpointSoftwareapplication> getContactpointSoftwareapplicationsByInstanceId() {
        return contactpointSoftwareapplicationsByInstanceId;
    }

    public void setContactpointSoftwareapplicationsByInstanceId(Collection<EDMContactpointSoftwareapplication> contactpointSoftwareapplicationsByInstanceId) {
        this.contactpointSoftwareapplicationsByInstanceId = contactpointSoftwareapplicationsByInstanceId;
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
    public EDMSoftwareapplication getSoftwareapplicationByInstanceChangedId() {
        return softwareapplicationByInstanceChangedId;
    }

    public void setSoftwareapplicationByInstanceChangedId(EDMSoftwareapplication softwareapplicationByInstanceChangedId) {
        this.softwareapplicationByInstanceChangedId = softwareapplicationByInstanceChangedId;
    }

    @OneToMany(mappedBy = "softwareapplicationByInstanceChangedId")
    public Collection<EDMSoftwareapplication> getSoftwareapplicationsByInstanceId() {
        return softwareapplicationsByInstanceId;
    }

    public void setSoftwareapplicationsByInstanceId(Collection<EDMSoftwareapplication> softwareapplicationsByInstanceId) {
        this.softwareapplicationsByInstanceId = softwareapplicationsByInstanceId;
    }

    @ManyToOne
    @JoinColumn(name = "editor_meta_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByEditorMetaId() {
        return edmEntityIdByEditorMetaId;
    }

    public void setEdmEntityIdByEditorMetaId(EDMEdmEntityId edmEntityIdByEditorMetaId) {
        this.edmEntityIdByEditorMetaId = edmEntityIdByEditorMetaId;
    }

    @OneToMany(mappedBy = "softwareapplicationByInstanceSoftwareapplicationId", cascade = CascadeType.ALL)
    public Collection<EDMSoftwareapplicationCategory> getSoftwareapplicationCategoriesByInstanceId() {
        return softwareapplicationCategoriesByInstanceId;
    }

    public void setSoftwareapplicationCategoriesByInstanceId(Collection<EDMSoftwareapplicationCategory> softwareapplicationCategoriesByInstanceId) {
        this.softwareapplicationCategoriesByInstanceId = softwareapplicationCategoriesByInstanceId;
    }

    @OneToMany(mappedBy = "softwareapplicationByInstanceSoftwareapplicationId", cascade = CascadeType.ALL)
    public Collection<EDMSoftwareapplicationIdentifier> getSoftwareapplicationIdentifiersByInstanceId() {
        return softwareapplicationIdentifiersByInstanceId;
    }

    public void setSoftwareapplicationIdentifiersByInstanceId(Collection<EDMSoftwareapplicationIdentifier> softwareapplicationIdentifiersByInstanceId) {
        this.softwareapplicationIdentifiersByInstanceId = softwareapplicationIdentifiersByInstanceId;
    }

    @OneToMany(mappedBy = "softwareapplicationByInstanceSoftwareapplicationId", cascade = CascadeType.ALL)
    public Collection<EDMSoftwareapplicationOperation> getSoftwareapplicationOperationsByInstanceId() {
        return softwareapplicationOperationsByInstanceId;
    }

    public void setSoftwareapplicationOperationsByInstanceId(Collection<EDMSoftwareapplicationOperation> softwareapplicationOperationsByInstanceId) {
        this.softwareapplicationOperationsByInstanceId = softwareapplicationOperationsByInstanceId;
    }

    @OneToMany(mappedBy = "softwareapplicationByInstanceSoftwareapplicationId", cascade = CascadeType.ALL)
    public Collection<EDMSoftwareapplicationParameters> getSoftwareapplicationParametersByInstanceId() {
        return softwareapplicationParametersByInstanceId;
    }

    public void setSoftwareapplicationParametersByInstanceId(Collection<EDMSoftwareapplicationParameters> softwareapplicationParametersByInstanceId) {
        this.softwareapplicationParametersByInstanceId = softwareapplicationParametersByInstanceId;
    }
}
