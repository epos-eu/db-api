package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "webservice")
@NamedQueries({
        @NamedQuery(name = "webservice.findAll", query = "SELECT c FROM EDMWebservice c"),
        @NamedQuery(name = "webservice.findAllByMetaId", query = "SELECT c FROM EDMWebservice c where c.metaId = :METAID"),
        @NamedQuery(name = "webservice.findAllByState", query = "SELECT c FROM EDMWebservice c where c.state = :STATE"),
        @NamedQuery(name = "webservice.findByUidAndState", query = "select c from EDMWebservice c where c.uid = :UID and c.state = :STATE"),
        @NamedQuery(name = "webservice.findByUid", query = "select c from EDMWebservice c where c.uid = :UID"),
        @NamedQuery(name = "webservice.findByFileProvenance", query = "select c from EDMWebservice c where c.fileprovenance = :FPROV"),
        @NamedQuery(name = "webservice.findByInstanceId", query = "select c from EDMWebservice c where c.instanceId = :INSTANCEID")
})
public class EDMWebservice {
    private String uid;
    private String description;
    private String schemaIdentifier;
    private String name;
    private String entrypoint;
    private Timestamp datapublished;
    private Timestamp datamodified;
    private String keywords;
    private String license;
    private String provider;
    private String aaaitypes;
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
    private Collection<EDMContactpointWebservice> contactpointWebservicesByInstanceId;
    private EDMEdmEntityId edmEntityIdByProvider;
    private EDMEdmEntityId edmEntityIdByMetaId;
    private EDMWebservice webserviceByInstanceChangedId;
    private Collection<EDMWebservice> webservicesByInstanceId;
    private EDMEdmEntityId edmEntityIdByEditorMetaId;
    private Collection<EDMDistribution> distributionByInstanceId;
    private Collection<EDMWebserviceCategory> webserviceCategoriesByInstanceId;
    private Collection<EDMWebserviceDocumentation> webserviceDocumentationsByInstanceId;
    private Collection<EDMWebserviceSpatial> webserviceSpatialsByInstanceId;
    private Collection<EDMWebserviceTemporal> webserviceTemporalsByInstanceId;
    private Collection<EDMSupportedOperation> supportedOperationByInstanceId;
    private Collection<EDMWebserviceIdentifier> webserviceIdentifiersByIduid;

