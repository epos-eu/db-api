package model;

import jakarta.persistence.*;

@Entity
@Table(name = "publication_identifier", schema = "public", catalog = "cerif")
@IdClass(PublicationIdentifierPK.class)
public class PublicationIdentifier {
    @Id
    @Column(name = "publication_instance_id", nullable = false, length = 100)
    private String publicationInstanceId;

    @Id
    @Column(name = "identifier_instance_id", nullable = false, length = 100)
    private String identifierInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "publication_instance_id", referencedColumnName = "instance_id")
    private Publication publicationByPublicationInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "identifier_instance_id", referencedColumnName = "instance_id")
    private Identifier identifierByIdentifierInstanceId;

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

        PublicationIdentifier that = (PublicationIdentifier) o;

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

    public Publication getPublicationByPublicationInstanceId() {
        return publicationByPublicationInstanceId;
    }

    public void setPublicationByPublicationInstanceId(Publication publicationByPublicationInstanceId) {
        this.publicationByPublicationInstanceId = publicationByPublicationInstanceId;
    }

    public Identifier getIdentifierByIdentifierInstanceId() {
        return identifierByIdentifierInstanceId;
    }

    public void setIdentifierByIdentifierInstanceId(Identifier identifierByIdentifierInstanceId) {
        this.identifierByIdentifierInstanceId = identifierByIdentifierInstanceId;
    }
}
