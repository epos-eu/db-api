package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMPersonIdentifierPK implements Serializable {
    private String type;
    private String identifier;
    private String instancePersonId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getInstancePersonId() {
        return instancePersonId;
    }

    public void setInstancePersonId(String instancePersonId) {
        this.instancePersonId = instancePersonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMPersonIdentifierPK that = (EDMPersonIdentifierPK) o;

        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) return false;
        return instancePersonId != null ? instancePersonId.equals(that.instancePersonId) : that.instancePersonId == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
        result = 31 * result + (instancePersonId != null ? instancePersonId.hashCode() : 0);
        return result;
    }
}
