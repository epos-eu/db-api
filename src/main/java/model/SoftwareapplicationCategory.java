package model;

import jakarta.persistence.*;

@Entity
@Table(name = "softwareapplication_category", schema = "public", catalog = "cerif")
@IdClass(SoftwareapplicationCategoryPK.class)
public class SoftwareapplicationCategory {
    @Id
    @Column(name = "category_instance_id", nullable = false, length = 100)
    private String categoryInstanceId;
    @Id
    @Column(name = "softwareapplication_instance_id", nullable = false, length = 100)
    private String softwareapplicationInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "category_instance_id", referencedColumnName = "instance_id")
    private Category categoryByCategoryInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "softwareapplication_instance_id", referencedColumnName = "instance_id")
    private Softwareapplication softwareapplicationBySoftwareapplicationInstanceId;

    public String getCategoryInstanceId() {
        return categoryInstanceId;
    }

    public void setCategoryInstanceId(String categoryInstanceId) {
        this.categoryInstanceId = categoryInstanceId;
    }

    public String getSoftwareapplicationInstanceId() {
        return softwareapplicationInstanceId;
    }

    public void setSoftwareapplicationInstanceId(String softwareapplicationInstanceId) {
        this.softwareapplicationInstanceId = softwareapplicationInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SoftwareapplicationCategory that = (SoftwareapplicationCategory) o;

        if (categoryInstanceId != null ? !categoryInstanceId.equals(that.categoryInstanceId) : that.categoryInstanceId != null)
            return false;
        if (softwareapplicationInstanceId != null ? !softwareapplicationInstanceId.equals(that.softwareapplicationInstanceId) : that.softwareapplicationInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryInstanceId != null ? categoryInstanceId.hashCode() : 0;
        result = 31 * result + (softwareapplicationInstanceId != null ? softwareapplicationInstanceId.hashCode() : 0);
        return result;
    }

    public Category getCategoryByCategoryInstanceId() {
        return categoryByCategoryInstanceId;
    }

    public void setCategoryByCategoryInstanceId(Category categoryByCategoryInstanceId) {
        this.categoryByCategoryInstanceId = categoryByCategoryInstanceId;
    }

    public Softwareapplication getSoftwareapplicationBySoftwareapplicationInstanceId() {
        return softwareapplicationBySoftwareapplicationInstanceId;
    }

    public void setSoftwareapplicationBySoftwareapplicationInstanceId(Softwareapplication softwareapplicationBySoftwareapplicationInstanceId) {
        this.softwareapplicationBySoftwareapplicationInstanceId = softwareapplicationBySoftwareapplicationInstanceId;
    }
}
