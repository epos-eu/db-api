package model;

import jakarta.persistence.*;

@Entity
@Table(name = "dataproduct_temporal", schema = "public", catalog = "cerif")
@IdClass(DataproductTemporalPK.class)
public class DataproductTemporal {
    @Id
    @Column(name = "dataproduct_instance_id", nullable = false, length = 100)
    private String dataproductInstanceId;
    @Id
    @Column(name = "temporal_instance_id", nullable = false, length = 100)
    private String temporalInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "dataproduct_instance_id", referencedColumnName = "instance_id")
    private Dataproduct dataproductByDataproductInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "temporal_instance_id", referencedColumnName = "instance_id")
    private Temporal temporalByTemporalInstanceId;

    public String getDataproductInstanceId() {
        return dataproductInstanceId;
    }

    public void setDataproductInstanceId(String dataproductInstanceId) {
        this.dataproductInstanceId = dataproductInstanceId;
    }

    public String getTemporalInstanceId() {
        return temporalInstanceId;
    }

    public void setTemporalInstanceId(String temporalInstanceId) {
        this.temporalInstanceId = temporalInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataproductTemporal that = (DataproductTemporal) o;

        if (dataproductInstanceId != null ? !dataproductInstanceId.equals(that.dataproductInstanceId) : that.dataproductInstanceId != null)
            return false;
        if (temporalInstanceId != null ? !temporalInstanceId.equals(that.temporalInstanceId) : that.temporalInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dataproductInstanceId != null ? dataproductInstanceId.hashCode() : 0;
        result = 31 * result + (temporalInstanceId != null ? temporalInstanceId.hashCode() : 0);
        return result;
    }

    public Dataproduct getDataproductByDataproductInstanceId() {
        return dataproductByDataproductInstanceId;
    }

    public void setDataproductByDataproductInstanceId(Dataproduct dataproductByDataproductInstanceId) {
        this.dataproductByDataproductInstanceId = dataproductByDataproductInstanceId;
    }

    public Temporal getTemporalByTemporalInstanceId() {
        return temporalByTemporalInstanceId;
    }

    public void setTemporalByTemporalInstanceId(Temporal temporalByTemporalInstanceId) {
        this.temporalByTemporalInstanceId = temporalByTemporalInstanceId;
    }
}
