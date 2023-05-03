package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMContactpointWebservicePK implements Serializable {
    private String instanceWebserviceId;
    private String instanceContactpointId;

    public String getInstanceWebserviceId() {
        return instanceWebserviceId;
    }

    public void setInstanceWebserviceId(String instanceWebserviceId) {
        this.instanceWebserviceId = instanceWebserviceId;
    }

    public String getInstanceContactpointId() {
        return instanceContactpointId;
    }

    public void setInstanceContactpointId(String metaContactpointId) {
        this.instanceContactpointId = metaContactpointId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMContactpointWebservicePK that = (EDMContactpointWebservicePK) o;

        if (instanceWebserviceId != null ? !instanceWebserviceId.equals(that.instanceWebserviceId) : that.instanceWebserviceId != null)
            return false;
        return instanceContactpointId != null ? instanceContactpointId.equals(that.instanceContactpointId) : that.instanceContactpointId == null;
    }

    @Override
    public int hashCode() {
        int result = instanceWebserviceId != null ? instanceWebserviceId.hashCode() : 0;
        result = 31 * result + (instanceContactpointId != null ? instanceContactpointId.hashCode() : 0);
        return result;
    }
}
