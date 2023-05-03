package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "distribution_accessurl")
public class EDMDistributionAccessurl {
    private String id;
    private String accessurl;
    private String instanceDistributionId;
    private EDMDistribution distributionByInstanceDistributionId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "accessurl")
    public String getAccessurl() {
        return accessurl;
    }

    public void setAccessurl(String accessurl) {
        this.accessurl = accessurl;
    }

    @Basic
    @Column(name = "instance_distribution_id", insertable = false, updatable = false)
    public String getInstanceDistributionId() {
        return instanceDistributionId;
    }

    public void setInstanceDistributionId(String instanceDistributionId) {
        this.instanceDistributionId = instanceDistributionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMDistributionAccessurl that = (EDMDistributionAccessurl) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (accessurl != null ? !accessurl.equals(that.accessurl) : that.accessurl != null) return false;
        return instanceDistributionId != null ? instanceDistributionId.equals(that.instanceDistributionId) : that.instanceDistributionId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (accessurl != null ? accessurl.hashCode() : 0);
        result = 31 * result + (instanceDistributionId != null ? instanceDistributionId.hashCode() : 0);
        return result;
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
