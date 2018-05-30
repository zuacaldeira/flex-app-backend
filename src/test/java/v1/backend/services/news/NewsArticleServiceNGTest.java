/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v1.backend.services.news;

import v1.db.news.NewsArticle;
import v1.db.news.Tag;
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

    @Test
    public void findArticlesTaggedAs() {
        System.out.println("-> findArticlesTaggedAs()");

        String url = "a url";
        NewsArticle article = new NewsArticle();
        article.setTitle("a title");
        article.setUrl(url);

        Tag tag = new Tag("TAG");
        article.getTags().add(tag);
        NewsArticleService service = new NewsArticleService();
        service.save(article);
        //assertTrue(service.findArticlesTaggedAs("TAG").iterator().hasNext());
    }


}
