package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contactpoint_language")
public class EDMContactpointLanguage {
    private String id;
    private String language;
    private String instanceContactpointId;
    private EDMContactpoint contactpointByInstanceContactpointId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "instance_contactpoint_id", insertable = false, updatable = false)
    public String getInstanceContactpointId() {
        return instanceContactpointId;
    }

    public void setInstanceContactpointId(String instanceContactpointId) {
        this.instanceContactpointId = instanceContactpointId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EDMContactpointLanguage that = (EDMContactpointLanguage) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getLanguage(), that.getLanguage()) && Objects.equals(getInstanceContactpointId(), that.getInstanceContactpointId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLanguage(), getInstanceContactpointId());
    }

    @ManyToOne
    @JoinColumn(name = "instance_contactpoint_id", referencedColumnName = "instance_id", nullable = false)
    public EDMContactpoint getContactpointByInstanceContactpointId() {
        return contactpointByInstanceContactpointId;
    }

    public void setContactpointByInstanceContactpointId(EDMContactpoint contactpointByInstanceContactpointId) {
        this.contactpointByInstanceContactpointId = contactpointByInstanceContactpointId;
    }
}
