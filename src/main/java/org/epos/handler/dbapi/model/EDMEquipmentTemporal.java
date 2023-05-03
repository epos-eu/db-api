package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "equipment_temporal")
public class EDMEquipmentTemporal {
    private String id;
    private Timestamp startdate;
    private Timestamp enddate;
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
    @Column(name = "startdate", nullable = true)
    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }

    @Basic
    @Column(name = "enddate", nullable = true)
    public Timestamp getEnddate() {
        return enddate;
    }

    public void setEnddate(Timestamp enddate) {
        this.enddate = enddate;
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

        EDMEquipmentTemporal that = (EDMEquipmentTemporal) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (startdate != null ? !startdate.equals(that.startdate) : that.startdate != null) return false;
        if (enddate != null ? !enddate.equals(that.enddate) : that.enddate != null) return false;
        return instanceEquipmentId != null ? instanceEquipmentId.equals(that.instanceEquipmentId) : that.instanceEquipmentId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (startdate != null ? startdate.hashCode() : 0);
        result = 31 * result + (enddate != null ? enddate.hashCode() : 0);
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
