/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v1.backend.services.news;

import v1.backend.services.news.NewsSourceService;
import v1.backend.services.news.NewsArticleService;
import v1.db.auth.FlexUser;
import v1.db.news.NewsArticle;
import v1.db.news.NewsAuthor;
import v1.db.news.NewsSource;
import v1.db.news.Tag;
import v1.it.NGTestIT;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class NewsArticleServiceNGTest extends NGTestIT {

    public NewsArticleServiceNGTest() {
    }

    @Test
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

    @Test
    public void findArticlesTaggedAsForUser() {
        System.out.println("-> findArticlesWithCategory()");

        // Our user
        FlexUser user = new FlexUser("username", "password");
        String username = user.getUsername();
        getSessionFactory().getNeo4jSession().save(user);

        NewsArticle article = new NewsArticle();
        article.setTitle("a title");
        article.setUrl("a url");
        Tag tag = new Tag("TAG");
        article.getTags().add(tag);

        NewsArticleService service = new NewsArticleService();
        service.save(article);
        //assertTrue(service.findArticlesTaggedAs(username, "TAG").iterator().hasNext());
    }

    @Test
    public void findArticlesPublishedBy() {
        System.out.println("-> findArticlesPublishedBy()");

        String url = "a url";
        NewsArticle article = new NewsArticle();
        article.setTitle("a title");
        article.setUrl(url);
        article.setSourceId("sourceId");

        NewsSource publisher = new NewsSource();
        publisher.setSourceId("sourceId");

        NewsAuthor author = new NewsAuthor("Johny");

        publisher.getAuthors().add(author);
        author.getAuthored().add(article);

        NewsSourceService service = new NewsSourceService();
        service.save(publisher);
        assertNotNull(new NewsArticleService().findArticlesPublishedBy("sourceId"));
    }

    @Test
    public void findArticlesPublishedByForUser() {
        System.out.println("-> findArticlesPublishedBy()");

        // Our user
        FlexUser user = new FlexUser("username", "password");
        String username = user.getUsername();
        getSessionFactory().getNeo4jSession().save(user);

        String url = "a url";
        NewsArticle article = new NewsArticle();
        article.setTitle("a title");
        article.setUrl(url);
        article.setSourceId("sourceId");

        NewsSource publisher = new NewsSource();
        publisher.setSourceId("sourceId");

        NewsAuthor author = new NewsAuthor("Johny");

        publisher.getAuthors().add(author);
        author.getAuthored().add(article);

        NewsSourceService service = new NewsSourceService();
        service.save(publisher);
        assertNotNull(new NewsArticleService().findArticlesPublishedBy(username, "sourceId"));
    }

    @Test
    public void findArticleWithLanguage() {
        System.out.println("-> findArticleWithLanguage()");

        String language = "en";

        String title = "A title";
        NewsArticle article = new NewsArticle();
        article.setTitle(title);
        article.setLanguage(language);

        NewsArticleService service = new NewsArticleService();
        service.save(article);
        assertNotNull(service.findArticlesWithLanguage(language));
    }
}
