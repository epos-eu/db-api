package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "distribution_description")
public class EDMDistributionDescription {
    private String id;
    private String description;
    private String lang;
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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "lang")
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
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

        EDMDistributionDescription that = (EDMDistributionDescription) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return instanceDistributionId != null ? instanceDistributionId.equals(that.instanceDistributionId) : that.instanceDistributionId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
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
