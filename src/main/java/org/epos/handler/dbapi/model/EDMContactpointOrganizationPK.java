package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMContactpointOrganizationPK implements Serializable {
    private String instanceOrganizationId;
    private String instanceContactpointId;

    public String getInstanceOrganizationId() {
        return instanceOrganizationId;
    }

    public void setInstanceOrganizationId(String instanceOrganizationId) {
        this.instanceOrganizationId = instanceOrganizationId;
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

        EDMContactpointOrganizationPK that = (EDMContactpointOrganizationPK) o;

        if (instanceOrganizationId != null ? !instanceOrganizationId.equals(that.instanceOrganizationId) : that.instanceOrganizationId != null)
            return false;
        return instanceContactpointId != null ? instanceContactpointId.equals(that.instanceContactpointId) : that.instanceContactpointId == null;
    }

    @Override
    public int hashCode() {
        int result = instanceOrganizationId != null ? instanceOrganizationId.hashCode() : 0;
        result = 31 * result + (instanceContactpointId != null ? instanceContactpointId.hashCode() : 0);
        return result;
    }
}
