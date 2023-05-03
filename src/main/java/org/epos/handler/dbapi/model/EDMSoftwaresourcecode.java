package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "softwaresourcecode")
@NamedQueries({
        @NamedQuery(name = "softwaresourcecode.findAll", query = "SELECT c FROM EDMSoftwaresourcecode c"),
        @NamedQuery(name = "softwaresourcecode.findAllByMetaId", query = "SELECT c FROM EDMSoftwaresourcecode c where c.metaId = :METAID"),
        @NamedQuery(name = "softwaresourcecode.findAllByState", query = "SELECT c FROM EDMSoftwaresourcecode c where c.state = :STATE"),
        @NamedQuery(name = "softwaresourcecode.findByUidAndState", query = "select c from EDMSoftwaresourcecode c where c.uid = :UID and c.state = :STATE"),
        @NamedQuery(name = "softwaresourcecode.findByUid", query = "select c from EDMSoftwaresourcecode c where c.uid = :UID"),
        @NamedQuery(name = "softwaresourcecode.findByFileProvenance", query = "select c from EDMSoftwaresourcecode c where c.fileprovenance = :FPROV"),
        @NamedQuery(name = "softwaresourcecode.findByInstanceId", query = "select c from EDMSoftwaresourcecode c where c.instanceId = :INSTANCEID")
})
public class EDMSoftwaresourcecode {
    private String uid;
    private String name;
    private String description;
    private String licenseurl;
    private String downloadurl;
    private String runtimeplatform;
    private String softwareversion;
    private String keywords;
    private String coderepository;
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
    private Collection<EDMContactpointSoftwaresourcecode> contactpointSoftwaresourcecodesByInstanceId;
    private EDMEdmEntityId edmEntityIdByMetaId;
    private EDMSoftwaresourcecode softwaresourcecodeByInstanceChangedId;
    private Collection<EDMSoftwaresourcecode> softwaresourcecodesByInstanceId;
    private EDMEdmEntityId edmEntityIdByEditorMetaId;
    private Collection<EDMSoftwaresourcecodeCategory> softwaresourcecodeCategoriesByInstanceId;
    private Collection<EDMSoftwaresourcecodeIdentifier> softwaresourcecodeIdentifiersByInstanceId;
    private Collection<EDMSoftwaresourcecodeProgramminglanguage> softwaresourcecodeProgramminglanguagesByInstanceId;

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
    @Column(name = "licenseurl")
    public String getLicenseurl() {
        return licenseurl;
    }

