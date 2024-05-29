package org.epos.eposdatamodel;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Online interfaces (APIs) enabling a user or a machine to programmatically access the given resource.
 */
public class WebService extends EPOSDataModelEntity {

    /**
     * This property refers to a secondary identifier of the Web Service, such as MAST/ADS, DataCite, DOI, EZID or W3ID.
     **/
    private List<Identifier> identifier;

    /**
     * This property contains the single idenfier of the WebService.
     */
    private String schemaIdentifier;

    /**
     * This property refers to a category of the Web Service. A Web Service may be associated with multiple categories.
     **/
    private List<String> category;

    /**
     * This property contains contact information (i.e. Role) that can be used for
     * sending comments about the Web Service.
     */
    private List<LinkedEntity> contactPoint;

    /**
     * This property contains the most recent date on which the Web Service was modified.
     **/
    private LocalDateTime dateModified;

    /**
     * This property contains the date of publication of the Web Service.
     **/
    private LocalDateTime datePublished;

    /**
     * This property contains a free-text description of the Web Service.
     **/
    private String description;

    /**
     * This property refers to the API documentation.
     **/
    private List<Documentation> documentation;

    /**
     * This property refers to the API definitions (e.g., WSDL, WADL)
     **/
    private String entryPoint;

    /**
     * This property contains the keywords used to describe the Web Service. Multiple entries in a keywords list are typically delimited by commas.
     **/
    private String keywords;

    /**
     * This property refers to the licence under which the Web Service can be used or reused.
     **/
    private String license;

    /**
     * This property contains a name given to the Web Service.
     **/
    private String name;

    /**
     * This property refers to an Organisation responsible for making the Web Service available.
     **/
    private LinkedEntity provider;

    /**
     * This property refers to a geographical area covered by the Web Service.
     **/
    private List<Location> spatialExtent;

    /**
     * This property refers to a web service operation supported by the
     * Web Service.
     */
    private List<LinkedEntity> supportedOperation;

    /**
     * This property refers to a temporal period (i.e. startDate, endDate) that the Web Service covers.
     **/
    private List<PeriodOfTime> temporalExtent;

    /**
     * Reverse reference to the related distribution.
     */
    private List<LinkedEntity> distribution;
    
    /**
     * It represents the link to another Epos resource.
     */
    private List<LinkedEntity> relation;


    /**
     * List of possible authentication/authorization methods supported.
     */
    private String aaaiTypes;
    

    public void addRelation(LinkedEntity relation) {
        if (this.getRelation() == null) {
            ArrayList<LinkedEntity> relationList = new ArrayList<>();
            relationList.add(relation);
            this.setRelation(relationList);
        } else {
            this.getRelation().add(relation);
        }
    }


