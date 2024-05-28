package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class EquipmentTemporalPK implements Serializable {
    @Column(name = "equipment_instance_id", nullable = false, length = 100)
    @Id
    private String equipmentInstanceId;
    @Column(name = "temporal_instance_id", nullable = false, length = 100)
    @Id
    private String temporalInstanceId;

    public String getEquipmentInstanceId() {
        return equipmentInstanceId;
    }

    public void setEquipmentInstanceId(String equipmentInstanceId) {
        this.equipmentInstanceId = equipmentInstanceId;
    }

    public String getTemporalInstanceId() {
        return temporalInstanceId;
    }

    public void setTemporalInstanceId(String temporalInstanceId) {
        this.temporalInstanceId = temporalInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EquipmentTemporalPK that = (EquipmentTemporalPK) o;

        if (equipmentInstanceId != null ? !equipmentInstanceId.equals(that.equipmentInstanceId) : that.equipmentInstanceId != null)
            return false;
        if (temporalInstanceId != null ? !temporalInstanceId.equals(that.temporalInstanceId) : that.temporalInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = equipmentInstanceId != null ? equipmentInstanceId.hashCode() : 0;
        result = 31 * result + (temporalInstanceId != null ? temporalInstanceId.hashCode() : 0);
        return result;
    }
}
