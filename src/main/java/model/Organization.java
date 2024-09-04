package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class Organization {
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
    @Column(name = "acronym", nullable = true, length = 1024)
    private String acronym;
    @Basic
    @Column(name = "leicode", nullable = true, length = 1024)
    private String leicode;
    @Basic
    @Column(name = "legalname", nullable = true, length = 1024)
    private String legalname;
    @Basic
    @Column(name = "address_id", nullable = true, length = 100)
    private String addressId;
    @Basic
    @Column(name = "logo", nullable = true, length = 1024)
    private String logo;
    @Basic
    @Column(name = "url", nullable = true, length = 1024)
    private String url;
    @Basic
    @Column(name = "type", nullable = true, length = 1024)
    private String type;
    @Basic
    @Column(name = "maturity", nullable = true, length = 1024)
    private String maturity;
    @OneToMany(mappedBy = "organizationByOrganizationInstanceId")
    private Collection<DataproductPublisher> dataproductPublishersByInstanceId;
    @OneToMany(mappedBy = "organizationByCreator")
    private Collection<Equipment> equipmentByInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "address_id", referencedColumnName = "instance_id")
    private Address addressByAddressId;
    @OneToMany(mappedBy = "organizationByOrganizationInstanceId")
    private Collection<OrganizationAffiliation> organizationAffiliationsByInstanceId;
    @OneToMany(mappedBy = "organizationByOrganizationInstanceId")
    private Collection<OrganizationContactpoint> organizationContactpointsByInstanceId;
    @OneToMany(mappedBy = "organizationByOrganizationInstanceId")
    private Collection<OrganizationElement> organizationElementsByInstanceId;
    @OneToMany(mappedBy = "organizationByOrganizationInstanceId")
    private Collection<OrganizationIdentifier> organizationIdentifiersByInstanceId;
    @OneToMany(mappedBy = "organizationByOrganization1InstanceId")
    private Collection<OrganizationMemberof> organizationMemberofsByInstanceId;
    @OneToMany(mappedBy = "organizationByOrganization2InstanceId")
    private Collection<OrganizationMemberof> organizationMemberofsByInstanceId_0;
    @OneToOne(mappedBy = "organizationByOrganizationInstanceId")
    private OrganizationOwns organizationOwnsByInstanceId;

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

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getLeicode() {
        return leicode;
    }

    public void setLeicode(String leicode) {
        this.leicode = leicode;
    }

    public String getLegalname() {
        return legalname;
    }

    public void setLegalname(String legalname) {
        this.legalname = legalname;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(that.metaId) : that.metaId != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (versionId != null ? !versionId.equals(that.versionId) : that.versionId != null) return false;
        if (acronym != null ? !acronym.equals(that.acronym) : that.acronym != null) return false;
        if (leicode != null ? !leicode.equals(that.leicode) : that.leicode != null) return false;
        if (addressId != null ? !addressId.equals(that.addressId) : that.addressId != null) return false;
        if (logo != null ? !logo.equals(that.logo) : that.logo != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (maturity != null ? !maturity.equals(that.maturity) : that.maturity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (acronym != null ? acronym.hashCode() : 0);
        result = 31 * result + (leicode != null ? leicode.hashCode() : 0);
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (maturity != null ? maturity.hashCode() : 0);
        return result;
    }

    public Collection<DataproductPublisher> getDataproductPublishersByInstanceId() {
        return dataproductPublishersByInstanceId;
    }

    public void setDataproductPublishersByInstanceId(Collection<DataproductPublisher> dataproductPublishersByInstanceId) {
        this.dataproductPublishersByInstanceId = dataproductPublishersByInstanceId;
    }

    public Collection<Equipment> getEquipmentByInstanceId() {
        return equipmentByInstanceId;
    }

    public void setEquipmentByInstanceId(Collection<Equipment> equipmentByInstanceId) {
        this.equipmentByInstanceId = equipmentByInstanceId;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Address getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(Address addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }

    public Collection<OrganizationAffiliation> getOrganizationAffiliationsByInstanceId() {
        return organizationAffiliationsByInstanceId;
    }

    public void setOrganizationAffiliationsByInstanceId(Collection<OrganizationAffiliation> organizationAffiliationsByInstanceId) {
        this.organizationAffiliationsByInstanceId = organizationAffiliationsByInstanceId;
    }

    public Collection<OrganizationContactpoint> getOrganizationContactpointsByInstanceId() {
        return organizationContactpointsByInstanceId;
    }

    public void setOrganizationContactpointsByInstanceId(Collection<OrganizationContactpoint> organizationContactpointsByInstanceId) {
        this.organizationContactpointsByInstanceId = organizationContactpointsByInstanceId;
    }

    public Collection<OrganizationElement> getOrganizationElementsByInstanceId() {
        return organizationElementsByInstanceId;
    }

    public void setOrganizationElementsByInstanceId(Collection<OrganizationElement> organizationElementsByInstanceId) {
        this.organizationElementsByInstanceId = organizationElementsByInstanceId;
    }

    public Collection<OrganizationIdentifier> getOrganizationIdentifiersByInstanceId() {
        return organizationIdentifiersByInstanceId;
    }

    public void setOrganizationIdentifiersByInstanceId(Collection<OrganizationIdentifier> organizationIdentifiersByInstanceId) {
        this.organizationIdentifiersByInstanceId = organizationIdentifiersByInstanceId;
    }

    public Collection<OrganizationMemberof> getOrganizationMemberofsByInstanceId() {
        return organizationMemberofsByInstanceId;
    }

    public void setOrganizationMemberofsByInstanceId(Collection<OrganizationMemberof> organizationMemberofsByInstanceId) {
        this.organizationMemberofsByInstanceId = organizationMemberofsByInstanceId;
    }

    public Collection<OrganizationMemberof> getOrganizationMemberofsByInstanceId_0() {
        return organizationMemberofsByInstanceId_0;
    }

    public void setOrganizationMemberofsByInstanceId_0(Collection<OrganizationMemberof> organizationMemberofsByInstanceId_0) {
        this.organizationMemberofsByInstanceId_0 = organizationMemberofsByInstanceId_0;
    }

    public OrganizationOwns getOrganizationOwnsByInstanceId() {
        return organizationOwnsByInstanceId;
    }

    public void setOrganizationOwnsByInstanceId(OrganizationOwns organizationOwnsByInstanceId) {
        this.organizationOwnsByInstanceId = organizationOwnsByInstanceId;
    }
}
