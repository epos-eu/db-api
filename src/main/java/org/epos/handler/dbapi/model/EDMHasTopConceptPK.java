package org.epos.handler.dbapi.model;

import java.io.Serializable;

public class EDMHasTopConceptPK implements Serializable {
    private String categoryschemeId;
    private String categoryId;

    public String getCategoryschemeId() {
		return categoryschemeId;
	}

	public void setCategoryschemeId(String categoryschemeId) {
		this.categoryschemeId = categoryschemeId;
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

        if (categoryschemeId != null ? !categoryschemeId.equals(that.categoryschemeId) : that.categoryschemeId != null) return false;
        return categoryId != null ? categoryId.equals(that.categoryId) : that.categoryId == null;
    }

    @Override
    public int hashCode() {
        int result = categoryschemeId != null ? categoryschemeId.hashCode() : 0;
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        return result;
    }
}
