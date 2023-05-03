package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "mapping_paramvalue")
public class EDMMappingParamvalue {
    private String id;
    private String instanceMappingId;
    private String paramvalue;
    private EDMMapping mappingByInstanceMappingId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "instance_mapping_id", insertable = false, updatable = false)
    public String getInstanceMappingId() {
        return instanceMappingId;
    }

    public void setInstanceMappingId(String instanceMappingId) {
        this.instanceMappingId = instanceMappingId;
    }

    @Basic
    @Column(name = "paramvalue")
    public String getParamvalue() {
        return paramvalue;
    }

    public void setParamvalue(String paramvalue) {
        this.paramvalue = paramvalue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMMappingParamvalue that = (EDMMappingParamvalue) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (instanceMappingId != null ? !instanceMappingId.equals(that.instanceMappingId) : that.instanceMappingId != null)
            return false;
        return paramvalue != null ? paramvalue.equals(that.paramvalue) : that.paramvalue == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (instanceMappingId != null ? instanceMappingId.hashCode() : 0);
        result = 31 * result + (paramvalue != null ? paramvalue.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_mapping_id", referencedColumnName = "id", nullable = false)
    public EDMMapping getMappingByInstanceMappingId() {
        return mappingByInstanceMappingId;
    }

    public void setMappingByInstanceMappingId(EDMMapping mappingByInstanceMappingId) {
        this.mappingByInstanceMappingId = mappingByInstanceMappingId;
    }
}
