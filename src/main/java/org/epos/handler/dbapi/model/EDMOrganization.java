package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "organization")
@NamedQueries({
        @NamedQuery(name = "organization.findAll", query = "SELECT c FROM EDMOrganization c"),
        @NamedQuery(name = "organization.findAllByMetaId", query = "SELECT c FROM EDMOrganization c where c.metaId = :METAID"),
        @NamedQuery(name = "organization.findAllByState", query = "SELECT c FROM EDMOrganization c where c.state = :STATE"),
        @NamedQuery(name = "organization.findByUidAndState", query = "select c from EDMOrganization c where c.uid = :UID and c.state = :STATE"),
        @NamedQuery(name = "organization.findByUid", query = "select c from EDMOrganization c where c.uid = :UID"),
        @NamedQuery(name = "organization.findByFileProvenance", query = "select c from EDMOrganization c where c.fileprovenance = :FPROV"),
        @NamedQuery(name = "organization.findByInstanceId", query = "select c from EDMOrganization c where c.instanceId = :INSTANCEID")
})
public class EDMOrganization {
    private String uid;
    private String acronym;
    private String leicode;
    private String addressId;
    private String logo;
    private String url;
    private String type;
    private String maturity;
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
    private Collection<EDMContactpointOrganization> contactpointOrganizationsByInstanceId;
    private Collection<EDMOrganization> father;
    private Collection<EDMOrganization> son;
    private EDMAddress addressByAddressId;
    private EDMEdmEntityId edmEntityIdByMetaId;
    private EDMOrganization organizationByInstanceChangedId;
    private Collection<EDMOrganization> organizationsByInstanceId;
    private EDMEdmEntityId edmEntityIdByEditorMetaId;
    private Collection<EDMOrganizationEmail> organizationEmailsByInstanceId;
    private Collection<EDMOrganizationIdentifier> organizationIdentifiersByInstanceId;
    private Collection<EDMOrganizationTelephone> organizationTelephonesByInstanceId;
    private Collection<EDMOrganizationLegalname> organizationLegalnameByInstanceId;
    private Collection<EDMOrganizationOwner> ownsByInstanceId;

