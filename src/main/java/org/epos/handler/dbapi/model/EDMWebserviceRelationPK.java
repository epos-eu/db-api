package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMWebserviceRelationPK implements Serializable {
    private String instanceWebserviceId;
    private String instanceWebserviceId_0;

    public String getInstanceWebserviceId() {
        return instanceWebserviceId;
    }

    public void setInstanceWebserviceId(String instanceWebserviceId) {
        this.instanceWebserviceId = instanceWebserviceId;
    }

    public String getInstanceWebserviceId_0() {
        return instanceWebserviceId_0;
    }

    public void setInstanceWebserviceId_0(String instanceWebserviceId_0) {
        this.instanceWebserviceId_0 = instanceWebserviceId_0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMWebserviceRelationPK that = (EDMWebserviceRelationPK) o;

        if (instanceWebserviceId != null ? !instanceWebserviceId.equals(that.instanceWebserviceId) : that.instanceWebserviceId != null)
            return false;
        return instanceWebserviceId_0 != null ? instanceWebserviceId_0.equals(that.instanceWebserviceId_0) : that.instanceWebserviceId_0 == null;
    }

    @Override
    public int hashCode() {
        int result = instanceWebserviceId != null ? instanceWebserviceId.hashCode() : 0;
        result = 31 * result + (instanceWebserviceId_0 != null ? instanceWebserviceId_0.hashCode() : 0);
        return result;
    }
}
