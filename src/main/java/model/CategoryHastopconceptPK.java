package model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import java.io.Serializable;

public class CategoryHastopconceptPK implements Serializable {
    @Column(name = "category_scheme_instance_id", nullable = false, length = 100)
    @Id
    private String categorySchemeInstanceId;
    @Column(name = "category_instance_id", nullable = false, length = 100)
    @Id
    private String categoryInstanceId;

    public String getCategorySchemeInstanceId() {
        return categorySchemeInstanceId;
    }

    public void setCategorySchemeInstanceId(String categorySchemeInstanceId) {
        this.categorySchemeInstanceId = categorySchemeInstanceId;
    }

    public String getCategoryInstanceId() {
        return categoryInstanceId;
    }

    public void setCategoryInstanceId(String categoryInstanceId) {
        this.categoryInstanceId = categoryInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryHastopconceptPK that = (CategoryHastopconceptPK) o;

        if (categorySchemeInstanceId != null ? !categorySchemeInstanceId.equals(that.categorySchemeInstanceId) : that.categorySchemeInstanceId != null)
            return false;
        if (categoryInstanceId != null ? !categoryInstanceId.equals(that.categoryInstanceId) : that.categoryInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categorySchemeInstanceId != null ? categorySchemeInstanceId.hashCode() : 0;
        result = 31 * result + (categoryInstanceId != null ? categoryInstanceId.hashCode() : 0);
        return result;
    }
}
