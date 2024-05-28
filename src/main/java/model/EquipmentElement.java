package model;

import jakarta.persistence.*;

@Entity
@Table(name = "equipment_element", schema = "public", catalog = "cerif")
@IdClass(EquipmentElementPK.class)
public class EquipmentElement {
    @Id
    @Column(name = "equipment_instance_id", nullable = false, length = 100)
    private String equipmentInstanceId;
    @Id
    @Column(name = "element_instance_id", nullable = false, length = 100)
    private String elementInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "equipment_instance_id", referencedColumnName = "instance_id")
    private Equipment equipmentByEquipmentInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "element_instance_id", referencedColumnName = "instance_id")
    private Element elementByElementInstanceId;

    public String getEquipmentInstanceId() {
        return equipmentInstanceId;
    }

    public void setEquipmentInstanceId(String equipmentInstanceId) {
        this.equipmentInstanceId = equipmentInstanceId;
    }

    public String getElementInstanceId() {
        return elementInstanceId;
    }

    public void setElementInstanceId(String elementInstanceId) {
        this.elementInstanceId = elementInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EquipmentElement that = (EquipmentElement) o;

        if (equipmentInstanceId != null ? !equipmentInstanceId.equals(that.equipmentInstanceId) : that.equipmentInstanceId != null)
            return false;
        if (elementInstanceId != null ? !elementInstanceId.equals(that.elementInstanceId) : that.elementInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = equipmentInstanceId != null ? equipmentInstanceId.hashCode() : 0;
        result = 31 * result + (elementInstanceId != null ? elementInstanceId.hashCode() : 0);
        return result;
    }

    public Equipment getEquipmentByEquipmentInstanceId() {
        return equipmentByEquipmentInstanceId;
    }

    public void setEquipmentByEquipmentInstanceId(Equipment equipmentByEquipmentInstanceId) {
        this.equipmentByEquipmentInstanceId = equipmentByEquipmentInstanceId;
    }

    public Element getElementByElementInstanceId() {
        return elementByElementInstanceId;
    }

    public void setElementByElementInstanceId(Element elementByElementInstanceId) {
        this.elementByElementInstanceId = elementByElementInstanceId;
    }
}
