package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMSoftwaresourcecodeCategoryPK implements Serializable {
    private String categoryId;
    private String instanceSoftwaresourcecodeId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getInstanceSoftwaresourcecodeId() {
        return instanceSoftwaresourcecodeId;
    }

    public void setInstanceSoftwaresourcecodeId(String instanceSoftwaresourcecodeId) {
        this.instanceSoftwaresourcecodeId = instanceSoftwaresourcecodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMSoftwaresourcecodeCategoryPK that = (EDMSoftwaresourcecodeCategoryPK) o;

        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        return instanceSoftwaresourcecodeId != null ? instanceSoftwaresourcecodeId.equals(that.instanceSoftwaresourcecodeId) : that.instanceSoftwaresourcecodeId == null;
    }

    @Override
    public int hashCode() {
        int result = categoryId != null ? categoryId.hashCode() : 0;
        result = 31 * result + (instanceSoftwaresourcecodeId != null ? instanceSoftwaresourcecodeId.hashCode() : 0);
        return result;
    }
}
