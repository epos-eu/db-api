package model;

import jakarta.persistence.*;

@Entity
@Table(name = "dataproduct_publisher", schema = "public", catalog = "cerif")
@IdClass(DataproductPublisherPK.class)
public class DataproductPublisher {
    @Id
    @Column(name = "dataproduct_instance_id", nullable = false, length = 100)
    private String dataproductInstanceId;
    @Id
    @Column(name = "organization_instance_id", nullable = false, length = 100)
    private String organizationInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "dataproduct_instance_id", referencedColumnName = "instance_id")
    private Dataproduct dataproductByDataproductInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "organization_instance_id", referencedColumnName = "instance_id")
    private Organization organizationByOrganizationInstanceId;

    public String getDataproductInstanceId() {
        return dataproductInstanceId;
    }

    public void setDataproductInstanceId(String dataproductInstanceId) {
        this.dataproductInstanceId = dataproductInstanceId;
    }

    public String getOrganizationInstanceId() {
        return organizationInstanceId;
    }

    public void setOrganizationInstanceId(String organizationInstanceId) {
        this.organizationInstanceId = organizationInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataproductPublisher that = (DataproductPublisher) o;

        if (dataproductInstanceId != null ? !dataproductInstanceId.equals(that.dataproductInstanceId) : that.dataproductInstanceId != null)
            return false;
        if (organizationInstanceId != null ? !organizationInstanceId.equals(that.organizationInstanceId) : that.organizationInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dataproductInstanceId != null ? dataproductInstanceId.hashCode() : 0;
        result = 31 * result + (organizationInstanceId != null ? organizationInstanceId.hashCode() : 0);
        return result;
    }

    public Dataproduct getDataproductByDataproductInstanceId() {
        return dataproductByDataproductInstanceId;
    }

    public void setDataproductByDataproductInstanceId(Dataproduct dataproductByDataproductInstanceId) {
        this.dataproductByDataproductInstanceId = dataproductByDataproductInstanceId;
    }

    public Organization getOrganizationByOrganizationInstanceId() {
        return organizationByOrganizationInstanceId;
    }

    public void setOrganizationByOrganizationInstanceId(Organization organizationByOrganizationInstanceId) {
        this.organizationByOrganizationInstanceId = organizationByOrganizationInstanceId;
    }
}
