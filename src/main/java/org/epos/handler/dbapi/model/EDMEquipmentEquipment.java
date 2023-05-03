package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "equipment_equipment")
@IdClass(EDMEquipmentEquipmentPK.class)
public class EDMEquipmentEquipment {
    private String instanceEquipment1Id;
    private String instanceEquipment2Id;
    private EDMEquipment equipmentByInstanceEquipment1Id;
    private EDMEquipment equipmentByInstanceEquipment2Id;

    @Id
    @Column(name = "instance_equipment1_id", insertable = false, updatable = false)
    public String getInstanceEquipment1Id() {
        return instanceEquipment1Id;
    }

    public void setInstanceEquipment1Id(String instanceEquipment1Id) {
        this.instanceEquipment1Id = instanceEquipment1Id;
    }

    @Id
    @Column(name = "instance_equipment2_id", insertable = false, updatable = false)
    public String getInstanceEquipment2Id() {
        return instanceEquipment2Id;
    }

    public void setInstanceEquipment2Id(String metaEquipment2Id) {
        this.instanceEquipment2Id = metaEquipment2Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMEquipmentEquipment that = (EDMEquipmentEquipment) o;

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

    @ManyToOne
    @JoinColumn(name = "instance_equipment1_id", referencedColumnName = "instance_id", nullable = false)
    public EDMEquipment getEquipmentByInstanceEquipment1Id() {
        return equipmentByInstanceEquipment1Id;
    }

    public void setEquipmentByInstanceEquipment1Id(EDMEquipment equipmentByInstanceEquipment1Id) {
        this.equipmentByInstanceEquipment1Id = equipmentByInstanceEquipment1Id;
    }

    @ManyToOne
    @JoinColumn(name = "instance_equipment2_id", referencedColumnName = "instance_id", nullable = false)
    public EDMEquipment getEquipmentByInstanceEquipment2Id() {
        return equipmentByInstanceEquipment2Id;
    }

    public void setEquipmentByInstanceEquipment2Id(EDMEquipment edmEntityIdByMetaEquipment2Id) {
        this.equipmentByInstanceEquipment2Id = edmEntityIdByMetaEquipment2Id;
    }
}
