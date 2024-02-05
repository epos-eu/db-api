package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "hastopconcept", schema = "public")
@IdClass(EDMHasTopConceptPK.class)
public class EDMHasTopConcept {
    private String categoryschemeId;
    private String categoryId;
    private EDMCategoryScheme categoryByCategorySchemeId;
    private EDMCategory categoryByCategoryId;

    @Id
    @Column(name = "category_scheme_id", insertable = false, updatable = false)
    public String getCategorySchemeId() {
        return categoryschemeId;
    }

    public void setCategorySchemeId(String categoryschemeId) {
        this.categoryschemeId = categoryschemeId;
    }

    @Id
    @Column(name = "category_id", insertable = false, updatable = false)
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMHasTopConcept that = (EDMHasTopConcept) o;

        if (categoryschemeId != null ? !categoryschemeId.equals(that.categoryschemeId) : that.categoryschemeId != null) return false;
        return categoryId != null ? categoryId.equals(that.categoryId) : that.categoryId == null;
    }

    @Override
    public int hashCode() {
        int result = categoryschemeId != null ? categoryschemeId.hashCode() : 0;
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "category_scheme_id", referencedColumnName = "id", nullable = false)
    public EDMCategoryScheme getCategorySchemeByCategorySchemeId() {
        return categoryByCategorySchemeId;
    }

    public void setCategorySchemeByCategorySchemeId(EDMCategoryScheme categoryByCategorySchemeId) {
        this.categoryByCategorySchemeId = categoryByCategorySchemeId;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public EDMCategory getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(EDMCategory categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }
}
