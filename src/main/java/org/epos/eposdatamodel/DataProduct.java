package org.epos.eposdatamodel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * It represents Data and Data Product.
 */
public class DataProduct extends EPOSDataModelEntity {

    /**
     * This property refers to information that indicates whether the Data Product is open data, has access restrictions or is not public.
     **/
    private String accessRight;

    /**
     * This property refers to the frequency at which the Data Product is updated.
     **/
    private String accrualPeriodicity;

    /**
     * This property refers to a category of the Data Product. A Data Product may be associated with multiple categories.
     **/
    private List<String> category;

    /**
     * This property contains contact information (i.e. Role) that can be used for sending comments about the Data Product.
     */
    private List<LinkedEntity> contactPoint;

    /**
     * This property contains the date on which the Data Product was created.
     **/
    private LocalDateTime created;

    /**
     * This property contains the single identifier of the DataProduct
     */
    private String dctIdentifier;

    /**
     * This property contains a free-text account of the Data Product. This property can be repeated for parallel language versions of the description.
     **/
    private List<String> description = new ArrayList<>();

    /**
     * This property links the Data Product to an available Distribution.
     */
    private List<LinkedEntity> distribution;

    /**
     * This property refers to a related Data Product that is part of the described Data Product.
     */
    private List<LinkedEntity> hasPart;

    /**
     * This property refers to a secondary identifier of the Data Product, such as MAST/ADS, DataCite, DOI, EZID or W3ID.
     **/
    private List<Identifier> identifier;

    /**
     * This property refers to a related Data Product in which the described Data Product is physically or logically included.
     */
    private List<LinkedEntity> isPartOf;

    /**
     * This property contains the date of formal issuance (e.g., publication) of the Data Product.
     **/
    private LocalDateTime issued;

    /**
     * This property contains a keyword or tag describing the Data Product. Multiple entries in a keywords list are typically delimited by commas.
     **/
    private String keywords;

    /**
     * This property contains the most recent date on which the Data Product was changed or modified.
     **/
    private LocalDateTime modified;

    /**
     * This property contains a statement about the lineage of a Data Product
     **/
    private List<String> provenance;

    /**
     * This property refers to an entity (organization) responsible for making the Data Product available.
     */
    private List<LinkedEntity> publisher;

    /**
     * It represents the link to another Epos resource.
     */
    private List<LinkedEntity> relation;

    /**
     * This property refers to a geographic region that is covered by the Data Product.
     **/
    private List<Location> spatialExtent;

    /**
     * This property refers to a temporal period (i.e. startDate, endDate) that the Data Product covers.
     **/
    private List<PeriodOfTime> temporalExtent;

    /**
     * This property contains a name given to the Data Product. This property can be repeated for parallel language versions of the name.
     **/
    private List<String> title = new ArrayList<>();

    /**
     * This property refers to the type of the Data Product. A controlled vocabulary for the values has not been established.
     **/
    private String type;

    /**
     * This property contains a version number or other version designation of the Data Product.
     **/
    private String versionInfo;

    /**
     * This property refers to the Data Product documentation.
     */
    private String documentation;

    /**
     * This property refers to the URI which make available information about quality assurance of the Data Product.
     */
    private String qualityAssurance;

    /**
     * probabily an useless property
     */
    private String hasQualityAnnotation;

    public void addSpatialExtent(Location spatialExtent) {
        if (this.getSpatialExtent() == null) {
            ArrayList<Location> spatialExtentList = new ArrayList<>();
            spatialExtentList.add(spatialExtent);
            this.setSpatialExtent(spatialExtentList);
        } else {
            this.getSpatialExtent().add(spatialExtent);
        }
    }

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

    public void addIdentifier(Identifier identifier) {
        if (this.getIdentifier() == null) {
            ArrayList<Identifier> identifierList = new ArrayList<>();
            identifierList.add(identifier);
            this.setIdentifier(identifierList);
        } else {
            this.getIdentifier().add(identifier);
        }
    }

    public void addCategory(String category) {
        if (this.getCategory() == null) {
            ArrayList<String> categoryList = new ArrayList<>();
            categoryList.add(category);
            this.setCategory(categoryList);
        } else {
            this.getCategory().add(category);
        }
    }

    public void addContactPoint(LinkedEntity contactPoint) {
        if (this.getContactPoint() == null) {
            ArrayList<LinkedEntity> contactPointList = new ArrayList<>();
            contactPointList.add(contactPoint);
            this.setContactPoint(contactPointList);
        } else {
            this.getContactPoint().add(contactPoint);
        }
    }

