package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMPublicationCategoryPK implements Serializable {
    private String categoryId;
    private String instancePublicationId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getInstancePublicationId() {
        return instancePublicationId;
    }

    public void setInstancePublicationId(String instancePublicationId) {
        this.instancePublicationId = instancePublicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMPublicationCategoryPK that = (EDMPublicationCategoryPK) o;

        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        return instancePublicationId != null ? instancePublicationId.equals(that.instancePublicationId) : that.instancePublicationId == null;
    }

    @Override
    public int hashCode() {
        int result = categoryId != null ? categoryId.hashCode() : 0;
        result = 31 * result + (instancePublicationId != null ? instancePublicationId.hashCode() : 0);
        return result;
    }
}
