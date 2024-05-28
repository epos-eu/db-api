package model;

import jakarta.persistence.*;

@Entity
@Table(name = "equipment_category", schema = "public", catalog = "cerif")
@IdClass(EquipmentCategoryPK.class)
public class EquipmentCategory {
    @Id
    @Column(name = "category_instance_id", nullable = false, length = 100)
    private String categoryInstanceId;
    @Id
    @Column(name = "equipment_instance_id", nullable = false, length = 100)
    private String equipmentInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "category_instance_id", referencedColumnName = "instance_id")
    private Category categoryByCategoryInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "equipment_instance_id", referencedColumnName = "instance_id")
    private Equipment equipmentByEquipmentInstanceId;

    public String getCategoryInstanceId() {
        return categoryInstanceId;
    }

    public void setCategoryInstanceId(String categoryInstanceId) {
        this.categoryInstanceId = categoryInstanceId;
    }

    public String getEquipmentInstanceId() {
        return equipmentInstanceId;
    }

    public void setEquipmentInstanceId(String equipmentInstanceId) {
        this.equipmentInstanceId = equipmentInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EquipmentCategory that = (EquipmentCategory) o;

        if (categoryInstanceId != null ? !categoryInstanceId.equals(that.categoryInstanceId) : that.categoryInstanceId != null)
            return false;
        if (equipmentInstanceId != null ? !equipmentInstanceId.equals(that.equipmentInstanceId) : that.equipmentInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryInstanceId != null ? categoryInstanceId.hashCode() : 0;
        result = 31 * result + (equipmentInstanceId != null ? equipmentInstanceId.hashCode() : 0);
        return result;
    }

    public Category getCategoryByCategoryInstanceId() {
        return categoryByCategoryInstanceId;
    }

    public void setCategoryByCategoryInstanceId(Category categoryByCategoryInstanceId) {
        this.categoryByCategoryInstanceId = categoryByCategoryInstanceId;
    }

    public Equipment getEquipmentByEquipmentInstanceId() {
        return equipmentByEquipmentInstanceId;
    }

    public void setEquipmentByEquipmentInstanceId(Equipment equipmentByEquipmentInstanceId) {
        this.equipmentByEquipmentInstanceId = equipmentByEquipmentInstanceId;
    }
}
