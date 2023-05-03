package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "operation_returns")
public class EDMOperationReturns {
    private String id;
    private String instanceOperationId;
    private String returns;
    private EDMOperation operationByInstanceOperationId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "instance_operation_id", insertable = false, updatable = false)
    public String getInstanceOperationId() {
        return instanceOperationId;
    }

    public void setInstanceOperationId(String instanceOperationId) {
        this.instanceOperationId = instanceOperationId;
    }

    @Basic
    @Column(name = "returns")
    public String getReturns() {
        return returns;
    }

    public void setReturns(String returns) {
        this.returns = returns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMOperationReturns that = (EDMOperationReturns) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (instanceOperationId != null ? !instanceOperationId.equals(that.instanceOperationId) : that.instanceOperationId != null)
            return false;
        return returns != null ? returns.equals(that.returns) : that.returns == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (instanceOperationId != null ? instanceOperationId.hashCode() : 0);
        result = 31 * result + (returns != null ? returns.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_operation_id", referencedColumnName = "instance_id")
    public EDMOperation getOperationByInstanceOperationId() {
        return operationByInstanceOperationId;
    }

    public void setOperationByInstanceOperationId(EDMOperation operationByInstanceOperationId) {
        this.operationByInstanceOperationId = operationByInstanceOperationId;
    }
}
