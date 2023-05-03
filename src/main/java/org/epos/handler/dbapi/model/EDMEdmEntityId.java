package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "edm_entity_id", schema = "public")
@NamedQueries({
        @NamedQuery(name = "edmentityid.findAll", query = "SELECT c FROM EDMEdmEntityId c"),
        @NamedQuery(name = "edmentityid.findByMetaId", query = "select c from EDMEdmEntityId c where c.metaId = :METAID")
})
public class EDMEdmEntityId {
    private String metaId;
    private Collection<EDMAffiliation> affiliationsByMetaId;
    private Collection<EDMAuthorization> authorizationsByMetaId;
    private Collection<EDMContactpoint> contactpointsByMetaId;
    private Collection<EDMContactpoint> contactpointsByMetaId_0;
    private Collection<EDMContactpoint> contactpointsByMetaId_1;
    private Collection<EDMContactpoint> contactpointsByMetaId_2;
    private Collection<EDMContract> contractsByMetaId;
    private Collection<EDMContract> contractsByMetaId_0;
    private Collection<EDMContributor> contributorsByMetaId;
    private Collection<EDMDataproduct> dataproductsByMetaId;
    private Collection<EDMDataproduct> dataproductsByMetaId_0;
    private Collection<EDMDataproductImplementationStatus> dataproductImplementationStatusesByMetaId;
    private Collection<EDMDataproductImplementationStatus> dataproductImplementationStatusesByMetaId_0;
    private Collection<EDMDataproductImplementationStatus> dataproductImplementationStatusesByMetaId_1;
    private Collection<EDMDataproductImplementationStatus> dataproductImplementationStatusesByMetaId_2;
    private Collection<EDMDistribution> distributionsByMetaId_0;
    private Collection<EDMDistribution> distributionsByMetaId_1;
    private Collection<EDMEquipment> equipmentByMetaId;
    private Collection<EDMEquipment> equipmentByMetaId_0;
    private Collection<EDMEquipment> equipmentByMetaId_1;
    private Collection<EDMEquipment> equipmentByMetaId_2;
    private Collection<EDMFacility> facilitiesByMetaId;
    private Collection<EDMFacility> facilitiesByMetaId_0;
    private Collection<EDMFacility> facilitiesByMetaId_1;
    private Collection<EDMGroupUser> groupUsersByMetaId;
    private Collection<EDMOperation> operationsByMetaId;
    private Collection<EDMOperation> operationsByMetaId_0;
    private Collection<EDMOrganization> organizationsByMetaId;
    private Collection<EDMOrganization> organizationsByMetaId_0;
    private Collection<EDMPerson> peopleByMetaId;
    private Collection<EDMPersonLite> peopleByMetaIdLite;
    private Collection<EDMPerson> peopleByMetaId_0;
    private Collection<EDMPublication> publicationsByMetaId_1;
    private Collection<EDMPublication> publicationsByMetaId_2;
    private Collection<EDMPublisher> publishersByMetaId;
    private Collection<EDMService> servicesByMetaId;
    private Collection<EDMService> servicesByMetaId_0;
    private Collection<EDMService> servicesByMetaId_1;
    private Collection<EDMService> servicesByMetaId_2;
    private Collection<EDMServiceImplementationStatus> serviceImplementationStatusesByMetaId;
    private Collection<EDMServiceImplementationStatus> serviceImplementationStatusesByMetaId_0;
    private Collection<EDMServiceImplementationStatus> serviceImplementationStatusesByMetaId_1;
    private Collection<EDMServiceImplementationStatus> serviceImplementationStatusesByMetaId_2;
    private Collection<EDMSoftwareapplication> softwareapplicationsByMetaId;
    private Collection<EDMSoftwareapplication> softwareapplicationsByMetaId_0;
    private Collection<EDMSoftwaresourcecode> softwaresourcecodesByMetaId;
    private Collection<EDMSoftwaresourcecode> softwaresourcecodesByMetaId_0;
    private Collection<EDMWebservice> webservicesByMetaId;
    private Collection<EDMWebservice> webservicesByMetaId_0;
    private Collection<EDMWebservice> webservicesByMetaId_1;

    @Id
    @Column(name = "meta_id", nullable = false)
    public String getMetaId() {
        return metaId;
    }

    public void setMetaId(String metaId) {
        this.metaId = metaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMEdmEntityId that = (EDMEdmEntityId) o;

        return metaId != null ? metaId.equals(that.metaId) : that.metaId == null;
    }

