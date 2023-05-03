package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "dataproduct_spatial")
public class EDMDataproductSpatial {
    private String id;
    private String location;
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
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

        EDMDataproductSpatial that = (EDMDataproductSpatial) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        return instanceDataproductId != null ? instanceDataproductId.equals(that.instanceDataproductId) : that.instanceDataproductId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (instanceDataproductId != null ? instanceDataproductId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_dataproduct_id", referencedColumnName = "instance_id", nullable = false)
    public EDMDataproduct getDataproductByInstanceDataproductId() {
        return dataproductByInstanceDataproductId;
    }

    public void setDataproductByInstanceDataproductId(EDMDataproduct dataproductByInstanceDataproductId) {
        this.dataproductByInstanceDataproductId = dataproductByInstanceDataproductId;
    }
}
