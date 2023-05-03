package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "publisher")
@IdClass(EDMPublisherPK.class)
public class EDMPublisher {
    private String instanceDataproductId;
    private String metaOrganizationId;
    private EDMDataproduct dataproductByInstanceDataproductId;
    private EDMEdmEntityId edmEntityIdByMetaOrganizationId;

    @Id
    @Column(name = "instance_dataproduct_id", insertable = false, updatable = false)
    public String getInstanceDataproductId() {
        return instanceDataproductId;
    }

    public void setInstanceDataproductId(String instanceDataproductId) {
        this.instanceDataproductId = instanceDataproductId;
    }

    @Id
    @Column(name = "meta_organization_id", insertable = false, updatable = false)
    public String getMetaOrganizationId() {
        return metaOrganizationId;
    }

    public void setMetaOrganizationId(String metaOrganizationId) {
        this.metaOrganizationId = metaOrganizationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMPublisher that = (EDMPublisher) o;

        if (instanceDataproductId != null ? !instanceDataproductId.equals(that.instanceDataproductId) : that.instanceDataproductId != null)
            return false;
        return metaOrganizationId != null ? metaOrganizationId.equals(that.metaOrganizationId) : that.metaOrganizationId == null;
    }

    @Override
    public int hashCode() {
        int result = instanceDataproductId != null ? instanceDataproductId.hashCode() : 0;
        result = 31 * result + (metaOrganizationId != null ? metaOrganizationId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_dataproduct_id", referencedColumnName = "instance_id", nullable = false)
    public EDMDataproduct getDataproductByInstanceDataproductId() {
        return dataproductByInstanceDataproductId;
    }

    public void setDataproductByInstanceDataproductId(EDMDataproduct dataproductByInstanceDataproductId) {
        this.dataproductByInstanceDataproductId = dataproductByInstanceDataproductId;
    }

    @ManyToOne
    @JoinColumn(name = "meta_organization_id", referencedColumnName = "meta_id", nullable = false)
    public EDMEdmEntityId getEdmEntityIdByMetaOrganizationId() {
        return edmEntityIdByMetaOrganizationId;
    }

    public void setEdmEntityIdByMetaOrganizationId(EDMEdmEntityId edmEntityIdByMetaOrganizationId) {
        this.edmEntityIdByMetaOrganizationId = edmEntityIdByMetaOrganizationId;
    }
}
