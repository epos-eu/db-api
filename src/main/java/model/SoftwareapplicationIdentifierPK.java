package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class SoftwareapplicationIdentifierPK implements Serializable {
    @Column(name = "softwareapplication_instance_id", nullable = false, length = 100)
    @Id
    private String softwareapplicationInstanceId;
    @Column(name = "identifier_instance_id", nullable = false, length = 100)
    @Id
    private String identifierInstanceId;

    public String getSoftwareapplicationInstanceId() {
        return softwareapplicationInstanceId;
    }

    public void setSoftwareapplicationInstanceId(String softwareapplicationInstanceId) {
        this.softwareapplicationInstanceId = softwareapplicationInstanceId;
    }

    public String getIdentifierInstanceId() {
        return identifierInstanceId;
    }

    public void setIdentifierInstanceId(String identifierInstanceId) {
        this.identifierInstanceId = identifierInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SoftwareapplicationIdentifierPK that = (SoftwareapplicationIdentifierPK) o;

        if (softwareapplicationInstanceId != null ? !softwareapplicationInstanceId.equals(that.softwareapplicationInstanceId) : that.softwareapplicationInstanceId != null)
            return false;
        if (identifierInstanceId != null ? !identifierInstanceId.equals(that.identifierInstanceId) : that.identifierInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = softwareapplicationInstanceId != null ? softwareapplicationInstanceId.hashCode() : 0;
        result = 31 * result + (identifierInstanceId != null ? identifierInstanceId.hashCode() : 0);
        return result;
    }
}
