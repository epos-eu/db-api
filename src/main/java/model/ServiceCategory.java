package model;

import jakarta.persistence.*;

@Entity
@Table(name = "service_category", schema = "public", catalog = "cerif")
@IdClass(ServiceCategoryPK.class)
public class ServiceCategory {
    @Id
    @Column(name = "category_instance_id", nullable = false, length = 100)
    private String categoryInstanceId;
    @Id
    @Column(name = "service_instance_id", nullable = false, length = 100)
    private String serviceInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "category_instance_id", referencedColumnName = "instance_id")
    private Category categoryByCategoryInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "service_instance_id", referencedColumnName = "instance_id")
    private Service serviceByServiceInstanceId;

    public String getCategoryInstanceId() {
        return categoryInstanceId;
    }

    public void setCategoryInstanceId(String categoryInstanceId) {
        this.categoryInstanceId = categoryInstanceId;
    }

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceCategory that = (ServiceCategory) o;

        if (categoryInstanceId != null ? !categoryInstanceId.equals(that.categoryInstanceId) : that.categoryInstanceId != null)
            return false;
        if (serviceInstanceId != null ? !serviceInstanceId.equals(that.serviceInstanceId) : that.serviceInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryInstanceId != null ? categoryInstanceId.hashCode() : 0;
        result = 31 * result + (serviceInstanceId != null ? serviceInstanceId.hashCode() : 0);
        return result;
    }

    public Category getCategoryByCategoryInstanceId() {
        return categoryByCategoryInstanceId;
    }

    public void setCategoryByCategoryInstanceId(Category categoryByCategoryInstanceId) {
        this.categoryByCategoryInstanceId = categoryByCategoryInstanceId;
    }

    public Service getServiceByServiceInstanceId() {
        return serviceByServiceInstanceId;
    }

    public void setServiceByServiceInstanceId(Service serviceByServiceInstanceId) {
        this.serviceByServiceInstanceId = serviceByServiceInstanceId;
    }
}
