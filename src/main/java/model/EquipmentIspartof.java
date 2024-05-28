package model;

import jakarta.persistence.*;

@Entity
@Table(name = "equipment_ispartof", schema = "public", catalog = "cerif")
public class EquipmentIspartof {
    @Id
    @Column(name = "equipment_instance_id", nullable = false, length = 100)
    private String equipmentInstanceId;
    @Basic
    @Column(name = "entity_instance_id", nullable = false, length = 100)
    private String entityInstanceId;
    @Basic
    @Column(name = "resource_entity", nullable = false, length = 100)
    private String resourceEntity;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "equipment_instance_id", referencedColumnName = "instance_id")
    private Equipment equipmentByEquipmentInstanceId;

    public String getEquipmentInstanceId() {
        return equipmentInstanceId;
    }

    public void setEquipmentInstanceId(String equipmentInstanceId) {
        this.equipmentInstanceId = equipmentInstanceId;
    }

    public String getEntityInstanceId() {
        return entityInstanceId;
    }

    public void setEntityInstanceId(String entityInstanceId) {
        this.entityInstanceId = entityInstanceId;
    }

    public String getResourceEntity() {
        return resourceEntity;
    }

    public void setResourceEntity(String resourceEntity) {
        this.resourceEntity = resourceEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EquipmentIspartof that = (EquipmentIspartof) o;

        if (equipmentInstanceId != null ? !equipmentInstanceId.equals(that.equipmentInstanceId) : that.equipmentInstanceId != null)
            return false;
        if (entityInstanceId != null ? !entityInstanceId.equals(that.entityInstanceId) : that.entityInstanceId != null)
            return false;
        if (resourceEntity != null ? !resourceEntity.equals(that.resourceEntity) : that.resourceEntity != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = equipmentInstanceId != null ? equipmentInstanceId.hashCode() : 0;
        result = 31 * result + (entityInstanceId != null ? entityInstanceId.hashCode() : 0);
        result = 31 * result + (resourceEntity != null ? resourceEntity.hashCode() : 0);
        return result;
    }

    public Equipment getEquipmentByEquipmentInstanceId() {
        return equipmentByEquipmentInstanceId;
    }

    public void setEquipmentByEquipmentInstanceId(Equipment equipmentByEquipmentInstanceId) {
        this.equipmentByEquipmentInstanceId = equipmentByEquipmentInstanceId;
    }
}
