package org.epos.eposdatamodel;

import model.RoleType;

import java.util.List;
import java.util.Objects;

public class UserGroup {

    private RoleType role;
    private Group groups;

    public UserGroup(RoleType role, Group groups) {
        this.role = role;
        this.groups = groups;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public Group getGroups() {
        return groups;
    }

    public void setGroups(Group groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGroup userGroup = (UserGroup) o;
        return role == userGroup.role && Objects.equals(groups, userGroup.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, groups);
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "role=" + role +
                ", groups=" + groups +
                '}';
    }
}
