package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "person_email")
public class EDMPersonEmail {
    private String id;
    private String email;
    private String instancePersonId;
    private EDMPerson personIdByInstancePersonId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

        EDMPersonEmail that = (EDMPersonEmail) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return instancePersonId != null ? instancePersonId.equals(that.instancePersonId) : that.instancePersonId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (instancePersonId != null ? instancePersonId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_person_id", referencedColumnName = "instance_id", nullable = false)
    public EDMPerson getPersonIdByInstancePersonId() {
        return personIdByInstancePersonId;
    }

    public void setPersonIdByInstancePersonId(EDMPerson personIdByInstancePersonId) {
        this.personIdByInstancePersonId = personIdByInstancePersonId;
    }
}
