package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class WebserviceTemporalPK implements Serializable {
    @Column(name = "webservice_instance_id", nullable = false, length = 100)
    @Id
    private String webserviceInstanceId;
    @Column(name = "temporal_instance_id", nullable = false, length = 100)
    @Id
    private String temporalInstanceId;

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

        WebserviceTemporalPK that = (WebserviceTemporalPK) o;

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
}
