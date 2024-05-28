package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class Equipment {
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
    @Column(name = "name", nullable = true, length = 1024)
    private String name;
    @Basic
    @Column(name = "type", nullable = true, length = 1024)
    private String type;
    @Basic
    @Column(name = "creator", nullable = true, length = 100)
    private String creator;
    @Basic
    @Column(name = "keywords", nullable = true, length = -1)
    private String keywords;
    @Basic
    @Column(name = "pageurl", nullable = true, length = 1024)
    private String pageurl;
    @Basic
    @Column(name = "filter", nullable = true, length = 1024)
    private String filter;
    @Basic
    @Column(name = "dynamicrange", nullable = true, length = 100)
    private String dynamicrange;
    @Basic
    @Column(name = "orientation", nullable = true, length = 1024)
    private String orientation;
    @Basic
    @Column(name = "resolution", nullable = true, length = 1024)
    private String resolution;
    @Basic
    @Column(name = "sampleperiod", nullable = true, length = 100)
    private String sampleperiod;
    @Basic
    @Column(name = "serialnumber", nullable = true, length = 1024)
    private String serialnumber;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "creator", referencedColumnName = "instance_id")
    private Organization organizationByCreator;
    @OneToMany(mappedBy = "equipmentByEquipmentInstanceId")
    private Collection<EquipmentCategory> equipmentCategoriesByInstanceId;
    @OneToMany(mappedBy = "equipmentByEquipmentInstanceId")
    private Collection<EquipmentContactpoint> equipmentContactpointsByInstanceId;
    @OneToMany(mappedBy = "equipmentByEquipmentInstanceId")
    private Collection<EquipmentElement> equipmentElementsByInstanceId;
    @OneToOne(mappedBy = "equipmentByEquipmentInstanceId")
    private EquipmentIspartof equipmentIspartofByInstanceId;
    @OneToOne(mappedBy = "equipmentByEquipmentInstanceId")
    private EquipmentRelation equipmentRelationByInstanceId;
    @OneToMany(mappedBy = "equipmentByEquipmentInstanceId")
    private Collection<EquipmentSpatial> equipmentSpatialsByInstanceId;
    @OneToMany(mappedBy = "equipmentByEquipmentInstanceId")
    private Collection<EquipmentTemporal> equipmentTemporalsByInstanceId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getPageurl() {
        return pageurl;
    }

    public void setPageurl(String pageurl) {
        this.pageurl = pageurl;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getDynamicrange() {
        return dynamicrange;
    }

    public void setDynamicrange(String dynamicrange) {
        this.dynamicrange = dynamicrange;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getSampleperiod() {
        return sampleperiod;
    }

    public void setSampleperiod(String sampleperiod) {
        this.sampleperiod = sampleperiod;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equipment equipment = (Equipment) o;

        if (instanceId != null ? !instanceId.equals(equipment.instanceId) : equipment.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(equipment.metaId) : equipment.metaId != null) return false;
        if (uid != null ? !uid.equals(equipment.uid) : equipment.uid != null) return false;
        if (versionId != null ? !versionId.equals(equipment.versionId) : equipment.versionId != null) return false;
        if (identifier != null ? !identifier.equals(equipment.identifier) : equipment.identifier != null) return false;
        if (description != null ? !description.equals(equipment.description) : equipment.description != null)
            return false;
        if (name != null ? !name.equals(equipment.name) : equipment.name != null) return false;
        if (type != null ? !type.equals(equipment.type) : equipment.type != null) return false;
        if (creator != null ? !creator.equals(equipment.creator) : equipment.creator != null) return false;
        if (keywords != null ? !keywords.equals(equipment.keywords) : equipment.keywords != null) return false;
        if (pageurl != null ? !pageurl.equals(equipment.pageurl) : equipment.pageurl != null) return false;
        if (filter != null ? !filter.equals(equipment.filter) : equipment.filter != null) return false;
        if (dynamicrange != null ? !dynamicrange.equals(equipment.dynamicrange) : equipment.dynamicrange != null)
            return false;
        if (orientation != null ? !orientation.equals(equipment.orientation) : equipment.orientation != null)
            return false;
        if (resolution != null ? !resolution.equals(equipment.resolution) : equipment.resolution != null) return false;
        if (sampleperiod != null ? !sampleperiod.equals(equipment.sampleperiod) : equipment.sampleperiod != null)
            return false;
        if (serialnumber != null ? !serialnumber.equals(equipment.serialnumber) : equipment.serialnumber != null)
            return false;

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
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (pageurl != null ? pageurl.hashCode() : 0);
        result = 31 * result + (filter != null ? filter.hashCode() : 0);
        result = 31 * result + (dynamicrange != null ? dynamicrange.hashCode() : 0);
        result = 31 * result + (orientation != null ? orientation.hashCode() : 0);
        result = 31 * result + (resolution != null ? resolution.hashCode() : 0);
        result = 31 * result + (sampleperiod != null ? sampleperiod.hashCode() : 0);
        result = 31 * result + (serialnumber != null ? serialnumber.hashCode() : 0);
        return result;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Organization getOrganizationByCreator() {
        return organizationByCreator;
    }

    public void setOrganizationByCreator(Organization organizationByCreator) {
        this.organizationByCreator = organizationByCreator;
    }

    public Collection<EquipmentCategory> getEquipmentCategoriesByInstanceId() {
        return equipmentCategoriesByInstanceId;
    }

    public void setEquipmentCategoriesByInstanceId(Collection<EquipmentCategory> equipmentCategoriesByInstanceId) {
        this.equipmentCategoriesByInstanceId = equipmentCategoriesByInstanceId;
    }

    public Collection<EquipmentContactpoint> getEquipmentContactpointsByInstanceId() {
        return equipmentContactpointsByInstanceId;
    }

    public void setEquipmentContactpointsByInstanceId(Collection<EquipmentContactpoint> equipmentContactpointsByInstanceId) {
        this.equipmentContactpointsByInstanceId = equipmentContactpointsByInstanceId;
    }

    public Collection<EquipmentElement> getEquipmentElementsByInstanceId() {
        return equipmentElementsByInstanceId;
    }

    public void setEquipmentElementsByInstanceId(Collection<EquipmentElement> equipmentElementsByInstanceId) {
        this.equipmentElementsByInstanceId = equipmentElementsByInstanceId;
    }

    public EquipmentIspartof getEquipmentIspartofByInstanceId() {
        return equipmentIspartofByInstanceId;
    }

    public void setEquipmentIspartofByInstanceId(EquipmentIspartof equipmentIspartofByInstanceId) {
        this.equipmentIspartofByInstanceId = equipmentIspartofByInstanceId;
    }

    public EquipmentRelation getEquipmentRelationByInstanceId() {
        return equipmentRelationByInstanceId;
    }

    public void setEquipmentRelationByInstanceId(EquipmentRelation equipmentRelationByInstanceId) {
        this.equipmentRelationByInstanceId = equipmentRelationByInstanceId;
    }

    public Collection<EquipmentSpatial> getEquipmentSpatialsByInstanceId() {
        return equipmentSpatialsByInstanceId;
    }

    public void setEquipmentSpatialsByInstanceId(Collection<EquipmentSpatial> equipmentSpatialsByInstanceId) {
        this.equipmentSpatialsByInstanceId = equipmentSpatialsByInstanceId;
    }

    public Collection<EquipmentTemporal> getEquipmentTemporalsByInstanceId() {
        return equipmentTemporalsByInstanceId;
    }

    public void setEquipmentTemporalsByInstanceId(Collection<EquipmentTemporal> equipmentTemporalsByInstanceId) {
        this.equipmentTemporalsByInstanceId = equipmentTemporalsByInstanceId;
    }
}
