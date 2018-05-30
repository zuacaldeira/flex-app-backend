/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v1.it;

import v1.db.news.NewsArticle;
import org.neo4j.ogm.session.Session;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class CreateNewsArticleNGTestIT extends NGTestIT {

    @Test
    public void create() {
        System.out.println("create()");        
        String title = " a title";
        NewsArticle article = new NewsArticle();
        article.setTitle(title);

        Session session = getSessionFactory().getNeo4jSession();
        assertEquals(session.countEntitiesOfType(NewsArticle.class), 0);
        
        session.save(article);

        assertEquals(session.countEntitiesOfType(NewsArticle.class), 1);
        assertNotNull(session.load(NewsArticle.class, title));
    }

    @Test(expectedExceptions = org.neo4j.ogm.exception.CypherException.class)
    public void createArticleWithoutTitle() {
        System.out.println("createArticleWithoutTitle()");
        
        NewsArticle article = new NewsArticle();
        getSessionFactory().getNeo4jSession().save(article);
    }
}
