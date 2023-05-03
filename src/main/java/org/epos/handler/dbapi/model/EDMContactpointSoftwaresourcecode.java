package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "contactpoint_softwaresourcecode")
@IdClass(EDMContactpointSoftwaresourcecodePK.class)
public class EDMContactpointSoftwaresourcecode {
    private String instanceSoftwaresourcecodeId;
    private String instanceContactpointId;
    private EDMSoftwaresourcecode softwaresourcecodeByInstanceSoftwaresourcecodeId;
    private EDMContactpoint contactpointByInstanceContactpointId;

    @Id
    @Column(name = "instance_softwaresourcecode_id", insertable = false, updatable = false)
    public String getInstanceSoftwaresourcecodeId() {
        return instanceSoftwaresourcecodeId;
    }

    public void setInstanceSoftwaresourcecodeId(String instanceSoftwaresourcecodeId) {
        this.instanceSoftwaresourcecodeId = instanceSoftwaresourcecodeId;
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

        EDMContactpointSoftwaresourcecode that = (EDMContactpointSoftwaresourcecode) o;

        if (instanceSoftwaresourcecodeId != null ? !instanceSoftwaresourcecodeId.equals(that.instanceSoftwaresourcecodeId) : that.instanceSoftwaresourcecodeId != null)
            return false;
        return instanceContactpointId != null ? instanceContactpointId.equals(that.instanceContactpointId) : that.instanceContactpointId == null;
    }

    @Override
    public int hashCode() {
        int result = instanceSoftwaresourcecodeId != null ? instanceSoftwaresourcecodeId.hashCode() : 0;
        result = 31 * result + (instanceContactpointId != null ? instanceContactpointId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_softwaresourcecode_id", referencedColumnName = "instance_id", nullable = false)
    public EDMSoftwaresourcecode getSoftwaresourcecodeByInstanceSoftwaresourcecodeId() {
        return softwaresourcecodeByInstanceSoftwaresourcecodeId;
    }

    public void setSoftwaresourcecodeByInstanceSoftwaresourcecodeId(EDMSoftwaresourcecode softwaresourcecodeByInstanceSoftwaresourcecodeId) {
        this.softwaresourcecodeByInstanceSoftwaresourcecodeId = softwaresourcecodeByInstanceSoftwaresourcecodeId;
    }

    @ManyToOne
    @JoinColumn(name = "instance_contactpoint_id", referencedColumnName = "instance_id", nullable = false)
    public EDMContactpoint getContactpointByInstanceContactpointId() {
        return contactpointByInstanceContactpointId;
    }

    public void setContactpointByInstanceContactpointId(EDMContactpoint edmEntityIdByMetaContactpointId) {
        this.contactpointByInstanceContactpointId = edmEntityIdByMetaContactpointId;
    }
}
