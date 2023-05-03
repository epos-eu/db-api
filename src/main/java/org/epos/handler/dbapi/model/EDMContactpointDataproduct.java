package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "contactpoint_dataproduct")
@IdClass(EDMContactpointDataproductPK.class)
public class EDMContactpointDataproduct {
    private String instanceDataproductId;
    private String instanceContactpointId;
    private EDMDataproduct dataproductByInstanceDataproductId;
    private EDMContactpoint contactpointByInstanceContactpointId;

    @Id
    @Column(name = "instance_dataproduct_id", insertable = false, updatable = false)
    public String getInstanceDataproductId() {
        return instanceDataproductId;
    }

    public void setInstanceDataproductId(String instanceDataproductId) {
        this.instanceDataproductId = instanceDataproductId;
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

        EDMContactpointDataproduct that = (EDMContactpointDataproduct) o;

        if (instanceDataproductId != null ? !instanceDataproductId.equals(that.instanceDataproductId) : that.instanceDataproductId != null)
            return false;
        return instanceContactpointId != null ? instanceContactpointId.equals(that.instanceContactpointId) : that.instanceContactpointId == null;
    }

    @Override
    public int hashCode() {
        int result = instanceDataproductId != null ? instanceDataproductId.hashCode() : 0;
        result = 31 * result + (instanceContactpointId != null ? instanceContactpointId.hashCode() : 0);
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

    @ManyToOne
    @JoinColumn(name = "instance_contactpoint_id", referencedColumnName = "instance_id", nullable = false)
    public EDMContactpoint getContactpointByInstanceContactpointId() {
        return contactpointByInstanceContactpointId;
    }

    public void setContactpointByInstanceContactpointId(EDMContactpoint contactpointByInstanceContactpointId) {
        this.contactpointByInstanceContactpointId = contactpointByInstanceContactpointId;
    }
}
