package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "organization_telephone")
public class EDMOrganizationTelephone {
    private String id;
    private String number;
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
    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
        EDMOrganizationTelephone that = (EDMOrganizationTelephone) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getNumber(), that.getNumber()) && Objects.equals(getInstanceOrganizationId(), that.getInstanceOrganizationId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumber(), getInstanceOrganizationId());
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
