package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "webservice_identifier")
@NamedQueries({
        @NamedQuery(name = "webservice_identifier.findAll", query = "SELECT c FROM EDMWebserviceIdentifier c"),
        @NamedQuery(name = "webservice_identifier.findByTypeAndByIdentifier", query = "select c from EDMWebserviceIdentifier c where c.type = :TYPE and c.identifier = :IDENTIFIER")
})
public class EDMWebserviceIdentifier {
    private String type;
    private String identifier;
    private EDMWebservice webserviceByWebserviceId;
    private String id;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "identifier")
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMWebserviceIdentifier that = (EDMWebserviceIdentifier) o;

        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) return false;
        return false;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "webservice_instance_id", referencedColumnName = "uid", nullable = false)
    public EDMWebservice getWebserviceByWebserviceId() {
        return webserviceByWebserviceId;
    }

    public void setWebserviceByWebserviceId(EDMWebservice webserviceByWebserviceId) {
        this.webserviceByWebserviceId = webserviceByWebserviceId;
    }
}
