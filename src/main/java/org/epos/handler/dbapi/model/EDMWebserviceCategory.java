package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "webservice_category")
@IdClass(EDMWebserviceCategoryPK.class)
public class EDMWebserviceCategory {
    private String categoryId;
    private String instanceWebserviceId;
    private EDMCategory categoryByCategoryId;
    private EDMWebservice webserviceByInstanceWebserviceId;

    @Id
    @Column(name = "category_id", insertable = false, updatable = false)
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Id
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

        EDMWebserviceCategory that = (EDMWebserviceCategory) o;

        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        return instanceWebserviceId != null ? instanceWebserviceId.equals(that.instanceWebserviceId) : that.instanceWebserviceId == null;
    }

    @Override
    public int hashCode() {
        int result = categoryId != null ? categoryId.hashCode() : 0;
        result = 31 * result + (instanceWebserviceId != null ? instanceWebserviceId.hashCode() : 0);
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
    @JoinColumn(name = "instance_webservice_id", referencedColumnName = "instance_id", nullable = false)
    public EDMWebservice getWebserviceByInstanceWebserviceId() {
        return webserviceByInstanceWebserviceId;
    }

    public void setWebserviceByInstanceWebserviceId(EDMWebservice webserviceByInstanceWebserviceId) {
        this.webserviceByInstanceWebserviceId = webserviceByInstanceWebserviceId;
    }
}
