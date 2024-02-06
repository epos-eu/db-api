package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "organization_owner")
@IdClass(EDMOrganizationOwnerPK.class)
public class EDMOrganizationOwner {
    private String instanceOrganizationId;
    private String entityMetaId;
    private EDMOrganization organizationByInstanceOrganizationId;
    private EDMEdmEntityId edmEntityIdByMetaEntityId;

    @Id
    @Column(name = "instance_organization_id", insertable = false, updatable = false)
    public String getInstanceOrganizationId() {
        return instanceOrganizationId;
    }

    public void setInstanceOrganizationId(String instanceOrganizationId) {
        this.instanceOrganizationId = instanceOrganizationId;
    }

    @Id
    @Column(name = "meta_id", insertable = false, updatable = false)
    public String getEntityMetaId() {
        return entityMetaId;
    }

    public void setEntityMetaId(String entityMetaId) {
        this.entityMetaId = entityMetaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMOrganizationOwner that = (EDMOrganizationOwner) o;

        if (instanceOrganizationId != null ? !instanceOrganizationId.equals(that.instanceOrganizationId) : that.instanceOrganizationId != null)
            return false;
        return entityMetaId != null ? entityMetaId.equals(that.entityMetaId) : that.entityMetaId == null;
    }

    @Override
    public int hashCode() {
        int result = instanceOrganizationId != null ? instanceOrganizationId.hashCode() : 0;
        result = 31 * result + (entityMetaId != null ? entityMetaId.hashCode() : 0);
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
    @JoinColumn(name = "meta_id", referencedColumnName = "meta_id", nullable = false)
    public EDMEdmEntityId getEdmEntityIdByMetaEntityId() {
        return edmEntityIdByMetaEntityId;
    }

    public void setEdmEntityIdByMetaEntityId(EDMEdmEntityId edmEntityIdByMetaEntityId) {
        this.edmEntityIdByMetaEntityId = edmEntityIdByMetaEntityId;
    }
}
