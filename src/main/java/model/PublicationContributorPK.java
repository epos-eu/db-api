package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class PublicationContributorPK implements Serializable {
    @Column(name = "person_instance_id", nullable = false, length = 100)
    @Id
    private String personInstanceId;
    @Column(name = "publication_instance_id", nullable = false, length = 100)
    @Id
    private String publicationInstanceId;

    public String getPersonInstanceId() {
        return personInstanceId;
    }

    public void setPersonInstanceId(String personInstanceId) {
        this.personInstanceId = personInstanceId;
    }

    public String getPublicationInstanceId() {
        return publicationInstanceId;
    }

    public void setPublicationInstanceId(String publicationInstanceId) {
        this.publicationInstanceId = publicationInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicationContributorPK that = (PublicationContributorPK) o;

        if (personInstanceId != null ? !personInstanceId.equals(that.personInstanceId) : that.personInstanceId != null)
            return false;
        if (publicationInstanceId != null ? !publicationInstanceId.equals(that.publicationInstanceId) : that.publicationInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personInstanceId != null ? personInstanceId.hashCode() : 0;
        result = 31 * result + (publicationInstanceId != null ? publicationInstanceId.hashCode() : 0);
        return result;
    }
}
