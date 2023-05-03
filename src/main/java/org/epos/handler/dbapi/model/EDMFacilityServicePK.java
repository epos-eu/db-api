package org.epos.handler.dbapi.model;

import java.io.Serializable;
import java.util.Objects;

public class EDMFacilityServicePK implements Serializable {
    private String instanceFacilityId;
    private String instanceServiceId;

    public String getInstanceFacilityId() {
        return instanceFacilityId;
    }

    public void setInstanceFacilityId(String instanceFacilityId) {
        this.instanceFacilityId = instanceFacilityId;
    }

    public String getInstanceServiceId() {
        return instanceServiceId;
    }

    public void setInstanceServiceId(String instanceServiceId) {
        this.instanceServiceId = instanceServiceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EDMFacilityServicePK that = (EDMFacilityServicePK) o;
        return Objects.equals(getInstanceFacilityId(), that.getInstanceFacilityId()) && Objects.equals(getInstanceServiceId(), that.getInstanceServiceId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInstanceFacilityId(), getInstanceServiceId());
    }
}
