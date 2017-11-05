/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.Advertises;
import db.NewsArticle;
import java.util.Collection;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.SortOrder;
import utils.DatabaseUtils;

/**
 *
 * @author zua
 */
@Stateless
public class FlexAdvertisementService extends AbstractDBService<Advertises> implements FlexAdvertisementServiceInterface {

    @Override
    public Class<Advertises> getClassType() {
        return Advertises.class;
    }

    @Override
    public SortOrder getSortOrderAsc() {
        return new SortOrder().add(SortOrder.Direction.ASC, "name");
    }

    @Override
    public SortOrder getSortOrderDesc() {
        return new SortOrder().add(SortOrder.Direction.DESC, "name");
    }
    
    @Override
    public Collection<Advertises> findAll(NewsArticle article) {
        String query = "MATCH (aP:AdsProvider)-[r:Advertises]-(nA:NewsArticle) WHERE ";
        query += "nA.url = " + DatabaseUtils.getInstance().wrapUp(article.getUrl());
        query += "RETURN r";
        return executeQuery(query);
    }
}
