package model;

import jakarta.persistence.*;

@Entity
@Table(name = "distribution_dataproduct", schema = "public", catalog = "cerif")
@IdClass(DistributionDataproductPK.class)
public class DistributionDataproduct {
    @Id
    @Column(name = "distribution_instance_id", nullable = false, length = 100)
    private String distributionInstanceId;
    @Id
    @Column(name = "dataproduct_instance_id", nullable = false, length = 100)
    private String dataproductInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "distribution_instance_id", referencedColumnName = "instance_id")
    private Distribution distributionByDistributionInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "dataproduct_instance_id", referencedColumnName = "instance_id")
    private Dataproduct dataproductByDataproductInstanceId;

    public String getDistributionInstanceId() {
        return distributionInstanceId;
    }

    public void setDistributionInstanceId(String distributionInstanceId) {
        this.distributionInstanceId = distributionInstanceId;
    }

    public String getDataproductInstanceId() {
        return dataproductInstanceId;
    }

    public void setDataproductInstanceId(String dataproductInstanceId) {
        this.dataproductInstanceId = dataproductInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DistributionDataproduct that = (DistributionDataproduct) o;

        if (distributionInstanceId != null ? !distributionInstanceId.equals(that.distributionInstanceId) : that.distributionInstanceId != null)
            return false;
        if (dataproductInstanceId != null ? !dataproductInstanceId.equals(that.dataproductInstanceId) : that.dataproductInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = distributionInstanceId != null ? distributionInstanceId.hashCode() : 0;
        result = 31 * result + (dataproductInstanceId != null ? dataproductInstanceId.hashCode() : 0);
        return result;
    }

    public Distribution getDistributionByDistributionInstanceId() {
        return distributionByDistributionInstanceId;
    }

    public void setDistributionByDistributionInstanceId(Distribution distributionByDistributionInstanceId) {
        this.distributionByDistributionInstanceId = distributionByDistributionInstanceId;
    }

    public Dataproduct getDataproductByDataproductInstanceId() {
        return dataproductByDataproductInstanceId;
    }

    public void setDataproductByDataproductInstanceId(Dataproduct dataproductByDataproductInstanceId) {
        this.dataproductByDataproductInstanceId = dataproductByDataproductInstanceId;
    }
}
