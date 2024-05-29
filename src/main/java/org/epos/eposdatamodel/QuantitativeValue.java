package org.epos.eposdatamodel;

import java.util.Objects;

/**
 * This class allow to represent the implementation status of a Service made available by a Service Provider.
 */
public class QuantitativeValue {
    /**
     * The unit of measurement given using the UN/CEFACT Common Code (3 characters) or a URL
     */
    private String unit;

    /**
     * The unit of measurement given using the UN/CEFACT Common Code (3 characters) or a URL
     */
    private String value;


    public String getUnit() {
        return unit;
    }


    public void setUnit(String unit) {
        this.unit = unit;
    }


    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "QuantitativeValue{" +
                "unit='" + unit + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuantitativeValue that = (QuantitativeValue) o;
        return Objects.equals(getUnit(), that.getUnit()) && Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUnit(), getValue());
    }
}
