package model;

import jakarta.persistence.*;

@Entity
@Table(name = "facility_spatial", schema = "public", catalog = "cerif")
@IdClass(FacilitySpatialPK.class)
public class FacilitySpatial {
    @Id
    @Column(name = "facility_instance_id", nullable = false, length = 100)
    private String facilityInstanceId;
    @Id
    @Column(name = "spatial_instance_id", nullable = false, length = 100)
    private String spatialInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "facility_instance_id", referencedColumnName = "instance_id")
    private Facility facilityByFacilityInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "spatial_instance_id", referencedColumnName = "instance_id")
    private Spatial spatialBySpatialInstanceId;

    public String getFacilityInstanceId() {
        return facilityInstanceId;
    }

    public void setFacilityInstanceId(String facilityInstanceId) {
        this.facilityInstanceId = facilityInstanceId;
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

        FacilitySpatial that = (FacilitySpatial) o;

        if (facilityInstanceId != null ? !facilityInstanceId.equals(that.facilityInstanceId) : that.facilityInstanceId != null)
            return false;
        if (spatialInstanceId != null ? !spatialInstanceId.equals(that.spatialInstanceId) : that.spatialInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = facilityInstanceId != null ? facilityInstanceId.hashCode() : 0;
        result = 31 * result + (spatialInstanceId != null ? spatialInstanceId.hashCode() : 0);
        return result;
    }

    public Facility getFacilityByFacilityInstanceId() {
        return facilityByFacilityInstanceId;
    }

    public void setFacilityByFacilityInstanceId(Facility facilityByFacilityInstanceId) {
        this.facilityByFacilityInstanceId = facilityByFacilityInstanceId;
    }

    public Spatial getSpatialBySpatialInstanceId() {
        return spatialBySpatialInstanceId;
    }

    public void setSpatialBySpatialInstanceId(Spatial spatialBySpatialInstanceId) {
        this.spatialBySpatialInstanceId = spatialBySpatialInstanceId;
    }
}
