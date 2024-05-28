package model;

import jakarta.persistence.*;

@Entity
@Table(name = "person_element", schema = "public", catalog = "cerif")
@IdClass(PersonElementPK.class)
public class PersonElement {
    @Id
    @Column(name = "person_instance_id", nullable = false, length = 100)
    private String personInstanceId;
    @Id
    @Column(name = "element_instance_id", nullable = false, length = 100)
    private String elementInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "person_instance_id", referencedColumnName = "instance_id")
    private Person personByPersonInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "element_instance_id", referencedColumnName = "instance_id")
    private Element elementByElementInstanceId;

    public String getPersonInstanceId() {
        return personInstanceId;
    }

    public void setPersonInstanceId(String personInstanceId) {
        this.personInstanceId = personInstanceId;
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

        PersonElement that = (PersonElement) o;

        if (personInstanceId != null ? !personInstanceId.equals(that.personInstanceId) : that.personInstanceId != null)
            return false;
        if (elementInstanceId != null ? !elementInstanceId.equals(that.elementInstanceId) : that.elementInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personInstanceId != null ? personInstanceId.hashCode() : 0;
        result = 31 * result + (elementInstanceId != null ? elementInstanceId.hashCode() : 0);
        return result;
    }

    public Person getPersonByPersonInstanceId() {
        return personByPersonInstanceId;
    }

    public void setPersonByPersonInstanceId(Person personByPersonInstanceId) {
        this.personByPersonInstanceId = personByPersonInstanceId;
    }

    public Element getElementByElementInstanceId() {
        return elementByElementInstanceId;
    }

    public void setElementByElementInstanceId(Element elementByElementInstanceId) {
        this.elementByElementInstanceId = elementByElementInstanceId;
    }
}
