package org.epos.handler.dbapi.model;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "operation")
@NamedQueries({
        @NamedQuery(name = "operation.findAll", query = "SELECT c FROM EDMOperation c"),
        @NamedQuery(name = "operation.findAllByMetaId", query = "SELECT c FROM EDMOperation c where c.metaId = :METAID"),
        @NamedQuery(name = "operation.findAllByState", query = "SELECT c FROM EDMOperation c where c.state = :STATE"),
        @NamedQuery(name = "operation.findByUidAndState", query = "select c from EDMOperation c where c.uid = :UID and c.state = :STATE"),
        @NamedQuery(name = "operation.findByUid", query = "select c from EDMOperation c where c.uid = :UID"),
        @NamedQuery(name = "operation.findByInstanceId", query = "select c from EDMOperation c where c.instanceId = :INSTANCEID"),
        @NamedQuery(name = "operation.findByFileProvenance", query = "select c from EDMOperation c where c.fileprovenance = :FPROV"),
        @NamedQuery(name = "operation.findByListOfUidAndState", query = "select c from EDMOperation c where c.uid in :LIST and c.state = :STATE")
})
public class EDMOperation {
    private String uid;
    private String method;
    private String template;
    private String supportedoperation;
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
    private Collection<EDMMapping> mappingsByInstanceId;
    private EDMEdmEntityId edmEntityIdByMetaId;
    private EDMOperation operationByInstanceChangedId;
    private Collection<EDMOperation> operationsByInstanceId;
    private EDMEdmEntityId edmEntityIdByEditorMetaId;
    private Collection<EDMOperationReturns> operationReturnsByInstanceId;
    private Collection<EDMSoftwareapplicationOperation> softwareapplicationOperationsByInstanceId;
    private Collection<EDMSupportedOperation> supportedOperationsByInstanceId;

    @Basic
    @Column(name = "uid", nullable = false, length = -1)
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "method")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Basic
    @Column(name = "template")
    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Basic
    @Column(name = "supportedoperation")
    public String getSupportedoperation() {
        return supportedoperation;
    }

    public void setSupportedoperation(String supportedoperation) {
        this.supportedoperation = supportedoperation;
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

        EDMOperation that = (EDMOperation) o;

        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (method != null ? !method.equals(that.method) : that.method != null) return false;
        if (template != null ? !template.equals(that.template) : that.template != null) return false;
        if (supportedoperation != null ? !supportedoperation.equals(that.supportedoperation) : that.supportedoperation != null)
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
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (template != null ? template.hashCode() : 0);
        result = 31 * result + (supportedoperation != null ? supportedoperation.hashCode() : 0);
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

    @JoinFetch(JoinFetchType.OUTER)
    @OneToMany(mappedBy = "operationByIsmappingof", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Collection<EDMMapping> getMappingsByInstanceId() {
        return mappingsByInstanceId;
    }

    public void setMappingsByInstanceId(Collection<EDMMapping> mappingsByInstanceId) {
        this.mappingsByInstanceId = mappingsByInstanceId;
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
    public EDMOperation getOperationByInstanceChangedId() {
        return operationByInstanceChangedId;
    }

    public void setOperationByInstanceChangedId(EDMOperation operationByInstanceChangedId) {
        this.operationByInstanceChangedId = operationByInstanceChangedId;
    }

    @OneToMany(mappedBy = "operationByInstanceChangedId")
    public Collection<EDMOperation> getOperationsByInstanceId() {
        return operationsByInstanceId;
    }

    public void setOperationsByInstanceId(Collection<EDMOperation> operationsByInstanceId) {
        this.operationsByInstanceId = operationsByInstanceId;
    }

    @ManyToOne
    @JoinColumn(name = "editor_meta_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByEditorMetaId() {
        return edmEntityIdByEditorMetaId;
    }

    public void setEdmEntityIdByEditorMetaId(EDMEdmEntityId edmEntityIdByEditorMetaId) {
        this.edmEntityIdByEditorMetaId = edmEntityIdByEditorMetaId;
    }

    @OneToMany(mappedBy = "operationByInstanceOperationId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinFetch(JoinFetchType.OUTER)
    public Collection<EDMOperationReturns> getOperationReturnsByInstanceId() {
        return operationReturnsByInstanceId;
    }

    public void setOperationReturnsByInstanceId(Collection<EDMOperationReturns> operationReturnsByInstanceId) {
        this.operationReturnsByInstanceId = operationReturnsByInstanceId;
    }

    @OneToMany(mappedBy = "operationByInstanceOperationId", cascade = CascadeType.ALL)
    public Collection<EDMSoftwareapplicationOperation> getSoftwareapplicationOperationsByInstanceId() {
        return softwareapplicationOperationsByInstanceId;
    }

    public void setSoftwareapplicationOperationsByInstanceId(Collection<EDMSoftwareapplicationOperation> softwareapplicationOperationsByInstanceId) {
        this.softwareapplicationOperationsByInstanceId = softwareapplicationOperationsByInstanceId;
    }

    @OneToMany(mappedBy = "operationByInstanceOperationId", cascade = CascadeType.ALL)
    public Collection<EDMSupportedOperation> getSupportedOperationsByInstanceId() {
        return supportedOperationsByInstanceId;
    }

    public void setSupportedOperationsByInstanceId(Collection<EDMSupportedOperation> supportedOperationsByInstanceId) {
        this.supportedOperationsByInstanceId = supportedOperationsByInstanceId;
    }
}
