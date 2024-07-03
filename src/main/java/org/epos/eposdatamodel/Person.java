package org.epos.eposdatamodel;

import io.swagger.v3.oas.annotations.media.Schema;
import model.RoleType;
import org.eclipse.persistence.internal.jpa.rs.metadata.model.Link;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Human being.
 */
public class Person extends EPOSDataModelEntity {

    /**
     * This property contains the physical address of the Person.
     */
    @Schema(description = "This property contains the physical address of the Person.", example = "{\n" +
            "    \"entityType\": \"ADDRESS\",\n" +
            "    \"instanceId\": \"an UUID\",\n" +
            "    \"metaId\": \"an UUID\",\n" +
            "    \"uid\": \"an UUID\"\n" +
            "  }", required = false)
    private LinkedEntity address;

    /**
     * This property refers to the organization to which the person is
     * affiliated.
     */
    @Schema(description = "This property refers to the organization to which the person is affiliated.", example = "{\n" +
            "    \"entityType\": \"ORGANIZATION\",\n" +
            "    \"instanceId\": \"an UUID\",\n" +
            "    \"metaId\": \"an UUID\",\n" +
            "    \"uid\": \"an UUID\"\n" +
            "  }", required = false)
    private List<LinkedEntity> affiliation;

    /**
     * This property refers to the URL of the Person curriculum vitae.
     **/
    @Schema(description = "This property refers to the URL of the Person curriculum vitae.", example = "http://urltocv", required = false)
    private String CVURL;

    /**
     * This property contains the email address of the Person.
     **/
    @Schema(description = "This property contains the email address of the Person.", example = "email@email.com", required = false)
    private List<String> email;

    /**
     * This property contains the last name of the Person.
     **/
    @Schema(description = "This property contains the last name of the Person.", example = "Doe", required = false)
    private String familyName;

    /**
     * This property contains the first name of the Person.
     **/
    @Schema(description = "This property contains the first name of the Person.", example = "John", required = false)
    private String givenName;

    /**
     * This property contains an identifier for the Person (e.g., ORCID, ScopusID, etc.).
     **/

    @Schema(description = "This property contains an identifier for the Person (e.g., ORCID, ScopusID, etc.).", example = "{\n" +
            "    \"entityType\": \"IDENTIFIER\",\n" +
            "    \"instanceId\": \"an UUID\",\n" +
            "    \"metaId\": \"an UUID\",\n" +
            "    \"uid\": \"an UUID\"\n" +
            "  }", required = false)
    private List<LinkedEntity> identifier = new ArrayList<>();

    /**
     * This property contains the specific qualifications of the Person.
     **/
    @Schema(description = "his property contains the specific qualifications of the Person.", example = "manager", required = false)
    private List<String> qualifications;

    /**
     * This property contains the telephone number of the Person.
     **/
    @Schema(description = "This property contains the telephone number of the Person.", example = "+0039213134325", required = false)
    private List<String> telephone;

    public void addQualifications(String qualification) {
        if (this.qualifications == null) {
            ArrayList<String> qualificationsList = new ArrayList<>();
            qualificationsList.add(qualification);
            this.setQualifications(qualificationsList);
        } else {
            this.qualifications.add(qualification);
        }
    }

    public void addEmail(String email) {
        if (this.email == null) {
            ArrayList<String> emailList = new ArrayList<>();
            emailList.add(email);
            this.setEmail(emailList);
        } else {
            this.email.add(email);
        }
    }

    public void addTelephone(String telephone) {
        if (this.telephone == null) {
            ArrayList<String> telephoneList = new ArrayList<>();
            telephoneList.add(telephone);
            this.setTelephone(telephoneList);
        } else {
            this.telephone.add(telephone);
        }
    }

    public void addAffiliation(LinkedEntity affiliation) {
        if (this.affiliation == null) {
            ArrayList<LinkedEntity> affiliationList = new ArrayList<>();
            affiliationList.add(affiliation);
            this.setAffiliation(affiliationList);
        } else {
            this.getAffiliation().add(affiliation);
        }
    }

    public void addIdentifier(LinkedEntity identifier) {
        if (this.identifier == null) {
            ArrayList<LinkedEntity> identifierList = new ArrayList<>();
            identifierList.add(identifier);
            this.setIdentifier(identifierList);
        } else {
            this.getIdentifier().add(identifier);
        }
    }


