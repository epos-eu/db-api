package org.epos.eposdatamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents software package, application and program.
 */
public class SoftwareApplication extends Software {
    /**
     * This property refers to a category of the Software Application. A Software Application may be associated with multiple categories.
     **/
    private List<String> category;

    /**
     * This property refers to the Contact Point (i.e. Role) defined for the Software
     * Application.
     */
    private List<LinkedEntity> contactPoint;

    /**
     * This property contains the description of the Software Application
     **/
    private String description;

    /**
     * If the Software Application can be downloaded this property contains the URL to download it.
     **/
    private String downloadURL;

    /**
     * This property contains an identifier for the Software Application.
     **/
    private List<Identifier> identifier = new ArrayList<>();

    /**
     * This property contains the URL at which the application may be installed.
     **/
    private String installURL;

    /**
     * This property contains the keywords used to describe the Software Application. Multiple entries in a keywords list are typically delimited by commas.
     **/
    private String keywords;

    /**
     * This property contains the URL of the license document that applies to the Software Application.
     **/
    private String licenseURL;

    /**
     * This property refers to the web page URL which describes the Software Application.
     **/
    private String mainEntityOfPage;

    /**
     * This property contains the name of the Software Application
     **/
    private String name;

    /**
     * This property represents an input or output parameters of the Software Application.
     **/
    private List<Parameter> parameter;

    /**
     * It represents the link to another Epos resource. e.g. Software, WebService,
     * Operation linked to this software.
     */
    private List<LinkedEntity> relation;

    /**
     * Component dependency requirements for application. This includes runtime environments and shared libraries that are not included in the application distribution package, but required to run the application.
     **/
    private String requirements;

    /**
     * This property contains the version of the Software Application instance.
     **/
    private String softwareVersion;

