package org.epos.eposdatamodel;
import java.util.Objects;

public class User {

    private String authIdentifier;
    private String familyname;
    private String givenname;
    private String email;

    public User(String authIdentifier, String familyname, String givenname, String email) {
        this.authIdentifier = authIdentifier;
        this.familyname = familyname;
        this.givenname = givenname;
        this.email = email;
    }

    public String getAuthIdentifier() {
        return authIdentifier;
    }

    public void setAuthIdentifier(String authIdentifier) {
        this.authIdentifier = authIdentifier;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getGivenname() {
        return givenname;
    }

    public void setGivenname(String givenname) {
        this.givenname = givenname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(authIdentifier, user.authIdentifier) && Objects.equals(familyname, user.familyname) && Objects.equals(givenname, user.givenname) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authIdentifier, familyname, givenname, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "authIdentifier='" + authIdentifier + '\'' +
                ", familyname='" + familyname + '\'' +
                ", givenname='" + givenname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
