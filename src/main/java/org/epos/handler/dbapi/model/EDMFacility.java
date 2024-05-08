package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "facility")
@NamedQueries({
        @NamedQuery(name = "facility.findAll", query = "SELECT c FROM EDMFacility c"),
        @NamedQuery(name = "facility.findAllByMetaId", query = "SELECT c FROM EDMFacility c where c.metaId = :METAID"),
        @NamedQuery(name = "facility.findAllByState", query = "SELECT c FROM EDMFacility c where c.state = :STATE"),
        @NamedQuery(name = "facility.findByUidAndState", query = "select c from EDMFacility c where c.uid = :UID and c.state = :STATE"),
        @NamedQuery(name = "facility.findByUid", query = "select c from EDMFacility c where c.uid = :UID"),
        @NamedQuery(name = "facility.findByFileProvenance", query = "select c from EDMFacility c where c.fileprovenance = :FPROV"),
        @NamedQuery(name = "facility.findByInstanceId", query = "select c from EDMFacility c where c.instanceId = :INSTANCEID")
})
public class EDMFacility {
    private String uid;
    private String identifier;
    private String description;
    private String title;
    private String type;
    private String addressId;
    private String owner;
    private String keywords;
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
    private Collection<EDMContactpointFacility> contactpointFacilitiesByInstanceId;
    private EDMAddress addressByAddressId;
    private EDMEdmEntityId edmEntityIdByOwner;
    private EDMEdmEntityId edmEntityIdByMetaId;
    private EDMFacility facilityByInstanceChangedId;
    private Collection<EDMFacility> facilitiesByInstanceId;
    private EDMEdmEntityId edmEntityIdByEditorMetaId;
    private Collection<EDMFacilityCategory> facilityCategoriesByInstanceId;
    private Collection<EDMFacilityFacility> facilityFacilitiesByInstanceId;
    private Collection<EDMFacilityFacility> facilityFacilitiesByInstanceId_0;
    private Collection<EDMFacilityService> facilityServicesByInstanceId;
    private Collection<EDMFacilityPageurl> facilityPageurlsByInstanceId;
    private Collection<EDMFacilitySpatial> facilitySpatialsByInstanceId;
    private Collection<EDMEquipmentFacility> equipmentFacilitiesByInstanceId;

    @Basic
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    
    @Basic
    @Column(name = "identifier")
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "address_id", insertable = false, updatable = false)
    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
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
    @Column(name = "keywords")
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
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

        EDMFacility that = (EDMFacility) o;

        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (addressId != null ? !addressId.equals(that.addressId) : that.addressId != null) return false;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
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
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
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

    @OneToMany(mappedBy = "facilityByInstanceFacilityId", cascade = CascadeType.ALL)
    public Collection<EDMContactpointFacility> getContactpointFacilitiesByInstanceId() {
        return contactpointFacilitiesByInstanceId;
    }

    public void setContactpointFacilitiesByInstanceId(Collection<EDMContactpointFacility> contactpointFacilitiesByInstanceId) {
        this.contactpointFacilitiesByInstanceId = contactpointFacilitiesByInstanceId;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    public EDMAddress getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(EDMAddress addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
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
    public EDMFacility getFacilityByInstanceChangedId() {
        return facilityByInstanceChangedId;
    }

    public void setFacilityByInstanceChangedId(EDMFacility facilityByInstanceChangedId) {
        this.facilityByInstanceChangedId = facilityByInstanceChangedId;
    }

    @OneToMany(mappedBy = "facilityByInstanceChangedId")
    public Collection<EDMFacility> getFacilitiesByInstanceId() {
        return facilitiesByInstanceId;
    }

    public void setFacilitiesByInstanceId(Collection<EDMFacility> facilitiesByInstanceId) {
        this.facilitiesByInstanceId = facilitiesByInstanceId;
    }

    @ManyToOne
    @JoinColumn(name = "editor_meta_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByEditorMetaId() {
        return edmEntityIdByEditorMetaId;
    }

    public void setEdmEntityIdByEditorMetaId(EDMEdmEntityId edmEntityIdByEditorMetaId) {
        this.edmEntityIdByEditorMetaId = edmEntityIdByEditorMetaId;
    }

    @OneToMany(mappedBy = "facilityByInstanceFacilityId", cascade = CascadeType.ALL)
    public Collection<EDMFacilityCategory> getFacilityCategoriesByInstanceId() {
        return facilityCategoriesByInstanceId;
    }

    public void setFacilityCategoriesByInstanceId(Collection<EDMFacilityCategory> facilityCategoriesByInstanceId) {
        this.facilityCategoriesByInstanceId = facilityCategoriesByInstanceId;
    }

    @OneToMany(mappedBy = "facilityByInstanceFacility1Id", cascade = CascadeType.ALL)
    public Collection<EDMFacilityFacility> getFacilityFacilitiesByInstanceId() {
        return facilityFacilitiesByInstanceId;
    }

    public void setFacilityFacilitiesByInstanceId(Collection<EDMFacilityFacility> facilityFacilitiesByInstanceId) {
        this.facilityFacilitiesByInstanceId = facilityFacilitiesByInstanceId;
    }

    @OneToMany(mappedBy = "facilityByInstanceFacilityId", cascade = CascadeType.ALL)
    public Collection<EDMFacilityPageurl> getFacilityPageurlsByInstanceId() {
        return facilityPageurlsByInstanceId;
    }

    public void setFacilityPageurlsByInstanceId(Collection<EDMFacilityPageurl> facilityPageurlsByInstanceId) {
        this.facilityPageurlsByInstanceId = facilityPageurlsByInstanceId;
    }

    @OneToMany(mappedBy = "facilityByInstanceFacilityId", cascade = CascadeType.ALL)
    public Collection<EDMFacilitySpatial> getFacilitySpatialsByInstanceId() {
        return facilitySpatialsByInstanceId;
    }

    public void setFacilitySpatialsByInstanceId(Collection<EDMFacilitySpatial> facilitySpatialsByInstanceId) {
        this.facilitySpatialsByInstanceId = facilitySpatialsByInstanceId;
    }

    @OneToMany(mappedBy = "facilityByInstanceFacilityId", cascade = CascadeType.ALL)
    public Collection<EDMFacilityService> getFacilityServicesByInstanceId() {
        return facilityServicesByInstanceId;
    }

    public void setFacilityServicesByInstanceId(Collection<EDMFacilityService> facilityServicesByInstanceId) {
        this.facilityServicesByInstanceId = facilityServicesByInstanceId;
    }

    @OneToMany(mappedBy = "facilityByInstanceFacility2Id", cascade = CascadeType.ALL)
    public Collection<EDMFacilityFacility> getFacilityFacilitiesByInstanceId_0() {
        return facilityFacilitiesByInstanceId_0;
    }

    public void setFacilityFacilitiesByInstanceId_0(Collection<EDMFacilityFacility> facilityFacilitiesByInstanceId_0) {
        this.facilityFacilitiesByInstanceId_0 = facilityFacilitiesByInstanceId_0;
    }

    @OneToMany(mappedBy = "facilityByInstanceFacilityId", cascade = CascadeType.ALL)
    public Collection<EDMEquipmentFacility> getEquipmentFacilitiesByInstanceId() {
        return equipmentFacilitiesByInstanceId;
    }

    public void setEquipmentFacilitiesByInstanceId(Collection<EDMEquipmentFacility> equipmentFacilitiesByInstanceId) {
        this.equipmentFacilitiesByInstanceId = equipmentFacilitiesByInstanceId;
    }
}
