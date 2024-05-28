package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class Contactpoint {
    @Id
    @Column(name = "instance_id", nullable = false, length = 100)
    private String instanceId;
    @Basic
    @Column(name = "meta_id", nullable = true, length = 100)
    private String metaId;
    @Basic
    @Column(name = "uid", nullable = true, length = 100)
    private String uid;
    @Basic
    @Column(name = "version_id", nullable = true, length = 100)
    private String versionId;
    @Basic
    @Column(name = "role", nullable = true, length = 1024)
    private String role;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @OneToMany(mappedBy = "contactpointByContactpointInstanceId")
    private Collection<ContactpointElement> contactpointElementsByInstanceId;
    @OneToMany(mappedBy = "contactpointByContactpointInstanceId")
    private Collection<DataproductContactpoint> dataproductContactpointsByInstanceId;
    @OneToMany(mappedBy = "contactpointByContactpointInstanceId")
    private Collection<EquipmentContactpoint> equipmentContactpointsByInstanceId;
    @OneToMany(mappedBy = "contactpointByContactpointInstanceId")
    private Collection<FacilityContactpoint> facilityContactpointsByInstanceId;
    @OneToMany(mappedBy = "contactpointByContactpointInstanceId")
    private Collection<OrganizationContactpoint> organizationContactpointsByInstanceId;
    @OneToMany(mappedBy = "contactpointByContactpointInstanceId")
    private Collection<ServiceContactpoint> serviceContactpointsByInstanceId;
    @OneToMany(mappedBy = "contactpointByContactpointInstanceId")
    private Collection<SoftwareapplicationContactpoint> softwareapplicationContactpointsByInstanceId;
    @OneToMany(mappedBy = "contactpointByContactpointInstanceId")
    private Collection<SoftwaresourcecodeContactpoint> softwaresourcecodeContactpointsByInstanceId;
    @OneToMany(mappedBy = "contactpointByContactpointInstanceId")
    private Collection<WebserviceContactpoint> webserviceContactpointsByInstanceId;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getMetaId() {
        return metaId;
    }

    public void setMetaId(String metaId) {
        this.metaId = metaId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contactpoint that = (Contactpoint) o;

        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(that.metaId) : that.metaId != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (versionId != null ? !versionId.equals(that.versionId) : that.versionId != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Collection<ContactpointElement> getContactpointElementsByInstanceId() {
        return contactpointElementsByInstanceId;
    }

    public void setContactpointElementsByInstanceId(Collection<ContactpointElement> contactpointElementsByInstanceId) {
        this.contactpointElementsByInstanceId = contactpointElementsByInstanceId;
    }

    public Collection<DataproductContactpoint> getDataproductContactpointsByInstanceId() {
        return dataproductContactpointsByInstanceId;
    }

    public void setDataproductContactpointsByInstanceId(Collection<DataproductContactpoint> dataproductContactpointsByInstanceId) {
        this.dataproductContactpointsByInstanceId = dataproductContactpointsByInstanceId;
    }

    public Collection<EquipmentContactpoint> getEquipmentContactpointsByInstanceId() {
        return equipmentContactpointsByInstanceId;
    }

    public void setEquipmentContactpointsByInstanceId(Collection<EquipmentContactpoint> equipmentContactpointsByInstanceId) {
        this.equipmentContactpointsByInstanceId = equipmentContactpointsByInstanceId;
    }

    public Collection<FacilityContactpoint> getFacilityContactpointsByInstanceId() {
        return facilityContactpointsByInstanceId;
    }

    public void setFacilityContactpointsByInstanceId(Collection<FacilityContactpoint> facilityContactpointsByInstanceId) {
        this.facilityContactpointsByInstanceId = facilityContactpointsByInstanceId;
    }

    public Collection<OrganizationContactpoint> getOrganizationContactpointsByInstanceId() {
        return organizationContactpointsByInstanceId;
    }

    public void setOrganizationContactpointsByInstanceId(Collection<OrganizationContactpoint> organizationContactpointsByInstanceId) {
        this.organizationContactpointsByInstanceId = organizationContactpointsByInstanceId;
    }

    public Collection<ServiceContactpoint> getServiceContactpointsByInstanceId() {
        return serviceContactpointsByInstanceId;
    }

    public void setServiceContactpointsByInstanceId(Collection<ServiceContactpoint> serviceContactpointsByInstanceId) {
        this.serviceContactpointsByInstanceId = serviceContactpointsByInstanceId;
    }

    public Collection<SoftwareapplicationContactpoint> getSoftwareapplicationContactpointsByInstanceId() {
        return softwareapplicationContactpointsByInstanceId;
    }

    public void setSoftwareapplicationContactpointsByInstanceId(Collection<SoftwareapplicationContactpoint> softwareapplicationContactpointsByInstanceId) {
        this.softwareapplicationContactpointsByInstanceId = softwareapplicationContactpointsByInstanceId;
    }

    public Collection<SoftwaresourcecodeContactpoint> getSoftwaresourcecodeContactpointsByInstanceId() {
        return softwaresourcecodeContactpointsByInstanceId;
    }

    public void setSoftwaresourcecodeContactpointsByInstanceId(Collection<SoftwaresourcecodeContactpoint> softwaresourcecodeContactpointsByInstanceId) {
        this.softwaresourcecodeContactpointsByInstanceId = softwaresourcecodeContactpointsByInstanceId;
    }

    public Collection<WebserviceContactpoint> getWebserviceContactpointsByInstanceId() {
        return webserviceContactpointsByInstanceId;
    }

    public void setWebserviceContactpointsByInstanceId(Collection<WebserviceContactpoint> webserviceContactpointsByInstanceId) {
        this.webserviceContactpointsByInstanceId = webserviceContactpointsByInstanceId;
    }
}
