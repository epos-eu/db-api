package org.epos.eposdatamodel;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a computer programming source code. Example: Full (compile ready) solutions,
 * code snippet samples, scripts, templates.
 */
public class SoftwareSourceCode extends Software {

    /**
     * This property refers to a category of the Software Source Code. A Software Source Code may be associated with multiple categories.
     **/
    private List<String> category;

    /**
     * This property contains the link to the repository where the un-compiled, human readable code and related code is located (SVN, GitHub, CodePlex)
     **/
    private String codeRepository;

    /**
     * This property refers to the Contact Point (i.e. Role) defined for the Software
     * Source Code.
     */
    private List<LinkedEntity> contactPoint;

    /**
     * This property contains the description of the Software
     **/
    private String description;

    /**
     * If the Software can be downloaded this property contains the URL to download it.
     **/

    private String downloadURL;

    /**
     * This property contains an identifier for the Software.
     **/
    private List<Identifier> identifier = new ArrayList<>();

    /**
     * This property contains the keywords used to describe the Software. Multiple entries in a keywords list are typically delimited by commas.
     **/
    private String keywords;

    /**
     * This property contains the URL of the license document that applies to the Software.
     **/
    private String licenseURL;

    /**
     * This property refers to the web page URL which describes the Software.
     **/
    private String mainEntityofPage;

    /**
     * This property contains the name of the Software
     **/
    private String name;

    /**
     * This property contains the computer programming language used to develop the Software.
     **/
    private List<String> programmingLanguage;

    /**
     * It represents the link to another Epos resource. e.g. Software, WebService,
     * Operation linked to this software.
     */
    private List<LinkedEntity> relation;

    /**
     * This property refers to the runtime platform or script interpreter dependencies (Example - Java v1, Python2.3, .Net Framework 3.0).
     **/

    private String runtimePlatform;

    /**
     * This property contains the version of the Software instance.
     **/
    private String softwareVersion;


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

    public void addProgrammingLanguage(String programmingLanguage) {
        if (this.getProgrammingLanguage() == null) {
            ArrayList<String> programmingLanguageList = new ArrayList<>();
            programmingLanguageList.add(programmingLanguage);
            this.setProgrammingLanguage(programmingLanguageList);
        } else {
            this.getProgrammingLanguage().add(programmingLanguage);
        }
    }

    public void addRelation(LinkedEntity relation) {
        if (this.getRelation() == null) {
            ArrayList<LinkedEntity> relationList = new ArrayList<>();
            relationList.add(relation);
            this.setRelation(relationList);
        } else {
            this.getRelation().add(relation);
        }
    }


    public SoftwareSourceCode category(List<String> category) {
        this.category = category;
        return this;
    }

    public SoftwareSourceCode addCategoryItem(String categoryItem) {
        if (this.category == null) {
            this.category = new ArrayList<>();
        }
        this.category.add(categoryItem);
        return this;
    }

    /**
     * This property refers to a category of the Software Source Code. A Software Source Code may be associated with multiple categories.
     *
     * @return category
     **/
    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public SoftwareSourceCode codeRepository(String codeRepository) {
        this.codeRepository = codeRepository;
        return this;
    }

    /**
     * This property contains the link to the repository where the un-compiled, human readable code and related code is located (SVN, GitHub, CodePlex)
     *
     * @return codeRepository
     **/

    public String getCodeRepository() {
        return codeRepository;
    }

    public void setCodeRepository(String codeRepository) {
        this.codeRepository = codeRepository;
    }


    public SoftwareSourceCode description(String description) {
        this.description = description;
        return this;
    }

    /**
     * This property contains the description of the Software
     *
     * @return description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SoftwareSourceCode downloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
        return this;
    }

    /**
     * If the Software can be downloaded this property contains the URL to download it.
     *
     * @return downloadURL
     **/

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    public SoftwareSourceCode identifier(List<Identifier> identifier) {
        this.identifier = identifier;
        return this;
    }

    public SoftwareSourceCode addIdentifierItem(Identifier identifierItem) {
        this.identifier.add(identifierItem);
        return this;
    }

    /**
     * This property contains an identifier for the Software.
     *
     * @return identifier
     **/

    public List<Identifier> getIdentifier() {
        return identifier;
    }

    public void setIdentifier(List<Identifier> identifier) {
        this.identifier = identifier;
    }

