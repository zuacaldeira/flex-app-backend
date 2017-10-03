/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class NewsAuthorTest {
    
    @DataProvider
    public static Object[][] namesProvider() {
        return new Object[][] {
            {new NewsAuthor("name"), "name"},
            {new NewsAuthor(), NewsAuthor.UNKNOWN.getName()}
        };
    }
    
    @DataProvider
    public static Object[][] setNameProvider() {
        return new Object[][] {
            {new NewsAuthor("name"), "name", "Someone"},
            {new NewsAuthor(), NewsAuthor.UNKNOWN.getName(), "Someone"}
        };
    }

    @DataProvider
    public static Object[][] articlesProvider() {
        Set<NewsArticle> articles = new HashSet<>();
        articles.add(new NewsArticle());
        return new Object[][] {
            {new NewsAuthor("name"), articles},
            {new NewsAuthor(), articles}
        };
    }

    @DataProvider
    public static Object[][] setArticlesProvider() {
        Set<NewsArticle> oldArticles = new HashSet<>();
        oldArticles.add(new NewsArticle());
        
        Set<NewsArticle> newArticles = new HashSet<>();
        NewsArticle article = new NewsArticle();
        article.setTitle("I have a title");
        newArticles.add(article);
        
        return new Object[][] {
            {new NewsAuthor("name"), oldArticles, newArticles},
            {new NewsAuthor(), oldArticles, newArticles}
        };
    }
    
    
    @DataProvider
    public static Object[][] compareToProvider() {
        return new Object[][] {
            {new NewsAuthor("name"), new NewsAuthor("name"), 0},
            {new NewsAuthor("name"), new NewsAuthor(), 1},
            {new NewsAuthor(), new NewsAuthor("name"), -1},
            {new NewsAuthor(), new NewsAuthor(), 0}
        };
    }
    
    @DataProvider
    public static Object[][] hashCodeProvider() {
        return new Object[][] {
            {new NewsAuthor("name"), new NewsAuthor("name")},
            {new NewsAuthor(), new NewsAuthor()}
        };
    }

    @DataProvider
    public static Object[][] equalsProvider() {
        NewsAuthor author1 = new NewsAuthor("name");
        NewsAuthor author2 = new NewsAuthor();
        return new Object[][] {
            {author1, author1, true},
            {author2, author2, true},
            {author1, author2, false},
            {author2, author1, false},
            {author1, null, false},
            {author1, "Oh Yeah!", false}
            
        };
    }

    @DataProvider
    public static Object[][] toStringProvider() {
        return new Object[][] {
            {new NewsAuthor("name"), "name"},
            {new NewsAuthor(), new NewsAuthor().getName()}
        };
    }
    
    @DataProvider
    public static Object[][] addArticleProvider() {
        return new Object[][] {
            {new NewsAuthor("name"), new NewsArticle()}
        };
    }

    @DataProvider
    public static Object[][] getSourceProvider() {
        NewsAuthor author = new NewsAuthor("name");
        NewsSource source = new NewsSource();
        author.setSource(source);
        
        return new Object[][] {
            {author, source}
        };
    }

    @DataProvider
    public static Object[][] setSourceProvider() {
        NewsSource oldSource = new NewsSource();
        NewsAuthor author = new NewsAuthor("name");
        author.setSource(oldSource);
        
        NewsSource newSource = new NewsSource();
        newSource.setName("new");
        
        return new Object[][] {
            {author, oldSource, newSource}
        };
    }

    @DataProvider
    public static Object[][] getUrlProvider() {
        NewsAuthor author = new NewsAuthor("name");
        author.setUrl("url");
        
        return new Object[][] {
            {author, "url"}
        };
    }

    @DataProvider
    public static Object[][] setUrlProvider() {
        String newUrl = "newUrl";
        String oldUrl = "oldUrl";
        
        NewsAuthor author = new NewsAuthor("name");
        author.setUrl(oldUrl);

        return new Object[][] {
            {author, oldUrl, newUrl}
        };
    }

    @DataProvider
    public static Object[][] hasUrlProvider() {
        NewsAuthor author = new NewsAuthor("name");
        author.setUrl("url");
        
        return new Object[][] {
            {author, true},
            {new NewsAuthor(), false}
        };
    }

    public NewsAuthorTest() {
    }

    
    /**
     * Test of getName method, of class NewsAuthor.
     * @param author An author.
     * @param expected It's expected name.
     */
    @Test
    @UseDataProvider("namesProvider")
    public void testGetName(NewsAuthor author, String expected) {
        System.out.println("getName");
        assertEquals(expected, author.getName());
    }

    /**
     * Test of setName method, of class NewsAuthor.
     */
    @Test
    @UseDataProvider("setNameProvider")
    public void testSetName(NewsAuthor author, String oldName, String newName) {
        System.out.println("setName");
        assertEquals(oldName, author.getName());
        author.setName(newName);
        assertEquals(newName, author.getName());
    }

    /**
     * Test of getArticles method, of class NewsAuthor.
     */
    @Test
    @UseDataProvider("articlesProvider")
    public void testGetArticles(NewsAuthor author, Set<NewsArticle> articles) {
        System.out.println("getArticles");
        assertTrue(articles.containsAll(author.getArticles()));
    }

    /**
     * Test of setArticles method, of class NewsAuthor.
     */
    @Test
    @UseDataProvider("setArticlesProvider")
    public void testSetArticles(NewsAuthor author, Set<NewsArticle> oldArticles, Set<NewsArticle> newArticles) {
        System.out.println("setArticles");
        assertTrue(oldArticles.containsAll(author.getArticles()));
        author.setArticles(newArticles);
        assertTrue(newArticles.containsAll(author.getArticles()));
    }

    /**
     * Test of compareTo method, of class NewsAuthor.
     */
    @Test
    @UseDataProvider("compareToProvider")
    public void testCompareTo(NewsAuthor author, NewsAuthor other, int expected) {
        System.out.println("compareTo");
        assertEquals(expected, author.compareTo(other));
    }

    /**
     * Test of hashCode method, of class NewsAuthor.
     */
    @Test
    @UseDataProvider("hashCodeProvider")
    public void testHashCode(NewsAuthor author, NewsAuthor other) {
        System.out.println("hashCode");
        assertEquals(author.hashCode(), other.hashCode());
    }

    /**
     * Test of equals method, of class NewsAuthor.
     */
    @Test
    @UseDataProvider("equalsProvider")
    public void testEquals(NewsAuthor author, Object other, boolean expected) {
        System.out.println("equals");
        assertEquals(expected, author.equals(other));
    }

    /**
     * Test of toString method, of class NewsAuthor.
     */
    @Test
    @UseDataProvider("toStringProvider")
    public void testToString(NewsAuthor author, String toString) {
        System.out.println("toString");
        assertEquals(toString, author.toString());
    }

    /**
     * Test of addArticle method, of class NewsAuthor.
     */
    @Test
    @UseDataProvider("addArticleProvider")
    public void testAddArticle(NewsAuthor author, NewsArticle article) {
        System.out.println("addArticle");
        assertTrue(author.getArticles().isEmpty());
        author.addArticle(article);
        assertFalse(author.getArticles().isEmpty());
    }

    /**
     * Test of setSource method, of class NewsAuthor.
     */
    @Test
    @UseDataProvider("setSourceProvider")
    public void testSetSource(NewsAuthor author, NewsSource oldSource, NewsSource newSource) {
        System.out.println("setSource");
        assertEquals(oldSource, author.getSource());
        author.setSource(newSource);
        assertEquals(newSource, author.getSource());
    }

    /**
     * Test of getSource method, of class NewsAuthor.
     */
    @Test
    @UseDataProvider("getSourceProvider")
    public void testGetSource(NewsAuthor author, NewsSource source) {
        System.out.println("getSource");
        assertEquals(source, author.getSource());
    }

    /**
     * Test of getPropertyName method, of class NewsAuthor.
     */
    @Test
    public void testGetPropertyName() {
        System.out.println("getPropertyName");
        assertEquals("name", new NewsAuthor().getPropertyName());
    }

    /**
     * Test of getPropertyValue method, of class NewsAuthor.
     */
    @Test
    public void testGetPropertyValue() {
        System.out.println("getPropertyValue");
        assertEquals("My Name", new NewsAuthor("My Name").getPropertyValue());
    }

    /**
     * Test of setUrl method, of class NewsAuthor.
     */
    @Test
    @UseDataProvider("setUrlProvider")
    public void testSetUrl(NewsAuthor author, String oldUrl, String newUrl) {
        System.out.println("setUrl");
        assertEquals(oldUrl, author.getUrl());
        author.setUrl(newUrl);
        assertEquals(newUrl, author.getUrl());
    }

    /**
     * Test of getUrl method, of class NewsAuthor.
     */
    @Test
    @UseDataProvider("getUrlProvider")
    public void testGetUrl(NewsAuthor author, String expected) {
        System.out.println("getUrl");
        assertEquals(expected, author.getUrl());
    }

    /**
     * Test of hasUrl method, of class NewsAuthor.
     */
    @Test
    @UseDataProvider("hasUrlProvider")
    public void testHasUrl(NewsAuthor author, boolean expected) {
        System.out.println("hasUrl");
        assertEquals(expected, author.hasUrl());
    }
    
}
