package model;

import jakarta.persistence.*;

@Entity
@Table(name = "dataproduct_relation", schema = "public", catalog = "cerif")
public class DataproductRelation {
    @Id
    @Column(name = "dataproduct_instance_id", nullable = false, length = 100)
    private String dataproductInstanceId;
    @Basic
    @Column(name = "entity_instance_id", nullable = false, length = 100)
    private String entityInstanceId;
    @Basic
    @Column(name = "resource_entity", nullable = false, length = 100)
    private String resourceEntity;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "dataproduct_instance_id", referencedColumnName = "instance_id")
    private Dataproduct dataproductByDataproductInstanceId;

    public String getDataproductInstanceId() {
        return dataproductInstanceId;
    }

    public void setDataproductInstanceId(String dataproductInstanceId) {
        this.dataproductInstanceId = dataproductInstanceId;
    }

    public String getEntityInstanceId() {
        return entityInstanceId;
    }

    public void setEntityInstanceId(String entityInstanceId) {
        this.entityInstanceId = entityInstanceId;
    }

    public String getResourceEntity() {
        return resourceEntity;
    }

    public void setResourceEntity(String resourceEntity) {
        this.resourceEntity = resourceEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataproductRelation that = (DataproductRelation) o;

        if (dataproductInstanceId != null ? !dataproductInstanceId.equals(that.dataproductInstanceId) : that.dataproductInstanceId != null)
            return false;
        if (entityInstanceId != null ? !entityInstanceId.equals(that.entityInstanceId) : that.entityInstanceId != null)
            return false;
        if (resourceEntity != null ? !resourceEntity.equals(that.resourceEntity) : that.resourceEntity != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dataproductInstanceId != null ? dataproductInstanceId.hashCode() : 0;
        result = 31 * result + (entityInstanceId != null ? entityInstanceId.hashCode() : 0);
        result = 31 * result + (resourceEntity != null ? resourceEntity.hashCode() : 0);
        return result;
    }

    public Dataproduct getDataproductByDataproductInstanceId() {
        return dataproductByDataproductInstanceId;
    }

    public void setDataproductByDataproductInstanceId(Dataproduct dataproductByDataproductInstanceId) {
        this.dataproductByDataproductInstanceId = dataproductByDataproductInstanceId;
    }
}
