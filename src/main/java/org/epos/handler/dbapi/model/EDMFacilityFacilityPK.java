package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMFacilityFacilityPK implements Serializable {
    private String instanceFacility1Id;
    private String instanceFacility2Id;

    public String getInstanceFacility1Id() {
        return instanceFacility1Id;
    }

    public void setInstanceFacility1Id(String instanceFacility1Id) {
        this.instanceFacility1Id = instanceFacility1Id;
    }

    public String getInstanceFacility2Id() {
        return instanceFacility2Id;
    }

    public void setInstanceFacility2Id(String instanceFacility2Id) {
        this.instanceFacility2Id = instanceFacility2Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMFacilityFacilityPK that = (EDMFacilityFacilityPK) o;

        if (instanceFacility1Id != null ? !instanceFacility1Id.equals(that.instanceFacility1Id) : that.instanceFacility1Id != null)
            return false;
        return instanceFacility2Id != null ? instanceFacility2Id.equals(that.instanceFacility2Id) : that.instanceFacility2Id == null;
    }

    @Override
    public int hashCode() {
        int result = instanceFacility1Id != null ? instanceFacility1Id.hashCode() : 0;
        result = 31 * result + (instanceFacility2Id != null ? instanceFacility2Id.hashCode() : 0);
        return result;
    }
}
