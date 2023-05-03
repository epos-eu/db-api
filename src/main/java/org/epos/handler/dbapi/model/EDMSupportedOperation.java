package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "webservice_supportedoperation")
@IdClass(EDMSupportedOperationPK.class)
@NamedQueries({
        @NamedQuery(name = "supportedOperation.findByinstanceId", query = "SELECT c FROM EDMSupportedOperation c where c.instanceWebserviceId = :WSINST and c.instanceOperationId = :OPINST")
})public class EDMSupportedOperation {
    private String instanceWebserviceId;
    private String instanceOperationId;
    private EDMWebservice webserviceByInstanceWebserviceId;
    private EDMOperation operationByInstanceOperationId;


    @Id
    @Column(name = "instance_webservice_id", insertable = false, updatable = false)
    public String getInstanceWebserviceId() {
        return instanceWebserviceId;
    }

    public void setInstanceWebserviceId(String instanceWebserviceId) {
        this.instanceWebserviceId = instanceWebserviceId;
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
        EDMSupportedOperation that = (EDMSupportedOperation) o;
        return Objects.equals(getInstanceWebserviceId(), that.getInstanceWebserviceId()) && Objects.equals(getInstanceOperationId(), that.getInstanceOperationId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInstanceWebserviceId(), getInstanceOperationId());
    }

    @ManyToOne
    @JoinColumn(name = "instance_webservice_id", referencedColumnName = "instance_id", nullable = false)
    public EDMWebservice getWebserviceByInstanceWebserviceId() {
        return webserviceByInstanceWebserviceId;
    }

    public void setWebserviceByInstanceWebserviceId(EDMWebservice webserviceByInstanceWebserviceId) {
        this.webserviceByInstanceWebserviceId = webserviceByInstanceWebserviceId;
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
