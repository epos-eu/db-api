package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "metadata_group", schema = "public", catalog = "cerif")
public class MetadataGroup {
    @Id
    @Column(name = "id", nullable = false, length = 100)
    private String id;
    @Basic
    @Column(name = "name", nullable = true, length = 1024)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @OneToMany(mappedBy = "metadataGroupByGroupId")
    private Collection<AuthorizationGroup> authorizationGroupsById;
    @OneToMany(mappedBy = "metadataGroupByGroupId")
    private Collection<MetadataGroupUser> metadataGroupUsersById;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetadataGroup that = (MetadataGroup) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public Collection<AuthorizationGroup> getAuthorizationGroupsById() {
        return authorizationGroupsById;
    }

    public void setAuthorizationGroupsById(Collection<AuthorizationGroup> authorizationGroupsById) {
        this.authorizationGroupsById = authorizationGroupsById;
    }

    public Collection<MetadataGroupUser> getMetadataGroupUsersById() {
        return metadataGroupUsersById;
    }

    public void setMetadataGroupUsersById(Collection<MetadataGroupUser> metadataGroupUsersById) {
        this.metadataGroupUsersById = metadataGroupUsersById;
    }
}
