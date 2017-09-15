/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news;

import db.news.NewsArticle;
import java.util.Collection;
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
public class NewsArticleService extends  AbstractDBService<NewsArticle> implements NewsArticleServiceInterface {
    
    
    @Override
    public Class<NewsArticle> getClassType() {
        return NewsArticle.class;
    }

    @Override
    public NewsArticle update(NewsArticle dbEntity, NewsArticle newEntity) {
        if(newEntity.getAuthors() != null && !newEntity.getAuthors().equals(dbEntity.getAuthors())) {
            dbEntity.getAuthors().addAll(newEntity.getAuthors());
        }
        
        if(newEntity.getDescription() != null && !newEntity.getDescription().equals(dbEntity.getDescription())) {
            dbEntity.setDescription(newEntity.getDescription());
        }
        
        if(newEntity.getImageUrl() != null && !newEntity.getImageUrl().equals(dbEntity.getImageUrl())) {
            dbEntity.setImageUrl(newEntity.getImageUrl());
        }
        
        if(newEntity.getPublishedAt() != null && !newEntity.getPublishedAt().equals(dbEntity.getPublishedAt())) {
            dbEntity.setPublishedAt(newEntity.getPublishedAt());
        }

        if(newEntity.getSourceId() != null && !newEntity.getSourceId().equals(dbEntity.getSourceId())) {
            dbEntity.setSourceId(newEntity.getSourceId());
        }
        
        if(newEntity.getTitle() != null && !newEntity.getTitle().equals(dbEntity.getTitle())) {
            dbEntity.setTitle(newEntity.getTitle());
        }

        if(newEntity.getUrl() != null && !newEntity.getUrl().equals(dbEntity.getUrl())) {
            dbEntity.setUrl(newEntity.getUrl());
        }
        
        return dbEntity;
    }
    
    @Override
    public SortOrder getSortOrderAsc() {
        return new SortOrder().add(SortOrder.Direction.ASC, "publishedAt");
    }
    
    @Override
    public SortOrder getSortOrderDesc() {
        return new SortOrder().add(SortOrder.Direction.DESC, "publishedAt");
    }

