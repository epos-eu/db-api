package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class OrganizationElementPK implements Serializable {
    @Column(name = "organization_instance_id", nullable = false, length = 100)
    @Id
    private String organizationInstanceId;
    @Column(name = "element_instance_id", nullable = false, length = 100)
    @Id
    private String elementInstanceId;

    public String getOrganizationInstanceId() {
        return organizationInstanceId;
    }

    public void setOrganizationInstanceId(String organizationInstanceId) {
        this.organizationInstanceId = organizationInstanceId;
    }

    public String getElementInstanceId() {
        return elementInstanceId;
    }

    public void setElementInstanceId(String elementInstanceId) {
        this.elementInstanceId = elementInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationElementPK that = (OrganizationElementPK) o;

        if (organizationInstanceId != null ? !organizationInstanceId.equals(that.organizationInstanceId) : that.organizationInstanceId != null)
            return false;
        if (elementInstanceId != null ? !elementInstanceId.equals(that.elementInstanceId) : that.elementInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = organizationInstanceId != null ? organizationInstanceId.hashCode() : 0;
        result = 31 * result + (elementInstanceId != null ? elementInstanceId.hashCode() : 0);
        return result;
    }
}
