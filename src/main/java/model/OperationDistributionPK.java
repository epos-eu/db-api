package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class OperationDistributionPK implements Serializable {
    @Column(name = "operation_instance_id", nullable = false, length = 100)
    @Id
    private String operationInstanceId;
    @Column(name = "distribution_instance_id", nullable = false, length = 100)
    @Id
    private String distributionInstanceId;

    public String getOperationInstanceId() {
        return operationInstanceId;
    }

    public void setOperationInstanceId(String operationInstanceId) {
        this.operationInstanceId = operationInstanceId;
    }

    public String getDistributionInstanceId() {
        return distributionInstanceId;
    }

    public void setDistributionInstanceId(String distributionInstanceId) {
        this.distributionInstanceId = distributionInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationDistributionPK that = (OperationDistributionPK) o;

        if (operationInstanceId != null ? !operationInstanceId.equals(that.operationInstanceId) : that.operationInstanceId != null)
            return false;
        if (distributionInstanceId != null ? !distributionInstanceId.equals(that.distributionInstanceId) : that.distributionInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operationInstanceId != null ? operationInstanceId.hashCode() : 0;
        result = 31 * result + (distributionInstanceId != null ? distributionInstanceId.hashCode() : 0);
        return result;
    }
}
