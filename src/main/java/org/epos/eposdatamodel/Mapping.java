package org.epos.eposdatamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class allows to map a variable used in the template to a property and may optionally specify whether that
 * variable is required or not. The syntax of the template literal is specified by its datatype and defaults to the
 * [RFC6570] URI Template syntax, which can be explicitly indicated by hydra:Rfc6570Template.
 */
public class Mapping {

    /**
     * This property contains the default value of the parameter.
     **/
    private String defaultValue;

    /**
     * This property contains a short string used to describe the meaning of the parameter.
     **/
    private String label;

    /**
     * This property contains the maximum value of the parameter.
     **/
    private String maxValue;

    /**
     * This property contains the minimum value of the parameter.
     **/
    private String minValue;

    /**
     * This property contains one of the possible values which should be used in the web service query. It could be repeated as many times as needed.
     **/
    private List<String> paramValue;

    /**
     * This property contains the vocabulary term which indicates the semantic description of parameter.
     **/
    private String property;

    /**
     * This property contains the type of parameter
     **/
    private String range;

    /**
     * This property contains true if the property is required, false otherwise.
     **/
    private String required;

    /**
     * This property contains the regular expression for testing values according to the parameters specification.
     **/
    private String valuePattern;

    /**
     * This property contains the name of the parameter as required by web service specifications.
     **/
    private String variable;

    /**
     * This property is a boolean property that describe if the mapping has multiple values.
     */
    private String multipleValues;

    /**
     * This property is a boolean property which if is marked as true means that the parameters need to be set but
     * it isn't showed on the gui and the user cannot interact with it
     */
    private String readOnlyValue;


    public void addParamValue(String paramValue) {
        if (this.getParamValue() == null) {
            ArrayList<String> paramValueList = new ArrayList<>();
            paramValueList.add(paramValue);
            this.setParamValue(paramValueList);
        } else {
            this.getParamValue().add(paramValue);
        }
    }


    public Mapping defaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    /**
     * This property contains the default value of the parameter.
     *
     * @return defaultValue
     **/

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Mapping label(String label) {
        this.label = label;
        return this;
    }

    /**
     * This property contains a short string used to describe the meaning of the parameter.
     *
     * @return label
     **/

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Mapping maxValue(String maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    /**
     * This property contains the maximum value of the parameter.
     *
     * @return maxValue
     **/

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public Mapping minValue(String minValue) {
        this.minValue = minValue;
        return this;
    }

    /**
     * This property contains the minimum value of the parameter.
     *
     * @return minValue
     **/

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public Mapping paramValue(List<String> paramValue) {
        this.paramValue = paramValue;
        return this;
    }

    public Mapping addParamValueItem(String paramValueItem) {
        if (this.paramValue == null) {
            this.paramValue = new ArrayList<>();
        }
        this.paramValue.add(paramValueItem);
        return this;
    }

    /**
     * This property contains one of the possible values which should be used in the web service query. It could be repeated as many times as needed.
     *
     * @return paramValue
     **/

    public List<String> getParamValue() {
        return paramValue;
    }

    public void setParamValue(List<String> paramValue) {
        this.paramValue = paramValue;
    }

    public Mapping property(String property) {
        this.property = property;
        return this;
    }

    /**
     * This property contains the vocabulary term which indicates the semantic description of parameter.
     *
     * @return property
     **/

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Mapping range(String range) {
        this.range = range;
        return this;
    }

    /**
     * This property contains the type of parameter
     *
     * @return range
     **/

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Mapping required(String required) {
        this.required = required;
        return this;
    }

    /**
     * This property contains true if the property is required, false otherwise.
     *
     * @return required
     **/

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public Mapping valuePattern(String valuePattern) {
        this.valuePattern = valuePattern;
        return this;
    }

    /**
     * This property contains the regular expression for testing values according to the parameters specification.
     *
     * @return valuePattern
     **/

    public String getValuePattern() {
        return valuePattern;
    }

    public void setValuePattern(String valuePattern) {
        this.valuePattern = valuePattern;
    }

    public Mapping variable(String variable) {
        this.variable = variable;
        return this;
    }

    /**
     * This property contains the name of the parameter as required by web service specifications.
     *
     * @return variable
     **/

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getMultipleValues() {
        return multipleValues;
    }

    public void setMultipleValues(String multipleValues) {
        this.multipleValues = multipleValues;
    }

    public String getReadOnlyValue() {
        return readOnlyValue;
    }

    public void setReadOnlyValue(String readOnlyValue) {
        this.readOnlyValue = readOnlyValue;
    }

    @Override
    public String toString() {
        return "Mapping{" +
                "defaultValue='" + defaultValue + '\'' +
                ", label='" + label + '\'' +
                ", maxValue='" + maxValue + '\'' +
                ", minValue='" + minValue + '\'' +
                ", paramValue=" + paramValue +
                ", property='" + property + '\'' +
                ", range='" + range + '\'' +
                ", required='" + required + '\'' +
                ", valuePattern='" + valuePattern + '\'' +
                ", variable='" + variable + '\'' +
                ", multipleValues='" + multipleValues + '\'' +
                ", readOnlyValue='" + readOnlyValue + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mapping mapping = (Mapping) o;
        return Objects.equals(getDefaultValue(), mapping.getDefaultValue()) && Objects.equals(getLabel(), mapping.getLabel()) && Objects.equals(getMaxValue(), mapping.getMaxValue()) && Objects.equals(getMinValue(), mapping.getMinValue()) && Objects.equals(getParamValue(), mapping.getParamValue()) && Objects.equals(getProperty(), mapping.getProperty()) && Objects.equals(getRange(), mapping.getRange()) && Objects.equals(getRequired(), mapping.getRequired()) && Objects.equals(getValuePattern(), mapping.getValuePattern()) && Objects.equals(getVariable(), mapping.getVariable()) && Objects.equals(getMultipleValues(), mapping.getMultipleValues()) && Objects.equals(getReadOnlyValue(), mapping.getReadOnlyValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDefaultValue(), getLabel(), getMaxValue(), getMinValue(), getParamValue(), getProperty(), getRange(), getRequired(), getValuePattern(), getVariable(), getMultipleValues(), getReadOnlyValue());
    }
}
