package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMIspartofCategoryPK implements Serializable {
    private String category1Id;
    private String category2Id;

    public String getCategory1Id() {
        return category1Id;
    }

    public void setCategory1Id(String category1Id) {
        this.category1Id = category1Id;
    }

    public String getCategory2Id() {
        return category2Id;
    }

    public void setCategory2Id(String category2Id) {
        this.category2Id = category2Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMIspartofCategoryPK that = (EDMIspartofCategoryPK) o;

        if (category1Id != null ? !category1Id.equals(that.category1Id) : that.category1Id != null) return false;
        return category2Id != null ? category2Id.equals(that.category2Id) : that.category2Id == null;
    }

    @Override
    public int hashCode() {
        int result = category1Id != null ? category1Id.hashCode() : 0;
        result = 31 * result + (category2Id != null ? category2Id.hashCode() : 0);
        return result;
    }
}
