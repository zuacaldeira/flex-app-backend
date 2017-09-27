/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.FlexUser;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author zua
 */
public class FlexUserServiceTest extends Neo4jTest {

    private String TEST_USERNAME = "test:username";
    private String TEST_PASSWORD = "test:password";

    public FlexUserServiceTest() {
        super();
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
    }
    
    @Test
    public void testRegister() {
        FlexUserService service = new FlexUserService();
        assertNotNull(service.register(TEST_USERNAME, TEST_PASSWORD));
        assertNotNull(service.login(TEST_USERNAME, TEST_PASSWORD));
    }
 
}
