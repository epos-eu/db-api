package org.epos.eposdatamodel;

import java.util.Objects;


/**
 * This class represent a Software either an application or its code.
 *
 * <p>It is used to group the software application and the software source code entities
 */
public class Software extends EPOSDataModelEntity {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public String toString() {
        return "Software{} " + super.toString();
    }


}
