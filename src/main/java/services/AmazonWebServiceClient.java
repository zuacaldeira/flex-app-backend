/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.ECS.client.jax.AWSECommerceService;
import com.ECS.client.jax.Cart;
import com.ECS.client.jax.ItemSearchRequest;
import com.ECS.client.jax.Items;
import db.AmazonProperties;
import factory.Neo4jSessionFactory;
import de.malkusch.amazon.ecs.ProductAdvertisingAPI;
import de.malkusch.amazon.ecs.configuration.Configuration;
import de.malkusch.amazon.ecs.configuration.PropertiesConfiguration;
import de.malkusch.amazon.ecs.exception.RequestException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Properties;

/**
 *
 * @author zua
 */
public class AmazonWebServiceClient {

    private AmazonProperties properties;
    private ProductAdvertisingAPI api;
    private Cart cart;

    public AmazonWebServiceClient() {
        initProperties();
    }

    private void initProperties() {
        this.properties = Neo4jSessionFactory.getInstance().getNeo4jSession().queryForObject(AmazonProperties.class, "MATCH (n:AmazonProperties) RETURN n", new HashMap<>());
    }

    /**
     * Initialize the api and search for items
     * @param searchIndex
     * @param keywords
     * @return 
     * @throws java.io.UnsupportedEncodingException
     * @throws de.malkusch.amazon.ecs.exception.RequestException
     */
    public Items search(String searchIndex, String keywords) throws UnsupportedEncodingException, RequestException {
        initApi();
        return searchItems(searchIndex, keywords);
    }

    private void initApi() throws UnsupportedEncodingException {
        Properties props = new Properties();
        props.setProperty("amazon.accessKey", this.properties.getAccessKey());
        props.setProperty("amazon.secretKey", this.properties.getSecretKey());
        props.setProperty("amazon.associateTag", this.properties.getAssociateTag());
        Configuration configuration = new PropertiesConfiguration(props);
        api = new ProductAdvertisingAPI(configuration,
                new AWSECommerceService().getAWSECommerceServicePortUS());
    }

    private Items searchItems(String searchIndex, String keywords) throws RequestException {
        ItemSearchRequest itemSearchRequest = new ItemSearchRequest();
        itemSearchRequest.setSearchIndex(searchIndex);
        itemSearchRequest.setKeywords(keywords);
        return api.getItemSearch().call(itemSearchRequest);
    }
}
