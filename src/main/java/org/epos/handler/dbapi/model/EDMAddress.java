package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "address")
public class EDMAddress {
    private String id;
    private String country;
    private String countrycode;
    private String street;
    private String postalCode;
    private String locality;
    private Collection<EDMFacility> facilitiesById;
    private Collection<EDMOrganization> organizationsById;
    private Collection<EDMPerson> peopleById;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "countrycode")
    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    @Basic
    @Column(name = "street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "locality")
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

        EDMAddress that = (EDMAddress) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (postalCode != null ? !postalCode.equals(that.postalCode) : that.postalCode != null) return false;
        return locality != null ? locality.equals(that.locality) : that.locality == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (locality != null ? locality.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "addressByAddressId")
    public Collection<EDMFacility> getFacilitiesById() {
        return facilitiesById;
    }

    public void setFacilitiesById(Collection<EDMFacility> facilitiesById) {
        this.facilitiesById = facilitiesById;
    }

    @OneToMany(mappedBy = "addressByAddressId")
    public Collection<EDMOrganization> getOrganizationsById() {
        return organizationsById;
    }

    public void setOrganizationsById(Collection<EDMOrganization> organizationsById) {
        this.organizationsById = organizationsById;
    }

    @OneToMany(mappedBy = "addressByAddressId")
    public Collection<EDMPerson> getPeopleById() {
        return peopleById;
    }

    public void setPeopleById(Collection<EDMPerson> peopleById) {
        this.peopleById = peopleById;
    }
}
