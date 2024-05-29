package org.epos.eposdatamodel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Distribution: A physical embodiment of the Data and Data Product in a particular format.
 */
public class Distribution extends EPOSDataModelEntity {

    /**
     * This property refers to the WebService which supports selection of an extract, sub-set, or combination of data
     */
    private LinkedEntity accessService;

    /**
     * This property contains a URL that gives access to a Distribution of the Data Product. The resource at the access
     * URL may contain information about how to get the Data Product.
     **/
    private List<LinkedEntity> accessURL = new ArrayList<>();

    /**
     * This property contains a free-text account of the Distribution. This property can be repeated for parallel language
     * versions of the description.
     **/
    private List<String> description;

    /**
     * This property contains a URL that is a direct link to a downloadable file in a given format.
     **/
    private List<String> downloadURL;

    /**
     * This property refers to the type of the Distribution. A controlled vocabulary for the values has not been established.
     **/
    private String format;

    /**
     * This property contains the date of formal issuance (e.g., publication) of the Distribution.
     **/
    private LocalDateTime issued;

    /**
     * This property refers to the licence under which the Distribution is made available.
     **/
    private String licence;

    /**
     * This property contains the most recent date on which the Distribution was changed or modified.
     **/
    private LocalDateTime modified;

    /**
     * This property contains a name given to the Distribution. This property can be repeated for parallel language
     * versions of the description.
     **/
    private List<String> title;

    /**
     * This property refers to the type of the Distribution.
     **/
    private String type;

    /**
     * This property refers to the Data Policy URI.
     */
    private String dataPolicy;

    /**
     * This property is used to indicate the model, schema, ontology, view or profile
     * that this representation of a dataset conforms to (e.g., xsd, json schema).
     */
    private String conformsTo;

    /**
     * Reverse reference to the related dataproduct.
     */
    private List<LinkedEntity> dataProduct;


    public void addTitle(String title) {
        if (this.getTitle() == null) {
            ArrayList<String> titleList = new ArrayList<>();
            titleList.add(title);
            this.setTitle(titleList);
        } else {
            this.getTitle().add(title);
        }
    }

    public void addDescription(String description) {
        if (this.getDescription() == null) {
            ArrayList<String> descriptionList = new ArrayList<>();
            descriptionList.add(description);
            this.setDescription(descriptionList);
        } else {
            this.getDescription().add(description);
        }
    }

    public void addDownloadURL(String downloadURL) {
        if (this.getDownloadURL() == null) {
            ArrayList<String> downloadURLList = new ArrayList<>();
            downloadURLList.add(downloadURL);
            this.setDownloadURL(downloadURLList);
        } else {
            this.getDownloadURL().add(downloadURL);
        }
    }

    public void addAccessURL(LinkedEntity accessURL) {
        if (this.getAccessURL() == null) {
            ArrayList<LinkedEntity> accessURLList = new ArrayList<>();
            accessURLList.add(accessURL);
            this.setAccessURL(accessURLList);
        } else {
            this.getAccessURL().add(accessURL);
        }
    }


    public Distribution accessURL(List<LinkedEntity> accessURL) {
        this.accessURL = accessURL;
        return this;
    }

    public Distribution addAccessURLItem(LinkedEntity accessURLItem) {
        this.accessURL.add(accessURLItem);
        return this;
    }

    /**
     * This property contains a URL that gives access to a Distribution of the Data Product. The resource at the access URL may contain information about how to get the Data Product.
     *
     * @return accessURL
     **/
    public List<LinkedEntity> getAccessURL() {
        return accessURL;
    }

    public void setAccessURL(List<LinkedEntity> accessURL) {
        this.accessURL = accessURL;
    }

    public Distribution description(List<String> description) {
        this.description = description;
        return this;
    }

    public Distribution addDescriptionItem(String descriptionItem) {
        if (this.description == null) {
            this.description = new ArrayList<>();
        }
        this.description.add(descriptionItem);
        return this;
    }

    /**
     * This property contains a free-text account of the Distribution. This property can be repeated for parallel language versions of the description.
     *
     * @return description
     **/

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public Distribution downloadURL(List<String> downloadURL) {
        this.downloadURL = downloadURL;
        return this;
    }

