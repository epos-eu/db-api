package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "category")
@NamedQueries({
        @NamedQuery(name = "EDMCategory.findAll", query = "SELECT c FROM EDMCategory c"),
        @NamedQuery(name = "EDMCategory.findByName", query = "select c from EDMCategory c where c.name = :NAME"),
        @NamedQuery(name = "EDMCategory.findByUid", query = "select c from EDMCategory c where c.uid = :UID")
})
public class EDMCategory {
    private String id;
    private String uid;
    private String description;
    private String name;
    private String inscheme;
    private Collection<EDMDataproductCategory> dataproductCategoriesById;
    private Collection<EDMEquipmentCategory> equipmentCategoriesById;
    private Collection<EDMIspartofCategory> ispartofCategoriesById;
    private Collection<EDMIspartofCategory> ispartofCategoriesById_0;
    private Collection<EDMPublicationCategory> publicationCategoriesById;
    private Collection<EDMServiceCategory> serviceCategoriesById;
    private Collection<EDMSoftwareapplicationCategory> softwareapplicationCategoriesById;
    private Collection<EDMSoftwaresourcecodeCategory> softwaresourcecodeCategoriesById;
    private Collection<EDMWebserviceCategory> webserviceCategoriesById;
    private Collection<EDMFacilityCategory> facilityCategoriesById;
    private Collection<EDMHasTopConcept> hasTopConceptCategoriesById;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @Basic
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Basic
    @Column(name = "in_scheme")
    public String getScheme() {
        return inscheme;
    }

    public void setScheme(String inscheme) {
        this.inscheme = inscheme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EDMCategory that = (EDMCategory) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, name);
    }

    @OneToMany(mappedBy = "categoryByCategoryId",fetch=FetchType.LAZY)
    public Collection<EDMDataproductCategory> getDataproductCategoriesById() {
        return dataproductCategoriesById;
    }

    public void setDataproductCategoriesById(Collection<EDMDataproductCategory> dataproductCategoriesById) {
        this.dataproductCategoriesById = dataproductCategoriesById;
    }

    @OneToMany(mappedBy = "categoryByCategoryId",fetch=FetchType.LAZY)
    public Collection<EDMEquipmentCategory> getEquipmentCategoriesById() {
        return equipmentCategoriesById;
    }

    public void setEquipmentCategoriesById(Collection<EDMEquipmentCategory> equipmentCategoriesById) {
        this.equipmentCategoriesById = equipmentCategoriesById;
    }

    @OneToMany(mappedBy = "categoryByCategory1Id",fetch=FetchType.LAZY)
    public Collection<EDMIspartofCategory> getIspartofCategoriesById() {
        return ispartofCategoriesById;
    }

    public void setIspartofCategoriesById(Collection<EDMIspartofCategory> ispartofCategoriesById) {
        this.ispartofCategoriesById = ispartofCategoriesById;
    }

    @OneToMany(mappedBy = "categoryByCategory2Id",fetch=FetchType.LAZY)
    public Collection<EDMIspartofCategory> getIspartofCategoriesById_0() {
        return ispartofCategoriesById_0;
    }

    public void setIspartofCategoriesById_0(Collection<EDMIspartofCategory> ispartofCategoriesById_0) {
        this.ispartofCategoriesById_0 = ispartofCategoriesById_0;
    }

    @OneToMany(mappedBy = "categoryByCategoryId",fetch=FetchType.LAZY)
    public Collection<EDMPublicationCategory> getPublicationCategoriesById() {
        return publicationCategoriesById;
    }

    public void setPublicationCategoriesById(Collection<EDMPublicationCategory> publicationCategoriesById) {
        this.publicationCategoriesById = publicationCategoriesById;
    }

    @OneToMany(mappedBy = "categoryByCategoryId",fetch=FetchType.LAZY)
    public Collection<EDMServiceCategory> getServiceCategoriesById() {
        return serviceCategoriesById;
    }

    public void setServiceCategoriesById(Collection<EDMServiceCategory> serviceCategoriesById) {
        this.serviceCategoriesById = serviceCategoriesById;
    }

    @OneToMany(mappedBy = "categoryByCategoryId",fetch=FetchType.LAZY)
    public Collection<EDMSoftwareapplicationCategory> getSoftwareapplicationCategoriesById() {
        return softwareapplicationCategoriesById;
    }

    public void setSoftwareapplicationCategoriesById(Collection<EDMSoftwareapplicationCategory> softwareapplicationCategoriesById) {
        this.softwareapplicationCategoriesById = softwareapplicationCategoriesById;
    }

    @OneToMany(mappedBy = "categoryByCategoryId",fetch=FetchType.LAZY)
    public Collection<EDMSoftwaresourcecodeCategory> getSoftwaresourcecodeCategoriesById() {
        return softwaresourcecodeCategoriesById;
    }

    public void setSoftwaresourcecodeCategoriesById(Collection<EDMSoftwaresourcecodeCategory> softwaresourcecodeCategoriesById) {
        this.softwaresourcecodeCategoriesById = softwaresourcecodeCategoriesById;
    }

    @OneToMany(mappedBy = "categoryByCategoryId",fetch=FetchType.LAZY)
    public Collection<EDMWebserviceCategory> getWebserviceCategoriesById() {
        return webserviceCategoriesById;
    }

    public void setWebserviceCategoriesById(Collection<EDMWebserviceCategory> webserviceCategoriesById) {
        this.webserviceCategoriesById = webserviceCategoriesById;
    }

    @OneToMany(mappedBy = "categoryByCategoryId",fetch=FetchType.LAZY)
    public Collection<EDMFacilityCategory> getFacilityCategoriesById() {
        return facilityCategoriesById;
    }

    public void setFacilityCategoriesById(Collection<EDMFacilityCategory> facilityCategoriesById) {
        this.facilityCategoriesById = facilityCategoriesById;
    }

    @OneToMany(mappedBy = "categoryByCategoryId", fetch=FetchType.LAZY)
	public Collection<EDMHasTopConcept> getHasTopConceptCategoriesById() {
		return hasTopConceptCategoriesById;
	}

	public void setHasTopConceptCategoriesById(Collection<EDMHasTopConcept> hasTopConceptCategoriesById) {
		this.hasTopConceptCategoriesById = hasTopConceptCategoriesById;
	}

}
