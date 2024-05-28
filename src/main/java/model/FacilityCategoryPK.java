package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class FacilityCategoryPK implements Serializable {
    @Column(name = "category_instance_id", nullable = false, length = 100)
    @Id
    private String categoryInstanceId;
    @Column(name = "facility_instance_id", nullable = false, length = 100)
    @Id
    private String facilityInstanceId;

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

        FacilityCategoryPK that = (FacilityCategoryPK) o;

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
}
