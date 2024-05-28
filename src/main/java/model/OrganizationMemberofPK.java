package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class OrganizationMemberofPK implements Serializable {
    @Column(name = "organization1_instance_id", nullable = false, length = 100)
    @Id
    private String organization1InstanceId;
    @Column(name = "organization2_instance_id", nullable = false, length = 100)
    @Id
    private String organization2InstanceId;

    public String getOrganization1InstanceId() {
        return organization1InstanceId;
    }

    public void setOrganization1InstanceId(String organization1InstanceId) {
        this.organization1InstanceId = organization1InstanceId;
    }

    public String getOrganization2InstanceId() {
        return organization2InstanceId;
    }

    public void setOrganization2InstanceId(String organization2InstanceId) {
        this.organization2InstanceId = organization2InstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationMemberofPK that = (OrganizationMemberofPK) o;

        if (organization1InstanceId != null ? !organization1InstanceId.equals(that.organization1InstanceId) : that.organization1InstanceId != null)
            return false;
        if (organization2InstanceId != null ? !organization2InstanceId.equals(that.organization2InstanceId) : that.organization2InstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = organization1InstanceId != null ? organization1InstanceId.hashCode() : 0;
        result = 31 * result + (organization2InstanceId != null ? organization2InstanceId.hashCode() : 0);
        return result;
    }
}