    public void addPublisher(LinkedEntity publisher) {
        if (this.getPublisher() == null) {
            ArrayList<LinkedEntity> publisherList = new ArrayList<>();
            publisherList.add(publisher);
            this.setPublisher(publisherList);
        } else {
            this.getPublisher().add(publisher);
        }
    }

    public void addTemporalExtent(PeriodOfTime temporalExtent) {
        if (this.getTemporalExtent() == null) {
            ArrayList<PeriodOfTime> temporalExtentList = new ArrayList<>();
            temporalExtentList.add(temporalExtent);
            this.setTemporalExtent(temporalExtentList);
        } else {
            this.getTemporalExtent().add(temporalExtent);
        }
    }

    public void addDistribution(LinkedEntity distribution) {
        if (this.getDistribution() == null) {
            ArrayList<LinkedEntity> distributionList = new ArrayList<>();
            distributionList.add(distribution);
            this.setDistribution(distributionList);
        } else {
            this.getDistribution().add(distribution);
        }
    }

    public void addIsPartOf(LinkedEntity isPartOf) {
        if (this.getIsPartOf() == null) {
            ArrayList<LinkedEntity> isPartOfList = new ArrayList<>();
            isPartOfList.add(isPartOf);
            this.setIsPartOf(isPartOfList);
        } else {
            this.getIsPartOf().add(isPartOf);
        }
    }

    public void addHasPart(LinkedEntity hasPart) {
        if (this.getHasPart() == null) {
            ArrayList<LinkedEntity> hasPartList = new ArrayList<>();
            hasPartList.add(hasPart);
            this.setHasPart(hasPartList);
        } else {
            this.getHasPart().add(hasPart);
        }
    }

    public void addProvenance(String provenance) {
        if (this.getProvenance() == null) {
            ArrayList<String> provenanceList = new ArrayList<>();
            provenanceList.add(provenance);
            this.setProvenance(provenanceList);
        } else {
            this.getProvenance().add(provenance);
        }
    }

    public String getDctIdentifier() {
        return dctIdentifier;
    }

    public void setDctIdentifier(String dctIdentifier) {
        this.dctIdentifier = dctIdentifier;
    }

    public DataProduct dctIdentifier(String dctIdentifier) {
        this.dctIdentifier = dctIdentifier;
        return this;
    }

    public DataProduct accessRight(String accessRight) {
        this.accessRight = accessRight;
        return this;
    }

    /**
     * This property refers to information that indicates whether the Data Product is open data, has access restrictions or is not public.
     *
     * @return accessRight
     **/

    public String getAccessRight() {
        return accessRight;
    }

    public void setAccessRight(String accessRight) {
        this.accessRight = accessRight;
    }

    public DataProduct accrualPeriodicity(String accrualPeriodicity) {
        this.accrualPeriodicity = accrualPeriodicity;
        return this;
    }

    /**
     * This property refers to the frequency at which the Data Product is updated.
     *
     * @return accrualPeriodicity
     **/

    public String getAccrualPeriodicity() {
        return accrualPeriodicity;
    }

    public void setAccrualPeriodicity(String accrualPeriodicity) {
        this.accrualPeriodicity = accrualPeriodicity;
    }

    public DataProduct category(List<String> category) {
        this.category = category;
        return this;
    }

    public DataProduct addCategoryItem(String categoryItem) {
        if (this.category == null) {
            this.category = new ArrayList<>();
        }
        this.category.add(categoryItem);
        return this;
    }

    /**
     * This property refers to a category of the Data Product. A Data Product may be associated with multiple categories.
     *
     * @return category
     **/

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public DataProduct created(LocalDateTime created) {
        this.created = created;
        return this;
    }

    /**
     * This property contains the date on which the Data Product was created.
     *
     * @return created
     **/

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public DataProduct description(List<String> description) {
        this.description = description;
        return this;
    }

    public DataProduct addDescriptionItem(String descriptionItem) {
        this.description.add(descriptionItem);
        return this;
    }