    @Override
    public List<NewsArticle> findArticlesWithCategory(String username, String category) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle)--(a:NewsAuthor)--(s:NewsSource) ";
        query += "WHERE u.username=" + BackendUtils.getInstance().wrapUp(username) + " ";
        query += "  AND s.category = " + BackendUtils.getInstance().wrapUp(category) + " ";
        query += "  AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        System.out.println(query);
        return super.executeQuery(query);
    }

    @Override
    public List<NewsArticle> findArticlesWithSource(String username, String publisherName) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle)--(a:NewsAuthor)--(s:NewsSource) ";
        query += "WHERE u.username=" + BackendUtils.getInstance().wrapUp(username) + " ";
        query += "  AND s.name=" + BackendUtils.getInstance().wrapUp(publisherName) + " ";
        query += "  AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        System.out.println(query);
        return super.executeQuery(query);
    }

    @Override
    public Collection<NewsArticle> findArticlesWithText(String username, String value) {
        String query = "MATCH (n:NewsArticle) ";
        query += "WHERE n.title CONTAINS " + BackendUtils.getInstance().wrapUp(value) + " ";
        query += " OR n.description CONTAINS " + BackendUtils.getInstance().wrapUp(value) + " ";
        query += " RETURN n ";
        query += " ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        System.out.println(query);
        return super.executeQuery(query);
    }

    @Override
    public Collection<NewsArticle> findArticlesWithLanguage(String username, String value) {
        String query = "MATCH (n:NewsArticle) ";
        query += "WHERE n.language=" + BackendUtils.getInstance().wrapUp(value) + " ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return super.executeQuery(query);
    }

    @Override
    public Collection<NewsArticle> findArticlesWithCountry(String username, String value) {
        String query = "MATCH (n:NewsArticle) ";
        query += "WHERE n.country=" + BackendUtils.getInstance().wrapUp(value) + " ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        System.out.println(query);
        return super.executeQuery(query);
    }

    @Override
    public List<NewsArticle> findLatest(){
        return findAllDesc();
    }
    
    @Override
    public List<NewsArticle> findOldest(){
        return findAllAsc();
    }

    @Override
    public List<NewsArticle> findLatest(int limit){
        return findAllDesc(limit);
    }
    
    @Override
    public List<NewsArticle> findOldest(int limit) {
        return findAllAsc(limit);
    }

    @Override
    public List<NewsArticle> findLatest(String username) {
        return findAllDesc(username);
    }
    
    @Override
    public List<NewsArticle> findOldest(String username) {
        return findAllAsc(username);
    }
    
    @Override
    public List<NewsArticle> findLatest(String username, int limit) {
        return findAllDesc(username, limit);
    }
    
    @Override
    public List<NewsArticle> findOldest(String username, int limit) {
        return findAllAsc(username, limit);
    }

    @Override
    public List<NewsArticle> findAllRead(String username) {
        String query = getMatchStateQuery("READ", username, null, null, LIMIT);
        return executeQuery(query);
    }

    @Override
    public List<NewsArticle> findAllFavorite(String username) {
        String query = getMatchStateQuery("FAVORITE", username, null, null, LIMIT);
        return executeQuery(query);
    }

    @Override
    public List<NewsArticle> findAllFake(String username) {
        String query = getMatchStateQuery("FAKE", username, null, null, LIMIT);
        return executeQuery(query);
    }
    
    
    @Override
    public List<NewsArticle> findAllRead(String username, int limit) {
        String query = getMatchStateQuery("READ", username, null, null, limit);
        return executeQuery(query);
    }

    @Override
    public List<NewsArticle> findAllFavorite(String username, int limit) {
        String query = getMatchStateQuery("FAVORITE", username, null, null, limit);
        return executeQuery(query);
    }

    @Override
    public List<NewsArticle> findAllFake(String username, int limit) {
        String query = getMatchStateQuery("FAKE", username, null, null, limit);
        return executeQuery(query);
    }
    

    @Override
    public void markAsRead(String username, NewsArticle entity) {
        if(!isRead(username, entity)){
            getSession().query(getCreateStateQuery("READ", "username", username, entity.getPropertyName(), entity.getPropertyValue()), new HashMap<String, Object>());
        }
    }

    @Override
    public void markAsFavorite(String username, NewsArticle entity) {
        if(!isFavorite(username, entity)) {
            getSession().query(getCreateStateQuery("FAVORITE", "username", username, entity.getPropertyName(), entity.getPropertyValue()), new HashMap<String, Object>());
        }
    }

    @Override
    public void markAsFake(String username, NewsArticle entity) {
        if(!isFake(username, entity)) {
            getSession().query(getCreateStateQuery("FAKE", "username", username, entity.getPropertyName(), entity.getPropertyValue()), new HashMap<String, Object>());
        }
    }


    @Override
    public void removeMarkAsRead(String username, NewsArticle entity) {
        if(isRead(username, entity)) {
            getSession().query(getDeleteStateQuery("READ", "username", username, entity.getPropertyName(), entity.getPropertyValue()), new HashMap<String, Object>());
        }
    }
    
    @Override
    public void removeMarkAsFavorite(String username, NewsArticle entity) {
        if(isFavorite(username, entity)) {
            getSession().query(getDeleteStateQuery("FAVORITE", "username", username, entity.getPropertyName(), entity.getPropertyValue()), new HashMap<String, Object>());
        }
    }

    @Override
    public void removeMarkAsFake(String username, NewsArticle entity) {
        if(isFake(username, entity)) {
            getSession().query(getDeleteStateQuery("FAKE", "username", username, entity.getPropertyName(), entity.getPropertyValue()), new HashMap<String, Object>());
        }
    }

    @Override
    public boolean isRead(String username, NewsArticle entity) {
        return getSession().queryForObject(getClassType(), getMatchStateQuery("READ", username, entity.getPropertyName(), entity.getPropertyValue(), -1), new HashMap<String, Object>()) 
                != null;
    }

    @Override
    public boolean isFavorite(String username, NewsArticle entity) {
        return getSession().queryForObject(getClassType(), getMatchStateQuery("FAVORITE", username, entity.getPropertyName(), entity.getPropertyValue(), -1), new HashMap<String, Object>()) 
                != null;
    }

    @Override
    public boolean isFake(String username, NewsArticle entity) {
        return getSession().queryForObject(getClassType(), getMatchStateQuery("FAKE", username, entity.getPropertyName(), entity.getPropertyValue(), -1), new HashMap<String, Object>()) 
                != null;
    }
 
}
