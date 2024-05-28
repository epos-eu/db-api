package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class Category {
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
    @Column(name = "in_scheme", nullable = true, length = 100)
    private String inScheme;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @Basic
    @Column(name = "name", nullable = true, length = 255)
    private String name;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "in_scheme", referencedColumnName = "instance_id")
    private CategoryScheme categorySchemeByInScheme;
    @OneToMany(mappedBy = "categoryByCategoryInstanceId")
    private Collection<CategoryHastopconcept> categoryHastopconceptsByInstanceId;
    @OneToMany(mappedBy = "categoryByCategory1InstanceId")
    private Collection<CategoryIspartof> categoryIspartofsByInstanceId;
    @OneToMany(mappedBy = "categoryByCategory2InstanceId")
    private Collection<CategoryIspartof> categoryIspartofsByInstanceId_0;
    @OneToMany(mappedBy = "categoryByCategoryInstanceId")
    private Collection<DataproductCategory> dataproductCategoriesByInstanceId;
    @OneToMany(mappedBy = "categoryByCategoryInstanceId")
    private Collection<EquipmentCategory> equipmentCategoriesByInstanceId;
    @OneToMany(mappedBy = "categoryByCategoryInstanceId")
    private Collection<FacilityCategory> facilityCategoriesByInstanceId;
    @OneToMany(mappedBy = "categoryByCategoryInstanceId")
    private Collection<PublicationCategory> publicationCategoriesByInstanceId;
    @OneToMany(mappedBy = "categoryByCategoryInstanceId")
    private Collection<ServiceCategory> serviceCategoriesByInstanceId;
    @OneToMany(mappedBy = "categoryByCategoryInstanceId")
    private Collection<SoftwareapplicationCategory> softwareapplicationCategoriesByInstanceId;
    @OneToMany(mappedBy = "categoryByCategoryInstanceId")
    private Collection<SoftwaresourcecodeCategory> softwaresourcecodeCategoriesByInstanceId;
    @OneToMany(mappedBy = "categoryByCategoryInstanceId")
    private Collection<WebserviceCategory> webserviceCategoriesByInstanceId;

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

    public String getInScheme() {
        return inScheme;
    }

    public void setInScheme(String inScheme) {
        this.inScheme = inScheme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (instanceId != null ? !instanceId.equals(category.instanceId) : category.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(category.metaId) : category.metaId != null) return false;
        if (uid != null ? !uid.equals(category.uid) : category.uid != null) return false;
        if (versionId != null ? !versionId.equals(category.versionId) : category.versionId != null) return false;
        if (inScheme != null ? !inScheme.equals(category.inScheme) : category.inScheme != null) return false;
        if (description != null ? !description.equals(category.description) : category.description != null)
            return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (inScheme != null ? inScheme.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public CategoryScheme getCategorySchemeByInScheme() {
        return categorySchemeByInScheme;
    }

    public void setCategorySchemeByInScheme(CategoryScheme categorySchemeByInScheme) {
        this.categorySchemeByInScheme = categorySchemeByInScheme;
    }

    public Collection<CategoryHastopconcept> getCategoryHastopconceptsByInstanceId() {
        return categoryHastopconceptsByInstanceId;
    }

    public void setCategoryHastopconceptsByInstanceId(Collection<CategoryHastopconcept> categoryHastopconceptsByInstanceId) {
        this.categoryHastopconceptsByInstanceId = categoryHastopconceptsByInstanceId;
    }

    public Collection<CategoryIspartof> getCategoryIspartofsByInstanceId() {
        return categoryIspartofsByInstanceId;
    }

    public void setCategoryIspartofsByInstanceId(Collection<CategoryIspartof> categoryIspartofsByInstanceId) {
        this.categoryIspartofsByInstanceId = categoryIspartofsByInstanceId;
    }

    public Collection<CategoryIspartof> getCategoryIspartofsByInstanceId_0() {
        return categoryIspartofsByInstanceId_0;
    }

    public void setCategoryIspartofsByInstanceId_0(Collection<CategoryIspartof> categoryIspartofsByInstanceId_0) {
        this.categoryIspartofsByInstanceId_0 = categoryIspartofsByInstanceId_0;
    }

    public Collection<DataproductCategory> getDataproductCategoriesByInstanceId() {
        return dataproductCategoriesByInstanceId;
    }

    public void setDataproductCategoriesByInstanceId(Collection<DataproductCategory> dataproductCategoriesByInstanceId) {
        this.dataproductCategoriesByInstanceId = dataproductCategoriesByInstanceId;
    }

    public Collection<EquipmentCategory> getEquipmentCategoriesByInstanceId() {
        return equipmentCategoriesByInstanceId;
    }

    public void setEquipmentCategoriesByInstanceId(Collection<EquipmentCategory> equipmentCategoriesByInstanceId) {
        this.equipmentCategoriesByInstanceId = equipmentCategoriesByInstanceId;
    }

    public Collection<FacilityCategory> getFacilityCategoriesByInstanceId() {
        return facilityCategoriesByInstanceId;
    }

    public void setFacilityCategoriesByInstanceId(Collection<FacilityCategory> facilityCategoriesByInstanceId) {
        this.facilityCategoriesByInstanceId = facilityCategoriesByInstanceId;
    }

    public Collection<PublicationCategory> getPublicationCategoriesByInstanceId() {
        return publicationCategoriesByInstanceId;
    }

    public void setPublicationCategoriesByInstanceId(Collection<PublicationCategory> publicationCategoriesByInstanceId) {
        this.publicationCategoriesByInstanceId = publicationCategoriesByInstanceId;
    }

    public Collection<ServiceCategory> getServiceCategoriesByInstanceId() {
        return serviceCategoriesByInstanceId;
    }

    public void setServiceCategoriesByInstanceId(Collection<ServiceCategory> serviceCategoriesByInstanceId) {
        this.serviceCategoriesByInstanceId = serviceCategoriesByInstanceId;
    }

    public Collection<SoftwareapplicationCategory> getSoftwareapplicationCategoriesByInstanceId() {
        return softwareapplicationCategoriesByInstanceId;
    }

    public void setSoftwareapplicationCategoriesByInstanceId(Collection<SoftwareapplicationCategory> softwareapplicationCategoriesByInstanceId) {
        this.softwareapplicationCategoriesByInstanceId = softwareapplicationCategoriesByInstanceId;
    }

    public Collection<SoftwaresourcecodeCategory> getSoftwaresourcecodeCategoriesByInstanceId() {
        return softwaresourcecodeCategoriesByInstanceId;
    }

    public void setSoftwaresourcecodeCategoriesByInstanceId(Collection<SoftwaresourcecodeCategory> softwaresourcecodeCategoriesByInstanceId) {
        this.softwaresourcecodeCategoriesByInstanceId = softwaresourcecodeCategoriesByInstanceId;
    }

    public Collection<WebserviceCategory> getWebserviceCategoriesByInstanceId() {
        return webserviceCategoriesByInstanceId;
    }

    public void setWebserviceCategoriesByInstanceId(Collection<WebserviceCategory> webserviceCategoriesByInstanceId) {
        this.webserviceCategoriesByInstanceId = webserviceCategoriesByInstanceId;
    }
}
