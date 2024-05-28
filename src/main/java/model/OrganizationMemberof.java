package model;

import jakarta.persistence.*;

@Entity
@Table(name = "organization_memberof", schema = "public", catalog = "cerif")
@IdClass(OrganizationMemberofPK.class)
public class OrganizationMemberof {
    @Id
    @Column(name = "organization1_instance_id", nullable = false, length = 100)
    private String organization1InstanceId;
    @Id
    @Column(name = "organization2_instance_id", nullable = false, length = 100)
    private String organization2InstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "organization1_instance_id", referencedColumnName = "instance_id")
    private Organization organizationByOrganization1InstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "organization2_instance_id", referencedColumnName = "instance_id")
    private Organization organizationByOrganization2InstanceId;

    public String getOrganization1InstanceId() {
        return organization1InstanceId;
    }

    public void setOrganization1InstanceId(String organization1InstanceId) {
        this.organization1InstanceId = organization1InstanceId;
    }

    public String getOrganization2InstanceId() {
        return organization2InstanceId;
    }

    public void setOrganization2InstanceId(String organization2InstanceId) {
        this.organization2InstanceId = organization2InstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationMemberof that = (OrganizationMemberof) o;

        if (organization1InstanceId != null ? !organization1InstanceId.equals(that.organization1InstanceId) : that.organization1InstanceId != null)
            return false;
        if (organization2InstanceId != null ? !organization2InstanceId.equals(that.organization2InstanceId) : that.organization2InstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = organization1InstanceId != null ? organization1InstanceId.hashCode() : 0;
        result = 31 * result + (organization2InstanceId != null ? organization2InstanceId.hashCode() : 0);
        return result;
    }

    public Organization getOrganizationByOrganization1InstanceId() {
        return organizationByOrganization1InstanceId;
    }

    public void setOrganizationByOrganization1InstanceId(Organization organizationByOrganization1InstanceId) {
        this.organizationByOrganization1InstanceId = organizationByOrganization1InstanceId;
    }

    public Organization getOrganizationByOrganization2InstanceId() {
        return organizationByOrganization2InstanceId;
    }

    public void setOrganizationByOrganization2InstanceId(Organization organizationByOrganization2InstanceId) {
        this.organizationByOrganization2InstanceId = organizationByOrganization2InstanceId;
    }
}
