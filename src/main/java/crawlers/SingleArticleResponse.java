/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import db.NewsArticle;
import db.NewsAuthor;
import db.NewsSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import services.NewsServiceException;
import utils.DateUtils;

/**
 *
 * @author zua
 */
public class SingleArticleResponse {
    
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public NewsArticle convert2NewsArticle(NewsSource source) {
        String sourceId = source.getSourceId();
        String language = source.getLanguage();
        String country = source.getCountry();
        String normalizedTimeString = DateUtils.getInstance().normalizeTime(publishedAt, language);
        Date date = DateUtils.getInstance().parseDate(normalizedTimeString, language);
        
        NewsArticle article = new NewsArticle(title, description, url, urlToImage, date, sourceId, language, country);
        NewsAuthor auth = new NewsAuthor(this.author);
        
        source.getCorrespondents().add(auth);
        article.getAuthors().add(auth);
        
        return article;
    }

}
