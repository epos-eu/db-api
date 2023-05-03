package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "person")
@NamedQueries({
        @NamedQuery(name = "person.findAll", query = "SELECT c FROM EDMPerson c"),
        @NamedQuery(name = "person.findAllByMetaId", query = "SELECT c FROM EDMPerson c where c.metaId = :METAID"),
        @NamedQuery(name = "person.findAllByState", query = "SELECT c FROM EDMPerson c where c.state = :STATE"),
        @NamedQuery(name = "person.findByUidAndState", query = "select c from EDMPerson c where c.uid = :UID and c.state = :STATE"),
        @NamedQuery(name = "person.findByUid", query = "select c from EDMPerson c where c.uid = :UID"),
        @NamedQuery(name = "person.findByInstanceId", query = "select c from EDMPerson c where c.instanceId = :INSTANCEID"),
        @NamedQuery(name = "person.findByFileProvenance", query = "select c from EDMPerson c where c.fileprovenance = :FPROV"),
        @NamedQuery(name = "person.findByAuthId", query = "select c from EDMPerson c where c.authIdentifier = :AUTHID")
})
public class EDMPerson extends EDMPersonLite {
    private String authIdentifier;
    private String familyname;
    private String givenname;
    private String qualifications;
    private String cvurl;
    private String addressId;
    private String tmpRole;
    private String fileprovenance;
    private String instanceChangedId;
    private Timestamp changeTimestamp;
    private String operation;
    private String editorMetaId;
    private String changeComment;
    private String version;
    private Boolean toBeDeleted;
    private Collection<EDMAffiliation> affiliationsByInstanceId;
    private EDMAddress addressByAddressId;
    private EDMPerson personByInstanceChangedId;
    private Collection<EDMPerson> peopleByInstanceId;
    private EDMEdmEntityId edmEntityIdByEditorMetaId;
    private Collection<EDMPersonTelephone> personTelephonesByInstanceId;
    private Collection<EDMPersonEmail> personEmailByInstanceId;
    private Collection<EDMPersonIdentifier> personIdentifiersByInstanceId;

    @Basic
    @Column(name = "auth_identifier")
    public String getAuthIdentifier() {
        return authIdentifier;
    }

    public void setAuthIdentifier(String authIdentifier) {
        this.authIdentifier = authIdentifier;
    }

    @Basic
    @Column(name = "familyname")
    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    @Basic
    @Column(name = "givenname")
    public String getGivenname() {
        return givenname;
    }

    public void setGivenname(String givenname) {
        this.givenname = givenname;
    }

    @Basic
    @Column(name = "tmpRole")
    public String getTmpRole() {
        return tmpRole;
    }

    public void setTmpRole(String tmpRole) {
        this.tmpRole = tmpRole;
    }

    @Basic
    @Column(name = "qualifications")
    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    @Basic
    @Column(name = "cvurl")
    public String getCvurl() {
        return cvurl;
    }

