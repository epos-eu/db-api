package model;

import jakarta.persistence.*;

@Entity
public class Ontologies {

    @Id
    @Column(name = "id", nullable = false, length = 100)
    private String id;
    @Basic
    @Column(name = "ontologyname", nullable = true, length = 1024)
    private String ontologyname;
    @Basic
    @Column(name = "ontologybase64", nullable = true, length = -1)
    private String ontologybase64;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOntologyname() {
        return ontologyname;
    }

    public void setOntologyname(String ontologyname) {
        this.ontologyname = ontologyname;
    }

    public String getOntologybase64() {
        return ontologybase64;
    }

    public void setOntologybase64(String ontologybase64) {
        this.ontologybase64 = ontologybase64;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ontologies that = (Ontologies) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ontologyname != null ? !ontologyname.equals(that.ontologyname) : that.ontologyname != null) return false;
        if (ontologybase64 != null ? !ontologybase64.equals(that.ontologybase64) : that.ontologybase64 != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ontologyname != null ? ontologyname.hashCode() : 0);
        result = 31 * result + (ontologybase64 != null ? ontologybase64.hashCode() : 0);
        return result;
    }
}
