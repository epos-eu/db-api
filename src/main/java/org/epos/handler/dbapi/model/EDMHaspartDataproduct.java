package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "haspart_dataproduct")
@IdClass(EDMHaspartDataproductPK.class)
public class EDMHaspartDataproduct {
    private String instanceDataproduct1Id;
    private String instanceDataproduct2Id;
    private EDMDataproduct dataproductByInstanceDataproduct1Id;
    private EDMDataproduct dataproductByInstanceDataproduct2Id;

    @Id
    @Column(name = "instance_dataproduct1_id", insertable = false, updatable = false)
    public String getInstanceDataproduct1Id() {
        return instanceDataproduct1Id;
    }

    public void setInstanceDataproduct1Id(String instanceDataproduct1Id) {
        this.instanceDataproduct1Id = instanceDataproduct1Id;
    }

    @Id
    @Column(name = "instance_dataproduct2_id", insertable = false, updatable = false)
    public String getInstanceDataproduct2Id() {
        return instanceDataproduct2Id;
    }

    public void setInstanceDataproduct2Id(String metaDataproduct2Id) {
        this.instanceDataproduct2Id = metaDataproduct2Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMHaspartDataproduct that = (EDMHaspartDataproduct) o;

        if (instanceDataproduct1Id != null ? !instanceDataproduct1Id.equals(that.instanceDataproduct1Id) : that.instanceDataproduct1Id != null)
            return false;
        return instanceDataproduct2Id != null ? instanceDataproduct2Id.equals(that.instanceDataproduct2Id) : that.instanceDataproduct2Id == null;
    }

    @Override
    public int hashCode() {
        int result = instanceDataproduct1Id != null ? instanceDataproduct1Id.hashCode() : 0;
        result = 31 * result + (instanceDataproduct2Id != null ? instanceDataproduct2Id.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_dataproduct1_id", referencedColumnName = "instance_id", nullable = false)
    public EDMDataproduct getDataproductByInstanceDataproduct1Id() {
        return dataproductByInstanceDataproduct1Id;
    }

    public void setDataproductByInstanceDataproduct1Id(EDMDataproduct dataproductByInstanceDataproduct1Id) {
        this.dataproductByInstanceDataproduct1Id = dataproductByInstanceDataproduct1Id;
    }

    @ManyToOne
    @JoinColumn(name = "instance_dataproduct2_id", referencedColumnName = "instance_id", nullable = false)
    public EDMDataproduct getDataproductByInstanceDataproduct2Id() {
        return dataproductByInstanceDataproduct2Id;
    }

    public void setDataproductByInstanceDataproduct2Id(EDMDataproduct dataproductByInstanceDataproduct2Id) {
        this.dataproductByInstanceDataproduct2Id = dataproductByInstanceDataproduct2Id;
    }
}
