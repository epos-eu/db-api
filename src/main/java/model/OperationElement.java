package model;

import jakarta.persistence.*;

@Entity
@Table(name = "operation_element", schema = "public", catalog = "cerif")
@IdClass(OperationElementPK.class)
public class OperationElement {
    @Id
    @Column(name = "operation_instance_id", nullable = false, length = 100)
    private String operationInstanceId;
    @Id
    @Column(name = "element_instance_id", nullable = false, length = 100)
    private String elementInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "operation_instance_id", referencedColumnName = "instance_id")
    private Operation operationByOperationInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "element_instance_id", referencedColumnName = "instance_id")
    private Element elementByElementInstanceId;

    public String getOperationInstanceId() {
        return operationInstanceId;
    }

    public void setOperationInstanceId(String operationInstanceId) {
        this.operationInstanceId = operationInstanceId;
    }

    public String getElementInstanceId() {
        return elementInstanceId;
    }

    public void setElementInstanceId(String elementInstanceId) {
        this.elementInstanceId = elementInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationElement that = (OperationElement) o;

        if (operationInstanceId != null ? !operationInstanceId.equals(that.operationInstanceId) : that.operationInstanceId != null)
            return false;
        if (elementInstanceId != null ? !elementInstanceId.equals(that.elementInstanceId) : that.elementInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operationInstanceId != null ? operationInstanceId.hashCode() : 0;
        result = 31 * result + (elementInstanceId != null ? elementInstanceId.hashCode() : 0);
        return result;
    }

    public Operation getOperationByOperationInstanceId() {
        return operationByOperationInstanceId;
    }

    public void setOperationByOperationInstanceId(Operation operationByOperationInstanceId) {
        this.operationByOperationInstanceId = operationByOperationInstanceId;
    }

    public Element getElementByElementInstanceId() {
        return elementByElementInstanceId;
    }

    public void setElementByElementInstanceId(Element elementByElementInstanceId) {
        this.elementByElementInstanceId = elementByElementInstanceId;
    }
}
