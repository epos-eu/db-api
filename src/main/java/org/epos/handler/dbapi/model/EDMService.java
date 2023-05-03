package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "service")
@NamedQueries({
        @NamedQuery(name = "service.findAll", query = "SELECT c FROM EDMService c"),
        @NamedQuery(name = "service.findAllByMetaId", query = "SELECT c FROM EDMService c where c.metaId = :METAID"),
        @NamedQuery(name = "service.findAllByState", query = "SELECT c FROM EDMService c where c.state = :STATE"),
        @NamedQuery(name = "service.findByUidAndState", query = "select c from EDMService c where c.uid = :UID and c.state = :STATE"),
        @NamedQuery(name = "service.findByUid", query = "select c from EDMService c where c.uid = :UID"),
        @NamedQuery(name = "service.findByidentifier", query = "select c from EDMService c where c.identifier = :IDENTIFIER"),
        @NamedQuery(name = "service.findByFileProvenance", query = "select c from EDMService c where c.fileprovenance = :FPROV"),
        @NamedQuery(name = "service.findByInstanceId", query = "select c from EDMService c where c.instanceId = :INSTANCEID")
})
public class EDMService {
    private String uid;
    private String identifier;
    private String name;
    private String description;
    private String type;
    private String pageurl;
    private String keywords;
    private String provider;
    private String servicecontract;
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
    private Collection<EDMContactpointService> contactpointServicesByInstanceId;
    private EDMEdmEntityId edmEntityIdByProvider;
    private EDMEdmEntityId edmEntityIdByServicecontract;
    private EDMEdmEntityId edmEntityIdByMetaId;
    private EDMService serviceByInstanceChangedId;
    private Collection<EDMService> servicesByInstanceId;
    private EDMEdmEntityId edmEntityIdByEditorMetaId;
    private Collection<EDMServiceCategory> serviceCategoriesByInstanceId;
    private Collection<EDMServiceSpatial> serviceSpatialsByInstanceId;
    private Collection<EDMServiceTemporal> serviceTemporalsByInstanceId;
    private Collection<EDMFacilityService> facilityServicesByInstanceId;

    @Basic
    @Column(name = "identifier")
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    @Column(name = "keywords")
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Basic
    @Column(name = "provider", insertable = false, updatable = false)
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Basic
    @Column(name = "servicecontract", insertable = false, updatable = false)
    public String getServicecontract() {
        return servicecontract;
    }

    public void setServicecontract(String servicecontract) {
        this.servicecontract = servicecontract;
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

        EDMService that = (EDMService) o;

        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (pageurl != null ? !pageurl.equals(that.pageurl) : that.pageurl != null) return false;
        if (keywords != null ? !keywords.equals(that.keywords) : that.keywords != null) return false;
        if (provider != null ? !provider.equals(that.provider) : that.provider != null) return false;
        if (servicecontract != null ? !servicecontract.equals(that.servicecontract) : that.servicecontract != null)
            return false;
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
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (pageurl != null ? pageurl.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (provider != null ? provider.hashCode() : 0);
        result = 31 * result + (servicecontract != null ? servicecontract.hashCode() : 0);
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

    @OneToMany(mappedBy = "serviceByInstanceServiceId", cascade = CascadeType.ALL)
    public Collection<EDMContactpointService> getContactpointServicesByInstanceId() {
        return contactpointServicesByInstanceId;
    }

    public void setContactpointServicesByInstanceId(Collection<EDMContactpointService> contactpointServicesByInstanceId) {
        this.contactpointServicesByInstanceId = contactpointServicesByInstanceId;
    }

    @ManyToOne
    @JoinColumn(name = "provider", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByProvider() {
        return edmEntityIdByProvider;
    }

    public void setEdmEntityIdByProvider(EDMEdmEntityId edmEntityIdByProvider) {
        this.edmEntityIdByProvider = edmEntityIdByProvider;
    }

    @ManyToOne
    @JoinColumn(name = "servicecontract", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByServicecontract() {
        return edmEntityIdByServicecontract;
    }

    public void setEdmEntityIdByServicecontract(EDMEdmEntityId edmEntityIdByServicecontract) {
        this.edmEntityIdByServicecontract = edmEntityIdByServicecontract;
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
    public EDMService getServiceByInstanceChangedId() {
        return serviceByInstanceChangedId;
    }

    public void setServiceByInstanceChangedId(EDMService serviceByInstanceChangedId) {
        this.serviceByInstanceChangedId = serviceByInstanceChangedId;
    }

    @OneToMany(mappedBy = "serviceByInstanceChangedId")
    public Collection<EDMService> getServicesByInstanceId() {
        return servicesByInstanceId;
    }

    public void setServicesByInstanceId(Collection<EDMService> servicesByInstanceId) {
        this.servicesByInstanceId = servicesByInstanceId;
    }

    @ManyToOne
    @JoinColumn(name = "editor_meta_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByEditorMetaId() {
        return edmEntityIdByEditorMetaId;
    }

    public void setEdmEntityIdByEditorMetaId(EDMEdmEntityId edmEntityIdByEditorMetaId) {
        this.edmEntityIdByEditorMetaId = edmEntityIdByEditorMetaId;
    }

    @OneToMany(mappedBy = "serviceByInstanceServiceId", cascade = CascadeType.ALL)
    public Collection<EDMServiceCategory> getServiceCategoriesByInstanceId() {
        return serviceCategoriesByInstanceId;
    }

    public void setServiceCategoriesByInstanceId(Collection<EDMServiceCategory> serviceCategoriesByInstanceId) {
        this.serviceCategoriesByInstanceId = serviceCategoriesByInstanceId;
    }

    @OneToMany(mappedBy = "serviceByInstanceServiceId", cascade = CascadeType.ALL)
    public Collection<EDMServiceSpatial> getServiceSpatialsByInstanceId() {
        return serviceSpatialsByInstanceId;
    }

    public void setServiceSpatialsByInstanceId(Collection<EDMServiceSpatial> serviceSpatialsByInstanceId) {
        this.serviceSpatialsByInstanceId = serviceSpatialsByInstanceId;
    }

    @OneToMany(mappedBy = "serviceByInstanceServiceId", cascade = CascadeType.ALL)
    public Collection<EDMServiceTemporal> getServiceTemporalsByInstanceId() {
        return serviceTemporalsByInstanceId;
    }

    public void setServiceTemporalsByInstanceId(Collection<EDMServiceTemporal> serviceTemporalsByInstanceId) {
        this.serviceTemporalsByInstanceId = serviceTemporalsByInstanceId;
    }

    @OneToMany(mappedBy = "serviceByInstanceServiceId", cascade = CascadeType.ALL)
    public Collection<EDMFacilityService> getFacilityServicesByInstanceId() {
        return facilityServicesByInstanceId;
    }

    public void setFacilityServicesByInstanceId(Collection<EDMFacilityService> facilityServicesByInstanceId) {
        this.facilityServicesByInstanceId = facilityServicesByInstanceId;
    }
}