    public Distribution addDownloadURLItem(String downloadURLItem) {
        if (this.downloadURL == null) {
            this.downloadURL = new ArrayList<>();
        }
        this.downloadURL.add(downloadURLItem);
        return this;
    }

    /**
     * This property contains a URL that is a direct link to a downloadable file in a given format.
     *
     * @return downloadURL
     **/

    public List<String> getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(List<String> downloadURL) {
        this.downloadURL = downloadURL;
    }

    public Distribution format(String format) {
        this.format = format;
        return this;
    }

    /**
     * This property refers to the type of the Distribution. A controlled vocabulary for the values has not been established.
     *
     * @return format
     **/

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Distribution issued(LocalDateTime issued) {
        this.issued = issued;
        return this;
    }

    /**
     * This property contains the date of formal issuance (e.g., publication) of the Distribution.
     *
     * @return issued
     **/
    public LocalDateTime getIssued() {
        return issued;
    }

    public void setIssued(LocalDateTime issued) {
        this.issued = issued;
    }

    public Distribution licence(String licence) {
        this.licence = licence;
        return this;
    }

    /**
     * This property refers to the licence under which the Distribution is made available.
     *
     * @return licence
     **/

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public Distribution modified(LocalDateTime modified) {
        this.modified = modified;
        return this;
    }

    /**
     * This property contains the most recent date on which the Distribution was changed or modified.
     *
     * @return modified
     **/
    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public Distribution title(List<String> title) {
        this.title = title;
        return this;
    }

    public Distribution addTitleItem(String titleItem) {
        if (this.title == null) {
            this.title = new ArrayList<>();
        }
        this.title.add(titleItem);
        return this;
    }

    /**
     * This property contains a name given to the Distribution. This property can be repeated for parallel language versions of the description.
     *
     * @return title
     **/

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public Distribution type(String type) {
        this.type = type;
        return this;
    }

    /**
     * This property refers to the type of the Distribution.
     *
     * @return type
     **/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public LinkedEntity getAccessService() {
        return accessService;
    }

    public void setAccessService(LinkedEntity accessService) {
        this.accessService = accessService;
    }


    public String getDataPolicy() {
        return dataPolicy;
    }

    public void setDataPolicy(String dataPolicy) {
        this.dataPolicy = dataPolicy;
    }

    public String getConformsTo() {
        return conformsTo;
    }

    public void setConformsTo(String conformsTo) {
        this.conformsTo = conformsTo;
    }

    public List<LinkedEntity> getDataProduct() {
        return dataProduct;
    }

    public Distribution setDataProduct(List<LinkedEntity> dataProduct) {
        this.dataProduct = dataProduct;
        return this;
    }

    @Override
    public String toString() {
        return "Distribution{" +
                "accessService=" + accessService +
                ", accessURL=" + accessURL +
                ", description=" + description +
                ", downloadURL=" + downloadURL +
                ", format='" + format + '\'' +
                ", issued=" + issued +
                ", licence='" + licence + '\'' +
                ", modified=" + modified +
                ", title=" + title +
                ", type='" + type + '\'' +
                ", dataPolicy='" + dataPolicy + '\'' +
                ", conformsTo='" + conformsTo + '\'' +
                ", dataProduct=" + dataProduct +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Distribution that = (Distribution) o;
        return Objects.equals(getAccessService(), that.getAccessService()) && Objects.equals(getAccessURL(), that.getAccessURL()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getDownloadURL(), that.getDownloadURL()) && Objects.equals(getFormat(), that.getFormat()) && Objects.equals(getIssued(), that.getIssued()) && Objects.equals(getLicence(), that.getLicence()) && Objects.equals(getModified(), that.getModified()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getType(), that.getType()) && Objects.equals(getDataPolicy(), that.getDataPolicy()) && Objects.equals(getConformsTo(), that.getConformsTo()) && Objects.equals(getDataProduct(), that.getDataProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAccessService(), getAccessURL(), getDescription(), getDownloadURL(), getFormat(), getIssued(), getLicence(), getModified(), getTitle(), getType(), getDataPolicy(), getConformsTo(), getDataProduct());
    }
}
