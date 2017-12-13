/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.opinion;

import db.GraphEntity;
import db.auth.FlexUser;
import db.news.NewsArticle;
import java.util.Date;
import java.util.Objects;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 *
 * @author zua
 */
@RelationshipEntity(type="FAVORITE")
public class Favorite extends GraphEntity {

    private static final long serialVersionUID = 7335250291804016473L;
    
    @StartNode
    private FlexUser user;
    
    @EndNode
    private NewsArticle article;
    
    @Property
    private Date createdAt;
    
    @Property
    private String reason;

    public Favorite() {
    }
    
    public FlexUser getUser() {
        return user;
    }

    public void setUser(FlexUser user) {
        this.user = user;
        this.user.getFavorite().add(this);
    }

    public NewsArticle getArticle() {
        return article;
    }

    public void setArticle(NewsArticle article) {
        this.article = article;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Favorite other = (Favorite) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.article, other.article)) {
            return false;
        }
        return true;
    }
    
    
}
