package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class Address {
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
    @Column(name = "country", nullable = true, length = 255)
    private String country;
    @Basic
    @Column(name = "countrycode", nullable = true, length = 10)
    private String countrycode;
    @Basic
    @Column(name = "street", nullable = true, length = 255)
    private String street;
    @Basic
    @Column(name = "postal_code", nullable = true, length = 20)
    private String postalCode;
    @Basic
    @Column(name = "locality", nullable = true, length = 255)
    private String locality;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @OneToMany(mappedBy = "addressByAddressInstanceId")
    private Collection<FacilityAddress> facilityAddressesByInstanceId;
    @OneToMany(mappedBy = "addressByAddressId")
    private Collection<Organization> organizationsByInstanceId;
    @OneToMany(mappedBy = "addressByAddressId")
    private Collection<Person> peopleByInstanceId;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (instanceId != null ? !instanceId.equals(address.instanceId) : address.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(address.metaId) : address.metaId != null) return false;
        if (uid != null ? !uid.equals(address.uid) : address.uid != null) return false;
        if (versionId != null ? !versionId.equals(address.versionId) : address.versionId != null) return false;
        if (country != null ? !country.equals(address.country) : address.country != null) return false;
        if (countrycode != null ? !countrycode.equals(address.countrycode) : address.countrycode != null) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (postalCode != null ? !postalCode.equals(address.postalCode) : address.postalCode != null) return false;
        if (locality != null ? !locality.equals(address.locality) : address.locality != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (countrycode != null ? countrycode.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (locality != null ? locality.hashCode() : 0);
        return result;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Collection<FacilityAddress> getFacilityAddressesByInstanceId() {
        return facilityAddressesByInstanceId;
    }

    public void setFacilityAddressesByInstanceId(Collection<FacilityAddress> facilityAddressesByInstanceId) {
        this.facilityAddressesByInstanceId = facilityAddressesByInstanceId;
    }

    public Collection<Organization> getOrganizationsByInstanceId() {
        return organizationsByInstanceId;
    }

    public void setOrganizationsByInstanceId(Collection<Organization> organizationsByInstanceId) {
        this.organizationsByInstanceId = organizationsByInstanceId;
    }

    public Collection<Person> getPeopleByInstanceId() {
        return peopleByInstanceId;
    }

    public void setPeopleByInstanceId(Collection<Person> peopleByInstanceId) {
        this.peopleByInstanceId = peopleByInstanceId;
    }
}
