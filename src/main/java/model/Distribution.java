package model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Distribution {
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
    @Column(name = "issued", nullable = true)
    private Timestamp issued;
    @Basic
    @Column(name = "modified", nullable = true)
    private Timestamp modified;
    @Basic
    @Column(name = "type", nullable = true, length = 1024)
    private String type;
    @Basic
    @Column(name = "format", nullable = true, length = 1024)
    private String format;
    @Basic
    @Column(name = "license", nullable = true, length = 1024)
    private String license;
    @Basic
    @Column(name = "access_service", nullable = true, length = 1024)
    private String accessService;
    @Basic
    @Column(name = "datapolicy", nullable = true, length = 1024)
    private String datapolicy;
    @Basic
    @Column(name = "conformsto", nullable = true, length = 1024)
    private String conformsto;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @OneToMany(mappedBy = "distributionByDistributionInstanceId")
    private Collection<DistributionDataproduct> distributionDataproductsByInstanceId;
    @OneToMany(mappedBy = "distributionByDistributionInstanceId")
    private Collection<DistributionDescription> distributionDescriptionsByInstanceId;
    @OneToMany(mappedBy = "distributionByDistributionInstanceId")
    private Collection<DistributionElement> distributionElementsByInstanceId;
    @OneToMany(mappedBy = "distributionByDistributionInstanceId")
    private Collection<DistributionTitle> distributionTitlesByInstanceId;
    @OneToMany(mappedBy = "distributionByDistributionInstanceId")
    private Collection<OperationDistribution> operationDistributionsByInstanceId;

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

    public Timestamp getIssued() {
        return issued;
    }

    public void setIssued(Timestamp issued) {
        this.issued = issued;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getAccessService() {
        return accessService;
    }

    public void setAccessService(String accessService) {
        this.accessService = accessService;
    }

    public String getDatapolicy() {
        return datapolicy;
    }

    public void setDatapolicy(String datapolicy) {
        this.datapolicy = datapolicy;
    }

    public String getConformsto() {
        return conformsto;
    }

    public void setConformsto(String conformsto) {
        this.conformsto = conformsto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Distribution that = (Distribution) o;

        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(that.metaId) : that.metaId != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (versionId != null ? !versionId.equals(that.versionId) : that.versionId != null) return false;
        if (issued != null ? !issued.equals(that.issued) : that.issued != null) return false;
        if (modified != null ? !modified.equals(that.modified) : that.modified != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (format != null ? !format.equals(that.format) : that.format != null) return false;
        if (license != null ? !license.equals(that.license) : that.license != null) return false;
        if (accessService != null ? !accessService.equals(that.accessService) : that.accessService != null)
            return false;
        if (datapolicy != null ? !datapolicy.equals(that.datapolicy) : that.datapolicy != null) return false;
        if (conformsto != null ? !conformsto.equals(that.conformsto) : that.conformsto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (issued != null ? issued.hashCode() : 0);
        result = 31 * result + (modified != null ? modified.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (format != null ? format.hashCode() : 0);
        result = 31 * result + (license != null ? license.hashCode() : 0);
        result = 31 * result + (accessService != null ? accessService.hashCode() : 0);
        result = 31 * result + (datapolicy != null ? datapolicy.hashCode() : 0);
        result = 31 * result + (conformsto != null ? conformsto.hashCode() : 0);
        return result;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Collection<DistributionDataproduct> getDistributionDataproductsByInstanceId() {
        return distributionDataproductsByInstanceId;
    }

    public void setDistributionDataproductsByInstanceId(Collection<DistributionDataproduct> distributionDataproductsByInstanceId) {
        this.distributionDataproductsByInstanceId = distributionDataproductsByInstanceId;
    }

    public Collection<DistributionDescription> getDistributionDescriptionsByInstanceId() {
        return distributionDescriptionsByInstanceId;
    }

    public void setDistributionDescriptionsByInstanceId(Collection<DistributionDescription> distributionDescriptionsByInstanceId) {
        this.distributionDescriptionsByInstanceId = distributionDescriptionsByInstanceId;
    }

    public Collection<DistributionElement> getDistributionElementsByInstanceId() {
        return distributionElementsByInstanceId;
    }

    public void setDistributionElementsByInstanceId(Collection<DistributionElement> distributionElementsByInstanceId) {
        this.distributionElementsByInstanceId = distributionElementsByInstanceId;
    }

    public Collection<DistributionTitle> getDistributionTitlesByInstanceId() {
        return distributionTitlesByInstanceId;
    }

    public void setDistributionTitlesByInstanceId(Collection<DistributionTitle> distributionTitlesByInstanceId) {
        this.distributionTitlesByInstanceId = distributionTitlesByInstanceId;
    }

    public Collection<OperationDistribution> getOperationDistributionsByInstanceId() {
        return operationDistributionsByInstanceId;
    }

    public void setOperationDistributionsByInstanceId(Collection<OperationDistribution> operationDistributionsByInstanceId) {
        this.operationDistributionsByInstanceId = operationDistributionsByInstanceId;
    }
}
