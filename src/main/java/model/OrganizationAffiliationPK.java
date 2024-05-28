package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class OrganizationAffiliationPK implements Serializable {
    @Column(name = "person_instance_id", nullable = false, length = 100)
    @Id
    private String personInstanceId;
    @Column(name = "organization_instance_id", nullable = false, length = 100)
    @Id
    private String organizationInstanceId;

    public String getPersonInstanceId() {
        return personInstanceId;
    }

    public void setPersonInstanceId(String personInstanceId) {
        this.personInstanceId = personInstanceId;
    }

    public String getOrganizationInstanceId() {
        return organizationInstanceId;
    }

    public void setOrganizationInstanceId(String organizationInstanceId) {
        this.organizationInstanceId = organizationInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationAffiliationPK that = (OrganizationAffiliationPK) o;

        if (personInstanceId != null ? !personInstanceId.equals(that.personInstanceId) : that.personInstanceId != null)
            return false;
        if (organizationInstanceId != null ? !organizationInstanceId.equals(that.organizationInstanceId) : that.organizationInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personInstanceId != null ? personInstanceId.hashCode() : 0;
        result = 31 * result + (organizationInstanceId != null ? organizationInstanceId.hashCode() : 0);
        return result;
    }
}
