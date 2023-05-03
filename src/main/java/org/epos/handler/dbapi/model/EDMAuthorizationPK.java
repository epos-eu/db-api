package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMAuthorizationPK implements Serializable {
    private String groupId;
    private String metaId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getMetaId() {
        return metaId;
    }

    public void setMetaId(String metaId) {
        this.metaId = metaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMAuthorizationPK that = (EDMAuthorizationPK) o;

        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        return metaId != null ? metaId.equals(that.metaId) : that.metaId == null;
    }

    @Override
    public int hashCode() {
        int result = groupId != null ? groupId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        return result;
    }
}
