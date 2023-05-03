package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "person_telephone")
public class EDMPersonTelephone {
    private String id;
    private String telnumber;
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
    @Column(name = "telnumber")
    public String getTelnumber() {
        return telnumber;
    }

    public void setTelnumber(String telnumber) {
        this.telnumber = telnumber;
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

        EDMPersonTelephone that = (EDMPersonTelephone) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (telnumber != null ? !telnumber.equals(that.telnumber) : that.telnumber != null) return false;
        return instancePersonId != null ? instancePersonId.equals(that.instancePersonId) : that.instancePersonId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (telnumber != null ? telnumber.hashCode() : 0);
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
