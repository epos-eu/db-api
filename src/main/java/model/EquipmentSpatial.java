package model;

import jakarta.persistence.*;

@Entity
@Table(name = "equipment_spatial", schema = "public", catalog = "cerif")
@IdClass(EquipmentSpatialPK.class)
public class EquipmentSpatial {
    @Id
    @Column(name = "equipment_instance_id", nullable = false, length = 100)
    private String equipmentInstanceId;

    @Id
    @Column(name = "spatial_instance_id", nullable = false, length = 100)
    private String spatialInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "equipment_instance_id", referencedColumnName = "instance_id")
    private Equipment equipmentByEquipmentInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "spatial_instance_id", referencedColumnName = "instance_id")
    private Spatial spatialBySpatialInstanceId;

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

        EquipmentSpatial that = (EquipmentSpatial) o;

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

    public Equipment getEquipmentByEquipmentInstanceId() {
        return equipmentByEquipmentInstanceId;
    }

    public void setEquipmentByEquipmentInstanceId(Equipment equipmentByEquipmentInstanceId) {
        this.equipmentByEquipmentInstanceId = equipmentByEquipmentInstanceId;
    }

    public Spatial getSpatialBySpatialInstanceId() {
        return spatialBySpatialInstanceId;
    }

    public void setSpatialBySpatialInstanceId(Spatial spatialBySpatialInstanceId) {
        this.spatialBySpatialInstanceId = spatialBySpatialInstanceId;
    }
}
