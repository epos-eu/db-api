package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "ispartof_category", schema = "public")
@IdClass(EDMIspartofCategoryPK.class)
public class EDMIspartofCategory {
    private String category1Id;
    private String category2Id;
    private EDMCategory categoryByCategory1Id;
    private EDMCategory categoryByCategory2Id;

    @Id
    @Column(name = "category1_id", insertable = false, updatable = false)
    public String getCategory1Id() {
        return category1Id;
    }

    public void setCategory1Id(String category1Id) {
        this.category1Id = category1Id;
    }

    @Id
    @Column(name = "category2_id", insertable = false, updatable = false)
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

        EDMIspartofCategory that = (EDMIspartofCategory) o;

        if (category1Id != null ? !category1Id.equals(that.category1Id) : that.category1Id != null) return false;
        return category2Id != null ? category2Id.equals(that.category2Id) : that.category2Id == null;
    }

    @Override
    public int hashCode() {
        int result = category1Id != null ? category1Id.hashCode() : 0;
        result = 31 * result + (category2Id != null ? category2Id.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "category1_id", referencedColumnName = "id", nullable = false)
    public EDMCategory getCategoryByCategory1Id() {
        return categoryByCategory1Id;
    }

    public void setCategoryByCategory1Id(EDMCategory categoryByCategory1Id) {
        this.categoryByCategory1Id = categoryByCategory1Id;
    }

    @ManyToOne
    @JoinColumn(name = "category2_id", referencedColumnName = "id", nullable = false)
    public EDMCategory getCategoryByCategory2Id() {
        return categoryByCategory2Id;
    }

    public void setCategoryByCategory2Id(EDMCategory categoryByCategory2Id) {
        this.categoryByCategory2Id = categoryByCategory2Id;
    }
}
