package model;

import jakarta.persistence.*;

@Entity
@Table(name = "operation_distribution", schema = "public", catalog = "cerif")
@IdClass(OperationDistributionPK.class)
public class OperationDistribution {
    @Id
    @Column(name = "operation_instance_id", nullable = false, length = 100)
    private String operationInstanceId;
    @Id
    @Column(name = "distribution_instance_id", nullable = false, length = 100)
    private String distributionInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "operation_instance_id", referencedColumnName = "instance_id")
    private Operation operationByOperationInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "distribution_instance_id", referencedColumnName = "instance_id")
    private Distribution distributionByDistributionInstanceId;

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

        OperationDistribution that = (OperationDistribution) o;

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

    public Operation getOperationByOperationInstanceId() {
        return operationByOperationInstanceId;
    }

    public void setOperationByOperationInstanceId(Operation operationByOperationInstanceId) {
        this.operationByOperationInstanceId = operationByOperationInstanceId;
    }

    public Distribution getDistributionByDistributionInstanceId() {
        return distributionByDistributionInstanceId;
    }

    public void setDistributionByDistributionInstanceId(Distribution distributionByDistributionInstanceId) {
        this.distributionByDistributionInstanceId = distributionByDistributionInstanceId;
    }
}
