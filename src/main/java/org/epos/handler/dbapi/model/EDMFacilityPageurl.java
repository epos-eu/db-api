package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "facility_pageurl")
public class EDMFacilityPageurl {
    private String id;
    private String pageurl;
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
    @Column(name = "pageurl")
    public String getPageurl() {
        return pageurl;
    }

    public void setPageurl(String pageurl) {
        this.pageurl = pageurl;
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

        EDMFacilityPageurl that = (EDMFacilityPageurl) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (pageurl != null ? !pageurl.equals(that.pageurl) : that.pageurl != null) return false;
        return instanceFacilityId != null ? instanceFacilityId.equals(that.instanceFacilityId) : that.instanceFacilityId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (pageurl != null ? pageurl.hashCode() : 0);
        result = 31 * result + (instanceFacilityId != null ? instanceFacilityId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_facility_id", referencedColumnName = "instance_id")
    public EDMFacility getFacilityByInstanceFacilityId() {
        return facilityByInstanceFacilityId;
    }

    public void setFacilityByInstanceFacilityId(EDMFacility facilityByInstanceFacilityId) {
        this.facilityByInstanceFacilityId = facilityByInstanceFacilityId;
    }
}
