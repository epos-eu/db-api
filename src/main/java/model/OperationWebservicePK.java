package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class OperationWebservicePK implements Serializable {
    @Column(name = "operation_instance_id", nullable = false, length = 100)
    @Id
    private String operationInstanceId;
    @Column(name = "webservice_instance_id", nullable = false, length = 100)
    @Id
    private String webserviceInstanceId;

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

        OperationWebservicePK that = (OperationWebservicePK) o;

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
}
