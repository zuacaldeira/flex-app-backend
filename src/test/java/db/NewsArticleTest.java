/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class NewsArticleTest {
    
    public NewsArticleTest() {
    }

    /**
     * Test of getTitle method, of class NewsArticle.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        NewsArticle article = new NewsArticle();
        assertNull(article.getTitle());
        article.setTitle("title");
        assertNotNull(article.getTitle());
    }

    /**
     * Test of getDescription method, of class NewsArticle.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        NewsArticle article = new NewsArticle();
        assertNull(article.getDescription());
        article.setDescription("description");
        assertNotNull(article.getDescription());
    }

    /**
     * Test of getUrl method, of class NewsArticle.
     */
    @Test
    public void testGetUrl() {
        System.out.println("getUrl");
        NewsArticle article = new NewsArticle();
        assertNull(article.getUrl());
        article.setUrl("url");
        assertNotNull(article.getUrl());
    }

    /**
     * Test of getImageUrl method, of class NewsArticle.
     */
    @Test
    public void testGetImageUrl() {
        System.out.println("getImageUrl");
        NewsArticle article = new NewsArticle();
        assertNull(article.getImageUrl());
        article.setImageUrl("imageUrl");
        assertNotNull(article.getImageUrl());
    }

    /**
     * Test of getPublishedAt method, of class NewsArticle.
     */
    @Test
    public void testGetPublishedAt() {
        System.out.println("getPublishedAt");
        NewsArticle article = new NewsArticle();
        assertNull(article.getPublishedAt());
        article.setPublishedAt(new Date());
        assertNotNull(article.getPublishedAt());
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
     * Test of getSourceId method, of class NewsArticle.
     */
    @Test
    public void testGetSourceId() {
        System.out.println("getSourceId");
        NewsArticle article = new NewsArticle();
        assertNull(article.getSourceId());
        article.setSourceId("sourceId");
        assertNotNull(article.getSourceId());
    }

    /**
     * Test of setSourceId method, of class NewsArticle.
     */
    @Test
    public void testSetSourceId() {
        System.out.println("setSourceId");
        testGetSourceId();
    }

    /**
     * Test of getAuthors method, of class NewsArticle.
     */
    @Test
    public void testGetAuthors() {
        System.out.println("getAuthors");
        NewsArticle article = new NewsArticle();
        assertTrue(article.getAuthors().isEmpty());
        article.getAuthors().add(NewsAuthor.UNKNOWN);
        assertFalse(article.getAuthors().isEmpty());
    }

    /**
     * Test of setAuthors method, of class NewsArticle.
     */
    @Test
    public void testSetAuthors() {
        System.out.println("setAuthors");
        testGetAuthors();
    }

    /**
     * Test of getLanguage method, of class NewsArticle.
     */
    @Test
    public void testGetLanguage() {
        System.out.println("getLanguage");
        NewsArticle article = new NewsArticle();
        assertNull(article.getLanguage());
        article.setLanguage("lang");
        assertNotNull(article.getLanguage());
    }

    /**
     * Test of setLanguage method, of class NewsArticle.
     */
    @Test
    public void testSetLanguage() {
        System.out.println("setLanguage");
        testGetLanguage();
    }

    /**
     * Test of getCountry method, of class NewsArticle.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        NewsArticle article = new NewsArticle();
        assertNull(article.getCountry());
        article.setCountry("country");
        assertNotNull(article.getCountry());
    }

    /**
     * Test of setCountry method, of class NewsArticle.
     */
    @Test
    public void testSetCountry() {
        System.out.println("setCountry");
        testGetCountry();
    }

    /**
     * Test of getCategories method, of class NewsArticle.
     */
    @Test
    public void testGetCategories() {
        System.out.println("getCategories");
        NewsArticle article = new NewsArticle();
        assertTrue(article.getCategories().isEmpty());
        article.addCategory("cat");
        assertFalse(article.getCategories().isEmpty());
    }

    /**
     * Test of setCategories method, of class NewsArticle.
     */
    @Test
    public void testSetCategories() {
        System.out.println("setCategories");
        testGetCategories();
    }

    @DataProvider
    public static Object[][] hashCodeProvider() {
        NewsArticle article = new NewsArticle();
        article.setTitle("T");
        
        NewsArticle other = new NewsArticle();
        other.setTitle("T");
        
        return new Object[][]{
            {new NewsArticle(), new NewsArticle()},
            {article, other}
        };
        
    }
    /**
     * Test of hashCode method, of class NewsArticle.
     */
    @Test
    @UseDataProvider("hashCodeProvider")
    public void testHashCode(NewsArticle article, NewsArticle other) {
        System.out.println("hashCode");
        assertEquals(other.hashCode(), article.hashCode());
    }

    @DataProvider
    public static Object[][] equalsProvider() {
        NewsArticle article = new NewsArticle();
        article.setTitle("T1");
        
        NewsArticle other = new NewsArticle();
        other.setTitle("T2");
        
        return new Object[][]{
            {article, article, true},
            {other, other, true},
            {article, other, false},
            {other, article, false},
            {article, null, false},
            {article, "Art", false}
        };
        
    }
    /**
     * Test of equals method, of class NewsArticle.
     */
    @Test
    @UseDataProvider("equalsProvider")
    public void testEquals(NewsArticle article, Object other, boolean expected) {
        System.out.println("equals");
        assertEquals(expected, article.equals(other));
    }

    /**
     * Test of toString method, of class NewsArticle.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        NewsArticle article = new NewsArticle();
        article.setTitle("title");
        assertEquals("title", article.toString());
    }

    @DataProvider
    public static Object[][] compareToProvider() {
        NewsArticle article = new NewsArticle();
        article.setTitle("T");
        
        NewsArticle other = new NewsArticle();
        other.setTitle("T1");
        
        NewsArticle another = new NewsArticle();
        another.setTitle("T2");
        
        return new Object[][]{
            {new NewsArticle(), new NewsArticle(), 0},
            {article, other, -1},
            {article, new NewsArticle(), -1},
            {new NewsArticle(), article, 1},
            {other, another, -1}
        };
        
    }

    /**
     * Test of compareTo method, of class NewsArticle.
     */
    @Test
    @UseDataProvider("compareToProvider")
    public void testCompareTo(NewsArticle article, NewsArticle other, int expected) {
        System.out.println("compareTo");
        assertEquals(expected, article.compareTo(other));
    }

    /**
     * Test of getPropertyName method, of class NewsArticle.
     */
    @Test
    public void testGetPropertyName() {
        System.out.println("getPropertyName");
        assertEquals("title", new NewsArticle().getPropertyName());
    }

    /**
     * Test of getPropertyValue method, of class NewsArticle.
     */
    @Test
    public void testGetPropertyValue() {
        System.out.println("getPropertyValue");
        NewsArticle article = new NewsArticle();
        assertEquals(null, article.getPropertyValue());
        article.setTitle("title");
        assertEquals("title", article.getPropertyValue());
    }

    /**
     * Test of hasUrl method, of class NewsArticle.
     */
    @Test
    public void testHasUrl() {
        System.out.println("hasUrl");
        NewsArticle instance = new NewsArticle();
        assertFalse(instance.hasUrl());
    }

    /**
     * Test of addCategory method, of class NewsArticle.
     */
    @Test
    public void testAddCategory() {
        System.out.println("addCategory");
        String category = "cat";
        NewsArticle instance = new NewsArticle();
        assertTrue(instance.getCategories().isEmpty());
        instance.addCategory(category);
        assertFalse(instance.getCategories().isEmpty());
    }
    
}
