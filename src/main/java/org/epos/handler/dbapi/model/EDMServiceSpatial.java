package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "service_spatial")
public class EDMServiceSpatial {
    private String id;
    private String location;
    private String instanceServiceId;
    private EDMService serviceByInstanceServiceId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
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

        EDMServiceSpatial that = (EDMServiceSpatial) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        return instanceServiceId != null ? instanceServiceId.equals(that.instanceServiceId) : that.instanceServiceId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (instanceServiceId != null ? instanceServiceId.hashCode() : 0);
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
}