    public Person address(LinkedEntity address) {
        this.address = address;
        return this;
    }

    /**
     * Get address
     *
     * @return address
     **/

    public LinkedEntity getAddress() {
        return address;
    }

    public void setAddress(LinkedEntity address) {
        this.address = address;
    }


    public Person CVURL(String CVURL) {
        this.CVURL = CVURL;
        return this;
    }

    /**
     * This property refers to the URL of the Person curriculum vitae.
     *
     * @return CVURL
     **/

    public String getCVURL() {
        return CVURL;
    }

    public void setCVURL(String CVURL) {
        this.CVURL = CVURL;
    }

    public Person email(List<String> email) {
        this.email = email;
        return this;
    }

    public Person addEmailItem(String emailItem) {
        if (this.email == null) {
            this.email = new ArrayList<>();
        }
        this.email.add(emailItem);
        return this;
    }

    /**
     * This property contains the email address of the Person.
     *
     * @return email
     **/

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public Person familyName(String familyName) {
        this.familyName = familyName;
        return this;
    }

    /**
     * This property contains the last name of the Person.
     *
     * @return familyName
     **/

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Person givenName(String givenName) {
        this.givenName = givenName;
        return this;
    }

    /**
     * This property contains the first name of the Person.
     *
     * @return givenName
     **/

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public Person identifier(List<LinkedEntity> identifier) {
        this.identifier = identifier;
        return this;
    }

    public Person addIdentifierItem(LinkedEntity identifierItem) {
        this.identifier.add(identifierItem);
        return this;
    }

    /**
     * This property contains an identifier for the Person (e.g., ORCID, ScopusID, etc.).
     *
     * @return identifier
     **/


    public List<LinkedEntity> getIdentifier() {
        return identifier;
    }

    public void setIdentifier(List<LinkedEntity> identifier) {
        this.identifier = identifier;
    }


    public Person qualifications(List<String> qualifications) {
        this.qualifications = qualifications;
        return this;
    }

    public Person addQualificationsItem(String qualificationsItem) {
        if (this.qualifications == null) {
            this.qualifications = new ArrayList<>();
        }
        this.qualifications.add(qualificationsItem);
        return this;
    }

    /**
     * This property contains the specific qualifications of the Person.
     *
     * @return qualification
     **/

    public List<String> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<String> qualifications) {
        this.qualifications = qualifications;
    }

    public Person telephone(List<String> telephone) {
        this.telephone = telephone;
        return this;
    }

    public Person addTelephoneItem(String telephoneItem) {
        if (this.telephone == null) {
            this.telephone = new ArrayList<>();
        }
        this.telephone.add(telephoneItem);
        return this;
    }

    /**
     * This property contains the telephone number of the Person.
     *
     * @return telephone
     **/

    public List<String> getTelephone() {
        return telephone;
    }

    public void setTelephone(List<String> telephone) {
        this.telephone = telephone;
    }

    public List<LinkedEntity> getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(List<LinkedEntity> affiliation) {
        this.affiliation = affiliation;
    }
    @Override
    public String toString() {
        return "Person{" +
                "address=" + address +
                ", affiliation=" + affiliation +
                ", CVURL='" + CVURL + '\'' +
                ", email=" + email +
                ", familyName='" + familyName + '\'' +
                ", givenName='" + givenName + '\'' +
                ", identifier=" + identifier +
                ", qualifications=" + qualifications +
                ", telephone=" + telephone +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Objects.equals(getAddress(), person.getAddress()) && Objects.equals(getAffiliation(), person.getAffiliation()) && Objects.equals(getCVURL(), person.getCVURL()) && Objects.equals(getEmail(), person.getEmail()) && Objects.equals(getFamilyName(), person.getFamilyName()) && Objects.equals(getGivenName(), person.getGivenName()) && Objects.equals(getIdentifier(), person.getIdentifier()) && Objects.equals(getQualifications(), person.getQualifications()) && Objects.equals(getTelephone(), person.getTelephone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAddress(), getAffiliation(), getCVURL(), getEmail(), getFamilyName(), getGivenName(), getIdentifier(), getQualifications(), getTelephone());
    }
}
