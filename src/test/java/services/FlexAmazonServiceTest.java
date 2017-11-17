/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.ECS.client.jax.Items;
import org.junit.Test;

/**
 *
 * @author zua
 */
public class FlexAmazonServiceTest extends AbstractAmazonWebServiceTest{
    
    public FlexAmazonServiceTest() {
    }

    /**
     * Test of searchAmazonItems method, of class FlexAmazonService.
     * @throws java.lang.Exception
     */
    @Test
    public void testSearchAmazonItems() throws Exception {
        System.out.println("searchAmazonItems");
        super.addPropertiesToTestDB();
        
    }
    
}
