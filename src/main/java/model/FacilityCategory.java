package model;

import jakarta.persistence.*;

@Entity
@Table(name = "facility_category", schema = "public", catalog = "cerif")
@IdClass(FacilityCategoryPK.class)
public class FacilityCategory {
    @Id
    @Column(name = "category_instance_id", nullable = false, length = 100)
    private String categoryInstanceId;
    @Id
    @Column(name = "facility_instance_id", nullable = false, length = 100)
    private String facilityInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "category_instance_id", referencedColumnName = "instance_id")
    private Category categoryByCategoryInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "facility_instance_id", referencedColumnName = "instance_id")
    private Facility facilityByFacilityInstanceId;

    public String getCategoryInstanceId() {
        return categoryInstanceId;
    }

    public void setCategoryInstanceId(String categoryInstanceId) {
        this.categoryInstanceId = categoryInstanceId;
    }

    public String getFacilityInstanceId() {
        return facilityInstanceId;
    }

    public void setFacilityInstanceId(String facilityInstanceId) {
        this.facilityInstanceId = facilityInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FacilityCategory that = (FacilityCategory) o;

        if (categoryInstanceId != null ? !categoryInstanceId.equals(that.categoryInstanceId) : that.categoryInstanceId != null)
            return false;
        if (facilityInstanceId != null ? !facilityInstanceId.equals(that.facilityInstanceId) : that.facilityInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryInstanceId != null ? categoryInstanceId.hashCode() : 0;
        result = 31 * result + (facilityInstanceId != null ? facilityInstanceId.hashCode() : 0);
        return result;
    }

    public Category getCategoryByCategoryInstanceId() {
        return categoryByCategoryInstanceId;
    }

    public void setCategoryByCategoryInstanceId(Category categoryByCategoryInstanceId) {
        this.categoryByCategoryInstanceId = categoryByCategoryInstanceId;
    }

    public Facility getFacilityByFacilityInstanceId() {
        return facilityByFacilityInstanceId;
    }

    public void setFacilityByFacilityInstanceId(Facility facilityByFacilityInstanceId) {
        this.facilityByFacilityInstanceId = facilityByFacilityInstanceId;
    }
}
