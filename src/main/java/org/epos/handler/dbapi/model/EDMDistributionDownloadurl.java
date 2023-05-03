package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "distribution_downloadurl")
public class EDMDistributionDownloadurl {
    private String id;
    private String downloadurl;
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
    @Column(name = "downloadurl")
    public String getDownloadurl() {
        return downloadurl;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl;
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

        EDMDistributionDownloadurl that = (EDMDistributionDownloadurl) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (downloadurl != null ? !downloadurl.equals(that.downloadurl) : that.downloadurl != null) return false;
        return instanceDistributionId != null ? instanceDistributionId.equals(that.instanceDistributionId) : that.instanceDistributionId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (downloadurl != null ? downloadurl.hashCode() : 0);
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
