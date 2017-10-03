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
public class NewsSourceTest {

    public NewsSourceTest() {
    }

    /**
     * Test of getSourceId method, of class NewsSource.
     */
    @Test
    public void testGetSourceId() {
        System.out.println("getSourceId");
        NewsSource source = new NewsSource();
        String sourceId = "sourceId";
        source.setSourceId(sourceId);
        assertEquals(sourceId, source.getSourceId());
    }

    /**
     * Test of getName method, of class NewsSource.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        NewsSource source = new NewsSource();
        String name = "name";
        source.setName(name);
        assertEquals(name, source.getName());
    }

    /**
     * Test of getDescription method, of class NewsSource.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        NewsSource source = new NewsSource();
        String description = "description";
        source.setDescription(description);
        assertEquals(description, source.getDescription());
    }

    /**
     * Test of getUrl method, of class NewsSource.
     */
    @Test
    public void testGetUrl() {
        System.out.println("getUrl");
        NewsSource source = new NewsSource();
        String url = "url";
        source.setUrl(url);
        assertEquals(url, source.getUrl());
    }

    /**
     * Test of getCategory method, of class NewsSource.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        NewsSource source = new NewsSource();
        String category = "category";
        source.setCategory(category);
        assertEquals(category, source.getCategory());
    }

    /**
     * Test of getLanguage method, of class NewsSource.
     */
    @Test
    public void testGetLanguage() {
        System.out.println("getLanguage");
        NewsSource source = new NewsSource();
        String language = "language";
        source.setLanguage(language);
        assertEquals(language, source.getLanguage());
    }

    /**
     * Test of getCountry method, of class NewsSource.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        NewsSource source = new NewsSource();
        String country = "country";
        source.setCountry(country);
        assertEquals(country, source.getCountry());
    }

    /**
     * Test of setSourceId method, of class NewsSource.
     */
    @Test
    public void testSetSourceId() {
        System.out.println("setSourceId");
        testGetSourceId();
    }

    /**
     * Test of setName method, of class NewsSource.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        testGetName();
    }

    /**
     * Test of setDescription method, of class NewsSource.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        testGetDescription();
    }

    /**
     * Test of setUrl method, of class NewsSource.
     */
    @Test
    public void testSetUrl() {
        System.out.println("setUrl");
        testGetUrl();
    }

    /**
     * Test of setCategory method, of class NewsSource.
     */
    @Test
    public void testSetCategory() {
        System.out.println("setCategory");
        testGetCategory();
    }

    /**
     * Test of setLanguage method, of class NewsSource.
     */
    @Test
    public void testSetLanguage() {
        System.out.println("setLanguage");
        testGetLanguage();
    }

    /**
     * Test of setCountry method, of class NewsSource.
     */
    @Test
    public void testSetCountry() {
        System.out.println("setCountry");
        testGetCountry();
    }

    /**
     * Test of toString method, of class NewsSource.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        NewsSource source = new NewsSource();
        assertEquals(source.getName(), source.toString());
        source.setName("Jamaika");
        assertEquals(source.getName(), source.toString());
    }

    /**
     * Test of compareTo method, of class NewsSource.
     */
    @Test
    @UseDataProvider("compareToData")
    public void testCompareTo(NewsSource source, NewsSource other, int expected) {
        System.out.println("compareTo");
        assertEquals(expected, source.compareTo(other));
    }

    @DataProvider
    public static Object[][] compareToData() {
        NewsSource source1 = new NewsSource("sourceId1", "name1", "description1", "url1", "category1", "language1", "country1");
        NewsSource source2 = new NewsSource("sourceId2", "name2", "description2", "url2", "category2", "language2", "country2");
        return new Object[][]{
            {source1, source2, -1},
            {source2, source1, 1},
            {source1, source1, 0},
            {source2, source2, 0}
        };
    }

    /**
     * Test of addCorrespondent method, of class NewsSource.
     */
    @Test
    @UseDataProvider("addCorrespondentData")
    public void testAddCorrespondent(NewsSource source, NewsAuthor author) {
        System.out.println("addCorrespondent");
        assertTrue(source.getCorrespondents().isEmpty());
        source.addCorrespondent(author);
        assertTrue(source.getCorrespondents().contains(author));
    }

    @DataProvider
    public static Object[][] addCorrespondentData() {
        return new Object[][]{
            {new NewsSource(), new NewsAuthor()}
        };
    }

