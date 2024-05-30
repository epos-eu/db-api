package model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Distribution {

    @Id
    @Column(name = "instance_id", nullable = false, length = 100)
    private String instanceId;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Basic
    @Column(name = "meta_id", nullable = true, length = 100)
    private String metaId;

    public String getMetaId() {
        return metaId;
    }

    public void setMetaId(String metaId) {
        this.metaId = metaId;
    }

    @Basic
    @Column(name = "uid", nullable = true, length = 100)
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "version_id", nullable = true, length = 100)
    private String versionId;

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    @Basic
    @Column(name = "issued", nullable = true)
    private Timestamp issued;

    public Timestamp getIssued() {
        return issued;
    }

    public void setIssued(Timestamp issued) {
        this.issued = issued;
    }

    @Basic
    @Column(name = "modified", nullable = true)
    private Timestamp modified;

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 1024)
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "format", nullable = true, length = 1024)
    private String format;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Basic
    @Column(name = "license", nullable = true, length = 1024)
    private String license;

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @Basic
    @Column(name = "datapolicy", nullable = true, length = 1024)
    private String datapolicy;

    public String getDatapolicy() {
        return datapolicy;
    }

    public void setDatapolicy(String datapolicy) {
        this.datapolicy = datapolicy;
    }
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
        if (datapolicy != null ? !datapolicy.equals(that.datapolicy) : that.datapolicy != null) return false;

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
        result = 31 * result + (datapolicy != null ? datapolicy.hashCode() : 0);
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
