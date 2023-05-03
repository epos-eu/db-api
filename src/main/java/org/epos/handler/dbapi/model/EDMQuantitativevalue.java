package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "quantitativevalue", schema = "public")
public class EDMQuantitativevalue {
    private String id;
    private String unit;
    private String value;
    private Collection<EDMEquipment> equipmentById;

    @Id
    @Column(name = "id", nullable = false, length = 1024)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "unit", nullable = false, length = 1024)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "value", nullable = false, length = 1024)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMQuantitativevalue that = (EDMQuantitativevalue) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;
        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "quantitativevalueByDynamicrange")
    public Collection<EDMEquipment> getEquipmentById() {
        return equipmentById;
    }

    public void setEquipmentById(Collection<EDMEquipment> equipmentById) {
        this.equipmentById = equipmentById;
    }
}