    public SoftwareSourceCode keywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    /**
     * This property contains the keywords used to describe the Software. Multiple entries in a keywords list are typically delimited by commas.
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


    public SoftwareSourceCode licenseURL(String licenseURL) {
        this.licenseURL = licenseURL;
        return this;
    }

    /**
     * This property contains the URL of the license document that applies to the Software.
     *
     * @return licenseURL
     **/

    public String getLicenseURL() {
        return licenseURL;
    }

    public void setLicenseURL(String licenseURL) {
        this.licenseURL = licenseURL;
    }

    public SoftwareSourceCode mainEntityofPage(String mainEntityofPage) {
        this.mainEntityofPage = mainEntityofPage;
        return this;
    }

    /**
     * This property refers to the web page URL which describes the Software.
     *
     * @return mainEntityOfPage
     **/

    public String getMainEntityofPage() {
        return mainEntityofPage;
    }

    public void setMainEntityofPage(String mainEntityofPage) {
        this.mainEntityofPage = mainEntityofPage;
    }

    public SoftwareSourceCode name(String name) {
        this.name = name;
        return this;
    }

    /**
     * This property contains the name of the Software
     *
     * @return name
     **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SoftwareSourceCode programmingLanguage(List<String> programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
        return this;
    }

    public SoftwareSourceCode addProgrammingLanguageItem(String programmingLanguageItem) {
        if (this.programmingLanguage == null) {
            this.programmingLanguage = new ArrayList<>();
        }
        this.programmingLanguage.add(programmingLanguageItem);
        return this;
    }

    /**
     * This property contains the computer programming language used to develop the Software.
     *
     * @return programmingLanguage
     **/

    public List<String> getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(List<String> programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }


    public SoftwareSourceCode runtimePlatform(String runtimePlatform) {
        this.runtimePlatform = runtimePlatform;
        return this;
    }

    /**
     * This property refers to the runtime platform or script interpreter dependencies (Example - Java v1, Python2.3, .Net Framework 3.0).
     *
     * @return runtimePlatform
     **/

    public String getRuntimePlatform() {
        return runtimePlatform;
    }

    public void setRuntimePlatform(String runtimePlatform) {
        this.runtimePlatform = runtimePlatform;
    }

    public SoftwareSourceCode softwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
        return this;
    }

    /**
     * This property contains the version of the Software instance.
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
        return "SoftwareSourceCode{ category=" + category +
                ", codeRepository='" + codeRepository + '\'' +
                ", contactPoint=" + contactPoint +
                ", description='" + description + '\'' +
                ", downloadURL='" + downloadURL + '\'' +
                ", identifier=" + identifier +
                ", keywords='" + keywords + '\'' +
                ", licenseURL='" + licenseURL + '\'' +
                ", mainEntityofPage='" + mainEntityofPage + '\'' +
                ", name='" + name + '\'' +
                ", programmingLanguage=" + programmingLanguage +
                ", relation=" + relation +
                ", runtimePlatform='" + runtimePlatform + '\'' +
                ", softwareVersion='" + softwareVersion + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SoftwareSourceCode that = (SoftwareSourceCode) o;
        return Objects.equals(getCategory(), that.getCategory()) && Objects.equals(getCodeRepository(), that.getCodeRepository()) && Objects.equals(getContactPoint(), that.getContactPoint()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getDownloadURL(), that.getDownloadURL()) && Objects.equals(getIdentifier(), that.getIdentifier()) && Objects.equals(getKeywords(), that.getKeywords()) && Objects.equals(getLicenseURL(), that.getLicenseURL()) && Objects.equals(getMainEntityofPage(), that.getMainEntityofPage()) && Objects.equals(getName(), that.getName()) && Objects.equals(getProgrammingLanguage(), that.getProgrammingLanguage()) && Objects.equals(getRelation(), that.getRelation()) && Objects.equals(getRuntimePlatform(), that.getRuntimePlatform()) && Objects.equals(getSoftwareVersion(), that.getSoftwareVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCategory(), getCodeRepository(), getContactPoint(), getDescription(), getDownloadURL(), getIdentifier(), getKeywords(), getLicenseURL(), getMainEntityofPage(), getName(), getProgrammingLanguage(), getRelation(), getRuntimePlatform(), getSoftwareVersion());
    }
}
