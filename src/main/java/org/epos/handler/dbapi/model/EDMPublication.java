package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "publication")
@NamedQueries({
        @NamedQuery(name = "publication.findAll", query = "SELECT c FROM EDMPublication c"),
        @NamedQuery(name = "publication.findAllByState", query = "SELECT c FROM EDMPublication c where c.state = :STATE"),
        @NamedQuery(name = "publication.findByUidAndState", query = "select c from EDMPublication c where c.uid = :UID and c.state = :STATE"),
        @NamedQuery(name = "publication.findByInstanceId", query = "select c from EDMPublication c where c.instanceId = :INSTANCEID")
})
public class EDMPublication {
    private String uid;
    private String name;
    private Date published;
    private String abstractString;
    private String licenseurl;
    private String keywords;
    private String issn;
    private Integer pages;
    private String volumenumber;
    private String author;
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
    private Collection<EDMContributor> contributorsByInstanceId;
    private EDMEdmEntityId edmEntityIdByMetaId;
    private EDMPublication publicationByInstanceChangedId;
    private Collection<EDMPublication> publicationsByInstanceId;
    private EDMEdmEntityId edmEntityIdByEditorMetaId;
    private Collection<EDMPublicationCategory> publicationCategoriesByInstanceId;
    private Collection<EDMPublicationIdentifier> publicationIdentifiersByInstanceId;

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
    @Column(name = "published", nullable = true)
    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    @Basic
    @Column(name = "abstract")
    public String getAbstractString() {
        return abstractString;
    }

    public void setAbstractString(String abstractString) {
        this.abstractString = abstractString;
    }

    @Basic
    @Column(name = "licenseurl")
    public String getLicenseurl() {
        return licenseurl;
    }

    public void setLicenseurl(String licenseurl) {
        this.licenseurl = licenseurl;
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
    @Column(name = "issn")
    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    @Basic
    @Column(name = "pages", nullable = true)
    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    @Basic
    @Column(name = "volumenumber")
    public String getVolumenumber() {
        return volumenumber;
    }

    public void setVolumenumber(String volumenumber) {
        this.volumenumber = volumenumber;
    }

    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

        EDMPublication that = (EDMPublication) o;

        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (published != null ? !published.equals(that.published) : that.published != null) return false;
        if (abstractString != null ? !abstractString.equals(that.abstractString) : that.abstractString != null)
            return false;
        if (licenseurl != null ? !licenseurl.equals(that.licenseurl) : that.licenseurl != null) return false;
        if (keywords != null ? !keywords.equals(that.keywords) : that.keywords != null) return false;
        if (issn != null ? !issn.equals(that.issn) : that.issn != null) return false;
        if (pages != null ? !pages.equals(that.pages) : that.pages != null) return false;
        if (volumenumber != null ? !volumenumber.equals(that.volumenumber) : that.volumenumber != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
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
        result = 31 * result + (published != null ? published.hashCode() : 0);
        result = 31 * result + (abstractString != null ? abstractString.hashCode() : 0);
        result = 31 * result + (licenseurl != null ? licenseurl.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (issn != null ? issn.hashCode() : 0);
        result = 31 * result + (pages != null ? pages.hashCode() : 0);
        result = 31 * result + (volumenumber != null ? volumenumber.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
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

    @OneToMany(mappedBy = "publicationByInstancePublicationId")
    public Collection<EDMContributor> getContributorsByInstanceId() {
        return contributorsByInstanceId;
    }

    public void setContributorsByInstanceId(Collection<EDMContributor> contributorsByInstanceId) {
        this.contributorsByInstanceId = contributorsByInstanceId;
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
    public EDMPublication getPublicationByInstanceChangedId() {
        return publicationByInstanceChangedId;
    }

    public void setPublicationByInstanceChangedId(EDMPublication publicationByInstanceChangedId) {
        this.publicationByInstanceChangedId = publicationByInstanceChangedId;
    }

    @OneToMany(mappedBy = "publicationByInstanceChangedId")
    public Collection<EDMPublication> getPublicationsByInstanceId() {
        return publicationsByInstanceId;
    }

    public void setPublicationsByInstanceId(Collection<EDMPublication> publicationsByInstanceId) {
        this.publicationsByInstanceId = publicationsByInstanceId;
    }

    @ManyToOne
    @JoinColumn(name = "editor_meta_id", referencedColumnName = "meta_id")
    public EDMEdmEntityId getEdmEntityIdByEditorMetaId() {
        return edmEntityIdByEditorMetaId;
    }

    public void setEdmEntityIdByEditorMetaId(EDMEdmEntityId edmEntityIdByEditorMetaId) {
        this.edmEntityIdByEditorMetaId = edmEntityIdByEditorMetaId;
    }

    @OneToMany(mappedBy = "publicationByInstancePublicationId")
    public Collection<EDMPublicationCategory> getPublicationCategoriesByInstanceId() {
        return publicationCategoriesByInstanceId;
    }

    public void setPublicationCategoriesByInstanceId(Collection<EDMPublicationCategory> publicationCategoriesByInstanceId) {
        this.publicationCategoriesByInstanceId = publicationCategoriesByInstanceId;
    }

    @OneToMany(mappedBy = "publicationByInstancePublicationId")
    public Collection<EDMPublicationIdentifier> getPublicationIdentifiersByInstanceId() {
        return publicationIdentifiersByInstanceId;
    }

    public void setPublicationIdentifiersByInstanceId(Collection<EDMPublicationIdentifier> publicationIdentifiersByInstanceId) {
        this.publicationIdentifiersByInstanceId = publicationIdentifiersByInstanceId;
    }
}
