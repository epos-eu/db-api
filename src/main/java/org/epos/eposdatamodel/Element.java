package org.epos.eposdatamodel;

import model.ElementType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A Contact Point of a Class. it can be refered to phisical person or an organization
 */
public class Element extends EPOSDataModelEntity {

    /**
     * This property refers to the Contact Point role.
     **/
    private ElementType type = null;


    /**
     * This property refers to the related Organization which represents the contactpoint.
     */
    private String value = null;


    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Element element = (Element) o;
        return type == element.type && Objects.equals(value, element.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, value);
    }

    @Override
    public String toString() {
        return "Element{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
