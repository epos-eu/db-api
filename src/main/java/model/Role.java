package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class Role {
    @Id
    @Column(name = "id", nullable = false, length = 100)
    private String id;
    @Basic
    @Column(name = "name", nullable = true, length = 1024)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @OneToMany(mappedBy = "roleByRoleId")
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

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;
        if (name != null ? !name.equals(role.name) : role.name != null) return false;
        if (description != null ? !description.equals(role.description) : role.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public Collection<MetadataGroupUser> getMetadataGroupUsersById() {
        return metadataGroupUsersById;
    }

    public void setMetadataGroupUsersById(Collection<MetadataGroupUser> metadataGroupUsersById) {
        this.metadataGroupUsersById = metadataGroupUsersById;
    }
}
