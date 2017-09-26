/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import services.NewsArticleService;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class FlexObjectMapperTest {

    public FlexObjectMapperTest() {
    }

    @DataProvider
    public static Object[][] sourceQueries() {
        return new Object[][]{
            {"", "", "", "http://newsapi.org/v1/sources?apiKey=5b4e00f3046843138d8368211777a4f2"}, // all categories
            {null, null, null, "http://newsapi.org/v1/sources?apiKey=5b4e00f3046843138d8368211777a4f2"}, // all categories
            {"cat", "", "", "http://newsapi.org/v1/sources?category=cat&apiKey=5b4e00f3046843138d8368211777a4f2"}, // all categories
            {"", "en", "", "http://newsapi.org/v1/sources?language=en&apiKey=5b4e00f3046843138d8368211777a4f2"}, // all categories
            {"", "", "gb", "http://newsapi.org/v1/sources?country=gb&apiKey=5b4e00f3046843138d8368211777a4f2"}, // all categories

            {"cat", "en", "", "http://newsapi.org/v1/sources?category=cat&language=en&apiKey=5b4e00f3046843138d8368211777a4f2"}, // all categories
            {"cat", "", "gb", "http://newsapi.org/v1/sources?category=cat&country=gb&apiKey=5b4e00f3046843138d8368211777a4f2"}, // all categories
            {"", "en", "gb", "http://newsapi.org/v1/sources?language=en&country=gb&apiKey=5b4e00f3046843138d8368211777a4f2"}, // all categories

            {"cat", "en", "gb", "http://newsapi.org/v1/sources?category=cat&language=en&country=gb&apiKey=5b4e00f3046843138d8368211777a4f2"}, // all categories
        };
    }

    @DataProvider
    public static Object[][] articlesQueries() {
        return new Object[][]{
            {"", "http://newsapi.org/v1/articles?apiKey=5b4e00f3046843138d8368211777a4f2"}, // all categories
            {"bbc-news", "http://newsapi.org/v1/articles?source=bbc-news&apiKey=5b4e00f3046843138d8368211777a4f2"}, // all categories
        };
    }

    @DataProvider
    public static Object[][] apiCalls() {
        return new Object[][]{
            {"http://newsapi.org/v1/articles?source=bloomberg&apiKey=5b4e00f3046843138d8368211777a4f2"}, // all categories
            {"http://newsapi.org/v1/articles?source=bloomberg&apiKey=5b4e00f3046843138d8368211777a4f2"}, // all categories
        };
    }

    @Test
    @UseDataProvider("sourceQueries")
    public void testGetSourceQuery(String category, String language2Letter, String country, String expected) {
        System.out.println("getSourcesQuery");
        FlexObjectMapper mapper = new FlexObjectMapper();
        assertEquals(expected, mapper.getSourcesQuery(category, language2Letter, country));
    }

    @Test
    @UseDataProvider("articlesQueries")
    public void testGetArticlesQuery(String source, String expected) {
        System.out.println("getArticlesQuery");
        FlexObjectMapper mapper = new FlexObjectMapper();
        assertEquals(expected, mapper.getArticlesQuery(source));
    }

    /**
     * Test of makeApiCall method, of class FlexObjectMapper.
     *
     * @param apiCall An string with the web service call.
     */
    @Test
    @UseDataProvider("apiCalls")
    public void testMakeApiCall(String apiCall) {
        System.out.println("makeApiCall");
        FlexObjectMapper instance = new FlexObjectMapper();
        assertNotNull(instance.makeApiCall(apiCall));
    }


    /**
     * Test of loadAllData method, of class FlexObjectMapper.
     */
    @Test
    public void testLoadAllData() {
        System.out.println("loadAllData");
        FlexObjectMapper instance = new FlexObjectMapper();
        instance.setNewsArticlesService(new NewsArticleService());
        instance.loadAllData();
        assertTrue(true);
    }
    
    

}
