/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.neo4j.ogm.testutil.TestServer;

/**
 *
 * @author zua
 */
public class Neo4jTest {
    
    private static TestServer testServer;
    
    @BeforeClass
    public static void startDB() {
        testServer = new TestServer(false, false, 1);
        testServer.startServer();
        System.out.println("DB Username is " + testServer.getUsername());
        System.out.println("DB Password is " + testServer.getPassword());
        System.out.println("Server is running? " + testServer.getGraphDatabaseService().isAvailable(10000)); 
    }
    
    @Before
    public void cleanUp() {
        System.out.println("-- CLEANUP --");
        testServer.getGraphDatabaseService().execute("MATCH (n) DELETE n");
    }
    
    @AfterClass
    public static void stopDB() {
        testServer.shutdown();
    }
    
    
}
