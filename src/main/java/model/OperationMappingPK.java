package model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;

public class OperationMappingPK implements Serializable {
    @Column(name = "operation_instance_id", nullable = false, length = 100)
    @Id
    private String operationInstanceId;
    @Column(name = "mapping_instance_id", nullable = false, length = 100)
    @Id
    private String mappingInstanceId;

    public String getOperationInstanceId() {
        return operationInstanceId;
    }

    public void setOperationInstanceId(String operationInstanceId) {
        this.operationInstanceId = operationInstanceId;
    }

    public String getMappingInstanceId() {
        return mappingInstanceId;
    }

    public void setMappingInstanceId(String mappingInstanceId) {
        this.mappingInstanceId = mappingInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationMappingPK that = (OperationMappingPK) o;

        if (operationInstanceId != null ? !operationInstanceId.equals(that.operationInstanceId) : that.operationInstanceId != null)
            return false;
        if (mappingInstanceId != null ? !mappingInstanceId.equals(that.mappingInstanceId) : that.mappingInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operationInstanceId != null ? operationInstanceId.hashCode() : 0;
        result = 31 * result + (mappingInstanceId != null ? mappingInstanceId.hashCode() : 0);
        return result;
    }
}
