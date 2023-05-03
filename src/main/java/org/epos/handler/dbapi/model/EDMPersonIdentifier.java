package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "person_identifier")
@NamedQueries({
        @NamedQuery(name = "person_identifier.findAll", query = "SELECT c FROM EDMPersonIdentifier c"),
        @NamedQuery(name = "person_identifier.findByTypeAndByIdentifier", query = "select c from EDMPersonIdentifier c where c.type = :TYPE and c.identifier = :IDENTIFIER")
})
public class EDMPersonIdentifier {
    private String id;
    private String type;
    private String identifier;
    private String instancePersonId;
    private EDMPerson personByInstancePersonId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "identifier")
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Basic
    @Column(name = "instance_person_id", insertable = false, updatable = false)
    public String getInstancePersonId() {
        return instancePersonId;
    }

    public void setInstancePersonId(String instancePersonId) {
        this.instancePersonId = instancePersonId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EDMPersonIdentifier that = (EDMPersonIdentifier) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getType(), that.getType()) && Objects.equals(getIdentifier(), that.getIdentifier()) && Objects.equals(getInstancePersonId(), that.getInstancePersonId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getIdentifier());
    }

    @ManyToOne
    @JoinColumn(name = "instance_person_id", referencedColumnName = "instance_id", nullable = false)
    public EDMPerson getPersonByInstancePersonId() {
        return personByInstancePersonId;
    }

    public void setPersonByInstancePersonId(EDMPerson personByInstancePersonId) {
        this.personByInstancePersonId = personByInstancePersonId;
    }
}
