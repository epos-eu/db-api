package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMGroupUserPK implements Serializable {
    private String metaPersonId;
    private String groupId;
    private String roleId;


    public String getMetaPersonId() {
        return metaPersonId;
    }

    public void setMetaPersonId(String metaPersonId) {
        this.metaPersonId = metaPersonId;
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

        EDMGroupUserPK that = (EDMGroupUserPK) o;

        if (metaPersonId != null ? !metaPersonId.equals(that.metaPersonId) : that.metaPersonId != null) return false;
        return groupId != null ? groupId.equals(that.groupId) : that.groupId == null;
    }

    @Override
    public int hashCode() {
        int result = metaPersonId != null ? metaPersonId.hashCode() : 0;
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        return result;
    }
}
