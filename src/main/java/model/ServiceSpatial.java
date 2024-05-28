package model;

import jakarta.persistence.*;

@Entity
@Table(name = "service_spatial", schema = "public", catalog = "cerif")
@IdClass(ServiceSpatialPK.class)
public class ServiceSpatial {
    @Id
    @Column(name = "service_instance_id", nullable = false, length = 100)
    private String serviceInstanceId;
    @Id
    @Column(name = "spatial_instance_id", nullable = false, length = 100)
    private String spatialInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "service_instance_id", referencedColumnName = "instance_id")
    private Service serviceByServiceInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "spatial_instance_id", referencedColumnName = "instance_id")
    private Spatial spatialBySpatialInstanceId;

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public String getSpatialInstanceId() {
        return spatialInstanceId;
    }

    public void setSpatialInstanceId(String spatialInstanceId) {
        this.spatialInstanceId = spatialInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceSpatial that = (ServiceSpatial) o;

        if (serviceInstanceId != null ? !serviceInstanceId.equals(that.serviceInstanceId) : that.serviceInstanceId != null)
            return false;
        if (spatialInstanceId != null ? !spatialInstanceId.equals(that.spatialInstanceId) : that.spatialInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serviceInstanceId != null ? serviceInstanceId.hashCode() : 0;
        result = 31 * result + (spatialInstanceId != null ? spatialInstanceId.hashCode() : 0);
        return result;
    }

    public Service getServiceByServiceInstanceId() {
        return serviceByServiceInstanceId;
    }

    public void setServiceByServiceInstanceId(Service serviceByServiceInstanceId) {
        this.serviceByServiceInstanceId = serviceByServiceInstanceId;
    }

    public Spatial getSpatialBySpatialInstanceId() {
        return spatialBySpatialInstanceId;
    }

    public void setSpatialBySpatialInstanceId(Spatial spatialBySpatialInstanceId) {
        this.spatialBySpatialInstanceId = spatialBySpatialInstanceId;
    }
}
