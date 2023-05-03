package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "softwareapplication_category")
@IdClass(EDMSoftwareapplicationCategoryPK.class)
public class EDMSoftwareapplicationCategory {
    private String categoryId;
    private String instanceSoftwareapplicationId;
    private EDMCategory categoryByCategoryId;
    private EDMSoftwareapplication softwareapplicationByInstanceSoftwareapplicationId;

    @Id
    @Column(name = "category_id", insertable = false, updatable = false)
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Id
    @Column(name = "instance_softwareapplication_id", insertable = false, updatable = false)
    public String getInstanceSoftwareapplicationId() {
        return instanceSoftwareapplicationId;
    }

    public void setInstanceSoftwareapplicationId(String instanceSoftwareapplicationId) {
        this.instanceSoftwareapplicationId = instanceSoftwareapplicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMSoftwareapplicationCategory that = (EDMSoftwareapplicationCategory) o;

        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        return instanceSoftwareapplicationId != null ? instanceSoftwareapplicationId.equals(that.instanceSoftwareapplicationId) : that.instanceSoftwareapplicationId == null;
    }

    @Override
    public int hashCode() {
        int result = categoryId != null ? categoryId.hashCode() : 0;
        result = 31 * result + (instanceSoftwareapplicationId != null ? instanceSoftwareapplicationId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public EDMCategory getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(EDMCategory categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }

    @ManyToOne
    @JoinColumn(name = "instance_softwareapplication_id", referencedColumnName = "instance_id", nullable = false)
    public EDMSoftwareapplication getSoftwareapplicationByInstanceSoftwareapplicationId() {
        return softwareapplicationByInstanceSoftwareapplicationId;
    }

    public void setSoftwareapplicationByInstanceSoftwareapplicationId(EDMSoftwareapplication softwareapplicationByInstanceSoftwareapplicationId) {
        this.softwareapplicationByInstanceSoftwareapplicationId = softwareapplicationByInstanceSoftwareapplicationId;
    }
}
