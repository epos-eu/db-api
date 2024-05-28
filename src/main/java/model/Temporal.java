package model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Temporal {
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
    @Column(name = "startdate", nullable = true)
    private Timestamp startdate;
    @Basic
    @Column(name = "enddate", nullable = true)
    private Timestamp enddate;
    @OneToMany(mappedBy = "temporalByTemporalInstanceId")
    private Collection<DataproductTemporal> dataproductTemporalsByInstanceId;
    @OneToMany(mappedBy = "temporalByTemporalInstanceId")
    private Collection<EquipmentTemporal> equipmentTemporalsByInstanceId;
    @OneToMany(mappedBy = "temporalByTemporalInstanceId")
    private Collection<ServiceTemporal> serviceTemporalsByInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @OneToMany(mappedBy = "temporalByTemporalInstanceId")
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

    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }

    public Timestamp getEnddate() {
        return enddate;
    }

    public void setEnddate(Timestamp enddate) {
        this.enddate = enddate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Temporal temporal = (Temporal) o;

        if (instanceId != null ? !instanceId.equals(temporal.instanceId) : temporal.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(temporal.metaId) : temporal.metaId != null) return false;
        if (uid != null ? !uid.equals(temporal.uid) : temporal.uid != null) return false;
        if (versionId != null ? !versionId.equals(temporal.versionId) : temporal.versionId != null) return false;
        if (startdate != null ? !startdate.equals(temporal.startdate) : temporal.startdate != null) return false;
        if (enddate != null ? !enddate.equals(temporal.enddate) : temporal.enddate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (startdate != null ? startdate.hashCode() : 0);
        result = 31 * result + (enddate != null ? enddate.hashCode() : 0);
        return result;
    }

    public Collection<DataproductTemporal> getDataproductTemporalsByInstanceId() {
        return dataproductTemporalsByInstanceId;
    }

    public void setDataproductTemporalsByInstanceId(Collection<DataproductTemporal> dataproductTemporalsByInstanceId) {
        this.dataproductTemporalsByInstanceId = dataproductTemporalsByInstanceId;
    }

    public Collection<EquipmentTemporal> getEquipmentTemporalsByInstanceId() {
        return equipmentTemporalsByInstanceId;
    }

    public void setEquipmentTemporalsByInstanceId(Collection<EquipmentTemporal> equipmentTemporalsByInstanceId) {
        this.equipmentTemporalsByInstanceId = equipmentTemporalsByInstanceId;
    }

    public Collection<ServiceTemporal> getServiceTemporalsByInstanceId() {
        return serviceTemporalsByInstanceId;
    }

    public void setServiceTemporalsByInstanceId(Collection<ServiceTemporal> serviceTemporalsByInstanceId) {
        this.serviceTemporalsByInstanceId = serviceTemporalsByInstanceId;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Collection<WebserviceTemporal> getWebserviceTemporalsByInstanceId() {
        return webserviceTemporalsByInstanceId;
    }

    public void setWebserviceTemporalsByInstanceId(Collection<WebserviceTemporal> webserviceTemporalsByInstanceId) {
        this.webserviceTemporalsByInstanceId = webserviceTemporalsByInstanceId;
    }
}
