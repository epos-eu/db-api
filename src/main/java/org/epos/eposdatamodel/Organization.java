package org.epos.eposdatamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Institution or International organization.
 */
public class Organization extends EPOSDataModelEntity {

    /**
     * This property contains the physical address of the Organization.
     **/
    private Address address;

    /**
     * This property refers to the Contact Point (i.e. Role) defined for the Organization
     */
    private List<LinkedEntity> contactPoint;

    /**
     * This property contains the email address of the Organization.
     **/
    private List<String> email;

    /**
     * This property contains an identifier for the Organization (e.g., PIC, ISNI, etc.).
     **/
    private List<Identifier> identifier = new ArrayList<>();

    /**
     * This property contains the Organization acronym (e.g., INGV).
     */
    private String acronym;

    /**
     * This property contains the official name of the Organization.
     **/
    private List<String> legalName;

    /**
     * This property contains the Organization identifier that uniquely identifies a legal entity as defined in ISO 17442.
     **/
    private String leiCode;

    /**
     * This property contains the URL of the Organization logo.
     **/
    private String logo;

    /**
     * This property refers to an Organization to which this Organization belongs.
     */
    private List<LinkedEntity> memberOf;

    /**
     * This property refers to a Facility or Equipment of which it is the owner.
     **/
    private List<String> owns;

    /**
     * This property contains the telephone number of the Organization.
     **/
    private List<String> telephone;

    /**
     * This property contains the URL of the Organization website.
     **/
    private String URL;

    /**
     * This property refers to the type of Organization (e.g., TCS, TCS internal
     * consortia, independent (INTERMAGNET), national EPOS consortia). A
     * controlled list for the values should be established.
     */
    private String type;

    /**
     * This property contains information about the status of the Organization with
     * respect to EPOS (e.g., existing TCS, candidate TCS, external service). A
     * controlled list for the values should be established.
     */
    private String maturity;


    public void addIdentifier(Identifier identifier) {
        if (this.getIdentifier() == null) {
            ArrayList<Identifier> identifierList = new ArrayList<>();
            identifierList.add(identifier);
            this.setIdentifier(identifierList);
        } else {
            this.getIdentifier().add(identifier);
        }
    }

    public void addEmail(String email) {
        if (this.getEmail() == null) {
            ArrayList<String> emailList = new ArrayList<>();
            emailList.add(email);
            this.setEmail(emailList);
        } else {
            this.getEmail().add(email);
        }
    }

    public void addTelephone(String telephone) {
        if (this.getTelephone() == null) {
            ArrayList<String> telephoneList = new ArrayList<>();
            telephoneList.add(telephone);
            this.setTelephone(telephoneList);
        } else {
            this.getTelephone().add(telephone);
        }
    }

