package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "facility_facility")
@IdClass(EDMFacilityFacilityPK.class)
public class EDMFacilityFacility {
    private String instanceFacility1Id;
    private String instanceFacility2Id;
    private EDMFacility facilityByInstanceFacility1Id;
    private EDMFacility facilityByInstanceFacility2Id;

    @Id
    @Column(name = "instance_facility1_id", insertable = false, updatable = false)
    public String getInstanceFacility1Id() {
        return instanceFacility1Id;
    }

    public void setInstanceFacility1Id(String instanceFacility1Id) {
        this.instanceFacility1Id = instanceFacility1Id;
    }

    @Id
    @Column(name = "instance_facility2_id", insertable = false, updatable = false)
    public String getInstanceFacility2Id() {
        return instanceFacility2Id;
    }

    public void setInstanceFacility2Id(String metaFacility2Id) {
        this.instanceFacility2Id = metaFacility2Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMFacilityFacility that = (EDMFacilityFacility) o;

        if (instanceFacility1Id != null ? !instanceFacility1Id.equals(that.instanceFacility1Id) : that.instanceFacility1Id != null)
            return false;
        return instanceFacility2Id != null ? instanceFacility2Id.equals(that.instanceFacility2Id) : that.instanceFacility2Id == null;
    }

    @Override
    public int hashCode() {
        int result = instanceFacility1Id != null ? instanceFacility1Id.hashCode() : 0;
        result = 31 * result + (instanceFacility2Id != null ? instanceFacility2Id.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_facility1_id", referencedColumnName = "instance_id", nullable = false)
    public EDMFacility getFacilityByInstanceFacility1Id() {
        return facilityByInstanceFacility1Id;
    }

    public void setFacilityByInstanceFacility1Id(EDMFacility facilityByInstanceFacility1Id) {
        this.facilityByInstanceFacility1Id = facilityByInstanceFacility1Id;
    }

    @ManyToOne
    @JoinColumn(name = "instance_facility2_id", referencedColumnName = "instance_id", nullable = false)
    public EDMFacility getFacilityByInstanceFacility2Id() {
        return facilityByInstanceFacility2Id;
    }

    public void setFacilityByInstanceFacility2Id(EDMFacility edmEntityIdByMetaFacility2Id) {
        this.facilityByInstanceFacility2Id = edmEntityIdByMetaFacility2Id;
    }
}
