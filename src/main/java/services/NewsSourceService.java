/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.NewsSource;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
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
    public Set<String> findCategories() {
        String query = "MATCH (s:NewsSource) RETURN DISTINCT s.category ORDER BY s.category ASC";
        TreeSet<String> result = new TreeSet<>();
        result.addAll(executeQuery(String.class, query));
        return result;
    }

    @Override
    public Set<String> findNames() {
        String query = "MATCH (s:NewsSource) RETURN DISTINCT s.name ORDER BY s.name ASC";
        TreeSet<String> result = new TreeSet<>();
        result.addAll(executeQuery(String.class, query));
        return result;
    }

    @Override
    public Set<String> findLanguages() {
        String query = "MATCH (s:NewsSource) RETURN DISTINCT s.language ORDER BY s.language ASC";
        TreeSet<String> result = new TreeSet<>();
        result.addAll(executeQuery(String.class, query));
        return result;
    }

    @Override
    public Set<String> findCountries() {
        String query = "MATCH (s:NewsSource) RETURN DISTINCT s.country ORDER BY s.country ASC";
        TreeSet<String> result = new TreeSet<>();
        result.addAll(executeQuery(String.class, query));
        return result;
    }

    @Override
    public Set<String> findLocales() {
        String query = "MATCH (s:NewsSource) RETURN DISTINCT s.language+'_'+s.country";
        TreeSet<String> result = new TreeSet<>();
        result.addAll(executeQuery(String.class, query));
        return result;
    }
    
    @Override
    public NewsSource findSourceWithSourceId(String sourceId) {
        String query = "MATCH (n:NewsSource) ";
        query += "WHERE n.sourceId=" + DatabaseUtils.getInstance().wrapUp(sourceId) + " ";
        query += "  RETURN n ";
        return getSession().queryForObject(getClassType(), query, new HashMap<String, Object>());
    }

}
