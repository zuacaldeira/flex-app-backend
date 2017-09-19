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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class DateUtilsTest {
    
    public DateUtilsTest() {
    }


    @DataProvider
    public static Object[][] formatData() {
        Calendar c = Calendar.getInstance();
        c.set(2017, 10, 20, 2, 30);
        Date d = c.getTime();
        Object[][] result = {
            {null, null}, 
            {d, "20/11/2017 02:30"}
        };
        return result;
    }

    @DataProvider
    public static Object[][] parseData() {
        Object[][] result = {
            {null, "en"}, 
            {"2017-09-12T18:15:54+0000", "en"}
        };
        return result;
    }

    /**
     * Test of formatDate method, of class DatabaseUtils.
     * @param date  The date to format according to the basic date format.
     * @param expectedDate  The expected string representing date.
     */
    @Test
    @UseDataProvider("formatData")
    public void testFormatDate(Date date, String expectedDate) {
        System.out.println("formatDate");
        assertEquals(DateUtils.getInstance().formatDate(date), expectedDate);
    }

    /**
     * Test of parseDate method, of class DatabaseUtils.
     * @param dateString A date string in the default format 'yyyy-MM-dd'T'HH:mm:ss.SSSZ'
     * @param language The language of the location corresponding to the date
     */
    @Test
    @UseDataProvider("parseData")
    @Ignore
    public void testParseDate(String dateString, String language) {
        System.out.println("parseData");
        Date result = DateUtils.getInstance().parseDate(dateString, language);
        if(dateString == null) {
            assertNull(result);
        }
        else {
            assertNotNull(result);
        }
    }
    
    @Test
    public void testBasicSimpleDateFormat() {
        try {
            SimpleDateFormat format = DateUtils.getInstance().getBasicSimpleDateFormat();
            Date date = new Date();
            
            String formatted = format.format(date);
            System.out.println("Formatted = " + formatted);
            
            Date parsed = format.parse(formatted);

            System.out.println("Original = " + date);
            
            System.out.println("Parsed = " + parsed);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtilsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
