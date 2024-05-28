package model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Dataproduct {
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
    @Column(name = "identifier", nullable = true, length = 1024)
    private String identifier;
    @Basic
    @Column(name = "created", nullable = true)
    private Timestamp created;
    @Basic
    @Column(name = "issued", nullable = true)
    private Timestamp issued;
    @Basic
    @Column(name = "modified", nullable = true)
    private Timestamp modified;
    @Basic
    @Column(name = "versioninfo", nullable = true, length = 1024)
    private String versioninfo;
    @Basic
    @Column(name = "type", nullable = true, length = 1024)
    private String type;
    @Basic
    @Column(name = "accrualperiodicity", nullable = true, length = 1024)
    private String accrualperiodicity;
    @Basic
    @Column(name = "keywords", nullable = true, length = -1)
    private String keywords;
    @Basic
    @Column(name = "accessright", nullable = true, length = 1024)
    private String accessright;
    @Basic
    @Column(name = "documentation", nullable = true, length = 1024)
    private String documentation;
    @Basic
    @Column(name = "qualityassurance", nullable = true, length = 1024)
    private String qualityassurance;
    @Basic
    @Column(name = "has_quality_annotation", nullable = true, length = 1024)
    private String hasQualityAnnotation;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @OneToMany(mappedBy = "dataproductByDataproductInstanceId")
    private Collection<DataproductCategory> dataproductCategoriesByInstanceId;
    @OneToMany(mappedBy = "dataproductByDataproductInstanceId")
    private Collection<DataproductContactpoint> dataproductContactpointsByInstanceId;
    @OneToMany(mappedBy = "dataproductByDataproductInstanceId")
    private Collection<DataproductDescription> dataproductDescriptionsByInstanceId;
    @OneToMany(mappedBy = "dataproductByDataproduct1InstanceId")
    private Collection<DataproductHaspart> dataproductHaspartsByInstanceId;
    @OneToMany(mappedBy = "dataproductByDataproduct2InstanceId")
    private Collection<DataproductHaspart> dataproductHaspartsByInstanceId_0;
    @OneToMany(mappedBy = "dataproductByDataproductInstanceId")
    private Collection<DataproductIdentifier> dataproductIdentifiersByInstanceId;
    @OneToMany(mappedBy = "dataproductByDataproduct1InstanceId")
    private Collection<DataproductIspartof> dataproductIspartofsByInstanceId;
    @OneToMany(mappedBy = "dataproductByDataproduct2InstanceId")
    private Collection<DataproductIspartof> dataproductIspartofsByInstanceId_0;
    @OneToMany(mappedBy = "dataproductByDataproductInstanceId")
    private Collection<DataproductProvenance> dataproductProvenancesByInstanceId;
    @OneToMany(mappedBy = "dataproductByDataproductInstanceId")
    private Collection<DataproductPublisher> dataproductPublishersByInstanceId;
    @OneToOne(mappedBy = "dataproductByDataproductInstanceId")
    private DataproductRelation dataproductRelationByInstanceId;
    @OneToMany(mappedBy = "dataproductByDataproductInstanceId")
    private Collection<DataproductSpatial> dataproductSpatialsByInstanceId;
    @OneToMany(mappedBy = "dataproductByDataproductInstanceId")
    private Collection<DataproductTemporal> dataproductTemporalsByInstanceId;
    @OneToMany(mappedBy = "dataproductByDataproductInstanceId")
    private Collection<DataproductTitle> dataproductTitlesByInstanceId;
    @OneToMany(mappedBy = "dataproductByDataproductInstanceId")
    private Collection<DistributionDataproduct> distributionDataproductsByInstanceId;

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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Object getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
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

    public String getVersioninfo() {
        return versioninfo;
    }

    public void setVersioninfo(String versioninfo) {
        this.versioninfo = versioninfo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccrualperiodicity() {
        return accrualperiodicity;
    }

    public void setAccrualperiodicity(String accrualperiodicity) {
        this.accrualperiodicity = accrualperiodicity;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getAccessright() {
        return accessright;
    }

    public void setAccessright(String accessright) {
        this.accessright = accessright;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getQualityassurance() {
        return qualityassurance;
    }

    public void setQualityassurance(String qualityassurance) {
        this.qualityassurance = qualityassurance;
    }

    public String getHasQualityAnnotation() {
        return hasQualityAnnotation;
    }

    public void setHasQualityAnnotation(String hasQualityAnnotation) {
        this.hasQualityAnnotation = hasQualityAnnotation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dataproduct that = (Dataproduct) o;

        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(that.metaId) : that.metaId != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (versionId != null ? !versionId.equals(that.versionId) : that.versionId != null) return false;
        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (issued != null ? !issued.equals(that.issued) : that.issued != null) return false;
        if (modified != null ? !modified.equals(that.modified) : that.modified != null) return false;
        if (versioninfo != null ? !versioninfo.equals(that.versioninfo) : that.versioninfo != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (accrualperiodicity != null ? !accrualperiodicity.equals(that.accrualperiodicity) : that.accrualperiodicity != null)
            return false;
        if (keywords != null ? !keywords.equals(that.keywords) : that.keywords != null) return false;
        if (accessright != null ? !accessright.equals(that.accessright) : that.accessright != null) return false;
        if (documentation != null ? !documentation.equals(that.documentation) : that.documentation != null)
            return false;
        if (qualityassurance != null ? !qualityassurance.equals(that.qualityassurance) : that.qualityassurance != null)
            return false;
        if (hasQualityAnnotation != null ? !hasQualityAnnotation.equals(that.hasQualityAnnotation) : that.hasQualityAnnotation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (issued != null ? issued.hashCode() : 0);
        result = 31 * result + (modified != null ? modified.hashCode() : 0);
        result = 31 * result + (versioninfo != null ? versioninfo.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (accrualperiodicity != null ? accrualperiodicity.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (accessright != null ? accessright.hashCode() : 0);
        result = 31 * result + (documentation != null ? documentation.hashCode() : 0);
        result = 31 * result + (qualityassurance != null ? qualityassurance.hashCode() : 0);
        result = 31 * result + (hasQualityAnnotation != null ? hasQualityAnnotation.hashCode() : 0);
        return result;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Collection<DataproductCategory> getDataproductCategoriesByInstanceId() {
        return dataproductCategoriesByInstanceId;
    }

    public void setDataproductCategoriesByInstanceId(Collection<DataproductCategory> dataproductCategoriesByInstanceId) {
        this.dataproductCategoriesByInstanceId = dataproductCategoriesByInstanceId;
    }

    public Collection<DataproductContactpoint> getDataproductContactpointsByInstanceId() {
        return dataproductContactpointsByInstanceId;
    }

    public void setDataproductContactpointsByInstanceId(Collection<DataproductContactpoint> dataproductContactpointsByInstanceId) {
        this.dataproductContactpointsByInstanceId = dataproductContactpointsByInstanceId;
    }

    public Collection<DataproductDescription> getDataproductDescriptionsByInstanceId() {
        return dataproductDescriptionsByInstanceId;
    }

    public void setDataproductDescriptionsByInstanceId(Collection<DataproductDescription> dataproductDescriptionsByInstanceId) {
        this.dataproductDescriptionsByInstanceId = dataproductDescriptionsByInstanceId;
    }

    public Collection<DataproductHaspart> getDataproductHaspartsByInstanceId() {
        return dataproductHaspartsByInstanceId;
    }

    public void setDataproductHaspartsByInstanceId(Collection<DataproductHaspart> dataproductHaspartsByInstanceId) {
        this.dataproductHaspartsByInstanceId = dataproductHaspartsByInstanceId;
    }

    public Collection<DataproductHaspart> getDataproductHaspartsByInstanceId_0() {
        return dataproductHaspartsByInstanceId_0;
    }

    public void setDataproductHaspartsByInstanceId_0(Collection<DataproductHaspart> dataproductHaspartsByInstanceId_0) {
        this.dataproductHaspartsByInstanceId_0 = dataproductHaspartsByInstanceId_0;
    }

    public Collection<DataproductIdentifier> getDataproductIdentifiersByInstanceId() {
        return dataproductIdentifiersByInstanceId;
    }

    public void setDataproductIdentifiersByInstanceId(Collection<DataproductIdentifier> dataproductIdentifiersByInstanceId) {
        this.dataproductIdentifiersByInstanceId = dataproductIdentifiersByInstanceId;
    }

    public Collection<DataproductIspartof> getDataproductIspartofsByInstanceId() {
        return dataproductIspartofsByInstanceId;
    }

    public void setDataproductIspartofsByInstanceId(Collection<DataproductIspartof> dataproductIspartofsByInstanceId) {
        this.dataproductIspartofsByInstanceId = dataproductIspartofsByInstanceId;
    }

    public Collection<DataproductIspartof> getDataproductIspartofsByInstanceId_0() {
        return dataproductIspartofsByInstanceId_0;
    }

    public void setDataproductIspartofsByInstanceId_0(Collection<DataproductIspartof> dataproductIspartofsByInstanceId_0) {
        this.dataproductIspartofsByInstanceId_0 = dataproductIspartofsByInstanceId_0;
    }

    public Collection<DataproductProvenance> getDataproductProvenancesByInstanceId() {
        return dataproductProvenancesByInstanceId;
    }

    public void setDataproductProvenancesByInstanceId(Collection<DataproductProvenance> dataproductProvenancesByInstanceId) {
        this.dataproductProvenancesByInstanceId = dataproductProvenancesByInstanceId;
    }

    public Collection<DataproductPublisher> getDataproductPublishersByInstanceId() {
        return dataproductPublishersByInstanceId;
    }

    public void setDataproductPublishersByInstanceId(Collection<DataproductPublisher> dataproductPublishersByInstanceId) {
        this.dataproductPublishersByInstanceId = dataproductPublishersByInstanceId;
    }

    public DataproductRelation getDataproductRelationByInstanceId() {
        return dataproductRelationByInstanceId;
    }

    public void setDataproductRelationByInstanceId(DataproductRelation dataproductRelationByInstanceId) {
        this.dataproductRelationByInstanceId = dataproductRelationByInstanceId;
    }

    public Collection<DataproductSpatial> getDataproductSpatialsByInstanceId() {
        return dataproductSpatialsByInstanceId;
    }

    public void setDataproductSpatialsByInstanceId(Collection<DataproductSpatial> dataproductSpatialsByInstanceId) {
        this.dataproductSpatialsByInstanceId = dataproductSpatialsByInstanceId;
    }

    public Collection<DataproductTemporal> getDataproductTemporalsByInstanceId() {
        return dataproductTemporalsByInstanceId;
    }

    public void setDataproductTemporalsByInstanceId(Collection<DataproductTemporal> dataproductTemporalsByInstanceId) {
        this.dataproductTemporalsByInstanceId = dataproductTemporalsByInstanceId;
    }

    public Collection<DataproductTitle> getDataproductTitlesByInstanceId() {
        return dataproductTitlesByInstanceId;
    }

    public void setDataproductTitlesByInstanceId(Collection<DataproductTitle> dataproductTitlesByInstanceId) {
        this.dataproductTitlesByInstanceId = dataproductTitlesByInstanceId;
    }

    public Collection<DistributionDataproduct> getDistributionDataproductsByInstanceId() {
        return distributionDataproductsByInstanceId;
    }

    public void setDistributionDataproductsByInstanceId(Collection<DistributionDataproduct> distributionDataproductsByInstanceId) {
        this.distributionDataproductsByInstanceId = distributionDataproductsByInstanceId;
    }
}
