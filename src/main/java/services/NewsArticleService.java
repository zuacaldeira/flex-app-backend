/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.NewsArticle;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.neo4j.ogm.cypher.BooleanOperator;
import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.cypher.Filters;
import org.neo4j.ogm.cypher.query.SortOrder;
import utils.DatabaseUtils;

/**
 *
 * @author zua
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NewsArticleService extends  AbstractDBService<NewsArticle> implements NewsArticleServiceInterface {
    
    
    @Override
    public NewsArticle findArticleWithTitle(String title) {
        String query = "MATCH (n:NewsArticle) ";
        query += "WHERE n.title=" + DatabaseUtils.getInstance().wrapUp(title) + " ";
        query += "  RETURN n ";
        return getSession().queryForObject(getClassType(), query, new HashMap<String, Object>());
    }

    @Override
    public Collection<NewsArticle> findAllArticles() {
        return getSession().loadAll(NewsArticle.class, 2);
    }
    
    @Override
    public Collection<NewsArticle> findArticlesWithText(String value) {
        if(value == null || value.isEmpty()){
            throw new NewsServiceException(new IllegalArgumentException("Value cannot be a null nor empty string"));
        }
        Filter fTitle = new Filter("title", ComparisonOperator.CONTAINING, value);
        Filter fDescription = new Filter("description", ComparisonOperator.CONTAINING, value);
        fDescription.setBooleanOperator(BooleanOperator.OR);
        Filters filters = new Filters(fTitle, fDescription);
        return getSession().loadAll(NewsArticle.class, filters, 2);
    }
    
    @Override
    public Collection<NewsArticle> findArticlesWithText(String username, String value) {
        if(value == null || value.isEmpty()){
            throw new NewsServiceException(new IllegalArgumentException("Value cannot be a null nor empty string"));
        }
        if(username == null || username.isEmpty()) {
            throw new NewsServiceException(new IllegalArgumentException("Username cannot be null nor empty string"));
        }
        String query = "MATCH (u:FlexUser)--(n:NewsArticle) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += " AND (n.title CONTAINS " + DatabaseUtils.getInstance().wrapUp(value) + " ";
        query += " OR n.description CONTAINS " + DatabaseUtils.getInstance().wrapUp(value) + ") ";
        query += " RETURN n ";
        query += " ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        //System.out.println(query);
        return super.executeQuery(query);
    }
    

    @Override
    public Class<NewsArticle> getClassType() {
        return NewsArticle.class;
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
    public List<NewsArticle> findArticlesWithCategory(String category) {
        String query = "MATCH (n:NewsArticle)--(a:NewsAuthor)--(s:NewsSource) ";
        query += "WHERE (n.category = " + DatabaseUtils.getInstance().wrapUp(category) + " ";
        query += "  OR  s.category = " + DatabaseUtils.getInstance().wrapUp(category) + ") ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        System.out.println(query);
        return super.executeQuery(query);
    }

    @Override
    public List<NewsArticle> findArticlesWithCategory(String username, String category) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle)--(a:NewsAuthor)--(s:NewsSource) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "  AND (n.category = " + DatabaseUtils.getInstance().wrapUp(category) + " ";
        query += "  OR  s.category = " + DatabaseUtils.getInstance().wrapUp(category) + ") ";
        query += "  AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        System.out.println(query);
        return super.executeQuery(query);
    }

    @Override
    public List<NewsArticle> findArticlesWithSource(String sourceId) {
        String query = "MATCH (n:NewsArticle) ";
        query += "WHERE n.sourceId=" + DatabaseUtils.getInstance().wrapUp(sourceId) + " ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        System.out.println(query);
        return super.executeQuery(query);
    }

    @Override
    public List<NewsArticle> findArticlesWithSource(String username, String sourceId) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle)--(a:NewsAuthor)--(s:NewsSource) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "  AND (n.sourceId=" + DatabaseUtils.getInstance().wrapUp(sourceId) + " ";
        query += "  OR s.sourceId=" + DatabaseUtils.getInstance().wrapUp(sourceId) + ") ";
        query += "  AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        System.out.println(query);
        return super.executeQuery(query);
    }


    @Override
    public Collection<NewsArticle> findArticlesWithLanguage(String username, String language) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "AND n.language=" + DatabaseUtils.getInstance().wrapUp(language) + " ";
        //query += "OR  s.language=" + DatabaseUtils.getInstance().wrapUp(language) + " ";
        query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return super.executeQuery(query);
    }

    @Override
    public Collection<NewsArticle> findArticlesWithLanguage(String language) {
        String query = "MATCH (n:NewsArticle) ";
        query += "WHERE n.language=" + DatabaseUtils.getInstance().wrapUp(language) + " ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return super.executeQuery(query);
    }

    @Override
    public Collection<NewsArticle> findArticlesWithCountry(String country) {
        String query = "MATCH (n:NewsArticle) ";
        query += "WHERE n.country=" + DatabaseUtils.getInstance().wrapUp(country) + " ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        System.out.println(query);
        return super.executeQuery(query);
    }

    @Override
    public Collection<NewsArticle> findArticlesWithCountry(String username, String country) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle)--(a:NewsAuthor)--(s:NewsSource) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "AND n.country=" + DatabaseUtils.getInstance().wrapUp(country) + " ";
        query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
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
    public List<NewsArticle> findLatest(String username) {
        return findAllDesc(username);
    }
    
    @Override
    public List<NewsArticle> findOldest(String username) {
        return findAllAsc(username);
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
