package org.epos.eposdatamodel;

import java.util.Objects;


/**
 * Object to link different entity to each other and be transparent to the versioning and approval process.
 */
public class LinkedEntity {

    /**
     * The instanceId of the related instance, it can be used to precisely refer to the ote instance.
     */
    private String instanceId;

    /**
     * The uid of the related instance.
     */
    private String uid;

    /**
     * The entity type of the related instance (e.g. DataProduct, Equipment...)
     */
    private String entityType;

    /**
     * The metaId of the related instance
     */
    private String metaId;


    @Override
    public String toString() {
        return "LinkedEntity{" +
                "instanceId='" + instanceId + '\'' +
                ", uid='" + uid + '\'' +
                ", EntityType='" + entityType + '\'' +
                ", metaId='" + metaId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedEntity that = (LinkedEntity) o;
        return Objects.equals(getInstanceId(), that.getInstanceId()) && Objects.equals(getUid(), that.getUid()) && Objects.equals(getEntityType(), that.getEntityType()) && Objects.equals(getMetaId(), that.getMetaId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInstanceId(), getUid(), getEntityType(), getMetaId());
    }

    public LinkedEntity instanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    public LinkedEntity uid(String uid) {
        this.uid = uid;
        return this;
    }

    public LinkedEntity entityType(String entityType) {
    	this.entityType = entityType;
        return this;
    }

    public LinkedEntity metaId(String metaId) {
        this.metaId = metaId;
        return this;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getMetaId() {
        return metaId;
    }

    public void setMetaId(String metaId) {
        this.metaId = metaId;
    }
}
