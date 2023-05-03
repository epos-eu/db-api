package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "group_user_")
@IdClass(EDMGroupUserPK.class)
public class EDMGroupUser {
    private String metaPersonId;
    private String groupId;
    private String roleId;
    private EDMEdmEntityId edmEntityIdByMetaPersonId;
    private EDMGroup groupByGroupId;
    private EDMRole roleByRoleId;

    @Id
    @Column(name = "meta_person_id", insertable = false, updatable = false)
    public String getMetaPersonId() {
        return metaPersonId;
    }

    public void setMetaPersonId(String metaPersonId) {
        this.metaPersonId = metaPersonId;
    }

    @Id
    @Column(name = "group_id", insertable = false, updatable = false)
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Id
    @Column(name = "role_id", insertable = false, updatable = false)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMGroupUser that = (EDMGroupUser) o;

        if (metaPersonId != null ? !metaPersonId.equals(that.metaPersonId) : that.metaPersonId != null) return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        return roleId != null ? roleId.equals(that.roleId) : that.roleId == null;
    }

    @Override
    public int hashCode() {
        int result = metaPersonId != null ? metaPersonId.hashCode() : 0;
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "meta_person_id", referencedColumnName = "meta_id", nullable = false)
    public EDMEdmEntityId getEdmEntityIdByMetaPersonId() {
        return edmEntityIdByMetaPersonId;
    }

    public void setEdmEntityIdByMetaPersonId(EDMEdmEntityId edmEntityIdByMetaPersonId) {
        this.edmEntityIdByMetaPersonId = edmEntityIdByMetaPersonId;
    }

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    public EDMGroup getGroupByGroupId() {
        return groupByGroupId;
    }

    public void setGroupByGroupId(EDMGroup groupByGroupId) {
        this.groupByGroupId = groupByGroupId;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public EDMRole getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(EDMRole roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }
}
