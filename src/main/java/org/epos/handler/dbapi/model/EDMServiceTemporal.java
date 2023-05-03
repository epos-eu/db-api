package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "service_temporal")
public class EDMServiceTemporal {
    private String id;
    private Timestamp startdate;
    private Timestamp enddate;
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
    @Column(name = "startdate", nullable = true)
    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }

    @Basic
    @Column(name = "enddate", nullable = true)
    public Timestamp getEnddate() {
        return enddate;
    }

    public void setEnddate(Timestamp enddate) {
        this.enddate = enddate;
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

        EDMServiceTemporal that = (EDMServiceTemporal) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (startdate != null ? !startdate.equals(that.startdate) : that.startdate != null) return false;
        if (enddate != null ? !enddate.equals(that.enddate) : that.enddate != null) return false;
        return instanceServiceId != null ? instanceServiceId.equals(that.instanceServiceId) : that.instanceServiceId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (startdate != null ? startdate.hashCode() : 0);
        result = 31 * result + (enddate != null ? enddate.hashCode() : 0);
        result = 31 * result + (instanceServiceId != null ? instanceServiceId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_service_id", referencedColumnName = "instance_id")
    public EDMService getServiceByInstanceServiceId() {
        return serviceByInstanceServiceId;
    }

    public void setServiceByInstanceServiceId(EDMService serviceByInstanceServiceId) {
        this.serviceByInstanceServiceId = serviceByInstanceServiceId;
    }
}
