package model;

import jakarta.persistence.*;

@Entity
@Table(name = "softwaresourcecode_category", schema = "public", catalog = "cerif")
@IdClass(SoftwaresourcecodeCategoryPK.class)
public class SoftwaresourcecodeCategory {
    @Id
    @Column(name = "category_instance_id", nullable = false, length = 100)
    private String categoryInstanceId;
    @Id
    @Column(name = "softwaresourcecode_instance_id", nullable = false, length = 100)
    private String softwaresourcecodeInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "category_instance_id", referencedColumnName = "instance_id")
    private Category categoryByCategoryInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "softwaresourcecode_instance_id", referencedColumnName = "instance_id")
    private SoftwareSourceCode softwaresourcecodeBySoftwareSourceCodeInstanceId;

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

        SoftwaresourcecodeCategory that = (SoftwaresourcecodeCategory) o;

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

    public Category getCategoryByCategoryInstanceId() {
        return categoryByCategoryInstanceId;
    }

    public void setCategoryByCategoryInstanceId(Category categoryByCategoryInstanceId) {
        this.categoryByCategoryInstanceId = categoryByCategoryInstanceId;
    }

    public SoftwareSourceCode getSoftwaresourcecodeBySoftwaresourcecodeInstanceId() {
        return softwaresourcecodeBySoftwareSourceCodeInstanceId;
    }

    public void setSoftwaresourcecodeBySoftwaresourcecodeInstanceId(SoftwareSourceCode softwaresourcecodeBySoftwareSourceCodeInstanceId) {
        this.softwaresourcecodeBySoftwareSourceCodeInstanceId = softwaresourcecodeBySoftwareSourceCodeInstanceId;
    }
}
