package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMDataproductCategoryPK implements Serializable {
    private String categoryId;
    private String instanceDataproductId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getInstanceDataproductId() {
        return instanceDataproductId;
    }

    public void setInstanceDataproductId(String instanceDataproductId) {
        this.instanceDataproductId = instanceDataproductId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMDataproductCategoryPK that = (EDMDataproductCategoryPK) o;

        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        return instanceDataproductId != null ? instanceDataproductId.equals(that.instanceDataproductId) : that.instanceDataproductId == null;
    }

    @Override
    public int hashCode() {
        int result = categoryId != null ? categoryId.hashCode() : 0;
        result = 31 * result + (instanceDataproductId != null ? instanceDataproductId.hashCode() : 0);
        return result;
    }
}
