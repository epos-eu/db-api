package org.epos.handler.dbapi.model;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "mapping")
public class EDMMapping {
    private String id;
    private String label;
    private String variable;
    private Boolean required;
    private String range;
    private String defaultvalue;
    private String minvalue;
    private String maxvalue;
    private String property;
    private String valuepattern;
    private String ismappingof;
    private String readOnlyValue;
    private String multipleValues;
    private EDMOperation operationByIsmappingof;
    private Collection<EDMMappingParamvalue> mappingParamvaluesById;

    @Id
    @Column(name = "id", nullable = false, length = -1)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Basic
    @Column(name = "variable")
    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    @Basic
    @Column(name = "required", nullable = false)
    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    @Basic
    @Column(name = "range")
    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    @Basic
    @Column(name = "defaultvalue")
    public String getDefaultvalue() {
        return defaultvalue;
    }

    public void setDefaultvalue(String defaultvalue) {
        this.defaultvalue = defaultvalue;
    }

    @Basic
    @Column(name = "minvalue")
    public String getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(String minvalue) {
        this.minvalue = minvalue;
    }

    @Basic
    @Column(name = "maxvalue")
    public String getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(String maxvalue) {
        this.maxvalue = maxvalue;
    }

    @Basic
    @Column(name = "property")
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Basic
    @Column(name = "valuepattern")
    public String getValuepattern() {
        return valuepattern;
    }

    public void setValuepattern(String valuepattern) {
        this.valuepattern = valuepattern;
    }

    @Basic
    @Column(name = "read_only_value")
    public String getReadOnlyValue() {
        return readOnlyValue;
    }

    public void setReadOnlyValue(String readOnlyValue) {
        this.readOnlyValue = readOnlyValue;
    }

    @Basic
    @Column(name = "multiple_values")
    public String getMultipleValues() {
        return multipleValues;
    }

    public void setMultipleValues(String multipleValues) {
        this.multipleValues = multipleValues;
    }

    @Basic
    @Column(name = "ismappingof", insertable = false, updatable = false)
    public String getIsmappingof() {
        return ismappingof;
    }

    public void setIsmappingof(String ismappingof) {
        this.ismappingof = ismappingof;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMMapping that = (EDMMapping) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        if (variable != null ? !variable.equals(that.variable) : that.variable != null) return false;
        if (required != null ? !required.equals(that.required) : that.required != null) return false;
        if (range != null ? !range.equals(that.range) : that.range != null) return false;
        if (defaultvalue != null ? !defaultvalue.equals(that.defaultvalue) : that.defaultvalue != null) return false;
        if (minvalue != null ? !minvalue.equals(that.minvalue) : that.minvalue != null) return false;
        if (maxvalue != null ? !maxvalue.equals(that.maxvalue) : that.maxvalue != null) return false;
        if (property != null ? !property.equals(that.property) : that.property != null) return false;
        if (valuepattern != null ? !valuepattern.equals(that.valuepattern) : that.valuepattern != null) return false;
        return ismappingof != null ? ismappingof.equals(that.ismappingof) : that.ismappingof == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (variable != null ? variable.hashCode() : 0);
        result = 31 * result + (required != null ? required.hashCode() : 0);
        result = 31 * result + (range != null ? range.hashCode() : 0);
        result = 31 * result + (defaultvalue != null ? defaultvalue.hashCode() : 0);
        result = 31 * result + (minvalue != null ? minvalue.hashCode() : 0);
        result = 31 * result + (maxvalue != null ? maxvalue.hashCode() : 0);
        result = 31 * result + (property != null ? property.hashCode() : 0);
        result = 31 * result + (valuepattern != null ? valuepattern.hashCode() : 0);
        result = 31 * result + (ismappingof != null ? ismappingof.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ismappingof", referencedColumnName = "instance_id")
    public EDMOperation getOperationByIsmappingof() {
        return operationByIsmappingof;
    }

    public void setOperationByIsmappingof(EDMOperation operationByIsmappingof) {
        this.operationByIsmappingof = operationByIsmappingof;
    }

    @OneToMany(mappedBy = "mappingByInstanceMappingId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinFetch(JoinFetchType.OUTER)
    public Collection<EDMMappingParamvalue> getMappingParamvaluesById() {
        return mappingParamvaluesById;
    }

    public void setMappingParamvaluesById(Collection<EDMMappingParamvalue> mappingParamvaluesById) {
        this.mappingParamvaluesById = mappingParamvaluesById;
    }
}
