/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.ECS.client.jax.Items;
import de.malkusch.amazon.ecs.exception.RequestException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class AmazonWebServiceClientTest extends AbstractAmazonWebServiceTest {
    
    public AmazonWebServiceClientTest() {
    }

    /**
     * Test of search method, of class AmazonWebServiceClient.
     * @throws java.io.IOException
     * @throws java.io.UnsupportedEncodingException
     * @throws de.malkusch.amazon.ecs.exception.RequestException
     */
    @Test
    public void testSearch() throws IOException, UnsupportedEncodingException, RequestException {
        System.out.println("search");
        String searchIndex = "Books";
        String keywords = "Java";
        addPropertiesToTestDB();
        AmazonWebServiceClient instance = new AmazonWebServiceClient();
        Items items = instance.search(searchIndex, keywords);
        assertFalse(items.getItem().isEmpty());
    }
}
