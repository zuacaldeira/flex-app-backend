package db.news;

import db.GraphEntity;
import db.relationships.EditedBy;
import db.relationships.PublishedBy;
import db.relationships.TaggedSourceAs;
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
    private TaggedSourceAs category;
    
    
    @Relationship(type = "PUBLISHED_BY")
    private Set<PublishedBy> published;
    
    @Relationship(type = "EDITED_BY")
    private Set<EditedBy> edited;
    
    public NewsSource() {
        published = new HashSet<>();
        edited = new HashSet<>();
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

    public Set<PublishedBy> getPublished() {
        return published;
    }

    public void setPublished(Set<PublishedBy> published) {
        this.published = published;
    }

    public Set<EditedBy> getEdited() {
        return edited;
    }

    public void setEdited(Set<EditedBy> edited) {
        this.edited = edited;
    }

    public TaggedSourceAs getCategory() {
        return category;
    }

    public void setCategory(TaggedSourceAs category) {
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
