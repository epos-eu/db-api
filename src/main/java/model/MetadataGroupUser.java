package model;

import jakarta.persistence.*;

@Entity
@Table(name = "metadata_group_user", schema = "public", catalog = "cerif")
public class MetadataGroupUser {
    @Id
    @Column(name = "id", nullable = false, length = 100)
    private String id;
    @Column(name = "auth_identifier", nullable = false, length = 100)
    private String authIdentifier;
    @Column(name = "group_id", nullable = false, length = 100)
    private String groupId;
    @Basic
    @Column(name = "request_status", nullable = true, length = 100)
    private RequestStatusType requestStatus;
    @Basic
    @Column(name = "role", nullable = true, length = 100)
    private RoleType role;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "auth_identifier", referencedColumnName = "auth_identifier")
    private User userByAuthIdentifier;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "group_id", referencedColumnName = "id")
    private MetadataGroup metadataGroupByGroupId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public RequestStatusType getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatusType requestStatus) {
        this.requestStatus = requestStatus;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetadataGroupUser that = (MetadataGroupUser) o;

        if (authIdentifier != null ? !authIdentifier.equals(that.authIdentifier) : that.authIdentifier != null)
            return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        if (requestStatus != null ? !requestStatus.equals(that.requestStatus) : that.requestStatus != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authIdentifier != null ? authIdentifier.hashCode() : 0;
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (requestStatus != null ? requestStatus.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
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

}
