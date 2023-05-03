package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMServiceCategoryPK implements Serializable {
    private String categoryId;
    private String instanceServiceId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getInstanceServiceId() {
        return instanceServiceId;
    }

    public void setInstanceServiceId(String instanceServiceId) {
        this.instanceServiceId = instanceServiceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMServiceCategoryPK that = (EDMServiceCategoryPK) o;

        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        return instanceServiceId != null ? instanceServiceId.equals(that.instanceServiceId) : that.instanceServiceId == null;
    }

    @Override
    public int hashCode() {
        int result = categoryId != null ? categoryId.hashCode() : 0;
        result = 31 * result + (instanceServiceId != null ? instanceServiceId.hashCode() : 0);
        return result;
    }
}
