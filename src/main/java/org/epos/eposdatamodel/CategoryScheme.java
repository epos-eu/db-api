package org.epos.eposdatamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This property contains a name of the category scheme. May be repeated for different versions of the name.
 */
public class CategoryScheme extends EPOSDataModelEntity {

    /**
     * This property contains a description of the category scheme.
     */
    private String description;

    /**
     * This property contains a name of the category scheme.
     */
    private String title;
    
    /**
     * This property contains a name of the category scheme.
     */
    private String code;
    

    private String logo;
    
    private String homepage;
    
    private String color;
    
    private String orderitemnumber;
    
    
    /**
     * This property contains a name of the category scheme.
     */
    private List<String> topconcepts;
    
    
    /**
     * This property contains a name of the category scheme.
     */
    private String uid;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
   

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getOrderitemnumber() {
		return orderitemnumber;
	}

	public void setOrderitemnumber(String orderitemnumber) {
		this.orderitemnumber = orderitemnumber;
	}

	public List<String> getTopConcepts() {
		return topconcepts;
	}

	public void setTopConcepts(List<String> topconcepts) {
		this.topconcepts = topconcepts;
	}
	
	public void addTopConcepts(String topconcepts) {
        if (this.getTopConcepts() == null) {
            ArrayList<String> broaders = new ArrayList<>();
            broaders.add(topconcepts);
            this.setTopConcepts(broaders);
        } else {
            this.getTopConcepts().add(topconcepts);
        }
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(code, color, description, homepage, logo, orderitemnumber, title, topconcepts, uid);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryScheme other = (CategoryScheme) obj;
		return Objects.equals(code, other.code) && Objects.equals(color, other.color)
				&& Objects.equals(description, other.description) && Objects.equals(homepage, other.homepage)
				&& Objects.equals(logo, other.logo) && Objects.equals(orderitemnumber, other.orderitemnumber)
				&& Objects.equals(title, other.title) && Objects.equals(topconcepts, other.topconcepts)
				&& Objects.equals(uid, other.uid);
	}

	@Override
	public String toString() {
		return "CategoryScheme [description=" + description + ", title=" + title + ", code=" + code + ", logo=" + logo
				+ ", homepage=" + homepage + ", color=" + color + ", orderitemnumber=" + orderitemnumber
				+ ", topconcepts=" + topconcepts + ", uid=" + uid + "]";
	}
	
}
