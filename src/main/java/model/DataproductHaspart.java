package model;

import jakarta.persistence.*;

@Entity
@Table(name = "dataproduct_haspart", schema = "public", catalog = "cerif")
@IdClass(DataproductHaspartPK.class)
public class DataproductHaspart {
    @Id
    @Column(name = "dataproduct1_instance_id", nullable = false, length = 100)
    private String dataproduct1InstanceId;
    @Id
    @Column(name = "dataproduct2_instance_id", nullable = false, length = 100)
    private String dataproduct2InstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "dataproduct1_instance_id", referencedColumnName = "instance_id")
    private Dataproduct dataproductByDataproduct1InstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "dataproduct2_instance_id", referencedColumnName = "instance_id")
    private Dataproduct dataproductByDataproduct2InstanceId;

    public String getDataproduct1InstanceId() {
        return dataproduct1InstanceId;
    }

    public void setDataproduct1InstanceId(String dataproduct1InstanceId) {
        this.dataproduct1InstanceId = dataproduct1InstanceId;
    }

    public String getDataproduct2InstanceId() {
        return dataproduct2InstanceId;
    }

    public void setDataproduct2InstanceId(String dataproduct2InstanceId) {
        this.dataproduct2InstanceId = dataproduct2InstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataproductHaspart that = (DataproductHaspart) o;

        if (dataproduct1InstanceId != null ? !dataproduct1InstanceId.equals(that.dataproduct1InstanceId) : that.dataproduct1InstanceId != null)
            return false;
        if (dataproduct2InstanceId != null ? !dataproduct2InstanceId.equals(that.dataproduct2InstanceId) : that.dataproduct2InstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dataproduct1InstanceId != null ? dataproduct1InstanceId.hashCode() : 0;
        result = 31 * result + (dataproduct2InstanceId != null ? dataproduct2InstanceId.hashCode() : 0);
        return result;
    }

    public Dataproduct getDataproductByDataproduct1InstanceId() {
        return dataproductByDataproduct1InstanceId;
    }

    public void setDataproductByDataproduct1InstanceId(Dataproduct dataproductByDataproduct1InstanceId) {
        this.dataproductByDataproduct1InstanceId = dataproductByDataproduct1InstanceId;
    }

    public Dataproduct getDataproductByDataproduct2InstanceId() {
        return dataproductByDataproduct2InstanceId;
    }

    public void setDataproductByDataproduct2InstanceId(Dataproduct dataproductByDataproduct2InstanceId) {
        this.dataproductByDataproduct2InstanceId = dataproductByDataproduct2InstanceId;
    }
}
