package model;

import jakarta.persistence.*;

@Entity
@Table(name = "softwareapplication_operation", schema = "public", catalog = "cerif")
@IdClass(SoftwareapplicationOperationPK.class)
public class SoftwareapplicationOperation {
    @Id
    @Column(name = "softwareapplication_instance_id", nullable = false, length = 100)
    private String softwareapplicationInstanceId;
    @Id
    @Column(name = "operation_instance_id", nullable = false, length = 100)
    private String operationInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "softwareapplication_instance_id", referencedColumnName = "instance_id")
    private Softwareapplication softwareapplicationBySoftwareapplicationInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "operation_instance_id", referencedColumnName = "instance_id")
    private Operation operationByOperationInstanceId;

    public String getSoftwareapplicationInstanceId() {
        return softwareapplicationInstanceId;
    }

    public void setSoftwareapplicationInstanceId(String softwareapplicationInstanceId) {
        this.softwareapplicationInstanceId = softwareapplicationInstanceId;
    }

    public String getOperationInstanceId() {
        return operationInstanceId;
    }

    public void setOperationInstanceId(String operationInstanceId) {
        this.operationInstanceId = operationInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SoftwareapplicationOperation that = (SoftwareapplicationOperation) o;

        if (softwareapplicationInstanceId != null ? !softwareapplicationInstanceId.equals(that.softwareapplicationInstanceId) : that.softwareapplicationInstanceId != null)
            return false;
        if (operationInstanceId != null ? !operationInstanceId.equals(that.operationInstanceId) : that.operationInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = softwareapplicationInstanceId != null ? softwareapplicationInstanceId.hashCode() : 0;
        result = 31 * result + (operationInstanceId != null ? operationInstanceId.hashCode() : 0);
        return result;
    }

    public Softwareapplication getSoftwareapplicationBySoftwareapplicationInstanceId() {
        return softwareapplicationBySoftwareapplicationInstanceId;
    }

    public void setSoftwareapplicationBySoftwareapplicationInstanceId(Softwareapplication softwareapplicationBySoftwareapplicationInstanceId) {
        this.softwareapplicationBySoftwareapplicationInstanceId = softwareapplicationBySoftwareapplicationInstanceId;
    }

    public Operation getOperationByOperationInstanceId() {
        return operationByOperationInstanceId;
    }

    public void setOperationByOperationInstanceId(Operation operationByOperationInstanceId) {
        this.operationByOperationInstanceId = operationByOperationInstanceId;
    }
}
