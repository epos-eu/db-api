package model;

import jakarta.persistence.*;

@Entity
@Table(name = "mapping_element", schema = "public", catalog = "cerif")
@IdClass(MappingElementPK.class)
public class MappingElement {
    @Id
    @Column(name = "mapping_instance_id", nullable = false, length = 100)
    private String mappingInstanceId;
    @Id
    @Column(name = "element_instance_id", nullable = false, length = 100)
    private String elementInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "mapping_instance_id", referencedColumnName = "instance_id")
    private Mapping mappingByMappingInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "element_instance_id", referencedColumnName = "instance_id")
    private Element elementByElementInstanceId;

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

        MappingElement that = (MappingElement) o;

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

    public Mapping getMappingByMappingInstanceId() {
        return mappingByMappingInstanceId;
    }

    public void setMappingByMappingInstanceId(Mapping mappingByMappingInstanceId) {
        this.mappingByMappingInstanceId = mappingByMappingInstanceId;
    }

    public Element getElementByElementInstanceId() {
        return elementByElementInstanceId;
    }

    public void setElementByElementInstanceId(Element elementByElementInstanceId) {
        this.elementByElementInstanceId = elementByElementInstanceId;
    }
}
