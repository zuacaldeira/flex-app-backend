package db.news;

import db.GraphEntity;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by zua on 15/04/17.
 */
@NodeEntity
public class NewsAuthor extends GraphEntity implements Comparable<NewsAuthor> {

    private static final long serialVersionUID = 5117714308650379252L;

    /**
     * Author's name.
     *
     * Names are unique, and the db has a uniqueness constraint on this
     * property.
     */
    @Index(unique = true, primary = true)
    private String name;

    /**
     * Author's online presence url.
     */
    private String url;


    @Relationship(type = "AUTHORED")
    private Set<NewsArticle> authored;

    public NewsAuthor() {
        name = "Unknown";
        authored = new HashSet<>();
    }

    public NewsAuthor(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<NewsArticle> getAuthored() {
        return authored;
    }

    public void setAuthored(Set<NewsArticle> authored) {
        this.authored = authored;
    }

    

    @Override
    public int compareTo(NewsAuthor o) {
        return (int) Math.signum(this.toString().compareTo(o.toString()));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.name);
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
        final NewsAuthor other = (NewsAuthor) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (name != null) {
            builder.append(name);
        }
        return builder.toString();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
