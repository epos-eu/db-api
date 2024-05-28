package model;

import jakarta.persistence.*;

@Entity
@Table(name = "service_identifier", schema = "public", catalog = "cerif")
@IdClass(ServiceIdentifierPK.class)
public class ServiceIdentifier {
    @Id
    @Column(name = "service_instance_id", nullable = false, length = 100)
    private String serviceInstanceId;
    @Id
    @Column(name = "identifier_instance_id", nullable = false, length = 100)
    private String identifierInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "service_instance_id", referencedColumnName = "instance_id")
    private Service serviceByServiceInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "identifier_instance_id", referencedColumnName = "instance_id")
    private Identifier identifierByIdentifierInstanceId;

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public String getIdentifierInstanceId() {
        return identifierInstanceId;
    }

    public void setIdentifierInstanceId(String identifierInstanceId) {
        this.identifierInstanceId = identifierInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceIdentifier that = (ServiceIdentifier) o;

        if (serviceInstanceId != null ? !serviceInstanceId.equals(that.serviceInstanceId) : that.serviceInstanceId != null)
            return false;
        if (identifierInstanceId != null ? !identifierInstanceId.equals(that.identifierInstanceId) : that.identifierInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serviceInstanceId != null ? serviceInstanceId.hashCode() : 0;
        result = 31 * result + (identifierInstanceId != null ? identifierInstanceId.hashCode() : 0);
        return result;
    }

    public Service getServiceByServiceInstanceId() {
        return serviceByServiceInstanceId;
    }

    public void setServiceByServiceInstanceId(Service serviceByServiceInstanceId) {
        this.serviceByServiceInstanceId = serviceByServiceInstanceId;
    }

    public Identifier getIdentifierByIdentifierInstanceId() {
        return identifierByIdentifierInstanceId;
    }

    public void setIdentifierByIdentifierInstanceId(Identifier identifierByIdentifierInstanceId) {
        this.identifierByIdentifierInstanceId = identifierByIdentifierInstanceId;
    }
}
