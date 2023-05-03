package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "group_")
@NamedQueries({
        @NamedQuery(name = "group.findById", query = "SELECT c FROM EDMGroup c WHERE c.id=:ID")
})
public class EDMGroup {
    private String id;
    private String name;
    private String description;
    private Collection<EDMAuthorization> authorizationsById;
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

        EDMGroup edmGroup = (EDMGroup) o;

        if (id != null ? !id.equals(edmGroup.id) : edmGroup.id != null) return false;
        if (name != null ? !name.equals(edmGroup.name) : edmGroup.name != null) return false;
        return description != null ? description.equals(edmGroup.description) : edmGroup.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "groupByGroupId")
    public Collection<EDMAuthorization> getAuthorizationsById() {
        return authorizationsById;
    }

    public void setAuthorizationsById(Collection<EDMAuthorization> authorizationsById) {
        this.authorizationsById = authorizationsById;
    }

    @OneToMany(mappedBy = "groupByGroupId")
    public Collection<EDMGroupUser> getGroupUsersById() {
        return groupUsersById;
    }

    public void setGroupUsersById(Collection<EDMGroupUser> groupUsersById) {
        this.groupUsersById = groupUsersById;
    }
}
