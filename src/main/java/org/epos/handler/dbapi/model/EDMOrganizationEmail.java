package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "organization_email")
public class EDMOrganizationEmail {
    private String id;
    private String email;
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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        EDMOrganizationEmail that = (EDMOrganizationEmail) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getInstanceOrganizationId(), that.getInstanceOrganizationId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getInstanceOrganizationId());
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
