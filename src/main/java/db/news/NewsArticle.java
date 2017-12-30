package db.news;

import db.GraphEntity;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by zua on 13/04/17.
 */
@NodeEntity
public class NewsArticle extends GraphEntity implements Comparable<NewsArticle> {

    private static final long serialVersionUID = -5488934119305207913L;

    @Index(primary = true, unique = true)
    private String title;

    private String url;
    private String description;
    private String imageUrl;

    //@DateLong
    private Date publishedAt;
    private String language;
    private String country;


    @Relationship(type = "TAGGED_AS")
    private Set<Tag> taggedAs;
    
    private String sourceId;
    
    
    public NewsArticle() {
        taggedAs = new HashSet<>();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country.toUpperCase();
    }


    public Set<Tag> getTaggedAs() {
        return taggedAs;
    }

    public void setTaggedAs(Set<Tag> taggedAs) {
        this.taggedAs = taggedAs;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.title);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NewsArticle other = (NewsArticle) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        if (title == null) {
            return "";
        }
        return title;
    }

    @Override
    public int compareTo(NewsArticle o) {
        if (this.title != null && o.title != null) {
            return this.title.compareTo(o.title);
        } else if (this.title == null && o.title != null) {
            return 1;
        } else if (this.title != null && o.title == null) {
            return -1;
        } else {
            return 0;
        }
    }

}
