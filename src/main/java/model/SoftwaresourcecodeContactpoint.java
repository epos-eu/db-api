package model;

import jakarta.persistence.*;

@Entity
@Table(name = "softwaresourcecode_contactpoint", schema = "public", catalog = "cerif")
@IdClass(SoftwaresourcecodeContactpointPK.class)
public class SoftwaresourcecodeContactpoint {
    @Id
    @Column(name = "softwaresourcecode_instance_id", nullable = false, length = 100)
    private String softwaresourcecodeInstanceId;
    @Id
    @Column(name = "contactpoint_instance_id", nullable = false, length = 100)
    private String contactpointInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "softwaresourcecode_instance_id", referencedColumnName = "instance_id")
    private SoftwareSourceCode softwaresourcecodeBySoftwareSourceCodeInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "contactpoint_instance_id", referencedColumnName = "instance_id")
    private Contactpoint contactpointByContactpointInstanceId;

    public String getSoftwaresourcecodeInstanceId() {
        return softwaresourcecodeInstanceId;
    }

    public void setSoftwaresourcecodeInstanceId(String softwaresourcecodeInstanceId) {
        this.softwaresourcecodeInstanceId = softwaresourcecodeInstanceId;
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

        SoftwaresourcecodeContactpoint that = (SoftwaresourcecodeContactpoint) o;

        if (softwaresourcecodeInstanceId != null ? !softwaresourcecodeInstanceId.equals(that.softwaresourcecodeInstanceId) : that.softwaresourcecodeInstanceId != null)
            return false;
        if (contactpointInstanceId != null ? !contactpointInstanceId.equals(that.contactpointInstanceId) : that.contactpointInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = softwaresourcecodeInstanceId != null ? softwaresourcecodeInstanceId.hashCode() : 0;
        result = 31 * result + (contactpointInstanceId != null ? contactpointInstanceId.hashCode() : 0);
        return result;
    }

    public SoftwareSourceCode getSoftwaresourcecodeBySoftwaresourcecodeInstanceId() {
        return softwaresourcecodeBySoftwareSourceCodeInstanceId;
    }

    public void setSoftwaresourcecodeBySoftwaresourcecodeInstanceId(SoftwareSourceCode softwaresourcecodeBySoftwareSourceCodeInstanceId) {
        this.softwaresourcecodeBySoftwareSourceCodeInstanceId = softwaresourcecodeBySoftwareSourceCodeInstanceId;
    }

    public Contactpoint getContactpointByContactpointInstanceId() {
        return contactpointByContactpointInstanceId;
    }

    public void setContactpointByContactpointInstanceId(Contactpoint contactpointByContactpointInstanceId) {
        this.contactpointByContactpointInstanceId = contactpointByContactpointInstanceId;
    }
}
