package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class PersonIdentifierPK implements Serializable {
    @Column(name = "person_instance_id", nullable = false, length = 100)
    @Id
    private String personInstanceId;
    @Column(name = "identifier_instance_id", nullable = false, length = 100)
    @Id
    private String identifierInstanceId;

    public String getPersonInstanceId() {
        return personInstanceId;
    }

    public void setPersonInstanceId(String personInstanceId) {
        this.personInstanceId = personInstanceId;
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

        PersonIdentifierPK that = (PersonIdentifierPK) o;

        if (personInstanceId != null ? !personInstanceId.equals(that.personInstanceId) : that.personInstanceId != null)
            return false;
        if (identifierInstanceId != null ? !identifierInstanceId.equals(that.identifierInstanceId) : that.identifierInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personInstanceId != null ? personInstanceId.hashCode() : 0;
        result = 31 * result + (identifierInstanceId != null ? identifierInstanceId.hashCode() : 0);
        return result;
    }
}
