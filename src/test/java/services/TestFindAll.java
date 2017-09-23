/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

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
public class TestFindAll extends Neo4jTest {
    
    public TestFindAll() {
    }

    @DataProvider
    public static Object[][] servicesData() {
        return new Object[][]{
            {new FlexUserService()},
            {new NewsArticleService()},
            {new NewsSourceService()},
            {new NewsAuthorService()}
        };
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    @UseDataProvider("servicesData")
    public <U> void testFindAll(DBService<U> service) {
        assertTrue(service.findAll().isEmpty());    
    }
}
