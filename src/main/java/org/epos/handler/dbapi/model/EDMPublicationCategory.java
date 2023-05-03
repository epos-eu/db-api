package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "publication_category")
@IdClass(EDMPublicationCategoryPK.class)
public class EDMPublicationCategory {
    private String categoryId;
    private String instancePublicationId;
    private EDMCategory categoryByCategoryId;
    private EDMPublication publicationByInstancePublicationId;


    @Id
    @Column(name = "category_id", insertable = false, updatable = false)
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

        EDMPublicationCategory that = (EDMPublicationCategory) o;

        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        return instancePublicationId != null ? instancePublicationId.equals(that.instancePublicationId) : that.instancePublicationId == null;
    }

    @Override
    public int hashCode() {
        int result = (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (instancePublicationId != null ? instancePublicationId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public EDMCategory getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(EDMCategory categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
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
