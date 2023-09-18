package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "webservice_relation")
@IdClass(EDMWebserviceRelationPK.class)
public class EDMWebserviceRelation {
    private String instanceWebserviceId;
    private String instanceWebserviceId_0;
    private EDMWebservice webserviceByInstanceWebserviceId;
    private EDMWebservice webserviceByInstanceWebserviceId_0;

    @Id
    @Column(name = "instance_webservice_id", insertable = false, updatable = false)
    public String getInstanceWebserviceId() {
        return instanceWebserviceId;
    }

    public void setInstanceWebserviceId(String webserviceByInstanceWebserviceId) {
        this.instanceWebserviceId = webserviceByInstanceWebserviceId;
    }

    @Id
    @Column(name = "instance_webservice_id_0", insertable = false, updatable = false)
    public String getInstanceWebserviceId_0() {
        return instanceWebserviceId_0;
    }

    public void setInstanceWebserviceId_0(String webserviceByInstanceWebserviceId_0) {
        this.instanceWebserviceId_0 = webserviceByInstanceWebserviceId_0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMWebserviceRelation that = (EDMWebserviceRelation) o;

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

    @ManyToOne
    @JoinColumn(name = "instance_webservice_id_0", referencedColumnName = "instance_id", nullable = false)
    public EDMWebservice getWebserviceByInstanceWebserviceId_0() {
        return webserviceByInstanceWebserviceId_0;
    }

    public void setWebserviceByInstanceWebserviceId_0(EDMWebservice edmEntityIdByMetaWebserviceId) {
        this.webserviceByInstanceWebserviceId_0 = edmEntityIdByMetaWebserviceId;
    }

    @ManyToOne
    @JoinColumn(name = "instance_webservice_id", referencedColumnName = "instance_id", nullable = false)
    public EDMWebservice getWebserviceByInstanceWebserviceId() {
        return webserviceByInstanceWebserviceId;
    }

    public void setWebserviceByInstanceWebserviceId(EDMWebservice webserviceByInstanceWebserviceId) {
        this.webserviceByInstanceWebserviceId = webserviceByInstanceWebserviceId;
    }
}
