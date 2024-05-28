package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class FacilityContactpointPK implements Serializable {
    @Column(name = "facility_instance_id", nullable = false, length = 100)
    @Id
    private String facilityInstanceId;
    @Column(name = "contactpoint_instance_id", nullable = false, length = 100)
    @Id
    private String contactpointInstanceId;

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

        FacilityContactpointPK that = (FacilityContactpointPK) o;

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
}
