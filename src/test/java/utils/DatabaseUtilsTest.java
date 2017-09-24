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
public class DatabaseUtilsTest {
    
    public DatabaseUtilsTest() {
    }



    @DataProvider
    public static Object[][] wrapUpData() {
        Object[][] result = {
            {null, null}, 
            {"Hello!", "\"Hello!\""}
        };
        return result;
    }


    /**
     * Test of wrapUp method, of class DatabaseUtils.
     * @param value The string we want to wrap.
     * @param expected  The expected wrapped string.
     */
    @Test
    @UseDataProvider("wrapUpData")
    public void testWrapUp(String value, String expected) {
        System.out.println("wrapUp");
        assertEquals(expected, DatabaseUtils.getInstance().wrapUp(value));
    }



    /**
     * Test of getInstance method, of class DatabaseUtils.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        assertNotNull(DatabaseUtils.getInstance());
        assertTrue(DatabaseUtils.getInstance() == DatabaseUtils.getInstance());
    }
  
}
