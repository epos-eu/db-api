package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "equipment_category")
@IdClass(EDMEquipmentCategoryPK.class)
public class EDMEquipmentCategory {
    private String categoryId;
    private String instanceEquipmentId;
    private EDMCategory categoryByCategoryId;
    private EDMEquipment equipmentByInstanceEquipmentId;

    @Id
    @Column(name = "category_id", insertable = false, updatable = false)
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Id
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

        EDMEquipmentCategory that = (EDMEquipmentCategory) o;

        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        return instanceEquipmentId != null ? instanceEquipmentId.equals(that.instanceEquipmentId) : that.instanceEquipmentId == null;
    }

    @Override
    public int hashCode() {
        int result = categoryId != null ? categoryId.hashCode() : 0;
        result = 31 * result + (instanceEquipmentId != null ? instanceEquipmentId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public EDMCategory getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(EDMCategory categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
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
