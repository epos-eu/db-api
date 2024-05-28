package model;

import jakarta.persistence.*;

@Entity
@Table(name = "webservice_category", schema = "public", catalog = "cerif")
@IdClass(WebserviceCategoryPK.class)
public class WebserviceCategory {
    @Id
    @Column(name = "category_instance_id", nullable = false, length = 100)
    private String categoryInstanceId;
    @Id
    @Column(name = "webservice_instance_id", nullable = false, length = 100)
    private String webserviceInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "category_instance_id", referencedColumnName = "instance_id")
    private Category categoryByCategoryInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "webservice_instance_id", referencedColumnName = "instance_id")
    private Webservice webserviceByWebserviceInstanceId;

    public String getCategoryInstanceId() {
        return categoryInstanceId;
    }

    public void setCategoryInstanceId(String categoryInstanceId) {
        this.categoryInstanceId = categoryInstanceId;
    }

    public String getWebserviceInstanceId() {
        return webserviceInstanceId;
    }

    public void setWebserviceInstanceId(String webserviceInstanceId) {
        this.webserviceInstanceId = webserviceInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebserviceCategory that = (WebserviceCategory) o;

        if (categoryInstanceId != null ? !categoryInstanceId.equals(that.categoryInstanceId) : that.categoryInstanceId != null)
            return false;
        if (webserviceInstanceId != null ? !webserviceInstanceId.equals(that.webserviceInstanceId) : that.webserviceInstanceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryInstanceId != null ? categoryInstanceId.hashCode() : 0;
        result = 31 * result + (webserviceInstanceId != null ? webserviceInstanceId.hashCode() : 0);
        return result;
    }

    public Category getCategoryByCategoryInstanceId() {
        return categoryByCategoryInstanceId;
    }

    public void setCategoryByCategoryInstanceId(Category categoryByCategoryInstanceId) {
        this.categoryByCategoryInstanceId = categoryByCategoryInstanceId;
    }

    public Webservice getWebserviceByWebserviceInstanceId() {
        return webserviceByWebserviceInstanceId;
    }

    public void setWebserviceByWebserviceInstanceId(Webservice webserviceByWebserviceInstanceId) {
        this.webserviceByWebserviceInstanceId = webserviceByWebserviceInstanceId;
    }
}
