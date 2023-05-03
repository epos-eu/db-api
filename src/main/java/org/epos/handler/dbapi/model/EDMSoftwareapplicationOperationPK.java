package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMSoftwareapplicationOperationPK implements Serializable {
    private String instanceOperationId;
    private String instanceSoftwareapplicationId;

    public String getInstanceOperationId() {
        return instanceOperationId;
    }

    public void setInstanceOperationId(String instanceOperationId) {
        this.instanceOperationId = instanceOperationId;
    }

    public String getInstanceSoftwareapplicationId() {
        return instanceSoftwareapplicationId;
    }

    public void setInstanceSoftwareapplicationId(String instanceSoftwareapplicationId) {
        this.instanceSoftwareapplicationId = instanceSoftwareapplicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMSoftwareapplicationOperationPK that = (EDMSoftwareapplicationOperationPK) o;

        if (instanceOperationId != null ? !instanceOperationId.equals(that.instanceOperationId) : that.instanceOperationId != null)
            return false;
        return instanceSoftwareapplicationId != null ? instanceSoftwareapplicationId.equals(that.instanceSoftwareapplicationId) : that.instanceSoftwareapplicationId == null;
    }

    @Override
    public int hashCode() {
        int result = instanceOperationId != null ? instanceOperationId.hashCode() : 0;
        result = 31 * result + (instanceSoftwareapplicationId != null ? instanceSoftwareapplicationId.hashCode() : 0);
        return result;
    }
}
