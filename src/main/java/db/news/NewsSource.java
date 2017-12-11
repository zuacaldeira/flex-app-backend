package db.news;

import db.GraphEntity;
import db.relationships.PublishedBy;
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
    private String category;
    private String language;
    private String country;
    private String logoUrl;
    
    @Relationship(type = "PUBLISHED_BY")
    private Set<PublishedBy> publishedBy;

    public NewsSource() {
        publishedBy = new HashSet<>();
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

    public String getCategory() {
        return category;
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

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(NewsSource o) {
        return sourceId.compareTo(o.getSourceId());
    }


    public Set<PublishedBy> getPublishedBy() {
        return publishedBy;
    }

    public void setPublishedBy(Set<PublishedBy> publishedBy) {
        this.publishedBy = publishedBy;
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
