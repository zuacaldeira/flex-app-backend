/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.services.news;

import db.news.NewsArticle;
import java.util.Collection;
import java.util.HashMap;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.Pagination;
import org.neo4j.ogm.cypher.query.SortOrder;
import backend.queries.ArticlesQueries;
import backend.services.AbstractDBService;
import io.reactivex.Observable;

/**
 *
 * @author zua
 */

@Stateless
public class NewsArticleService extends AbstractDBService<NewsArticle>  {

    public NewsArticle findArticleWithTitle(String title) {
        NewsArticle article = find(title);
        return article;
    }

    public Observable<NewsArticle> findArticlesWithUrl(String url) {
        String cypher = ArticlesQueries.findArticleWithUrl(url);
        return Observable.fromIterable(getSession().query(NewsArticle.class, cypher, new HashMap<>()));
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


    
    public Observable<NewsArticle> findArticlesWithoutShortUrl() {
        String cypher = "MATCH (n:NewsArticle) ";
        cypher += "WHERE n.shortUrl IS NULL RETURN n ";
        cypher += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        System.out.println(cypher);
        return Observable.fromIterable(getSession().query(NewsArticle.class, cypher, new HashMap<>()));
    }
    
    public Collection<NewsArticle> findAll(int page, int pageSize) {
        Pagination paging = new Pagination(page, pageSize);
        return getSession().loadAll(NewsArticle.class, paging);
    }

    public Observable<NewsArticle> findArticlesTaggedAs(String tag) {
        String cypher = ArticlesQueries.findArticlesTaggedAs(tag);
        return Observable.fromIterable(getSession().query(NewsArticle.class, cypher, new HashMap<>()));
    }

    public Observable<NewsArticle> findArticlesTaggedAs(String username, String tag) {
        String cypher = ArticlesQueries.findArticlesTaggedAs(username, tag);
        return Observable.fromIterable(getSession().query(NewsArticle.class, cypher, new HashMap<>()));
    }

    public Observable<NewsArticle> findArticlesPublishedBy(String sourceId) {
        String cypher = ArticlesQueries.findArticlesPublishedBy(sourceId);
        return Observable.fromIterable(getSession().query(NewsArticle.class, cypher, new HashMap<>()));
    }

    public Observable<NewsArticle> findArticlesPublishedBy(String username, String sourceId) {
        String cypher = ArticlesQueries.findArticlesPublishedBy(username, sourceId);
        return Observable.fromIterable(getSession().query(NewsArticle.class, cypher, new HashMap<>()));
    }

    public Observable<NewsArticle> findArticlesWithLanguage(String language) {
        String cypher = ArticlesQueries.findArticlesWithLanguage(language, LIMIT);
        return Observable.fromIterable(getSession().query(NewsArticle.class, cypher, new HashMap<>()));
    }

    public Observable<NewsArticle> findArticlesWithLanguage(String username, String language) {
        String cypher = ArticlesQueries.findArticlesWithLanguage(username, language, LIMIT);
        return Observable.fromIterable(getSession().query(NewsArticle.class, cypher, new HashMap<>()));
    }

    public Observable<NewsArticle> findArticlesWithCountry(String country) {
        String cypher = ArticlesQueries.findArticlesWithCountry(country, LIMIT);
        return Observable.fromIterable(getSession().query(NewsArticle.class, cypher, new HashMap<>()));
    }

    public Observable<NewsArticle> findArticlesWithCountry(String username, String country) {
        String cypher = ArticlesQueries.findArticlesWithCountry(username, country, LIMIT);
        return Observable.fromIterable(getSession().query(NewsArticle.class, cypher, new HashMap<>()));
    }

    public Observable<NewsArticle> findArticlesWithText(String text) {
        String cypher = ArticlesQueries.findArticlesWithText(text, LIMIT);
        return Observable.fromIterable(getSession().query(NewsArticle.class, cypher, new HashMap<>()));
    }
    
    public Observable<NewsArticle> findLatest(String username) {
        String cypher = ArticlesQueries.findLatest(username, LIMIT);
        return Observable.fromIterable(getSession().query(NewsArticle.class, cypher, new HashMap<>()));
    }
    
    public Observable<NewsArticle> findLatest() {
        String cypher = ArticlesQueries.findLatest(LIMIT);
        return Observable.fromIterable(getSession().query(NewsArticle.class, cypher, new HashMap<>()));
    }

    public Observable<NewsArticle> findOldest(String username) {
        String cypher = ArticlesQueries.findOldest(username, LIMIT);
        return Observable.fromIterable(getSession().query(NewsArticle.class, cypher, new HashMap<>()));
    }
    
    public Observable<NewsArticle> findOldest() {
        String cypher = ArticlesQueries.findOldest(LIMIT);
        return Observable.fromIterable(getSession().query(NewsArticle.class, cypher, new HashMap<>()));
    }

    public Observable<NewsArticle> findRead(String username) {
        String cypher = ArticlesQueries.findRead(username, LIMIT);
        return Observable.fromIterable(getSession().query(NewsArticle.class, cypher, new HashMap<>()));
    }
    
    public Observable<NewsArticle> findFavorite(String username) {
        String cypher = ArticlesQueries.findFavorite(username, LIMIT);
        return Observable.fromIterable(getSession().query(NewsArticle.class, cypher, new HashMap<>()));
    }
    public Observable<NewsArticle> findFake(String username) {
        String cypher = ArticlesQueries.findFake(username, LIMIT);
        return Observable.fromIterable(getSession().query(NewsArticle.class, cypher, new HashMap<>()));
    }
}