    @Basic
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "acronym")
    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    @Basic
    @Column(name = "leicode")
    public String getLeicode() {
        return leicode;
    }

    public void setLeicode(String leicode) {
        this.leicode = leicode;
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
    @Column(name = "logo")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
    @Column(name = "maturity")
    public String getMaturity() {
        return maturity;
    }

    public void setMaturity(String maturity) {
        this.maturity = maturity;
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

        EDMOrganization that = (EDMOrganization) o;

        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (acronym != null ? !acronym.equals(that.acronym) : that.acronym != null) return false;
        if (leicode != null ? !leicode.equals(that.leicode) : that.leicode != null) return false;
        if (addressId != null ? !addressId.equals(that.addressId) : that.addressId != null) return false;
        if (logo != null ? !logo.equals(that.logo) : that.logo != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (maturity != null ? !maturity.equals(that.maturity) : that.maturity != null) return false;
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
        result = 31 * result + (acronym != null ? acronym.hashCode() : 0);
        result = 31 * result + (leicode != null ? leicode.hashCode() : 0);
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (maturity != null ? maturity.hashCode() : 0);
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

    @OneToMany(mappedBy = "organizationByInstanceOrganizationId", cascade = CascadeType.ALL)
    public Collection<EDMContactpointOrganization> getContactpointOrganizationsByInstanceId() {
        return contactpointOrganizationsByInstanceId;
    }

    public void setContactpointOrganizationsByInstanceId(Collection<EDMContactpointOrganization> contactpointOrganizationsByInstanceId) {
        this.contactpointOrganizationsByInstanceId = contactpointOrganizationsByInstanceId;
    }

    @JoinTable(name = "memberof",
            joinColumns = {@JoinColumn(name = "instance_organization1_id")},
            inverseJoinColumns = {@JoinColumn(name = "instance_organization2_id")})
    @ManyToMany
    public Collection<EDMOrganization> getFather() {
        return father;
    }

    public void setFather(Collection<EDMOrganization> father) {
        this.father = father;
    }

    @ManyToMany(mappedBy = "father")
    public Collection<EDMOrganization> getSon() {
        return son;
    }

    public void setSon(Collection<EDMOrganization> son) {
        this.son = son;
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
    @JoinColumn(name = "meta_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByMetaId() {
        return edmEntityIdByMetaId;
    }

    public void setEdmEntityIdByMetaId(EDMEdmEntityId edmEntityIdByMetaId) {
        this.edmEntityIdByMetaId = edmEntityIdByMetaId;
    }

    @ManyToOne
    @JoinColumn(name = "instance_changed_id", referencedColumnName = "instance_id")
    public EDMOrganization getOrganizationByInstanceChangedId() {
        return organizationByInstanceChangedId;
    }

    public void setOrganizationByInstanceChangedId(EDMOrganization organizationByInstanceChangedId) {
        this.organizationByInstanceChangedId = organizationByInstanceChangedId;
    }

    @OneToMany(mappedBy = "organizationByInstanceChangedId")
    public Collection<EDMOrganization> getOrganizationsByInstanceId() {
        return organizationsByInstanceId;
    }

    public void setOrganizationsByInstanceId(Collection<EDMOrganization> organizationsByInstanceId) {
        this.organizationsByInstanceId = organizationsByInstanceId;
    }

    @ManyToOne
    @JoinColumn(name = "editor_meta_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByEditorMetaId() {
        return edmEntityIdByEditorMetaId;
    }

    public void setEdmEntityIdByEditorMetaId(EDMEdmEntityId edmEntityIdByEditorMetaId) {
        this.edmEntityIdByEditorMetaId = edmEntityIdByEditorMetaId;
    }

    @OneToMany(mappedBy = "organizationIdByInstanceOrganizationId", cascade = CascadeType.ALL)
    public Collection<EDMOrganizationEmail> getOrganizationEmailsByInstanceId() {
        return organizationEmailsByInstanceId;
    }

    public void setOrganizationEmailsByInstanceId(Collection<EDMOrganizationEmail> organizationEmailsByInstanceId) {
        this.organizationEmailsByInstanceId = organizationEmailsByInstanceId;
    }

    @OneToMany(mappedBy = "organizationIdByInstanceOrganizationId", cascade = CascadeType.ALL)
    public Collection<EDMOrganizationIdentifier> getOrganizationIdentifiersByInstanceId() {
        return organizationIdentifiersByInstanceId;
    }

    public void setOrganizationIdentifiersByInstanceId(Collection<EDMOrganizationIdentifier> organizationIdentifiersByInstanceId) {
        this.organizationIdentifiersByInstanceId = organizationIdentifiersByInstanceId;
    }

    @OneToMany(mappedBy = "organizationIdByInstanceOrganizationId", cascade = CascadeType.ALL)
    public Collection<EDMOrganizationTelephone> getOrganizationTelephonesByInstanceId() {
        return organizationTelephonesByInstanceId;
    }

    public void setOrganizationTelephonesByInstanceId(Collection<EDMOrganizationTelephone> organizationTelephonesByInstanceId) {
        this.organizationTelephonesByInstanceId = organizationTelephonesByInstanceId;
    }

    @OneToMany(mappedBy = "organizationIdByInstanceOrganizationId", cascade = CascadeType.ALL)
    public Collection<EDMOrganizationLegalname> getOrganizationLegalnameByInstanceId() {
        return organizationLegalnameByInstanceId;
    }

    public void setOrganizationLegalnameByInstanceId(Collection<EDMOrganizationLegalname> organizationLegalnameByInstanceId) {
        this.organizationLegalnameByInstanceId = organizationLegalnameByInstanceId;
    }

    @OneToMany(mappedBy = "organizationByInstanceOrganizationId", cascade = CascadeType.ALL)
	public Collection<EDMOrganizationOwner> getOwnsByInstanceId() {
		return ownsByInstanceId;
	}

	public void setOwnsByInstanceId(Collection<EDMOrganizationOwner> ownsByInstanceId) {
		this.ownsByInstanceId = ownsByInstanceId;
	}

}
