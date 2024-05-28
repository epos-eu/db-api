package model;

import jakarta.persistence.*;

@Entity
@Table(name = "equipment_temporal", schema = "public", catalog = "cerif")
@IdClass(EquipmentTemporalPK.class)
public class EquipmentTemporal {
    @Id
    @Column(name = "equipment_instance_id", nullable = false, length = 100)
    private String equipmentInstanceId;
    @Id
    @Column(name = "temporal_instance_id", nullable = false, length = 100)
    private String temporalInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "equipment_instance_id", referencedColumnName = "instance_id")
    private Equipment equipmentByEquipmentInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "temporal_instance_id", referencedColumnName = "instance_id")
    private Temporal temporalByTemporalInstanceId;

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

        EquipmentTemporal that = (EquipmentTemporal) o;

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

    public Equipment getEquipmentByEquipmentInstanceId() {
        return equipmentByEquipmentInstanceId;
    }

    public void setEquipmentByEquipmentInstanceId(Equipment equipmentByEquipmentInstanceId) {
        this.equipmentByEquipmentInstanceId = equipmentByEquipmentInstanceId;
    }

    public Temporal getTemporalByTemporalInstanceId() {
        return temporalByTemporalInstanceId;
    }

    public void setTemporalByTemporalInstanceId(Temporal temporalByTemporalInstanceId) {
        this.temporalByTemporalInstanceId = temporalByTemporalInstanceId;
    }
}
