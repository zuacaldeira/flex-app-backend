/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elemets;

import elements.TitleElement;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 * Test for the {@code TitleElement} class.
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class TitleElementTest {
    
    public TitleElementTest() {
    }

    @DataProvider
    public static Object[][] valueData() {
        Object[][] result = new Object[][]{
            {null}, {"Title"}, {""}  
        };
        return result;
    }
    
    @Test
    @UseDataProvider("valueData")
    public void testValue(String title) {
        TitleElement te = new TitleElement(title);
        assertEquals(title, te.getValue());
    }

}
