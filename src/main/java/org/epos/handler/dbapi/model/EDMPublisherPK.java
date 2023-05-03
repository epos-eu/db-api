package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMPublisherPK implements Serializable {
    private String instanceDataproductId;
    private String metaOrganizationId;

    public String getInstanceDataproductId() {
        return instanceDataproductId;
    }

    public void setInstanceDataproductId(String instanceDataproductId) {
        this.instanceDataproductId = instanceDataproductId;
    }

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

        EDMPublisherPK that = (EDMPublisherPK) o;

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
}
