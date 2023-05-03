package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "facility_category")
@IdClass(EDMFacilityCategoryPK.class)
public class EDMFacilityCategory {
    private String categoryId;
    private String instanceFacilityId;
    private EDMCategory categoryByCategoryId;
    private EDMFacility facilityByInstanceFacilityId;

    @Id
    @Column(name = "category_id", insertable = false, updatable = false)
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Id
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

        EDMFacilityCategory that = (EDMFacilityCategory) o;

        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        return instanceFacilityId != null ? instanceFacilityId.equals(that.instanceFacilityId) : that.instanceFacilityId == null;
    }

    @Override
    public int hashCode() {
        int result = categoryId != null ? categoryId.hashCode() : 0;
        result = 31 * result + (instanceFacilityId != null ? instanceFacilityId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public EDMCategory getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(EDMCategory edmEntityIdByCategoryId) {
        this.categoryByCategoryId = edmEntityIdByCategoryId;
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
