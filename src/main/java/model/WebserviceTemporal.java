package model;

import jakarta.persistence.*;

@Entity
@Table(name = "webservice_temporal", schema = "public", catalog = "cerif")
@IdClass(WebserviceTemporalPK.class)
public class WebserviceTemporal {
    @Id
    @Column(name = "webservice_instance_id", nullable = false, length = 100)
    private String webserviceInstanceId;
    @Id
    @Column(name = "temporal_instance_id", nullable = false, length = 100)
    private String temporalInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "webservice_instance_id", referencedColumnName = "instance_id")
    private Webservice webserviceByWebserviceInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "temporal_instance_id", referencedColumnName = "instance_id")
    private Temporal temporalByTemporalInstanceId;

    public String getWebserviceInstanceId() {
        return webserviceInstanceId;
    }

    public void setWebserviceInstanceId(String webserviceInstanceId) {
        this.webserviceInstanceId = webserviceInstanceId;
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

        WebserviceTemporal that = (WebserviceTemporal) o;

        if (webserviceInstanceId != null ? !webserviceInstanceId.equals(that.webserviceInstanceId) : that.webserviceInstanceId != null)
            return false;
        if (temporalInstanceId != null ? !temporalInstanceId.equals(that.temporalInstanceId) : that.temporalInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = webserviceInstanceId != null ? webserviceInstanceId.hashCode() : 0;
        result = 31 * result + (temporalInstanceId != null ? temporalInstanceId.hashCode() : 0);
        return result;
    }

    public Webservice getWebserviceByWebserviceInstanceId() {
        return webserviceByWebserviceInstanceId;
    }

    public void setWebserviceByWebserviceInstanceId(Webservice webserviceByWebserviceInstanceId) {
        this.webserviceByWebserviceInstanceId = webserviceByWebserviceInstanceId;
    }

    public Temporal getTemporalByTemporalInstanceId() {
        return temporalByTemporalInstanceId;
    }

    public void setTemporalByTemporalInstanceId(Temporal temporalByTemporalInstanceId) {
        this.temporalByTemporalInstanceId = temporalByTemporalInstanceId;
    }
}
