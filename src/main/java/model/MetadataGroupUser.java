package model;

import jakarta.persistence.*;

@Entity
@Table(name = "metadata_group_user", schema = "public", catalog = "cerif")
@IdClass(MetadataGroupUserPK.class)
public class MetadataGroupUser {
    @Id
    @Column(name = "auth_identifier", nullable = false, length = 100)
    private String authIdentifier;
    @Id
    @Column(name = "group_id", nullable = false, length = 100)
    private String groupId;
    @Basic
    @Column(name = "role_id", nullable = true, length = 100)
    private String roleId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "auth_identifier", referencedColumnName = "auth_identifier")
    private User userByAuthIdentifier;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "group_id", referencedColumnName = "id")
    private MetadataGroup metadataGroupByGroupId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "role_id", referencedColumnName = "id")
    private Role roleByRoleId;

    public String getAuthIdentifier() {
        return authIdentifier;
    }

    public void setAuthIdentifier(String authIdentifier) {
        this.authIdentifier = authIdentifier;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

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

        MetadataGroupUser that = (MetadataGroupUser) o;

        if (authIdentifier != null ? !authIdentifier.equals(that.authIdentifier) : that.authIdentifier != null)
            return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authIdentifier != null ? authIdentifier.hashCode() : 0;
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }

    public User getUserByAuthIdentifier() {
        return userByAuthIdentifier;
    }

    public void setUserByAuthIdentifier(User userByAuthIdentifier) {
        this.userByAuthIdentifier = userByAuthIdentifier;
    }

    public MetadataGroup getMetadataGroupByGroupId() {
        return metadataGroupByGroupId;
    }

    public void setMetadataGroupByGroupId(MetadataGroup metadataGroupByGroupId) {
        this.metadataGroupByGroupId = metadataGroupByGroupId;
    }

    public Role getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(Role roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }
}
