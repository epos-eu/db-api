package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "distribution_accessurl")
@IdClass(EDMDistributionAccessURLPK.class)
@NamedQueries({
        @NamedQuery(name = "accessurl.findByinstanceId", query = "SELECT c FROM EDMDistributionAccessURL c where c.instanceDistributionId = :DISTINST and c.instanceOperationId = :OPINST")
})public class EDMDistributionAccessURL {
    private String instanceDistributionId;
    private String instanceOperationId;
    private EDMDistribution distributionByInstanceDistributionId;
    private EDMOperation operationByInstanceOperationId;


    @Id
    @Column(name = "instance_distribution_id", insertable = false, updatable = false)
    public String getInstanceDistributionId() {
        return instanceDistributionId;
    }

    public void setInstanceDistributionId(String instanceDistributionId) {
        this.instanceDistributionId = instanceDistributionId;
    }

    @Id
    @Column(name = "instance_operation_id", insertable = false, updatable = false)
    public String getInstanceOperationId() {
        return instanceOperationId;
    }

    public void setInstanceOperationId(String metaOperationId) {
        this.instanceOperationId = metaOperationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EDMDistributionAccessURL that = (EDMDistributionAccessURL) o;
        return Objects.equals(getInstanceDistributionId(), that.getInstanceDistributionId()) && Objects.equals(getInstanceOperationId(), that.getInstanceOperationId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInstanceDistributionId(), getInstanceOperationId());
    }

    @ManyToOne
    @JoinColumn(name = "instance_distribution_id", referencedColumnName = "instance_id", nullable = false)
    public EDMDistribution getDistributionByInstanceDistributionId() {
        return distributionByInstanceDistributionId;
    }

    public void setDistributionByInstanceDistributionId(EDMDistribution distributionByInstanceDistributionId) {
        this.distributionByInstanceDistributionId = distributionByInstanceDistributionId;
    }


    @ManyToOne
    @JoinColumn(name = "instance_operation_id", referencedColumnName = "instance_id", nullable = false)
    public EDMOperation getOperationByInstanceOperationId() {
        return operationByInstanceOperationId;
    }

    public void setOperationByInstanceOperationId(EDMOperation edmEntityIdByMetaOperationId) {
        this.operationByInstanceOperationId = edmEntityIdByMetaOperationId;
    }
}
