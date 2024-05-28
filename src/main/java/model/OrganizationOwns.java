package model;

import jakarta.persistence.*;

@Entity
@Table(name = "organization_owns", schema = "public", catalog = "cerif")
public class OrganizationOwns {
    @Id
    @Column(name = "organization_instance_id", nullable = false, length = 100)
    private String organizationInstanceId;
    @Basic
    @Column(name = "entity_instance_id", nullable = false, length = 100)
    private String entityInstanceId;
    @Basic
    @Column(name = "resource_entity", nullable = false, length = 100)
    private String resourceEntity;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "organization_instance_id", referencedColumnName = "instance_id")
    private Organization organizationByOrganizationInstanceId;

    public String getOrganizationInstanceId() {
        return organizationInstanceId;
    }

    public void setOrganizationInstanceId(String organizationInstanceId) {
        this.organizationInstanceId = organizationInstanceId;
    }

    public String getEntityInstanceId() {
        return entityInstanceId;
    }

    public void setEntityInstanceId(String entityInstanceId) {
        this.entityInstanceId = entityInstanceId;
    }

    public String getResourceEntity() {
        return resourceEntity;
    }

    public void setResourceEntity(String resourceEntity) {
        this.resourceEntity = resourceEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationOwns that = (OrganizationOwns) o;

        if (organizationInstanceId != null ? !organizationInstanceId.equals(that.organizationInstanceId) : that.organizationInstanceId != null)
            return false;
        if (entityInstanceId != null ? !entityInstanceId.equals(that.entityInstanceId) : that.entityInstanceId != null)
            return false;
        if (resourceEntity != null ? !resourceEntity.equals(that.resourceEntity) : that.resourceEntity != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = organizationInstanceId != null ? organizationInstanceId.hashCode() : 0;
        result = 31 * result + (entityInstanceId != null ? entityInstanceId.hashCode() : 0);
        result = 31 * result + (resourceEntity != null ? resourceEntity.hashCode() : 0);
        return result;
    }

    public Organization getOrganizationByOrganizationInstanceId() {
        return organizationByOrganizationInstanceId;
    }

    public void setOrganizationByOrganizationInstanceId(Organization organizationByOrganizationInstanceId) {
        this.organizationByOrganizationInstanceId = organizationByOrganizationInstanceId;
    }
}
