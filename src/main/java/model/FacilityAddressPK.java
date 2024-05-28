package model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import java.io.Serializable;

public class FacilityAddressPK implements Serializable {
    @Column(name = "facility_instance_id", nullable = false, length = 100)
    @Id
    private String facilityInstanceId;
    @Column(name = "address_instance_id", nullable = false, length = 100)
    @Id
    private String addressInstanceId;

    public String getFacilityInstanceId() {
        return facilityInstanceId;
    }

    public void setFacilityInstanceId(String facilityInstanceId) {
        this.facilityInstanceId = facilityInstanceId;
    }

    public String getAddressInstanceId() {
        return addressInstanceId;
    }

    public void setAddressInstanceId(String addressInstanceId) {
        this.addressInstanceId = addressInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FacilityAddressPK that = (FacilityAddressPK) o;

        if (facilityInstanceId != null ? !facilityInstanceId.equals(that.facilityInstanceId) : that.facilityInstanceId != null)
            return false;
        if (addressInstanceId != null ? !addressInstanceId.equals(that.addressInstanceId) : that.addressInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = facilityInstanceId != null ? facilityInstanceId.hashCode() : 0;
        result = 31 * result + (addressInstanceId != null ? addressInstanceId.hashCode() : 0);
        return result;
    }
}
