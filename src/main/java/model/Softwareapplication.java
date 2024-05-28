package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class Softwareapplication {
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
    @Column(name = "name", nullable = true, length = 1024)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @Basic
    @Column(name = "downloadurl", nullable = true, length = 1024)
    private String downloadurl;
    @Basic
    @Column(name = "licenseurl", nullable = true, length = 1024)
    private String licenseurl;
    @Basic
    @Column(name = "softwareversion", nullable = true, length = 1024)
    private String softwareversion;
    @Basic
    @Column(name = "keywords", nullable = true, length = -1)
    private String keywords;
    @Basic
    @Column(name = "requirements", nullable = true, length = 1024)
    private String requirements;
    @Basic
    @Column(name = "installurl", nullable = true, length = 1024)
    private String installurl;
    @Basic
    @Column(name = "mainentityofpage", nullable = true, length = 1024)
    private String mainentityofpage;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @OneToMany(mappedBy = "softwareapplicationBySoftwareapplicationInstanceId")
    private Collection<SoftwareapplicationCategory> softwareapplicationCategoriesByInstanceId;
    @OneToMany(mappedBy = "softwareapplicationBySoftwareapplicationInstanceId")
    private Collection<SoftwareapplicationContactpoint> softwareapplicationContactpointsByInstanceId;
    @OneToMany(mappedBy = "softwareapplicationBySoftwareapplicationInstanceId")
    private Collection<SoftwareapplicationIdentifier> softwareapplicationIdentifiersByInstanceId;
    @OneToMany(mappedBy = "softwareapplicationBySoftwareapplicationInstanceId")
    private Collection<SoftwareapplicationOperation> softwareapplicationOperationsByInstanceId;
    @OneToMany(mappedBy = "softwareapplicationBySoftwareapplicationInstanceId")
    private Collection<SoftwareapplicationParameters> softwareapplicationParametersByInstanceId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl;
    }

    public String getLicenseurl() {
        return licenseurl;
    }

    public void setLicenseurl(String licenseurl) {
        this.licenseurl = licenseurl;
    }

    public String getSoftwareversion() {
        return softwareversion;
    }

    public void setSoftwareversion(String softwareversion) {
        this.softwareversion = softwareversion;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getInstallurl() {
        return installurl;
    }

    public void setInstallurl(String installurl) {
        this.installurl = installurl;
    }

    public String getMainentityofpage() {
        return mainentityofpage;
    }

    public void setMainentityofpage(String mainentityofpage) {
        this.mainentityofpage = mainentityofpage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Softwareapplication that = (Softwareapplication) o;

        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(that.metaId) : that.metaId != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (versionId != null ? !versionId.equals(that.versionId) : that.versionId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (downloadurl != null ? !downloadurl.equals(that.downloadurl) : that.downloadurl != null) return false;
        if (licenseurl != null ? !licenseurl.equals(that.licenseurl) : that.licenseurl != null) return false;
        if (softwareversion != null ? !softwareversion.equals(that.softwareversion) : that.softwareversion != null)
            return false;
        if (keywords != null ? !keywords.equals(that.keywords) : that.keywords != null) return false;
        if (requirements != null ? !requirements.equals(that.requirements) : that.requirements != null) return false;
        if (installurl != null ? !installurl.equals(that.installurl) : that.installurl != null) return false;
        if (mainentityofpage != null ? !mainentityofpage.equals(that.mainentityofpage) : that.mainentityofpage != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (downloadurl != null ? downloadurl.hashCode() : 0);
        result = 31 * result + (licenseurl != null ? licenseurl.hashCode() : 0);
        result = 31 * result + (softwareversion != null ? softwareversion.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (requirements != null ? requirements.hashCode() : 0);
        result = 31 * result + (installurl != null ? installurl.hashCode() : 0);
        result = 31 * result + (mainentityofpage != null ? mainentityofpage.hashCode() : 0);
        return result;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Collection<SoftwareapplicationCategory> getSoftwareapplicationCategoriesByInstanceId() {
        return softwareapplicationCategoriesByInstanceId;
    }

    public void setSoftwareapplicationCategoriesByInstanceId(Collection<SoftwareapplicationCategory> softwareapplicationCategoriesByInstanceId) {
        this.softwareapplicationCategoriesByInstanceId = softwareapplicationCategoriesByInstanceId;
    }

    public Collection<SoftwareapplicationContactpoint> getSoftwareapplicationContactpointsByInstanceId() {
        return softwareapplicationContactpointsByInstanceId;
    }

    public void setSoftwareapplicationContactpointsByInstanceId(Collection<SoftwareapplicationContactpoint> softwareapplicationContactpointsByInstanceId) {
        this.softwareapplicationContactpointsByInstanceId = softwareapplicationContactpointsByInstanceId;
    }

    public Collection<SoftwareapplicationIdentifier> getSoftwareapplicationIdentifiersByInstanceId() {
        return softwareapplicationIdentifiersByInstanceId;
    }

    public void setSoftwareapplicationIdentifiersByInstanceId(Collection<SoftwareapplicationIdentifier> softwareapplicationIdentifiersByInstanceId) {
        this.softwareapplicationIdentifiersByInstanceId = softwareapplicationIdentifiersByInstanceId;
    }

    public Collection<SoftwareapplicationOperation> getSoftwareapplicationOperationsByInstanceId() {
        return softwareapplicationOperationsByInstanceId;
    }

    public void setSoftwareapplicationOperationsByInstanceId(Collection<SoftwareapplicationOperation> softwareapplicationOperationsByInstanceId) {
        this.softwareapplicationOperationsByInstanceId = softwareapplicationOperationsByInstanceId;
    }

    public Collection<SoftwareapplicationParameters> getSoftwareapplicationParametersByInstanceId() {
        return softwareapplicationParametersByInstanceId;
    }

    public void setSoftwareapplicationParametersByInstanceId(Collection<SoftwareapplicationParameters> softwareapplicationParametersByInstanceId) {
        this.softwareapplicationParametersByInstanceId = softwareapplicationParametersByInstanceId;
    }
}