    public void setCvurl(String cvurl) {
        this.cvurl = cvurl;
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
    @Column(name = "fileprovenance")
    public String getFileprovenance() {
        return fileprovenance;
    }

    public void setFileprovenance(String fileprovenance) {
        this.fileprovenance = fileprovenance;
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

        EDMPerson edmPerson = (EDMPerson) o;

        if (authIdentifier != null ? !authIdentifier.equals(edmPerson.authIdentifier) : edmPerson.authIdentifier != null)
            return false;
        if (familyname != null ? !familyname.equals(edmPerson.familyname) : edmPerson.familyname != null) return false;
        if (givenname != null ? !givenname.equals(edmPerson.givenname) : edmPerson.givenname != null) return false;
        if (qualifications != null ? !qualifications.equals(edmPerson.qualifications) : edmPerson.qualifications != null)
            return false;
        if (cvurl != null ? !cvurl.equals(edmPerson.cvurl) : edmPerson.cvurl != null) return false;
        if (addressId != null ? !addressId.equals(edmPerson.addressId) : edmPerson.addressId != null) return false;
        if (fileprovenance != null ? !fileprovenance.equals(edmPerson.fileprovenance) : edmPerson.fileprovenance != null)
            return false;
        if (instanceChangedId != null ? !instanceChangedId.equals(edmPerson.instanceChangedId) : edmPerson.instanceChangedId != null)
            return false;
        if (changeTimestamp != null ? !changeTimestamp.equals(edmPerson.changeTimestamp) : edmPerson.changeTimestamp != null)
            return false;
        if (operation != null ? !operation.equals(edmPerson.operation) : edmPerson.operation != null) return false;
        if (editorMetaId != null ? !editorMetaId.equals(edmPerson.editorMetaId) : edmPerson.editorMetaId != null)
            return false;
        if (changeComment != null ? !changeComment.equals(edmPerson.changeComment) : edmPerson.changeComment != null)
            return false;
        if (version != null ? !version.equals(edmPerson.version) : edmPerson.version != null) return false;
        return toBeDeleted != null ? toBeDeleted.equals(edmPerson.toBeDeleted) : edmPerson.toBeDeleted == null;
    }

    @Override
    public int hashCode() {
        int result = authIdentifier != null ? authIdentifier.hashCode() : 0;
        result = 31 * result + (familyname != null ? familyname.hashCode() : 0);
        result = 31 * result + (givenname != null ? givenname.hashCode() : 0);
        result = 31 * result + (qualifications != null ? qualifications.hashCode() : 0);
        result = 31 * result + (cvurl != null ? cvurl.hashCode() : 0);
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        result = 31 * result + (fileprovenance != null ? fileprovenance.hashCode() : 0);
        result = 31 * result + (instanceChangedId != null ? instanceChangedId.hashCode() : 0);
        result = 31 * result + (changeTimestamp != null ? changeTimestamp.hashCode() : 0);
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        result = 31 * result + (editorMetaId != null ? editorMetaId.hashCode() : 0);
        result = 31 * result + (changeComment != null ? changeComment.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (toBeDeleted != null ? toBeDeleted.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "personByInstancePersonId", cascade = CascadeType.ALL)
    public Collection<EDMAffiliation> getAffiliationsByInstanceId() {
        return affiliationsByInstanceId;
    }

    public void setAffiliationsByInstanceId(Collection<EDMAffiliation> affiliationsByInstanceId) {
        this.affiliationsByInstanceId = affiliationsByInstanceId;
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
    @JoinColumn(name = "instance_changed_id", referencedColumnName = "instance_id")
    public EDMPerson getPersonByInstanceChangedId() {
        return personByInstanceChangedId;
    }

    public void setPersonByInstanceChangedId(EDMPerson personByInstanceChangedId) {
        this.personByInstanceChangedId = personByInstanceChangedId;
    }

    @OneToMany(mappedBy = "personByInstanceChangedId")
    public Collection<EDMPerson> getPeopleByInstanceId() {
        return peopleByInstanceId;
    }

    public void setPeopleByInstanceId(Collection<EDMPerson> peopleByInstanceId) {
        this.peopleByInstanceId = peopleByInstanceId;
    }

    @ManyToOne
    @JoinColumn(name = "editor_meta_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByEditorMetaId() {
        return edmEntityIdByEditorMetaId;
    }

    public void setEdmEntityIdByEditorMetaId(EDMEdmEntityId edmEntityIdByEditorMetaId) {
        this.edmEntityIdByEditorMetaId = edmEntityIdByEditorMetaId;
    }

    @OneToMany(mappedBy = "personByInstancePersonId", cascade = CascadeType.ALL)
    public Collection<EDMPersonIdentifier> getPersonIdentifiersByInstanceId() {
        return personIdentifiersByInstanceId;
    }

    public void setPersonIdentifiersByInstanceId(Collection<EDMPersonIdentifier> personIdentifiersByInstanceId) {
        this.personIdentifiersByInstanceId = personIdentifiersByInstanceId;
    }

    @OneToMany(mappedBy = "personIdByInstancePersonId", cascade = CascadeType.ALL)
    public Collection<EDMPersonTelephone> getPersonTelephonesByInstanceId() {
        return personTelephonesByInstanceId;
    }

    public void setPersonTelephonesByInstanceId(Collection<EDMPersonTelephone> personTelephonesByInstanceId) {
        this.personTelephonesByInstanceId = personTelephonesByInstanceId;
    }

    @OneToMany(mappedBy = "personIdByInstancePersonId", cascade = CascadeType.ALL)
    public Collection<EDMPersonEmail> getPersonEmailByInstanceId() {
        return personEmailByInstanceId;
    }

    public void setPersonEmailByInstanceId(Collection<EDMPersonEmail> personEmailByInstanceId) {
        this.personEmailByInstanceId = personEmailByInstanceId;
    }
}
