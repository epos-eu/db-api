package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class SoftwareapplicationContactpointPK implements Serializable {
    @Column(name = "softwareapplication_instance_id", nullable = false, length = 100)
    @Id
    private String softwareapplicationInstanceId;
    @Column(name = "contactpoint_instance_id", nullable = false, length = 100)
    @Id
    private String contactpointInstanceId;

    public String getSoftwareapplicationInstanceId() {
        return softwareapplicationInstanceId;
    }

    public void setSoftwareapplicationInstanceId(String softwareapplicationInstanceId) {
        this.softwareapplicationInstanceId = softwareapplicationInstanceId;
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

        SoftwareapplicationContactpointPK that = (SoftwareapplicationContactpointPK) o;

        if (softwareapplicationInstanceId != null ? !softwareapplicationInstanceId.equals(that.softwareapplicationInstanceId) : that.softwareapplicationInstanceId != null)
            return false;
        if (contactpointInstanceId != null ? !contactpointInstanceId.equals(that.contactpointInstanceId) : that.contactpointInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = softwareapplicationInstanceId != null ? softwareapplicationInstanceId.hashCode() : 0;
        result = 31 * result + (contactpointInstanceId != null ? contactpointInstanceId.hashCode() : 0);
        return result;
    }
}
