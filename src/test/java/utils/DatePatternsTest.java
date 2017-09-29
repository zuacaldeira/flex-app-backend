/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import java.text.ParseException;
import java.util.Locale;
import org.apache.commons.lang3.time.DateUtils;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class DatePatternsTest {
    
    public DatePatternsTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    
    @DataProvider
    public static Object[][] dataDefaultProvider() {
        return new Object[][]{
            {"31-12-1999", "dd-MM-yyyy"},
            {"31/12/1999", "dd/MM/yyyy"},
            {"31 Sep 1999", "dd MMM yyyy"},
            {"2017-09-28T15:31:06.8Z", "yyyy-MM-dd'T'HH:mm:ss.S'Z'"}
        };
    }
    
    @DataProvider
    public static Object[][] dataProvider() {
        return new Object[][]{
            {"31 Set 1999", Locale.forLanguageTag("pt"), "dd MMM yyyy"}
        };
    }

    @Test
    @UseDataProvider("dataDefaultProvider")
    public void hello(String value, String format) throws ParseException {
        assertNotNull(DateUtils.parseDate(value, format));
    }

    @Test
    @UseDataProvider("dataProvider")
    public void hello(String value, Locale locale, String format) throws ParseException {
        assertNotNull(DateUtils.parseDate(value, locale, format));
    }

}
