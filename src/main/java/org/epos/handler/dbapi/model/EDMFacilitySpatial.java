package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "facility_spatial")
public class EDMFacilitySpatial {
    private String id;
    private String location;
    private String instanceFacilityId;
    private EDMFacility facilityByInstanceFacilityId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "instance_facility_id", insertable = false, updatable = false)
    public String getInstanceFacilityId() {
        return instanceFacilityId;
    }

    public void setInstanceFacilityId(String instanceFacilityId) {
        this.instanceFacilityId = instanceFacilityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMFacilitySpatial that = (EDMFacilitySpatial) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        return instanceFacilityId != null ? instanceFacilityId.equals(that.instanceFacilityId) : that.instanceFacilityId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (instanceFacilityId != null ? instanceFacilityId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_facility_id", referencedColumnName = "instance_id", nullable = false)
    public EDMFacility getFacilityByInstanceFacilityId() {
        return facilityByInstanceFacilityId;
    }

    public void setFacilityByInstanceFacilityId(EDMFacility facilityByInstanceFacilityId) {
        this.facilityByInstanceFacilityId = facilityByInstanceFacilityId;
    }
}
