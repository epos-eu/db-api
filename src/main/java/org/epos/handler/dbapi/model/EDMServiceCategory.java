package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "service_category")
@IdClass(EDMServiceCategoryPK.class)
public class EDMServiceCategory {
    private String categoryId;
    private String instanceServiceId;
    private EDMCategory categoryByCategoryId;
    private EDMService serviceByInstanceServiceId;

    @Id
    @Column(name = "category_id", insertable = false, updatable = false)
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Id
    @Column(name = "instance_service_id", insertable = false, updatable = false)
    public String getInstanceServiceId() {
        return instanceServiceId;
    }

    public void setInstanceServiceId(String instanceServiceId) {
        this.instanceServiceId = instanceServiceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMServiceCategory that = (EDMServiceCategory) o;

        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        return instanceServiceId != null ? instanceServiceId.equals(that.instanceServiceId) : that.instanceServiceId == null;
    }

    @Override
    public int hashCode() {
        int result = categoryId != null ? categoryId.hashCode() : 0;
        result = 31 * result + (instanceServiceId != null ? instanceServiceId.hashCode() : 0);
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
    @JoinColumn(name = "instance_service_id", referencedColumnName = "instance_id", nullable = false)
    public EDMService getServiceByInstanceServiceId() {
        return serviceByInstanceServiceId;
    }

    public void setServiceByInstanceServiceId(EDMService serviceByInstanceServiceId) {
        this.serviceByInstanceServiceId = serviceByInstanceServiceId;
    }
}
