package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMContactpointSoftwareapplicationPK implements Serializable {
    private String instanceSoftwareapplicationId;
    private String instanceContactpointId;

    public String getInstanceSoftwareapplicationId() {
        return instanceSoftwareapplicationId;
    }

    public void setInstanceSoftwareapplicationId(String instanceSoftwareapplicationId) {
        this.instanceSoftwareapplicationId = instanceSoftwareapplicationId;
    }

    public String getInstanceContactpointId() {
        return instanceContactpointId;
    }

    public void setInstanceContactpointId(String instanceContactpointId) {
        this.instanceContactpointId = instanceContactpointId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMContactpointSoftwareapplicationPK that = (EDMContactpointSoftwareapplicationPK) o;

        if (instanceSoftwareapplicationId != null ? !instanceSoftwareapplicationId.equals(that.instanceSoftwareapplicationId) : that.instanceSoftwareapplicationId != null)
            return false;
        return instanceContactpointId != null ? instanceContactpointId.equals(that.instanceContactpointId) : that.instanceContactpointId == null;
    }

    @Override
    public int hashCode() {
        int result = instanceSoftwareapplicationId != null ? instanceSoftwareapplicationId.hashCode() : 0;
        result = 31 * result + (instanceContactpointId != null ? instanceContactpointId.hashCode() : 0);
        return result;
    }
}
