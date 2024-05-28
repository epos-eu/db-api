package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class ServiceSpatialPK implements Serializable {
    @Column(name = "service_instance_id", nullable = false, length = 100)
    @Id
    private String serviceInstanceId;
    @Column(name = "spatial_instance_id", nullable = false, length = 100)
    @Id
    private String spatialInstanceId;

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

        ServiceSpatialPK that = (ServiceSpatialPK) o;

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
}
