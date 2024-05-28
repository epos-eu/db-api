package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class ServiceCategoryPK implements Serializable {
    @Column(name = "category_instance_id", nullable = false, length = 100)
    @Id
    private String categoryInstanceId;
    @Column(name = "service_instance_id", nullable = false, length = 100)
    @Id
    private String serviceInstanceId;

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

        ServiceCategoryPK that = (ServiceCategoryPK) o;

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
}
