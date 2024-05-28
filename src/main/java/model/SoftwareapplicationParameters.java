package model;

import jakarta.persistence.*;

@Entity
@Table(name = "softwareapplication_parameters", schema = "public", catalog = "cerif")
public class SoftwareapplicationParameters {
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
    @Column(name = "encodingformat", nullable = true, length = 1024)
    private String encodingformat;
    @Basic
    @Column(name = "conformsto", nullable = true, length = 1024)
    private String conformsto;
    @Basic
    @Column(name = "action", nullable = false, length = 1024)
    private String action;
    @Basic
    @Column(name = "softwareapplication_instance_id", nullable = false, length = 100)
    private String softwareapplicationInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "softwareapplication_instance_id", referencedColumnName = "instance_id")
    private Softwareapplication softwareapplicationBySoftwareapplicationInstanceId;

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

    public String getEncodingformat() {
        return encodingformat;
    }

    public void setEncodingformat(String encodingformat) {
        this.encodingformat = encodingformat;
    }

    public String getConformsto() {
        return conformsto;
    }

    public void setConformsto(String conformsto) {
        this.conformsto = conformsto;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSoftwareapplicationInstanceId() {
        return softwareapplicationInstanceId;
    }

    public void setSoftwareapplicationInstanceId(String softwareapplicationInstanceId) {
        this.softwareapplicationInstanceId = softwareapplicationInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SoftwareapplicationParameters that = (SoftwareapplicationParameters) o;

        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(that.metaId) : that.metaId != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (versionId != null ? !versionId.equals(that.versionId) : that.versionId != null) return false;
        if (encodingformat != null ? !encodingformat.equals(that.encodingformat) : that.encodingformat != null)
            return false;
        if (conformsto != null ? !conformsto.equals(that.conformsto) : that.conformsto != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (softwareapplicationInstanceId != null ? !softwareapplicationInstanceId.equals(that.softwareapplicationInstanceId) : that.softwareapplicationInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (encodingformat != null ? encodingformat.hashCode() : 0);
        result = 31 * result + (conformsto != null ? conformsto.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (softwareapplicationInstanceId != null ? softwareapplicationInstanceId.hashCode() : 0);
        return result;
    }

    public Softwareapplication getSoftwareapplicationBySoftwareapplicationInstanceId() {
        return softwareapplicationBySoftwareapplicationInstanceId;
    }

    public void setSoftwareapplicationBySoftwareapplicationInstanceId(Softwareapplication softwareapplicationBySoftwareapplicationInstanceId) {
        this.softwareapplicationBySoftwareapplicationInstanceId = softwareapplicationBySoftwareapplicationInstanceId;
    }
}
