package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMHasTopConceptPK implements Serializable {
    private String categorySchemeId;
    private String categoryId;

    public String getCategorySchemeId() {
		return categorySchemeId;
	}

	public void setCategorySchemeId(String categorySchemeId) {
		this.categorySchemeId = categorySchemeId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMHasTopConceptPK that = (EDMHasTopConceptPK) o;

        if (categorySchemeId != null ? !categorySchemeId.equals(that.categorySchemeId) : that.categorySchemeId != null) return false;
        return categoryId != null ? categoryId.equals(that.categoryId) : that.categoryId == null;
    }

    @Override
    public int hashCode() {
        int result = categorySchemeId != null ? categorySchemeId.hashCode() : 0;
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        return result;
    }
}
