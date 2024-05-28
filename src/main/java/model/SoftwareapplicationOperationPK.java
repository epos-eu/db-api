package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class SoftwareapplicationOperationPK implements Serializable {
    @Column(name = "softwareapplication_instance_id", nullable = false, length = 100)
    @Id
    private String softwareapplicationInstanceId;
    @Column(name = "operation_instance_id", nullable = false, length = 100)
    @Id
    private String operationInstanceId;

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

        SoftwareapplicationOperationPK that = (SoftwareapplicationOperationPK) o;

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
}
