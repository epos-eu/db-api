package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "contactpoint_organization")
@IdClass(EDMContactpointOrganizationPK.class)
public class EDMContactpointOrganization {
    private String instanceOrganizationId;
    private String instanceContactpointId;
    private EDMOrganization organizationByInstanceOrganizationId;
    private EDMContactpoint contactpointByInstanceContactpointId;

    @Id
    @Column(name = "instance_organization_id", insertable = false, updatable = false)
    public String getInstanceOrganizationId() {
        return instanceOrganizationId;
    }

    public void setInstanceOrganizationId(String instanceOrganizationId) {
        this.instanceOrganizationId = instanceOrganizationId;
    }

    @Id
    @Column(name = "instance_contactpoint_id", insertable = false, updatable = false)
    public String getInstanceContactpointId() {
        return instanceContactpointId;
    }

    public void setInstanceContactpointId(String metaContactpointId) {
        this.instanceContactpointId = metaContactpointId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMContactpointOrganization that = (EDMContactpointOrganization) o;

        if (instanceOrganizationId != null ? !instanceOrganizationId.equals(that.instanceOrganizationId) : that.instanceOrganizationId != null)
            return false;
        return instanceContactpointId != null ? instanceContactpointId.equals(that.instanceContactpointId) : that.instanceContactpointId == null;
    }

    @Override
    public int hashCode() {
        int result = instanceOrganizationId != null ? instanceOrganizationId.hashCode() : 0;
        result = 31 * result + (instanceContactpointId != null ? instanceContactpointId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_organization_id", referencedColumnName = "instance_id", nullable = false)
    public EDMOrganization getOrganizationByInstanceOrganizationId() {
        return organizationByInstanceOrganizationId;
    }

    public void setOrganizationByInstanceOrganizationId(EDMOrganization organizationByInstanceOrganizationId) {
        this.organizationByInstanceOrganizationId = organizationByInstanceOrganizationId;
    }

    @ManyToOne
    @JoinColumn(name = "instance_contactpoint_id", referencedColumnName = "instance_id", nullable = false)
    public EDMContactpoint getContactpointByInstanceContactpointId() {
        return contactpointByInstanceContactpointId;
    }

    public void setContactpointByInstanceContactpointId(EDMContactpoint contactpointByInstanceContactpointId) {
        this.contactpointByInstanceContactpointId = contactpointByInstanceContactpointId;
    }
}
