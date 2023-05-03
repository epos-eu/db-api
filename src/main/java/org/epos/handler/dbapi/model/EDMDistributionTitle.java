package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "distribution_title")
@NamedQueries({
        @NamedQuery(name = "distribution_title.findAll", query = "SELECT c FROM EDMDistributionTitle c"),
        @NamedQuery(name = "distribution_title.findByName", query = "select c from EDMDistributionTitle c where c.title = :TITLE")
})
public class EDMDistributionTitle {
    private String id;
    private String title;
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
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

        EDMDistributionTitle that = (EDMDistributionTitle) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return instanceDistributionId != null ? instanceDistributionId.equals(that.instanceDistributionId) : that.instanceDistributionId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
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
