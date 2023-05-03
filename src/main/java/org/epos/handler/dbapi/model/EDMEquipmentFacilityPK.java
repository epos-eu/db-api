package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMEquipmentFacilityPK implements Serializable {
    private String instanceEquipmentId;
    private String instanceFacilityId;

    public String getInstanceEquipmentId() {
        return instanceEquipmentId;
    }

    public void setInstanceEquipmentId(String instanceEquipmentId) {
        this.instanceEquipmentId = instanceEquipmentId;
    }

    public String getInstanceFacilityId() {
        return instanceFacilityId;
    }

    public void setInstanceFacilityId(String metaFacilityId) {
        this.instanceFacilityId = metaFacilityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMEquipmentFacilityPK that = (EDMEquipmentFacilityPK) o;

        if (instanceEquipmentId != null ? !instanceEquipmentId.equals(that.instanceEquipmentId) : that.instanceEquipmentId != null)
            return false;
        return instanceFacilityId != null ? instanceFacilityId.equals(that.instanceFacilityId) : that.instanceFacilityId == null;
    }

    @Override
    public int hashCode() {
        int result = instanceEquipmentId != null ? instanceEquipmentId.hashCode() : 0;
        result = 31 * result + (instanceFacilityId != null ? instanceFacilityId.hashCode() : 0);
        return result;
    }
}
