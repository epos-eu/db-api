package model;

import jakarta.persistence.*;

@Entity
@Table(name = "facility_element", schema = "public", catalog = "cerif")
@IdClass(FacilityElementPK.class)
public class FacilityElement {
    @Id
    @Column(name = "facility_instance_id", nullable = false, length = 100)
    private String facilityInstanceId;
    @Id
    @Column(name = "element_instance_id", nullable = false, length = 100)
    private String elementInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "facility_instance_id", referencedColumnName = "instance_id")
    private Facility facilityByFacilityInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "element_instance_id", referencedColumnName = "instance_id")
    private Element elementByElementInstanceId;

    public String getFacilityInstanceId() {
        return facilityInstanceId;
    }

    public void setFacilityInstanceId(String facilityInstanceId) {
        this.facilityInstanceId = facilityInstanceId;
    }

    public String getElementInstanceId() {
        return elementInstanceId;
    }

    public void setElementInstanceId(String elementInstanceId) {
        this.elementInstanceId = elementInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FacilityElement that = (FacilityElement) o;

        if (facilityInstanceId != null ? !facilityInstanceId.equals(that.facilityInstanceId) : that.facilityInstanceId != null)
            return false;
        if (elementInstanceId != null ? !elementInstanceId.equals(that.elementInstanceId) : that.elementInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = facilityInstanceId != null ? facilityInstanceId.hashCode() : 0;
        result = 31 * result + (elementInstanceId != null ? elementInstanceId.hashCode() : 0);
        return result;
    }

    public Facility getFacilityByFacilityInstanceId() {
        return facilityByFacilityInstanceId;
    }

    public void setFacilityByFacilityInstanceId(Facility facilityByFacilityInstanceId) {
        this.facilityByFacilityInstanceId = facilityByFacilityInstanceId;
    }

    public Element getElementByElementInstanceId() {
        return elementByElementInstanceId;
    }

    public void setElementByElementInstanceId(Element elementByElementInstanceId) {
        this.elementByElementInstanceId = elementByElementInstanceId;
    }
}
