package model;

import jakarta.persistence.*;

@Entity
@Table(name = "softwareapplication_identifier", schema = "public", catalog = "cerif")
@IdClass(SoftwareapplicationIdentifierPK.class)
public class SoftwareapplicationIdentifier {
    @Id
    @Column(name = "softwareapplication_instance_id", nullable = false, length = 100)
    private String softwareapplicationInstanceId;
    @Id
    @Column(name = "identifier_instance_id", nullable = false, length = 100)
    private String identifierInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "softwareapplication_instance_id", referencedColumnName = "instance_id")
    private SoftwareApplication softwareapplicationBySoftwareApplicationInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "identifier_instance_id", referencedColumnName = "instance_id")
    private Identifier identifierByIdentifierInstanceId;

    public String getSoftwareapplicationInstanceId() {
        return softwareapplicationInstanceId;
    }

    public void setSoftwareapplicationInstanceId(String softwareapplicationInstanceId) {
        this.softwareapplicationInstanceId = softwareapplicationInstanceId;
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

        SoftwareapplicationIdentifier that = (SoftwareapplicationIdentifier) o;

        if (softwareapplicationInstanceId != null ? !softwareapplicationInstanceId.equals(that.softwareapplicationInstanceId) : that.softwareapplicationInstanceId != null)
            return false;
        if (identifierInstanceId != null ? !identifierInstanceId.equals(that.identifierInstanceId) : that.identifierInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = softwareapplicationInstanceId != null ? softwareapplicationInstanceId.hashCode() : 0;
        result = 31 * result + (identifierInstanceId != null ? identifierInstanceId.hashCode() : 0);
        return result;
    }

    public SoftwareApplication getSoftwareapplicationBySoftwareapplicationInstanceId() {
        return softwareapplicationBySoftwareApplicationInstanceId;
    }

    public void setSoftwareapplicationBySoftwareapplicationInstanceId(SoftwareApplication softwareapplicationBySoftwareApplicationInstanceId) {
        this.softwareapplicationBySoftwareApplicationInstanceId = softwareapplicationBySoftwareApplicationInstanceId;
    }

    public Identifier getIdentifierByIdentifierInstanceId() {
        return identifierByIdentifierInstanceId;
    }

    public void setIdentifierByIdentifierInstanceId(Identifier identifierByIdentifierInstanceId) {
        this.identifierByIdentifierInstanceId = identifierByIdentifierInstanceId;
    }
}
