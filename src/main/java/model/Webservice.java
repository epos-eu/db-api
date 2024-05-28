package model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Webservice {
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
    @Column(name = "schemaidentifier", nullable = true, length = 1024)
    private String schemaidentifier;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @Basic
    @Column(name = "name", nullable = true, length = 1024)
    private String name;
    @Basic
    @Column(name = "entrypoint", nullable = true, length = 1024)
    private String entrypoint;
    @Basic
    @Column(name = "datapublished", nullable = true)
    private Timestamp datapublished;
    @Basic
    @Column(name = "datamodified", nullable = true)
    private Timestamp datamodified;
    @Basic
    @Column(name = "keywords", nullable = true, length = -1)
    private String keywords;
    @Basic
    @Column(name = "license", nullable = true, length = 1024)
    private String license;
    @Basic
    @Column(name = "provider", nullable = true, length = 100)
    private String provider;
    @Basic
    @Column(name = "aaaitypes", nullable = true, length = 1024)
    private String aaaitypes;
    @OneToMany(mappedBy = "webserviceByWebserviceInstanceId")
    private Collection<OperationWebservice> operationWebservicesByInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @OneToMany(mappedBy = "webserviceByWebserviceInstanceId")
    private Collection<WebserviceCategory> webserviceCategoriesByInstanceId;
    @OneToMany(mappedBy = "webserviceByWebserviceInstanceId")
    private Collection<WebserviceContactpoint> webserviceContactpointsByInstanceId;
    @OneToMany(mappedBy = "webserviceByWebserviceInstanceId")
    private Collection<WebserviceElement> webserviceElementsByInstanceId;
    @OneToMany(mappedBy = "webserviceByWebserviceInstanceId")
    private Collection<WebserviceIdentifier> webserviceIdentifiersByInstanceId;
    @OneToOne(mappedBy = "webserviceByWebserviceInstanceId")
    private WebserviceRelation webserviceRelationByInstanceId;
    @OneToMany(mappedBy = "webserviceByWebserviceInstanceId")
    private Collection<WebserviceSpatial> webserviceSpatialsByInstanceId;
    @OneToMany(mappedBy = "webserviceByWebserviceInstanceId")
    private Collection<WebserviceTemporal> webserviceTemporalsByInstanceId;

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

    public String getSchemaidentifier() {
        return schemaidentifier;
    }

    public void setSchemaidentifier(String schemaidentifier) {
        this.schemaidentifier = schemaidentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntrypoint() {
        return entrypoint;
    }

    public void setEntrypoint(String entrypoint) {
        this.entrypoint = entrypoint;
    }

    public Timestamp getDatapublished() {
        return datapublished;
    }

    public void setDatapublished(Timestamp datapublished) {
        this.datapublished = datapublished;
    }

    public Timestamp getDatamodified() {
        return datamodified;
    }

    public void setDatamodified(Timestamp datamodified) {
        this.datamodified = datamodified;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getAaaitypes() {
        return aaaitypes;
    }

    public void setAaaitypes(String aaaitypes) {
        this.aaaitypes = aaaitypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Webservice that = (Webservice) o;

        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(that.metaId) : that.metaId != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (versionId != null ? !versionId.equals(that.versionId) : that.versionId != null) return false;
        if (schemaidentifier != null ? !schemaidentifier.equals(that.schemaidentifier) : that.schemaidentifier != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (entrypoint != null ? !entrypoint.equals(that.entrypoint) : that.entrypoint != null) return false;
        if (datapublished != null ? !datapublished.equals(that.datapublished) : that.datapublished != null)
            return false;
        if (datamodified != null ? !datamodified.equals(that.datamodified) : that.datamodified != null) return false;
        if (keywords != null ? !keywords.equals(that.keywords) : that.keywords != null) return false;
        if (license != null ? !license.equals(that.license) : that.license != null) return false;
        if (provider != null ? !provider.equals(that.provider) : that.provider != null) return false;
        if (aaaitypes != null ? !aaaitypes.equals(that.aaaitypes) : that.aaaitypes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (schemaidentifier != null ? schemaidentifier.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (entrypoint != null ? entrypoint.hashCode() : 0);
        result = 31 * result + (datapublished != null ? datapublished.hashCode() : 0);
        result = 31 * result + (datamodified != null ? datamodified.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (license != null ? license.hashCode() : 0);
        result = 31 * result + (provider != null ? provider.hashCode() : 0);
        result = 31 * result + (aaaitypes != null ? aaaitypes.hashCode() : 0);
        return result;
    }

    public Collection<OperationWebservice> getOperationWebservicesByInstanceId() {
        return operationWebservicesByInstanceId;
    }

    public void setOperationWebservicesByInstanceId(Collection<OperationWebservice> operationWebservicesByInstanceId) {
        this.operationWebservicesByInstanceId = operationWebservicesByInstanceId;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Collection<WebserviceCategory> getWebserviceCategoriesByInstanceId() {
        return webserviceCategoriesByInstanceId;
    }

    public void setWebserviceCategoriesByInstanceId(Collection<WebserviceCategory> webserviceCategoriesByInstanceId) {
        this.webserviceCategoriesByInstanceId = webserviceCategoriesByInstanceId;
    }

    public Collection<WebserviceContactpoint> getWebserviceContactpointsByInstanceId() {
        return webserviceContactpointsByInstanceId;
    }

    public void setWebserviceContactpointsByInstanceId(Collection<WebserviceContactpoint> webserviceContactpointsByInstanceId) {
        this.webserviceContactpointsByInstanceId = webserviceContactpointsByInstanceId;
    }

    public Collection<WebserviceElement> getWebserviceElementsByInstanceId() {
        return webserviceElementsByInstanceId;
    }

    public void setWebserviceElementsByInstanceId(Collection<WebserviceElement> webserviceElementsByInstanceId) {
        this.webserviceElementsByInstanceId = webserviceElementsByInstanceId;
    }

    public Collection<WebserviceIdentifier> getWebserviceIdentifiersByInstanceId() {
        return webserviceIdentifiersByInstanceId;
    }

    public void setWebserviceIdentifiersByInstanceId(Collection<WebserviceIdentifier> webserviceIdentifiersByInstanceId) {
        this.webserviceIdentifiersByInstanceId = webserviceIdentifiersByInstanceId;
    }

    public WebserviceRelation getWebserviceRelationByInstanceId() {
        return webserviceRelationByInstanceId;
    }

    public void setWebserviceRelationByInstanceId(WebserviceRelation webserviceRelationByInstanceId) {
        this.webserviceRelationByInstanceId = webserviceRelationByInstanceId;
    }

    public Collection<WebserviceSpatial> getWebserviceSpatialsByInstanceId() {
        return webserviceSpatialsByInstanceId;
    }

    public void setWebserviceSpatialsByInstanceId(Collection<WebserviceSpatial> webserviceSpatialsByInstanceId) {
        this.webserviceSpatialsByInstanceId = webserviceSpatialsByInstanceId;
    }

    public Collection<WebserviceTemporal> getWebserviceTemporalsByInstanceId() {
        return webserviceTemporalsByInstanceId;
    }

    public void setWebserviceTemporalsByInstanceId(Collection<WebserviceTemporal> webserviceTemporalsByInstanceId) {
        this.webserviceTemporalsByInstanceId = webserviceTemporalsByInstanceId;
    }
}
