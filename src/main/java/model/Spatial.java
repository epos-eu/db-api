package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class Spatial {
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
    @Column(name = "location", nullable = false, length = -1)
    private String location;
    @OneToMany(mappedBy = "spatialBySpatialInstanceId")
    private Collection<DataproductSpatial> dataproductSpatialsByInstanceId;
    @OneToMany(mappedBy = "spatialBySpatialInstanceId")
    private Collection<EquipmentSpatial> equipmentSpatialsByInstanceId;
    @OneToMany(mappedBy = "spatialBySpatialInstanceId")
    private Collection<FacilitySpatial> facilitySpatialsByInstanceId;
    @OneToMany(mappedBy = "spatialBySpatialInstanceId")
    private Collection<ServiceSpatial> serviceSpatialsByInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @OneToMany(mappedBy = "spatialBySpatialInstanceId")
    private Collection<WebserviceSpatial> webserviceSpatialsByInstanceId;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Spatial spatial = (Spatial) o;

        if (instanceId != null ? !instanceId.equals(spatial.instanceId) : spatial.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(spatial.metaId) : spatial.metaId != null) return false;
        if (uid != null ? !uid.equals(spatial.uid) : spatial.uid != null) return false;
        if (versionId != null ? !versionId.equals(spatial.versionId) : spatial.versionId != null) return false;
        if (location != null ? !location.equals(spatial.location) : spatial.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    public Collection<DataproductSpatial> getDataproductSpatialsByInstanceId() {
        return dataproductSpatialsByInstanceId;
    }

    public void setDataproductSpatialsByInstanceId(Collection<DataproductSpatial> dataproductSpatialsByInstanceId) {
        this.dataproductSpatialsByInstanceId = dataproductSpatialsByInstanceId;
    }

    public Collection<EquipmentSpatial> getEquipmentSpatialsByInstanceId() {
        return equipmentSpatialsByInstanceId;
    }

    public void setEquipmentSpatialsByInstanceId(Collection<EquipmentSpatial> equipmentSpatialsByInstanceId) {
        this.equipmentSpatialsByInstanceId = equipmentSpatialsByInstanceId;
    }

    public Collection<FacilitySpatial> getFacilitySpatialsByInstanceId() {
        return facilitySpatialsByInstanceId;
    }

    public void setFacilitySpatialsByInstanceId(Collection<FacilitySpatial> facilitySpatialsByInstanceId) {
        this.facilitySpatialsByInstanceId = facilitySpatialsByInstanceId;
    }

    public Collection<ServiceSpatial> getServiceSpatialsByInstanceId() {
        return serviceSpatialsByInstanceId;
    }

    public void setServiceSpatialsByInstanceId(Collection<ServiceSpatial> serviceSpatialsByInstanceId) {
        this.serviceSpatialsByInstanceId = serviceSpatialsByInstanceId;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Collection<WebserviceSpatial> getWebserviceSpatialsByInstanceId() {
        return webserviceSpatialsByInstanceId;
    }

    public void setWebserviceSpatialsByInstanceId(Collection<WebserviceSpatial> webserviceSpatialsByInstanceId) {
        this.webserviceSpatialsByInstanceId = webserviceSpatialsByInstanceId;
    }
}
