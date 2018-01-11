/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it;

import db.news.NewsArticle;
import java.util.Date;
import org.neo4j.ogm.session.Session;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class UpdateNewsArticleNGTestIT extends NGTestIT {

    @Test
    public void update() {
        System.out.println("update()");
        
        String title = " a title";
        NewsArticle article = new NewsArticle();
        article.setTitle(title);

        Session session = getSessionFactory().getNeo4jSession();
        assertEquals(session.countEntitiesOfType(NewsArticle.class), 0);
        
        session.save(article);
        assertEquals(session.countEntitiesOfType(NewsArticle.class), 1);
        assertNotNull(session.load(NewsArticle.class, title));
        
        assertEquals(session.countEntitiesOfType(NewsArticle.class), 1);

        // Performs a title updates
        article.setTitle("another title");
        article.setPublishedAt(new Date());
        session.save(article);
        
        // Check that there's no new article but a simple title update
        assertEquals(session.countEntitiesOfType(NewsArticle.class), 1);
        assertEquals(session.load(NewsArticle.class, title).getTitle(), "another title");
    }
}
