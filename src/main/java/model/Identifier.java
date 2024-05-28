package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class Identifier {
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
    @Column(name = "type", nullable = false, length = 1024)
    private String type;
    @Basic
    @Column(name = "value", nullable = false, length = 1024)
    private String value;
    @OneToMany(mappedBy = "identifierByIdentifierInstanceId")
    private Collection<DataproductIdentifier> dataproductIdentifiersByInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @OneToMany(mappedBy = "identifierByIdentifierInstanceId")
    private Collection<OrganizationIdentifier> organizationIdentifiersByInstanceId;
    @OneToMany(mappedBy = "identifierByIdentifierInstanceId")
    private Collection<PersonIdentifier> personIdentifiersByInstanceId;
    @OneToMany(mappedBy = "identifierByIdentifierInstanceId")
    private Collection<PublicationIdentifier> publicationIdentifiersByInstanceId;
    @OneToMany(mappedBy = "identifierByIdentifierInstanceId")
    private Collection<ServiceIdentifier> serviceIdentifiersByInstanceId;
    @OneToMany(mappedBy = "identifierByIdentifierInstanceId")
    private Collection<SoftwareapplicationIdentifier> softwareapplicationIdentifiersByInstanceId;
    @OneToMany(mappedBy = "identifierByIdentifierInstanceId")
    private Collection<SoftwaresourcecodeIdentifier> softwaresourcecodeIdentifiersByInstanceId;
    @OneToMany(mappedBy = "identifierByIdentifierInstanceId")
    private Collection<WebserviceIdentifier> webserviceIdentifiersByInstanceId;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Identifier that = (Identifier) o;

        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(that.metaId) : that.metaId != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (versionId != null ? !versionId.equals(that.versionId) : that.versionId != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    public Collection<DataproductIdentifier> getDataproductIdentifiersByInstanceId() {
        return dataproductIdentifiersByInstanceId;
    }

    public void setDataproductIdentifiersByInstanceId(Collection<DataproductIdentifier> dataproductIdentifiersByInstanceId) {
        this.dataproductIdentifiersByInstanceId = dataproductIdentifiersByInstanceId;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Collection<OrganizationIdentifier> getOrganizationIdentifiersByInstanceId() {
        return organizationIdentifiersByInstanceId;
    }

    public void setOrganizationIdentifiersByInstanceId(Collection<OrganizationIdentifier> organizationIdentifiersByInstanceId) {
        this.organizationIdentifiersByInstanceId = organizationIdentifiersByInstanceId;
    }

    public Collection<PersonIdentifier> getPersonIdentifiersByInstanceId() {
        return personIdentifiersByInstanceId;
    }

    public void setPersonIdentifiersByInstanceId(Collection<PersonIdentifier> personIdentifiersByInstanceId) {
        this.personIdentifiersByInstanceId = personIdentifiersByInstanceId;
    }

    public Collection<PublicationIdentifier> getPublicationIdentifiersByInstanceId() {
        return publicationIdentifiersByInstanceId;
    }

    public void setPublicationIdentifiersByInstanceId(Collection<PublicationIdentifier> publicationIdentifiersByInstanceId) {
        this.publicationIdentifiersByInstanceId = publicationIdentifiersByInstanceId;
    }

    public Collection<ServiceIdentifier> getServiceIdentifiersByInstanceId() {
        return serviceIdentifiersByInstanceId;
    }

    public void setServiceIdentifiersByInstanceId(Collection<ServiceIdentifier> serviceIdentifiersByInstanceId) {
        this.serviceIdentifiersByInstanceId = serviceIdentifiersByInstanceId;
    }

    public Collection<SoftwareapplicationIdentifier> getSoftwareapplicationIdentifiersByInstanceId() {
        return softwareapplicationIdentifiersByInstanceId;
    }

    public void setSoftwareapplicationIdentifiersByInstanceId(Collection<SoftwareapplicationIdentifier> softwareapplicationIdentifiersByInstanceId) {
        this.softwareapplicationIdentifiersByInstanceId = softwareapplicationIdentifiersByInstanceId;
    }

    public Collection<SoftwaresourcecodeIdentifier> getSoftwaresourcecodeIdentifiersByInstanceId() {
        return softwaresourcecodeIdentifiersByInstanceId;
    }

    public void setSoftwaresourcecodeIdentifiersByInstanceId(Collection<SoftwaresourcecodeIdentifier> softwaresourcecodeIdentifiersByInstanceId) {
        this.softwaresourcecodeIdentifiersByInstanceId = softwaresourcecodeIdentifiersByInstanceId;
    }

    public Collection<WebserviceIdentifier> getWebserviceIdentifiersByInstanceId() {
        return webserviceIdentifiersByInstanceId;
    }

    public void setWebserviceIdentifiersByInstanceId(Collection<WebserviceIdentifier> webserviceIdentifiersByInstanceId) {
        this.webserviceIdentifiersByInstanceId = webserviceIdentifiersByInstanceId;
    }
}
