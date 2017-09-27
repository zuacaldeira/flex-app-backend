/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elemets;

import elements.TimeElement;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 * Tests a time element.
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class TimeElementTest {

    
    @DataProvider
    public static Object[][] timeData() {
        Object[][] result = new Object[][]{
            {null,null,null},
        };
        return result;
    }
    
    public TimeElementTest() {
    }

    /**
     * Test of getDate method, of class TimeElement.
     */
    @Test
    @UseDataProvider("timeData")
    public void testGetDate(String dateString, String language) {
        System.out.println("getDate");
        TimeElement instance = new TimeElement(dateString, language);
        assertNotNull(instance.getDate());
    }

}
