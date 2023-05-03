package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "equipment")
@NamedQueries({
        @NamedQuery(name = "equipment.findAll", query = "SELECT c FROM EDMEquipment c"),
        @NamedQuery(name = "equipment.findAllByMetaId", query = "SELECT c FROM EDMEquipment c where c.metaId = :METAID"),
        @NamedQuery(name = "equipment.findAllByState", query = "SELECT c FROM EDMEquipment c where c.state = :STATE"),
        @NamedQuery(name = "equipment.findByUidAndState", query = "select c from EDMEquipment c where c.uid = :UID and c.state = :STATE"),
        @NamedQuery(name = "equipment.findByUid", query = "select c from EDMEquipment c where c.uid = :UID"),
        @NamedQuery(name = "equipment.findByFileProvenance", query = "select c from EDMEquipment c where c.fileprovenance = :FPROV"),
        @NamedQuery(name = "equipment.findByInstanceId", query = "select c from EDMEquipment c where c.instanceId = :INSTANCEID")
})
public class EDMEquipment {
    private String uid;
    private String description;
    private String name;
    private String type;
    private String manufacturer;
    private String pageurl;
    private String filter;
    private String dynamicrange;
    private String orientation;
    private String resolution;
    private String sampleperiod;
    private String owner;
    private String serialnumber;
    private String fileprovenance;
    private String instanceId;
    private String metaId;
    private String instanceChangedId;
    private Timestamp changeTimestamp;
    private String operation;
    private String editorMetaId;
    private String changeComment;
    private String version;
    private String state;
    private Boolean toBeDeleted;
    private Collection<EDMContactpointEquipment> contactpointEquipmentsByInstanceId;
    private EDMEdmEntityId edmEntityIdByManufacturer;
    private EDMQuantitativevalue quantitativevalueByDynamicrange;
    private EDMEdmEntityId edmEntityIdByOwner;
    private EDMEdmEntityId edmEntityIdByMetaId;
    private EDMEquipment equipmentByInstanceChangedId;
    private Collection<EDMEquipment> equipmentByInstanceId;
    private EDMEdmEntityId edmEntityIdByEditorMetaId;
    private Collection<EDMEquipmentCategory> equipmentCategoriesByInstanceId;
    private Collection<EDMEquipmentEquipment> equipmentEquipmentsByInstanceId;
    private Collection<EDMEquipmentEquipment> equipmentEquipmentsByInstanceId_0;
    private Collection<EDMEquipmentFacility> equipmentFacilitiesByInstanceId;
    private Collection<EDMEquipmentSpatial> equipmentSpatialsByInstanceId;
    private Collection<EDMEquipmentTemporal> equipmentTemporalsByInstanceId;

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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "manufacturer", insertable = false, updatable = false)
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Basic
    @Column(name = "pageurl")
    public String getPageurl() {
        return pageurl;
    }

    public void setPageurl(String pageurl) {
        this.pageurl = pageurl;
    }

    @Basic
    @Column(name = "filter")
    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    @Basic
    @Column(name = "dynamicrange", insertable = false, updatable = false)
    public String getDynamicrange() {
        return dynamicrange;
    }

    public void setDynamicrange(String dynamicrange) {
        this.dynamicrange = dynamicrange;
    }

    @Basic
    @Column(name = "orientation")
    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    @Basic
    @Column(name = "resolution")
    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @Basic
    @Column(name = "sampleperiod")
    public String getSampleperiod() {
        return sampleperiod;
    }

    public void setSampleperiod(String sampleperiod) {
        this.sampleperiod = sampleperiod;
    }

    @Basic
    @Column(name = "owner", insertable = false, updatable = false)
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "serialnumber")
    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    @Basic
    @Column(name = "fileprovenance")
    public String getFileprovenance() {
        return fileprovenance;
    }

    public void setFileprovenance(String fileprovenance) {
        this.fileprovenance = fileprovenance;
    }

    @Id
    @Column(name = "instance_id")
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Basic
    @Column(name = "meta_id", insertable = false, updatable = false)
    public String getMetaId() {
        return metaId;
    }

    public void setMetaId(String metaId) {
        this.metaId = metaId;
    }

    @Basic
    @Column(name = "instance_changed_id", insertable = false, updatable = false)
    public String getInstanceChangedId() {
        return instanceChangedId;
    }

    public void setInstanceChangedId(String instanceChangedId) {
        this.instanceChangedId = instanceChangedId;
    }

    @Basic
    @Column(name = "change_timestamp", nullable = true)
    public Timestamp getChangeTimestamp() {
        return changeTimestamp;
    }

    public void setChangeTimestamp(Timestamp changeTimestamp) {
        this.changeTimestamp = changeTimestamp;
    }

    @Basic
    @Column(name = "operation")
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Basic
    @Column(name = "editor_meta_id", insertable = false, updatable = false)
    public String getEditorMetaId() {
        return editorMetaId;
    }

    public void setEditorMetaId(String editorMetaId) {
        this.editorMetaId = editorMetaId;
    }

    @Basic
    @Column(name = "change_comment")
    public String getChangeComment() {
        return changeComment;
    }

    public void setChangeComment(String changeComment) {
        this.changeComment = changeComment;
    }

    @Basic
    @Column(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "to_be_deleted", nullable = true)
    public Boolean getToBeDeleted() {
        return toBeDeleted;
    }

    public void setToBeDeleted(Boolean toBeDeleted) {
        this.toBeDeleted = toBeDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMEquipment that = (EDMEquipment) o;

        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (manufacturer != null ? !manufacturer.equals(that.manufacturer) : that.manufacturer != null) return false;
        if (pageurl != null ? !pageurl.equals(that.pageurl) : that.pageurl != null) return false;
        if (filter != null ? !filter.equals(that.filter) : that.filter != null) return false;
        if (dynamicrange != null ? !dynamicrange.equals(that.dynamicrange) : that.dynamicrange != null) return false;
        if (orientation != null ? !orientation.equals(that.orientation) : that.orientation != null) return false;
        if (resolution != null ? !resolution.equals(that.resolution) : that.resolution != null) return false;
        if (sampleperiod != null ? !sampleperiod.equals(that.sampleperiod) : that.sampleperiod != null) return false;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
        if (serialnumber != null ? !serialnumber.equals(that.serialnumber) : that.serialnumber != null) return false;
        if (fileprovenance != null ? !fileprovenance.equals(that.fileprovenance) : that.fileprovenance != null)
            return false;
        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(that.metaId) : that.metaId != null) return false;
        if (instanceChangedId != null ? !instanceChangedId.equals(that.instanceChangedId) : that.instanceChangedId != null)
            return false;
        if (changeTimestamp != null ? !changeTimestamp.equals(that.changeTimestamp) : that.changeTimestamp != null)
            return false;
        if (operation != null ? !operation.equals(that.operation) : that.operation != null) return false;
        if (editorMetaId != null ? !editorMetaId.equals(that.editorMetaId) : that.editorMetaId != null) return false;
        if (changeComment != null ? !changeComment.equals(that.changeComment) : that.changeComment != null)
            return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        return toBeDeleted != null ? toBeDeleted.equals(that.toBeDeleted) : that.toBeDeleted == null;
    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (pageurl != null ? pageurl.hashCode() : 0);
        result = 31 * result + (filter != null ? filter.hashCode() : 0);
        result = 31 * result + (dynamicrange != null ? dynamicrange.hashCode() : 0);
        result = 31 * result + (orientation != null ? orientation.hashCode() : 0);
        result = 31 * result + (resolution != null ? resolution.hashCode() : 0);
        result = 31 * result + (sampleperiod != null ? sampleperiod.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (serialnumber != null ? serialnumber.hashCode() : 0);
        result = 31 * result + (fileprovenance != null ? fileprovenance.hashCode() : 0);
        result = 31 * result + (instanceId != null ? instanceId.hashCode() : 0);
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (instanceChangedId != null ? instanceChangedId.hashCode() : 0);
        result = 31 * result + (changeTimestamp != null ? changeTimestamp.hashCode() : 0);
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        result = 31 * result + (editorMetaId != null ? editorMetaId.hashCode() : 0);
        result = 31 * result + (changeComment != null ? changeComment.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (toBeDeleted != null ? toBeDeleted.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "equipmentByInstanceEquipmentId", cascade = CascadeType.ALL)
    public Collection<EDMContactpointEquipment> getContactpointEquipmentsByInstanceId() {
        return contactpointEquipmentsByInstanceId;
    }

    public void setContactpointEquipmentsByInstanceId(Collection<EDMContactpointEquipment> contactpointEquipmentsByInstanceId) {
        this.contactpointEquipmentsByInstanceId = contactpointEquipmentsByInstanceId;
    }

    @ManyToOne
    @JoinColumn(name = "manufacturer", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByManufacturer() {
        return edmEntityIdByManufacturer;
    }

    public void setEdmEntityIdByManufacturer(EDMEdmEntityId edmEntityIdByManufacturer) {
        this.edmEntityIdByManufacturer = edmEntityIdByManufacturer;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dynamicrange", referencedColumnName = "id")
    public EDMQuantitativevalue getQuantitativevalueByDynamicrange() {
        return quantitativevalueByDynamicrange;
    }

    public void setQuantitativevalueByDynamicrange(EDMQuantitativevalue quantitativevalueByDynamicrange) {
        this.quantitativevalueByDynamicrange = quantitativevalueByDynamicrange;
    }

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByOwner() {
        return edmEntityIdByOwner;
    }

    public void setEdmEntityIdByOwner(EDMEdmEntityId edmEntityIdByOwner) {
        this.edmEntityIdByOwner = edmEntityIdByOwner;
    }

    @ManyToOne
    @JoinColumn(name = "meta_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByMetaId() {
        return edmEntityIdByMetaId;
    }

    public void setEdmEntityIdByMetaId(EDMEdmEntityId edmEntityIdByMetaId) {
        this.edmEntityIdByMetaId = edmEntityIdByMetaId;
    }

    @ManyToOne
    @JoinColumn(name = "instance_changed_id", referencedColumnName = "instance_id")
    public EDMEquipment getEquipmentByInstanceChangedId() {
        return equipmentByInstanceChangedId;
    }

    public void setEquipmentByInstanceChangedId(EDMEquipment equipmentByInstanceChangedId) {
        this.equipmentByInstanceChangedId = equipmentByInstanceChangedId;
    }

    @OneToMany(mappedBy = "equipmentByInstanceChangedId")
    public Collection<EDMEquipment> getEquipmentByInstanceId() {
        return equipmentByInstanceId;
    }

    public void setEquipmentByInstanceId(Collection<EDMEquipment> equipmentByInstanceId) {
        this.equipmentByInstanceId = equipmentByInstanceId;
    }

    @ManyToOne
    @JoinColumn(name = "editor_meta_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByEditorMetaId() {
        return edmEntityIdByEditorMetaId;
    }

    public void setEdmEntityIdByEditorMetaId(EDMEdmEntityId edmEntityIdByEditorMetaId) {
        this.edmEntityIdByEditorMetaId = edmEntityIdByEditorMetaId;
    }

    @OneToMany(mappedBy = "equipmentByInstanceEquipmentId", cascade = CascadeType.ALL)
    public Collection<EDMEquipmentCategory> getEquipmentCategoriesByInstanceId() {
        return equipmentCategoriesByInstanceId;
    }

    public void setEquipmentCategoriesByInstanceId(Collection<EDMEquipmentCategory> equipmentCategoriesByInstanceId) {
        this.equipmentCategoriesByInstanceId = equipmentCategoriesByInstanceId;
    }

    @OneToMany(mappedBy = "equipmentByInstanceEquipment1Id", cascade = CascadeType.ALL)
    public Collection<EDMEquipmentEquipment> getEquipmentEquipmentsByInstanceId() {
        return equipmentEquipmentsByInstanceId;
    }

    public void setEquipmentEquipmentsByInstanceId(Collection<EDMEquipmentEquipment> equipmentEquipmentsByInstanceId) {
        this.equipmentEquipmentsByInstanceId = equipmentEquipmentsByInstanceId;
    }

    @OneToMany(mappedBy = "equipmentByInstanceEquipmentId", cascade = CascadeType.ALL)
    public Collection<EDMEquipmentFacility> getEquipmentFacilitiesByInstanceId() {
        return equipmentFacilitiesByInstanceId;
    }

    public void setEquipmentFacilitiesByInstanceId(Collection<EDMEquipmentFacility> equipmentFacilitiesByInstanceId) {
        this.equipmentFacilitiesByInstanceId = equipmentFacilitiesByInstanceId;
    }

    @OneToMany(mappedBy = "equipmentByInstanceEquipmentId", cascade = CascadeType.ALL)
    public Collection<EDMEquipmentSpatial> getEquipmentSpatialsByInstanceId() {
        return equipmentSpatialsByInstanceId;
    }

    public void setEquipmentSpatialsByInstanceId(Collection<EDMEquipmentSpatial> equipmentSpatialsByInstanceId) {
        this.equipmentSpatialsByInstanceId = equipmentSpatialsByInstanceId;
    }

    @OneToMany(mappedBy = "equipmentByInstanceEquipmentId", cascade = CascadeType.ALL)
    public Collection<EDMEquipmentTemporal> getEquipmentTemporalsByInstanceId() {
        return equipmentTemporalsByInstanceId;
    }

    public void setEquipmentTemporalsByInstanceId(Collection<EDMEquipmentTemporal> equipmentTemporalsByInstanceId) {
        this.equipmentTemporalsByInstanceId = equipmentTemporalsByInstanceId;
    }

    @OneToMany(mappedBy = "equipmentByInstanceEquipment2Id", cascade = CascadeType.ALL)
    public Collection<EDMEquipmentEquipment> getEquipmentEquipmentsByInstanceId_0() {
        return equipmentEquipmentsByInstanceId_0;
    }

    public void setEquipmentEquipmentsByInstanceId_0(Collection<EDMEquipmentEquipment> equipmentEquipmentsByInstanceId_0) {
        this.equipmentEquipmentsByInstanceId_0 = equipmentEquipmentsByInstanceId_0;
    }
}
