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
@RelationshipEntity(type = "WRITES")
public class Writes extends GraphEntity {

    private static final long serialVersionUID = -6591042395668891203L;
    
    @StartNode
    private NewsAuthor newsAuthor;
    
    @EndNode
    private NewsArticle newsArticle;
    
    public Writes(){}

    public Writes(NewsAuthor newsAuthor, NewsArticle newsArticle) {
        this.newsAuthor = newsAuthor;
        this.newsArticle = newsArticle;
    }

    public NewsAuthor getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(NewsAuthor newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

    public NewsArticle getNewsArticle() {
        return newsArticle;
    }

    public void setNewsArticle(NewsArticle newsArticle) {
        this.newsArticle = newsArticle;
    }
    
    

    
    
    
}
