package model;

import jakarta.persistence.*;

@Entity
@Table(name = "service_temporal", schema = "public", catalog = "cerif")
@IdClass(ServiceTemporalPK.class)
public class ServiceTemporal {
    @Id
    @Column(name = "service_instance_id", nullable = false, length = 100)
    private String serviceInstanceId;
    @Id
    @Column(name = "temporal_instance_id", nullable = false, length = 100)
    private String temporalInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "service_instance_id", referencedColumnName = "instance_id")
    private Service serviceByServiceInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "temporal_instance_id", referencedColumnName = "instance_id")
    private Temporal temporalByTemporalInstanceId;

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public String getTemporalInstanceId() {
        return temporalInstanceId;
    }

    public void setTemporalInstanceId(String temporalInstanceId) {
        this.temporalInstanceId = temporalInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceTemporal that = (ServiceTemporal) o;

        if (serviceInstanceId != null ? !serviceInstanceId.equals(that.serviceInstanceId) : that.serviceInstanceId != null)
            return false;
        if (temporalInstanceId != null ? !temporalInstanceId.equals(that.temporalInstanceId) : that.temporalInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serviceInstanceId != null ? serviceInstanceId.hashCode() : 0;
        result = 31 * result + (temporalInstanceId != null ? temporalInstanceId.hashCode() : 0);
        return result;
    }

    public Service getServiceByServiceInstanceId() {
        return serviceByServiceInstanceId;
    }

    public void setServiceByServiceInstanceId(Service serviceByServiceInstanceId) {
        this.serviceByServiceInstanceId = serviceByServiceInstanceId;
    }

    public Temporal getTemporalByTemporalInstanceId() {
        return temporalByTemporalInstanceId;
    }

    public void setTemporalByTemporalInstanceId(Temporal temporalByTemporalInstanceId) {
        this.temporalByTemporalInstanceId = temporalByTemporalInstanceId;
    }
}
