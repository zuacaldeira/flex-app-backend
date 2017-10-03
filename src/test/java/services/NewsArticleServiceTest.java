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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class NewsArticleServiceTest {

    private static String TEST_USERNAME = "test:username";
    private static String TEST_PASSWORD = "test:password";

    public NewsArticleServiceTest() {
        super();
    }

    @Before
    public void cleanUp() {
        System.out.println("-- CLEANUP --");
        Neo4jSessionFactory.getInstance().getNeo4jSession().purgeDatabase();
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
            {new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country".toUpperCase()), "", "description"},
            {new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country".toUpperCase()), "", ""}
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
    public void testFindAllUsername(NewsArticle article) {
        System.out.println("\ttestFindAllUsername");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        new FlexUserService().save(user);

        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAll(TEST_USERNAME).isEmpty());

        service.save(article);

        assertFalse(service.findAll(TEST_USERNAME).isEmpty());
        service.markAsRead(TEST_USERNAME, article);
        assertTrue(service.findAll(TEST_USERNAME).isEmpty());
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
    @UseDataProvider("articlesData")
    public void testDelete(NewsArticle article) {
        System.out.println("\ttestFindAllUsers");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAllArticles().isEmpty());
        service.save(article);
        assertFalse(service.findAllArticles().isEmpty());
        article = service.find(article);
        service.delete(article.getTitle());
        assertTrue(service.findAllArticles().isEmpty());
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFind(NewsArticle article) {
        System.out.println("\ttestFindAllUsers");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAllArticles().isEmpty());
        service.save(article);
        assertFalse(service.findAllArticles().isEmpty());

        article = service.find(article);
        assertNotNull(article);

        article = service.find(article.getTitle());
        assertNotNull(article);
    }

    @Test
    @UseDataProvider("articlesData")
    public void testCount(NewsArticle article) {
        System.out.println("\ttestFindAllUsers");
        NewsArticleService service = new NewsArticleService();
        assertTrue(0 == service.count());
        service.save(article);
        assertTrue(1 == service.count());
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFindAll(NewsArticle article) {
        System.out.println("\ttestFindAll");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAll().isEmpty());
        service.save(article);
        assertFalse(service.findAll().isEmpty());
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFindAllDesc(NewsArticle article) {
        System.out.println("\ttestFindAllDesc");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAllDesc().isEmpty());
        service.save(article);
        assertFalse(service.findAllDesc().isEmpty());
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFindAllAsc(NewsArticle article) {
        System.out.println("\ttestFindAllDesc");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAllAsc().isEmpty());
        service.save(article);
        assertFalse(service.findAllAsc().isEmpty());
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFindAllWithLimit(NewsArticle article) {
        System.out.println("\ttestFindAll");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAllWithLimit(10).isEmpty());
        service.save(article);
        assertFalse(service.findAllWithLimit(10).isEmpty());
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFindAllDescWithLimit(NewsArticle article) {
        System.out.println("\ttestFindAllDesc");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAllDescWithLimit(10).isEmpty());
        service.save(article);
        assertFalse(service.findAllDescWithLimit(10).isEmpty());
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFindAllAscWithLimit(NewsArticle article) {
        System.out.println("\ttestFindAllDesc");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAllAscWithLimit(10).isEmpty());
        service.save(article);
        assertFalse(service.findAllAscWithLimit(10).isEmpty());
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFindAllProperty(NewsArticle article) {
        System.out.println("\ttestFindAll");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAll("title", article.getTitle()).isEmpty());
        service.save(article);
        assertFalse(service.findAll("title", article.getTitle()).isEmpty());
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFindAllDescProperty(NewsArticle article) {
        System.out.println("\ttestFindAllDesc");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAllDesc("title", article.getTitle()).isEmpty());
        service.save(article);
        assertFalse(service.findAllDesc("title", article.getTitle()).isEmpty());
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFindAllAscProperty(NewsArticle article) {
        System.out.println("\ttestFindAllDesc");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAllAsc("title", article.getTitle()).isEmpty());
        service.save(article);
        assertFalse(service.findAllAsc("title", article.getTitle()).isEmpty());
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFindAllPropertyLimit(NewsArticle article) {
        System.out.println("\ttestFindAll");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAll("title", article.getTitle(), 10).isEmpty());
        service.save(article);
        assertFalse(service.findAll("title", article.getTitle(), 10).isEmpty());
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFindAllDescPropertyLimit(NewsArticle article) {
        System.out.println("\ttestFindAllDesc");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAllDesc("title", article.getTitle(), 10).isEmpty());
        service.save(article);
        assertFalse(service.findAllDesc("title", article.getTitle(), 10).isEmpty());
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFindAllAscPropertyLimit(NewsArticle article) {
        System.out.println("\ttestFindAllDesc");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAllAsc("title", article.getTitle(), 10).isEmpty());
        service.save(article);
        assertFalse(service.findAllAsc("title", article.getTitle(), 10).isEmpty());
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFindAllPropertyUsername(NewsArticle article) {
        System.out.println("\ttestFindAll");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAll("Username", "title", article.getTitle()).isEmpty());
        service.save(article);
        assertTrue(service.findAll("Username", "title", article.getTitle()).isEmpty());
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFindAllDescPropertyUsername(NewsArticle article) {
        System.out.println("\ttestFindAllDesc");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAllDesc("Username", "title", article.getTitle()).isEmpty());
        service.save(article);
        assertTrue(service.findAllDesc("Username", "title", article.getTitle()).isEmpty());
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFindAllAscPropertyUsername(NewsArticle article) {
        System.out.println("\ttestFindAllDesc");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAllAsc("Username", "title", article.getTitle()).isEmpty());
        service.save(article);
        assertTrue(service.findAllAsc("Username", "title", article.getTitle()).isEmpty());
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFindAllPropertyUsernameLimit(NewsArticle article) {
        System.out.println("\ttestFindAll");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAll("username", "title", article.getTitle(), 10).isEmpty());
        service.save(article);
        assertTrue(service.findAll("username", "title", article.getTitle(), 10).isEmpty());
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFindAllDescPropertyUsernameLimit(NewsArticle article) {
        System.out.println("\ttestFindAllDesc");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAllDesc("username", "title", article.getTitle(), 10).isEmpty());
        service.save(article);
        assertTrue(service.findAllDesc("username", "title", article.getTitle(), 10).isEmpty());
    }

    @Test
    @UseDataProvider("articlesData")
    public void testFindAllAscPropertyUsernameLimit(NewsArticle article) {
        System.out.println("\ttestFindAllDesc");
        NewsArticleService service = new NewsArticleService();
        assertTrue(service.findAllAsc("username", "title", article.getTitle(), 10).isEmpty());
        service.save(article);
        assertTrue(service.findAllAsc("username", "title", article.getTitle(), 10).isEmpty());
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

    @Test(expected = NewsServiceException.class)
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
        assertFalse(service.findAllFake(TEST_USERNAME).isEmpty());
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
        assertFalse(service.findAllFake(TEST_USERNAME).isEmpty());

        service.removeMarkAsFake(TEST_USERNAME, article);
        assertTrue(service.findAllFake(TEST_USERNAME).isEmpty());
    }

    @Test
    @UseDataProvider("readData")
    public void testLatest(NewsArticle article) {
        NewsArticleService service = new NewsArticleService();

        assertTrue(service.findLatest().isEmpty());
        service.save(article);
        assertFalse(service.findAllArticles().isEmpty());
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
        assertFalse(service.findAllArticles().isEmpty());
        assertFalse(service.findLatest(TEST_USERNAME).isEmpty());

        service.markAsRead(TEST_USERNAME, article);
        assertFalse(service.findAllArticles().isEmpty());
        assertTrue(service.findLatest(TEST_USERNAME).isEmpty());
    }

    @Test
    @UseDataProvider("readData")
    public void testOldest(NewsArticle article) {
        NewsArticleService service = new NewsArticleService();

        assertTrue(service.findOldest().isEmpty());
        service.save(article);
        assertFalse(service.findAllArticles().isEmpty());
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
        assertFalse(service.findAllArticles().isEmpty());
        assertFalse(service.findOldest(TEST_USERNAME).isEmpty());

        service.markAsRead(TEST_USERNAME, article);
        assertFalse(service.findAllArticles().isEmpty());
        assertTrue(service.findOldest(TEST_USERNAME).isEmpty());
    }

    @Test
    public void testGetSortOrder() {
        NewsArticleService service = new NewsArticleService();
        assertNotNull(service.getSortOrderAsc());
        assertNotNull(service.getSortOrderDesc());
        Assert.assertNotEquals(service.getSortOrderAsc(), service.getSortOrderDesc());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindFail() {
        NewsArticleService service = new NewsArticleService();
        service.find((String) null);
    }

    @Test
    public void findAllWithUserAndLimit() {
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        new FlexUserService().save(user);

        NewsArticleService service = new NewsArticleService();
        assertNotNull(service.findAll(TEST_USERNAME, 10));

        NewsArticle article = new NewsArticle("T", "D", "U", "I", new Date(), "Q", "L", "C");
        service.save(article);
        assertFalse(service.findAll(TEST_USERNAME, 10).isEmpty());

        service.markAsFake(TEST_USERNAME, article);
        assertTrue(service.findAll(TEST_USERNAME, 10).isEmpty());
    }

    @Test
    public void findAllAscWithUserAndLimit() {
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        new FlexUserService().save(user);

        NewsArticleService service = new NewsArticleService();
        assertNotNull(service.findAllAsc(TEST_USERNAME, 10));

        NewsArticle article = new NewsArticle("T", "D", "U", "I", new Date(), "Q", "L", "C");
        service.save(article);
        assertFalse(service.findAllAsc(TEST_USERNAME, 10).isEmpty());

        service.markAsFake(TEST_USERNAME, article);
        assertTrue(service.findAllAsc(TEST_USERNAME, 10).isEmpty());
    }

    @Test
    public void findAllDescWithUserAndLimit() {
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        new FlexUserService().save(user);

        NewsArticleService service = new NewsArticleService();
        assertNotNull(service.findAllDesc(TEST_USERNAME, 10));

        NewsArticle article = new NewsArticle("T", "D", "U", "I", new Date(), "Q", "L", "C");
        service.save(article);
        assertFalse(service.findAllDesc(TEST_USERNAME, 10).isEmpty());

        service.markAsFake(TEST_USERNAME, article);
        assertTrue(service.findAllDesc(TEST_USERNAME, 10).isEmpty());
    }
}
