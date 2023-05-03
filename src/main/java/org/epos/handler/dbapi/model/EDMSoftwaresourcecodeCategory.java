package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "softwaresourcecode_category")
@IdClass(EDMSoftwaresourcecodeCategoryPK.class)
public class EDMSoftwaresourcecodeCategory {
    private String categoryId;
    private String instanceSoftwaresourcecodeId;
    private EDMCategory categoryByCategoryId;
    private EDMSoftwaresourcecode softwaresourcecodeByInstanceSoftwaresourcecodeId;

    @Id
    @Column(name = "category_id", insertable = false, updatable = false)
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Id
    @Column(name = "instance_softwaresourcecode_id", insertable = false, updatable = false)
    public String getInstanceSoftwaresourcecodeId() {
        return instanceSoftwaresourcecodeId;
    }

    public void setInstanceSoftwaresourcecodeId(String instanceSoftwaresourcecodeId) {
        this.instanceSoftwaresourcecodeId = instanceSoftwaresourcecodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMSoftwaresourcecodeCategory that = (EDMSoftwaresourcecodeCategory) o;

        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        return instanceSoftwaresourcecodeId != null ? instanceSoftwaresourcecodeId.equals(that.instanceSoftwaresourcecodeId) : that.instanceSoftwaresourcecodeId == null;
    }

    @Override
    public int hashCode() {
        int result = categoryId != null ? categoryId.hashCode() : 0;
        result = 31 * result + (instanceSoftwaresourcecodeId != null ? instanceSoftwaresourcecodeId.hashCode() : 0);
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
    @JoinColumn(name = "instance_softwaresourcecode_id", referencedColumnName = "instance_id", nullable = false)
    public EDMSoftwaresourcecode getSoftwaresourcecodeByInstanceSoftwaresourcecodeId() {
        return softwaresourcecodeByInstanceSoftwaresourcecodeId;
    }

    public void setSoftwaresourcecodeByInstanceSoftwaresourcecodeId(EDMSoftwaresourcecode softwaresourcecodeByInstanceSoftwaresourcecodeId) {
        this.softwaresourcecodeByInstanceSoftwaresourcecodeId = softwaresourcecodeByInstanceSoftwaresourcecodeId;
    }
}
