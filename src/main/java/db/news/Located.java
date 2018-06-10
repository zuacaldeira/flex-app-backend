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
@RelationshipEntity(type = "LOCATED")
public class Located extends GraphEntity {

    private static final long serialVersionUID = -6591042395668891203L;
    
    @StartNode
    private NewsArticle newsArticle;
    
    @EndNode
    private LocaleInfo localeInfo;
    
    public Located(){}

    public Located(NewsArticle newsArticle, LocaleInfo localeInfo) {
        this.newsArticle = newsArticle;
        this.localeInfo = localeInfo;
    }

    public NewsArticle getNewsArticle() {
        return newsArticle;
    }

    public void setNewsArticle(NewsArticle newsArticle) {
        this.newsArticle = newsArticle;
    }

    public LocaleInfo getLocaleInfo() {
        return localeInfo;
    }

    public void setLocaleInfo(LocaleInfo localeInfo) {
        this.localeInfo = localeInfo;
    }

    
}
