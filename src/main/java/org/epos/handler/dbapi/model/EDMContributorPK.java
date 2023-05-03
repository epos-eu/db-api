package org.epos.handler.dbapi.model;

import java.io.Serializable;
import java.util.Objects;

public class EDMContributorPK implements Serializable {
    private String instancePublicationId;
    private String metaPersonId;

    public String getInstancePublicationId() {
        return instancePublicationId;
    }

    public void setInstancePublicationId(String instancePublicationId) {
        this.instancePublicationId = instancePublicationId;
    }

    public String getMetaPersonId() {
        return metaPersonId;
    }

    public void setMetaPersonId(String metaPersonId) {
        this.metaPersonId = metaPersonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EDMContributorPK that = (EDMContributorPK) o;
        return Objects.equals(getInstancePublicationId(), that.getInstancePublicationId()) && Objects.equals(getMetaPersonId(), that.getMetaPersonId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInstancePublicationId(), getMetaPersonId());
    }
}
