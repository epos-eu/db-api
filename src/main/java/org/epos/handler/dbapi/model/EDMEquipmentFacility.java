package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "equipment_facility")
@IdClass(EDMEquipmentFacilityPK.class)
public class EDMEquipmentFacility {
    private String instanceEquipmentId;
    private String instanceFacilityId;
    private EDMEquipment equipmentByInstanceEquipmentId;
    private EDMFacility facilityByInstanceFacilityId;

    @Id
    @Column(name = "instance_equipment_id", insertable = false, updatable = false)
    public String getInstanceEquipmentId() {
        return instanceEquipmentId;
    }

    public void setInstanceEquipmentId(String instanceEquipmentId) {
        this.instanceEquipmentId = instanceEquipmentId;
    }

    @Id
    @Column(name = "instance_facility_id", insertable = false, updatable = false)
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

        EDMEquipmentFacility that = (EDMEquipmentFacility) o;

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

    @ManyToOne
    @JoinColumn(name = "instance_equipment_id", referencedColumnName = "instance_id", nullable = false)
    public EDMEquipment getEquipmentByInstanceEquipmentId() {
        return equipmentByInstanceEquipmentId;
    }

    public void setEquipmentByInstanceEquipmentId(EDMEquipment equipmentByInstanceEquipmentId) {
        this.equipmentByInstanceEquipmentId = equipmentByInstanceEquipmentId;
    }

    @ManyToOne
    @JoinColumn(name = "instance_facility_id", referencedColumnName = "instance_id", nullable = false)
    public EDMFacility getFacilityByInstanceFacilityId() {
        return facilityByInstanceFacilityId;
    }

    public void setFacilityByInstanceFacilityId(EDMFacility facilityByInstanceFacilityId) {
        this.facilityByInstanceFacilityId = facilityByInstanceFacilityId;
    }
}
