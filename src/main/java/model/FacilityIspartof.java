package model;

import jakarta.persistence.*;

@Entity
@Table(name = "facility_ispartof", schema = "public", catalog = "cerif")
@IdClass(FacilityIspartofPK.class)
public class FacilityIspartof {
    @Id
    @Column(name = "facility1_instance_id", nullable = false, length = 100)
    private String facility1InstanceId;
    @Id
    @Column(name = "facility2_instance_id", nullable = false, length = 100)
    private String facility2InstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "facility1_instance_id", referencedColumnName = "instance_id")
    private Facility facilityByFacility1InstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "facility2_instance_id", referencedColumnName = "instance_id")
    private Facility facilityByFacility2InstanceId;

    public String getFacility1InstanceId() {
        return facility1InstanceId;
    }

    public void setFacility1InstanceId(String facility1InstanceId) {
        this.facility1InstanceId = facility1InstanceId;
    }

    public String getFacility2InstanceId() {
        return facility2InstanceId;
    }

    public void setFacility2InstanceId(String facility2InstanceId) {
        this.facility2InstanceId = facility2InstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FacilityIspartof that = (FacilityIspartof) o;

        if (facility1InstanceId != null ? !facility1InstanceId.equals(that.facility1InstanceId) : that.facility1InstanceId != null)
            return false;
        if (facility2InstanceId != null ? !facility2InstanceId.equals(that.facility2InstanceId) : that.facility2InstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = facility1InstanceId != null ? facility1InstanceId.hashCode() : 0;
        result = 31 * result + (facility2InstanceId != null ? facility2InstanceId.hashCode() : 0);
        return result;
    }

    public Facility getFacilityByFacility1InstanceId() {
        return facilityByFacility1InstanceId;
    }

    public void setFacilityByFacility1InstanceId(Facility facilityByFacility1InstanceId) {
        this.facilityByFacility1InstanceId = facilityByFacility1InstanceId;
    }

    public Facility getFacilityByFacility2InstanceId() {
        return facilityByFacility2InstanceId;
    }

    public void setFacilityByFacility2InstanceId(Facility facilityByFacility2InstanceId) {
        this.facilityByFacility2InstanceId = facilityByFacility2InstanceId;
    }
}
