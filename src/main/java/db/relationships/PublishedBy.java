/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.relationships;

import db.GraphEntity;
import db.news.NewsArticle;
import db.news.NewsSource;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 *
 * @author zua
 */
@RelationshipEntity(type = "PUBLISHED_BY")
public class PublishedBy extends GraphEntity {

    private static final long serialVersionUID = -6504968713105578857L;
    
    @StartNode
    private NewsArticle article;
    
    @EndNode
    private NewsSource source;

    public NewsArticle getArticle() {
        return article;
    }

    public void setArticle(NewsArticle article) {
        this.article = article;
        this.article.setPublishedBy(this);
    }

    public NewsSource getSource() {
        return source;
    }

    public void setSource(NewsSource source) {
        this.source = source;
    }
    
    
}
