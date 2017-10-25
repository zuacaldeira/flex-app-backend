/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.FlexUser;
import db.Neo4jSessionFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.ogm.cypher.query.SortOrder;

/**
 *
 * @author zua
 */
public class FlexUserServiceTestIT  {

    private String TEST_USERNAME = "test:username";
    private String TEST_PASSWORD = "test:password";

    public FlexUserServiceTestIT() {
        super();
    }

    @Before
    public void cleanUp() {
        System.out.println("-- CLEANUP --");
        Neo4jSessionFactory.getInstance().getNeo4jSession().purgeDatabase();
    }
    
    @Test
    public void testFindAllUsers() {
        System.out.println("\ttestFindAllUsers");
        FlexUserService service = new FlexUserService();
        assertTrue(service.findAllUsers().isEmpty());
        service.register(TEST_USERNAME, TEST_PASSWORD);
        assertFalse(service.findAllUsers().isEmpty());
    }
    
    @Test
    public void testFindUserNamed() {
        FlexUserService service = new FlexUserService();
        assertNull(service.findUserNamed(TEST_USERNAME));
        service.register(TEST_USERNAME, TEST_PASSWORD);
        assertNotNull(service.findUserNamed(TEST_USERNAME));
    }
    
    @Test
    public void testLogin() {
        FlexUserService service = new FlexUserService();
        service.register(TEST_USERNAME, TEST_PASSWORD);
        assertNotNull(service.login(TEST_USERNAME, TEST_PASSWORD));
        assertNull(service.login(TEST_USERNAME, "otherPassword"));
        assertNull(service.login("otherUsername", TEST_PASSWORD));
        assertNull(service.login("otherUsername", "otherPassword"));
    }
    
    @Test
    public void testRegister() {
        FlexUserService service = new FlexUserService();
        assertNotNull(service.register(TEST_USERNAME, TEST_PASSWORD));
        assertNotNull(service.login(TEST_USERNAME, TEST_PASSWORD));
    }
    
    @Test
    public void testSortOrder() {
        FlexUserService service = new FlexUserService();
        assertEquals(service.getSortOrderAsc().toString(), new SortOrder().add(SortOrder.Direction.ASC, "username").toString());
        assertEquals(service.getSortOrderDesc().toString(), new SortOrder().add(SortOrder.Direction.DESC, "username").toString());
    }
    
    @Test
    public void testDelete() {
        FlexUserService service = new FlexUserService();
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        service.save(user);
        assertNotNull(service.find(user));        
        service.delete(user);
        assertNull(service.find(user));        
    }
}
