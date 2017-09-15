/*
 * NewsArticleo change this license header, choose License Headers in Project Properties.
 * NewsArticleo change this template file, choose NewsArticleools | NewsArticleemplates
 * and open the template in the editor.
 */
package services.news;

import db.news.NewsArticle;
import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author zua
 */
@Remote
public interface NewsArticleServiceInterface  extends DBService<NewsArticle> {
    public List<NewsArticle> findArticlesWithCategory(String username, String category);
    public List<NewsArticle> findArticlesWithSource(String username, String publisherName);
    
    public Collection<NewsArticle> findArticlesWithText(String username, String value);
    public Collection<NewsArticle> findArticlesWithLanguage(String username, String value);
    public Collection<NewsArticle> findArticlesWithCountry(String username, String value);
    
    public Collection<NewsArticle> findAllRead(String username);
    public Collection<NewsArticle> findAllFavorite(String username);
    public Collection<NewsArticle> findAllFake(String username);
    
    public Collection<NewsArticle> findAllRead(String username, int limit);
    public Collection<NewsArticle> findAllFavorite(String username, int limit);
    public Collection<NewsArticle> findAllFake(String username, int limit);
    
    public Collection<NewsArticle> findLatest();
    public Collection<NewsArticle> findOldest();

    public Collection<NewsArticle> findLatest(int limit);
    public Collection<NewsArticle> findOldest(int limit);

    public Collection<NewsArticle> findLatest(String username);
    public Collection<NewsArticle> findOldest(String username);
    
    public Collection<NewsArticle> findLatest(String username, int limit);
    public Collection<NewsArticle> findOldest(String username, int limit);
   

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
