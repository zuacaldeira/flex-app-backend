/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class FlexUserTest {

    public FlexUserTest() {
    }

    @DataProvider
    public static Object[][] usersData() {
        return new Object[][]{
            {new FlexUser()},
            {new FlexUser("U", "P")}
        };
    }

    @DataProvider
    public static Object[][] usernameData() {
        return new Object[][]{
            {new FlexUser(), null},
            {new FlexUser("U", "P"), "U"}
        };
    }

    @DataProvider
    public static Object[][] setUsernameData() {
        return new Object[][]{
            {new FlexUser(), null, "U"},
            {new FlexUser("U", "P"), "U", "U2"}
        };
    }

    @DataProvider
    public static Object[][] passwordData() {
        return new Object[][]{
            {new FlexUser(), null},
            {new FlexUser("U", "P"), "P"}
        };
    }

    @DataProvider
    public static Object[][] setPasswordData() {
        return new Object[][]{
            {new FlexUser(), null, "P"},
            {new FlexUser("U", "P"), "P", "P2"}
        };
    }

    @DataProvider
    public static Object[][] readData() {
        return new Object[][]{
            {new FlexUser(), new NewsArticle()},
            {new FlexUser("U", "P"), new NewsArticle()}
        };
    }

    /**
     * Test of getPropertyName method, of class FlexUser.
     *
     * @param user A test user.
     */
    @Test
    @UseDataProvider("usersData")
    public void testGetPropertyName(FlexUser user) {
        System.out.println("getPropertyName");
        assertEquals("username", user.getPropertyName());
    }

    /**
     * Test of getPropertyValue method, of class FlexUser.
     *
     * @param user A test user.
     */
    @Test
    @UseDataProvider("usersData")
    public void testGetPropertyValue(FlexUser user) {
        System.out.println("getPropertyValue");
        assertEquals(user.getUsername(), user.getPropertyValue());
    }

    /**
     * Test of getUsername method, of class FlexUser.
     *
     * @param user A test user.
     * @param username The test user expected username
     */
    @Test
    @UseDataProvider("usernameData")
    public void testGetUsername(FlexUser user, String username) {
        System.out.println("getUsername");
        assertEquals(username, user.getUsername());
    }

    /**
     * Test of setUsername method, of class FlexUser.
     *
     * @param user A test user.
     * @param oldUsername The user's username before it is set.
     * @param newUsername The new username
     */
    @Test
    @UseDataProvider("setUsernameData")
    public void testSetUsername(FlexUser user, String oldUsername, String newUsername) {
        System.out.println("setUsername");
        assertEquals(oldUsername, user.getUsername());
        user.setUsername(newUsername);
        assertEquals(newUsername, user.getUsername());
    }

    /**
     * Test of getPassword method, of class FlexUser.
     *
     * @param user A test user.
     * @param password The user's password.
     */
    @Test
    @UseDataProvider("passwordData")
    public void testGetPassword(FlexUser user, String password) {
        System.out.println("getPassword");
        assertEquals(password, user.getPassword());
    }

    /**
     * Test of setPassword method, of class FlexUser.
     *
     * @param user A test user.
     * @param oldPassword The user's password.
     * @param newPassword The new password.
     */
    @Test
    @UseDataProvider("setPasswordData")
    public void testSetPassword(FlexUser user, String oldPassword, String newPassword) {
        System.out.println("setPassword");
        assertEquals(oldPassword, user.getPassword());
        user.setPassword(newPassword);
        assertEquals(newPassword, user.getPassword());
    }

    /**
     * Test of read method, of class FlexUser.
     *
     * @param user A test user.
     * @param article The article to be marked as read.
     */
    @Test
    @UseDataProvider("readData")
    public void testRead(FlexUser user, NewsArticle article) {
        System.out.println("read");
        assertFalse(user.hasRead(article));
        user.read(article);
        assertTrue(user.hasRead(article));
    }

    /**
     * Test of hasRead method, of class FlexUser.
     *
     * @param user The test user.
     * @param article An article to be read.
     */
    @Test
    @UseDataProvider("readData")
    public void testHasRead(FlexUser user, NewsArticle article) {
        System.out.println("hasRead");
        assertFalse(user.hasRead(article));
        user.read(article);
        assertTrue(user.hasRead(article));
    }

    /**
     * Test of favorite method, of class FlexUser.
     *
     * @param user The test user.
     * @param article An article to be read.
     */
    @Test
    @UseDataProvider("readData")
    public void testFavorite(FlexUser user, NewsArticle article) {
        System.out.println("favorite");
        assertFalse(user.hasFavorite(article));
        user.favorite(article);
        assertTrue(user.hasFavorite(article));
    }

    /**
     * Test of hasFavorite method, of class FlexUser.
     *
     * @param user The test user.
     * @param article An article to be read.
     */
    @Test
    @UseDataProvider("readData")
    public void testHasFavorite(FlexUser user, NewsArticle article) {
        System.out.println("hasFavorite");
        assertFalse(user.hasFavorite(article));
        user.favorite(article);
        assertTrue(user.hasFavorite(article));
    }

    /**
     * Test of fake method, of class FlexUser.
     *
     * @param user The test user.
     * @param article An article to be read.
     */
    @Test
    @UseDataProvider("readData")
    public void testFake(FlexUser user, NewsArticle article) {
        System.out.println("fake");
        assertFalse(user.hasFake(article));
        user.fake(article);
        assertTrue(user.hasFake(article));
    }

    /**
     * Test of hasFake method, of class FlexUser.
     *
     * @param user The test user.
     * @param article An article to be read.
     */
    @Test
    @UseDataProvider("readData")
    public void testHasFake(FlexUser user, NewsArticle article) {
        System.out.println("hasFake");
        assertFalse(user.hasFake(article));
        user.fake(article);
        assertTrue(user.hasFake(article));
    }

    /**
     * Test of hasUrl method, of class FlexUser.
     *
     * @param user The test user.
     */
    @Test
    @UseDataProvider("usersData")
    public void testHasUrl(FlexUser user) {
        System.out.println("hasUrl");
        assertFalse(user.hasUrl());
    }

    /**
     * Test of getUrl method, of class FlexUser.
     *
     * @param user A test user.
     */
    @Test
    @UseDataProvider("usersData")
    public void testGetUrl(FlexUser user) {
        System.out.println("getUrl");
        assertNull(user.getUrl());
    }

    /**
     * Test of getRead method, of class FlexUser.
     * @param user The test user.
     * @param article An article to be read.
     */
    @Test
    @UseDataProvider("readData")
    public void testGetRead(FlexUser user, NewsArticle article) {
        System.out.println("getRead");
        assertTrue(user.getRead().isEmpty());
        user.read(article);
        assertFalse(user.getRead().isEmpty());
    }

    /**
     * Test of getFavorite method, of class FlexUser.
     * @param user The test user.
     * @param article An article to be read.
     */
    @Test
    @UseDataProvider("readData")
    public void testGetFavorite(FlexUser user, NewsArticle article) {
        System.out.println("getFavorite");
        assertTrue(user.getFavorite().isEmpty());
        user.favorite(article);
        assertFalse(user.getFavorite().isEmpty());
    }

    /**
     * Test of getFake method, of class FlexUser.
     * @param user The test user.
     * @param article An article to be read.
     */
    @Test
    @UseDataProvider("readData")
    public void testGetFake(FlexUser user, NewsArticle article) {
        System.out.println("getFavorite");
        assertTrue(user.getFake().isEmpty());
        user.fake(article);
        assertFalse(user.getFake().isEmpty());
    }

    /**
     * Test of setRead method, of class FlexUser.
     * @param user The test user.
     * @param article An article to be read.
     */
    @Test
    @UseDataProvider("readData")
    public void testSetRead(FlexUser user, NewsArticle article) {
        System.out.println("setRead");
        assertTrue(user.getRead().isEmpty());
        user.read(article);
        assertFalse(user.getRead().isEmpty());
    }

    /**
     * Test of setFavorite method, of class FlexUser.
     * @param user The test user.
     * @param article An article to be read.
     */
    @Test
    @UseDataProvider("readData")
    public void testSetFavorite(FlexUser user, NewsArticle article) {
        System.out.println("setFavorite");
        assertTrue(user.getFavorite().isEmpty());
        user.favorite(article);
        assertFalse(user.getFavorite().isEmpty());
    }

    /**
     * Test of setFake method, of class FlexUser.
     * @param user The test user.
     * @param article An article to be read.
     */
    @Test
    @UseDataProvider("readData")
    public void testSetFake(FlexUser user, NewsArticle article) {
        System.out.println("setFavorite");
        assertTrue(user.getFake().isEmpty());
        user.fake(article);
        assertFalse(user.getFake().isEmpty());
    }

}
