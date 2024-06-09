package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class Mapping {
    @Id
    @Column(name = "instance_id", nullable = false, length = 100)
    private String instanceId;
    @Basic
    @Column(name = "meta_id", nullable = true, length = 100)
    private String metaId;
    @Basic
    @Column(name = "uid", nullable = true, length = 100)
    private String uid;
    @Basic
    @Column(name = "version_id", nullable = true, length = 100)
    private String versionId;
    @Basic
    @Column(name = "label", nullable = true, length = 1024)
    private String label;
    @Basic
    @Column(name = "variable", nullable = true, length = 1024)
    private String variable;
    @Basic
    @Column(name = "required", nullable = true)
    private boolean required;
    @Basic
    @Column(name = "range", nullable = true, length = 1024)
    private String range;
    @Basic
    @Column(name = "defaultvalue", nullable = true, length = 1024)
    private String defaultvalue;
    @Basic
    @Column(name = "minvalue", nullable = true, length = 1024)
    private String minvalue;
    @Basic
    @Column(name = "maxvalue", nullable = true, length = 1024)
    private String maxvalue;
    @Basic
    @Column(name = "property", nullable = true, length = 1024)
    private String property;
    @Basic
    @Column(name = "valuepattern", nullable = true, length = 1024)
    private String valuepattern;
    @Basic
    @Column(name = "read_only_value", nullable = true, length = 1024)
    private String readOnlyValue;
    @Basic
    @Column(name = "multiple_values", nullable = true, length = 1024)
    private String multipleValues;
    @Basic
    @Column(name = "ismappingof", nullable = true, length = 100)
    private String ismappingof;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "ismappingof", referencedColumnName = "instance_id")
    private Operation operationByIsmappingof;
    @OneToMany(mappedBy = "mappingByMappingInstanceId")
    private Collection<MappingElement> mappingElementsByInstanceId;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getMetaId() {
        return metaId;
    }

    public void setMetaId(String metaId) {
        this.metaId = metaId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getDefaultvalue() {
        return defaultvalue;
    }

    public void setDefaultvalue(String defaultvalue) {
        this.defaultvalue = defaultvalue;
    }

    public String getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(String minvalue) {
        this.minvalue = minvalue;
    }

    public String getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(String maxvalue) {
        this.maxvalue = maxvalue;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValuepattern() {
        return valuepattern;
    }

    public void setValuepattern(String valuepattern) {
        this.valuepattern = valuepattern;
    }

    public String getReadOnlyValue() {
        return readOnlyValue;
    }

    public void setReadOnlyValue(String readOnlyValue) {
        this.readOnlyValue = readOnlyValue;
    }

    public String getMultipleValues() {
        return multipleValues;
    }

    public void setMultipleValues(String multipleValues) {
        this.multipleValues = multipleValues;
    }

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

        Mapping mapping = (Mapping) o;

        if (required != mapping.required) return false;
        if (instanceId != null ? !instanceId.equals(mapping.instanceId) : mapping.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(mapping.metaId) : mapping.metaId != null) return false;
        if (uid != null ? !uid.equals(mapping.uid) : mapping.uid != null) return false;
        if (versionId != null ? !versionId.equals(mapping.versionId) : mapping.versionId != null) return false;
        if (label != null ? !label.equals(mapping.label) : mapping.label != null) return false;
        if (variable != null ? !variable.equals(mapping.variable) : mapping.variable != null) return false;
        if (range != null ? !range.equals(mapping.range) : mapping.range != null) return false;
        if (defaultvalue != null ? !defaultvalue.equals(mapping.defaultvalue) : mapping.defaultvalue != null)
            return false;
        if (minvalue != null ? !minvalue.equals(mapping.minvalue) : mapping.minvalue != null) return false;
        if (maxvalue != null ? !maxvalue.equals(mapping.maxvalue) : mapping.maxvalue != null) return false;
        if (property != null ? !property.equals(mapping.property) : mapping.property != null) return false;
        if (valuepattern != null ? !valuepattern.equals(mapping.valuepattern) : mapping.valuepattern != null)
            return false;
        if (readOnlyValue != null ? !readOnlyValue.equals(mapping.readOnlyValue) : mapping.readOnlyValue != null)
            return false;
        if (multipleValues != null ? !multipleValues.equals(mapping.multipleValues) : mapping.multipleValues != null)
            return false;
        if (ismappingof != null ? !ismappingof.equals(mapping.ismappingof) : mapping.ismappingof != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (variable != null ? variable.hashCode() : 0);
        result = 31 * result + (required ? 1 : 0);
        result = 31 * result + (range != null ? range.hashCode() : 0);
        result = 31 * result + (defaultvalue != null ? defaultvalue.hashCode() : 0);
        result = 31 * result + (minvalue != null ? minvalue.hashCode() : 0);
        result = 31 * result + (maxvalue != null ? maxvalue.hashCode() : 0);
        result = 31 * result + (property != null ? property.hashCode() : 0);
        result = 31 * result + (valuepattern != null ? valuepattern.hashCode() : 0);
        result = 31 * result + (readOnlyValue != null ? readOnlyValue.hashCode() : 0);
        result = 31 * result + (multipleValues != null ? multipleValues.hashCode() : 0);
        result = 31 * result + (ismappingof != null ? ismappingof.hashCode() : 0);
        return result;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }

    public Operation getOperationByIsmappingof() {
        return operationByIsmappingof;
    }

    public void setOperationByIsmappingof(Operation operationByIsmappingof) {
        this.operationByIsmappingof = operationByIsmappingof;
    }

    public Collection<MappingElement> getMappingElementsByInstanceId() {
        return mappingElementsByInstanceId;
    }

    public void setMappingElementsByInstanceId(Collection<MappingElement> mappingElementsByInstanceId) {
        this.mappingElementsByInstanceId = mappingElementsByInstanceId;
    }
}
