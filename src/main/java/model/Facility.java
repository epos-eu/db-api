package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class Facility {
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
    @Column(name = "identifier", nullable = true, length = 1024)
    private String identifier;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @Basic
    @Column(name = "title", nullable = true, length = 1024)
    private String title;
    @Basic
    @Column(name = "type", nullable = true, length = 1024)
    private String type;
    @Basic
    @Column(name = "keywords", nullable = true, length = -1)
    private String keywords;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @OneToMany(mappedBy = "facilityByFacilityInstanceId")
    private Collection<FacilityAddress> facilityAddressesByInstanceId;
    @OneToMany(mappedBy = "facilityByFacilityInstanceId")
    private Collection<FacilityCategory> facilityCategoriesByInstanceId;
    @OneToMany(mappedBy = "facilityByFacilityInstanceId")
    private Collection<FacilityContactpoint> facilityContactpointsByInstanceId;
    @OneToMany(mappedBy = "facilityByFacilityInstanceId")
    private Collection<FacilityElement> facilityElementsByInstanceId;
    @OneToMany(mappedBy = "facilityByFacility1InstanceId")
    private Collection<FacilityIspartof> facilityIspartofsByInstanceId;
    @OneToMany(mappedBy = "facilityByFacility2InstanceId")
    private Collection<FacilityIspartof> facilityIspartofsByInstanceId_0;
    @OneToMany(mappedBy = "facilityByFacilityInstanceId")
    private Collection<FacilitySpatial> facilitySpatialsByInstanceId;

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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Facility facility = (Facility) o;

        if (instanceId != null ? !instanceId.equals(facility.instanceId) : facility.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(facility.metaId) : facility.metaId != null) return false;
        if (uid != null ? !uid.equals(facility.uid) : facility.uid != null) return false;
        if (versionId != null ? !versionId.equals(facility.versionId) : facility.versionId != null) return false;
        if (identifier != null ? !identifier.equals(facility.identifier) : facility.identifier != null) return false;
        if (description != null ? !description.equals(facility.description) : facility.description != null)
            return false;
        if (title != null ? !title.equals(facility.title) : facility.title != null) return false;
        if (type != null ? !type.equals(facility.type) : facility.type != null) return false;
        if (keywords != null ? !keywords.equals(facility.keywords) : facility.keywords != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        return result;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Collection<FacilityAddress> getFacilityAddressesByInstanceId() {
        return facilityAddressesByInstanceId;
    }

    public void setFacilityAddressesByInstanceId(Collection<FacilityAddress> facilityAddressesByInstanceId) {
        this.facilityAddressesByInstanceId = facilityAddressesByInstanceId;
    }

    public Collection<FacilityCategory> getFacilityCategoriesByInstanceId() {
        return facilityCategoriesByInstanceId;
    }

    public void setFacilityCategoriesByInstanceId(Collection<FacilityCategory> facilityCategoriesByInstanceId) {
        this.facilityCategoriesByInstanceId = facilityCategoriesByInstanceId;
    }

    public Collection<FacilityContactpoint> getFacilityContactpointsByInstanceId() {
        return facilityContactpointsByInstanceId;
    }

    public void setFacilityContactpointsByInstanceId(Collection<FacilityContactpoint> facilityContactpointsByInstanceId) {
        this.facilityContactpointsByInstanceId = facilityContactpointsByInstanceId;
    }

    public Collection<FacilityElement> getFacilityElementsByInstanceId() {
        return facilityElementsByInstanceId;
    }

    public void setFacilityElementsByInstanceId(Collection<FacilityElement> facilityElementsByInstanceId) {
        this.facilityElementsByInstanceId = facilityElementsByInstanceId;
    }

    public Collection<FacilityIspartof> getFacilityIspartofsByInstanceId() {
        return facilityIspartofsByInstanceId;
    }

    public void setFacilityIspartofsByInstanceId(Collection<FacilityIspartof> facilityIspartofsByInstanceId) {
        this.facilityIspartofsByInstanceId = facilityIspartofsByInstanceId;
    }

    public Collection<FacilityIspartof> getFacilityIspartofsByInstanceId_0() {
        return facilityIspartofsByInstanceId_0;
    }

    public void setFacilityIspartofsByInstanceId_0(Collection<FacilityIspartof> facilityIspartofsByInstanceId_0) {
        this.facilityIspartofsByInstanceId_0 = facilityIspartofsByInstanceId_0;
    }

    public Collection<FacilitySpatial> getFacilitySpatialsByInstanceId() {
        return facilitySpatialsByInstanceId;
    }

    public void setFacilitySpatialsByInstanceId(Collection<FacilitySpatial> facilitySpatialsByInstanceId) {
        this.facilitySpatialsByInstanceId = facilitySpatialsByInstanceId;
    }
}
