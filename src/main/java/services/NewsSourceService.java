/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.NewsSource;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.SortOrder;
import utils.DatabaseUtils;

/**
 *
 * @author zua
 */
@Stateless
public class NewsSourceService extends AbstractDBService<NewsSource>  implements NewsSourceServiceInterface {

    @Override
    public Class<NewsSource> getClassType() {
        return NewsSource.class;
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
    public List<String> findCategories() {
        String query = "MATCH (s:NewsSource) RETURN DISTINCT s.category ORDER BY s.category ASC";
        return executeQuery(String.class, query);
    }

    @Override
    public List<String> findNames() {
        String query = "MATCH (s:NewsSource) RETURN DISTINCT s.name ORDER BY s.name ASC";
        return executeQuery(String.class, query);
    }

    @Override
    public List<String> findLanguages() {
        String query = "MATCH (s:NewsSource) RETURN DISTINCT s.language ORDER BY s.language ASC";
        return executeQuery(String.class, query);
    }

    @Override
    public List<String> findCountries() {
        String query = "MATCH (s:NewsSource) RETURN DISTINCT s.language+'_'+s.country";
        return executeQuery(String.class, query);
    }
    
    @Override
    public NewsSource findSourceWithSourceId(String sourceId) {
        String query = "MATCH (n:NewsSource) ";
        query += "WHERE n.sourceId=" + DatabaseUtils.getInstance().wrapUp(sourceId) + " ";
        query += "  RETURN n ";
        return getSession().queryForObject(getClassType(), query, new HashMap<String, Object>());
    }

}
