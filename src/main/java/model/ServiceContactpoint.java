package model;

import jakarta.persistence.*;

@Entity
@Table(name = "service_contactpoint", schema = "public", catalog = "cerif")
@IdClass(ServiceContactpointPK.class)
public class ServiceContactpoint {
    @Id
    @Column(name = "service_instance_id", nullable = false, length = 100)
    private String serviceInstanceId;
    @Id
    @Column(name = "contactpoint_instance_id", nullable = false, length = 100)
    private String contactpointInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "service_instance_id", referencedColumnName = "instance_id")
    private Service serviceByServiceInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "contactpoint_instance_id", referencedColumnName = "instance_id")
    private Contactpoint contactpointByContactpointInstanceId;

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

        ServiceContactpoint that = (ServiceContactpoint) o;

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

    public Service getServiceByServiceInstanceId() {
        return serviceByServiceInstanceId;
    }

    public void setServiceByServiceInstanceId(Service serviceByServiceInstanceId) {
        this.serviceByServiceInstanceId = serviceByServiceInstanceId;
    }

    public Contactpoint getContactpointByContactpointInstanceId() {
        return contactpointByContactpointInstanceId;
    }

    public void setContactpointByContactpointInstanceId(Contactpoint contactpointByContactpointInstanceId) {
        this.contactpointByContactpointInstanceId = contactpointByContactpointInstanceId;
    }
}
