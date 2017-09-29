/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.Neo4jSessionFactory;
import javax.annotation.concurrent.NotThreadSafe;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.neo4j.ogm.testutil.TestServer;

/**
 *
 * @author zua
 */
@NotThreadSafe
public class Neo4jTest {
    
    private static TestServer testServer;

    private static int getRandomPort() {
        return 60000 + (int) (Math.random()*1000);
    }

    public Neo4jTest() {
    }
    
    
    @BeforeClass
    public static void startDB() {
        testServer = new TestServer(false, false, 10, getRandomPort());
        testServer.startServer();
        System.out.println("DB Username is " + testServer.getUsername());
        System.out.println("DB Password is " + testServer.getPassword());
        System.out.println("Server is running? " + testServer.getGraphDatabaseService().isAvailable(10000)); 
        System.out.println("Port " + testServer.getPort()); 
    }
    
    @Before
    public void cleanUp() {
        System.out.println("-- CLEANUP --");
        Neo4jSessionFactory.getInstance().getNeo4jSession().purgeDatabase();
    }
    
    @AfterClass
    public static void stopDB() {
        testServer.shutdown();
        System.out.println("-- Server Shutdown --");
    }
    
    
}
