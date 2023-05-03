package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "contactpoint")
@NamedQueries({
        @NamedQuery(name = "contactpoint.findAll", query = "SELECT c FROM EDMContactpoint c"),
        @NamedQuery(name = "contactpoint.findAllByState", query = "SELECT c FROM EDMContactpoint c where c.state = :STATE"),
        @NamedQuery(name = "contactpoint.findByUidAndState", query = "select c from EDMContactpoint c where c.uid = :UID and c.state = :STATE"),
        @NamedQuery(name = "contactpoint.findByInstanceId", query = "select c from EDMContactpoint c where c.instanceId = :INSTANCEID"),
        @NamedQuery(name = "contactpoint.findByUid", query = "select c from EDMContactpoint c where c.uid = :UID"),
        @NamedQuery(name = "contactpoint.findByFileProvenance", query = "select c from EDMContactpoint c where c.fileprovenance = :FPROV"),
        @NamedQuery(name = "contactpoint.findAllByMetaId", query = "select c from EDMContactpoint c where c.metaId = :METAID")
})
public class EDMContactpoint {
    private String uid;
    private String role;
    private String instancePersonId;
    private String instanceOrganizationId;
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
    private EDMEdmEntityId edmEntityIdByMetaPersonId;
    private EDMEdmEntityId edmEntityIdByMetaOrganizationId;
    private EDMEdmEntityId edmEntityIdByMetaId;
    private EDMContactpoint contactpointByInstanceChangedId;
    private Collection<EDMContactpoint> contactpointsByInstanceId;
    private EDMEdmEntityId edmEntityIdByEditorMetaId;
    private Collection<EDMContactpointEmail> contactpointEmailsByInstanceId;
    private Collection<EDMContactpointLanguage> contactpointLanguageByInstanceId;
    private Collection<EDMContactpointTelephone> contactpointTelephonesByInstanceId;
    private Collection<EDMContactpointDataproduct> contactpointDataproductsByInstanceId;
    private Collection<EDMContactpointEquipment> contactpointEquipmentsByInstanceId;
    private Collection<EDMContactpointFacility> contactpointFacilitiesByInstanceId;
    private Collection<EDMContactpointOrganization> contactpointOrganizationsByInstanceId;
    private Collection<EDMContactpointService> contactpointServicesByInstanceId;
    private Collection<EDMContactpointSoftwareapplication> contactpointSoftwareapplicationsByInstanceId;
    private Collection<EDMContactpointSoftwaresourcecode> contactpointSoftwaresourcecodesByInstanceId;
    private Collection<EDMContactpointWebservice> contactpointWebservicesByInstanceId;

