/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it;

import db.news.NewsArticle;
import java.util.Date;
import java.util.HashMap;
import org.neo4j.ogm.session.Session;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;
import queries.ArticlesQueries;

/**
 *
 * @author zua
 */
public class ReadNewsArticleNGTestIT extends NGTestIT {

    public ReadNewsArticleNGTestIT() {
    }

    private void initScenario(String title, String url, Date publishedAt) {
        // Stores an article with title
        NewsArticle article = new NewsArticle();
        article.setTitle(title);
        article.setUrl(url);
        article.setPublishedAt(publishedAt);

        // Uses session factory to create a session and to save the article
        Session session = getSessionFactory().getNeo4jSession();
        session.save(article);
    }

    @Test
    public void readWithTitle() {
        System.out.println("readWithTitle()");

        String title = "a title";
        String url = "a url";
        initScenario(title, url, new Date());
        assertNotNull(getSessionFactory().getNeo4jSession().load(NewsArticle.class, title));
    }

    @Test
    public void readWithUrl() {
        System.out.println("readWithUrl()");

        String title = "a title";
        String url = "a url";
        initScenario(title, url, new Date());
        String cypher = ArticlesQueries.findArticleWithUrl(url);
        NewsArticle dbArticle = getSessionFactory().getNeo4jSession().queryForObject(NewsArticle.class, cypher, new HashMap<>());
        assertEquals(dbArticle.getUrl(), url);
    }

    @Test
    public void readWithPublishedAt() {
        String title = "a title";
        String url = "a url";
        Date publishedAt = new Date();
        initScenario(title, url, publishedAt);
        NewsArticle dbArticle = getSessionFactory().getNeo4jSession().load(NewsArticle.class, title);
        assertEquals(dbArticle.getUrl(), url);
    }

    @Test
    public void readPublishedBefore() {

    }

    @Test
    public void readPublishedAfter() {

    }

    @Test
    public void readPublishedBetween() {

    }

    @Test
    public void readWithAuthor() {

    }

    @Test
    public void readWithPublisher() {

    }

    @Test
    public void readWithTag() {

    }

    @Test
    public void readWithOpinionRead() {

    }

    @Test
    public void readWithOpinionFavorite() {

    }

    @Test
    public void readWithOpinionFake() {

    }

}
