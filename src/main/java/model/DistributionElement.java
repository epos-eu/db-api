package model;

import jakarta.persistence.*;

@Entity
@Table(name = "distribution_element", schema = "public", catalog = "cerif")
@IdClass(DistributionElementPK.class)
public class DistributionElement {
    @Id
    @Column(name = "distribution_instance_id", nullable = false, length = 100)
    private String distributionInstanceId;
    @Id
    @Column(name = "element_instance_id", nullable = false, length = 100)
    private String elementInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "distribution_instance_id", referencedColumnName = "instance_id")
    private Distribution distributionByDistributionInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "element_instance_id", referencedColumnName = "instance_id")
    private Element elementByElementInstanceId;

    public String getDistributionInstanceId() {
        return distributionInstanceId;
    }

    public void setDistributionInstanceId(String distributionInstanceId) {
        this.distributionInstanceId = distributionInstanceId;
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

        DistributionElement that = (DistributionElement) o;

        if (distributionInstanceId != null ? !distributionInstanceId.equals(that.distributionInstanceId) : that.distributionInstanceId != null)
            return false;
        if (elementInstanceId != null ? !elementInstanceId.equals(that.elementInstanceId) : that.elementInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = distributionInstanceId != null ? distributionInstanceId.hashCode() : 0;
        result = 31 * result + (elementInstanceId != null ? elementInstanceId.hashCode() : 0);
        return result;
    }

    public Distribution getDistributionByDistributionInstanceId() {
        return distributionByDistributionInstanceId;
    }

    public void setDistributionByDistributionInstanceId(Distribution distributionByDistributionInstanceId) {
        this.distributionByDistributionInstanceId = distributionByDistributionInstanceId;
    }

    public Element getElementByElementInstanceId() {
        return elementByElementInstanceId;
    }

    public void setElementByElementInstanceId(Element elementByElementInstanceId) {
        this.elementByElementInstanceId = elementByElementInstanceId;
    }
}