    @Basic
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "meta_person_id", insertable = false, updatable = false)
    public String getInstancePersonId() {
        return instancePersonId;
    }

    public void setInstancePersonId(String metaPersonId) {
        this.instancePersonId = metaPersonId;
    }

    @Basic
    @Column(name = "meta_organization_id", insertable = false, updatable = false)
    public String getInstanceOrganizationId() {
        return instanceOrganizationId;
    }

    public void setInstanceOrganizationId(String metaOrganizationId) {
        this.instanceOrganizationId = metaOrganizationId;
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
    @Column(name = "change_timestamp")
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
    @Column(name = "to_be_deleted")
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

        EDMContactpoint that = (EDMContactpoint) o;

        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (instancePersonId != null ? !instancePersonId.equals(that.instancePersonId) : that.instancePersonId != null)
            return false;
        if (instanceOrganizationId != null ? !instanceOrganizationId.equals(that.instanceOrganizationId) : that.instanceOrganizationId != null)
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
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (instancePersonId != null ? instancePersonId.hashCode() : 0);
        result = 31 * result + (instanceOrganizationId != null ? instanceOrganizationId.hashCode() : 0);
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

    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name = "meta_person_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByMetaPersonId() {
        return edmEntityIdByMetaPersonId;
    }

    public void setEdmEntityIdByMetaPersonId(EDMEdmEntityId edmEntityIdByMetaPersonId) {
        this.edmEntityIdByMetaPersonId = edmEntityIdByMetaPersonId;
    }

    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name = "meta_organization_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByMetaOrganizationId() {
        return edmEntityIdByMetaOrganizationId;
    }

    public void setEdmEntityIdByMetaOrganizationId(EDMEdmEntityId edmEntityIdByMetaOrganizationId) {
        this.edmEntityIdByMetaOrganizationId = edmEntityIdByMetaOrganizationId;
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
    public EDMContactpoint getContactpointByInstanceChangedId() {
        return contactpointByInstanceChangedId;
    }

    public void setContactpointByInstanceChangedId(EDMContactpoint contactpointByInstanceChangedId) {
        this.contactpointByInstanceChangedId = contactpointByInstanceChangedId;
    }

    @OneToMany(mappedBy = "contactpointByInstanceChangedId", fetch = FetchType.EAGER)
    public Collection<EDMContactpoint> getContactpointsByInstanceId() {
        return contactpointsByInstanceId;
    }

    public void setContactpointsByInstanceId(Collection<EDMContactpoint> contactpointsByInstanceId) {
        this.contactpointsByInstanceId = contactpointsByInstanceId;
    }

    @ManyToOne
    @JoinColumn(name = "editor_meta_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByEditorMetaId() {
        return edmEntityIdByEditorMetaId;
    }

    public void setEdmEntityIdByEditorMetaId(EDMEdmEntityId edmEntityIdByEditorMetaId) {
        this.edmEntityIdByEditorMetaId = edmEntityIdByEditorMetaId;
    }

    @OneToMany(mappedBy = "contactpointByInstanceContactpointId", cascade = CascadeType.ALL)
    public Collection<EDMContactpointEmail> getContactpointEmailsByInstanceId() {
        return contactpointEmailsByInstanceId;
    }

    public void setContactpointEmailsByInstanceId(Collection<EDMContactpointEmail> contactpointEmailsByInstanceId) {
        this.contactpointEmailsByInstanceId = contactpointEmailsByInstanceId;
    }


    @OneToMany(mappedBy = "contactpointByInstanceContactpointId", cascade = CascadeType.ALL)
    public Collection<EDMContactpointLanguage> getContactpointLanguageByInstanceId() {
        return contactpointLanguageByInstanceId;
    }

    public void setContactpointLanguageByInstanceId(Collection<EDMContactpointLanguage> contactpointLanguageByInstanceId) {
        this.contactpointLanguageByInstanceId = contactpointLanguageByInstanceId;
    }


    @OneToMany(mappedBy = "contactpointByInstanceContactpointId", cascade = CascadeType.ALL)
    public Collection<EDMContactpointTelephone> getContactpointTelephonesByInstanceId() {
        return contactpointTelephonesByInstanceId;
    }

    public void setContactpointTelephonesByInstanceId(Collection<EDMContactpointTelephone> contactpointTelephonesByInstanceId) {
        this.contactpointTelephonesByInstanceId = contactpointTelephonesByInstanceId;
    }


    @OneToMany(mappedBy = "contactpointByInstanceContactpointId", cascade = CascadeType.ALL)
    public Collection<EDMContactpointDataproduct> getContactpointDataproductsByInstanceId() {
        return contactpointDataproductsByInstanceId;
    }

    public void setContactpointDataproductsByInstanceId(Collection<EDMContactpointDataproduct> contactpointDataproductsByInstanceId) {
        this.contactpointDataproductsByInstanceId = contactpointDataproductsByInstanceId;
    }

    @OneToMany(mappedBy = "contactpointByInstanceContactpointId", cascade = CascadeType.ALL)
    public Collection<EDMContactpointEquipment> getContactpointEquipmentsByInstanceId() {
        return contactpointEquipmentsByInstanceId;
    }

    public void setContactpointEquipmentsByInstanceId(Collection<EDMContactpointEquipment> contactpointEquipmentsByInstanceId) {
        this.contactpointEquipmentsByInstanceId = contactpointEquipmentsByInstanceId;
    }

    @OneToMany(mappedBy = "contactpointByInstanceContactpointId", cascade = CascadeType.ALL)
    public Collection<EDMContactpointFacility> getContactpointFacilitiesByInstanceId() {
        return contactpointFacilitiesByInstanceId;
    }

    public void setContactpointFacilitiesByInstanceId(Collection<EDMContactpointFacility> contactpointFacilitiesByInstanceId) {
        this.contactpointFacilitiesByInstanceId = contactpointFacilitiesByInstanceId;
    }

    @OneToMany(mappedBy = "contactpointByInstanceContactpointId", cascade = CascadeType.ALL)
    public Collection<EDMContactpointOrganization> getContactpointOrganizationsByInstanceId() {
        return contactpointOrganizationsByInstanceId;
    }

    public void setContactpointOrganizationsByInstanceId(Collection<EDMContactpointOrganization> contactpointOrganizationsByInstanceId) {
        this.contactpointOrganizationsByInstanceId = contactpointOrganizationsByInstanceId;
    }

    @OneToMany(mappedBy = "contactpointByInstanceContactpointId", cascade = CascadeType.ALL)
    public Collection<EDMContactpointService> getContactpointServicesByInstanceId() {
        return contactpointServicesByInstanceId;
    }

    public void setContactpointServicesByInstanceId(Collection<EDMContactpointService> contactpointServicesByInstanceId) {
        this.contactpointServicesByInstanceId = contactpointServicesByInstanceId;
    }

    @OneToMany(mappedBy = "contactpointByInstanceContactpointId", cascade = CascadeType.ALL)
    public Collection<EDMContactpointSoftwareapplication> getContactpointSoftwareapplicationsByInstanceId() {
        return contactpointSoftwareapplicationsByInstanceId;
    }

    public void setContactpointSoftwareapplicationsByInstanceId(Collection<EDMContactpointSoftwareapplication> contactpointSoftwareapplicationsByInstanceId) {
        this.contactpointSoftwareapplicationsByInstanceId = contactpointSoftwareapplicationsByInstanceId;
    }

    @OneToMany(mappedBy = "contactpointByInstanceContactpointId", cascade = CascadeType.ALL)
    public Collection<EDMContactpointSoftwaresourcecode> getContactpointSoftwaresourcecodesByInstanceId() {
        return contactpointSoftwaresourcecodesByInstanceId;
    }

    public void setContactpointSoftwaresourcecodesByInstanceId(Collection<EDMContactpointSoftwaresourcecode> contactpointSoftwaresourcecodesByInstanceId) {
        this.contactpointSoftwaresourcecodesByInstanceId = contactpointSoftwaresourcecodesByInstanceId;
    }

    @OneToMany(mappedBy = "contactpointByInstanceContactpointId", cascade = CascadeType.ALL)
    public Collection<EDMContactpointWebservice> getContactpointWebservicesByInstanceId() {
        return contactpointWebservicesByInstanceId;
    }

    public void setContactpointWebservicesByInstanceId(Collection<EDMContactpointWebservice> contactpointWebservicesByInstanceId) {
        this.contactpointWebservicesByInstanceId = contactpointWebservicesByInstanceId;
    }
}
