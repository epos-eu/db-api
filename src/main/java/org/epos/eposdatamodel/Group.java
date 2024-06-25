package org.epos.eposdatamodel;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import model.AuthorizationGroup;
import model.MetadataGroupUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Group {

    private String id;
    private String name;
    private String description;

    private List<String> users;

    private List<String> entities;


    public Group(){}

    public Group(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.users = new ArrayList<>();
        this.entities = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public List<String> getEntities() {
        return entities;
    }

    public void setEntities(List<String> entities) {
        this.entities = entities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) && Objects.equals(name, group.name) && Objects.equals(description, group.description) && Objects.equals(users, group.users) && Objects.equals(entities, group.entities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, users, entities);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", users=" + users +
                ", entities=" + entities +
                '}';
    }
}
