/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import db.Neo4jSessionFactory;
import db.NewsSource;
import io.reactivex.disposables.Disposable;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class NewsSourceServiceTestIT {

    private static String TEST_USERNAME = "test:username";
    private static String TEST_PASSWORD = "test:password";

    public NewsSourceServiceTestIT() {
        super();
    }

    @DataProvider
    public static Object[][] sourcesData() {
        return new Object[][]{
            {new NewsSource("sourceId", "name", "description", "url", "category", "language", "country".toUpperCase())}
        };
    }

    @DataProvider
    public static Object[][] searchByCategoryData() {
        return new Object[][]{
            {new NewsSource("sourceId", "name", "description", "url", "category", "language", "country".toUpperCase()), "category"}
        };
    }

    @DataProvider
    public static Object[][] searchByNameData() {
        return new Object[][]{
            {new NewsSource("sourceId", "name", "description", "url", "category", "language", "country".toUpperCase()), "name"}
        };
    }

    @DataProvider
    public static Object[][] searchBySourceIdData() {
        return new Object[][]{
            {new NewsSource("sourceId", "name", "description", "url", "category", "language", "country".toUpperCase()), "sourceId"}
        };
    }

    @DataProvider
    public static Object[][] searchByLanguageData() {
        return new Object[][]{
            {new NewsSource("sourceId", "name", "description", "url", "category", "language", "country".toUpperCase()), "language"}
        };
    }

    @DataProvider
    public static Object[][] searchByCountryData() {
        return new Object[][]{
            {new NewsSource("sourceId", "name", "description", "url", "category", "language", "country".toUpperCase()), "country"}
        };
    }

    @DataProvider
    public static Object[][] searchByLocaleData() {
        return new Object[][]{
            {new NewsSource("sourceId", "name", "description", "url", "category", "language", "country".toUpperCase()), "language_COUNTRY"}
        };
    }

    @Before
    public void cleanUp() {
        System.out.println("-- CLEANUP --");
        Neo4jSessionFactory.getInstance().getNeo4jSession().purgeDatabase();
    }
    
    @Test
    @UseDataProvider("sourcesData")
    public void testFindAllSources(NewsSource source) {
        System.out.println("\ttestFindAllUsers");
        NewsSourceService service = new NewsSourceService();
        assertTrue(service.findAllSources().isEmpty().blockingGet());
        service.save(source);
        assertFalse(service.findAllSources().isEmpty().blockingGet());
    }
    
    @Test
    @UseDataProvider("searchByNameData")
    public void testSourceNamed(NewsSource source, String name) {
        NewsSourceService service = new NewsSourceService();
        assertNull(service.findSourceNamed(name));
        service.save(source);
        assertNotNull(service.findSourceNamed(name));
    }
    
    @Test
    @UseDataProvider("searchBySourceIdData")
    public void testSourceWithSourceId(NewsSource source, String sourceId) {
        NewsSourceService service = new NewsSourceService();
        assertNull(service.findSourceWithSourceId(sourceId));
        service.save(source);
        assertNotNull(service.findSourceWithSourceId(sourceId));
    }

    @Test
    @UseDataProvider("searchByNameData")
    public void testFindNames(NewsSource source, String name) {
        NewsSourceService service = new NewsSourceService();
        service.findNames().subscribe(Assert::assertNotNull);
        service.save(source);
        service.findNames().subscribe(Assert::assertNotNull);
    }


    @Test
    @UseDataProvider("searchByCategoryData")
    public void testFindCategories(NewsSource source, String category) {
        NewsSourceService service = new NewsSourceService();
        Disposable categories = service.findCategories().subscribe(System.out::println);
        service.save(source);
        assertFalse(service.findAllSources().isEmpty().blockingGet());
        Disposable newCategories = service.findCategories().subscribe(System.out::println);
    }

    @Test
    @UseDataProvider("searchByCategoryData")
    public void testSourceWithCategory(NewsSource source, String category) {
        NewsSourceService service = new NewsSourceService();
        assertTrue(service.findSourcesWithCategory(category).isEmpty().blockingGet());
        service.save(source);
        assertFalse(service.findAllSources().isEmpty().blockingGet());
        assertFalse(service.findSourcesWithCategory(category).isEmpty().blockingGet());
    }

    @Test
    @UseDataProvider("searchByLanguageData")
    public void testSourceWithLanguage(NewsSource article, String language) {
        NewsSourceService service = new NewsSourceService();
        assertTrue(service.findSourcesWithLanguage(language).isEmpty().blockingGet());
        service.save(article);
        assertFalse(service.findAllSources().isEmpty().blockingGet());
        assertFalse(service.findSourcesWithLanguage(language).isEmpty().blockingGet());
    }

    @Test
    @UseDataProvider("searchByLanguageData")
    public void testFindLanguages(NewsSource source, String language) {
        NewsSourceService service = new NewsSourceService();
        service.findLanguages().subscribe(Assert::assertNotNull);
        service.save(source);
        assertFalse(service.findAllSources().isEmpty().blockingGet());
        service.findLanguages().subscribe(Assert::assertNotNull);
    }

    @Test
    @UseDataProvider("searchByCountryData")
    public void testSourceWithCountry(NewsSource article, String country) {
        NewsSourceService service = new NewsSourceService();
        assertTrue(service.findSourcesWithCountry(country.toUpperCase()).isEmpty().blockingGet());
        service.save(article);
        assertFalse(service.findAllSources().isEmpty().blockingGet());
        assertFalse(service.findSourcesWithCountry(country.toUpperCase()).isEmpty().blockingGet());
    }
    
    @Test
    @UseDataProvider("searchByCountryData")
    public void testFindCountries(NewsSource source, String country) {
        NewsSourceService service = new NewsSourceService();
        service.findCountries().subscribe(Assert::assertNotNull);
        service.save(source);
        assertFalse(service.findAllSources().isEmpty().blockingGet());
        service.findCountries().subscribe(Assert::assertNotNull);
    }
    
    @Test
    @UseDataProvider("searchByLocaleData")
    public void testFindLocales(NewsSource source, String locale) {
        NewsSourceService service = new NewsSourceService();
        service.findLocales().subscribe(Assert::assertNotNull);
        service.save(source);
        service.findLocales().subscribe(Assert::assertNotNull);
    }

    @Test
    public void testGetSortOrder() {
        NewsSourceService service = new NewsSourceService();
        assertNotNull(service.getSortOrderAsc());
        assertNotNull(service.getSortOrderDesc());
        Assert.assertNotEquals(service.getSortOrderAsc(), service.getSortOrderDesc());
    }
    
}
