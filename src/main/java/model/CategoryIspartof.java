package model;

import jakarta.persistence.*;

@Entity
@Table(name = "category_ispartof", schema = "public", catalog = "cerif")
@IdClass(CategoryIspartofPK.class)
public class CategoryIspartof {
    @Id
    @Column(name = "category1_instance_id", nullable = false, length = 100)
    private String category1InstanceId;
    @Id
    @Column(name = "category2_instance_id", nullable = false, length = 100)
    private String category2InstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "category1_instance_id", referencedColumnName = "instance_id")
    private Category categoryByCategory1InstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "category2_instance_id", referencedColumnName = "instance_id")
    private Category categoryByCategory2InstanceId;

    public String getCategory1InstanceId() {
        return category1InstanceId;
    }

    public void setCategory1InstanceId(String category1InstanceId) {
        this.category1InstanceId = category1InstanceId;
    }

    public String getCategory2InstanceId() {
        return category2InstanceId;
    }

    public void setCategory2InstanceId(String category2InstanceId) {
        this.category2InstanceId = category2InstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryIspartof that = (CategoryIspartof) o;

        if (category1InstanceId != null ? !category1InstanceId.equals(that.category1InstanceId) : that.category1InstanceId != null)
            return false;
        if (category2InstanceId != null ? !category2InstanceId.equals(that.category2InstanceId) : that.category2InstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = category1InstanceId != null ? category1InstanceId.hashCode() : 0;
        result = 31 * result + (category2InstanceId != null ? category2InstanceId.hashCode() : 0);
        return result;
    }

    public Category getCategoryByCategory1InstanceId() {
        return categoryByCategory1InstanceId;
    }

    public void setCategoryByCategory1InstanceId(Category categoryByCategory1InstanceId) {
        this.categoryByCategory1InstanceId = categoryByCategory1InstanceId;
    }

    public Category getCategoryByCategory2InstanceId() {
        return categoryByCategory2InstanceId;
    }

    public void setCategoryByCategory2InstanceId(Category categoryByCategory2InstanceId) {
        this.categoryByCategory2InstanceId = categoryByCategory2InstanceId;
    }
}
