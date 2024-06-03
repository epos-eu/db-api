package model;

import jakarta.persistence.*;

@Entity
@Table(name = "softwaresourcecode_identifier", schema = "public", catalog = "cerif")
@IdClass(SoftwaresourcecodeIdentifierPK.class)
public class SoftwaresourcecodeIdentifier {
    @Id
    @Column(name = "softwaresourcecode_instance_id", nullable = false, length = 100)
    private String softwaresourcecodeInstanceId;
    @Id
    @Column(name = "identifier_instance_id", nullable = false, length = 100)
    private String identifierInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "softwaresourcecode_instance_id", referencedColumnName = "instance_id")
    private SoftwareSourceCode softwaresourcecodeBySoftwareSourceCodeInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "identifier_instance_id", referencedColumnName = "instance_id")
    private Identifier identifierByIdentifierInstanceId;

    public String getSoftwaresourcecodeInstanceId() {
        return softwaresourcecodeInstanceId;
    }

    public void setSoftwaresourcecodeInstanceId(String softwaresourcecodeInstanceId) {
        this.softwaresourcecodeInstanceId = softwaresourcecodeInstanceId;
    }

    public String getIdentifierInstanceId() {
        return identifierInstanceId;
    }

    public void setIdentifierInstanceId(String identifierInstanceId) {
        this.identifierInstanceId = identifierInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SoftwaresourcecodeIdentifier that = (SoftwaresourcecodeIdentifier) o;

        if (softwaresourcecodeInstanceId != null ? !softwaresourcecodeInstanceId.equals(that.softwaresourcecodeInstanceId) : that.softwaresourcecodeInstanceId != null)
            return false;
        if (identifierInstanceId != null ? !identifierInstanceId.equals(that.identifierInstanceId) : that.identifierInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = softwaresourcecodeInstanceId != null ? softwaresourcecodeInstanceId.hashCode() : 0;
        result = 31 * result + (identifierInstanceId != null ? identifierInstanceId.hashCode() : 0);
        return result;
    }

    public SoftwareSourceCode getSoftwaresourcecodeBySoftwaresourcecodeInstanceId() {
        return softwaresourcecodeBySoftwareSourceCodeInstanceId;
    }

    public void setSoftwaresourcecodeBySoftwaresourcecodeInstanceId(SoftwareSourceCode softwaresourcecodeBySoftwareSourceCodeInstanceId) {
        this.softwaresourcecodeBySoftwareSourceCodeInstanceId = softwaresourcecodeBySoftwareSourceCodeInstanceId;
    }

    public Identifier getIdentifierByIdentifierInstanceId() {
        return identifierByIdentifierInstanceId;
    }

    public void setIdentifierByIdentifierInstanceId(Identifier identifierByIdentifierInstanceId) {
        this.identifierByIdentifierInstanceId = identifierByIdentifierInstanceId;
    }
}
