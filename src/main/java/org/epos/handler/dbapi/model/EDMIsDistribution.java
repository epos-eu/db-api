package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "is_distribution")
@IdClass(EDMIsDistributionPK.class)
@NamedQueries({
        @NamedQuery(name = "isDistribution.findByinstanceId", query = "SELECT c FROM EDMIsDistribution c where c.instanceDataproductId = :DPINST and c.instanceDistributionId = :DINST")
})public class EDMIsDistribution {
    private String instanceDataproductId;
    private String instanceDistributionId;
    private EDMDataproduct dataproductByInstanceDataproductId;
    private EDMDistribution distributionByInstanceDistributionId;

    @Id
    @Column(name = "instance_dataproduct_id", insertable = false, updatable = false)
    public String getInstanceDataproductId() {
        return instanceDataproductId;
    }

    public void setInstanceDataproductId(String instanceDataproductId) {
        this.instanceDataproductId = instanceDataproductId;
    }

    @Id
    @Column(name = "instance_distribution_id", insertable = false, updatable = false)
    public String getInstanceDistributionId() {
        return instanceDistributionId;
    }

    public void setInstanceDistributionId(String metaDistributionId) {
        this.instanceDistributionId = metaDistributionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMIsDistribution that = (EDMIsDistribution) o;

        if (instanceDataproductId != null ? !instanceDataproductId.equals(that.instanceDataproductId) : that.instanceDataproductId != null)
            return false;
        return instanceDistributionId != null ? instanceDistributionId.equals(that.instanceDistributionId) : that.instanceDistributionId == null;
    }

    @Override
    public int hashCode() {
        int result = instanceDataproductId != null ? instanceDataproductId.hashCode() : 0;
        result = 31 * result + (instanceDistributionId != null ? instanceDistributionId.hashCode() : 0);
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
    @JoinColumn(name = "instance_distribution_id", referencedColumnName = "instance_id", nullable = false)
    public EDMDistribution getDistributionByInstanceDistributionId() {
        return distributionByInstanceDistributionId;
    }

    public void setDistributionByInstanceDistributionId(EDMDistribution distributionByInstanceDistributionId) {
        this.distributionByInstanceDistributionId = distributionByInstanceDistributionId;
    }
}
