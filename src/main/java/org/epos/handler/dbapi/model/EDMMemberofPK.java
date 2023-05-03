package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMMemberofPK implements Serializable {
    private String instanceOrganization1Id;
    private String instanceOrganization2Id;

    public String getInstanceOrganization1Id() {
        return instanceOrganization1Id;
    }

    public void setInstanceOrganization1Id(String instanceOrganization1Id) {
        this.instanceOrganization1Id = instanceOrganization1Id;
    }

    public String getInstanceOrganization2Id() {
        return instanceOrganization2Id;
    }

    public void setInstanceOrganization2Id(String instanceOrganization2Id) {
        this.instanceOrganization2Id = instanceOrganization2Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMMemberofPK that = (EDMMemberofPK) o;

        if (instanceOrganization1Id != null ? !instanceOrganization1Id.equals(that.instanceOrganization1Id) : that.instanceOrganization1Id != null)
            return false;
        return instanceOrganization2Id != null ? instanceOrganization2Id.equals(that.instanceOrganization2Id) : that.instanceOrganization2Id == null;
    }

    @Override
    public int hashCode() {
        int result = instanceOrganization1Id != null ? instanceOrganization1Id.hashCode() : 0;
        result = 31 * result + (instanceOrganization2Id != null ? instanceOrganization2Id.hashCode() : 0);
        return result;
    }
}
