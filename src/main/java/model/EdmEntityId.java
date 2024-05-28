package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "edm_entity_id", schema = "public", catalog = "cerif")
public class EdmEntityId {
    @Id
    @Column(name = "meta_id", nullable = false, length = 100)
    private String metaId;
    @Basic
    @Column(name = "table_name", nullable = true, length = 1024)
    private String tableName;
    @OneToMany(mappedBy = "edmEntityIdByMetaId")
    private Collection<AuthorizationGroup> authorizationGroupsByMetaId;

    public String getMetaId() {
        return metaId;
    }

    public void setMetaId(String metaId) {
        this.metaId = metaId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EdmEntityId that = (EdmEntityId) o;

        if (metaId != null ? !metaId.equals(that.metaId) : that.metaId != null) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = metaId != null ? metaId.hashCode() : 0;
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        return result;
    }

    public Collection<AuthorizationGroup> getAuthorizationGroupsByMetaId() {
        return authorizationGroupsByMetaId;
    }

    public void setAuthorizationGroupsByMetaId(Collection<AuthorizationGroup> authorizationGroupsByMetaId) {
        this.authorizationGroupsByMetaId = authorizationGroupsByMetaId;
    }
}
