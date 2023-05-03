package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "softwaresourcecode_programminglanguage")
public class EDMSoftwaresourcecodeProgramminglanguage {
    private String id;
    private String language;
    private String instanceSoftwaresourcecodeId;
    private EDMSoftwaresourcecode softwaresourcecodeByInstanceSoftwaresourcecodeId;

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
    @Column(name = "instance_softwaresourcecode_id", insertable = false, updatable = false)
    public String getInstanceSoftwaresourcecodeId() {
        return instanceSoftwaresourcecodeId;
    }

    public void setInstanceSoftwaresourcecodeId(String instanceSoftwaresourcecodeId) {
        this.instanceSoftwaresourcecodeId = instanceSoftwaresourcecodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMSoftwaresourcecodeProgramminglanguage that = (EDMSoftwaresourcecodeProgramminglanguage) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;
        return instanceSoftwaresourcecodeId != null ? instanceSoftwaresourcecodeId.equals(that.instanceSoftwaresourcecodeId) : that.instanceSoftwaresourcecodeId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (instanceSoftwaresourcecodeId != null ? instanceSoftwaresourcecodeId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_softwaresourcecode_id", referencedColumnName = "instance_id", nullable = false)
    public EDMSoftwaresourcecode getSoftwaresourcecodeByInstanceSoftwaresourcecodeId() {
        return softwaresourcecodeByInstanceSoftwaresourcecodeId;
    }

    public void setSoftwaresourcecodeByInstanceSoftwaresourcecodeId(EDMSoftwaresourcecode softwaresourcecodeByInstanceSoftwaresourcecodeId) {
        this.softwaresourcecodeByInstanceSoftwaresourcecodeId = softwaresourcecodeByInstanceSoftwaresourcecodeId;
    }
}
