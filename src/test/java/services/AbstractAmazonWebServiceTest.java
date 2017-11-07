/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.AmazonProperties;
import db.Neo4jSessionFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 *
 * @author zua
 */
public class AbstractAmazonWebServiceTest {

    protected void addPropertiesToTestDB() throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("/Users/zua/Work/apps/properties/flex-app/amazon.properties"));

        AmazonProperties amazonProperties = new AmazonProperties();
        amazonProperties.setAccessKey(properties.getProperty("amazon.accessKey"));
        amazonProperties.setSecretKey(properties.getProperty("amazon.secretKey"));
        amazonProperties.setAssociateTag(properties.getProperty("amazon.associateTag"));

        Neo4jSessionFactory.getInstance().getNeo4jSession().query("CREATE CONSTRAINT ON (n:AmazonProperties) ASSERT n.associateTag IS UNIQUE", new HashMap<>());
        Neo4jSessionFactory.getInstance().getNeo4jSession().save(amazonProperties);
    }

}
