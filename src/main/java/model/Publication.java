package model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Publication {
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
    @Column(name = "name", nullable = false, length = 1024)
    private String name;
    @Basic
    @Column(name = "published", nullable = true)
    private Timestamp published;
    @Basic
    @Column(name = "abstracttext", nullable = true, length = -1)
    private String abstracttext;
    @Basic
    @Column(name = "licenseurl", nullable = true, length = 1024)
    private String licenseurl;
    @Basic
    @Column(name = "keywords", nullable = true, length = -1)
    private String keywords;
    @Basic
    @Column(name = "issn", nullable = true, length = 1024)
    private String issn;
    @Basic
    @Column(name = "pages", nullable = true)
    private Integer pages;
    @Basic
    @Column(name = "volumenumber", nullable = true, length = 1024)
    private String volumenumber;
    @Basic
    @Column(name = "author", nullable = false, length = 1024)
    private String author;
    @Basic
    @Column(name = "publisher", nullable = true, length = 100)
    private String publisher;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @OneToMany(mappedBy = "publicationByPublicationInstanceId")
    private Collection<PublicationCategory> publicationCategoriesByInstanceId;
    @OneToMany(mappedBy = "publicationByPublicationInstanceId")
    private Collection<PublicationContributor> publicationContributorsByInstanceId;
    @OneToMany(mappedBy = "publicationByPublicationInstanceId")
    private Collection<PublicationIdentifier> publicationIdentifiersByInstanceId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getPublished() {
        return published;
    }

    public void setPublished(Timestamp published) {
        this.published = published;
    }

    public String getAbstracttext() {
        return abstracttext;
    }

    public void setAbstracttext(String abstracttext) {
        this.abstracttext = abstracttext;
    }

    public String getLicenseurl() {
        return licenseurl;
    }

    public void setLicenseurl(String licenseurl) {
        this.licenseurl = licenseurl;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getVolumenumber() {
        return volumenumber;
    }

    public void setVolumenumber(String volumenumber) {
        this.volumenumber = volumenumber;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publication that = (Publication) o;

        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(that.metaId) : that.metaId != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (versionId != null ? !versionId.equals(that.versionId) : that.versionId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (published != null ? !published.equals(that.published) : that.published != null) return false;
        if (abstracttext != null ? !abstracttext.equals(that.abstracttext) : that.abstracttext != null) return false;
        if (licenseurl != null ? !licenseurl.equals(that.licenseurl) : that.licenseurl != null) return false;
        if (keywords != null ? !keywords.equals(that.keywords) : that.keywords != null) return false;
        if (issn != null ? !issn.equals(that.issn) : that.issn != null) return false;
        if (pages != null ? !pages.equals(that.pages) : that.pages != null) return false;
        if (volumenumber != null ? !volumenumber.equals(that.volumenumber) : that.volumenumber != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (publisher != null ? !publisher.equals(that.publisher) : that.publisher != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (published != null ? published.hashCode() : 0);
        result = 31 * result + (abstracttext != null ? abstracttext.hashCode() : 0);
        result = 31 * result + (licenseurl != null ? licenseurl.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (issn != null ? issn.hashCode() : 0);
        result = 31 * result + (pages != null ? pages.hashCode() : 0);
        result = 31 * result + (volumenumber != null ? volumenumber.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        return result;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Collection<PublicationCategory> getPublicationCategoriesByInstanceId() {
        return publicationCategoriesByInstanceId;
    }

    public void setPublicationCategoriesByInstanceId(Collection<PublicationCategory> publicationCategoriesByInstanceId) {
        this.publicationCategoriesByInstanceId = publicationCategoriesByInstanceId;
    }

    public Collection<PublicationContributor> getPublicationContributorsByInstanceId() {
        return publicationContributorsByInstanceId;
    }

    public void setPublicationContributorsByInstanceId(Collection<PublicationContributor> publicationContributorsByInstanceId) {
        this.publicationContributorsByInstanceId = publicationContributorsByInstanceId;
    }

    public Collection<PublicationIdentifier> getPublicationIdentifiersByInstanceId() {
        return publicationIdentifiersByInstanceId;
    }

    public void setPublicationIdentifiersByInstanceId(Collection<PublicationIdentifier> publicationIdentifiersByInstanceId) {
        this.publicationIdentifiersByInstanceId = publicationIdentifiersByInstanceId;
    }
}
