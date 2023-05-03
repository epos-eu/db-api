package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "contributor")
@IdClass(EDMContributorPK.class)
public class EDMContributor {
    private String metaPersonId;
    private String instancePublicationId;
    private EDMEdmEntityId edmEntityIdByMetaPersonId;
    private EDMPublication publicationByInstancePublicationId;

    @Id
    @Column(name = "meta_person_id", insertable = false, updatable = false)
    public String getMetaPersonId() {
        return metaPersonId;
    }

    public void setMetaPersonId(String metaPersonId) {
        this.metaPersonId = metaPersonId;
    }

    @Id
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

        EDMContributor that = (EDMContributor) o;

        if (metaPersonId != null ? !metaPersonId.equals(that.metaPersonId) : that.metaPersonId != null) return false;
        return instancePublicationId != null ? instancePublicationId.equals(that.instancePublicationId) : that.instancePublicationId == null;
    }

    @Override
    public int hashCode() {
        int result = (metaPersonId != null ? metaPersonId.hashCode() : 0);
        result = 31 * result + (instancePublicationId != null ? instancePublicationId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "meta_person_id", referencedColumnName = "meta_id", nullable = false)
    public EDMEdmEntityId getEdmEntityIdByMetaPersonId() {
        return edmEntityIdByMetaPersonId;
    }

    public void setEdmEntityIdByMetaPersonId(EDMEdmEntityId edmEntityIdByMetaPersonId) {
        this.edmEntityIdByMetaPersonId = edmEntityIdByMetaPersonId;
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
