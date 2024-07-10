package org.epos.eposdatamodel;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * Physical address of the item.
 */
public class LegalName extends EPOSDataModelEntity {
    /**
     * The language assigned to the legalname
     **/
    @Schema(name = "language", description = "The language assigned to the legalname", example = "en", required = false)
    private String language = null;

    /**
     * The legalname of an organization
     **/
    @Schema(name = "legalname", description = "The legalname of an organization", example = "Institute of examples", required = false)
    private String legalname = null;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLegalname() {
        return legalname;
    }

    public void setLegalname(String legalname) {
        this.legalname = legalname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LegalName legalName = (LegalName) o;
        return Objects.equals(language, legalName.language) && Objects.equals(legalname, legalName.legalname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), language, legalname);
    }

    @Override
    public String toString() {
        return "LegalName{" +
                "language='" + language + '\'' +
                ", legalname='" + legalname + '\'' +
                '}'+ super.toString();
    }
}
