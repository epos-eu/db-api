package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMEquipmentCategoryPK implements Serializable {
    private String categoryId;
    private String instanceEquipmentId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

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

        EDMEquipmentCategoryPK that = (EDMEquipmentCategoryPK) o;

        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        return instanceEquipmentId != null ? instanceEquipmentId.equals(that.instanceEquipmentId) : that.instanceEquipmentId == null;
    }

    @Override
    public int hashCode() {
        int result = categoryId != null ? categoryId.hashCode() : 0;
        result = 31 * result + (instanceEquipmentId != null ? instanceEquipmentId.hashCode() : 0);
        return result;
    }
}
