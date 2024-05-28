package model;

import jakarta.persistence.*;

@Entity
@Table(name = "organization_affiliation", schema = "public", catalog = "cerif")
@IdClass(OrganizationAffiliationPK.class)
public class OrganizationAffiliation {
    @Id
    @Column(name = "person_instance_id", nullable = false, length = 100)
    private String personInstanceId;
    @Id
    @Column(name = "organization_instance_id", nullable = false, length = 100)
    private String organizationInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "person_instance_id", referencedColumnName = "instance_id")
    private Person personByPersonInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "organization_instance_id", referencedColumnName = "instance_id")
    private Organization organizationByOrganizationInstanceId;

    public String getPersonInstanceId() {
        return personInstanceId;
    }

    public void setPersonInstanceId(String personInstanceId) {
        this.personInstanceId = personInstanceId;
    }

    public String getOrganizationInstanceId() {
        return organizationInstanceId;
    }

    public void setOrganizationInstanceId(String organizationInstanceId) {
        this.organizationInstanceId = organizationInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationAffiliation that = (OrganizationAffiliation) o;

        if (personInstanceId != null ? !personInstanceId.equals(that.personInstanceId) : that.personInstanceId != null)
            return false;
        if (organizationInstanceId != null ? !organizationInstanceId.equals(that.organizationInstanceId) : that.organizationInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personInstanceId != null ? personInstanceId.hashCode() : 0;
        result = 31 * result + (organizationInstanceId != null ? organizationInstanceId.hashCode() : 0);
        return result;
    }

    public Person getPersonByPersonInstanceId() {
        return personByPersonInstanceId;
    }

    public void setPersonByPersonInstanceId(Person personByPersonInstanceId) {
        this.personByPersonInstanceId = personByPersonInstanceId;
    }

    public Organization getOrganizationByOrganizationInstanceId() {
        return organizationByOrganizationInstanceId;
    }

    public void setOrganizationByOrganizationInstanceId(Organization organizationByOrganizationInstanceId) {
        this.organizationByOrganizationInstanceId = organizationByOrganizationInstanceId;
    }
}
