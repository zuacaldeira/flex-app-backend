/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news;

import db.news.TaggedAs;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.SortOrder;
import services.AbstractDBService;

/**
 *
 * @author zua
 */
@Stateless
public class TaggedAsService extends AbstractDBService<TaggedAs>{

    @Override
    public Class<TaggedAs> getClassType() {
        return TaggedAs.class;
    }

    @Override
    public SortOrder getSortOrderAsc() {
        return new SortOrder().add(SortOrder.Direction.ASC, "id");
    }

    @Override
    public SortOrder getSortOrderDesc() {
        return new SortOrder().add(SortOrder.Direction.DESC, "id");
    }

}
