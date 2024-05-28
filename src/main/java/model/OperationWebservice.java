package model;

import jakarta.persistence.*;

@Entity
@Table(name = "operation_webservice", schema = "public", catalog = "cerif")
@IdClass(OperationWebservicePK.class)
public class OperationWebservice {
    @Id
    @Column(name = "operation_instance_id", nullable = false, length = 100)
    private String operationInstanceId;
    @Id
    @Column(name = "webservice_instance_id", nullable = false, length = 100)
    private String webserviceInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "operation_instance_id", referencedColumnName = "instance_id")
    private Operation operationByOperationInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "webservice_instance_id", referencedColumnName = "instance_id")
    private Webservice webserviceByWebserviceInstanceId;

    public String getOperationInstanceId() {
        return operationInstanceId;
    }

    public void setOperationInstanceId(String operationInstanceId) {
        this.operationInstanceId = operationInstanceId;
    }

    public String getWebserviceInstanceId() {
        return webserviceInstanceId;
    }

    public void setWebserviceInstanceId(String webserviceInstanceId) {
        this.webserviceInstanceId = webserviceInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationWebservice that = (OperationWebservice) o;

        if (operationInstanceId != null ? !operationInstanceId.equals(that.operationInstanceId) : that.operationInstanceId != null)
            return false;
        if (webserviceInstanceId != null ? !webserviceInstanceId.equals(that.webserviceInstanceId) : that.webserviceInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operationInstanceId != null ? operationInstanceId.hashCode() : 0;
        result = 31 * result + (webserviceInstanceId != null ? webserviceInstanceId.hashCode() : 0);
        return result;
    }

    public Operation getOperationByOperationInstanceId() {
        return operationByOperationInstanceId;
    }

    public void setOperationByOperationInstanceId(Operation operationByOperationInstanceId) {
        this.operationByOperationInstanceId = operationByOperationInstanceId;
    }

    public Webservice getWebserviceByWebserviceInstanceId() {
        return webserviceByWebserviceInstanceId;
    }

    public void setWebserviceByWebserviceInstanceId(Webservice webserviceByWebserviceInstanceId) {
        this.webserviceByWebserviceInstanceId = webserviceByWebserviceInstanceId;
    }
}