    /**
     * This property contains a free-text account of the Data Product. This property can be repeated for parallel language versions of the description.
     *
     * @return description
     **/

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }


    public DataProduct identifier(List<Identifier> identifier) {
        this.identifier = identifier;
        return this;
    }

    public DataProduct addIdentifierItem(Identifier identifierItem) {
        if (this.identifier == null) {
            this.identifier = new ArrayList<>();
        }
        this.identifier.add(identifierItem);
        return this;
    }

    /**
     * This property refers to a secondary identifier of the Data Product, such as MAST/ADS, DataCite, DOI, EZID or W3ID.
     *
     * @return identifier
     **/
    public List<Identifier> getIdentifier() {
        return identifier;
    }

    public void setIdentifier(List<Identifier> identifier) {
        this.identifier = identifier;
    }

    public DataProduct issued(LocalDateTime issued) {
        this.issued = issued;
        return this;
    }

    /**
     * This property contains the date of formal issuance (e.g., publication) of the Data Product.
     *
     * @return issued
     **/

    public LocalDateTime getIssued() {
        return issued;
    }

    public void setIssued(LocalDateTime issued) {
        this.issued = issued;
    }

    public DataProduct keywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public void addKeywords(String keyword) {
        if (this.keywords == null) {
            this.keywords = keyword;
        } else {
            this.keywords = this.keywords + ",\t" + keyword;
        }
    }

    /**
     * This property contains a keyword or tag describing the Data Product. Multiple entries in a keywords list are typically delimited by commas.
     *
     * @return keywords
     **/

    public String getKeywords() {
        return keywords;
    }


    public DataProduct modified(LocalDateTime modified) {
        this.modified = modified;
        return this;
    }

    /**
     * This property contains the most recent date on which the Data Product was changed or modified.
     *
     * @return modified
     **/

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public DataProduct provenance(List<String> provenance) {
        this.provenance = provenance;
        return this;
    }

    public DataProduct addProvenanceItem(String provenanceItem) {
        if (this.provenance == null) {
            this.provenance = new ArrayList<>();
        }
        this.provenance.add(provenanceItem);
        return this;
    }

    /**
     * This property contains a statement about the lineage of a Data Product
     *
     * @return provenance
     **/

    public List<String> getProvenance() {
        return provenance;
    }

    public void setProvenance(List<String> provenance) {
        this.provenance = provenance;
    }


    public DataProduct spatialExtent(List<Location> spatialExtent) {
        this.spatialExtent = spatialExtent;
        return this;
    }

    public DataProduct addSpatialExtentItem(Location spatialExtentItem) {
        if (this.spatialExtent == null) {
            this.spatialExtent = new ArrayList<>();
        }
        this.spatialExtent.add(spatialExtentItem);
        return this;
    }

    /**
     * This property refers to a geographic region that is covered by the Data Product.
     *
     * @return spatialExtent
     **/
    public List<Location> getSpatialExtent() {
        return spatialExtent;
    }

    public void setSpatialExtent(ArrayList<Location> spatialExtent) {
        this.spatialExtent = spatialExtent;
    }

    public DataProduct temporalExtent(List<PeriodOfTime> temporalExtent) {
        this.temporalExtent = temporalExtent;
        return this;
    }

    public DataProduct addTemporalExtentItem(PeriodOfTime temporalExtentItem) {
        if (this.temporalExtent == null) {
            this.temporalExtent = new ArrayList<>();
        }
        this.temporalExtent.add(temporalExtentItem);
        return this;
    }

    /**
     * This property refers to a temporal period (i.e. startDate, endDate) that the Data Product covers.
     *
     * @return temporalExtent
     **/
    public List<PeriodOfTime> getTemporalExtent() {
        return temporalExtent;
    }

    public void setTemporalExtent(List<PeriodOfTime> temporalExtent) {
        this.temporalExtent = temporalExtent;
    }

    public DataProduct title(List<String> title) {
        this.title = title;
        return this;
    }

    public DataProduct addTitleItem(String titleItem) {
        this.title.add(titleItem);
        return this;
    }

    /**
     * This property contains a name given to the Data Product. This property can be repeated for parallel language versions of the name.
     *
     * @return title
     **/

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public DataProduct type(String type) {
        this.type = type;
        return this;
    }

    /**
     * This property refers to the type of the Data Product. A controlled vocabulary for the values has not been established.
     *
     * @return type
     **/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataProduct versionInfo(String versionInfo) {
        this.versionInfo = versionInfo;
        return this;
    }

    /**
     * This property contains a version number or other version designation of the Data Product.
     *
     * @return versionInfo
     **/

    public String getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(String versionInfo) {
        this.versionInfo = versionInfo;
    }

    public String getHasQualityAnnotation() {
        return hasQualityAnnotation;
    }

    public void setHasQualityAnnotation(String hasQualityAnnotation) {
        this.hasQualityAnnotation = hasQualityAnnotation;
    }


    public List<LinkedEntity> getContactPoint() {
        return contactPoint;
    }

    public void setContactPoint(List<LinkedEntity> contactPoint) {
        this.contactPoint = contactPoint;
    }

    public List<LinkedEntity> getDistribution() {
        return distribution;
    }

    public void setDistribution(List<LinkedEntity> distribution) {
        this.distribution = distribution;
    }

    public List<LinkedEntity> getHasPart() {
        return hasPart;
    }

    public void setHasPart(List<LinkedEntity> hasPart) {
        this.hasPart = hasPart;
    }

    public List<LinkedEntity> getIsPartOf() {
        return isPartOf;
    }

    public void setIsPartOf(List<LinkedEntity> isPartOf) {
        this.isPartOf = isPartOf;
    }

    public List<LinkedEntity> getPublisher() {
        return publisher;
    }

    public void setPublisher(List<LinkedEntity> publisher) {
        this.publisher = publisher;
    }

    public List<LinkedEntity> getRelation() {
        return relation;
    }

    public void setRelation(List<LinkedEntity> relation) {
        this.relation = relation;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getQualityAssurance() {
        return qualityAssurance;
    }

    public DataProduct setQualityAssurance(String qualityAssurance) {
        this.qualityAssurance = qualityAssurance;
        return this;
    }

    @Override
    public String toString() {
        return "DataProduct{ accessRight='" + accessRight + '\'' +
                ", dctIdentifier='" + dctIdentifier + '\'' +
                ", accrualPeriodicity='" + accrualPeriodicity + '\'' +
                ", category=" + category +
                ", contactPoint=" + contactPoint +
                ", created=" + created +
                ", description=" + description +
                ", distribution=" + distribution +
                ", hasPart=" + hasPart +
                ", identifier=" + identifier +
                ", isPartOf=" + isPartOf +
                ", issued=" + issued +
                ", keywords='" + keywords + '\'' +
                ", modified=" + modified +
                ", provenance=" + provenance +
                ", publisher=" + publisher +
                ", relation=" + relation +
                ", spatialExtent=" + spatialExtent +
                ", temporalExtent=" + temporalExtent +
                ", title=" + title +
                ", type='" + type + '\'' +
                ", versionInfo='" + versionInfo + '\'' +
                ", documentation='" + documentation + '\'' +
                ", qualityAssurance='" + qualityAssurance + '\'' +
                ", hasQualityAnnotation='" + hasQualityAnnotation + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DataProduct that = (DataProduct) o;
        return Objects.equals(getAccessRight(), that.getAccessRight()) && Objects.equals(getDctIdentifier(), that.getDctIdentifier()) && Objects.equals(getAccrualPeriodicity(), that.getAccrualPeriodicity()) && Objects.equals(getCategory(), that.getCategory()) && Objects.equals(getContactPoint(), that.getContactPoint()) && Objects.equals(getCreated(), that.getCreated()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getDistribution(), that.getDistribution()) && Objects.equals(getHasPart(), that.getHasPart()) && Objects.equals(getIdentifier(), that.getIdentifier()) && Objects.equals(getIsPartOf(), that.getIsPartOf()) && Objects.equals(getIssued(), that.getIssued()) && Objects.equals(getKeywords(), that.getKeywords()) && Objects.equals(getModified(), that.getModified()) && Objects.equals(getProvenance(), that.getProvenance()) && Objects.equals(getPublisher(), that.getPublisher()) && Objects.equals(getRelation(), that.getRelation()) && Objects.equals(getSpatialExtent(), that.getSpatialExtent()) && Objects.equals(getTemporalExtent(), that.getTemporalExtent()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getType(), that.getType()) && Objects.equals(getVersionInfo(), that.getVersionInfo()) && Objects.equals(getDocumentation(), that.getDocumentation()) && Objects.equals(qualityAssurance, that.qualityAssurance) && Objects.equals(getHasQualityAnnotation(), that.getHasQualityAnnotation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAccessRight(), getDctIdentifier(), getAccrualPeriodicity(), getCategory(), getContactPoint(), getCreated(), getDescription(), getDistribution(), getHasPart(), getIdentifier(), getIsPartOf(), getIssued(), getKeywords(), getModified(), getProvenance(), getPublisher(), getRelation(), getSpatialExtent(), getTemporalExtent(), getTitle(), getType(), getVersionInfo(), getDocumentation(), qualityAssurance, getHasQualityAnnotation());
    }
}
