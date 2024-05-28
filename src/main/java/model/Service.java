package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class Service {
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
    @Column(name = "type", nullable = true, length = 1024)
    private String type;
    @Basic
    @Column(name = "pageurl", nullable = true, length = 1024)
    private String pageurl;
    @Basic
    @Column(name = "keywords", nullable = true, length = -1)
    private String keywords;
    @Basic
    @Column(name = "servicecontract", nullable = true, length = 100)
    private String servicecontract;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @OneToMany(mappedBy = "serviceByServiceInstanceId")
    private Collection<ServiceCategory> serviceCategoriesByInstanceId;
    @OneToMany(mappedBy = "serviceByServiceInstanceId")
    private Collection<ServiceContactpoint> serviceContactpointsByInstanceId;
    @OneToMany(mappedBy = "serviceByServiceInstanceId")
    private Collection<ServiceIdentifier> serviceIdentifiersByInstanceId;
    @OneToOne(mappedBy = "serviceByServiceInstanceId")
    private ServiceProvider serviceProviderByInstanceId;
    @OneToMany(mappedBy = "serviceByServiceInstanceId")
    private Collection<ServiceSpatial> serviceSpatialsByInstanceId;
    @OneToMany(mappedBy = "serviceByServiceInstanceId")
    private Collection<ServiceTemporal> serviceTemporalsByInstanceId;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPageurl() {
        return pageurl;
    }

    public void setPageurl(String pageurl) {
        this.pageurl = pageurl;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getServicecontract() {
        return servicecontract;
    }

    public void setServicecontract(String servicecontract) {
        this.servicecontract = servicecontract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        if (instanceId != null ? !instanceId.equals(service.instanceId) : service.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(service.metaId) : service.metaId != null) return false;
        if (uid != null ? !uid.equals(service.uid) : service.uid != null) return false;
        if (versionId != null ? !versionId.equals(service.versionId) : service.versionId != null) return false;
        if (name != null ? !name.equals(service.name) : service.name != null) return false;
        if (description != null ? !description.equals(service.description) : service.description != null) return false;
        if (type != null ? !type.equals(service.type) : service.type != null) return false;
        if (pageurl != null ? !pageurl.equals(service.pageurl) : service.pageurl != null) return false;
        if (keywords != null ? !keywords.equals(service.keywords) : service.keywords != null) return false;
        if (servicecontract != null ? !servicecontract.equals(service.servicecontract) : service.servicecontract != null)
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
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (pageurl != null ? pageurl.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (servicecontract != null ? servicecontract.hashCode() : 0);
        return result;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Collection<ServiceCategory> getServiceCategoriesByInstanceId() {
        return serviceCategoriesByInstanceId;
    }

    public void setServiceCategoriesByInstanceId(Collection<ServiceCategory> serviceCategoriesByInstanceId) {
        this.serviceCategoriesByInstanceId = serviceCategoriesByInstanceId;
    }

    public Collection<ServiceContactpoint> getServiceContactpointsByInstanceId() {
        return serviceContactpointsByInstanceId;
    }

    public void setServiceContactpointsByInstanceId(Collection<ServiceContactpoint> serviceContactpointsByInstanceId) {
        this.serviceContactpointsByInstanceId = serviceContactpointsByInstanceId;
    }

    public Collection<ServiceIdentifier> getServiceIdentifiersByInstanceId() {
        return serviceIdentifiersByInstanceId;
    }

    public void setServiceIdentifiersByInstanceId(Collection<ServiceIdentifier> serviceIdentifiersByInstanceId) {
        this.serviceIdentifiersByInstanceId = serviceIdentifiersByInstanceId;
    }

    public ServiceProvider getServiceProviderByInstanceId() {
        return serviceProviderByInstanceId;
    }

    public void setServiceProviderByInstanceId(ServiceProvider serviceProviderByInstanceId) {
        this.serviceProviderByInstanceId = serviceProviderByInstanceId;
    }

    public Collection<ServiceSpatial> getServiceSpatialsByInstanceId() {
        return serviceSpatialsByInstanceId;
    }

    public void setServiceSpatialsByInstanceId(Collection<ServiceSpatial> serviceSpatialsByInstanceId) {
        this.serviceSpatialsByInstanceId = serviceSpatialsByInstanceId;
    }

    public Collection<ServiceTemporal> getServiceTemporalsByInstanceId() {
        return serviceTemporalsByInstanceId;
    }

    public void setServiceTemporalsByInstanceId(Collection<ServiceTemporal> serviceTemporalsByInstanceId) {
        this.serviceTemporalsByInstanceId = serviceTemporalsByInstanceId;
    }
}
