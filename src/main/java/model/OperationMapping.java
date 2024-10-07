package model;

import jakarta.persistence.*;

@Entity
@Table(name = "operation_mapping", schema = "public", catalog = "cerif")
@IdClass(OperationMappingPK.class)
public class OperationMapping {
    @Id
    @Column(name = "operation_instance_id", nullable = false, length = 100)
    private String operationInstanceId;
    @Id
    @Column(name = "mapping_instance_id", nullable = false, length = 100)
    private String mappingInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "operation_instance_id", referencedColumnName = "instance_id")
    private Operation operationByOperationInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "mapping_instance_id", referencedColumnName = "instance_id")
    private Mapping mappingByMappingInstanceId;

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

        OperationMapping that = (OperationMapping) o;

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

    public Operation getOperationByOperationInstanceId() {
        return operationByOperationInstanceId;
    }

    public void setOperationByOperationInstanceId(Operation operationByOperationInstanceId) {
        this.operationByOperationInstanceId = operationByOperationInstanceId;
    }

    public Mapping getMappingByMappingInstanceId() {
        return mappingByMappingInstanceId;
    }

    public void setMappingByMappingInstanceId(Mapping mappingByMappingInstanceId) {
        this.mappingByMappingInstanceId = mappingByMappingInstanceId;
    }
}
