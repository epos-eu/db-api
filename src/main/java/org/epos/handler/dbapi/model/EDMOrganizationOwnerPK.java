package org.epos.handler.dbapi.model;

import java.io.Serializable;
import java.util.Objects;

public class EDMOrganizationOwnerPK implements Serializable {
	
    private String instanceOrganizationId;
    private String entityMetaId;
    
	public String getInstanceOrganizationId() {
		return instanceOrganizationId;
	}
	public void setInstanceOrganizationId(String instanceOrganizationId) {
		this.instanceOrganizationId = instanceOrganizationId;
	}
	public String getEntityMetaId() {
		return entityMetaId;
	}
	public void setEntityMetaId(String entityMetaId) {
		this.entityMetaId = entityMetaId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(entityMetaId, instanceOrganizationId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EDMOrganizationOwnerPK other = (EDMOrganizationOwnerPK) obj;
		return Objects.equals(entityMetaId, other.entityMetaId)
				&& Objects.equals(instanceOrganizationId, other.instanceOrganizationId);
	}
	@Override
	public String toString() {
		return "EDMOrganizationOwnerPK [instanceOrganizationId=" + instanceOrganizationId + ", entityMetaId="
				+ entityMetaId + "]";
	}
    
}
