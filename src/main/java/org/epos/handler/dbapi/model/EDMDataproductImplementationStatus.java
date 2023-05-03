package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "dataproduct_implementation_status")
@NamedQueries({
        @NamedQuery(name = "dataproductimplementationstatus.findAll", query = "SELECT c FROM EDMDataproductImplementationStatus c"),
        @NamedQuery(name = "dataproductimplementationstatus.findAllByMetaId", query = "SELECT c FROM EDMDataproductImplementationStatus c where c.metaId = :METAID"),
        @NamedQuery(name = "dataproductimplementationstatus.findByInstanceId", query = "SELECT c FROM EDMDataproductImplementationStatus c where c.instanceId = :INSTANCEID"),
        @NamedQuery(name = "dataproductimplementationstatus.findAllByState", query = "SELECT c FROM EDMDataproductImplementationStatus c where c.state = :STATE"),
        @NamedQuery(name = "dataproductimplementationstatus.findByDataProviderUidAndDataProductUid", query = "SELECT c FROM EDMDataproductImplementationStatus c where c.metaDataproductId = :METADATAPRODUCTID and c.metaDataproviderId = :METADATAPROVIDER"),
        @NamedQuery(name = "dataproductimplementationstatus.findByDataProviderUidAndDataProductUidAndState", query = "SELECT c FROM EDMDataproductImplementationStatus c where c.metaDataproductId = :METADATAPRODUCTID and c.metaDataproviderId = :METADATAPROVIDER and c.state = :STATE"),
})
public class EDMDataproductImplementationStatus {
    private String metaDataproviderId;
    private String metaDataproductId;
    private String status;
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
    private EDMEdmEntityId edmEntityIdByMetaDataproviderId;
    private EDMEdmEntityId edmEntityIdByMetaDataproductId;
    private EDMEdmEntityId edmEntityIdByMetaId;
    private EDMDataproductImplementationStatus dataproductImplementationStatusByInstanceChangedId;
    private Collection<EDMDataproductImplementationStatus> dataproductImplementationStatusesByInstanceId;
    private EDMEdmEntityId edmEntityIdByEditorMetaId;

    @Basic
    @Column(name = "meta_dataprovider_id", insertable = false, updatable = false)
    public String getMetaDataproviderId() {
        return metaDataproviderId;
    }

    public void setMetaDataproviderId(String metaDataproviderId) {
        this.metaDataproviderId = metaDataproviderId;
    }

    @Basic
    @Column(name = "meta_dataproduct_id", insertable = false, updatable = false)
    public String getMetaDataproductId() {
        return metaDataproductId;
    }

    public void setMetaDataproductId(String metaDataproductId) {
        this.metaDataproductId = metaDataproductId;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

        EDMDataproductImplementationStatus that = (EDMDataproductImplementationStatus) o;

        if (metaDataproviderId != null ? !metaDataproviderId.equals(that.metaDataproviderId) : that.metaDataproviderId != null)
            return false;
        if (metaDataproductId != null ? !metaDataproductId.equals(that.metaDataproductId) : that.metaDataproductId != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
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
        int result = metaDataproviderId != null ? metaDataproviderId.hashCode() : 0;
        result = 31 * result + (metaDataproductId != null ? metaDataproductId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
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
    @JoinColumn(name = "meta_dataprovider_id", referencedColumnName = "meta_id", nullable = false)
    public EDMEdmEntityId getEdmEntityIdByMetaDataproviderId() {
        return edmEntityIdByMetaDataproviderId;
    }

    public void setEdmEntityIdByMetaDataproviderId(EDMEdmEntityId edmEntityIdByMetaDataproviderId) {
        this.edmEntityIdByMetaDataproviderId = edmEntityIdByMetaDataproviderId;
    }

    @ManyToOne
    @JoinColumn(name = "meta_dataproduct_id", referencedColumnName = "meta_id", nullable = false)
    public EDMEdmEntityId getEdmEntityIdByMetaDataproviderId_0() {
        return edmEntityIdByMetaDataproductId;
    }

    public void setEdmEntityIdByMetaDataproviderId_0(EDMEdmEntityId edmEntityIdByMetaDataproviderId_0) {
        this.edmEntityIdByMetaDataproductId = edmEntityIdByMetaDataproviderId_0;
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
    public EDMDataproductImplementationStatus getDataproductImplementationStatusByInstanceChangedId() {
        return dataproductImplementationStatusByInstanceChangedId;
    }

    public void setDataproductImplementationStatusByInstanceChangedId(EDMDataproductImplementationStatus dataproductImplementationStatusByInstanceChangedId) {
        this.dataproductImplementationStatusByInstanceChangedId = dataproductImplementationStatusByInstanceChangedId;
    }

    @OneToMany(mappedBy = "dataproductImplementationStatusByInstanceChangedId")
    public Collection<EDMDataproductImplementationStatus> getDataproductImplementationStatusesByInstanceId() {
        return dataproductImplementationStatusesByInstanceId;
    }

    public void setDataproductImplementationStatusesByInstanceId(Collection<EDMDataproductImplementationStatus> dataproductImplementationStatusesByInstanceId) {
        this.dataproductImplementationStatusesByInstanceId = dataproductImplementationStatusesByInstanceId;
    }

    @ManyToOne
    @JoinColumn(name = "editor_meta_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByEditorMetaId() {
        return edmEntityIdByEditorMetaId;
    }

    public void setEdmEntityIdByEditorMetaId(EDMEdmEntityId edmEntityIdByEditorMetaId) {
        this.edmEntityIdByEditorMetaId = edmEntityIdByEditorMetaId;
    }
}