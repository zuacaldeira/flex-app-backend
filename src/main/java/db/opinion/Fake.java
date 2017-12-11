/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.opinion;

import db.auth.FlexUser;
import db.news.NewsArticle;
import java.util.Date;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 *
 * @author zua
 */
@RelationshipEntity(type="FAKE")
public class Fake {
    
    @StartNode
    private FlexUser user;
    
    @EndNode
    private NewsArticle article;
    
    @Property
    private Date createdAt;
    
    public FlexUser getUser() {
        return user;
    }

    public void setUser(FlexUser user) {
        this.user = user;
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
    
    
}
