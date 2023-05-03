package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMSoftwareapplicationCategoryPK implements Serializable {
    private String categoryId;
    private String instanceSoftwareapplicationId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getInstanceSoftwareapplicationId() {
        return instanceSoftwareapplicationId;
    }

    public void setInstanceSoftwareapplicationId(String instanceSoftwareapplicationId) {
        this.instanceSoftwareapplicationId = instanceSoftwareapplicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMSoftwareapplicationCategoryPK that = (EDMSoftwareapplicationCategoryPK) o;

        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        return instanceSoftwareapplicationId != null ? instanceSoftwareapplicationId.equals(that.instanceSoftwareapplicationId) : that.instanceSoftwareapplicationId == null;
    }

    @Override
    public int hashCode() {
        int result = categoryId != null ? categoryId.hashCode() : 0;
        result = 31 * result + (instanceSoftwareapplicationId != null ? instanceSoftwareapplicationId.hashCode() : 0);
        return result;
    }
}
