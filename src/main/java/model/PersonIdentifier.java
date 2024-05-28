package model;

import jakarta.persistence.*;

@Entity
@Table(name = "person_identifier", schema = "public", catalog = "cerif")
@IdClass(PersonIdentifierPK.class)
public class PersonIdentifier {
    @Id
    @Column(name = "person_instance_id", nullable = false, length = 100)
    private String personInstanceId;
    @Id
    @Column(name = "identifier_instance_id", nullable = false, length = 100)
    private String identifierInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "person_instance_id", referencedColumnName = "instance_id")
    private Person personByPersonInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "identifier_instance_id", referencedColumnName = "instance_id")
    private Identifier identifierByIdentifierInstanceId;

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

        PersonIdentifier that = (PersonIdentifier) o;

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

    public Person getPersonByPersonInstanceId() {
        return personByPersonInstanceId;
    }

    public void setPersonByPersonInstanceId(Person personByPersonInstanceId) {
        this.personByPersonInstanceId = personByPersonInstanceId;
    }

    public Identifier getIdentifierByIdentifierInstanceId() {
        return identifierByIdentifierInstanceId;
    }

    public void setIdentifierByIdentifierInstanceId(Identifier identifierByIdentifierInstanceId) {
        this.identifierByIdentifierInstanceId = identifierByIdentifierInstanceId;
    }
}
