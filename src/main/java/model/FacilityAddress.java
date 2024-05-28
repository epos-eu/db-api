package model;

import jakarta.persistence.*;

@Entity
@Table(name = "facility_address", schema = "public", catalog = "cerif")
@IdClass(FacilityAddressPK.class)
public class FacilityAddress {
    @Id
    @Column(name = "facility_instance_id", nullable = false, length = 100)
    private String facilityInstanceId;
    @Id
    @Column(name = "address_instance_id", nullable = false, length = 100)
    private String addressInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "facility_instance_id", referencedColumnName = "instance_id")
    private Facility facilityByFacilityInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "address_instance_id", referencedColumnName = "instance_id")
    private Address addressByAddressInstanceId;

    public String getFacilityInstanceId() {
        return facilityInstanceId;
    }

    public void setFacilityInstanceId(String facilityInstanceId) {
        this.facilityInstanceId = facilityInstanceId;
    }

    public String getAddressInstanceId() {
        return addressInstanceId;
    }

    public void setAddressInstanceId(String addressInstanceId) {
        this.addressInstanceId = addressInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FacilityAddress that = (FacilityAddress) o;

        if (facilityInstanceId != null ? !facilityInstanceId.equals(that.facilityInstanceId) : that.facilityInstanceId != null)
            return false;
        if (addressInstanceId != null ? !addressInstanceId.equals(that.addressInstanceId) : that.addressInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = facilityInstanceId != null ? facilityInstanceId.hashCode() : 0;
        result = 31 * result + (addressInstanceId != null ? addressInstanceId.hashCode() : 0);
        return result;
    }

    public Facility getFacilityByFacilityInstanceId() {
        return facilityByFacilityInstanceId;
    }

    public void setFacilityByFacilityInstanceId(Facility facilityByFacilityInstanceId) {
        this.facilityByFacilityInstanceId = facilityByFacilityInstanceId;
    }

    public Address getAddressByAddressInstanceId() {
        return addressByAddressInstanceId;
    }

    public void setAddressByAddressInstanceId(Address addressByAddressInstanceId) {
        this.addressByAddressInstanceId = addressByAddressInstanceId;
    }
}
