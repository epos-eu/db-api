package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "contactpoint_equipment")
@IdClass(EDMContactpointEquipmentPK.class)
public class EDMContactpointEquipment {
    private String instanceEquipmentId;
    private String instanceContactpointId;
    private EDMEquipment equipmentByInstanceEquipmentId;
    private EDMContactpoint contactpointByInstanceContactpointId;

    @Id
    @Column(name = "instance_equipment_id", insertable = false, updatable = false)
    public String getInstanceEquipmentId() {
        return instanceEquipmentId;
    }

    public void setInstanceEquipmentId(String instanceEquipmentId) {
        this.instanceEquipmentId = instanceEquipmentId;
    }

    @Id
    @Column(name = "instance_contactpoint_id", insertable = false, updatable = false)
    public String getInstanceContactpointId() {
        return instanceContactpointId;
    }

    public void setInstanceContactpointId(String metaContactpointId) {
        this.instanceContactpointId = metaContactpointId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMContactpointEquipment that = (EDMContactpointEquipment) o;

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

    @ManyToOne
    @JoinColumn(name = "instance_equipment_id", referencedColumnName = "instance_id", nullable = false)
    public EDMEquipment getEquipmentByInstanceEquipmentId() {
        return equipmentByInstanceEquipmentId;
    }

    public void setEquipmentByInstanceEquipmentId(EDMEquipment equipmentByInstanceEquipmentId) {
        this.equipmentByInstanceEquipmentId = equipmentByInstanceEquipmentId;
    }

    @ManyToOne
    @JoinColumn(name = "instance_contactpoint_id", referencedColumnName = "instance_id", nullable = false)
    public EDMContactpoint getContactpointByInstanceContactpointId() {
        return contactpointByInstanceContactpointId;
    }

    public void setContactpointByInstanceContactpointId(EDMContactpoint contactpointByInstanceContactpointId) {
        this.contactpointByInstanceContactpointId = contactpointByInstanceContactpointId;
    }
}
