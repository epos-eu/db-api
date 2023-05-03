package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "equipment_spatial")
public class EDMEquipmentSpatial {
    private String id;
    private String location;
    private String instanceEquipmentId;
    private EDMEquipment equipmentByInstanceEquipmentId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "instance_equipment_id", insertable = false, updatable = false)
    public String getInstanceEquipmentId() {
        return instanceEquipmentId;
    }

    public void setInstanceEquipmentId(String instanceEquipmentId) {
        this.instanceEquipmentId = instanceEquipmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMEquipmentSpatial that = (EDMEquipmentSpatial) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        return instanceEquipmentId != null ? instanceEquipmentId.equals(that.instanceEquipmentId) : that.instanceEquipmentId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (instanceEquipmentId != null ? instanceEquipmentId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_equipment_id", referencedColumnName = "instance_id", nullable = false)
    public EDMEquipment getEquipmentByInstanceEquipmentId() {
        return equipmentByInstanceEquipmentId;
    }

    public void setEquipmentByInstanceEquipmentId(EDMEquipment equipmentByInstanceEquipmentId) {
        this.equipmentByInstanceEquipmentId = equipmentByInstanceEquipmentId;
    }
}
