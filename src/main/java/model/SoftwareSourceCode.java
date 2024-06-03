package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "softwaresourcecode", schema = "public", catalog = "cerif")
public class SoftwareSourceCode {
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
    @Column(name = "licenseurl", nullable = true, length = 1024)
    private String licenseurl;
    @Basic
    @Column(name = "downloadurl", nullable = true, length = 1024)
    private String downloadurl;
    @Basic
    @Column(name = "runtimeplatform", nullable = true, length = 1024)
    private String runtimeplatform;
    @Basic
    @Column(name = "softwareversion", nullable = true, length = 1024)
    private String softwareversion;
    @Basic
    @Column(name = "keywords", nullable = true, length = -1)
    private String keywords;
    @Basic
    @Column(name = "coderepository", nullable = true, length = 1024)
    private String coderepository;
    @Basic
    @Column(name = "mainentityofpage", nullable = true, length = 1024)
    private String mainentityofpage;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @OneToMany(mappedBy = "softwaresourcecodeBySoftwareSourceCodeInstanceId")
    private Collection<SoftwaresourcecodeCategory> softwaresourcecodeCategoriesByInstanceId;
    @OneToMany(mappedBy = "softwaresourcecodeBySoftwareSourceCodeInstanceId")
    private Collection<SoftwaresourcecodeContactpoint> softwaresourcecodeContactpointsByInstanceId;
    @OneToMany(mappedBy = "softwaresourcecodeBySoftwareSourceCodeInstanceId")
    private Collection<SoftwaresourcecodeElement> softwaresourcecodeElementsByInstanceId;
    @OneToMany(mappedBy = "softwaresourcecodeBySoftwareSourceCodeInstanceId")
    private Collection<SoftwaresourcecodeIdentifier> softwaresourcecodeIdentifiersByInstanceId;

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

    public String getLicenseurl() {
        return licenseurl;
    }

    public void setLicenseurl(String licenseurl) {
        this.licenseurl = licenseurl;
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl;
    }

    public String getRuntimeplatform() {
        return runtimeplatform;
    }

    public void setRuntimeplatform(String runtimeplatform) {
        this.runtimeplatform = runtimeplatform;
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

    public String getCoderepository() {
        return coderepository;
    }

    public void setCoderepository(String coderepository) {
        this.coderepository = coderepository;
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

        SoftwareSourceCode that = (SoftwareSourceCode) o;

        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(that.metaId) : that.metaId != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (versionId != null ? !versionId.equals(that.versionId) : that.versionId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (licenseurl != null ? !licenseurl.equals(that.licenseurl) : that.licenseurl != null) return false;
        if (downloadurl != null ? !downloadurl.equals(that.downloadurl) : that.downloadurl != null) return false;
        if (runtimeplatform != null ? !runtimeplatform.equals(that.runtimeplatform) : that.runtimeplatform != null)
            return false;
        if (softwareversion != null ? !softwareversion.equals(that.softwareversion) : that.softwareversion != null)
            return false;
        if (keywords != null ? !keywords.equals(that.keywords) : that.keywords != null) return false;
        if (coderepository != null ? !coderepository.equals(that.coderepository) : that.coderepository != null)
            return false;
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
        result = 31 * result + (licenseurl != null ? licenseurl.hashCode() : 0);
        result = 31 * result + (downloadurl != null ? downloadurl.hashCode() : 0);
        result = 31 * result + (runtimeplatform != null ? runtimeplatform.hashCode() : 0);
        result = 31 * result + (softwareversion != null ? softwareversion.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (coderepository != null ? coderepository.hashCode() : 0);
        result = 31 * result + (mainentityofpage != null ? mainentityofpage.hashCode() : 0);
        return result;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Collection<SoftwaresourcecodeCategory> getSoftwaresourcecodeCategoriesByInstanceId() {
        return softwaresourcecodeCategoriesByInstanceId;
    }

    public void setSoftwaresourcecodeCategoriesByInstanceId(Collection<SoftwaresourcecodeCategory> softwaresourcecodeCategoriesByInstanceId) {
        this.softwaresourcecodeCategoriesByInstanceId = softwaresourcecodeCategoriesByInstanceId;
    }

    public Collection<SoftwaresourcecodeContactpoint> getSoftwaresourcecodeContactpointsByInstanceId() {
        return softwaresourcecodeContactpointsByInstanceId;
    }

    public void setSoftwaresourcecodeContactpointsByInstanceId(Collection<SoftwaresourcecodeContactpoint> softwaresourcecodeContactpointsByInstanceId) {
        this.softwaresourcecodeContactpointsByInstanceId = softwaresourcecodeContactpointsByInstanceId;
    }

    public Collection<SoftwaresourcecodeElement> getSoftwaresourcecodeElementsByInstanceId() {
        return softwaresourcecodeElementsByInstanceId;
    }

    public void setSoftwaresourcecodeElementsByInstanceId(Collection<SoftwaresourcecodeElement> softwaresourcecodeElementsByInstanceId) {
        this.softwaresourcecodeElementsByInstanceId = softwaresourcecodeElementsByInstanceId;
    }

    public Collection<SoftwaresourcecodeIdentifier> getSoftwaresourcecodeIdentifiersByInstanceId() {
        return softwaresourcecodeIdentifiersByInstanceId;
    }

    public void setSoftwaresourcecodeIdentifiersByInstanceId(Collection<SoftwaresourcecodeIdentifier> softwaresourcecodeIdentifiersByInstanceId) {
        this.softwaresourcecodeIdentifiersByInstanceId = softwaresourcecodeIdentifiersByInstanceId;
    }
}
