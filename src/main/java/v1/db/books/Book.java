package v1.db.books;


import v1.db.GraphEntity;
import v1.db.news.NewsAuthor;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by zua on 13/04/17.
 */
@NodeEntity
public class Book extends GraphEntity implements Comparable<Book>{

    private static final long serialVersionUID = -5488934119305207913L;

    @GraphId    
    private String advertisementUrl;
    private String ISBN;
    private String title;
    private String description;
    
    //@DateLong
    private Date publishedAt;
    private String publisher;
    
    private String language;
    private String country;
    private Set<String> categories;
    
    @Relationship(type = "AUTHORED", direction = Relationship.INCOMING)
    private Set<NewsAuthor> authors;
    
    public Book(){
        authors = new HashSet<>();
        categories = new HashSet<>();
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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
        this.country = country;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public Set<NewsAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<NewsAuthor> authors) {
        this.authors = authors;
    }

    
    @Override
    public String toString() {
        if(title == null) {
            return "";
        }
        return title;
    }

    @Override
    public int compareTo(Book o) {
        if(this.ISBN != null && o.ISBN != null) {
            return this.ISBN.compareTo(o.ISBN);
        }
        else if(this.ISBN == null && o.ISBN!= null) {
            return 1;
        }
        else if(this.ISBN != null && o.ISBN == null) {
            return -1;
        } 
        else {
            return 0;
        }
    }

    public void addCategory(String category) {
        categories.add(category);
    }
}
