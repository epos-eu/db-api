package model;

import jakarta.persistence.*;

@Entity
@Table(name = "organization_contactpoint", schema = "public", catalog = "cerif")
@IdClass(OrganizationContactpointPK.class)
public class OrganizationContactpoint {
    @Id
    @Column(name = "organization_instance_id", nullable = false, length = 100)
    private String organizationInstanceId;
    @Id
    @Column(name = "contactpoint_instance_id", nullable = false, length = 100)
    private String contactpointInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "organization_instance_id", referencedColumnName = "instance_id")
    private Organization organizationByOrganizationInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "contactpoint_instance_id", referencedColumnName = "instance_id")
    private Contactpoint contactpointByContactpointInstanceId;

    public String getOrganizationInstanceId() {
        return organizationInstanceId;
    }

    public void setOrganizationInstanceId(String organizationInstanceId) {
        this.organizationInstanceId = organizationInstanceId;
    }

    public String getContactpointInstanceId() {
        return contactpointInstanceId;
    }

    public void setContactpointInstanceId(String contactpointInstanceId) {
        this.contactpointInstanceId = contactpointInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationContactpoint that = (OrganizationContactpoint) o;

        if (organizationInstanceId != null ? !organizationInstanceId.equals(that.organizationInstanceId) : that.organizationInstanceId != null)
            return false;
        if (contactpointInstanceId != null ? !contactpointInstanceId.equals(that.contactpointInstanceId) : that.contactpointInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = organizationInstanceId != null ? organizationInstanceId.hashCode() : 0;
        result = 31 * result + (contactpointInstanceId != null ? contactpointInstanceId.hashCode() : 0);
        return result;
    }

    public Organization getOrganizationByOrganizationInstanceId() {
        return organizationByOrganizationInstanceId;
    }

    public void setOrganizationByOrganizationInstanceId(Organization organizationByOrganizationInstanceId) {
        this.organizationByOrganizationInstanceId = organizationByOrganizationInstanceId;
    }

    public Contactpoint getContactpointByContactpointInstanceId() {
        return contactpointByContactpointInstanceId;
    }

    public void setContactpointByContactpointInstanceId(Contactpoint contactpointByContactpointInstanceId) {
        this.contactpointByContactpointInstanceId = contactpointByContactpointInstanceId;
    }
}
