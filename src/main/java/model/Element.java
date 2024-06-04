package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class Element {
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
    @Column(name = "type", nullable = true)
    @Enumerated(EnumType.STRING)
    private ElementType type;
    @Basic
    @Column(name = "value", nullable = false, length = 1024)
    private String value;
    @OneToMany(mappedBy = "elementByElementInstanceId")
    private Collection<ContactpointElement> contactpointElementsByInstanceId;
    @OneToMany(mappedBy = "elementByElementInstanceId")
    private Collection<DistributionElement> distributionElementsByInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @OneToMany(mappedBy = "elementByElementInstanceId")
    private Collection<EquipmentElement> equipmentElementsByInstanceId;
    @OneToMany(mappedBy = "elementByElementInstanceId")
    private Collection<FacilityElement> facilityElementsByInstanceId;
    @OneToMany(mappedBy = "elementByElementInstanceId")
    private Collection<MappingElement> mappingElementsByInstanceId;
    @OneToMany(mappedBy = "elementByElementInstanceId")
    private Collection<OperationElement> operationElementsByInstanceId;
    @OneToMany(mappedBy = "elementByElementInstanceId")
    private Collection<OrganizationElement> organizationElementsByInstanceId;
    @OneToMany(mappedBy = "elementByElementInstanceId")
    private Collection<PersonElement> personElementsByInstanceId;
    @OneToMany(mappedBy = "elementByElementInstanceId")
    private Collection<SoftwaresourcecodeElement> softwaresourcecodeElementsByInstanceId;
    @OneToMany(mappedBy = "elementByElementInstanceId")
    private Collection<WebserviceElement> webserviceElementsByInstanceId;

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

    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Element element = (Element) o;

        if (instanceId != null ? !instanceId.equals(element.instanceId) : element.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(element.metaId) : element.metaId != null) return false;
        if (uid != null ? !uid.equals(element.uid) : element.uid != null) return false;
        if (versionId != null ? !versionId.equals(element.versionId) : element.versionId != null) return false;
        if (type != null ? !type.equals(element.type) : element.type != null) return false;
        if (value != null ? !value.equals(element.value) : element.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    public Collection<ContactpointElement> getContactpointElementsByInstanceId() {
        return contactpointElementsByInstanceId;
    }

    public void setContactpointElementsByInstanceId(Collection<ContactpointElement> contactpointElementsByInstanceId) {
        this.contactpointElementsByInstanceId = contactpointElementsByInstanceId;
    }

    public Collection<DistributionElement> getDistributionElementsByInstanceId() {
        return distributionElementsByInstanceId;
    }

    public void setDistributionElementsByInstanceId(Collection<DistributionElement> distributionElementsByInstanceId) {
        this.distributionElementsByInstanceId = distributionElementsByInstanceId;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Collection<EquipmentElement> getEquipmentElementsByInstanceId() {
        return equipmentElementsByInstanceId;
    }

    public void setEquipmentElementsByInstanceId(Collection<EquipmentElement> equipmentElementsByInstanceId) {
        this.equipmentElementsByInstanceId = equipmentElementsByInstanceId;
    }

    public Collection<FacilityElement> getFacilityElementsByInstanceId() {
        return facilityElementsByInstanceId;
    }

    public void setFacilityElementsByInstanceId(Collection<FacilityElement> facilityElementsByInstanceId) {
        this.facilityElementsByInstanceId = facilityElementsByInstanceId;
    }

    public Collection<MappingElement> getMappingElementsByInstanceId() {
        return mappingElementsByInstanceId;
    }

    public void setMappingElementsByInstanceId(Collection<MappingElement> mappingElementsByInstanceId) {
        this.mappingElementsByInstanceId = mappingElementsByInstanceId;
    }

    public Collection<OperationElement> getOperationElementsByInstanceId() {
        return operationElementsByInstanceId;
    }

    public void setOperationElementsByInstanceId(Collection<OperationElement> operationElementsByInstanceId) {
        this.operationElementsByInstanceId = operationElementsByInstanceId;
    }

    public Collection<OrganizationElement> getOrganizationElementsByInstanceId() {
        return organizationElementsByInstanceId;
    }

    public void setOrganizationElementsByInstanceId(Collection<OrganizationElement> organizationElementsByInstanceId) {
        this.organizationElementsByInstanceId = organizationElementsByInstanceId;
    }

    public Collection<PersonElement> getPersonElementsByInstanceId() {
        return personElementsByInstanceId;
    }

    public void setPersonElementsByInstanceId(Collection<PersonElement> personElementsByInstanceId) {
        this.personElementsByInstanceId = personElementsByInstanceId;
    }

    public Collection<SoftwaresourcecodeElement> getSoftwaresourcecodeElementsByInstanceId() {
        return softwaresourcecodeElementsByInstanceId;
    }

    public void setSoftwaresourcecodeElementsByInstanceId(Collection<SoftwaresourcecodeElement> softwaresourcecodeElementsByInstanceId) {
        this.softwaresourcecodeElementsByInstanceId = softwaresourcecodeElementsByInstanceId;
    }

    public Collection<WebserviceElement> getWebserviceElementsByInstanceId() {
        return webserviceElementsByInstanceId;
    }

    public void setWebserviceElementsByInstanceId(Collection<WebserviceElement> webserviceElementsByInstanceId) {
        this.webserviceElementsByInstanceId = webserviceElementsByInstanceId;
    }
}
