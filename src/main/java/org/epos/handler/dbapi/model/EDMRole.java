package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "role", schema = "public")
public class EDMRole {
    private String id;
    private String name;
    private String description;
    private Collection<EDMGroupUser> groupUsersById;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
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

        EDMRole edmRole = (EDMRole) o;

        if (id != null ? !id.equals(edmRole.id) : edmRole.id != null) return false;
        if (name != null ? !name.equals(edmRole.name) : edmRole.name != null) return false;
        return description != null ? description.equals(edmRole.description) : edmRole.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "roleByRoleId")
    public Collection<EDMGroupUser> getGroupUsersById() {
        return groupUsersById;
    }

    public void setGroupUsersById(Collection<EDMGroupUser> groupUsersById) {
        this.groupUsersById = groupUsersById;
    }
}
