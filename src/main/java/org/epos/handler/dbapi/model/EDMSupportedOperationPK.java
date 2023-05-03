package org.epos.handler.dbapi.model;

import java.io.Serializable;
import java.util.Objects;

public class EDMSupportedOperationPK implements Serializable {
    private String instanceWebserviceId;
    private String instanceOperationId;

    public String getInstanceWebserviceId() {
        return instanceWebserviceId;
    }

    public void setInstanceWebserviceId(String instanceWebserviceId) {
        this.instanceWebserviceId = instanceWebserviceId;
    }

    public String getInstanceOperationId() {
        return instanceOperationId;
    }

    public void setInstanceOperationId(String instanceOperationId) {
        this.instanceOperationId = instanceOperationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EDMSupportedOperationPK that = (EDMSupportedOperationPK) o;
        return Objects.equals(getInstanceWebserviceId(), that.getInstanceWebserviceId()) && Objects.equals(getInstanceOperationId(), that.getInstanceOperationId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInstanceWebserviceId(), getInstanceOperationId());
    }
}
