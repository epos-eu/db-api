package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "softwareapplication_parameters")
public class EDMSoftwareapplicationParameters {
    private String id;
    private String encodingformat;
    private String conformsto;
    private String action;
    private String instanceSoftwareapplicationId;
    private EDMSoftwareapplication softwareapplicationByInstanceSoftwareapplicationId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "encodingformat")
    public String getEncodingformat() {
        return encodingformat;
    }

    public void setEncodingformat(String encodingformat) {
        this.encodingformat = encodingformat;
    }

    @Basic
    @Column(name = "conformsto")
    public String getConformsto() {
        return conformsto;
    }

    public void setConformsto(String conformsto) {
        this.conformsto = conformsto;
    }

    @Basic
    @Column(name = "action")
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Basic
    @Column(name = "instance_softwareapplication_id", insertable = false, updatable = false)
    public String getInstanceSoftwareapplicationId() {
        return instanceSoftwareapplicationId;
    }

    public void setInstanceSoftwareapplicationId(String instanceSoftwareapplicationId) {
        this.instanceSoftwareapplicationId = instanceSoftwareapplicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMSoftwareapplicationParameters that = (EDMSoftwareapplicationParameters) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (encodingformat != null ? !encodingformat.equals(that.encodingformat) : that.encodingformat != null)
            return false;
        if (conformsto != null ? !conformsto.equals(that.conformsto) : that.conformsto != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        return instanceSoftwareapplicationId != null ? instanceSoftwareapplicationId.equals(that.instanceSoftwareapplicationId) : that.instanceSoftwareapplicationId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (encodingformat != null ? encodingformat.hashCode() : 0);
        result = 31 * result + (conformsto != null ? conformsto.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (instanceSoftwareapplicationId != null ? instanceSoftwareapplicationId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_softwareapplication_id", referencedColumnName = "instance_id", nullable = false)
    public EDMSoftwareapplication getSoftwareapplicationByInstanceSoftwareapplicationId() {
        return softwareapplicationByInstanceSoftwareapplicationId;
    }

    public void setSoftwareapplicationByInstanceSoftwareapplicationId(EDMSoftwareapplication softwareapplicationByInstanceSoftwareapplicationId) {
        this.softwareapplicationByInstanceSoftwareapplicationId = softwareapplicationByInstanceSoftwareapplicationId;
    }
}
