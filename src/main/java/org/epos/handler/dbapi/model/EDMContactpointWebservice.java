package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "contactpoint_webservice")
@IdClass(EDMContactpointWebservicePK.class)
public class EDMContactpointWebservice {
    private String instanceWebserviceId;
    private String instanceContactpointId;
    private EDMWebservice webserviceByInstanceWebserviceId;
    private EDMContactpoint contactpointByInstanceContactpointId;

    @Id
    @Column(name = "instance_webservice_id", insertable = false, updatable = false)
    public String getInstanceWebserviceId() {
        return instanceWebserviceId;
    }

    public void setInstanceWebserviceId(String instanceWebserviceId) {
        this.instanceWebserviceId = instanceWebserviceId;
    }

    @Id
    @Column(name = "instance_contactpoint_id", insertable = false, updatable = false)
    public String getInstanceContactpointId() {
        return instanceContactpointId;
    }

    public void setInstanceContactpointId(String instanceContactpointId) {
        this.instanceContactpointId = instanceContactpointId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMContactpointWebservice that = (EDMContactpointWebservice) o;

        if (instanceWebserviceId != null ? !instanceWebserviceId.equals(that.instanceWebserviceId) : that.instanceWebserviceId != null)
            return false;
        return instanceContactpointId != null ? instanceContactpointId.equals(that.instanceContactpointId) : that.instanceContactpointId == null;
    }

    @Override
    public int hashCode() {
        int result = instanceWebserviceId != null ? instanceWebserviceId.hashCode() : 0;
        result = 31 * result + (instanceContactpointId != null ? instanceContactpointId.hashCode() : 0);
        return result;
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
    @JoinColumn(name = "instance_contactpoint_id", referencedColumnName = "instance_id", nullable = false)
    public EDMContactpoint getContactpointByInstanceContactpointId() {
        return contactpointByInstanceContactpointId;
    }

    public void setContactpointByInstanceContactpointId(EDMContactpoint edmEntityIdByMetaContactpointId) {
        this.contactpointByInstanceContactpointId = edmEntityIdByMetaContactpointId;
    }
}
