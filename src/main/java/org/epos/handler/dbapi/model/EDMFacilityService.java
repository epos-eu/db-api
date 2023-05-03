package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "facility_service")
@IdClass(EDMFacilityServicePK.class)
public class EDMFacilityService {
    private String instanceFacilityId;
    private String instanceServiceId;
    private EDMFacility facilityByInstanceFacilityId;
    private EDMService serviceByInstanceServiceId;

    @Id
    @Column(name = "instance_facility_id", insertable = false, updatable = false)
    public String getInstanceFacilityId() {
        return instanceFacilityId;
    }

    public void setInstanceFacilityId(String instanceFacilityId) {
        this.instanceFacilityId = instanceFacilityId;
    }

    @Id
    @Column(name = "instance_service_id", insertable = false, updatable = false)
    public String getInstanceServiceId() {
        return instanceServiceId;
    }

    public void setInstanceServiceId(String metaserviceId) {
        this.instanceServiceId = metaserviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EDMFacilityService that = (EDMFacilityService) o;
        return Objects.equals(getInstanceFacilityId(), that.getInstanceFacilityId()) && Objects.equals(getInstanceServiceId(), that.getInstanceServiceId()) && Objects.equals(facilityByInstanceFacilityId, that.facilityByInstanceFacilityId) && Objects.equals(serviceByInstanceServiceId, that.serviceByInstanceServiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInstanceFacilityId(), getInstanceServiceId(), facilityByInstanceFacilityId, serviceByInstanceServiceId);
    }

    @ManyToOne
    @JoinColumn(name = "instance_facility_id", referencedColumnName = "instance_id", nullable = false)
    public EDMFacility getFacilityByInstanceFacilityId() {
        return facilityByInstanceFacilityId;
    }

    public void setFacilityByInstanceFacilityId(EDMFacility facilityByInstanceFacilityId) {
        this.facilityByInstanceFacilityId = facilityByInstanceFacilityId;
    }

    @ManyToOne
    @JoinColumn(name = "instance_service_id", referencedColumnName = "instance_id", nullable = false)
    public EDMService getServiceByInstanceServiceId() {
        return serviceByInstanceServiceId;
    }

    public void setServiceByInstanceServiceId(EDMService edmEntityIdByMetaServiceId) {
        this.serviceByInstanceServiceId = edmEntityIdByMetaServiceId;
    }
}
