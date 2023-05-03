package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "contactpoint_service")
@IdClass(EDMContactpointServicePK.class)
public class EDMContactpointService {
    private String instanceServiceId;
    private String instanceContactpointId;
    private EDMService serviceByInstanceServiceId;
    private EDMContactpoint contactpointByInstanceContactpointId;

    @Id
    @Column(name = "instance_service_id", insertable = false, updatable = false)
    public String getInstanceServiceId() {
        return instanceServiceId;
    }

    public void setInstanceServiceId(String instanceServiceId) {
        this.instanceServiceId = instanceServiceId;
    }

    @Id
    @Column(name = "instance_contactpoint_id", insertable = false, updatable = false)
    public String getInstanceContactpointId() {
        return instanceContactpointId;
    }

    public void setInstanceContactpointId(String metaContactpointId) {
        this.instanceContactpointId = metaContactpointId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMContactpointService that = (EDMContactpointService) o;

        if (instanceServiceId != null ? !instanceServiceId.equals(that.instanceServiceId) : that.instanceServiceId != null)
            return false;
        return instanceContactpointId != null ? instanceContactpointId.equals(that.instanceContactpointId) : that.instanceContactpointId == null;
    }

    @Override
    public int hashCode() {
        int result = instanceServiceId != null ? instanceServiceId.hashCode() : 0;
        result = 31 * result + (instanceContactpointId != null ? instanceContactpointId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_service_id", referencedColumnName = "instance_id", nullable = false)
    public EDMService getServiceByInstanceServiceId() {
        return serviceByInstanceServiceId;
    }

    public void setServiceByInstanceServiceId(EDMService serviceByInstanceServiceId) {
        this.serviceByInstanceServiceId = serviceByInstanceServiceId;
    }

    @ManyToOne
    @JoinColumn(name = "instance_contactpoint_id", referencedColumnName = "instance_id", nullable = false)
    public EDMContactpoint getContactpointByInstanceContactpointId() {
        return contactpointByInstanceContactpointId;
    }

    public void setContactpointByInstanceContactpointId(EDMContactpoint contactpointByInstanceContactpointId) {
        this.contactpointByInstanceContactpointId = contactpointByInstanceContactpointId;
    }
}
