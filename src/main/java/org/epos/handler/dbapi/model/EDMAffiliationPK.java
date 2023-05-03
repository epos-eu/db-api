package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMAffiliationPK implements Serializable {
    private String instancePersonId;
    private String metaOrganizationId;

    public String getInstancePersonId() {
        return instancePersonId;
    }

    public void setInstancePersonId(String instancePersonId) {
        this.instancePersonId = instancePersonId;
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

        EDMAffiliationPK that = (EDMAffiliationPK) o;

        if (instancePersonId != null ? !instancePersonId.equals(that.instancePersonId) : that.instancePersonId != null)
            return false;
        return metaOrganizationId != null ? metaOrganizationId.equals(that.metaOrganizationId) : that.metaOrganizationId == null;
    }

    @Override
    public int hashCode() {
        int result = instancePersonId != null ? instancePersonId.hashCode() : 0;
        result = 31 * result + (metaOrganizationId != null ? metaOrganizationId.hashCode() : 0);
        return result;
    }
}
