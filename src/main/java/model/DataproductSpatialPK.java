package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class DataproductSpatialPK implements Serializable {
    @Column(name = "dataproduct_instance_id", nullable = false, length = 100)
    @Id
    private String dataproductInstanceId;
    @Column(name = "spatial_instance_id", nullable = false, length = 100)
    @Id
    private String spatialInstanceId;

    public String getDataproductInstanceId() {
        return dataproductInstanceId;
    }

    public void setDataproductInstanceId(String dataproductInstanceId) {
        this.dataproductInstanceId = dataproductInstanceId;
    }

    public String getSpatialInstanceId() {
        return spatialInstanceId;
    }

    public void setSpatialInstanceId(String spatialInstanceId) {
        this.spatialInstanceId = spatialInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataproductSpatialPK that = (DataproductSpatialPK) o;

        if (dataproductInstanceId != null ? !dataproductInstanceId.equals(that.dataproductInstanceId) : that.dataproductInstanceId != null)
            return false;
        if (spatialInstanceId != null ? !spatialInstanceId.equals(that.spatialInstanceId) : that.spatialInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dataproductInstanceId != null ? dataproductInstanceId.hashCode() : 0;
        result = 31 * result + (spatialInstanceId != null ? spatialInstanceId.hashCode() : 0);
        return result;
    }
}
