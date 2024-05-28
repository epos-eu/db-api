package model;

import jakarta.persistence.*;

@Entity
@Table(name = "organization_element", schema = "public", catalog = "cerif")
@IdClass(OrganizationElementPK.class)
public class OrganizationElement {
    @Id
    @Column(name = "organization_instance_id", nullable = false, length = 100)
    private String organizationInstanceId;
    @Id
    @Column(name = "element_instance_id", nullable = false, length = 100)
    private String elementInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "organization_instance_id", referencedColumnName = "instance_id")
    private Organization organizationByOrganizationInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "element_instance_id", referencedColumnName = "instance_id")
    private Element elementByElementInstanceId;

    public String getOrganizationInstanceId() {
        return organizationInstanceId;
    }

    public void setOrganizationInstanceId(String organizationInstanceId) {
        this.organizationInstanceId = organizationInstanceId;
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

        OrganizationElement that = (OrganizationElement) o;

        if (organizationInstanceId != null ? !organizationInstanceId.equals(that.organizationInstanceId) : that.organizationInstanceId != null)
            return false;
        if (elementInstanceId != null ? !elementInstanceId.equals(that.elementInstanceId) : that.elementInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = organizationInstanceId != null ? organizationInstanceId.hashCode() : 0;
        result = 31 * result + (elementInstanceId != null ? elementInstanceId.hashCode() : 0);
        return result;
    }

    public Organization getOrganizationByOrganizationInstanceId() {
        return organizationByOrganizationInstanceId;
    }

    public void setOrganizationByOrganizationInstanceId(Organization organizationByOrganizationInstanceId) {
        this.organizationByOrganizationInstanceId = organizationByOrganizationInstanceId;
    }

    public Element getElementByElementInstanceId() {
        return elementByElementInstanceId;
    }

    public void setElementByElementInstanceId(Element elementByElementInstanceId) {
        this.elementByElementInstanceId = elementByElementInstanceId;
    }
}
