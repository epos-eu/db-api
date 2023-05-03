package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "webservice_documentation")
public class EDMWebserviceDocumentation {
    private String id;
    private String documentation;
    private String instanceWebserviceId;
    private EDMWebservice webserviceByInstanceWebserviceId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "documentation")
    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    @Basic
    @Column(name = "instance_webservice_id", insertable = false, updatable = false)
    public String getInstanceWebserviceId() {
        return instanceWebserviceId;
    }

    public void setInstanceWebserviceId(String instanceWebserviceId) {
        this.instanceWebserviceId = instanceWebserviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMWebserviceDocumentation that = (EDMWebserviceDocumentation) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return documentation != null ? documentation.equals(that.documentation) : that.documentation == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (documentation != null ? documentation.hashCode() : 0);
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
}
