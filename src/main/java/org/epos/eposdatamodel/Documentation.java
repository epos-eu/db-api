package org.epos.eposdatamodel;


import java.util.Objects;

/**
 * Documentation.
 */
public class Documentation {
    private String title = null;

    private String description = null;

    private String uri = null;

    public Documentation title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get title
     *
     * @return title
     **/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Documentation description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get description
     *
     * @return description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Documentation uri(String uri) {
        this.uri = uri;
        return this;
    }

    /**
     * Get uri
     *
     * @return uri
     **/

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Documentation documentation = (Documentation) o;
        return Objects.equals(this.title, documentation.title) &&
                Objects.equals(this.description, documentation.description) &&
                Objects.equals(this.uri, documentation.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, uri);
    }

    @Override
    public String toString() {
        return "Documentation{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", URI='" + uri + '\'' +
                '}';
    }
}
