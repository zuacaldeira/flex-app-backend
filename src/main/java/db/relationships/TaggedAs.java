/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.relationships;

import db.GraphEntity;
import db.news.NewsArticle;
import db.news.Tag;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 *
 * @author zua
 */
@RelationshipEntity(type = "TAGGED_AS")
public class TaggedAs extends GraphEntity {

    private static final long serialVersionUID = 7786453007625414455L;
    
    @StartNode
    private NewsArticle article;
    
    @EndNode
    private Tag tag;

    public NewsArticle getArticle() {
        return article;
    }

    public void setArticle(NewsArticle article) {
        this.article = article;
        this.article.getTaggedAs().add(this);
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
    
}
