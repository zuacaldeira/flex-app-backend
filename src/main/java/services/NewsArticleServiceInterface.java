/*
 * NewsArticleo change this license header, choose License Headers in Project Properties.
 * NewsArticleo change this template file, choose NewsArticleools | NewsArticleemplates
 * and open the template in the editor.
 */
package services;

import db.NewsArticle;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author zua
 */
@Remote
public interface NewsArticleServiceInterface  extends DBService<NewsArticle> {
    public NewsArticle findArticleWithTitle(String title);
    public Collection<NewsArticle> findAllArticles();

    public Collection<NewsArticle> findArticlesWithText(String search);
    public Collection<NewsArticle> findArticlesWithText(String username, String search);

    public Collection<NewsArticle> findArticlesWithCategory(String category);
    public Collection<NewsArticle> findArticlesWithCategory(String username, String category);
    
    public Collection<NewsArticle> findArticlesWithSource(String sourceId);
    public Collection<NewsArticle> findArticlesWithSource(String username, String sourceId);
    
    public Collection<NewsArticle> findArticlesWithLanguage(String language);
    public Collection<NewsArticle> findArticlesWithLanguage(String username, String language);
    
    public Collection<NewsArticle> findArticlesWithCountry(String value);
    public Collection<NewsArticle> findArticlesWithCountry(String username, String value);
    
    public Collection<NewsArticle> findAllRead(String username);
    public Collection<NewsArticle> findAllFavorite(String username);
    public Collection<NewsArticle> findAllFake(String username);
    
    public Collection<NewsArticle> findLatest();
    public Collection<NewsArticle> findLatest(String username);

    public Collection<NewsArticle> findOldest();
    public Collection<NewsArticle> findOldest(String username);
    
    public boolean isRead(String username, NewsArticle entity);
    public boolean isFavorite(String username, NewsArticle entity);
    public boolean isFake(String username, NewsArticle entity);

    public void markAsRead(String username, NewsArticle entity);
    public void markAsFavorite(String username, NewsArticle entity);
    public void markAsFake(String username, NewsArticle entity);

    public void removeMarkAsRead(String username, NewsArticle entity);
    public void removeMarkAsFavorite(String username, NewsArticle entity);
    public void removeMarkAsFake(String username, NewsArticle entity);

  }