    /**
     * Test of getCorrespondents method, of class NewsSource.
     */
    @Test
    @UseDataProvider("addCorrespondentData")
    public void testGetCorrespondents(NewsSource source, NewsAuthor author) {
        System.out.println("getCorrespondents");
        testAddCorrespondent(source, author);
    }

    /**
     * Test of setCorrespondents method, of class NewsSource.
     */
    @Test
    @UseDataProvider("setCorrespondentsData")
    public void testSetCorrespondents(NewsSource source, Set<NewsAuthor> oldCorrespondents, Set<NewsAuthor> newCorrespondents) {
        System.out.println("setCorrespondents");
        assertTrue(oldCorrespondents.containsAll(source.getCorrespondents()));
        source.setCorrespondents(newCorrespondents);
        assertTrue(newCorrespondents.containsAll(source.getCorrespondents()));
    }

    @DataProvider
    public static Object[][] setCorrespondentsData() {
        NewsAuthor author1 = new NewsAuthor();
        Set<NewsAuthor> oldCorrespondents = new HashSet<>();
        oldCorrespondents.add(author1);

        NewsAuthor author2 = new NewsAuthor("named");
        Set<NewsAuthor> newCorrespondents = new HashSet<>();
        newCorrespondents.add(author2);

        NewsSource source = new NewsSource();
        source.setSourceId("sourceId");
        source.setCorrespondents(oldCorrespondents);

        return new Object[][]{
            {source, oldCorrespondents, newCorrespondents}
        };
    }

    /**
     * Test of hashCode method, of class NewsSource.
     */
    @Test
    @UseDataProvider("hashCodeData")
    public void testHashCode(NewsSource source, NewsSource other) {
        System.out.println("hashCode");
        assertEquals(source.hashCode(), other.hashCode());
    }

    @DataProvider
    public static Object[][] hashCodeData() {
        NewsSource source1 = new NewsSource("sourceId1", "name1", "description1", "url1", "category1", "language1", "country1");
        NewsSource source2 = new NewsSource("sourceId2", "name2", "description2", "url2", "category2", "language2", "country2");
        return new Object[][]{
            {new NewsSource(), new NewsSource()},
            {source1, source1},
            {source2, source2}
        };
    }

    /**
     * Test of equals method, of class NewsSource.
     */
    @Test
    @UseDataProvider("equalsData")
    public void testEquals(NewsSource source, Object other, boolean result) {
        System.out.println("equals");
        assertEquals(result, source.equals(other));
    }

    @DataProvider
    public static Object[][] equalsData() {
        NewsSource source1 = new NewsSource("sourceId1", "name1", "description1", "url1", "category1", "language1", "country1");
        NewsSource source2 = new NewsSource("sourceId2", "name2", "description2", "url2", "category2", "language2", "country2");
        return new Object[][]{
            {new NewsSource(), new NewsSource(), true},
            {source1, source1, true},
            {source2, source2, true},
            {new NewsSource(), source2, false},
            {source1, new NewsSource(), false},
            {source1, source2, false},
            {source1, null, false},
            {source1, "You", false},};
    }

    /**
     * Test of getPropertyName method, of class NewsSource.
     */
    @Test
    public void testGetPropertyName() {
        System.out.println("getPropertyName");
        assertEquals("sourceId", new NewsSource().getPropertyName());
    }

    /**
     * Test of getPropertyValue method, of class NewsSource.
     */
    @Test
    public void testGetPropertyValue() {
        System.out.println("getPropertyValue");
        NewsSource source = new NewsSource();
        assertNull(source.getPropertyValue());

        source.setSourceId("sourceId");
        assertEquals("sourceId", source.getPropertyValue());
    }

    /**
     * Test of hasUrl method, of class NewsSource.
     */
    @Test
    public void testHasUrl() {
        System.out.println("hasUrl");
        NewsSource source = new NewsSource();
        assertFalse(source.hasUrl());
        source.setUrl("url");
        assertTrue(source.hasUrl());
    }

    /**
     * Test of setLogoUrl method, of class NewsSource.
     */
    @Test
    public void testSetLogoUrl() {
        System.out.println("setLogoUrl");
        testGetLogoUrl();
    }

    /**
     * Test of getLogoUrl method, of class NewsSource.
     */
    @Test
    public void testGetLogoUrl() {
        System.out.println("getLogoUrl");
        NewsSource source = new NewsSource();
        String logoUrl = "logo url";
        source.setLogoUrl(logoUrl);
        assertEquals(logoUrl, source.getLogoUrl());
    }

}
