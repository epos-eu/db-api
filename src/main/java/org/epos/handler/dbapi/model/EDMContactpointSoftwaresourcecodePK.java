package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMContactpointSoftwaresourcecodePK implements Serializable {
    private String instanceSoftwaresourcecodeId;
    private String metaContactpointId;

    public String getInstanceSoftwaresourcecodeId() {
        return instanceSoftwaresourcecodeId;
    }

    public void setInstanceSoftwaresourcecodeId(String instanceSoftwaresourcecodeId) {
        this.instanceSoftwaresourcecodeId = instanceSoftwaresourcecodeId;
    }

    public String getInstanceContactpointId() {
        return metaContactpointId;
    }

    public void setInstanceContactpointId(String metaContactpointId) {
        this.metaContactpointId = metaContactpointId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMContactpointSoftwaresourcecodePK that = (EDMContactpointSoftwaresourcecodePK) o;

        if (instanceSoftwaresourcecodeId != null ? !instanceSoftwaresourcecodeId.equals(that.instanceSoftwaresourcecodeId) : that.instanceSoftwaresourcecodeId != null)
            return false;
        return metaContactpointId != null ? metaContactpointId.equals(that.metaContactpointId) : that.metaContactpointId == null;
    }

    @Override
    public int hashCode() {
        int result = instanceSoftwaresourcecodeId != null ? instanceSoftwaresourcecodeId.hashCode() : 0;
        result = 31 * result + (metaContactpointId != null ? metaContactpointId.hashCode() : 0);
        return result;
    }
}
