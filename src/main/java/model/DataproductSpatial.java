package model;

import jakarta.persistence.*;

@Entity
@Table(name = "dataproduct_spatial", schema = "public", catalog = "cerif")
@IdClass(DataproductSpatialPK.class)
public class DataproductSpatial {
    @Id
    @Column(name = "dataproduct_instance_id", nullable = false, length = 100)
    private String dataproductInstanceId;
    @Id
    @Column(name = "spatial_instance_id", nullable = false, length = 100)
    private String spatialInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "dataproduct_instance_id", referencedColumnName = "instance_id")
    private Dataproduct dataproductByDataproductInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "spatial_instance_id", referencedColumnName = "instance_id")
    private Spatial spatialBySpatialInstanceId;

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

        DataproductSpatial that = (DataproductSpatial) o;

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

    public Dataproduct getDataproductByDataproductInstanceId() {
        return dataproductByDataproductInstanceId;
    }

    public void setDataproductByDataproductInstanceId(Dataproduct dataproductByDataproductInstanceId) {
        this.dataproductByDataproductInstanceId = dataproductByDataproductInstanceId;
    }

    public Spatial getSpatialBySpatialInstanceId() {
        return spatialBySpatialInstanceId;
    }

    public void setSpatialBySpatialInstanceId(Spatial spatialBySpatialInstanceId) {
        this.spatialBySpatialInstanceId = spatialBySpatialInstanceId;
    }
}
