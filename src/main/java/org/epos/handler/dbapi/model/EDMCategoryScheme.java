package org.epos.handler.dbapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "category_scheme")
@NamedQueries({
        @NamedQuery(name = "EDMCategoryScheme.findAll", query = "SELECT c FROM EDMCategoryScheme c"),
        @NamedQuery(name = "EDMCategoryScheme.findByName", query = "select c from EDMCategoryScheme c where c.name = :NAME"),
        @NamedQuery(name = "EDMCategoryScheme.findByUid", query = "select c from EDMCategoryScheme c where c.uid = :UID")
})
public class EDMCategoryScheme {
    private String id;
    private String uid;
    private String description;
    private String name;
    private String code;


    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @Basic
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EDMCategoryScheme that = (EDMCategoryScheme) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, name);
    }
}
