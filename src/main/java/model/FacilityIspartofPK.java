package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class FacilityIspartofPK implements Serializable {
    @Column(name = "facility1_instance_id", nullable = false, length = 100)
    @Id
    private String facility1InstanceId;
    @Column(name = "facility2_instance_id", nullable = false, length = 100)
    @Id
    private String facility2InstanceId;

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

        FacilityIspartofPK that = (FacilityIspartofPK) o;

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
}
