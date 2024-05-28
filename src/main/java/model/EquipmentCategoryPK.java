package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class EquipmentCategoryPK implements Serializable {
    @Column(name = "category_instance_id", nullable = false, length = 100)
    @Id
    private String categoryInstanceId;
    @Column(name = "equipment_instance_id", nullable = false, length = 100)
    @Id
    private String equipmentInstanceId;

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

        EquipmentCategoryPK that = (EquipmentCategoryPK) o;

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
}
