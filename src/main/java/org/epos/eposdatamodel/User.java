package org.epos.eposdatamodel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private String authIdentifier;
    private String lastName;
    private String firstName;
    private String email;

    private Boolean isAdmin;

    private List<UserGroup> groups;

    public User(String authIdentifier, String familyname, String firstName, String email, Boolean isAdmin) {
        this.authIdentifier = authIdentifier;
        this.lastName = familyname;
        this.firstName = firstName;
        this.email = email;
        this.isAdmin = isAdmin;
        this.groups = new ArrayList<>();
    }

    public String getAuthIdentifier() {
        return authIdentifier;
    }

    public void setAuthIdentifier(String authIdentifier) {
        this.authIdentifier = authIdentifier;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<UserGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<UserGroup> groups) { this.groups = groups; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(authIdentifier, user.authIdentifier) && Objects.equals(lastName, user.lastName) && Objects.equals(firstName, user.firstName) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authIdentifier, lastName, firstName, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "authIdentifier='" + authIdentifier + '\'' +
                ", familyname='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
