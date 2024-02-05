package org.epos.handler.dbapi.model;

import javax.persistence.*;

import java.util.Collection;
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
    private String logo;
    private String homepage;
    private String color;
    private String orderitemnumber;
   

    private Collection<EDMHasTopConcept> hasTopConceptCategorySchemeId;

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
    
    @Basic
    @Column(name = "logo")
    public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Basic
	@Column(name = "homepage")
	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	@Basic
    @Column(name = "color")
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Basic
	@Column(name = "orderitemnumber")
	public String getOrderitemnumber() {
		return orderitemnumber;
	}

	public void setOrderitemnumber(String orderitemnumber) {
		this.orderitemnumber = orderitemnumber;
	}
    
    @OneToMany(mappedBy = "categoryByCategorySchemeId", fetch=FetchType.LAZY)
   	public Collection<EDMHasTopConcept> getHasTopConceptCategorySchemeId() {
   		return hasTopConceptCategorySchemeId;
   	}

   	public void setHasTopConceptCategorySchemeId(Collection<EDMHasTopConcept> hasTopConceptCategorySchemeId) {
   		this.hasTopConceptCategorySchemeId = hasTopConceptCategorySchemeId;
   	}
   	
	@Override
	public int hashCode() {
		return Objects.hash(code, color, description, homepage, id, logo, name, orderitemnumber, uid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EDMCategoryScheme other = (EDMCategoryScheme) obj;
		return Objects.equals(code, other.code) && Objects.equals(color, other.color)
				&& Objects.equals(description, other.description) && Objects.equals(homepage, other.homepage)
				&& Objects.equals(id, other.id) && Objects.equals(logo, other.logo) && Objects.equals(name, other.name)
				&& Objects.equals(orderitemnumber, other.orderitemnumber) && Objects.equals(uid, other.uid);
	}

	@Override
	public String toString() {
		return "EDMCategoryScheme [id=" + id + ", uid=" + uid + ", description=" + description + ", name=" + name
				+ ", code=" + code + ", logo=" + logo + ", homepage=" + homepage + ", color=" + color
				+ ", orderitemnumber=" + orderitemnumber + "]";
	}

	
}
