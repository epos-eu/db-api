package model;

import jakarta.persistence.*;

@Entity
@Table(name = "authorization_group", schema = "public", catalog = "cerif")
public class AuthorizationGroup {
    @Id
    @Column(name = "id", nullable = false, length = 100)
    private String id;
    @Basic
    @Column(name = "group_id", nullable = true, length = 100)
    private String groupId;
    @Basic
    @Column(name = "meta_id", nullable = true, length = 100)
    private String metaId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "group_id", referencedColumnName = "id")
    private MetadataGroup metadataGroupByGroupId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "meta_id", referencedColumnName = "meta_id")
    private EdmEntityId edmEntityIdByMetaId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getMetaId() {
        return metaId;
    }

    public void setMetaId(String metaId) {
        this.metaId = metaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorizationGroup that = (AuthorizationGroup) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        if (metaId != null ? !metaId.equals(that.metaId) : that.metaId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        return result;
    }

    public MetadataGroup getMetadataGroupByGroupId() {
        return metadataGroupByGroupId;
    }

    public void setMetadataGroupByGroupId(MetadataGroup metadataGroupByGroupId) {
        this.metadataGroupByGroupId = metadataGroupByGroupId;
    }

    public EdmEntityId getEdmEntityIdByMetaId() {
        return edmEntityIdByMetaId;
    }

    public void setEdmEntityIdByMetaId(EdmEntityId edmEntityIdByMetaId) {
        this.edmEntityIdByMetaId = edmEntityIdByMetaId;
    }
}
