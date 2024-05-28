package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class DistributionDataproductPK implements Serializable {
    @Column(name = "distribution_instance_id", nullable = false, length = 100)
    @Id
    private String distributionInstanceId;
    @Column(name = "dataproduct_instance_id", nullable = false, length = 100)
    @Id
    private String dataproductInstanceId;

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

        DistributionDataproductPK that = (DistributionDataproductPK) o;

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
}
