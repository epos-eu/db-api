package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMSoftwaresourcecodeIdentifierPK implements Serializable {
    private String type;
    private String identifier;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMSoftwaresourcecodeIdentifierPK that = (EDMSoftwaresourcecodeIdentifierPK) o;

        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return identifier != null ? identifier.equals(that.identifier) : that.identifier == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
        return result;
    }
}