    @Basic
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "schema_identifier")
    public String getSchemaIdentifier() {
        return schemaIdentifier;
    }

    public void setSchemaIdentifier(String schemaIdentifier) {
        this.schemaIdentifier = schemaIdentifier;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "entrypoint")
    public String getEntrypoint() {
        return entrypoint;
    }

    public void setEntrypoint(String entrypoint) {
        this.entrypoint = entrypoint;
    }

    @Basic
    @Column(name = "datapublished", nullable = true)
    public Timestamp getDatapublished() {
        return datapublished;
    }

    public void setDatapublished(Timestamp datapublished) {
        this.datapublished = datapublished;
    }

    @Basic
    @Column(name = "datamodified", nullable = true)
    public Timestamp getDatamodified() {
        return datamodified;
    }

    public void setDatamodified(Timestamp datamodified) {
        this.datamodified = datamodified;
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
    @Column(name = "license")
    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @Basic
    @Column(name = "provider", insertable = false, updatable = false)
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Basic
    @Column(name = "aaaitypes")
    public String getAaaitypes() {
        return aaaitypes;
    }

    public void setAaaitypes(String aaaitypes) {
        this.aaaitypes = aaaitypes;
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

        EDMWebservice that = (EDMWebservice) o;

        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (entrypoint != null ? !entrypoint.equals(that.entrypoint) : that.entrypoint != null) return false;
        if (datapublished != null ? !datapublished.equals(that.datapublished) : that.datapublished != null)
            return false;
        if (datamodified != null ? !datamodified.equals(that.datamodified) : that.datamodified != null) return false;
        if (keywords != null ? !keywords.equals(that.keywords) : that.keywords != null) return false;
        if (license != null ? !license.equals(that.license) : that.license != null) return false;
        if (provider != null ? !provider.equals(that.provider) : that.provider != null) return false;
        if (aaaitypes != null ? !aaaitypes.equals(that.aaaitypes) : that.aaaitypes != null) return false;
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
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (entrypoint != null ? entrypoint.hashCode() : 0);
        result = 31 * result + (datapublished != null ? datapublished.hashCode() : 0);
        result = 31 * result + (datamodified != null ? datamodified.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (license != null ? license.hashCode() : 0);
        result = 31 * result + (provider != null ? provider.hashCode() : 0);
        result = 31 * result + (aaaitypes != null ? aaaitypes.hashCode() : 0);
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

    @OneToMany(mappedBy = "webserviceByInstanceWebserviceId", cascade = CascadeType.ALL)
    public Collection<EDMContactpointWebservice> getContactpointWebservicesByInstanceId() {
        return contactpointWebservicesByInstanceId;
    }

    public void setContactpointWebservicesByInstanceId(Collection<EDMContactpointWebservice> contactpointWebservicesByInstanceId) {
        this.contactpointWebservicesByInstanceId = contactpointWebservicesByInstanceId;
    }

    @ManyToOne
    @JoinColumn(name = "provider", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByProvider() {
        return edmEntityIdByProvider;
    }

    public void setEdmEntityIdByProvider(EDMEdmEntityId edmEntityIdByProvider) {
        this.edmEntityIdByProvider = edmEntityIdByProvider;
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
    public EDMWebservice getWebserviceByInstanceChangedId() {
        return webserviceByInstanceChangedId;
    }

    public void setWebserviceByInstanceChangedId(EDMWebservice webserviceByInstanceChangedId) {
        this.webserviceByInstanceChangedId = webserviceByInstanceChangedId;
    }

    @OneToMany(mappedBy = "webserviceByInstanceChangedId")
    public Collection<EDMWebservice> getWebservicesByInstanceId() {
        return webservicesByInstanceId;
    }

    public void setWebservicesByInstanceId(Collection<EDMWebservice> webservicesByInstanceId) {
        this.webservicesByInstanceId = webservicesByInstanceId;
    }

    @ManyToOne
    @JoinColumn(name = "editor_meta_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByEditorMetaId() {
        return edmEntityIdByEditorMetaId;
    }

    public void setEdmEntityIdByEditorMetaId(EDMEdmEntityId edmEntityIdByEditorMetaId) {
        this.edmEntityIdByEditorMetaId = edmEntityIdByEditorMetaId;
    }

    @OneToMany(mappedBy = "webserviceByInstanceWebserviceId", cascade = CascadeType.ALL)
    public Collection<EDMWebserviceCategory> getWebserviceCategoriesByInstanceId() {
        return webserviceCategoriesByInstanceId;
    }

    public void setWebserviceCategoriesByInstanceId(Collection<EDMWebserviceCategory> webserviceCategoriesByInstanceId) {
        this.webserviceCategoriesByInstanceId = webserviceCategoriesByInstanceId;
    }

    @OneToMany(mappedBy = "webserviceByInstanceWebserviceId", cascade = CascadeType.ALL)
    public Collection<EDMWebserviceDocumentation> getWebserviceDocumentationsByInstanceId() {
        return webserviceDocumentationsByInstanceId;
    }

    public void setWebserviceDocumentationsByInstanceId(Collection<EDMWebserviceDocumentation> webserviceDocumentationsByInstanceId) {
        this.webserviceDocumentationsByInstanceId = webserviceDocumentationsByInstanceId;
    }

    @OneToMany(mappedBy = "webserviceByInstanceWebserviceId", cascade = CascadeType.ALL)
    public Collection<EDMWebserviceSpatial> getWebserviceSpatialsByInstanceId() {
        return webserviceSpatialsByInstanceId;
    }

    public void setWebserviceSpatialsByInstanceId(Collection<EDMWebserviceSpatial> webserviceSpatialsByInstanceId) {
        this.webserviceSpatialsByInstanceId = webserviceSpatialsByInstanceId;
    }

    @OneToMany(mappedBy = "webserviceByInstanceWebserviceId", cascade = CascadeType.ALL)
    public Collection<EDMWebserviceTemporal> getWebserviceTemporalsByInstanceId() {
        return webserviceTemporalsByInstanceId;
    }

    public void setWebserviceTemporalsByInstanceId(Collection<EDMWebserviceTemporal> webserviceTemporalsByInstanceId) {
        this.webserviceTemporalsByInstanceId = webserviceTemporalsByInstanceId;
    }

    @OneToMany(mappedBy = "webserviceByInstanceWebserviceId", cascade = CascadeType.ALL)
    public Collection<EDMSupportedOperation> getSupportedOperationByInstanceId() {
        return supportedOperationByInstanceId;
    }

    public void setSupportedOperationByInstanceId(Collection<EDMSupportedOperation> supportedOperationByInstanceId) {
        this.supportedOperationByInstanceId = supportedOperationByInstanceId;
    }

    @OneToMany(mappedBy = "webserviceByAccessService", cascade = CascadeType.ALL)
    public Collection<EDMDistribution> getDistributionByInstanceId() {
        return distributionByInstanceId;
    }

    public void setDistributionByInstanceId(Collection<EDMDistribution> distributionByInstanceId) {
        this.distributionByInstanceId = distributionByInstanceId;
    }

    @OneToMany(mappedBy = "webserviceByWebserviceId", cascade = CascadeType.ALL)
    public Collection<EDMWebserviceIdentifier> getWebserviceIdentifiersByIduid() {
        return webserviceIdentifiersByIduid;
    }

    public void setWebserviceIdentifiersByIduid(Collection<EDMWebserviceIdentifier> webserviceIdentifiersByIduid) {
        this.webserviceIdentifiersByIduid = webserviceIdentifiersByIduid;
    }
}
