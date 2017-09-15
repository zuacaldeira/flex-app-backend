/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news;

import db.news.NewsSource;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.SortOrder;
import utils.BackendUtils;

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
    public NewsSource update(NewsSource dbEntity, NewsSource newEntity) {
        if(newEntity.getCategory() != null) {
            dbEntity.setCategory(newEntity.getCategory());
        }
        if(newEntity.getCorrespondents() != null) {
            dbEntity.getCorrespondents().addAll(newEntity.getCorrespondents());
        }
        if(newEntity.getCountry() != null) {
            dbEntity.setCountry(newEntity.getCountry());
        }
        if(newEntity.getDescription() != null) {
            dbEntity.setDescription(newEntity.getDescription());
        }
        if(newEntity.getLanguage()!= null) {
            dbEntity.setLanguage(newEntity.getLanguage());
        }
        if(newEntity.getSourceId() != null) {
            dbEntity.setSourceId(newEntity.getSourceId());
        }
        if(newEntity.getUrl() != null) {
            dbEntity.setUrl(newEntity.getUrl());
        }
        if(newEntity.getLogoUrl() != null) {
            dbEntity.setLogoUrl(newEntity.getLogoUrl());
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
        String query = "MATCH (s:NewsSource) RETURN DISTINCT s.country ORDER BY s.country ASC";
        return executeQuery(String.class, query);
    }
    
    @Override
    public NewsSource findSourceWithSourceId(String sourceId) {
        String query = "MATCH (n:NewsSource) ";
        query += "WHERE n.sourceId=" + BackendUtils.getInstance().wrapUp(sourceId) + " ";
        query += "  RETURN n ";
        return getSession().queryForObject(getClassType(), query, new HashMap<String, Object>());
    }

}
