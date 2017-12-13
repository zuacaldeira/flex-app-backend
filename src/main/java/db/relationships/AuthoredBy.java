/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.relationships;

import db.GraphEntity;
import db.news.NewsArticle;
import db.news.NewsAuthor;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 *
 * @author zua
 */
@RelationshipEntity(type = "AUTHORED_BY")
public class AuthoredBy extends GraphEntity {

    private static final long serialVersionUID = -8559689549886304845L;
    
    @StartNode
    private NewsArticle article;
    
    @EndNode
    private NewsAuthor author;

    public NewsArticle getArticle() {
        return article;
    }

    public void setArticle(NewsArticle article) {
        this.article = article;
        this.article.getAuthoredBy().add(this);
    }

    public NewsAuthor getAuthor() {
        return author;
    }

    public void setAuthor(NewsAuthor author) {
        this.author = author;
        this.author.getAuthored().add(this);
    }

    
    
}
