package model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "category_scheme", schema = "public", catalog = "cerif")
public class CategoryScheme {
    @Id
    @Column(name = "instance_id", nullable = false, length = 100)
    private String instanceId;
    @Basic
    @Column(name = "meta_id", nullable = true, length = 100)
    private String metaId;
    @Basic
    @Column(name = "uid", nullable = true, length = 100)
    private String uid;
    @Basic
    @Column(name = "version_id", nullable = true, length = 100)
    private String versionId;
    @Basic
    @Column(name = "name", nullable = true, length = 255)
    private String name;
    @Basic
    @Column(name = "code", nullable = true, length = 255)
    private String code;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @Basic
    @Column(name = "logo", nullable = true, length = 255)
    private String logo;
    @Basic
    @Column(name = "homepage", nullable = true, length = 255)
    private String homepage;
    @Basic
    @Column(name = "color", nullable = true, length = 255)
    private String color;
    @Basic
    @Column(name = "orderitemnumber", nullable = true, length = 255)
    private String orderitemnumber;
    @OneToMany(mappedBy = "categorySchemeByInScheme")
    private Collection<Category> categoriesByInstanceId;
    @OneToMany(mappedBy = "categorySchemeByCategorySchemeInstanceId")
    private Collection<CategoryHastopconcept> categoryHastopconceptsByInstanceId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "version_id", referencedColumnName = "version_id")
    private Versioningstatus versioningstatusByVersionId;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getMetaId() {
        return metaId;
    }

    public void setMetaId(String metaId) {
        this.metaId = metaId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryScheme that = (CategoryScheme) o;

        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (metaId != null ? !metaId.equals(that.metaId) : that.metaId != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (versionId != null ? !versionId.equals(that.versionId) : that.versionId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (logo != null ? !logo.equals(that.logo) : that.logo != null) return false;
        if (homepage != null ? !homepage.equals(that.homepage) : that.homepage != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        if (orderitemnumber != null ? !orderitemnumber.equals(that.orderitemnumber) : that.orderitemnumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (homepage != null ? homepage.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (orderitemnumber != null ? orderitemnumber.hashCode() : 0);
        return result;
    }

    public Collection<Category> getCategoriesByInstanceId() {
        return categoriesByInstanceId;
    }

    public void setCategoriesByInstanceId(Collection<Category> categoriesByInstanceId) {
        this.categoriesByInstanceId = categoriesByInstanceId;
    }

    public Collection<CategoryHastopconcept> getCategoryHastopconceptsByInstanceId() {
        return categoryHastopconceptsByInstanceId;
    }

    public void setCategoryHastopconceptsByInstanceId(Collection<CategoryHastopconcept> categoryHastopconceptsByInstanceId) {
        this.categoryHastopconceptsByInstanceId = categoryHastopconceptsByInstanceId;
    }

    public Versioningstatus getVersioningstatusByVersionId() {
        return versioningstatusByVersionId;
    }

    public void setVersioningstatusByVersionId(Versioningstatus versioningstatusByVersionId) {
        this.versioningstatusByVersionId = versioningstatusByVersionId;
    }
}
