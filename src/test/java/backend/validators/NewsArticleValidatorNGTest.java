/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.validators;

import validators.NewsArticleValidator;
import db.news.NewsArticle;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class NewsArticleValidatorNGTest {
    
    public NewsArticleValidatorNGTest() {
    }

    /**
     * Test of isValid method, of class NewsArticleValidator.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        
        NewsArticle entity = new NewsArticle();
        entity.setTitle("A Title");
        entity.setUrl("A Url");
        
        NewsArticleValidator instance = new NewsArticleValidator();
        assertEquals(instance.isValid(entity), true);
    }

    /**
     * Test of isValid method for an article without title.
     */
    @Test
    public void testIsInvalidBecauseOfMissingTitle() {
        System.out.println("testIsInvalidBecauseOfMissingTitle()");
        
        NewsArticle entity = new NewsArticle();
        entity.setUrl("A Url");
        
        NewsArticleValidator instance = new NewsArticleValidator();
        assertEquals(instance.isValid(entity), false);
    }


    /**
     * Test of isValid method for an article without title.
     */
    @Test
    public void testIsInvalidBecauseOfMissingUrl() {
        System.out.println("testIsInvalidBecauseOfMissingUrl()");
        
        NewsArticle entity = new NewsArticle();
        entity.setTitle("A Title");
        
        NewsArticleValidator instance = new NewsArticleValidator();
        assertEquals(instance.isValid(entity), false);
    }

    
}
