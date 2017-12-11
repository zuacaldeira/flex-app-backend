/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.relationships;

import db.GraphEntity;
import db.news.NewsAuthor;
import db.news.NewsSource;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 *
 * @author zua
 */
@RelationshipEntity(type = "EdITED_BY")
public class EditedBy extends GraphEntity {

    private static final long serialVersionUID = -6504968713105578857L;
    
    @StartNode
    private NewsAuthor author;
    
    @EndNode
    private NewsSource source;

    public NewsAuthor getAuthor() {
        return author;
    }

    public void setAuthor(NewsAuthor author) {
        this.author = author;
        this.author.getEditedBy().add(this);
    }

    public NewsSource getSource() {
        return source;
    }

    public void setSource(NewsSource source) {
        this.source = source;
    }

    
    
}
