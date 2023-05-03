package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "softwaresourcecode_identifier")
@NamedQueries({
        @NamedQuery(name = "softwaresourcecode_identifier.findAll", query = "SELECT c FROM EDMSoftwaresourcecodeIdentifier c"),
        @NamedQuery(name = "softwaresourcecode_identifier.findByTypeAndByIdentifier", query = "select c from EDMSoftwaresourcecodeIdentifier c where c.type = :TYPE and c.identifier = :IDENTIFIER")
})
public class EDMSoftwaresourcecodeIdentifier {
    private String id;
    private String type;
    private String identifier;
    private String instanceSoftwaresourcecodeId;
    private EDMSoftwaresourcecode softwaresourcecodeByInstanceSoftwaresourcecodeId;

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
    @Column(name = "instance_softwaresourcecode_id", insertable = false, updatable = false)
    public String getInstanceSoftwaresourcecodeId() {
        return instanceSoftwaresourcecodeId;
    }

    public void setInstanceSoftwaresourcecodeId(String instanceSoftwaresourcecodeId) {
        this.instanceSoftwaresourcecodeId = instanceSoftwaresourcecodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EDMSoftwaresourcecodeIdentifier that = (EDMSoftwaresourcecodeIdentifier) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getType(), that.getType()) && Objects.equals(getIdentifier(), that.getIdentifier()) && Objects.equals(getInstanceSoftwaresourcecodeId(), that.getInstanceSoftwaresourcecodeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getIdentifier());
    }

    @ManyToOne
    @JoinColumn(name = "instance_softwaresourcecode_id", referencedColumnName = "instance_id", nullable = false)
    public EDMSoftwaresourcecode getSoftwaresourcecodeByInstanceSoftwaresourcecodeId() {
        return softwaresourcecodeByInstanceSoftwaresourcecodeId;
    }

    public void setSoftwaresourcecodeByInstanceSoftwaresourcecodeId(EDMSoftwaresourcecode softwaresourcecodeByInstanceSoftwaresourcecodeId) {
        this.softwaresourcecodeByInstanceSoftwaresourcecodeId = softwaresourcecodeByInstanceSoftwaresourcecodeId;
    }
}
