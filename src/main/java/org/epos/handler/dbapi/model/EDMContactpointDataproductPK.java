package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMContactpointDataproductPK implements Serializable {
    private String instanceDataproductId;
    private String instanceContactpointId;

    public String getInstanceDataproductId() {
        return instanceDataproductId;
    }

    public void setInstanceDataproductId(String instanceDataproductId) {
        this.instanceDataproductId = instanceDataproductId;
    }

    public String getInstanceContactpointId() {
        return instanceContactpointId;
    }

    public void setInstanceContactpointId(String metaContactpointId) {
        this.instanceContactpointId = metaContactpointId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMContactpointDataproductPK that = (EDMContactpointDataproductPK) o;

        if (instanceDataproductId != null ? !instanceDataproductId.equals(that.instanceDataproductId) : that.instanceDataproductId != null)
            return false;
        return instanceContactpointId != null ? instanceContactpointId.equals(that.instanceContactpointId) : that.instanceContactpointId == null;
    }

    @Override
    public int hashCode() {
        int result = instanceDataproductId != null ? instanceDataproductId.hashCode() : 0;
        result = 31 * result + (instanceContactpointId != null ? instanceContactpointId.hashCode() : 0);
        return result;
    }
}