    public void addLegalName(String legalName) {
        if (this.getLegalName() == null) {
            ArrayList<String> legalNameList = new ArrayList<>();
            legalNameList.add(legalName);
            this.setLegalName(legalNameList);
        } else {
            this.getLegalName().add(legalName);
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

    public void addMemberOf(LinkedEntity memberOf) {
        if (this.getMemberOf() == null) {
            ArrayList<LinkedEntity> memberOfList = new ArrayList<>();
            memberOfList.add(memberOf);
            this.setMemberOf(memberOfList);
        } else {
            this.getMemberOf().add(memberOf);
        }
    }


    public Organization address(Address address) {
        this.address = address;
        return this;
    }

    /**
     * Get address
     *
     * @return address
     **/
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public Organization email(List<String> email) {
        this.email = email;
        return this;
    }

    public Organization addEmailItem(String emailItem) {
        if (this.email == null) {
            this.email = new ArrayList<>();
        }
        this.email.add(emailItem);
        return this;
    }

    /**
     * This property contains the email address of the Organization.
     *
     * @return email
     **/

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public Organization identifier(List<Identifier> identifier) {
        this.identifier = identifier;
        return this;
    }

    public Organization addIdentifierItem(Identifier identifierItem) {
        this.identifier.add(identifierItem);
        return this;
    }

    /**
     * This property contains an identifier for the Organization (e.g., PIC, ISNI, etc.).
     *
     * @return identifier
     **/
    public List<Identifier> getIdentifier() {
        return identifier;
    }

    public void setIdentifier(List<Identifier> identifier) {
        this.identifier = identifier;
    }

    public Organization legalName(List<String> legalName) {
        this.legalName = legalName;
        return this;
    }

    /**
     * This property contains the official name of the Organization.
     *
     * @return legalName
     **/

    public List<String> getLegalName() {
        return legalName;
    }

    public void setLegalName(List<String> legalName) {
        this.legalName = legalName;
    }

    public Organization leiCode(String leiCode) {
        this.leiCode = leiCode;
        return this;
    }

    /**
     * This property contains the Organization identifier that uniquely identifies a legal entity as defined in ISO 17442.
     *
     * @return leiCode
     **/

    public String getLeiCode() {
        return leiCode;
    }

    public void setLeiCode(String leiCode) {
        this.leiCode = leiCode;
    }

    public Organization logo(String logo) {
        this.logo = logo;
        return this;
    }

    /**
     * This property contains the URL of the Organization logo.
     *
     * @return logo
     **/

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


    public Organization owns(List<String> owns) {
        this.owns = owns;
        return this;
    }

    public Organization addOwns(String ownsItem) {
        if (this.owns == null) {
            this.owns = new ArrayList<>();
        }
        this.owns.add(ownsItem);
        return this;
    }

    /**
     * This property refers to a Facility or Equipment of which it is the owner.
     *
     * @return owns
     **/

    public List<String> getOwns() {
        return owns;
    }

    public void setOwns(List<String> owns) {
        this.owns = owns;
    }

    public Organization telephone(List<String> telephone) {
        this.telephone = telephone;
        return this;
    }

    public Organization addTelephoneItem(String telephoneItem) {
        if (this.telephone == null) {
            this.telephone = new ArrayList<>();
        }
        this.telephone.add(telephoneItem);
        return this;
    }

    /**
     * This property contains the telephone number of the Organization.
     *
     * @return telephone
     **/

    public List<String> getTelephone() {
        return telephone;
    }

    public void setTelephone(List<String> telephone) {
        this.telephone = telephone;
    }

    public Organization URL(String URL) {
        this.URL = URL;
        return this;
    }

    /**
     * This property contains the URL of the Organization website.
     *
     * @return URL
     **/

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public List<LinkedEntity> getContactPoint() {
        return contactPoint;
    }

    public void setContactPoint(List<LinkedEntity> contactPoint) {
        this.contactPoint = contactPoint;
    }

    public List<LinkedEntity> getMemberOf() {
        return memberOf;
    }

    public void setMemberOf(List<LinkedEntity> memberOf) {
        this.memberOf = memberOf;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaturity() {
        return maturity;
    }

    public void setMaturity(String maturity) {
        this.maturity = maturity;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "address=" + address +
                ", contactPoint=" + contactPoint +
                ", email=" + email +
                ", identifier=" + identifier +
                ", acronym='" + acronym + '\'' +
                ", legalName='" + legalName + '\'' +
                ", leiCode='" + leiCode + '\'' +
                ", logo='" + logo + '\'' +
                ", memberOf=" + memberOf +
                ", owns=" + owns +
                ", telephone=" + telephone +
                ", URL='" + URL + '\'' +
                ", type='" + type + '\'' +
                ", maturity='" + maturity + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Organization that = (Organization) o;
        return Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getContactPoint(), that.getContactPoint()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getIdentifier(), that.getIdentifier()) && Objects.equals(getAcronym(), that.getAcronym()) && Objects.equals(getLegalName(), that.getLegalName()) && Objects.equals(getLeiCode(), that.getLeiCode()) && Objects.equals(getLogo(), that.getLogo()) && Objects.equals(getMemberOf(), that.getMemberOf()) && Objects.equals(getOwns(), that.getOwns()) && Objects.equals(getTelephone(), that.getTelephone()) && Objects.equals(getURL(), that.getURL()) && Objects.equals(getType(), that.getType()) && Objects.equals(getMaturity(), that.getMaturity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAddress(), getContactPoint(), getEmail(), getIdentifier(), getAcronym(), getLegalName(), getLeiCode(), getLogo(), getMemberOf(), getOwns(), getTelephone(), getURL(), getType(), getMaturity());
    }
}