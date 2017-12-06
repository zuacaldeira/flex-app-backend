/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.HashSet;
import java.util.Set;
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
     * Test of getPropertyName method, of class FlexUser.
     */
    @Test
    public void testGetPropertyName() {
        System.out.println("getPropertyName");
        FlexUser instance = new FlexUser();
        String result = instance.getPropertyName();
        assertEquals(result, "username");
    }

    /**
     * Test of getPropertyValue method, of class FlexUser.
     */
    @Test
    public void testGetPropertyValue() {
        System.out.println("getPropertyValue");
        FlexUser instance = new FlexUser();
        assertNull(instance.getPropertyValue());
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
     * Test of read method, of class FlexUser.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        FlexUser instance = new FlexUser();
        
        assertNotNull(instance.getRead());
        assertTrue(instance.getRead().isEmpty());
        
        GraphEntity article = new NewsArticle();
        instance.read(article);
        
        assertFalse(instance.getRead().isEmpty());
    }

    /**
     * Test of hasRead method, of class FlexUser.
     */
    @Test
    public void testHasRead() {
        System.out.println("hasRead");
        FlexUser instance = new FlexUser();
        
        assertNotNull(instance.getRead());
        assertTrue(instance.getRead().isEmpty());
        
        GraphEntity article = new NewsArticle();
        instance.read(article);
        
        assertFalse(instance.getRead().isEmpty());
        assertTrue(instance.hasRead(article));
    }

    /**
     * Test of favorite method, of class FlexUser.
     */
    @Test
    public void testFavorite() {
        System.out.println("favorite");
        FlexUser instance = new FlexUser();
        
        assertNotNull(instance.getFavorite());
        assertTrue(instance.getFavorite().isEmpty());
        
        GraphEntity article = new NewsArticle();
        instance.favorite(article);
        
        assertFalse(instance.getFavorite().isEmpty());
    }

    /**
     * Test of hasFavorite method, of class FlexUser.
     */
    @Test
    public void testHasFavorite() {
        System.out.println("hasFavorite");
        FlexUser instance = new FlexUser();
        
        assertNotNull(instance.getFavorite());
        assertTrue(instance.getFavorite().isEmpty());
        
        GraphEntity article = new NewsArticle();
        instance.favorite(article);
        
        assertFalse(instance.getFavorite().isEmpty());
        assertTrue(instance.hasFavorite(article));
    }

    /**
     * Test of fake method, of class FlexUser.
     */
    @Test
    public void testFake() {
        System.out.println("fake");
        FlexUser instance = new FlexUser();
        
        assertNotNull(instance.getFake());
        assertTrue(instance.getFake().isEmpty());
        
        GraphEntity article = new NewsArticle();
        instance.fake(article);
        
        assertFalse(instance.getFake().isEmpty());
    }

    /**
     * Test of hasFake method, of class FlexUser.
     */
    @Test
    public void testHasFake() {
        System.out.println("hasFake");
        FlexUser instance = new FlexUser();
        
        assertNotNull(instance.getFake());
        assertTrue(instance.getFake().isEmpty());
        
        GraphEntity article = new NewsArticle();
        instance.fake(article);
        
        assertFalse(instance.getFake().isEmpty());
        assertTrue(instance.hasFake(article));
    }

    /**
     * Test of hasUrl method, of class FlexUser.
     */
    @Test
    public void testHasUrl() {
        System.out.println("hasUrl");
        FlexUser instance = new FlexUser();
        assertFalse(instance.hasUrl());
        assertNull(instance.getUrl());
    }

    /**
     * Test of getUrl method, of class FlexUser.
     */
    @Test
    public void testGetUrl() {
        System.out.println("getUrl");
        FlexUser instance = new FlexUser();
        assertFalse(instance.hasUrl());
        assertNull(instance.getUrl());
    }

    /**
     * Test of getRead method, of class FlexUser.
     */
    @Test
    public void testGetRead() {
        System.out.println("getRead");
        FlexUser instance = new FlexUser();
        assertTrue(instance.getRead().isEmpty());
        instance.read(new NewsArticle());
        assertFalse(instance.getRead().isEmpty());
    }

    /**
     * Test of getFavorite method, of class FlexUser.
     */
    @Test
    public void testGetFavorite() {
        System.out.println("getFavorite");
        FlexUser instance = new FlexUser();
        assertTrue(instance.getFavorite().isEmpty());
        instance.favorite(new NewsArticle());
        assertFalse(instance.getFavorite().isEmpty());
    }

    /**
     * Test of getFake method, of class FlexUser.
     */
    @Test
    public void testGetFake() {
        System.out.println("getFake");
        FlexUser instance = new FlexUser();
        assertTrue(instance.getFake().isEmpty());
        instance.fake(new NewsArticle());
        assertFalse(instance.getFake().isEmpty());
    }

    /**
     * Test of setRead method, of class FlexUser.
     */
    @Test
    public void testSetRead() {
        System.out.println("setRead");
        FlexUser instance = new FlexUser();
        Set<GraphEntity> read = new HashSet<>();
        read.add(new NewsArticle());
        instance.setRead(read);
        assertFalse(instance.getRead().isEmpty());
    }

    /**
     * Test of setFavorite method, of class FlexUser.
     */
    @Test
    public void testSetFavorite() {
        System.out.println("setFavorite");
        FlexUser instance = new FlexUser();
        Set<GraphEntity> favorite = new HashSet<>();
        favorite.add(new NewsArticle());
        instance.setFavorite(favorite);
        assertFalse(instance.getFavorite().isEmpty());
    }

    /**
     * Test of setFake method, of class FlexUser.
     */
    @Test
    public void testSetFake() {
        System.out.println("setFake");
        FlexUser instance = new FlexUser();
        Set<GraphEntity> fake = new HashSet<>();
        fake.add(new NewsArticle());
        instance.setFake(fake);
        assertFalse(instance.getFake().isEmpty());
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
