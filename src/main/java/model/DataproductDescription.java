package model;

import jakarta.persistence.*;

@Entity
@Table(name = "dataproduct_description", schema = "public", catalog = "cerif")
public class DataproductDescription {
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
    @Column(name = "description", nullable = false, length = -1)
    private String description;
    @Basic
    @Column(name = "lang", nullable = true, length = 20)
    private String lang;
    @Basic
    @Column(name = "dataproduct_instance_id", nullable = false, length = 100)
    private String dataproductInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "dataproduct_instance_id", referencedColumnName = "instance_id")
    private Dataproduct dataproductByDataproductInstanceId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getDataproductInstanceId() {
        return dataproductInstanceId;
    }

    public void setDataproductInstanceId(String dataproductInstanceId) {
        this.dataproductInstanceId = dataproductInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataproductDescription that = (DataproductDescription) o;

        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(that.metaId) : that.metaId != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (versionId != null ? !versionId.equals(that.versionId) : that.versionId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (lang != null ? !lang.equals(that.lang) : that.lang != null) return false;
        if (dataproductInstanceId != null ? !dataproductInstanceId.equals(that.dataproductInstanceId) : that.dataproductInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (lang != null ? lang.hashCode() : 0);
        result = 31 * result + (dataproductInstanceId != null ? dataproductInstanceId.hashCode() : 0);
        return result;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Dataproduct getDataproductByDataproductInstanceId() {
        return dataproductByDataproductInstanceId;
    }

    public void setDataproductByDataproductInstanceId(Dataproduct dataproductByDataproductInstanceId) {
        this.dataproductByDataproductInstanceId = dataproductByDataproductInstanceId;
    }
}
