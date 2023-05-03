package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "contactpoint_softwareapplication")
@IdClass(EDMContactpointSoftwareapplicationPK.class)
public class EDMContactpointSoftwareapplication {
    private String instanceSoftwareapplicationId;
    private String instanceContactpointId;
    private EDMSoftwareapplication softwareapplicationByInstanceSoftwareapplicationId;
    private EDMContactpoint contactpointByInstanceContactpointId;

    @Id
    @Column(name = "instance_softwareapplication_id", insertable = false, updatable = false)
    public String getInstanceSoftwareapplicationId() {
        return instanceSoftwareapplicationId;
    }

    public void setInstanceSoftwareapplicationId(String instanceSoftwareapplicationId) {
        this.instanceSoftwareapplicationId = instanceSoftwareapplicationId;
    }

    @Id
    @Column(name = "instance_contactpoint_id", insertable = false, updatable = false)
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

        EDMContactpointSoftwareapplication that = (EDMContactpointSoftwareapplication) o;

        if (instanceSoftwareapplicationId != null ? !instanceSoftwareapplicationId.equals(that.instanceSoftwareapplicationId) : that.instanceSoftwareapplicationId != null)
            return false;
        return instanceContactpointId != null ? instanceContactpointId.equals(that.instanceContactpointId) : that.instanceContactpointId == null;
    }

    @Override
    public int hashCode() {
        int result = instanceSoftwareapplicationId != null ? instanceSoftwareapplicationId.hashCode() : 0;
        result = 31 * result + (instanceContactpointId != null ? instanceContactpointId.hashCode() : 0);
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

    @ManyToOne
    @JoinColumn(name = "instance_contactpoint_id", referencedColumnName = "instance_id", nullable = false)
    public EDMContactpoint getContactpointByInstanceContactpointId() {
        return contactpointByInstanceContactpointId;
    }

    public void setContactpointByInstanceContactpointId(EDMContactpoint contactpointByInstanceContactpointId) {
        this.contactpointByInstanceContactpointId = contactpointByInstanceContactpointId;
    }
}
