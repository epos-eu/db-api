package model;

import jakarta.persistence.*;

@Entity
@Table(name = "category_hastopconcept", schema = "public", catalog = "cerif")
@IdClass(CategoryHastopconceptPK.class)
public class CategoryHastopconcept {
    @Id
    @Column(name = "category_scheme_instance_id", nullable = false, length = 100)
    private String categorySchemeInstanceId;
    @Id
    @Column(name = "category_instance_id", nullable = false, length = 100)
    private String categoryInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "category_scheme_instance_id", referencedColumnName = "instance_id")
    private CategoryScheme categorySchemeByCategorySchemeInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "category_instance_id", referencedColumnName = "instance_id")
    private Category categoryByCategoryInstanceId;

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

        CategoryHastopconcept that = (CategoryHastopconcept) o;

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

    public CategoryScheme getCategorySchemeByCategorySchemeInstanceId() {
        return categorySchemeByCategorySchemeInstanceId;
    }

    public void setCategorySchemeByCategorySchemeInstanceId(CategoryScheme categorySchemeByCategorySchemeInstanceId) {
        this.categorySchemeByCategorySchemeInstanceId = categorySchemeByCategorySchemeInstanceId;
    }

    public Category getCategoryByCategoryInstanceId() {
        return categoryByCategoryInstanceId;
    }

    public void setCategoryByCategoryInstanceId(Category categoryByCategoryInstanceId) {
        this.categoryByCategoryInstanceId = categoryByCategoryInstanceId;
    }
}
