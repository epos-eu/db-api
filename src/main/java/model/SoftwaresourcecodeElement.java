package model;

import jakarta.persistence.*;

@Entity
@Table(name = "softwaresourcecode_element", schema = "public", catalog = "cerif")
@IdClass(SoftwaresourcecodeElementPK.class)
public class SoftwaresourcecodeElement {
    @Id
    @Column(name = "softwaresourcecode_instance_id", nullable = false, length = 100)
    private String softwaresourcecodeInstanceId;
    @Id
    @Column(name = "element_instance_id", nullable = false, length = 100)
    private String elementInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "softwaresourcecode_instance_id", referencedColumnName = "instance_id")
    private Softwaresourcecode softwaresourcecodeBySoftwaresourcecodeInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "element_instance_id", referencedColumnName = "instance_id")
    private Element elementByElementInstanceId;

    public String getSoftwaresourcecodeInstanceId() {
        return softwaresourcecodeInstanceId;
    }

    public void setSoftwaresourcecodeInstanceId(String softwaresourcecodeInstanceId) {
        this.softwaresourcecodeInstanceId = softwaresourcecodeInstanceId;
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

        SoftwaresourcecodeElement that = (SoftwaresourcecodeElement) o;

        if (softwaresourcecodeInstanceId != null ? !softwaresourcecodeInstanceId.equals(that.softwaresourcecodeInstanceId) : that.softwaresourcecodeInstanceId != null)
            return false;
        if (elementInstanceId != null ? !elementInstanceId.equals(that.elementInstanceId) : that.elementInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = softwaresourcecodeInstanceId != null ? softwaresourcecodeInstanceId.hashCode() : 0;
        result = 31 * result + (elementInstanceId != null ? elementInstanceId.hashCode() : 0);
        return result;
    }

    public Softwaresourcecode getSoftwaresourcecodeBySoftwaresourcecodeInstanceId() {
        return softwaresourcecodeBySoftwaresourcecodeInstanceId;
    }

    public void setSoftwaresourcecodeBySoftwaresourcecodeInstanceId(Softwaresourcecode softwaresourcecodeBySoftwaresourcecodeInstanceId) {
        this.softwaresourcecodeBySoftwaresourcecodeInstanceId = softwaresourcecodeBySoftwaresourcecodeInstanceId;
    }

    public Element getElementByElementInstanceId() {
        return elementByElementInstanceId;
    }

    public void setElementByElementInstanceId(Element elementByElementInstanceId) {
        this.elementByElementInstanceId = elementByElementInstanceId;
    }
}
