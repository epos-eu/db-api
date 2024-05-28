package model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import java.io.Serializable;

public class CategoryIspartofPK implements Serializable {
    @Column(name = "category1_instance_id", nullable = false, length = 100)
    @Id
    private String category1InstanceId;
    @Column(name = "category2_instance_id", nullable = false, length = 100)
    @Id
    private String category2InstanceId;

    public String getCategory1InstanceId() {
        return category1InstanceId;
    }

    public void setCategory1InstanceId(String category1InstanceId) {
        this.category1InstanceId = category1InstanceId;
    }

    public String getCategory2InstanceId() {
        return category2InstanceId;
    }

    public void setCategory2InstanceId(String category2InstanceId) {
        this.category2InstanceId = category2InstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryIspartofPK that = (CategoryIspartofPK) o;

        if (category1InstanceId != null ? !category1InstanceId.equals(that.category1InstanceId) : that.category1InstanceId != null)
            return false;
        if (category2InstanceId != null ? !category2InstanceId.equals(that.category2InstanceId) : that.category2InstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = category1InstanceId != null ? category1InstanceId.hashCode() : 0;
        result = 31 * result + (category2InstanceId != null ? category2InstanceId.hashCode() : 0);
        return result;
    }
}