    public void addRelation(LinkedEntity relation) {
        if (this.getRelation() == null) {
            ArrayList<LinkedEntity> relationList = new ArrayList<>();
            relationList.add(relation);
            this.setRelation(relationList);
        } else {
            this.getRelation().add(relation);
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

    public void addIdentifier(Identifier identifier) {
        if (this.getIdentifier() == null) {
            ArrayList<Identifier> identifierList = new ArrayList<>();
            identifierList.add(identifier);
            this.setIdentifier(identifierList);
        } else {
            this.getIdentifier().add(identifier);
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

    public void addParameter(Parameter parameter) {
        if (this.getParameter() == null) {
            ArrayList<Parameter> parameterList = new ArrayList<>();
            parameterList.add(parameter);
            this.setParameter(parameterList);
        } else {
            this.getParameter().add(parameter);
        }
    }


    public SoftwareApplication category(List<String> category) {
        this.category = category;
        return this;
    }

    public SoftwareApplication addCategoryItem(String categoryItem) {
        if (this.category == null) {
            this.category = new ArrayList<>();
        }
        this.category.add(categoryItem);
        return this;
    }

    /**
     * This property refers to a category of the Software Application. A Software Application may be associated with multiple categories.
     *
     * @return category
     **/

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public SoftwareApplication description(String description) {
        this.description = description;
        return this;
    }

    /**
     * This property contains the description of the Software Application
     *
     * @return description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SoftwareApplication downloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
        return this;
    }

    /**
     * If the Software Application can be downloaded this property contains the URL to download it.
     *
     * @return downloadURL
     **/

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    public SoftwareApplication identifier(List<Identifier> identifier) {
        this.identifier = identifier;
        return this;
    }

    public SoftwareApplication addIdentifierItem(Identifier identifierItem) {
        this.identifier.add(identifierItem);
        return this;
    }

    /**
     * This property contains an identifier for the Software Application.
     *
     * @return identifier
     **/

    public List<Identifier> getIdentifier() {
        return identifier;
    }

    public void setIdentifier(List<Identifier> identifier) {
        this.identifier = identifier;
    }

    public SoftwareApplication installURL(String installURL) {
        this.installURL = installURL;
        return this;
    }

    /**
     * This property contains the URL at which the application may be installed.
     *
     * @return installURL
     **/

    public String getInstallURL() {
        return installURL;
    }

    public void setInstallURL(String installURL) {
        this.installURL = installURL;
    }

    public SoftwareApplication keywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    /**
     * This property contains the keywords used to describe the Software Application. Multiple entries in a keywords list are typically delimited by commas.
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


    public SoftwareApplication licenseURL(String licenseURL) {
        this.licenseURL = licenseURL;
        return this;
    }

    /**
     * This property contains the URL of the license document that applies to the Software Application.
     *
     * @return licenseURL
     **/

    public String getLicenseURL() {
        return licenseURL;
    }

    public void setLicenseURL(String licenseURL) {
        this.licenseURL = licenseURL;
    }

    public SoftwareApplication mainEntityOfPage(String mainEntityOfPage) {
        this.mainEntityOfPage = mainEntityOfPage;
        return this;
    }

    /**
     * This property refers to the web page URL which describes the Software Application.
     *
     * @return mainEntityofPage
     **/

    public String getMainEntityOfPage() {
        return mainEntityOfPage;
    }

    public void setMainEntityOfPage(String mainEntityOfPage) {
        this.mainEntityOfPage = mainEntityOfPage;
    }

    public SoftwareApplication name(String name) {
        this.name = name;
        return this;
    }

    /**
     * This property contains the name of the Software Application
     *
     * @return name
     **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SoftwareApplication parameter(ArrayList<Parameter> parameter) {
        this.parameter = parameter;
        return this;
    }

    public SoftwareApplication addParameterItem(Parameter parameterItem) {
        if (this.parameter == null) {
            this.parameter = new ArrayList<>();
        }
        this.parameter.add(parameterItem);
        return this;
    }

    /**
     * This property represents an input or output parameters of the Software Application.
     *
     * @return parameter
     **/

    public List<Parameter> getParameter() {
        return parameter;
    }

    public void setParameter(ArrayList<Parameter> parameter) {
        this.parameter = parameter;
    }


    public SoftwareApplication requirements(String requirements) {
        this.requirements = requirements;
        return this;
    }

    /**
     * Component dependency requirements for application. This includes runtime environments and shared libraries that are not included in the application distribution package, but required to run the application.
     *
     * @return requirements
     **/

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public SoftwareApplication softwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
        return this;
    }

    /**
     * This property contains the version of the Software Application instance.
     *
     * @return softwareVersion
     **/

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public List<LinkedEntity> getContactPoint() {
        return contactPoint;
    }

    public void setContactPoint(List<LinkedEntity> contactPoint) {
        this.contactPoint = contactPoint;
    }

    public List<LinkedEntity> getRelation() {
        return relation;
    }

    public void setRelation(List<LinkedEntity> relation) {
        this.relation = relation;
    }


    @Override
    public String toString() {
        return "SoftwareApplication{ category=" + category +
                ", contactPoint=" + contactPoint +
                ", description='" + description + '\'' +
                ", downloadURL='" + downloadURL + '\'' +
                ", identifier=" + identifier +
                ", installURL='" + installURL + '\'' +
                ", keywords='" + keywords + '\'' +
                ", licenseURL='" + licenseURL + '\'' +
                ", mainEntityOfPage='" + mainEntityOfPage + '\'' +
                ", name='" + name + '\'' +
                ", parameter=" + parameter +
                ", relation=" + relation +
                ", requirements='" + requirements + '\'' +
                ", softwareVersion='" + softwareVersion + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SoftwareApplication that = (SoftwareApplication) o;
        return Objects.equals(getCategory(), that.getCategory()) && Objects.equals(getContactPoint(), that.getContactPoint()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getDownloadURL(), that.getDownloadURL()) && Objects.equals(getIdentifier(), that.getIdentifier()) && Objects.equals(getInstallURL(), that.getInstallURL()) && Objects.equals(getKeywords(), that.getKeywords()) && Objects.equals(getLicenseURL(), that.getLicenseURL()) && Objects.equals(getMainEntityOfPage(), that.getMainEntityOfPage()) && Objects.equals(getName(), that.getName()) && Objects.equals(getParameter(), that.getParameter()) && Objects.equals(getRelation(), that.getRelation()) && Objects.equals(getRequirements(), that.getRequirements()) && Objects.equals(getSoftwareVersion(), that.getSoftwareVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCategory(), getContactPoint(), getDescription(), getDownloadURL(), getIdentifier(), getInstallURL(), getKeywords(), getLicenseURL(), getMainEntityOfPage(), getName(), getParameter(), getRelation(), getRequirements(), getSoftwareVersion());
    }
}
