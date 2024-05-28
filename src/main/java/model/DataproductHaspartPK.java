package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class DataproductHaspartPK implements Serializable {
    @Column(name = "dataproduct1_instance_id", nullable = false, length = 100)
    @Id
    private String dataproduct1InstanceId;
    @Column(name = "dataproduct2_instance_id", nullable = false, length = 100)
    @Id
    private String dataproduct2InstanceId;

    public String getDataproduct1InstanceId() {
        return dataproduct1InstanceId;
    }

    public void setDataproduct1InstanceId(String dataproduct1InstanceId) {
        this.dataproduct1InstanceId = dataproduct1InstanceId;
    }

    public String getDataproduct2InstanceId() {
        return dataproduct2InstanceId;
    }

    public void setDataproduct2InstanceId(String dataproduct2InstanceId) {
        this.dataproduct2InstanceId = dataproduct2InstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataproductHaspartPK that = (DataproductHaspartPK) o;

        if (dataproduct1InstanceId != null ? !dataproduct1InstanceId.equals(that.dataproduct1InstanceId) : that.dataproduct1InstanceId != null)
            return false;
        if (dataproduct2InstanceId != null ? !dataproduct2InstanceId.equals(that.dataproduct2InstanceId) : that.dataproduct2InstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dataproduct1InstanceId != null ? dataproduct1InstanceId.hashCode() : 0;
        result = 31 * result + (dataproduct2InstanceId != null ? dataproduct2InstanceId.hashCode() : 0);
        return result;
    }
}
