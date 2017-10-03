/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class NamesUtilsTest {
    
    public NamesUtilsTest() {
    }



    @DataProvider
    public static Object[][] urlData() {
        Object[][] result = {
            {null, false},
            {"http://test.com/zuacaldeira", true}, 
            {"https://test.com/zuacaldeira", true}, 
            {"zuacaldeira", false} 
        };
        return result;
    }

    @DataProvider
    public static Object[][] extractData() {
        Object[][] result = {
            {"http://test.com/zuacaldeira", "zuacaldeira"}, 
            {"http://www.abc.net.au/news/zuacaldeira", "zuacaldeira"}, 
            {"http://xml.zeit.de/autoren/zuacaldeira", "zuacaldeira"},
            {"zuacaldeira", "zuacaldeira"}
        };
        return result;
    }

    @DataProvider
    public static Object[][] extractFailData() {
        Object[][] result = {
            {null, null}
        };
        return result;
    }

    @DataProvider
    public static Object[][] authorsData() {
        Object[][] result = {
            {null, 0},
            {"Me", 1}, 
            {"Me, You", 2}, 
            {"Me, You,", 2},
            {"http://xml.zeit.de/autoren/zuacaldeira", 1}
        };
        return result;
    }

    /**
     * Test of isUrl method, of class DatabaseUtils.
     */
    @Test
    @UseDataProvider("urlData")
    public void testIsUrl(String value, boolean isUrl) {
        System.out.println("isUrl");
        assertEquals(isUrl, NamesUtils.getInstance().isUrl(value));
    }

    /**
     * Test of getInstance method, of class DatabaseUtils.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        assertNotNull(DatabaseUtils.getInstance());
        assertTrue(NamesUtils.getInstance() == NamesUtils.getInstance());
    }

    /**
     * Test of extractAuthors method, of class DatabaseUtils.
     * @param namesString   A comma-separated string with authors names.
     * @param howMany   The expected number of names.
     */
    @Test
    @UseDataProvider("authorsData")
    public void testExtractAuthors(String namesString, int howMany) {
        System.out.println("extractAuthors");
        assertEquals(howMany, NamesUtils.getInstance().extractAuthors(namesString).size());
    }

    /**
     * Test of extractAuthorsNames method, of class DatabaseUtils.
     */
    @Test
    @UseDataProvider("authorsData")
    public void testExtractAuthornames(String namesString, int howMany) {
        System.out.println("extractAuthorNames");
        assertEquals(howMany, NamesUtils.getInstance().extractAuthorsNames(namesString).size());
    }

    /**
     * Test of extractNameFromUrl method, of class DatabaseUtils.
     */
    @Test
    @UseDataProvider("extractData")
    public void testExtractNameFromUrl(String value, String expected) {
        System.out.println("extractNameFromUrl");
        assertEquals(expected, NamesUtils.getInstance().extractNameFromUrl(value));
    }
        
    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider("extractFailData")
    public void testExtractNameFromUrlFail(String value, String expected) {
        System.out.println("extractNameFromUrl");
        assertEquals(expected, NamesUtils.getInstance().extractNameFromUrl(value));
    }
}
