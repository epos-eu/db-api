package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMContactpointEquipmentPK implements Serializable {
    private String instanceEquipmentId;
    private String instanceContactpointId;

    public String getInstanceEquipmentId() {
        return instanceEquipmentId;
    }

    public void setInstanceEquipmentId(String instanceEquipmentId) {
        this.instanceEquipmentId = instanceEquipmentId;
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

        EDMContactpointEquipmentPK that = (EDMContactpointEquipmentPK) o;

        if (instanceEquipmentId != null ? !instanceEquipmentId.equals(that.instanceEquipmentId) : that.instanceEquipmentId != null)
            return false;
        return instanceContactpointId != null ? instanceContactpointId.equals(that.instanceContactpointId) : that.instanceContactpointId == null;
    }

    @Override
    public int hashCode() {
        int result = instanceEquipmentId != null ? instanceEquipmentId.hashCode() : 0;
        result = 31 * result + (instanceContactpointId != null ? instanceContactpointId.hashCode() : 0);
        return result;
    }
}
