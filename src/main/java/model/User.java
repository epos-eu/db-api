package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class User {
    @Id
    @Column(name = "auth_identifier", nullable = false, length = 1024)
    private String authIdentifier;
    @Basic
    @Column(name = "familyname", nullable = true, length = 1024)
    private String familyname;
    @Basic
    @Column(name = "givenname", nullable = true, length = 1024)
    private String givenname;
    @Basic
    @Column(name = "email", nullable = true, length = 1024)
    private String email;
    @OneToMany(mappedBy = "userByAuthIdentifier")
    private Collection<MetadataGroupUser> metadataGroupUsersByAuthIdentifier;

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

        if (authIdentifier != null ? !authIdentifier.equals(user.authIdentifier) : user.authIdentifier != null)
            return false;
        if (familyname != null ? !familyname.equals(user.familyname) : user.familyname != null) return false;
        if (givenname != null ? !givenname.equals(user.givenname) : user.givenname != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authIdentifier != null ? authIdentifier.hashCode() : 0;
        result = 31 * result + (familyname != null ? familyname.hashCode() : 0);
        result = 31 * result + (givenname != null ? givenname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public Collection<MetadataGroupUser> getMetadataGroupUsersByAuthIdentifier() {
        return metadataGroupUsersByAuthIdentifier;
    }

    public void setMetadataGroupUsersByAuthIdentifier(Collection<MetadataGroupUser> metadataGroupUsersByAuthIdentifier) {
        this.metadataGroupUsersByAuthIdentifier = metadataGroupUsersByAuthIdentifier;
    }
}
