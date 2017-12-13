/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.services.news;

import db.news.NewsAuthor;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.SortOrder;
import backend.services.AbstractDBService;

/**
 *
 * @author zua
 */
@Stateless
public class NewsAuthorService extends AbstractDBService<NewsAuthor> {

    @Override
    public Class<NewsAuthor> getClassType() {
        return NewsAuthor.class;
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
