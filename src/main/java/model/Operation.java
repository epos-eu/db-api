package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class Operation {
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
    @Column(name = "method", nullable = true, length = 1024)
    private String method;
    @Basic
    @Column(name = "template", nullable = true, length = -1)
    private String template;
    @OneToMany(mappedBy = "operationByIsmappingof")
    private Collection<Mapping> mappingsByInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @OneToMany(mappedBy = "operationByOperationInstanceId")
    private Collection<OperationDistribution> operationDistributionsByInstanceId;
    @OneToMany(mappedBy = "operationByOperationInstanceId")
    private Collection<OperationElement> operationElementsByInstanceId;
    @OneToMany(mappedBy = "operationByOperationInstanceId")
    private Collection<OperationWebservice> operationWebservicesByInstanceId;
    @OneToMany(mappedBy = "operationByOperationInstanceId")
    private Collection<SoftwareapplicationOperation> softwareapplicationOperationsByInstanceId;

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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operation operation = (Operation) o;

        if (instanceId != null ? !instanceId.equals(operation.instanceId) : operation.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(operation.metaId) : operation.metaId != null) return false;
        if (uid != null ? !uid.equals(operation.uid) : operation.uid != null) return false;
        if (versionId != null ? !versionId.equals(operation.versionId) : operation.versionId != null) return false;
        if (method != null ? !method.equals(operation.method) : operation.method != null) return false;
        if (template != null ? !template.equals(operation.template) : operation.template != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (template != null ? template.hashCode() : 0);
        return result;
    }

    public Collection<Mapping> getMappingsByInstanceId() {
        return mappingsByInstanceId;
    }

    public void setMappingsByInstanceId(Collection<Mapping> mappingsByInstanceId) {
        this.mappingsByInstanceId = mappingsByInstanceId;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Collection<OperationDistribution> getOperationDistributionsByInstanceId() {
        return operationDistributionsByInstanceId;
    }

    public void setOperationDistributionsByInstanceId(Collection<OperationDistribution> operationDistributionsByInstanceId) {
        this.operationDistributionsByInstanceId = operationDistributionsByInstanceId;
    }

    public Collection<OperationElement> getOperationElementsByInstanceId() {
        return operationElementsByInstanceId;
    }

    public void setOperationElementsByInstanceId(Collection<OperationElement> operationElementsByInstanceId) {
        this.operationElementsByInstanceId = operationElementsByInstanceId;
    }

    public Collection<OperationWebservice> getOperationWebservicesByInstanceId() {
        return operationWebservicesByInstanceId;
    }

    public void setOperationWebservicesByInstanceId(Collection<OperationWebservice> operationWebservicesByInstanceId) {
        this.operationWebservicesByInstanceId = operationWebservicesByInstanceId;
    }

    public Collection<SoftwareapplicationOperation> getSoftwareapplicationOperationsByInstanceId() {
        return softwareapplicationOperationsByInstanceId;
    }

    public void setSoftwareapplicationOperationsByInstanceId(Collection<SoftwareapplicationOperation> softwareapplicationOperationsByInstanceId) {
        this.softwareapplicationOperationsByInstanceId = softwareapplicationOperationsByInstanceId;
    }
}
