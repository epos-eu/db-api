package model;

import jakarta.persistence.*;

@Entity
@Table(name = "organization_identifier", schema = "public", catalog = "cerif")
@IdClass(OrganizationIdentifierPK.class)
public class OrganizationIdentifier {
    @Id
    @Column(name = "organization_instance_id", nullable = false, length = 100)
    private String organizationInstanceId;
    @Id
    @Column(name = "identifier_instance_id", nullable = false, length = 100)
    private String identifierInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "organization_instance_id", referencedColumnName = "instance_id")
    private Organization organizationByOrganizationInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "identifier_instance_id", referencedColumnName = "instance_id")
    private Identifier identifierByIdentifierInstanceId;

    public String getOrganizationInstanceId() {
        return organizationInstanceId;
    }

    public void setOrganizationInstanceId(String organizationInstanceId) {
        this.organizationInstanceId = organizationInstanceId;
    }

    public String getIdentifierInstanceId() {
        return identifierInstanceId;
    }

    public void setIdentifierInstanceId(String identifierInstanceId) {
        this.identifierInstanceId = identifierInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationIdentifier that = (OrganizationIdentifier) o;

        if (organizationInstanceId != null ? !organizationInstanceId.equals(that.organizationInstanceId) : that.organizationInstanceId != null)
            return false;
        if (identifierInstanceId != null ? !identifierInstanceId.equals(that.identifierInstanceId) : that.identifierInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = organizationInstanceId != null ? organizationInstanceId.hashCode() : 0;
        result = 31 * result + (identifierInstanceId != null ? identifierInstanceId.hashCode() : 0);
        return result;
    }

    public Organization getOrganizationByOrganizationInstanceId() {
        return organizationByOrganizationInstanceId;
    }

    public void setOrganizationByOrganizationInstanceId(Organization organizationByOrganizationInstanceId) {
        this.organizationByOrganizationInstanceId = organizationByOrganizationInstanceId;
    }

    public Identifier getIdentifierByIdentifierInstanceId() {
        return identifierByIdentifierInstanceId;
    }

    public void setIdentifierByIdentifierInstanceId(Identifier identifierByIdentifierInstanceId) {
        this.identifierByIdentifierInstanceId = identifierByIdentifierInstanceId;
    }
}
