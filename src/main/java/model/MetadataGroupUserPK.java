package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class MetadataGroupUserPK implements Serializable {
    @Column(name = "auth_identifier", nullable = false, length = 100)
    @Id
    private String authIdentifier;
    @Column(name = "group_id", nullable = false, length = 100)
    @Id
    private String groupId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetadataGroupUserPK that = (MetadataGroupUserPK) o;

        if (authIdentifier != null ? !authIdentifier.equals(that.authIdentifier) : that.authIdentifier != null)
            return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authIdentifier != null ? authIdentifier.hashCode() : 0;
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        return result;
    }
}
