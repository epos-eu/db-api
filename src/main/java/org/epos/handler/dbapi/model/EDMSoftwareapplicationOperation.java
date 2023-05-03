package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "softwareapplication_operation")
@IdClass(EDMSoftwareapplicationOperationPK.class)
public class EDMSoftwareapplicationOperation {
    private String instanceOperationId;
    private String instanceSoftwareapplicationId;
    private EDMOperation operationByInstanceOperationId;
    private EDMSoftwareapplication softwareapplicationByInstanceSoftwareapplicationId;

    @Id
    @Column(name = "instance_operation_id", insertable = false, updatable = false)
    public String getInstanceOperationId() {
        return instanceOperationId;
    }

    public void setInstanceOperationId(String metaOperationId) {
        this.instanceOperationId = metaOperationId;
    }

    @Id
    @Column(name = "instance_softwareapplication_id", insertable = false, updatable = false)
    public String getInstanceSoftwareapplicationId() {
        return instanceSoftwareapplicationId;
    }

    public void setInstanceSoftwareapplicationId(String instanceSoftwareapplicationId) {
        this.instanceSoftwareapplicationId = instanceSoftwareapplicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMSoftwareapplicationOperation that = (EDMSoftwareapplicationOperation) o;

        if (instanceOperationId != null ? !instanceOperationId.equals(that.instanceOperationId) : that.instanceOperationId != null)
            return false;
        return instanceSoftwareapplicationId != null ? instanceSoftwareapplicationId.equals(that.instanceSoftwareapplicationId) : that.instanceSoftwareapplicationId == null;
    }

    @Override
    public int hashCode() {
        int result = instanceOperationId != null ? instanceOperationId.hashCode() : 0;
        result = 31 * result + (instanceSoftwareapplicationId != null ? instanceSoftwareapplicationId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_operation_id", referencedColumnName = "instance_id", nullable = false)
    public EDMOperation getOperationByInstanceOperationId() {
        return operationByInstanceOperationId;
    }

    public void setOperationByInstanceOperationId(EDMOperation edmEntityIdByMetaOperationId) {
        this.operationByInstanceOperationId = edmEntityIdByMetaOperationId;
    }

    @ManyToOne
    @JoinColumn(name = "instance_softwareapplication_id", referencedColumnName = "instance_id", nullable = false)
    public EDMSoftwareapplication getSoftwareapplicationByInstanceSoftwareapplicationId() {
        return softwareapplicationByInstanceSoftwareapplicationId;
    }

    public void setSoftwareapplicationByInstanceSoftwareapplicationId(EDMSoftwareapplication softwareapplicationByInstanceSoftwareapplicationId) {
        this.softwareapplicationByInstanceSoftwareapplicationId = softwareapplicationByInstanceSoftwareapplicationId;
    }
}
