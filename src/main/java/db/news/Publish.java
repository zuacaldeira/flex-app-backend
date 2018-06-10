/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.news;

import db.GraphEntity;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 *
 * @author zua
 */
@RelationshipEntity(type = "PUBLISHES")
public class Publish extends GraphEntity {

    private static final long serialVersionUID = -6591042395668891203L;
    
    @StartNode
    private NewsSource newsSource;
    
    @EndNode
    private NewsAuthor newsAuthor;
    
    public Publish(){}

    public Publish(NewsSource newsSource, NewsAuthor newsAuthor) {
        this.newsSource = newsSource;
        this.newsAuthor = newsAuthor;
    }
    
    

    public NewsSource getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(NewsSource newsSource) {
        this.newsSource = newsSource;
    }

    public NewsAuthor getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(NewsAuthor newsAuthor) {
        this.newsAuthor = newsAuthor;
    }
    
    
    
}
