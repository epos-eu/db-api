package org.epos.eposdatamodel;

import java.util.Objects;

/**
 * An identifier in a particular context, consisting of the string that is the identifier; an optional identifier for the identifier
 * scheme; an optional identifier for the version of the identifier scheme; an optional identifier for the agency that
 * manages the identifier scheme.
 */
public class Identifier {

    /**
     * This property refers the identifier scheme referenced by its datatype (e.g., DDSS-ID, DOI, etc.).
     **/
    private String type = null;

    /**
     * This property contains a string that is an identifier in the context of the identifier scheme referenced by its datatype
     **/
    private String identifier = null;

    public Identifier type(String type) {
        this.type = type;
        return this;
    }

    /**
     * This property refers the identifier scheme referenced by its datatype (e.g., DDSS-ID, DOI, etc.).
     *
     * @return type
     **/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Identifier identifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    /**
     * This property contains a string that is an identifier in the context of the identifier scheme referenced by its datatype
     *
     * @return identifier
     **/

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }


    @Override
    public String toString() {
        return "Identifier{" +
                "type='" + type + '\'' +
                ", identifier='" + identifier + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identifier that = (Identifier) o;
        return Objects.equals(getType(), that.getType()) && Objects.equals(getIdentifier(), that.getIdentifier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getIdentifier());
    }
}
