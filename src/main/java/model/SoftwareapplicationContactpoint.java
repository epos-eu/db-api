package model;

import jakarta.persistence.*;

@Entity
@Table(name = "softwareapplication_contactpoint", schema = "public", catalog = "cerif")
@IdClass(SoftwareapplicationContactpointPK.class)
public class SoftwareapplicationContactpoint {
    @Id
    @Column(name = "softwareapplication_instance_id", nullable = false, length = 100)
    private String softwareapplicationInstanceId;
    @Id
    @Column(name = "contactpoint_instance_id", nullable = false, length = 100)
    private String contactpointInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "softwareapplication_instance_id", referencedColumnName = "instance_id")
    private Softwareapplication softwareapplicationBySoftwareapplicationInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "contactpoint_instance_id", referencedColumnName = "instance_id")
    private Contactpoint contactpointByContactpointInstanceId;

    public String getSoftwareapplicationInstanceId() {
        return softwareapplicationInstanceId;
    }

    public void setSoftwareapplicationInstanceId(String softwareapplicationInstanceId) {
        this.softwareapplicationInstanceId = softwareapplicationInstanceId;
    }

    public String getContactpointInstanceId() {
        return contactpointInstanceId;
    }

    public void setContactpointInstanceId(String contactpointInstanceId) {
        this.contactpointInstanceId = contactpointInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SoftwareapplicationContactpoint that = (SoftwareapplicationContactpoint) o;

        if (softwareapplicationInstanceId != null ? !softwareapplicationInstanceId.equals(that.softwareapplicationInstanceId) : that.softwareapplicationInstanceId != null)
            return false;
        if (contactpointInstanceId != null ? !contactpointInstanceId.equals(that.contactpointInstanceId) : that.contactpointInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = softwareapplicationInstanceId != null ? softwareapplicationInstanceId.hashCode() : 0;
        result = 31 * result + (contactpointInstanceId != null ? contactpointInstanceId.hashCode() : 0);
        return result;
    }

    public Softwareapplication getSoftwareapplicationBySoftwareapplicationInstanceId() {
        return softwareapplicationBySoftwareapplicationInstanceId;
    }

    public void setSoftwareapplicationBySoftwareapplicationInstanceId(Softwareapplication softwareapplicationBySoftwareapplicationInstanceId) {
        this.softwareapplicationBySoftwareapplicationInstanceId = softwareapplicationBySoftwareapplicationInstanceId;
    }

    public Contactpoint getContactpointByContactpointInstanceId() {
        return contactpointByContactpointInstanceId;
    }

    public void setContactpointByContactpointInstanceId(Contactpoint contactpointByContactpointInstanceId) {
        this.contactpointByContactpointInstanceId = contactpointByContactpointInstanceId;
    }
}
