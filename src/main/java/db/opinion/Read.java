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
@RelationshipEntity(type="READ")
public class Read extends GraphEntity {

    private static final long serialVersionUID = 3218005468506300052L;
    
    @StartNode
    private FlexUser user;
    
    @EndNode
    private NewsArticle article;
    
    @Property
    private Date createdAt;
    
    public Read() {
    }

    public FlexUser getUser() {
        return user;
    }

    public void setUser(FlexUser user) {
        this.user = user;
        user.getRead().add(this);
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.user);
        hash = 29 * hash + Objects.hashCode(this.article);
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
        final Read other = (Read) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.article, other.article)) {
            return false;
        }
        return true;
    }
    
    
    
}
