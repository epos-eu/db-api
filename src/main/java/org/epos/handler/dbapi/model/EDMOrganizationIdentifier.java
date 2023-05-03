package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "organization_identifier")
@NamedQueries({
        @NamedQuery(name = "organization_identifier.findAll", query = "SELECT c FROM EDMOrganizationIdentifier c"),
        @NamedQuery(name = "organization_identifier.findByTypeAndByIdentifier", query = "select c from EDMOrganizationIdentifier c where c.type = :TYPE and c.identifier = :IDENTIFIER")
})
public class EDMOrganizationIdentifier {
    private String id;
    private String type;
    private String identifier;
    private String instanceOrganizationId;
    private EDMOrganization organizationIdByInstanceOrganizationId;

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
    @Column(name = "instance_organization_id", insertable = false, updatable = false)
    public String getInstanceOrganizationId() {
        return instanceOrganizationId;
    }

    public void setInstanceOrganizationId(String instanceOrganizationId) {
        this.instanceOrganizationId = instanceOrganizationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EDMOrganizationIdentifier that = (EDMOrganizationIdentifier) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getType(), that.getType()) && Objects.equals(getIdentifier(), that.getIdentifier()) && Objects.equals(getInstanceOrganizationId(), that.getInstanceOrganizationId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getIdentifier(), getInstanceOrganizationId());
    }

    @ManyToOne
    @JoinColumn(name = "instance_organization_id", referencedColumnName = "instance_id", nullable = false)
    public EDMOrganization getOrganizationIdByInstanceOrganizationId() {
        return organizationIdByInstanceOrganizationId;
    }

    public void setOrganizationIdByInstanceOrganizationId(EDMOrganization organizationIdByInstanceOrganizationId) {
        this.organizationIdByInstanceOrganizationId = organizationIdByInstanceOrganizationId;
    }
}
