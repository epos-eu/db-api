package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMContactpointFacilityPK implements Serializable {
    private String instanceFacilityId;
    private String instanceContactpointId;

    public String getInstanceFacilityId() {
        return instanceFacilityId;
    }

    public void setInstanceFacilityId(String instanceFacilityId) {
        this.instanceFacilityId = instanceFacilityId;
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

        EDMContactpointFacilityPK that = (EDMContactpointFacilityPK) o;

        if (instanceFacilityId != null ? !instanceFacilityId.equals(that.instanceFacilityId) : that.instanceFacilityId != null)
            return false;
        return instanceContactpointId != null ? instanceContactpointId.equals(that.instanceContactpointId) : that.instanceContactpointId == null;
    }

    @Override
    public int hashCode() {
        int result = instanceFacilityId != null ? instanceFacilityId.hashCode() : 0;
        result = 31 * result + (instanceContactpointId != null ? instanceContactpointId.hashCode() : 0);
        return result;
    }
}
