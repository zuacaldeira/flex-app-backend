/*
 * NewsArticleo change this license header, choose License Headers in Project Properties.
 * NewsArticleo change this template file, choose NewsArticleools | NewsArticleemplates
 * and open the template in the editor.
 */
package services;

import db.NewsArticle;
import io.reactivex.Observable;
import javax.ejb.Remote;

/**
 *
 * @author zua
 */
@Remote
public interface NewsArticleServiceInterface  extends DBService<NewsArticle> {
    public NewsArticle findArticleWithTitle(String title);
    public Observable<NewsArticle> findAllArticles();

    public Observable<NewsArticle> findArticlesWithText(String search);
    public Observable<NewsArticle> findArticlesWithText(String username, String search);

    public Observable<NewsArticle> findArticlesWithCategory(String category);
    public Observable<NewsArticle> findArticlesWithCategory(String username, String category);
    
    public Observable<NewsArticle> findArticlesWithSource(String sourceId);
    public Observable<NewsArticle> findArticlesWithSource(String username, String sourceId);
    
    public Observable<NewsArticle> findArticlesWithLanguage(String language);
    public Observable<NewsArticle> findArticlesWithLanguage(String username, String language);
    
    public Observable<NewsArticle> findArticlesWithCountry(String value);
    public Observable<NewsArticle> findArticlesWithCountry(String username, String value);

    public Observable<NewsArticle> findArticlesWithoutShortUrl();
    
    public Observable<NewsArticle> findAllRead(String username);
    public Observable<NewsArticle> findAllFavorite(String username);
    public Observable<NewsArticle> findAllFake(String username);
    
    public Observable<NewsArticle> findLatest();
    public Observable<NewsArticle> findLatest(String username);

    public Observable<NewsArticle> findOldest();
    public Observable<NewsArticle> findOldest(String username);
    
    public boolean isRead(String username, NewsArticle entity);
    public boolean isFavorite(String username, NewsArticle entity);
    public boolean isFake(String username, NewsArticle entity);

    public void markAsRead(String username, NewsArticle entity);
    public void markAsFavorite(String username, NewsArticle entity);
    public void markAsFake(String username, NewsArticle entity);

    public void removeMarkAsRead(String username, NewsArticle entity);
    public void removeMarkAsFavorite(String username, NewsArticle entity);
    public void removeMarkAsFake(String username, NewsArticle entity);

    public Observable<NewsArticle> findAll(int page, int pageSize);

  }
