package model;

import jakarta.persistence.*;

@Entity
@Table(name = "webservice_distribution", schema = "public", catalog = "cerif")
@IdClass(WebserviceDistributionPK.class)
public class WebserviceDistribution {
    @Id
    @Column(name = "distribution_instance_id", nullable = false, length = 100)
    private String distributionInstanceId;
    @Id
    @Column(name = "webservice_instance_id", nullable = false, length = 100)
    private String webserviceInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "distribution_instance_id", referencedColumnName = "instance_id")
    private Distribution distributionByDistributionInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "webservice_instance_id", referencedColumnName = "instance_id")
    private Webservice webserviceByWebserviceInstanceId;

    public String getDistributionInstanceId() {
        return distributionInstanceId;
    }

    public void setDistributionInstanceId(String distributionInstanceId) {
        this.distributionInstanceId = distributionInstanceId;
    }

    public String getWebserviceInstanceId() {
        return webserviceInstanceId;
    }

    public void setWebserviceInstanceId(String webserviceInstanceId) {
        this.webserviceInstanceId = webserviceInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebserviceDistribution that = (WebserviceDistribution) o;

        if (distributionInstanceId != null ? !distributionInstanceId.equals(that.distributionInstanceId) : that.distributionInstanceId != null)
            return false;
        if (webserviceInstanceId != null ? !webserviceInstanceId.equals(that.webserviceInstanceId) : that.webserviceInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = distributionInstanceId != null ? distributionInstanceId.hashCode() : 0;
        result = 31 * result + (webserviceInstanceId != null ? webserviceInstanceId.hashCode() : 0);
        return result;
    }

    public Distribution getDistributionByDistributionInstanceId() {
        return distributionByDistributionInstanceId;
    }

    public void setDistributionByDistributionInstanceId(Distribution distributionByDistributionInstanceId) {
        this.distributionByDistributionInstanceId = distributionByDistributionInstanceId;
    }

    public Webservice getWebserviceByWebserviceInstanceId() {
        return webserviceByWebserviceInstanceId;
    }

    public void setWebserviceByWebserviceInstanceId(Webservice webserviceByWebserviceInstanceId) {
        this.webserviceByWebserviceInstanceId = webserviceByWebserviceInstanceId;
    }
}
