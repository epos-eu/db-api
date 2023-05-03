package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMHaspartDataproductPK implements Serializable {
    private String instanceDataproduct1Id;
    private String instanceDataproduct2Id;

    public String getInstanceDataproduct1Id() {
        return instanceDataproduct1Id;
    }

    public void setInstanceDataproduct1Id(String instanceDataproduct1Id) {
        this.instanceDataproduct1Id = instanceDataproduct1Id;
    }

    public String getInstanceDataproduct2Id() {
        return instanceDataproduct2Id;
    }

    public void setInstanceDataproduct2Id(String instanceDataproduct2Id) {
        this.instanceDataproduct2Id = instanceDataproduct2Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMHaspartDataproductPK that = (EDMHaspartDataproductPK) o;

        if (instanceDataproduct1Id != null ? !instanceDataproduct1Id.equals(that.instanceDataproduct1Id) : that.instanceDataproduct1Id != null)
            return false;
        return instanceDataproduct2Id != null ? instanceDataproduct2Id.equals(that.instanceDataproduct2Id) : that.instanceDataproduct2Id == null;
    }

    @Override
    public int hashCode() {
        int result = instanceDataproduct1Id != null ? instanceDataproduct1Id.hashCode() : 0;
        result = 31 * result + (instanceDataproduct2Id != null ? instanceDataproduct2Id.hashCode() : 0);
        return result;
    }
}
