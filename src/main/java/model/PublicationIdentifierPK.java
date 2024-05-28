package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class PublicationIdentifierPK implements Serializable {
    @Column(name = "publication_instance_id", nullable = false, length = 100)
    @Id
    private String publicationInstanceId;
    @Column(name = "identifier_instance_id", nullable = false, length = 100)
    @Id
    private String identifierInstanceId;

    public String getPublicationInstanceId() {
        return publicationInstanceId;
    }

    public void setPublicationInstanceId(String publicationInstanceId) {
        this.publicationInstanceId = publicationInstanceId;
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

        PublicationIdentifierPK that = (PublicationIdentifierPK) o;

        if (publicationInstanceId != null ? !publicationInstanceId.equals(that.publicationInstanceId) : that.publicationInstanceId != null)
            return false;
        if (identifierInstanceId != null ? !identifierInstanceId.equals(that.identifierInstanceId) : that.identifierInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = publicationInstanceId != null ? publicationInstanceId.hashCode() : 0;
        result = 31 * result + (identifierInstanceId != null ? identifierInstanceId.hashCode() : 0);
        return result;
    }
}
