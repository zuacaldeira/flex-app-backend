/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.services.news;

import services.news.NewsArticleService;
import db.news.NewsArticle;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class NewsArticleServiceNGTest {

    public NewsArticleServiceNGTest() {
    }

    @Test
    @Ignore
    public void findArticleWithTitle() {
        System.out.println("-> findArticleWithTitle()");

        String title = "A title";
        NewsArticle article = new NewsArticle();
        article.setTitle(title);

        NewsArticleService service = new NewsArticleService();
        service.save(article);
        assertNotNull(service.findArticleWithTitle(title));
        assertNotNull(service.find(title));
    }

    @Test
    public void findArticleWithUrl() {
        System.out.println("-> findArticleWithUrl()");

        String url = "a url";
        NewsArticle article = new NewsArticle();
        article.setTitle("a title");
        article.setUrl(url);

        NewsArticleService service = new NewsArticleService();
        service.save(article);
        assertNotNull(service.findArticlesWithUrl(url));
    }
}
