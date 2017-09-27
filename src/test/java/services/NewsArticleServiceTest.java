/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import db.FlexUser;
import db.Neo4jSessionFactory;
import db.NewsArticle;
import java.util.Date;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class NewsArticleServiceTest extends Neo4jTest {

    private static String TEST_USERNAME = "test:username";
    private static String TEST_PASSWORD = "test:password";

    public NewsArticleServiceTest() {
        super();
    }

    @DataProvider
    public static Object[][] articlesData() {
        return new Object[][]{
            {new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country".toUpperCase())}
        };
    }
    @DataProvider
    public static Object[][] searchData() {
        return new Object[][]{
            {new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country".toUpperCase()), "title"},
            {new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country".toUpperCase()), "description"}
        };
    }

    @DataProvider
    public static Object[][] searchDataForUser() {
        return new Object[][]{
            {new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country".toUpperCase()), TEST_USERNAME, "title"},
            {new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country".toUpperCase()), TEST_USERNAME, "description"}
        };
    }

    @DataProvider
    public static Object[][] negativeSearchDataForUser() {
        return new Object[][]{
            {new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country".toUpperCase()), null, "title"},
            {new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country".toUpperCase()), "", "description"}
        };
    }

    @DataProvider
    public static Object[][] negativeSearchData() {
        return new Object[][]{
            {new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country".toUpperCase()), null},
            {new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country".toUpperCase()), ""}
        };
    }
    
    @DataProvider
    public static Object[][] searchByCategoryData() {
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country".toUpperCase());
        article.addCategory("category");
        
        return new Object[][]{
            {article, "category"}
        };
    }

    @DataProvider
    public static Object[][] searchBySourceData() {
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country".toUpperCase());
        article.setSourceId("sourceId");
        
        return new Object[][]{
            {article, "sourceId"}
        };
    }

    @DataProvider
    public static Object[][] searchByLanguageData() {
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country".toUpperCase());
        article.setSourceId("sourceId");
        
        return new Object[][]{
            {article, "language"}
        };
    }

    @DataProvider
    public static Object[][] searchByCountryData() {
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country".toUpperCase());
        return new Object[][]{
            {article, "country"}
        };
    }

    @DataProvider
    public static Object[][] readData() {
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country".toUpperCase());
        return new Object[][]{
            {article}
        };
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFindAllArticles(NewsArticle article) {
        System.out.println("\ttestFindAllUsers");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAllArticles().isEmpty());
        service.save(article);
        assertFalse(service.findAllArticles().isEmpty());
    }
    
    @Test
    @UseDataProvider("searchData")
    public void testArticleWithText(NewsArticle article, String searchString) {
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findArticlesWithText(searchString).isEmpty());
        service.save(article);
        assertFalse(service.findArticlesWithText(searchString).isEmpty());
    }
    
    @Test(expected = NewsServiceException.class)
    @UseDataProvider("negativeSearchData")
    public void testArticleWithTextShouldFail(NewsArticle article, String searchString) {
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findArticlesWithText(searchString).isEmpty());
        service.save(article);
        assertFalse(service.findArticlesWithText(searchString).isEmpty());
    }


    @Test
    @UseDataProvider("searchDataForUser")
    public void testArticleWithTextForUser(NewsArticle article, String username, String searchString) {
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findArticlesWithText(username, searchString).isEmpty());
        service.save(article);
        assertFalse(service.findAllArticles().isEmpty());
        assertTrue(service.findArticlesWithText(username, searchString).isEmpty());
    }
 
    @Test(expected=NewsServiceException.class)
    @UseDataProvider("negativeSearchDataForUser")
    public void testArticleWithTextForUserShouldFail(NewsArticle article, String username, String searchString) {
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findArticlesWithText(username, searchString).isEmpty());
        service.save(article);
        assertFalse(service.findAllArticles().isEmpty());
        assertTrue(service.findArticlesWithText(username, searchString).isEmpty());
    }

    @Test
    @UseDataProvider("searchByCategoryData")
    public void testArticleWithCategory(NewsArticle article, String category) {
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findArticlesWithCategory(category).isEmpty());
        service.save(article);
        assertFalse(service.findAllArticles().isEmpty());
        assertTrue(service.findArticlesWithCategory(category).isEmpty());
    }

    @Test
    @UseDataProvider("searchByCategoryData")
    public void testArticleWithCategoryForUser(NewsArticle article, String category) {
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findArticlesWithCategory(TEST_USERNAME, category).isEmpty());
        service.save(article);
        assertFalse(service.findAllArticles().isEmpty());
        assertTrue(service.findArticlesWithCategory(TEST_USERNAME, category).isEmpty());
    }

    @Test
    @UseDataProvider("searchBySourceData")
    public void testArticleWithSource(NewsArticle article, String source) {
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findArticlesWithSource(source).isEmpty());
        service.save(article);
        assertFalse(service.findAllArticles().isEmpty());
        assertFalse(service.findArticlesWithSource(source).isEmpty());
    }

    @Test
    @UseDataProvider("searchBySourceData")
    public void testArticleWithSourceForUser(NewsArticle article, String source) {
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findArticlesWithSource(TEST_USERNAME, source).isEmpty());
        service.save(article);
        assertFalse(service.findAllArticles().isEmpty());
        assertTrue(service.findArticlesWithSource(TEST_USERNAME, source).isEmpty());
    }

    @Test
    @UseDataProvider("searchByLanguageData")
    public void testArticleWithLanguage(NewsArticle article, String language) {
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findArticlesWithLanguage(language).isEmpty());
        service.save(article);
        assertFalse(service.findAllArticles().isEmpty());
        assertFalse(service.findArticlesWithLanguage(language).isEmpty());
    }

    @Test
    @UseDataProvider("searchByLanguageData")
    public void testArticleWithLanguageForUser(NewsArticle article, String language) {
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findArticlesWithLanguage(TEST_USERNAME, language).isEmpty());
        service.save(article);
        assertFalse(service.findAllArticles().isEmpty());
        assertTrue(service.findArticlesWithLanguage(TEST_USERNAME, language).isEmpty());
    }

    @Test
    @UseDataProvider("searchByCountryData")
    public void testArticleWithCountry(NewsArticle article, String country) {
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findArticlesWithCountry(country.toUpperCase()).isEmpty());
        service.save(article);
        assertFalse(service.findAllArticles().isEmpty());
        assertFalse(service.findArticlesWithCountry(country.toUpperCase()).isEmpty());
    }

    @Test
    @UseDataProvider("searchByCountryData")
    public void testArticleWithCountryForUser(NewsArticle article, String country) {
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findArticlesWithCountry(TEST_USERNAME, country).isEmpty());
        service.save(article);
        assertFalse(service.findAllArticles().isEmpty());
        assertTrue(service.findArticlesWithCountry(TEST_USERNAME, country).isEmpty());
    }
    
    
    @Test
    @UseDataProvider("readData")
    public void testArticleRead(NewsArticle article) {
        NewsArticleService service = new NewsArticleService();

        assertTrue(service.findAllRead(TEST_USERNAME).isEmpty());
        
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        user.read(article);        
        Neo4jSessionFactory.getInstance().getNeo4jSession().save(user);
        service.save(article);
        
        assertFalse(service.findAllArticles().isEmpty());
        assertFalse(service.findAllRead(TEST_USERNAME).isEmpty());
    }

    @Test
    @UseDataProvider("readData")
    public void testArticleFavorite(NewsArticle article) {
        NewsArticleService service = new NewsArticleService();

        assertTrue(service.findAllFavorite(TEST_USERNAME).isEmpty());
        
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        user.favorite(article);        
        Neo4jSessionFactory.getInstance().getNeo4jSession().save(user);
        service.save(article);
        
        assertFalse(service.findAllArticles().isEmpty());
        assertFalse(service.findAllFavorite(TEST_USERNAME).isEmpty());
    }

    @Test
    @UseDataProvider("readData")
    public void testArticleFake(NewsArticle article) {
        NewsArticleService service = new NewsArticleService();

        assertTrue(service.findAllFake(TEST_USERNAME).isEmpty());
        
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        user.fake(article);        
        Neo4jSessionFactory.getInstance().getNeo4jSession().save(user);
        service.save(article);
        
        assertFalse(service.findAllArticles().isEmpty());
        assertFalse(service.findAllFake(TEST_USERNAME).isEmpty());
    }



    @Test
    @UseDataProvider("readData")
    public void testArticleMarkAsRead(NewsArticle article) {
        NewsArticleService service = new NewsArticleService();

        assertTrue(service.findAllRead(TEST_USERNAME).isEmpty());
        
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        Neo4jSessionFactory.getInstance().getNeo4jSession().save(user);

        service.save(article);
        service.markAsRead(TEST_USERNAME, article);
        
        assertFalse(service.findAllArticles().isEmpty());
        assertFalse(service.findAllRead(TEST_USERNAME).isEmpty());
    }

    @Test
    @UseDataProvider("readData")
    public void testArticleMarkAsFavorite(NewsArticle article) {
        NewsArticleService service = new NewsArticleService();

        assertTrue(service.findAllFavorite(TEST_USERNAME).isEmpty());
        
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        Neo4jSessionFactory.getInstance().getNeo4jSession().save(user);

        service.save(article);
        service.markAsFavorite(TEST_USERNAME, article);
        
        assertFalse(service.findAllArticles().isEmpty());
        assertFalse(service.findAllFavorite(TEST_USERNAME).isEmpty());
    }
    
    @Test
    @UseDataProvider("readData")
    public void testArticleMarkAsFake(NewsArticle article) {
        NewsArticleService service = new NewsArticleService();

        assertTrue(service.findAllFavorite(TEST_USERNAME).isEmpty());
        
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        Neo4jSessionFactory.getInstance().getNeo4jSession().save(user);

        service.save(article);
        service.markAsFake(TEST_USERNAME, article);
        
        assertFalse(service.findAllArticles().isEmpty());
        assertFalse(service.findAllFake (TEST_USERNAME).isEmpty());
    }
    

    @Test
    @UseDataProvider("readData")
    public void testArticleUnmarkAsRead(NewsArticle article) {
        NewsArticleService service = new NewsArticleService();

        assertTrue(service.findAllRead(TEST_USERNAME).isEmpty());
        
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        Neo4jSessionFactory.getInstance().getNeo4jSession().save(user);

        service.save(article);
        service.markAsRead(TEST_USERNAME, article);
        
        assertFalse(service.findAllArticles().isEmpty());
        assertFalse(service.findAllRead(TEST_USERNAME).isEmpty());

        service.removeMarkAsRead(TEST_USERNAME, article);
        assertTrue(service.findAllRead(TEST_USERNAME).isEmpty());
    }

    @Test
    @UseDataProvider("readData")
    public void testArticleUnmarkAsFavorite(NewsArticle article) {
        NewsArticleService service = new NewsArticleService();

        assertTrue(service.findAllFavorite(TEST_USERNAME).isEmpty());
        
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        Neo4jSessionFactory.getInstance().getNeo4jSession().save(user);

        service.save(article);
        service.markAsFavorite(TEST_USERNAME, article);
        
        assertFalse(service.findAllArticles().isEmpty());
        assertFalse(service.findAllFavorite(TEST_USERNAME).isEmpty());

        service.removeMarkAsFavorite(TEST_USERNAME, article);
        assertTrue(service.findAllFavorite(TEST_USERNAME).isEmpty());
    }
    
    @Test
    @UseDataProvider("readData")
    public void testArticleUnmarkAsFake(NewsArticle article) {
        NewsArticleService service = new NewsArticleService();

        assertTrue(service.findAllFavorite(TEST_USERNAME).isEmpty());
        
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        Neo4jSessionFactory.getInstance().getNeo4jSession().save(user);

        service.save(article);
        service.markAsFake(TEST_USERNAME, article);
        
        assertFalse(service.findAllArticles().isEmpty());
        assertFalse(service.findAllFake (TEST_USERNAME).isEmpty());
        
        service.removeMarkAsFake(TEST_USERNAME, article);
        assertTrue(service.findAllFake(TEST_USERNAME).isEmpty());        
    }

    @Test
    @UseDataProvider("readData")
    public void testLatest(NewsArticle article) {
        NewsArticleService service = new NewsArticleService();

        assertTrue(service.findLatest().isEmpty());
        service.save(article);
        assertFalse(service.findAll().isEmpty());
        assertFalse(service.findLatest().isEmpty());
    }

    @Test
    @UseDataProvider("readData")
    public void testLatestForUser(NewsArticle article) {
        NewsArticleService service = new NewsArticleService();

        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        Neo4jSessionFactory.getInstance().getNeo4jSession().save(user);

        assertTrue(service.findLatest(TEST_USERNAME).isEmpty());
        service.save(article);
        assertFalse(service.findAll().isEmpty());
        assertFalse(service.findLatest(TEST_USERNAME).isEmpty());
        
        service.markAsRead(TEST_USERNAME, article);
        assertFalse(service.findAll().isEmpty());
        assertTrue(service.findLatest(TEST_USERNAME).isEmpty());
    }

    @Test
    @UseDataProvider("readData")
    public void testOldest(NewsArticle article) {
        NewsArticleService service = new NewsArticleService();

        assertTrue(service.findOldest().isEmpty());
        service.save(article);
        assertFalse(service.findAll().isEmpty());
        assertFalse(service.findOldest().isEmpty());
    }

    @Test
    @UseDataProvider("readData")
    public void testOldestForUser(NewsArticle article) {
        NewsArticleService service = new NewsArticleService();

        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        Neo4jSessionFactory.getInstance().getNeo4jSession().save(user);

        assertTrue(service.findOldest(TEST_USERNAME).isEmpty());
        service.save(article);
        assertFalse(service.findAll().isEmpty());
        assertFalse(service.findOldest(TEST_USERNAME).isEmpty());
        
        service.markAsRead(TEST_USERNAME, article);
        assertFalse(service.findAll().isEmpty());
        assertTrue(service.findOldest(TEST_USERNAME).isEmpty());
    }
    
    
    @Test
    public void testGetSortOrder() {
        NewsArticleService service = new NewsArticleService();
        assertNotNull(service.getSortOrderAsc());
        assertNotNull(service.getSortOrderDesc());
        Assert.assertNotEquals(service.getSortOrderAsc(), service.getSortOrderDesc());

    }
}