    public void addSpatialExtent(Location spatialExtent) {
        if (this.getSpatialExtent() == null) {
            ArrayList<Location> spatialExtentList = new ArrayList<>();
            spatialExtentList.add(spatialExtent);
            this.setSpatialExtent(spatialExtentList);
        } else {
            this.getSpatialExtent().add(spatialExtent);
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

    public void addSupportedOperation(LinkedEntity supportedOperation) {
        if (this.getSupportedOperation() == null) {
            ArrayList<LinkedEntity> supportedOperationList = new ArrayList<>();
            supportedOperationList.add(supportedOperation);
            this.setSupportedOperation(supportedOperationList);
        } else {
            this.getSupportedOperation().add(supportedOperation);
        }
    }

    public void addDocumentation(Documentation documentation) {
        if (this.getDocumentation() == null) {
            ArrayList<Documentation> documentationList = new ArrayList<>();
            documentationList.add(documentation);
            this.setDocumentation(documentationList);
        } else {
            this.getDocumentation().add(documentation);
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

    public void addIdentifier(Identifier identifier) {
        if (this.getIdentifier() == null) {
            ArrayList<Identifier> identifierList = new ArrayList<>();
            identifierList.add(identifier);
            this.setIdentifier(identifierList);
        } else {
            this.getIdentifier().add(identifier);
        }
    }


    public WebService schemaIdentifier(String schemaIdentifier) {
        this.schemaIdentifier = schemaIdentifier;
        return this;
    }

    public String getSchemaIdentifier() {
        return schemaIdentifier;
    }

    public void setSchemaIdentifier(String schemaIdentifier) {
        this.schemaIdentifier = schemaIdentifier;
    }

    public WebService identifier(List<Identifier> identifier) {
        this.identifier = identifier;
        return this;
    }

    public WebService addIdentifierItem(Identifier identifierItem) {
        if (this.identifier == null) {
            this.identifier = new ArrayList<>();
        }
        this.identifier.add(identifierItem);
        return this;
    }

    /**
     * This property refers to a secondary identifier of the Web Service, such as MAST/ADS, DataCite, DOI, EZID or W3ID.
     *
     * @return identifier
     **/
    public List<Identifier> getIdentifier() {
        return identifier;
    }

    public void setIdentifier(List<Identifier> identifier) {
        this.identifier = identifier;
    }

    public WebService category(List<String> category) {
        this.category = category;
        return this;
    }

    public WebService addCategoryItem(String categoryItem) {
        if (this.category == null) {
            this.category = new ArrayList<>();
        }
        this.category.add(categoryItem);
        return this;
    }

    /**
     * This property refers to a category of the Web Service. A Web Service may be associated with multiple categories.
     *
     * @return category
     **/

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }


    public WebService dateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
        return this;
    }

    /**
     * This property contains the most recent date on which the Web Service was modified.
     *
     * @return dateModified
     **/

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    public WebService datePublished(LocalDateTime datePublished) {
        this.datePublished = datePublished;
        return this;
    }

    /**
     * This property contains the date of publication of the Web Service.
     *
     * @return datePublished
     **/

    public LocalDateTime getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDateTime datePublished) {
        this.datePublished = datePublished;
    }

    public WebService description(String description) {
        this.description = description;
        return this;
    }

    /**
     * This property contains a free-text description of the Web Service.
     *
     * @return description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WebService documentation(List<Documentation> documentation) {
        this.documentation = documentation;
        return this;
    }

    public WebService addDocumentationItem(Documentation documentationItem) {
        if (this.documentation == null) {
            this.documentation = new ArrayList<>();
        }
        this.documentation.add(documentationItem);
        return this;
    }

    /**
     * This property refers to the API documentation.
     *
     * @return documentation
     **/
    public List<Documentation> getDocumentation() {
        return documentation;
    }

    public void setDocumentation(ArrayList<Documentation> documentation) {
        this.documentation = documentation;
    }

    public WebService entryPoint(String entryPoint) {
        this.entryPoint = entryPoint;
        return this;
    }

    /**
     * This property refers to the API definitions (e.g., WSDL, WADL)
     *
     * @return entryPoint
     **/

    public String getEntryPoint() {
        return entryPoint;
    }

    public void setEntryPoint(String entryPoint) {
        this.entryPoint = entryPoint;
    }

    public WebService keywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    /**
     * This property contains the keywords used to describe the Web Service. Multiple entries in a keywords list are typically delimited by commas.
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


    public WebService license(String license) {
        this.license = license;
        return this;
    }

    /**
     * This property refers to the licence under which the Web Service can be used or reused.
     *
     * @return license
     **/

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public WebService name(String name) {
        this.name = name;
        return this;
    }

    /**
     * This property contains a name given to the Web Service.
     *
     * @return name
     **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WebService provider(LinkedEntity provider) {
        this.provider = provider;
        return this;
    }

    /**
     * This property refers to an Organisation responsible for making the Web Service available.
     *
     * @return provider
     **/

    public LinkedEntity getProvider() {
        return provider;
    }

    public void setProvider(LinkedEntity provider) {
        this.provider = provider;
    }

    public WebService spatialExtent(List<Location> spatialExtent) {
        this.spatialExtent = spatialExtent;
        return this;
    }

    public WebService addSpatialExtentItem(Location spatialExtentItem) {
        if (this.spatialExtent == null) {
            this.spatialExtent = new ArrayList<>();
        }
        this.spatialExtent.add(spatialExtentItem);
        return this;
    }

    /**
     * This property refers to a geographical area covered by the Web Service.
     *
     * @return spatialExtent
     **/
    public List<Location> getSpatialExtent() {
        return spatialExtent;
    }

    public void setSpatialExtent(ArrayList<Location> spatialExtent) {
        this.spatialExtent = spatialExtent;
    }


    public WebService temporalExtent(List<PeriodOfTime> temporalExtent) {
        this.temporalExtent = temporalExtent;
        return this;
    }

    public WebService addTemporalExtentItem(PeriodOfTime temporalExtentItem) {
        if (this.temporalExtent == null) {
            this.temporalExtent = new ArrayList<>();
        }
        this.temporalExtent.add(temporalExtentItem);
        return this;
    }

    /**
     * This property refers to a temporal period (i.e. startDate, endDate) that the Web Service covers.
     *
     * @return temporalExtent
     **/
    public List<PeriodOfTime> getTemporalExtent() {
        return temporalExtent;
    }

    public void setTemporalExtent(List<PeriodOfTime> temporalExtent) {
        this.temporalExtent = temporalExtent;
    }

    public List<LinkedEntity> getContactPoint() {
        return contactPoint;
    }

    public void setContactPoint(List<LinkedEntity> contactPoint) {
        this.contactPoint = contactPoint;
    }

    public List<LinkedEntity> getSupportedOperation() {
        return supportedOperation;
    }

    public void setSupportedOperation(List<LinkedEntity> supportedOperation) {
        this.supportedOperation = supportedOperation;
    }

    public String getAaaiTypes() {
        return aaaiTypes;
    }

    public void setAaaiTypes(String aaaiTypes) {
        this.aaaiTypes = aaaiTypes;
    }

    public List<LinkedEntity> getDistribution() {
        return distribution;
    }

    public WebService setDistribution(List<LinkedEntity> distribution) {
        this.distribution = distribution;
        return this;
    }
    
    public List<LinkedEntity> getRelation() {
        return relation;
    }

    public void setRelation(List<LinkedEntity> relation) {
        this.relation = relation;
    }

    @Override
    public String toString() {
        return "WebService{" +
                "identifier=" + identifier +
                ", schemaIdentifier='" + schemaIdentifier + '\'' +
                ", category=" + category +
                ", contactPoint=" + contactPoint +
                ", dateModified=" + dateModified +
                ", datePublished=" + datePublished +
                ", description='" + description + '\'' +
                ", documentation=" + documentation +
                ", entryPoint='" + entryPoint + '\'' +
                ", keywords='" + keywords + '\'' +
                ", license='" + license + '\'' +
                ", name='" + name + '\'' +
                ", provider=" + provider +
                ", spatialExtent=" + spatialExtent +
                ", supportedOperation=" + supportedOperation +
                ", temporalExtent=" + temporalExtent +
                ", distribution=" + distribution +
                ", relation=" + relation +
                ", aaaiTypes='" + aaaiTypes + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WebService that = (WebService) o;
        return Objects.equals(getIdentifier(), that.getIdentifier()) && Objects.equals(getSchemaIdentifier(), that.getSchemaIdentifier()) && Objects.equals(getCategory(), that.getCategory()) && Objects.equals(getContactPoint(), that.getContactPoint()) && Objects.equals(getDateModified(), that.getDateModified()) && Objects.equals(getDatePublished(), that.getDatePublished()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getDocumentation(), that.getDocumentation()) && Objects.equals(getEntryPoint(), that.getEntryPoint()) && Objects.equals(getKeywords(), that.getKeywords()) && Objects.equals(getLicense(), that.getLicense()) && Objects.equals(getName(), that.getName()) && Objects.equals(getProvider(), that.getProvider()) && Objects.equals(getSpatialExtent(), that.getSpatialExtent()) && Objects.equals(getSupportedOperation(), that.getSupportedOperation()) && Objects.equals(getTemporalExtent(), that.getTemporalExtent()) && Objects.equals(getDistribution(), that.getDistribution()) && Objects.equals(getAaaiTypes(), that.getAaaiTypes()) && Objects.equals(getRelation(), that.getRelation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getIdentifier(), getSchemaIdentifier(), getCategory(), getContactPoint(), getDateModified(), getDatePublished(), getDescription(), getDocumentation(), getEntryPoint(), getKeywords(), getLicense(), getName(), getProvider(), getSpatialExtent(), getSupportedOperation(), getTemporalExtent(), getDistribution(), getAaaiTypes());
    }
}
