/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.NewsArticle;
import io.reactivex.Observable;
import java.util.HashMap;
import java.util.LinkedList;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.Pagination;
import org.neo4j.ogm.cypher.query.SortOrder;
import utils.DatabaseUtils;

/**
 *
 * @author zua
 */

@Stateless
public class NewsArticleService extends AbstractDBService<NewsArticle> implements NewsArticleServiceInterface {

    @Override
    public NewsArticle findArticleWithTitle(String title) {
        try {
            String query = "MATCH (n:NewsArticle) ";
            query += "WHERE n.title=" + DatabaseUtils.getInstance().wrapUp(title) + " ";
            query += "  RETURN n ";
            return getSession().queryForObject(getClassType(), query, new HashMap<String, Object>());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Observable<NewsArticle> findAllArticles() {
        return Observable.fromIterable(getSession().loadAll(NewsArticle.class, 2));
    }

    @Override
    public Observable<NewsArticle> findArticlesWithText(String value) {
        if(value == null || value.isEmpty()) {
            return Observable.fromIterable(new LinkedList<>());
        }
        return executeQuery(Neo4jQueries.findArticlesWithText(value, LIMIT));
    }

    @Override
    public Observable<NewsArticle> findArticlesWithText(String username, String value) {
        if(username == null || username.isEmpty() ) {
            return findArticlesWithText(value);
        }
        return executeQuery(Neo4jQueries.findArticlesWithText(username, value, LIMIT));
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
    public Observable<NewsArticle> findArticlesWithCategory(String category) {
        return executeQuery(Neo4jQueries.findArticlesWithCategory(category, LIMIT));
    }

    @Override
    public Observable<NewsArticle> findArticlesWithCategory(String username, String category) {
        if(username == null) {
            return findArticlesWithCategory(category);
        }
        return executeQuery(Neo4jQueries.findArticlesWithCategory(username, category, LIMIT));
    }

    @Override
    public Observable<NewsArticle> findArticlesWithSource(String sourceId) {
        return executeQuery(Neo4jQueries.findArticlesWithSource(sourceId, LIMIT));
    }

    @Override
    public Observable<NewsArticle> findArticlesWithSource(String username, String sourceId) {
        if(username == null) {
            return findArticlesWithSource(sourceId);
        }
        return executeQuery(Neo4jQueries.findArticlesWithSource(username, sourceId, LIMIT));
    }

    @Override
    public Observable<NewsArticle> findArticlesWithLanguage(String username, String language) {
        if(username == null) {
            return findArticlesWithLanguage(language);
        }
        return executeQuery(Neo4jQueries.findArticlesWithLanguage(username, language, LIMIT));
    }

    @Override
    public Observable<NewsArticle> findArticlesWithLanguage(String language) {
        return executeQuery(Neo4jQueries.findArticlesWithLanguage(language, LIMIT));
    }

    @Override
    public Observable<NewsArticle> findArticlesWithCountry(String country) {
        return executeQuery(Neo4jQueries.findArticlesWithCountry(country, LIMIT));
    }

    @Override
    public Observable<NewsArticle> findArticlesWithCountry(String username, String country) {
        if(username == null) {
            return findArticlesWithCountry(country);
        }
        return executeQuery(Neo4jQueries.findArticlesWithCountry(username, country, LIMIT));
    }

    @Override
    public Observable<NewsArticle> findLatest() {
        return findAllDesc();
    }

    @Override
    public Observable<NewsArticle> findOldest() {
        return findAllAsc();
    }

    @Override
    public Observable<NewsArticle> findLatest(String username) {
        if(username == null) {
            return findLatest();
        }
        return findAllDesc(username);
    }

    @Override
    public Observable<NewsArticle> findOldest(String username) {
        if(username == null) {
            return findOldest();
        }
        return findAllAsc(username);
    }

    @Override
    public Observable<NewsArticle> findAllRead(String username) {
        if(username == null) {
            return findLatest();
        }
        else {
            String query = Neo4jQueries.getMatchStateQuery(getClassType(), "READ", username, null, null, LIMIT);
            return executeQuery(query);
        }
    }

    @Override
    public Observable<NewsArticle> findAllFavorite(String username) {
        if(username == null) {
            return findLatest();
        }
        else {
            String query = Neo4jQueries.getMatchStateQuery(getClassType(), "FAVORITE", username, null, null, LIMIT);
            return executeQuery(query);
        }
    }

    @Override
    public Observable<NewsArticle> findAllFake(String username) {
        if(username == null) {
            return findLatest();
        }
        else {
            String query = Neo4jQueries.getMatchStateQuery(getClassType(), "FAKE", username, null, null, LIMIT);
            return executeQuery(query);
        }
    }

    @Override
    public void markAsRead(String username, NewsArticle entity) {
        if (!isRead(username, entity)) {
            getSession().query(Neo4jQueries.getCreateStateQuery(getClassType(), "READ", "username", username, entity.getPropertyName(), entity.getPropertyValue()), new HashMap<>());
        }
    }

    @Override
    public void markAsFavorite(String username, NewsArticle entity) {
        if (!isFavorite(username, entity)) {
            getSession().query(Neo4jQueries.getCreateStateQuery(getClassType(), "FAVORITE", "username", username, entity.getPropertyName(), entity.getPropertyValue()), new HashMap<>());
        }
    }

    @Override
    public void markAsFake(String username, NewsArticle entity) {
        if (!isFake(username, entity)) {
            getSession().query(Neo4jQueries.getCreateStateQuery(getClassType(), "FAKE", "username", username, entity.getPropertyName(), entity.getPropertyValue()), new HashMap<>());
        }
    }

    @Override
    public void removeMarkAsRead(String username, NewsArticle entity) {
        if (isRead(username, entity)) {
            getSession().query(Neo4jQueries.getDeleteStateQuery(getClassType(), "READ", "username", username, entity.getPropertyName(), entity.getPropertyValue()), new HashMap<>());
        }
    }

    @Override
    public void removeMarkAsFavorite(String username, NewsArticle entity) {
        if (isFavorite(username, entity)) {
            getSession().query(Neo4jQueries.getDeleteStateQuery(getClassType(), "FAVORITE", "username", username, entity.getPropertyName(), entity.getPropertyValue()), new HashMap<>());
        }
    }

    @Override
    public void removeMarkAsFake(String username, NewsArticle entity) {
        if (isFake(username, entity)) {
            getSession().query(Neo4jQueries.getDeleteStateQuery(getClassType(), "FAKE", "username", username, entity.getPropertyName(), entity.getPropertyValue()), new HashMap<>());
        }
    }

    @Override
    public boolean isRead(String username, NewsArticle entity) {
        return getSession().queryForObject(getClassType(), Neo4jQueries.getMatchStateQuery(getClassType(), "READ", username, entity.getPropertyName(), entity.getPropertyValue(), -1), new HashMap<>())
                != null;
    }

    @Override
    public boolean isFavorite(String username, NewsArticle entity) {
        return getSession().queryForObject(getClassType(), Neo4jQueries.getMatchStateQuery(getClassType(), "FAVORITE", username, entity.getPropertyName(), entity.getPropertyValue(), -1), new HashMap<>())
                    != null;
    }

    @Override
    public boolean isFake(String username, NewsArticle entity) {
        return getSession().queryForObject(getClassType(), Neo4jQueries.getMatchStateQuery(getClassType(), "FAKE", username, entity.getPropertyName(), entity.getPropertyValue(), -1), new HashMap<>())
            != null;
    }
    
    @Override
    public Observable<NewsArticle> findArticlesWithoutShortUrl() {
        String query = "MATCH (n:NewsArticle) ";
        query += "WHERE n.shortUrl IS NULL RETURN n ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        System.out.println(query);
        return executeQuery(query);
    }
    
    @Override
    public Observable<NewsArticle> findAll(int page, int pageSize) {
        Pagination paging = new Pagination(page, pageSize);
        return Observable.fromIterable(getSession().loadAll(NewsArticle.class, paging));
    }
}
