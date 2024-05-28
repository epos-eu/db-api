package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class MappingElementPK implements Serializable {
    @Column(name = "mapping_instance_id", nullable = false, length = 100)
    @Id
    private String mappingInstanceId;
    @Column(name = "element_instance_id", nullable = false, length = 100)
    @Id
    private String elementInstanceId;

    public String getMappingInstanceId() {
        return mappingInstanceId;
    }

    public void setMappingInstanceId(String mappingInstanceId) {
        this.mappingInstanceId = mappingInstanceId;
    }

    public String getElementInstanceId() {
        return elementInstanceId;
    }

    public void setElementInstanceId(String elementInstanceId) {
        this.elementInstanceId = elementInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MappingElementPK that = (MappingElementPK) o;

        if (mappingInstanceId != null ? !mappingInstanceId.equals(that.mappingInstanceId) : that.mappingInstanceId != null)
            return false;
        if (elementInstanceId != null ? !elementInstanceId.equals(that.elementInstanceId) : that.elementInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mappingInstanceId != null ? mappingInstanceId.hashCode() : 0;
        result = 31 * result + (elementInstanceId != null ? elementInstanceId.hashCode() : 0);
        return result;
    }
}
