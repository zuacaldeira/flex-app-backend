/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news;

import db.news.Tag;
import java.util.Collection;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.SortOrder;
import services.AbstractDBService;

/**
 *
 * @author zua
 */
@Stateless
public class NewsTagService extends AbstractDBService<Tag> {

    public Collection<Tag> findAllTags() {
        return getSession().loadAll(getClassType(), 2);
    }

    @Override
    public Class<Tag> getClassType() {
        return Tag.class;     
    }

    @Override
    public SortOrder getSortOrderAsc() {
        return new SortOrder().add(SortOrder.Direction.ASC, "tag");
    }

    @Override
    public SortOrder getSortOrderDesc() {
        return new SortOrder().add(SortOrder.Direction.DESC, "tag");
    }
    
    
}
