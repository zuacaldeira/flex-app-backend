/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v1.backend.session;

import org.neo4j.ogm.session.SessionFactory;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class Neo4jSessionFactoryNGTest {
    
    public Neo4jSessionFactoryNGTest() {
    }

    @BeforeClass
    public static void initFactory() {
    }
    /**
     * Test of getInstance method, of class Neo4jSessionFactory.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        SessionFactory factory = new SessionFactory("db");
        assertNotNull(factory);
    }

    /**
     * Test of getNeo4jSession method, of class Neo4jSessionFactory.
     */
    @Test
    public void testGetNeo4jSession() {
        System.out.println("getNeo4jSession");
        SessionFactory factory = new SessionFactory("db");
        assertNotNull(factory.openSession());
    }
    
}
