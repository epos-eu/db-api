package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "dataproduct_temporal")
public class EDMDataproductTemporal {
    private String id;
    private Timestamp startdate;
    private Timestamp enddate;
    private String instanceDataproductId;
    private EDMDataproduct dataproductByInstanceDataproductId;

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
    @Column(name = "instance_dataproduct_id", insertable = false, updatable = false)
    public String getInstanceDataproductId() {
        return instanceDataproductId;
    }

    public void setInstanceDataproductId(String instanceDataproductId) {
        this.instanceDataproductId = instanceDataproductId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMDataproductTemporal that = (EDMDataproductTemporal) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (startdate != null ? !startdate.equals(that.startdate) : that.startdate != null) return false;
        if (enddate != null ? !enddate.equals(that.enddate) : that.enddate != null) return false;
        return instanceDataproductId != null ? instanceDataproductId.equals(that.instanceDataproductId) : that.instanceDataproductId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (startdate != null ? startdate.hashCode() : 0);
        result = 31 * result + (enddate != null ? enddate.hashCode() : 0);
        result = 31 * result + (instanceDataproductId != null ? instanceDataproductId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_dataproduct_id", referencedColumnName = "instance_id")
    public EDMDataproduct getDataproductByInstanceDataproductId() {
        return dataproductByInstanceDataproductId;
    }

    public void setDataproductByInstanceDataproductId(EDMDataproduct dataproductByInstanceDataproductId) {
        this.dataproductByInstanceDataproductId = dataproductByInstanceDataproductId;
    }
}
