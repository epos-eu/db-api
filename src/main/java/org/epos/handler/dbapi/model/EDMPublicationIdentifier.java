package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "publication_identifier")
@NamedQueries({
        @NamedQuery(name = "publication_identifier.findAll", query = "SELECT c FROM EDMPublicationIdentifier c"),
})
public class EDMPublicationIdentifier {
    private String id;
    private String type;
    private String identifier;
    private String instancePublicationId;
    private EDMPublication publicationByInstancePublicationId;

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

    @Basic
    @Column(name = "instance_publication_id", insertable = false, updatable = false)
    public String getInstancePublicationId() {
        return instancePublicationId;
    }

    public void setInstancePublicationId(String instancePublicationId) {
        this.instancePublicationId = instancePublicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMPublicationIdentifier that = (EDMPublicationIdentifier) o;

        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) return false;
        return instancePublicationId != null ? instancePublicationId.equals(that.instancePublicationId) : that.instancePublicationId == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
        result = 31 * result + (instancePublicationId != null ? instancePublicationId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_publication_id", referencedColumnName = "instance_id", nullable = false)
    public EDMPublication getPublicationByInstancePublicationId() {
        return publicationByInstancePublicationId;
    }

    public void setPublicationByInstancePublicationId(EDMPublication publicationByInstancePublicationId) {
        this.publicationByInstancePublicationId = publicationByInstancePublicationId;
    }
}
