package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "organization_legalname")
public class EDMOrganizationLegalname {
    private String id;
    private String legalname;
    private String language;
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
    @Column(name = "legalname")
    public String getLegalname() {
        return legalname;
    }

    public void setLegalname(String legalname) {
        this.legalname = legalname;
    }

    @Basic
    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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
        EDMOrganizationLegalname that = (EDMOrganizationLegalname) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getLegalname(), that.getLegalname()) && Objects.equals(getLanguage(), that.getLanguage()) && Objects.equals(getInstanceOrganizationId(), that.getInstanceOrganizationId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLegalname(), getLanguage(), getInstanceOrganizationId());
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
