/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v1.db.news;

import v1.db.news.NewsArticle;
import java.util.Date;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class NewsArticleNGTest {
    
    public NewsArticleNGTest() {
    }

    /**
     * Test of getTitle method, of class NewsArticle.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");

        NewsArticle instance = new NewsArticle();
        assertEquals(instance.getTitle(), null);
        
        instance.setTitle("a title");
        assertEquals(instance.getTitle(), "a title");
    }

    /**
     * Test of getDescription method, of class NewsArticle.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");

        NewsArticle instance = new NewsArticle();
        assertEquals(instance.getDescription(), null);
        
        instance.setDescription("a description");
        assertEquals(instance.getDescription(), "a description");
    }

    /**
     * Test of getUrl method, of class NewsArticle.
     */
    @Test
    public void testGetUrl() {
        System.out.println("getUrl");

        NewsArticle instance = new NewsArticle();
        assertEquals(instance.getUrl(), null);
        
        instance.setUrl("a url");
        assertEquals(instance.getUrl(), "a url");
    }

    /**
     * Test of getImageUrl method, of class NewsArticle.
     */
    @Test
    public void testGetImageUrl() {
        System.out.println("getImageUrl");

        NewsArticle instance = new NewsArticle();
        assertEquals(instance.getImageUrl(), null);
        
        instance.setImageUrl("a url");
        assertEquals(instance.getImageUrl(), "a url");
    }

    /**
     * Test of getPublishedAt method, of class NewsArticle.
     */
    @Test
    public void testGetPublishedAt() {
        System.out.println("getPublishedAt");

        NewsArticle instance = new NewsArticle();
        assertEquals(instance.getPublishedAt(), null);
        
        Date date = new Date();
        instance.setPublishedAt(date);
        assertEquals(instance.getPublishedAt(), date);
    }

    /**
     * Test of setTitle method, of class NewsArticle.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        testGetTitle();
    }

    /**
     * Test of setDescription method, of class NewsArticle.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        testGetDescription();
    }

    /**
     * Test of setUrl method, of class NewsArticle.
     */
    @Test
    public void testSetUrl() {
        System.out.println("setUrl");
        testGetUrl();
    }

    /**
     * Test of setImageUrl method, of class NewsArticle.
     */
    @Test
    public void testSetImageUrl() {
        System.out.println("setImageUrl");
        testGetImageUrl();
    }

    /**
     * Test of setPublishedAt method, of class NewsArticle.
     */
    @Test
    public void testSetPublishedAt() {
        System.out.println("setPublishedAt");
        testGetPublishedAt();
    }

    /**
     * Test of getSource method, of class NewsArticle.
     */
    @Test
    public void testGetSourceId() {
        System.out.println("getSourceId");
        testGetDescription();
    }

}
