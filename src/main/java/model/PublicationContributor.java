package model;

import jakarta.persistence.*;

@Entity
@Table(name = "publication_contributor", schema = "public", catalog = "cerif")
@IdClass(PublicationContributorPK.class)
public class PublicationContributor {
    @Id
    @Column(name = "person_instance_id", nullable = false, length = 100)
    private String personInstanceId;
    @Id
    @Column(name = "publication_instance_id", nullable = false, length = 100)
    private String publicationInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "person_instance_id", referencedColumnName = "instance_id")
    private Person personByPersonInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "publication_instance_id", referencedColumnName = "instance_id")
    private Publication publicationByPublicationInstanceId;

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

        PublicationContributor that = (PublicationContributor) o;

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

    public Person getPersonByPersonInstanceId() {
        return personByPersonInstanceId;
    }

    public void setPersonByPersonInstanceId(Person personByPersonInstanceId) {
        this.personByPersonInstanceId = personByPersonInstanceId;
    }

    public Publication getPublicationByPublicationInstanceId() {
        return publicationByPublicationInstanceId;
    }

    public void setPublicationByPublicationInstanceId(Publication publicationByPublicationInstanceId) {
        this.publicationByPublicationInstanceId = publicationByPublicationInstanceId;
    }
}
