package model;

import jakarta.persistence.*;

@Entity
@Table(name = "webservice_spatial", schema = "public", catalog = "cerif")
@IdClass(WebserviceSpatialPK.class)
public class WebserviceSpatial {
    @Id
    @Column(name = "webservice_instance_id", nullable = false, length = 100)
    private String webserviceInstanceId;
    @Id
    @Column(name = "spatial_instance_id", nullable = false, length = 100)
    private String spatialInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "webservice_instance_id", referencedColumnName = "instance_id")
    private Webservice webserviceByWebserviceInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "spatial_instance_id", referencedColumnName = "instance_id")
    private Spatial spatialBySpatialInstanceId;

    public String getWebserviceInstanceId() {
        return webserviceInstanceId;
    }

    public void setWebserviceInstanceId(String webserviceInstanceId) {
        this.webserviceInstanceId = webserviceInstanceId;
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

        WebserviceSpatial that = (WebserviceSpatial) o;

        if (webserviceInstanceId != null ? !webserviceInstanceId.equals(that.webserviceInstanceId) : that.webserviceInstanceId != null)
            return false;
        if (spatialInstanceId != null ? !spatialInstanceId.equals(that.spatialInstanceId) : that.spatialInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = webserviceInstanceId != null ? webserviceInstanceId.hashCode() : 0;
        result = 31 * result + (spatialInstanceId != null ? spatialInstanceId.hashCode() : 0);
        return result;
    }

    public Webservice getWebserviceByWebserviceInstanceId() {
        return webserviceByWebserviceInstanceId;
    }

    public void setWebserviceByWebserviceInstanceId(Webservice webserviceByWebserviceInstanceId) {
        this.webserviceByWebserviceInstanceId = webserviceByWebserviceInstanceId;
    }

    public Spatial getSpatialBySpatialInstanceId() {
        return spatialBySpatialInstanceId;
    }

    public void setSpatialBySpatialInstanceId(Spatial spatialBySpatialInstanceId) {
        this.spatialBySpatialInstanceId = spatialBySpatialInstanceId;
    }
}
