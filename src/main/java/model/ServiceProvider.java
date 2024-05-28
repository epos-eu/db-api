package model;

import jakarta.persistence.*;

@Entity
@Table(name = "service_provider", schema = "public", catalog = "cerif")
public class ServiceProvider {
    @Id
    @Column(name = "service_instance_id", nullable = false, length = 100)
    private String serviceInstanceId;
    @Basic
    @Column(name = "entity_instance_id", nullable = false, length = 100)
    private String entityInstanceId;
    @Basic
    @Column(name = "resource_entity", nullable = false, length = 100)
    private String resourceEntity;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "service_instance_id", referencedColumnName = "instance_id")
    private Service serviceByServiceInstanceId;

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public String getEntityInstanceId() {
        return entityInstanceId;
    }

    public void setEntityInstanceId(String entityInstanceId) {
        this.entityInstanceId = entityInstanceId;
    }

    public String getResourceEntity() {
        return resourceEntity;
    }

    public void setResourceEntity(String resourceEntity) {
        this.resourceEntity = resourceEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProvider that = (ServiceProvider) o;

        if (serviceInstanceId != null ? !serviceInstanceId.equals(that.serviceInstanceId) : that.serviceInstanceId != null)
            return false;
        if (entityInstanceId != null ? !entityInstanceId.equals(that.entityInstanceId) : that.entityInstanceId != null)
            return false;
        if (resourceEntity != null ? !resourceEntity.equals(that.resourceEntity) : that.resourceEntity != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serviceInstanceId != null ? serviceInstanceId.hashCode() : 0;
        result = 31 * result + (entityInstanceId != null ? entityInstanceId.hashCode() : 0);
        result = 31 * result + (resourceEntity != null ? resourceEntity.hashCode() : 0);
        return result;
    }

    public Service getServiceByServiceInstanceId() {
        return serviceByServiceInstanceId;
    }

    public void setServiceByServiceInstanceId(Service serviceByServiceInstanceId) {
        this.serviceByServiceInstanceId = serviceByServiceInstanceId;
    }
}
