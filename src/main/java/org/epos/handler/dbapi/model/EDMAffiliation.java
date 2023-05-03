package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "affiliation")
@IdClass(EDMAffiliationPK.class)
public class EDMAffiliation {
    private String instancePersonId;
    private String metaOrganizationId;
    private EDMPerson personByInstancePersonId;
    private EDMEdmEntityId edmEntityIdByMetaOrganizationId;

    @Id
    @Column(name = "instance_person_id", insertable = false, updatable = false)
    public String getInstancePersonId() {
        return instancePersonId;
    }

    public void setInstancePersonId(String instancePersonId) {
        this.instancePersonId = instancePersonId;
    }

    @Id
    @Column(name = "meta_organization_id", insertable = false, updatable = false)
    public String getMetaOrganizationId() {
        return metaOrganizationId;
    }

    public void setMetaOrganizationId(String metaOrganizationId) {
        this.metaOrganizationId = metaOrganizationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMAffiliation that = (EDMAffiliation) o;

        if (instancePersonId != null ? !instancePersonId.equals(that.instancePersonId) : that.instancePersonId != null)
            return false;
        return metaOrganizationId != null ? metaOrganizationId.equals(that.metaOrganizationId) : that.metaOrganizationId == null;
    }

    @Override
    public int hashCode() {
        int result = instancePersonId != null ? instancePersonId.hashCode() : 0;
        result = 31 * result + (metaOrganizationId != null ? metaOrganizationId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_person_id", referencedColumnName = "instance_id", nullable = false)
    public EDMPerson getPersonByInstancePersonId() {
        return personByInstancePersonId;
    }

    public void setPersonByInstancePersonId(EDMPerson personByInstancePersonId) {
        this.personByInstancePersonId = personByInstancePersonId;
    }

    @ManyToOne
    @JoinColumn(name = "meta_organization_id", referencedColumnName = "meta_id", nullable = false)
    public EDMEdmEntityId getEdmEntityIdByMetaOrganizationId() {
        return edmEntityIdByMetaOrganizationId;
    }

    public void setEdmEntityIdByMetaOrganizationId(EDMEdmEntityId edmEntityIdByMetaOrganizationId) {
        this.edmEntityIdByMetaOrganizationId = edmEntityIdByMetaOrganizationId;
    }
}
