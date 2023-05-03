package org.epos.handler.dbapi.model;

import javax.persistence.*;

@Entity
@Table(name = "memberof")
@IdClass(EDMMemberofPK.class)
public class EDMMemberof {
    private String instanceOrganization1Id;
    private String instanceOrganization2Id;
    private EDMOrganization organizationByInstanceOrganization1Id;
    private EDMOrganization organizationByInstanceOrganization2Id;

    @Id
    @Column(name = "instance_organization1_id", insertable = false, updatable = false)
    public String getInstanceOrganization1Id() {
        return instanceOrganization1Id;
    }

    public void setInstanceOrganization1Id(String instanceOrganization1Id) {
        this.instanceOrganization1Id = instanceOrganization1Id;
    }

    @Id
    @Column(name = "instance_organization2_id", insertable = false, updatable = false)
    public String getInstanceOrganization2Id() {
        return instanceOrganization2Id;
    }

    public void setInstanceOrganization2Id(String metaOrganization2Id) {
        this.instanceOrganization2Id = metaOrganization2Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EDMMemberof memberof = (EDMMemberof) o;

        if (instanceOrganization1Id != null ? !instanceOrganization1Id.equals(memberof.instanceOrganization1Id) : memberof.instanceOrganization1Id != null)
            return false;
        return instanceOrganization2Id != null ? instanceOrganization2Id.equals(memberof.instanceOrganization2Id) : memberof.instanceOrganization2Id == null;
    }

    @Override
    public int hashCode() {
        int result = instanceOrganization1Id != null ? instanceOrganization1Id.hashCode() : 0;
        result = 31 * result + (instanceOrganization2Id != null ? instanceOrganization2Id.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "instance_organization1_id", referencedColumnName = "instance_id", nullable = false)
    public EDMOrganization getOrganizationByInstanceOrganization1Id() {
        return organizationByInstanceOrganization1Id;
    }

    public void setOrganizationByInstanceOrganization1Id(EDMOrganization organizationByInstanceOrganization1Id) {
        this.organizationByInstanceOrganization1Id = organizationByInstanceOrganization1Id;
    }

    @ManyToOne
    @JoinColumn(name = "instance_organization2_id", referencedColumnName = "instance_id", nullable = false)
    public EDMOrganization getOrganizationByInstanceOrganization2Id() {
        return organizationByInstanceOrganization2Id;
    }

    public void setOrganizationByInstanceOrganization2Id(EDMOrganization edmEntityIdByMetaOrganization2Id) {
        this.organizationByInstanceOrganization2Id = edmEntityIdByMetaOrganization2Id;
    }
}