    public void setLicenseurl(String licenseurl) {
        this.licenseurl = licenseurl;
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
    @Column(name = "runtimeplatform")
    public String getRuntimeplatform() {
        return runtimeplatform;
    }

    public void setRuntimeplatform(String runtimeplatform) {
        this.runtimeplatform = runtimeplatform;
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
    @Column(name = "coderepository")
    public String getCoderepository() {
        return coderepository;
    }

    public void setCoderepository(String coderepository) {
        this.coderepository = coderepository;
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

        EDMSoftwaresourcecode that = (EDMSoftwaresourcecode) o;

        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (licenseurl != null ? !licenseurl.equals(that.licenseurl) : that.licenseurl != null) return false;
        if (downloadurl != null ? !downloadurl.equals(that.downloadurl) : that.downloadurl != null) return false;
        if (runtimeplatform != null ? !runtimeplatform.equals(that.runtimeplatform) : that.runtimeplatform != null)
            return false;
        if (softwareversion != null ? !softwareversion.equals(that.softwareversion) : that.softwareversion != null)
            return false;
        if (keywords != null ? !keywords.equals(that.keywords) : that.keywords != null) return false;
        if (coderepository != null ? !coderepository.equals(that.coderepository) : that.coderepository != null)
            return false;
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
        result = 31 * result + (licenseurl != null ? licenseurl.hashCode() : 0);
        result = 31 * result + (downloadurl != null ? downloadurl.hashCode() : 0);
        result = 31 * result + (runtimeplatform != null ? runtimeplatform.hashCode() : 0);
        result = 31 * result + (softwareversion != null ? softwareversion.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (coderepository != null ? coderepository.hashCode() : 0);
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

    @OneToMany(mappedBy = "softwaresourcecodeByInstanceSoftwaresourcecodeId", cascade = CascadeType.ALL)
    public Collection<EDMContactpointSoftwaresourcecode> getContactpointSoftwaresourcecodesByInstanceId() {
        return contactpointSoftwaresourcecodesByInstanceId;
    }

    public void setContactpointSoftwaresourcecodesByInstanceId(Collection<EDMContactpointSoftwaresourcecode> contactpointSoftwaresourcecodesByInstanceId) {
        this.contactpointSoftwaresourcecodesByInstanceId = contactpointSoftwaresourcecodesByInstanceId;
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
    public EDMSoftwaresourcecode getSoftwaresourcecodeByInstanceChangedId() {
        return softwaresourcecodeByInstanceChangedId;
    }

    public void setSoftwaresourcecodeByInstanceChangedId(EDMSoftwaresourcecode softwaresourcecodeByInstanceChangedId) {
        this.softwaresourcecodeByInstanceChangedId = softwaresourcecodeByInstanceChangedId;
    }

    @OneToMany(mappedBy = "softwaresourcecodeByInstanceChangedId")
    public Collection<EDMSoftwaresourcecode> getSoftwaresourcecodesByInstanceId() {
        return softwaresourcecodesByInstanceId;
    }

    public void setSoftwaresourcecodesByInstanceId(Collection<EDMSoftwaresourcecode> softwaresourcecodesByInstanceId) {
        this.softwaresourcecodesByInstanceId = softwaresourcecodesByInstanceId;
    }

    @ManyToOne
    @JoinColumn(name = "editor_meta_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByEditorMetaId() {
        return edmEntityIdByEditorMetaId;
    }

    public void setEdmEntityIdByEditorMetaId(EDMEdmEntityId edmEntityIdByEditorMetaId) {
        this.edmEntityIdByEditorMetaId = edmEntityIdByEditorMetaId;
    }

    @OneToMany(mappedBy = "softwaresourcecodeByInstanceSoftwaresourcecodeId", cascade = CascadeType.ALL)
    public Collection<EDMSoftwaresourcecodeCategory> getSoftwaresourcecodeCategoriesByInstanceId() {
        return softwaresourcecodeCategoriesByInstanceId;
    }

    public void setSoftwaresourcecodeCategoriesByInstanceId(Collection<EDMSoftwaresourcecodeCategory> softwaresourcecodeCategoriesByInstanceId) {
        this.softwaresourcecodeCategoriesByInstanceId = softwaresourcecodeCategoriesByInstanceId;
    }

    @OneToMany(mappedBy = "softwaresourcecodeByInstanceSoftwaresourcecodeId", cascade = CascadeType.ALL)
    public Collection<EDMSoftwaresourcecodeIdentifier> getSoftwaresourcecodeIdentifiersByInstanceId() {
        return softwaresourcecodeIdentifiersByInstanceId;
    }

    public void setSoftwaresourcecodeIdentifiersByInstanceId(Collection<EDMSoftwaresourcecodeIdentifier> softwaresourcecodeIdentifiersByInstanceId) {
        this.softwaresourcecodeIdentifiersByInstanceId = softwaresourcecodeIdentifiersByInstanceId;
    }

    @OneToMany(mappedBy = "softwaresourcecodeByInstanceSoftwaresourcecodeId", cascade = CascadeType.ALL)
    public Collection<EDMSoftwaresourcecodeProgramminglanguage> getSoftwaresourcecodeProgramminglanguagesByInstanceId() {
        return softwaresourcecodeProgramminglanguagesByInstanceId;
    }

    public void setSoftwaresourcecodeProgramminglanguagesByInstanceId(Collection<EDMSoftwaresourcecodeProgramminglanguage> softwaresourcecodeProgramminglanguagesByInstanceId) {
        this.softwaresourcecodeProgramminglanguagesByInstanceId = softwaresourcecodeProgramminglanguagesByInstanceId;
    }
}
