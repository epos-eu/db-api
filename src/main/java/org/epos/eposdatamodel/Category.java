package org.epos.eposdatamodel;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A subject of a Class.
 */
public class Category extends EPOSDataModelEntity {
    /**
     * This property contains a description of the category
     */
    @Schema(name = "Description", example = "A sample description of a category", required = false)
    private String description;

    /**
     * Relates a resource of type CATEGORYSCHEME
     */
    @Schema (name = "Category Scheme", example = "Relation to a LinkedEntity of type CATEGORYSCHEME", required = false)
    private LinkedEntity inScheme;

    /**
     * This property contains a preferred label of the category
     */
    @Schema (name = "Name", example = "Seismic Waveform", required = false)
    private String name;
    
    /**
     * This property contains a name of the category scheme.
     */
    @Schema (name = "UID", example = "Category/0012302013", required = false)
    private String uid;
    
    /**
     * Relates a list of resources of type CATEGORY
     */
    @Schema (name = "Category broaders", example = "Relation to a LinkedEntity of type CATEGORY", required = false)
    private List<LinkedEntity> broader;
    
    /**
     * Relates a list of resources of type CATEGORY
     */
    @Schema (name = "Category narrowers", example = "Relation to a LinkedEntity of type CATEGORY", required = false)
    private List<LinkedEntity> narrower;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LinkedEntity getInScheme() {
        return inScheme;
    }

    public void setInScheme(LinkedEntity inScheme) {
        this.inScheme = inScheme;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<LinkedEntity> getBroader() {
		return broader;
	}

	public void setBroader(List<LinkedEntity> broader) {
		this.broader = broader;
	}

	public List<LinkedEntity> getNarrower() {
		return narrower;
	}

	public void setNarrower(List<LinkedEntity> narrower) {
		this.narrower = narrower;
	}
	
	public void addNarrower(LinkedEntity narrower) {
        if (this.getNarrower() == null) {
            ArrayList<LinkedEntity> narrowers = new ArrayList<>();
            narrowers.add(narrower);
            this.setNarrower(narrowers);
        } else {
            this.getNarrower().add(narrower);
        }
    }
	
	public void addBroader(LinkedEntity broader) {
        if (this.getBroader() == null) {
            ArrayList<LinkedEntity> broaders = new ArrayList<>();
            broaders.add(broader);
            this.setBroader(broaders);
        } else {
            this.getBroader().add(broader);
        }
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(broader, description, inScheme, name, narrower, uid);
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
		Category other = (Category) obj;
		return Objects.equals(broader, other.broader) && Objects.equals(description, other.description)
				&& Objects.equals(inScheme, other.inScheme) && Objects.equals(name, other.name)
				&& Objects.equals(narrower, other.narrower) && Objects.equals(uid, other.uid);
	}

	@Override
	public String toString() {
		return "Category [description=" + description + ", inScheme=" + inScheme + ", name=" + name + ", uid=" + uid
				+ ", broader=" + broader + ", narrower=" + narrower + "]";
	}
	
}