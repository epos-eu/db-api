package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMEquipmentEquipmentPK implements Serializable {
    private String instanceEquipment1Id;
    private String instanceEquipment2Id;

    public String getInstanceEquipment1Id() {
        return instanceEquipment1Id;
    }

    public void setInstanceEquipment1Id(String instanceEquipment1Id) {
        this.instanceEquipment1Id = instanceEquipment1Id;
    }

    public String getInstanceEquipment2Id() {
        return instanceEquipment2Id;
    }

    public void setInstanceEquipment2Id(String instanceEquipment2Id) {
        this.instanceEquipment2Id = instanceEquipment2Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMEquipmentEquipmentPK that = (EDMEquipmentEquipmentPK) o;

        if (instanceEquipment1Id != null ? !instanceEquipment1Id.equals(that.instanceEquipment1Id) : that.instanceEquipment1Id != null)
            return false;
        return instanceEquipment2Id != null ? instanceEquipment2Id.equals(that.instanceEquipment2Id) : that.instanceEquipment2Id == null;
    }

    @Override
    public int hashCode() {
        int result = instanceEquipment1Id != null ? instanceEquipment1Id.hashCode() : 0;
        result = 31 * result + (instanceEquipment2Id != null ? instanceEquipment2Id.hashCode() : 0);
        return result;
    }
}
