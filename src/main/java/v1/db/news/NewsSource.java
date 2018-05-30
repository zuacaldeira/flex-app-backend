package v1.db.news;

import v1.db.GraphEntity;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by zua on 12/04/17.
 */
@NodeEntity
public class NewsSource extends GraphEntity implements Comparable<NewsSource> {

    private static final long serialVersionUID = -7869974236968731845L;

    @Index(primary = true, unique = true)    
    private String sourceId;
    private String name;
    private String description;
    private String url;
    private String language;
    private String country;
    private String logoUrl;
    
    @Relationship(type = "TAGGED_AS")
    private Tag category;

    @Relationship(type = "PUBLISHED")
    private Set<NewsAuthor> authors;
    
    public NewsSource() {
        authors = new HashSet<>();
    }

    public String getSourceId() {
        return sourceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<NewsAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<NewsAuthor> authors) {
        this.authors = authors;
    }

    public Tag getCategory() {
        return category;
    }

    public void setCategory(Tag category) {
        this.category = category;
    }

    
    
    

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(NewsSource o) {
        return sourceId.compareTo(o.getSourceId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.sourceId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NewsSource other = (NewsSource) obj;
        if (!Objects.equals(this.sourceId, other.sourceId)) {
            return false;
        }
        return true;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }
    
    

}
