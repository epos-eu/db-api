package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "dataproduct_provenance")
public class EDMDataproductProvenance {
    private String id;
    private String provenance;
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
    @Column(name = "provenance")
    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
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

        EDMDataproductProvenance that = (EDMDataproductProvenance) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (provenance != null ? !provenance.equals(that.provenance) : that.provenance != null) return false;
        return instanceDataproductId != null ? instanceDataproductId.equals(that.instanceDataproductId) : that.instanceDataproductId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (provenance != null ? provenance.hashCode() : 0);
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
