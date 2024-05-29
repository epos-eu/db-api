package org.epos.eposdatamodel;

import java.util.Objects;

public class Group {
    private String name;
    private String id;
    private String description;
    private Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(getName(), group.getName()) && Objects.equals(getId(), group.getId()) && Objects.equals(getDescription(), group.getDescription()) && getRole() == group.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId(), getDescription(), getRole());
    }
}
