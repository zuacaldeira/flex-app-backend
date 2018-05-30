/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v1.backend.queries;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class ArticlesQueriesNGTest {

    public ArticlesQueriesNGTest() {
    }

    /**
     * Test of findArticleWithTitle method, of class ArticlesQueries.
     */
    @Test
    public void testFindArticleWithTitle() {
        System.out.println("findArticleWithTitle()");
        String title = "A Title";
        String expResult = "MATCH (n:NewsArticle) WHERE n.title='A Title' RETURN n";
        String result = ArticlesQueries.findArticleWithTitle(title);
        assertTrue(result.startsWith(expResult));
    }

    /**
     * Test of findArticleWithUrl method, to read the article with the given
     * url.
     */
    @Test
    public void testFindArticleWithUrl() {
        System.out.println("testFindArticleWithUrl()");
        String url = "a url";
        String expResult = "MATCH (n:NewsArticle) WHERE n.url='a url' RETURN n";
        String result = ArticlesQueries.findArticleWithUrl(url);
        assertTrue(result.startsWith(expResult));
    }

}
