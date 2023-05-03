package org.epos.handler.dbapi.model;

import org.eclipse.persistence.annotations.ReadOnly;

import javax.persistence.*;
import java.util.Objects;

@Entity
@MappedSuperclass
@Table(name = "person")
@ReadOnly
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQueries({
        @NamedQuery(name = "person.findAll", query = "SELECT c FROM EDMPerson c"),
        @NamedQuery(name = "person.findAllByMetaId", query = "SELECT c FROM EDMPerson c where c.metaId = :METAID"),
        @NamedQuery(name = "person.findAllByState", query = "SELECT c FROM EDMPerson c where c.state = :STATE"),
        @NamedQuery(name = "person.findByUidAndState", query = "select c from EDMPerson c where c.uid = :UID and c.state = :STATE"),
        @NamedQuery(name = "person.findByUid", query = "select c from EDMPerson c where c.uid = :UID"),
        @NamedQuery(name = "person.findByInstanceId", query = "select c from EDMPerson c where c.instanceId = :INSTANCEID"),
        @NamedQuery(name = "person.findByAuthId", query = "select c from EDMPerson c where c.authIdentifier = :AUTHID")
})
public class EDMPersonLite {
    private String uid;
    private String instanceId;
    private String metaId;
    private String state;
    private EDMEdmEntityId edmEntityIdByMetaId;

    @Basic
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Id
    @Column(name = "instance_id")
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Basic
    @Column(name = "meta_id", insertable = false, updatable = false)
    public String getMetaId() {
        return metaId;
    }

    public void setMetaId(String metaId) {
        this.metaId = metaId;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @ManyToOne
    @JoinColumn(name = "meta_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByMetaId() {
        return edmEntityIdByMetaId;
    }

    public void setEdmEntityIdByMetaId(EDMEdmEntityId edmEntityIdByMetaId) {
        this.edmEntityIdByMetaId = edmEntityIdByMetaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EDMPersonLite that = (EDMPersonLite) o;
        return Objects.equals(getUid(), that.getUid()) && Objects.equals(getInstanceId(), that.getInstanceId()) && Objects.equals(getMetaId(), that.getMetaId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid(), getInstanceId(), getMetaId());
    }
}
