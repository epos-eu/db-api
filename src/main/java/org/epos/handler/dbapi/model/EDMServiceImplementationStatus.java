package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "service_implementation_status")
@NamedQueries({
        @NamedQuery(name = "serviceimplementationstatus.findAll", query = "SELECT c FROM EDMServiceImplementationStatus c"),
        @NamedQuery(name = "serviceimplementationstatus.findAllByMetaId", query = "SELECT c FROM EDMServiceImplementationStatus c where c.metaId = :METAID"),
        @NamedQuery(name = "serviceimplementationstatus.findByInstanceId", query = "SELECT c FROM EDMServiceImplementationStatus c where c.instanceId = :INSTANCEID"),
        @NamedQuery(name = "serviceimplementationstatus.findAllByState", query = "SELECT c FROM EDMServiceImplementationStatus c where c.state = :STATE"),
        @NamedQuery(name = "serviceimplementationstatus.findByServiceUidAndServiceProviderUid", query = "SELECT c FROM EDMServiceImplementationStatus c where c.metaServiceId = :METASERVICEPROVIDE and c.metaServiceproviderId = :METASERVICEPROVIDER"),
        @NamedQuery(name = "serviceimplementationstatus.findByServiceUidAndServiceProviderUidAndState", query = "SELECT c FROM EDMServiceImplementationStatus c where c.metaServiceId = :METASERVICEPROVIDE and c.metaServiceproviderId = :METASERVICEPROVIDER and c.state = :STATE"),
})
public class EDMServiceImplementationStatus {
    private String metaServiceproviderId;
    private String metaServiceId;
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
    private EDMEdmEntityId edmEntityIdByMetaServiceproviderId;
    private EDMEdmEntityId edmEntityIdByMetaServiceId;
    private EDMEdmEntityId edmEntityIdByMetaId;
    private EDMServiceImplementationStatus serviceImplementationStatusByInstanceChangedId;
    private Collection<EDMServiceImplementationStatus> serviceImplementationStatusesByInstanceId;
    private EDMEdmEntityId edmEntityIdByEditorMetaId;

    @Basic
    @Column(name = "meta_serviceprovider_id", insertable = false, updatable = false)
    public String getMetaServiceproviderId() {
        return metaServiceproviderId;
    }

    public void setMetaServiceproviderId(String metaServiceproviderId) {
        this.metaServiceproviderId = metaServiceproviderId;
    }

    @Basic
    @Column(name = "meta_service_id", insertable = false, updatable = false)
    public String getMetaServiceId() {
        return metaServiceId;
    }

    public void setMetaServiceId(String metaServiceId) {
        this.metaServiceId = metaServiceId;
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
    @Column(name = "change_timestamp")
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

        EDMServiceImplementationStatus that = (EDMServiceImplementationStatus) o;

        if (metaServiceproviderId != null ? !metaServiceproviderId.equals(that.metaServiceproviderId) : that.metaServiceproviderId != null)
            return false;
        if (metaServiceId != null ? !metaServiceId.equals(that.metaServiceId) : that.metaServiceId != null)
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
        int result = metaServiceproviderId != null ? metaServiceproviderId.hashCode() : 0;
        result = 31 * result + (metaServiceId != null ? metaServiceId.hashCode() : 0);
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
    @JoinColumn(name = "meta_serviceprovider_id", referencedColumnName = "meta_id", nullable = false)
    public EDMEdmEntityId getEdmEntityIdByMetaServiceproviderId() {
        return edmEntityIdByMetaServiceproviderId;
    }

    public void setEdmEntityIdByMetaServiceproviderId(EDMEdmEntityId edmEntityIdByMetaServiceproviderId) {
        this.edmEntityIdByMetaServiceproviderId = edmEntityIdByMetaServiceproviderId;
    }

    @ManyToOne
    @JoinColumn(name = "meta_service_id", referencedColumnName = "meta_id", nullable = false)
    public EDMEdmEntityId getEdmEntityIdByMetaServiceId() {
        return edmEntityIdByMetaServiceId;
    }

    public void setEdmEntityIdByMetaServiceId(EDMEdmEntityId edmEntityIdByMetaServiceId) {
        this.edmEntityIdByMetaServiceId = edmEntityIdByMetaServiceId;
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
    public EDMServiceImplementationStatus getServiceImplementationStatusByInstanceChangedId() {
        return serviceImplementationStatusByInstanceChangedId;
    }

    public void setServiceImplementationStatusByInstanceChangedId(EDMServiceImplementationStatus serviceImplementationStatusByInstanceChangedId) {
        this.serviceImplementationStatusByInstanceChangedId = serviceImplementationStatusByInstanceChangedId;
    }

    @OneToMany(mappedBy = "serviceImplementationStatusByInstanceChangedId")
    public Collection<EDMServiceImplementationStatus> getServiceImplementationStatusesByInstanceId() {
        return serviceImplementationStatusesByInstanceId;
    }

    public void setServiceImplementationStatusesByInstanceId(Collection<EDMServiceImplementationStatus> serviceImplementationStatusesByInstanceId) {
        this.serviceImplementationStatusesByInstanceId = serviceImplementationStatusesByInstanceId;
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