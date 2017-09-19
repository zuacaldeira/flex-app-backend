/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.NewsAuthor;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.SortOrder;

/**
 *
 * @author zua
 */
@Stateless
public class NewsAuthorService extends AbstractDBService<NewsAuthor>  implements NewsAuthorServiceInterface {

    @Override
    public Class<NewsAuthor> getClassType() {
        return NewsAuthor.class;
    }
    
    @Override
    public NewsAuthor update(NewsAuthor dbEntity, NewsAuthor newEntity) {
        if(newEntity.getName() != null && !newEntity.getName().equals(dbEntity.getName())) {
            dbEntity.setName(newEntity.getName());
        }
        
        if(newEntity.getSource() != null && !newEntity.getSource().equals(dbEntity.getSource())) {
            dbEntity.setSource(newEntity.getSource());
        }
        
        if(newEntity.getArticles() != null) {
            dbEntity.getArticles().addAll(newEntity.getArticles());
        }
        
        return dbEntity;
        
    }

    @Override
    public SortOrder getSortOrderAsc() {
        return new SortOrder().add(SortOrder.Direction.ASC, "name");
    }

    @Override
    public SortOrder getSortOrderDesc() {
        return new SortOrder().add(SortOrder.Direction.DESC, "name");
    }
}
