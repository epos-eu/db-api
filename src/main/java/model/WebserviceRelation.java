package model;

import jakarta.persistence.*;

@Entity
@Table(name = "webservice_relation", schema = "public", catalog = "cerif")
public class WebserviceRelation {
    @Id
    @Column(name = "webservice_instance_id", nullable = false, length = 100)
    private String webserviceInstanceId;
    @Basic
    @Column(name = "entity_instance_id", nullable = false, length = 100)
    private String entityInstanceId;
    @Basic
    @Column(name = "resource_entity", nullable = false, length = 100)
    private String resourceEntity;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "webservice_instance_id", referencedColumnName = "instance_id")
    private Webservice webserviceByWebserviceInstanceId;

    public String getWebserviceInstanceId() {
        return webserviceInstanceId;
    }

    public void setWebserviceInstanceId(String webserviceInstanceId) {
        this.webserviceInstanceId = webserviceInstanceId;
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

        WebserviceRelation that = (WebserviceRelation) o;

        if (webserviceInstanceId != null ? !webserviceInstanceId.equals(that.webserviceInstanceId) : that.webserviceInstanceId != null)
            return false;
        if (entityInstanceId != null ? !entityInstanceId.equals(that.entityInstanceId) : that.entityInstanceId != null)
            return false;
        if (resourceEntity != null ? !resourceEntity.equals(that.resourceEntity) : that.resourceEntity != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = webserviceInstanceId != null ? webserviceInstanceId.hashCode() : 0;
        result = 31 * result + (entityInstanceId != null ? entityInstanceId.hashCode() : 0);
        result = 31 * result + (resourceEntity != null ? resourceEntity.hashCode() : 0);
        return result;
    }

    public Webservice getWebserviceByWebserviceInstanceId() {
        return webserviceByWebserviceInstanceId;
    }

    public void setWebserviceByWebserviceInstanceId(Webservice webserviceByWebserviceInstanceId) {
        this.webserviceByWebserviceInstanceId = webserviceByWebserviceInstanceId;
    }
}