    @Override
    public int hashCode() {
        return metaId != null ? metaId.hashCode() : 0;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaOrganizationId")
    public Collection<EDMAffiliation> getAffiliationsByMetaId() {
        return affiliationsByMetaId;
    }

    public void setAffiliationsByMetaId(Collection<EDMAffiliation> affiliationsByMetaId) {
        this.affiliationsByMetaId = affiliationsByMetaId;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaPersonId")
    public Collection<EDMContactpoint> getContactpointsByMetaId() {
        return contactpointsByMetaId;
    }

    public void setContactpointsByMetaId(Collection<EDMContactpoint> contactpointsByMetaId) {
        this.contactpointsByMetaId = contactpointsByMetaId;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaOrganizationId")
    public Collection<EDMContactpoint> getContactpointsByMetaId_0() {
        return contactpointsByMetaId_0;
    }

    public void setContactpointsByMetaId_0(Collection<EDMContactpoint> contactpointsByMetaId_0) {
        this.contactpointsByMetaId_0 = contactpointsByMetaId_0;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaId")
    public Collection<EDMContactpoint> getContactpointsByMetaId_1() {
        return contactpointsByMetaId_1;
    }

    public void setContactpointsByMetaId_1(Collection<EDMContactpoint> contactpointsByMetaId_1) {
        this.contactpointsByMetaId_1 = contactpointsByMetaId_1;
    }

    @OneToMany(mappedBy = "edmEntityIdByEditorMetaId")
    public Collection<EDMContactpoint> getContactpointsByMetaId_2() {
        return contactpointsByMetaId_2;
    }

    public void setContactpointsByMetaId_2(Collection<EDMContactpoint> contactpointsByMetaId_2) {
        this.contactpointsByMetaId_2 = contactpointsByMetaId_2;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaPersonId")
    public Collection<EDMContributor> getContributorsByMetaId() {
        return contributorsByMetaId;
    }

    public void setContributorsByMetaId(Collection<EDMContributor> contributorsByMetaId) {
        this.contributorsByMetaId = contributorsByMetaId;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaId")
    public Collection<EDMDataproduct> getDataproductsByMetaId() {
        return dataproductsByMetaId;
    }

    public void setDataproductsByMetaId(Collection<EDMDataproduct> dataproductsByMetaId) {
        this.dataproductsByMetaId = dataproductsByMetaId;
    }

    @OneToMany(mappedBy = "edmEntityIdByEditorMetaId")
    public Collection<EDMDataproduct> getDataproductsByMetaId_0() {
        return dataproductsByMetaId_0;
    }

    public void setDataproductsByMetaId_0(Collection<EDMDataproduct> dataproductsByMetaId_0) {
        this.dataproductsByMetaId_0 = dataproductsByMetaId_0;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaDataproviderId")
    public Collection<EDMDataproductImplementationStatus> getDataproductImplementationStatusesByMetaId() {
        return dataproductImplementationStatusesByMetaId;
    }

    public void setDataproductImplementationStatusesByMetaId(Collection<EDMDataproductImplementationStatus> dataproductImplementationStatusesByMetaId) {
        this.dataproductImplementationStatusesByMetaId = dataproductImplementationStatusesByMetaId;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaDataproviderId_0")
    public Collection<EDMDataproductImplementationStatus> getDataproductImplementationStatusesByMetaId_0() {
        return dataproductImplementationStatusesByMetaId_0;
    }

    public void setDataproductImplementationStatusesByMetaId_0(Collection<EDMDataproductImplementationStatus> dataproductImplementationStatusesByMetaId_0) {
        this.dataproductImplementationStatusesByMetaId_0 = dataproductImplementationStatusesByMetaId_0;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaId")
    public Collection<EDMDataproductImplementationStatus> getDataproductImplementationStatusesByMetaId_1() {
        return dataproductImplementationStatusesByMetaId_1;
    }

    public void setDataproductImplementationStatusesByMetaId_1(Collection<EDMDataproductImplementationStatus> dataproductImplementationStatusesByMetaId_1) {
        this.dataproductImplementationStatusesByMetaId_1 = dataproductImplementationStatusesByMetaId_1;
    }

    @OneToMany(mappedBy = "edmEntityIdByEditorMetaId")
    public Collection<EDMDataproductImplementationStatus> getDataproductImplementationStatusesByMetaId_2() {
        return dataproductImplementationStatusesByMetaId_2;
    }

    public void setDataproductImplementationStatusesByMetaId_2(Collection<EDMDataproductImplementationStatus> dataproductImplementationStatusesByMetaId_2) {
        this.dataproductImplementationStatusesByMetaId_2 = dataproductImplementationStatusesByMetaId_2;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaId")
    public Collection<EDMDistribution> getDistributionsByMetaId_0() {
        return distributionsByMetaId_0;
    }

    public void setDistributionsByMetaId_0(Collection<EDMDistribution> distributionsByMetaId_0) {
        this.distributionsByMetaId_0 = distributionsByMetaId_0;
    }

    @OneToMany(mappedBy = "edmEntityIdByEditorMetaId")
    public Collection<EDMDistribution> getDistributionsByMetaId_1() {
        return distributionsByMetaId_1;
    }

    public void setDistributionsByMetaId_1(Collection<EDMDistribution> distributionsByMetaId_1) {
        this.distributionsByMetaId_1 = distributionsByMetaId_1;
    }

    @OneToMany(mappedBy = "edmEntityIdByManufacturer")
    public Collection<EDMEquipment> getEquipmentByMetaId() {
        return equipmentByMetaId;
    }

    public void setEquipmentByMetaId(Collection<EDMEquipment> equipmentByMetaId) {
        this.equipmentByMetaId = equipmentByMetaId;
    }

    @OneToMany(mappedBy = "edmEntityIdByOwner")
    public Collection<EDMEquipment> getEquipmentByMetaId_0() {
        return equipmentByMetaId_0;
    }

    public void setEquipmentByMetaId_0(Collection<EDMEquipment> equipmentByMetaId_0) {
        this.equipmentByMetaId_0 = equipmentByMetaId_0;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaId")
    public Collection<EDMEquipment> getEquipmentByMetaId_1() {
        return equipmentByMetaId_1;
    }

    public void setEquipmentByMetaId_1(Collection<EDMEquipment> equipmentByMetaId_1) {
        this.equipmentByMetaId_1 = equipmentByMetaId_1;
    }

    @OneToMany(mappedBy = "edmEntityIdByEditorMetaId")
    public Collection<EDMEquipment> getEquipmentByMetaId_2() {
        return equipmentByMetaId_2;
    }

    public void setEquipmentByMetaId_2(Collection<EDMEquipment> equipmentByMetaId_2) {
        this.equipmentByMetaId_2 = equipmentByMetaId_2;
    }

    @OneToMany(mappedBy = "edmEntityIdByOwner")
    public Collection<EDMFacility> getFacilitiesByMetaId() {
        return facilitiesByMetaId;
    }

    public void setFacilitiesByMetaId(Collection<EDMFacility> facilitiesByMetaId) {
        this.facilitiesByMetaId = facilitiesByMetaId;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaId")
    public Collection<EDMFacility> getFacilitiesByMetaId_0() {
        return facilitiesByMetaId_0;
    }

    public void setFacilitiesByMetaId_0(Collection<EDMFacility> facilitiesByMetaId_0) {
        this.facilitiesByMetaId_0 = facilitiesByMetaId_0;
    }

    @OneToMany(mappedBy = "edmEntityIdByEditorMetaId")
    public Collection<EDMFacility> getFacilitiesByMetaId_1() {
        return facilitiesByMetaId_1;
    }

    public void setFacilitiesByMetaId_1(Collection<EDMFacility> facilitiesByMetaId_1) {
        this.facilitiesByMetaId_1 = facilitiesByMetaId_1;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaPersonId")
    public Collection<EDMGroupUser> getGroupUsersByMetaId() {
        return groupUsersByMetaId;
    }

    public void setGroupUsersByMetaId(Collection<EDMGroupUser> groupUsersByMetaId) {
        this.groupUsersByMetaId = groupUsersByMetaId;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaId")
    public Collection<EDMOperation> getOperationsByMetaId() {
        return operationsByMetaId;
    }

    public void setOperationsByMetaId(Collection<EDMOperation> operationsByMetaId) {
        this.operationsByMetaId = operationsByMetaId;
    }

    @OneToMany(mappedBy = "edmEntityIdByEditorMetaId")
    public Collection<EDMOperation> getOperationsByMetaId_0() {
        return operationsByMetaId_0;
    }

    public void setOperationsByMetaId_0(Collection<EDMOperation> operationsByMetaId_0) {
        this.operationsByMetaId_0 = operationsByMetaId_0;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaId")
    public Collection<EDMOrganization> getOrganizationsByMetaId() {
        return organizationsByMetaId;
    }

    public void setOrganizationsByMetaId(Collection<EDMOrganization> organizationsByMetaId) {
        this.organizationsByMetaId = organizationsByMetaId;
    }

    @OneToMany(mappedBy = "edmEntityIdByEditorMetaId")
    public Collection<EDMOrganization> getOrganizationsByMetaId_0() {
        return organizationsByMetaId_0;
    }

    public void setOrganizationsByMetaId_0(Collection<EDMOrganization> organizationsByMetaId_0) {
        this.organizationsByMetaId_0 = organizationsByMetaId_0;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaId")
    public Collection<EDMPerson> getPeopleByMetaId() {
        return peopleByMetaId;
    }

    public void setPeopleByMetaId(Collection<EDMPerson> peopleByMetaId) {
        this.peopleByMetaId = peopleByMetaId;
    }

    @OneToMany(mappedBy = "edmEntityIdByEditorMetaId")
    public Collection<EDMPerson> getPeopleByMetaId_0() {
        return peopleByMetaId_0;
    }

    public void setPeopleByMetaId_0(Collection<EDMPerson> peopleByMetaId_0) {
        this.peopleByMetaId_0 = peopleByMetaId_0;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaId")
    public Collection<EDMPublication> getPublicationsByMetaId_1() {
        return publicationsByMetaId_1;
    }

    public void setPublicationsByMetaId_1(Collection<EDMPublication> publicationsByMetaId_1) {
        this.publicationsByMetaId_1 = publicationsByMetaId_1;
    }

    @OneToMany(mappedBy = "edmEntityIdByEditorMetaId")
    public Collection<EDMPublication> getPublicationsByMetaId_2() {
        return publicationsByMetaId_2;
    }

    public void setPublicationsByMetaId_2(Collection<EDMPublication> publicationsByMetaId_2) {
        this.publicationsByMetaId_2 = publicationsByMetaId_2;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaOrganizationId")
    public Collection<EDMPublisher> getPublishersByMetaId() {
        return publishersByMetaId;
    }

    public void setPublishersByMetaId(Collection<EDMPublisher> publishersByMetaId) {
        this.publishersByMetaId = publishersByMetaId;
    }

    @OneToMany(mappedBy = "edmEntityIdByProvider")
    public Collection<EDMService> getServicesByMetaId() {
        return servicesByMetaId;
    }

    public void setServicesByMetaId(Collection<EDMService> servicesByMetaId) {
        this.servicesByMetaId = servicesByMetaId;
    }

    @OneToMany(mappedBy = "edmEntityIdByServicecontract")
    public Collection<EDMService> getServicesByMetaId_0() {
        return servicesByMetaId_0;
    }

    public void setServicesByMetaId_0(Collection<EDMService> servicesByMetaId_0) {
        this.servicesByMetaId_0 = servicesByMetaId_0;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaId")
    public Collection<EDMService> getServicesByMetaId_1() {
        return servicesByMetaId_1;
    }

    public void setServicesByMetaId_1(Collection<EDMService> servicesByMetaId_1) {
        this.servicesByMetaId_1 = servicesByMetaId_1;
    }

    @OneToMany(mappedBy = "edmEntityIdByEditorMetaId")
    public Collection<EDMService> getServicesByMetaId_2() {
        return servicesByMetaId_2;
    }

    public void setServicesByMetaId_2(Collection<EDMService> servicesByMetaId_2) {
        this.servicesByMetaId_2 = servicesByMetaId_2;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaServiceproviderId")
    public Collection<EDMServiceImplementationStatus> getServiceImplementationStatusesByMetaId() {
        return serviceImplementationStatusesByMetaId;
    }

    public void setServiceImplementationStatusesByMetaId(Collection<EDMServiceImplementationStatus> serviceImplementationStatusesByMetaId) {
        this.serviceImplementationStatusesByMetaId = serviceImplementationStatusesByMetaId;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaServiceId")
    public Collection<EDMServiceImplementationStatus> getServiceImplementationStatusesByMetaId_0() {
        return serviceImplementationStatusesByMetaId_0;
    }

    public void setServiceImplementationStatusesByMetaId_0(Collection<EDMServiceImplementationStatus> serviceImplementationStatusesByMetaId_0) {
        this.serviceImplementationStatusesByMetaId_0 = serviceImplementationStatusesByMetaId_0;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaId")
    public Collection<EDMServiceImplementationStatus> getServiceImplementationStatusesByMetaId_1() {
        return serviceImplementationStatusesByMetaId_1;
    }

    public void setServiceImplementationStatusesByMetaId_1(Collection<EDMServiceImplementationStatus> serviceImplementationStatusesByMetaId_1) {
        this.serviceImplementationStatusesByMetaId_1 = serviceImplementationStatusesByMetaId_1;
    }

    @OneToMany(mappedBy = "edmEntityIdByEditorMetaId")
    public Collection<EDMServiceImplementationStatus> getServiceImplementationStatusesByMetaId_2() {
        return serviceImplementationStatusesByMetaId_2;
    }

    public void setServiceImplementationStatusesByMetaId_2(Collection<EDMServiceImplementationStatus> serviceImplementationStatusesByMetaId_2) {
        this.serviceImplementationStatusesByMetaId_2 = serviceImplementationStatusesByMetaId_2;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaId")
    public Collection<EDMSoftwareapplication> getSoftwareapplicationsByMetaId() {
        return softwareapplicationsByMetaId;
    }

    public void setSoftwareapplicationsByMetaId(Collection<EDMSoftwareapplication> softwareapplicationsByMetaId) {
        this.softwareapplicationsByMetaId = softwareapplicationsByMetaId;
    }

    @OneToMany(mappedBy = "edmEntityIdByEditorMetaId")
    public Collection<EDMSoftwareapplication> getSoftwareapplicationsByMetaId_0() {
        return softwareapplicationsByMetaId_0;
    }

    public void setSoftwareapplicationsByMetaId_0(Collection<EDMSoftwareapplication> softwareapplicationsByMetaId_0) {
        this.softwareapplicationsByMetaId_0 = softwareapplicationsByMetaId_0;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaId")
    public Collection<EDMSoftwaresourcecode> getSoftwaresourcecodesByMetaId() {
        return softwaresourcecodesByMetaId;
    }

    public void setSoftwaresourcecodesByMetaId(Collection<EDMSoftwaresourcecode> softwaresourcecodesByMetaId) {
        this.softwaresourcecodesByMetaId = softwaresourcecodesByMetaId;
    }

    @OneToMany(mappedBy = "edmEntityIdByEditorMetaId")
    public Collection<EDMSoftwaresourcecode> getSoftwaresourcecodesByMetaId_0() {
        return softwaresourcecodesByMetaId_0;
    }

    public void setSoftwaresourcecodesByMetaId_0(Collection<EDMSoftwaresourcecode> softwaresourcecodesByMetaId_0) {
        this.softwaresourcecodesByMetaId_0 = softwaresourcecodesByMetaId_0;
    }

    @OneToMany(mappedBy = "edmEntityIdByProvider")
    public Collection<EDMWebservice> getWebservicesByMetaId() {
        return webservicesByMetaId;
    }

    public void setWebservicesByMetaId(Collection<EDMWebservice> webservicesByMetaId) {
        this.webservicesByMetaId = webservicesByMetaId;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaId")
    public Collection<EDMWebservice> getWebservicesByMetaId_0() {
        return webservicesByMetaId_0;
    }

    public void setWebservicesByMetaId_0(Collection<EDMWebservice> webservicesByMetaId_0) {
        this.webservicesByMetaId_0 = webservicesByMetaId_0;
    }

    @OneToMany(mappedBy = "edmEntityIdByEditorMetaId")
    public Collection<EDMWebservice> getWebservicesByMetaId_1() {
        return webservicesByMetaId_1;
    }

    public void setWebservicesByMetaId_1(Collection<EDMWebservice> webservicesByMetaId_1) {
        this.webservicesByMetaId_1 = webservicesByMetaId_1;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaId")
    public Collection<EDMContract> getContractsByMetaId() {
        return contractsByMetaId;
    }

    public void setContractsByMetaId(Collection<EDMContract> contractsByMetaId) {
        this.contractsByMetaId = contractsByMetaId;
    }

    @OneToMany(mappedBy = "edmEntityIdByEditorMetaId")
    public Collection<EDMContract> getContractsByMetaId_0() {
        return contractsByMetaId_0;
    }

    public void setContractsByMetaId_0(Collection<EDMContract> contractsByMetaId_0) {
        this.contractsByMetaId_0 = contractsByMetaId_0;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaId")
    @JoinColumn(name = "meta_id")
    public Collection<EDMPersonLite> getPeopleByMetaIdLite() {
        return peopleByMetaIdLite;
    }

    public EDMEdmEntityId setPeopleByMetaIdLite(Collection<EDMPersonLite> peopleByMetaIdLite) {
        this.peopleByMetaIdLite = peopleByMetaIdLite;
        return this;
    }

    @OneToMany(mappedBy = "edmEntityIdByMetaId")
    @JoinColumn(name = "meta_id")
    public Collection<EDMAuthorization> getAuthorizationsByMetaId() {
        return authorizationsByMetaId;
    }

    public void setAuthorizationsByMetaId(Collection<EDMAuthorization> authorizationsByMetaId) {
        this.authorizationsByMetaId = authorizationsByMetaId;
    }
}
