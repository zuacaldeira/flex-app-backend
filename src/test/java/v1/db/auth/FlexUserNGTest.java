/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v1.db.auth;

import v1.db.auth.AuthUserInfo;
import v1.db.auth.FlexUser;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class FlexUserNGTest {
    
    public FlexUserNGTest() {
    }



    /**
     * Test of getUsername method, of class FlexUser.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        FlexUser instance = new FlexUser();
        assertEquals(instance.getUsername(), null);
    }

    /**
     * Test of setUsername method, of class FlexUser.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        FlexUser instance = new FlexUser();
        assertEquals(instance.getUsername(), null);
        instance.setUsername("username");
        assertEquals(instance.getUsername(), "username");
    }

    /**
     * Test of getPassword method, of class FlexUser.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        FlexUser instance = new FlexUser();
        assertEquals(instance.getPassword(), null);
    }

    /**
     * Test of setPassword method, of class FlexUser.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        FlexUser instance = new FlexUser();
        assertEquals(instance.getPassword(), null);
        instance.setPassword("password");
        assertEquals(instance.getPassword(), "password");
    }



    /**
     * Test of getUserInfo method, of class FlexUser.
     */
    @Test
    public void testGetUserInfo() {
        System.out.println("getUserInfo");
        FlexUser instance = new FlexUser();
        assertNull(instance.getUserInfo());
        instance.setUserInfo(new AuthUserInfo());
        assertNotNull(instance.getUserInfo());
    }

    /**
     * Test of setUserInfo method, of class FlexUser.
     */
    @Test
    public void testSetUserInfo() {
        System.out.println("setUserInfo");
        FlexUser instance = new FlexUser();
        assertNull(instance.getUserInfo());
        instance.setUserInfo(new AuthUserInfo());
        assertNotNull(instance.getUserInfo());
    }
    
}
