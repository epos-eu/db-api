package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class Person {
    @Id
    @Column(name = "instance_id", nullable = false, length = 100)
    private String instanceId;
    @Basic
    @Column(name = "meta_id", nullable = true, length = 100)
    private String metaId;
    @Basic
    @Column(name = "uid", nullable = true, length = 100)
    private String uid;
    @Basic
    @Column(name = "version_id", nullable = true, length = 100)
    private String versionId;
    @Basic
    @Column(name = "familyname", nullable = true, length = 1024)
    private String familyname;
    @Basic
    @Column(name = "givenname", nullable = true, length = 1024)
    private String givenname;
    @Basic
    @Column(name = "qualifications", nullable = true, length = 1024)
    private String qualifications;
    @Basic
    @Column(name = "cvurl", nullable = true, length = 1024)
    private String cvurl;
    @Basic
    @Column(name = "address_id", nullable = true, length = 100)
    private String addressId;
    @OneToMany(mappedBy = "personByPersonInstanceId")
    private Collection<OrganizationAffiliation> organizationAffiliationsByInstanceId;

    @OneToMany(mappedBy = "personByPersonInstanceId")
    private Collection<PersonContactpoint> personContactpointsByInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "address_id", referencedColumnName = "instance_id")
    private Address addressByAddressId;
    @OneToMany(mappedBy = "personByPersonInstanceId")
    private Collection<PersonElement> personElementsByInstanceId;
    @OneToMany(mappedBy = "personByPersonInstanceId")
    private Collection<PersonIdentifier> personIdentifiersByInstanceId;
    @OneToMany(mappedBy = "personByPersonInstanceId")
    private Collection<PublicationContributor> publicationContributorsByInstanceId;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getMetaId() {
        return metaId;
    }

    public void setMetaId(String metaId) {
        this.metaId = metaId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getGivenname() {
        return givenname;
    }

    public void setGivenname(String givenname) {
        this.givenname = givenname;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getCvurl() {
        return cvurl;
    }

    public void setCvurl(String cvurl) {
        this.cvurl = cvurl;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (instanceId != null ? !instanceId.equals(person.instanceId) : person.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(person.metaId) : person.metaId != null) return false;
        if (uid != null ? !uid.equals(person.uid) : person.uid != null) return false;
        if (versionId != null ? !versionId.equals(person.versionId) : person.versionId != null) return false;
        if (familyname != null ? !familyname.equals(person.familyname) : person.familyname != null) return false;
        if (givenname != null ? !givenname.equals(person.givenname) : person.givenname != null) return false;
        if (qualifications != null ? !qualifications.equals(person.qualifications) : person.qualifications != null)
            return false;
        if (cvurl != null ? !cvurl.equals(person.cvurl) : person.cvurl != null) return false;
        if (addressId != null ? !addressId.equals(person.addressId) : person.addressId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (familyname != null ? familyname.hashCode() : 0);
        result = 31 * result + (givenname != null ? givenname.hashCode() : 0);
        result = 31 * result + (qualifications != null ? qualifications.hashCode() : 0);
        result = 31 * result + (cvurl != null ? cvurl.hashCode() : 0);
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        return result;
    }

    public Collection<OrganizationAffiliation> getOrganizationAffiliationsByInstanceId() {
        return organizationAffiliationsByInstanceId;
    }

    public void setOrganizationAffiliationsByInstanceId(Collection<OrganizationAffiliation> organizationAffiliationsByInstanceId) {
        this.organizationAffiliationsByInstanceId = organizationAffiliationsByInstanceId;
    }

    public Collection<PersonContactpoint> getPersonContactpointsByInstanceId() {
        return personContactpointsByInstanceId;
    }

    public void setPersonContactpointsByInstanceId(Collection<PersonContactpoint> personContactpointsByInstanceId) {
        this.personContactpointsByInstanceId = personContactpointsByInstanceId;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Address getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(Address addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }

    public Collection<PersonElement> getPersonElementsByInstanceId() {
        return personElementsByInstanceId;
    }

    public void setPersonElementsByInstanceId(Collection<PersonElement> personElementsByInstanceId) {
        this.personElementsByInstanceId = personElementsByInstanceId;
    }

    public Collection<PersonIdentifier> getPersonIdentifiersByInstanceId() {
        return personIdentifiersByInstanceId;
    }

    public void setPersonIdentifiersByInstanceId(Collection<PersonIdentifier> personIdentifiersByInstanceId) {
        this.personIdentifiersByInstanceId = personIdentifiersByInstanceId;
    }

    public Collection<PublicationContributor> getPublicationContributorsByInstanceId() {
        return publicationContributorsByInstanceId;
    }

    public void setPublicationContributorsByInstanceId(Collection<PublicationContributor> publicationContributorsByInstanceId) {
        this.publicationContributorsByInstanceId = publicationContributorsByInstanceId;
    }
}
