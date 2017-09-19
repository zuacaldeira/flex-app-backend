/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class FlexUserTest {
    
    public FlexUserTest() {
    }

    /**
     * Test of getPropertyName method, of class FlexUser.
     */
    @Test
    public void testGetPropertyName() {
        System.out.println("getPropertyName");
        FlexUser instance = new FlexUser();
        String expResult = "";
        String result = instance.getPropertyName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropertyValue method, of class FlexUser.
     */
    @Test
    public void testGetPropertyValue() {
        System.out.println("getPropertyValue");
        FlexUser instance = new FlexUser();
        String expResult = "";
        String result = instance.getPropertyValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsername method, of class FlexUser.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        FlexUser instance = new FlexUser();
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsername method, of class FlexUser.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        FlexUser instance = new FlexUser();
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class FlexUser.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        FlexUser instance = new FlexUser();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class FlexUser.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        FlexUser instance = new FlexUser();
        instance.setPassword(password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class FlexUser.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        GraphEntity article = null;
        FlexUser instance = new FlexUser();
        instance.read(article);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasRead method, of class FlexUser.
     */
    @Test
    public void testHasRead() {
        System.out.println("hasRead");
        GraphEntity article = null;
        FlexUser instance = new FlexUser();
        boolean expResult = false;
        boolean result = instance.hasRead(article);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of favorite method, of class FlexUser.
     */
    @Test
    public void testFavorite() {
        System.out.println("favorite");
        GraphEntity article = null;
        FlexUser instance = new FlexUser();
        instance.favorite(article);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasFavorite method, of class FlexUser.
     */
    @Test
    public void testHasFavorite() {
        System.out.println("hasFavorite");
        GraphEntity article = null;
        FlexUser instance = new FlexUser();
        boolean expResult = false;
        boolean result = instance.hasFavorite(article);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fake method, of class FlexUser.
     */
    @Test
    public void testFake() {
        System.out.println("fake");
        GraphEntity article = null;
        FlexUser instance = new FlexUser();
        instance.fake(article);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasFake method, of class FlexUser.
     */
    @Test
    public void testHasFake() {
        System.out.println("hasFake");
        GraphEntity article = null;
        FlexUser instance = new FlexUser();
        boolean expResult = false;
        boolean result = instance.hasFake(article);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasUrl method, of class FlexUser.
     */
    @Test
    public void testHasUrl() {
        System.out.println("hasUrl");
        FlexUser instance = new FlexUser();
        boolean expResult = false;
        boolean result = instance.hasUrl();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUrl method, of class FlexUser.
     */
    @Test
    public void testGetUrl() {
        System.out.println("getUrl");
        FlexUser instance = new FlexUser();
        String expResult = "";
        String result = instance.getUrl();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRead method, of class FlexUser.
     */
    @Test
    public void testGetRead() {
        System.out.println("getRead");
        FlexUser instance = new FlexUser();
        Set<GraphEntity> expResult = null;
        Set<GraphEntity> result = instance.getRead();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFavorite method, of class FlexUser.
     */
    @Test
    public void testGetFavorite() {
        System.out.println("getFavorite");
        FlexUser instance = new FlexUser();
        Set<GraphEntity> expResult = null;
        Set<GraphEntity> result = instance.getFavorite();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFake method, of class FlexUser.
     */
    @Test
    public void testGetFake() {
        System.out.println("getFake");
        FlexUser instance = new FlexUser();
        Set<GraphEntity> expResult = null;
        Set<GraphEntity> result = instance.getFake();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRead method, of class FlexUser.
     */
    @Test
    public void testSetRead() {
        System.out.println("setRead");
        Set<GraphEntity> read = null;
        FlexUser instance = new FlexUser();
        instance.setRead(read);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFavorite method, of class FlexUser.
     */
    @Test
    public void testSetFavorite() {
        System.out.println("setFavorite");
        Set<GraphEntity> favorite = null;
        FlexUser instance = new FlexUser();
        instance.setFavorite(favorite);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFake method, of class FlexUser.
     */
    @Test
    public void testSetFake() {
        System.out.println("setFake");
        Set<GraphEntity> fake = null;
        FlexUser instance = new FlexUser();
        instance.setFake(fake);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
