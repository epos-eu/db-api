package model;

import jakarta.persistence.*;

@Entity
@Table(name = "facility_contactpoint", schema = "public", catalog = "cerif")
@IdClass(FacilityContactpointPK.class)
public class FacilityContactpoint {
    @Id
    @Column(name = "facility_instance_id", nullable = false, length = 100)
    private String facilityInstanceId;
    @Id
    @Column(name = "contactpoint_instance_id", nullable = false, length = 100)
    private String contactpointInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "facility_instance_id", referencedColumnName = "instance_id")
    private Facility facilityByFacilityInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "contactpoint_instance_id", referencedColumnName = "instance_id")
    private Contactpoint contactpointByContactpointInstanceId;

    public String getFacilityInstanceId() {
        return facilityInstanceId;
    }

    public void setFacilityInstanceId(String facilityInstanceId) {
        this.facilityInstanceId = facilityInstanceId;
    }

    public String getContactpointInstanceId() {
        return contactpointInstanceId;
    }

    public void setContactpointInstanceId(String contactpointInstanceId) {
        this.contactpointInstanceId = contactpointInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FacilityContactpoint that = (FacilityContactpoint) o;

        if (facilityInstanceId != null ? !facilityInstanceId.equals(that.facilityInstanceId) : that.facilityInstanceId != null)
            return false;
        if (contactpointInstanceId != null ? !contactpointInstanceId.equals(that.contactpointInstanceId) : that.contactpointInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = facilityInstanceId != null ? facilityInstanceId.hashCode() : 0;
        result = 31 * result + (contactpointInstanceId != null ? contactpointInstanceId.hashCode() : 0);
        return result;
    }

    public Facility getFacilityByFacilityInstanceId() {
        return facilityByFacilityInstanceId;
    }

    public void setFacilityByFacilityInstanceId(Facility facilityByFacilityInstanceId) {
        this.facilityByFacilityInstanceId = facilityByFacilityInstanceId;
    }

    public Contactpoint getContactpointByContactpointInstanceId() {
        return contactpointByContactpointInstanceId;
    }

    public void setContactpointByContactpointInstanceId(Contactpoint contactpointByContactpointInstanceId) {
        this.contactpointByContactpointInstanceId = contactpointByContactpointInstanceId;
    }
}
