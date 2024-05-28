package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class SoftwaresourcecodeCategoryPK implements Serializable {
    @Column(name = "category_instance_id", nullable = false, length = 100)
    @Id
    private String categoryInstanceId;
    @Column(name = "softwaresourcecode_instance_id", nullable = false, length = 100)
    @Id
    private String softwaresourcecodeInstanceId;

    public String getCategoryInstanceId() {
        return categoryInstanceId;
    }

    public void setCategoryInstanceId(String categoryInstanceId) {
        this.categoryInstanceId = categoryInstanceId;
    }

    public String getSoftwaresourcecodeInstanceId() {
        return softwaresourcecodeInstanceId;
    }

    public void setSoftwaresourcecodeInstanceId(String softwaresourcecodeInstanceId) {
        this.softwaresourcecodeInstanceId = softwaresourcecodeInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SoftwaresourcecodeCategoryPK that = (SoftwaresourcecodeCategoryPK) o;

        if (categoryInstanceId != null ? !categoryInstanceId.equals(that.categoryInstanceId) : that.categoryInstanceId != null)
            return false;
        if (softwaresourcecodeInstanceId != null ? !softwaresourcecodeInstanceId.equals(that.softwaresourcecodeInstanceId) : that.softwaresourcecodeInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryInstanceId != null ? categoryInstanceId.hashCode() : 0;
        result = 31 * result + (softwaresourcecodeInstanceId != null ? softwaresourcecodeInstanceId.hashCode() : 0);
        return result;
    }
}
