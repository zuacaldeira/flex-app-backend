package db;


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
public class NewsArticle extends GraphEntity implements Comparable<NewsArticle>{

    private static final long serialVersionUID = -5488934119305207913L;

    @Index(unique=true)
    private String title;
    
    private String description;
    private String url;
    private String imageUrl;
    
    //@DateLong
    private Date publishedAt;
    private String sourceId;
    
    private String language;
    private String country;
    private Set<String> categories;
    
    
    
    @Relationship(type = "AUTHORED", direction = Relationship.INCOMING)
    private Set<NewsAuthor> authors;
    
    public NewsArticle(){
        authors = new HashSet<>();
        categories = new HashSet<>();
    }

    public NewsArticle(String title, String description, String url, String imageUrl, Date publishedAt, String sourceId, String language, String country) {
        this();
        this.title = title;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
        this.publishedAt = publishedAt;
        this.sourceId = sourceId;
        this.language = language;
        this.categories = categories;
        this.authors = authors;
        setCountry(country);
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

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Set<NewsAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<NewsAuthor> authors) {
        this.authors = authors;
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

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
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
        if(title == null) {
            return "";
        }
        return title;
    }

    @Override
    public int compareTo(NewsArticle o) {
        if(this.title != null && o.title != null) {
            return this.title.compareTo(o.title);
        }
        else if(this.title == null && o.title!= null) {
            return 1;
        }
        else if(this.title != null && o.title == null) {
            return -1;
        } 
        else {
            return 0;
        }
    }

    @Override
    public String getPropertyName() {
        return "title";
    }

    @Override
    public String getPropertyValue() {
        return title;
    }

    @Override
    public boolean hasUrl() {
        return url != null;
    }

    public void addCategory(String category) {
        categories.add(category);
    }
}
