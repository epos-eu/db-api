package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class EquipmentSpatialPK implements Serializable {
    @Column(name = "equipment_instance_id", nullable = false, length = 100)
    @Id
    private String equipmentInstanceId;
    @Column(name = "spatial_instance_id", nullable = false, length = 100)
    @Id
    private String spatialInstanceId;

    public String getEquipmentInstanceId() {
        return equipmentInstanceId;
    }

    public void setEquipmentInstanceId(String equipmentInstanceId) {
        this.equipmentInstanceId = equipmentInstanceId;
    }

    public String getSpatialInstanceId() {
        return spatialInstanceId;
    }

    public void setSpatialInstanceId(String spatialInstanceId) {
        this.spatialInstanceId = spatialInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EquipmentSpatialPK that = (EquipmentSpatialPK) o;

        if (equipmentInstanceId != null ? !equipmentInstanceId.equals(that.equipmentInstanceId) : that.equipmentInstanceId != null)
            return false;
        if (spatialInstanceId != null ? !spatialInstanceId.equals(that.spatialInstanceId) : that.spatialInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = equipmentInstanceId != null ? equipmentInstanceId.hashCode() : 0;
        result = 31 * result + (spatialInstanceId != null ? spatialInstanceId.hashCode() : 0);
        return result;
    }
}
