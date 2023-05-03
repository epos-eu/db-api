package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "dataproduct_identifier")
//@IdClass(EDMDataproductIdentifierPK.class)
@NamedQueries({
        @NamedQuery(name = "dataproduct_identifier.findAll", query = "SELECT c FROM EDMDataproductIdentifier c"),
        @NamedQuery(name = "dataproduct_identifier.findByTypeAndByIdentifier", query = "select c from EDMDataproductIdentifier c where c.type = :TYPE and c.identifier = :IDENTIFIER")
})
public class EDMDataproductIdentifier {
    private String id;
    private String type;
    private String identifier;
    private String instanceDataproductId;
    private EDMDataproduct dataproductByInstanceDataproductId;

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
    @Column(name = "instance_dataproduct_id", insertable = false, updatable = false)
    public String getInstanceDataproductId() {
        return instanceDataproductId;
    }

    public void setInstanceDataproductId(String instanceDataproductId) {
        this.instanceDataproductId = instanceDataproductId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMDataproductIdentifier that = (EDMDataproductIdentifier) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) return false;
        return instanceDataproductId != null ? instanceDataproductId.equals(that.instanceDataproductId) : that.instanceDataproductId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
        result = 31 * result + (instanceDataproductId != null ? instanceDataproductId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_dataproduct_id", referencedColumnName = "instance_id", nullable = false)
    public EDMDataproduct getDataproductByInstanceDataproductId() {
        return dataproductByInstanceDataproductId;
    }

    public void setDataproductByInstanceDataproductId(EDMDataproduct dataproductByInstanceDataproductId) {
        this.dataproductByInstanceDataproductId = dataproductByInstanceDataproductId;
    }
}
