package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "authorization_")
@NamedQueries({
        @NamedQuery(name = "authorization.findByMetaIdAndGroupId", query = "SELECT c FROM EDMAuthorization c WHERE c.metaId=:METAID AND c.groupId=:GROUPID")
})
@IdClass(EDMAuthorizationPK.class)
public class EDMAuthorization {
    private String groupId;
    private String metaId;
    private EDMGroup groupByGroupId;
    private EDMEdmEntityId edmEntityIdByMetaId;

    @Id
    @Column(name = "group_id", insertable = false, updatable = false)
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Id
    @Column(name = "meta_id", insertable = false, updatable = false)
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

        EDMAuthorization that = (EDMAuthorization) o;

        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        return metaId != null ? metaId.equals(that.metaId) : that.metaId == null;
    }

    @Override
    public int hashCode() {
        int result = groupId != null ? groupId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        return result;
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
    @JoinColumn(name = "meta_id", referencedColumnName = "meta_id", nullable = false)
    public EDMEdmEntityId getEdmEntityIdByMetaId() {
        return edmEntityIdByMetaId;
    }

    public void setEdmEntityIdByMetaId(EDMEdmEntityId edmEntityIdByMetaId) {
        this.edmEntityIdByMetaId = edmEntityIdByMetaId;
    }
}
