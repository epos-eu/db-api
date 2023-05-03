package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMWebserviceCategoryPK implements Serializable {
    private String categoryId;
    private String instanceWebserviceId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getInstanceWebserviceId() {
        return instanceWebserviceId;
    }

    public void setInstanceWebserviceId(String instanceWebserviceId) {
        this.instanceWebserviceId = instanceWebserviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMWebserviceCategoryPK that = (EDMWebserviceCategoryPK) o;

        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        return instanceWebserviceId != null ? instanceWebserviceId.equals(that.instanceWebserviceId) : that.instanceWebserviceId == null;
    }

    @Override
    public int hashCode() {
        int result = categoryId != null ? categoryId.hashCode() : 0;
        result = 31 * result + (instanceWebserviceId != null ? instanceWebserviceId.hashCode() : 0);
        return result;
    }
}
