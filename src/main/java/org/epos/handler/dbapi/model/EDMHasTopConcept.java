package org.epos.handler.dbapi.model;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "hastopconcept", schema = "public")
@IdClass(EDMHasTopConceptPK.class)
@NamedQueries({
        @NamedQuery(name = "EDMHasTopConcept.findAll", query = "SELECT c FROM EDMHasTopConcept c")
})
public class EDMHasTopConcept {
    private String categoryschemeId;
    private String categoryId;
    private EDMCategoryScheme categoryByCategorySchemeId;
    private EDMCategory categoryByCategoryId;


    @Id
    @Column(name = "category_id", insertable = false, updatable = false)
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public EDMCategory getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(EDMCategory categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }
    
    @Id
    @Column(name = "category_scheme_id", insertable = false, updatable = false)
	public String getCategoryschemeId() {
		return categoryschemeId;
	}

	public void setCategoryschemeId(String categoryschemeId) {
		this.categoryschemeId = categoryschemeId;
	}

	@ManyToOne
    @JoinColumn(name = "category_scheme_id", referencedColumnName = "id", nullable = false)
	public EDMCategoryScheme getCategoryByCategorySchemeId() {
		return categoryByCategorySchemeId;
	}

	public void setCategoryByCategorySchemeId(EDMCategoryScheme categoryByCategorySchemeId) {
		this.categoryByCategorySchemeId = categoryByCategorySchemeId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryByCategoryId, categoryByCategorySchemeId, categoryId, categoryschemeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EDMHasTopConcept other = (EDMHasTopConcept) obj;
		return Objects.equals(categoryByCategoryId, other.categoryByCategoryId)
				&& Objects.equals(categoryByCategorySchemeId, other.categoryByCategorySchemeId)
				&& Objects.equals(categoryId, other.categoryId)
				&& Objects.equals(categoryschemeId, other.categoryschemeId);
	}

	@Override
	public String toString() {
		return "EDMHasTopConcept [categoryschemeId=" + categoryschemeId + ", categoryId=" + categoryId
				+ ", categoryByCategorySchemeId=" + categoryByCategorySchemeId + ", categoryByCategoryId="
				+ categoryByCategoryId + "]";
	}
    
}
