package org.epos.handler.dbapi.model;

import java.io.Serializable;
import java.util.Objects;

public class EDMDistributionAccessURLPK implements Serializable {
    private String instanceDistributionId;
    private String instanceOperationId;

    public String getInstanceDistributionId() {
        return instanceDistributionId;
    }

    public void setInstanceDistributionId(String instanceDistributionId) {
        this.instanceDistributionId = instanceDistributionId;
    }

    public String getInstanceOperationId() {
        return instanceOperationId;
    }

    public void setInstanceOperationId(String instanceOperationId) {
        this.instanceOperationId = instanceOperationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EDMDistributionAccessURLPK that = (EDMDistributionAccessURLPK) o;
        return Objects.equals(getInstanceDistributionId(), that.getInstanceDistributionId()) && Objects.equals(getInstanceOperationId(), that.getInstanceOperationId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInstanceDistributionId(), getInstanceOperationId());
    }
}
