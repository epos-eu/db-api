package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "softwareapplication_identifier")
@NamedQueries({
        @NamedQuery(name = "softwareapplication_identifier.findAll", query = "SELECT c FROM EDMSoftwareapplicationIdentifier c"),
        @NamedQuery(name = "softwareapplication_identifier.findByTypeAndByIdentifier", query = "select c from EDMSoftwareapplicationIdentifier c where c.type = :TYPE and c.identifier = :IDENTIFIER")
})
public class EDMSoftwareapplicationIdentifier {
    private String id;
    private String type;
    private String identifier;
    private String instanceSoftwareapplicationId;
    private EDMSoftwareapplication softwareapplicationByInstanceSoftwareapplicationId;

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
    @Column(name = "instance_softwareapplication_id", insertable = false, updatable = false)
    public String getInstanceSoftwareapplicationId() {
        return instanceSoftwareapplicationId;
    }

    public void setInstanceSoftwareapplicationId(String instanceSoftwareapplicationId) {
        this.instanceSoftwareapplicationId = instanceSoftwareapplicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EDMSoftwareapplicationIdentifier that = (EDMSoftwareapplicationIdentifier) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getType(), that.getType()) && Objects.equals(getIdentifier(), that.getIdentifier()) && Objects.equals(getInstanceSoftwareapplicationId(), that.getInstanceSoftwareapplicationId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getIdentifier());
    }

    @ManyToOne
    @JoinColumn(name = "instance_softwareapplication_id", referencedColumnName = "instance_id", nullable = false)
    public EDMSoftwareapplication getSoftwareapplicationByInstanceSoftwareapplicationId() {
        return softwareapplicationByInstanceSoftwareapplicationId;
    }

    public void setSoftwareapplicationByInstanceSoftwareapplicationId(EDMSoftwareapplication softwareapplicationByInstanceSoftwareapplicationId) {
        this.softwareapplicationByInstanceSoftwareapplicationId = softwareapplicationByInstanceSoftwareapplicationId;
    }
}
