package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class ServiceContactpointPK implements Serializable {
    @Column(name = "service_instance_id", nullable = false, length = 100)
    @Id
    private String serviceInstanceId;
    @Column(name = "contactpoint_instance_id", nullable = false, length = 100)
    @Id
    private String contactpointInstanceId;

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public String getContactpointInstanceId() {
        return contactpointInstanceId;
    }

    public void setContactpointInstanceId(String contactpointInstanceId) {
        this.contactpointInstanceId = contactpointInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceContactpointPK that = (ServiceContactpointPK) o;

        if (serviceInstanceId != null ? !serviceInstanceId.equals(that.serviceInstanceId) : that.serviceInstanceId != null)
            return false;
        if (contactpointInstanceId != null ? !contactpointInstanceId.equals(that.contactpointInstanceId) : that.contactpointInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serviceInstanceId != null ? serviceInstanceId.hashCode() : 0;
        result = 31 * result + (contactpointInstanceId != null ? contactpointInstanceId.hashCode() : 0);
        return result;
    }
}
