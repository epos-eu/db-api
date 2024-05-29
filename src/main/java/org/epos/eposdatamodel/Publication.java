package org.epos.eposdatamodel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A text publication is a resource relating to a dataset, data product or other.
 */
public class Publication extends EPOSDataModelEntity {

    /**
     * This property contains an identifier for the Publication.
     **/
    private List<Identifier> identifier = new ArrayList<>();

    /**
     * This property contains the name of the Publication.
     **/
    private String name;

    /**
     * This property contains the date of first Publication.
     **/
    private LocalDateTime published;

    /**
     * This property refers to an organization or person or agent which is the publisher of the Publication.
     **/
    private String publisher;

    /**
     * This property contains the abstract of the Publication.
     **/
    private String _abstract;

    /**
     * This property refers to a Person which is the author of the Publication.
     **/
    private String author;

    /**
     * This property refers to a Person which is a contributor of the Publication.
     **/
    private List<String> contributor;

    /**
     * This property contains the URL of the license document that applies to the Publication.
     **/
    private String licenseURL;

    /**
     * This property contains the keywords used to describe the Publication. Multiple entries in a keywords list are typically delimited by commas.
     **/
    private String keywords;

    /**
     * This property contains the International Standard Serial Number (ISSN) that identifies the Publication.
     **/
    private String issn;

    /**
     * This property contains the number of pages of the Publication.
     **/
    private int pages;

    /**
     * This property contains the volume identifier of the Publication.
     **/
    private String volumesNumber;

    /**
     * This property refers to a category of the Publication. A Publication may be associated with multiple categories.
     **/
    private List<String> category;


    public Publication identifier(List<Identifier> identifier) {
        this.identifier = identifier;
        return this;
    }

    public Publication addIdentifierItem(Identifier identifierItem) {
        this.identifier.add(identifierItem);
        return this;
    }

    /**
     * This property contains an identifier for the Publication.
     *
     * @return identifier
     **/
    public List<Identifier> getIdentifier() {
        return identifier;
    }

    public void setIdentifier(List<Identifier> identifier) {
        this.identifier = identifier;
    }

    public Publication name(String name) {
        this.name = name;
        return this;
    }

    /**
     * This property contains the name of the Publication.
     *
     * @return name
     **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Publication published(LocalDateTime published) {
        this.published = published;
        return this;
    }

    /**
     * This property contains the date of first Publication.
     *
     * @return published
     **/
    public LocalDateTime getPublished() {
        return published;
    }

    public void setPublished(LocalDateTime published) {
        this.published = published;
    }

    public Publication publisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    /**
     * This property refers to an organization or person or agent which is the publisher of the Publication.
     *
     * @return publisher
     **/

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Publication _abstract(String _abstract) {
        this._abstract = _abstract;
        return this;
    }

    /**
     * This property contains the abstract of the Publication.
     *
     * @return _abstract
     **/

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public Publication author(String author) {
        this.author = author;
        return this;
    }

    /**
     * This property refers to a Person which is the author of the Publication.
     *
     * @return author
     **/

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Publication contributor(List<String> contributor) {
        this.contributor = contributor;
        return this;
    }

    public Publication addContributorItem(String contributorItem) {
        if (this.contributor == null) {
            this.contributor = new ArrayList<>();
        }
        this.contributor.add(contributorItem);
        return this;
    }

    /**
     * This property refers to a Person which is a contributor of the Publication.
     *
     * @return contributor
     **/

    public List<String> getContributor() {
        return contributor;
    }

    public void setContributor(List<String> contributor) {
        this.contributor = contributor;
    }

    public Publication licenseURL(String licenseURL) {
        this.licenseURL = licenseURL;
        return this;
    }

    /**
     * This property contains the URL of the license document that applies to the Publication.
     *
     * @return licenseUrl
     **/

    public String getLicenseURL() {
        return licenseURL;
    }

    public void setLicenseURL(String licenseURL) {
        this.licenseURL = licenseURL;
    }

    public Publication keywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    /**
     * This property contains the keywords used to describe the Publication. Multiple entries in a keywords list are typically delimited by commas.
     *
     * @return keywords
     **/

    public String getKeywords() {
        return keywords;
    }

    public void addKeywords(String keyword) {
        if (this.keywords == null) {
            this.keywords = keyword;
        } else {
            this.keywords = this.keywords + ",\t" + keyword;
        }
    }


    public Publication issn(String issn) {
        this.issn = issn;
        return this;
    }

    /**
     * This property contains the International Standard Serial Number (ISSN) that identifies the Publication.
     *
     * @return issn
     **/

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public Publication pages(int pages) {
        this.pages = pages;
        return this;
    }

    /**
     * This property contains the number of pages of the Publication.
     *
     * @return pages
     **/

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Publication volumesNumber(String volumesNumber) {
        this.volumesNumber = volumesNumber;
        return this;
    }

    /**
     * This property contains the volume identifier of the Publication.
     *
     * @return volumesNumber
     **/

    public String getVolumesNumber() {
        return volumesNumber;
    }

    public void setVolumesNumber(String volumesNumber) {
        this.volumesNumber = volumesNumber;
    }

    public Publication category(List<String> category) {
        this.category = category;
        return this;
    }

    public Publication addCategoryItem(String categoryItem) {
        if (this.category == null) {
            this.category = new ArrayList<>();
        }
        this.category.add(categoryItem);
        return this;
    }

    /**
     * This property refers to a category of the Publication. A Publication may be associated with multiple categories.
     *
     * @return category
     **/

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", published=" + published +
                ", publisher='" + publisher + '\'' +
                ", _abstract='" + _abstract + '\'' +
                ", author='" + author + '\'' +
                ", contributor=" + contributor +
                ", licenseURL='" + licenseURL + '\'' +
                ", keywords='" + keywords + '\'' +
                ", issn='" + issn + '\'' +
                ", pages=" + pages +
                ", volumesNumber='" + volumesNumber + '\'' +
                ", category=" + category +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Publication that = (Publication) o;
        return getPages() == that.getPages() && Objects.equals(getIdentifier(), that.getIdentifier()) && Objects.equals(getName(), that.getName()) && Objects.equals(getPublished(), that.getPublished()) && Objects.equals(getPublisher(), that.getPublisher()) && Objects.equals(_abstract, that._abstract) && Objects.equals(getAuthor(), that.getAuthor()) && Objects.equals(getContributor(), that.getContributor()) && Objects.equals(getLicenseURL(), that.getLicenseURL()) && Objects.equals(getKeywords(), that.getKeywords()) && Objects.equals(getIssn(), that.getIssn()) && Objects.equals(getVolumesNumber(), that.getVolumesNumber()) && Objects.equals(getCategory(), that.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getIdentifier(), getName(), getPublished(), getPublisher(), _abstract, getAuthor(), getContributor(), getLicenseURL(), getKeywords(), getIssn(), getPages(), getVolumesNumber(), getCategory());
    }
}
