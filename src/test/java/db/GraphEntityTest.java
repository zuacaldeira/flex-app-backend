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
public class GraphEntityTest {

    public GraphEntityTest() {
    }

    @DataProvider
    public static Object[][] getIdData() {
        GraphEntity instance = new GraphEntityImpl();
        instance.setId(Long.MIN_VALUE);

        Object[][] result = new Object[][]{
            {new GraphEntityImpl(), null},
            {instance, Long.MIN_VALUE}
        };
        return result;
    }

    @DataProvider
    public static Object[][] setIdData() {
        GraphEntity instance = new GraphEntityImpl();
        instance.setId(Long.MIN_VALUE);

        Object[][] result = new Object[][]{
            {new GraphEntityImpl(), null, 1L},
            {instance, Long.MIN_VALUE, 1L}
        };
        return result;
    }

    @DataProvider
    public static Object[][] getPropertyData() {
        GraphEntity instance = new GraphEntityImpl();
        instance.setId(Long.MIN_VALUE);

        Object[][] result = new Object[][]{
            {new GraphEntityImpl()}
        };
        return result;
    }

    @DataProvider
    public static Object[][] equalsData() {
        GraphEntity instance = new GraphEntityImpl();
        instance.setId(Long.MIN_VALUE);

        Object[][] result = new Object[][]{
            {new GraphEntityImpl(), new GraphEntityImpl(), true},
            {instance, instance, true},
            {instance, new GraphEntityImpl(), false},
            {instance, "Please...", false}
        };
        return result;
    }

    /**
     * Test of getId method, of class GraphEntity.
     *
     * @param instance A test graph entity.
     * @param expectedId It's expected id.
     */
    @Test
    @UseDataProvider("getIdData")
    public void testGetId(GraphEntity instance, Long expectedId) {
        System.out.println("getId");
        assertEquals(expectedId, instance.getId());
    }

    /**
     * Test of setId method, of class GraphEntity.
     *
     * @param instance A test graph entity.
     * @param oldId The old id.
     * @param newId The new id.
     */
    @Test
    @UseDataProvider("setIdData")
    public void testSetId(GraphEntity instance, Long oldId, Long newId) {
        System.out.println("setId");
        assertEquals(oldId, instance.getId());
        instance.setId(newId);
        assertEquals(newId, instance.getId());
    }

    /**
     * Test of getPropertyName method, of class GraphEntity.
     *
     * @param instance A test graph entity.
     */
    @Test
    @UseDataProvider("getPropertyData")
    public void testGetPropertyName(GraphEntity instance) {
        System.out.println("getPropertyName");
        assertEquals(null, instance.getPropertyValue());
    }

    /**
     * Test of getPropertyValue method, of class GraphEntity.
     *
     * @param instance A test graph entity.
     */
    @Test
    @UseDataProvider("getPropertyData")
    public void testGetPropertyValue(GraphEntity instance) {
        System.out.println("getPropertyValue");
        assertEquals(null, instance.getPropertyValue());
    }

    /**
     * Test of hasUrl method, of class GraphEntity.
     *
     * @param instance A test graph entity.
     */
    @Test
    @UseDataProvider("getPropertyData")
    public void testHasUrl(GraphEntity instance) {
        System.out.println("hasUrl");
        assertFalse(instance.hasUrl());
    }

    /**
     * Test of getUrl method, of class GraphEntity.
     *
     * @param instance A test graph entity.
     */
    @Test
    @UseDataProvider("getPropertyData")
    public void testGetUrl(GraphEntity instance) {
        System.out.println("getUrl");
        assertNull(instance.getUrl());
    }

    /**
     * Test of equals method, of class GraphEntity.
     *
     * @param entity The graph entity we want to compare.
     * @param other The other object we are comparing to.
     */
    @Test
    @UseDataProvider("equalsData")
    public void testEquals(GraphEntity entity, Object other, boolean expected) {
        System.out.println("equals");
        assertEquals(expected, entity.equals(other));
    }

    /**
     * Test of hashCode method, of class GraphEntity.
     *
     * @param entity The graph entity we want to compare.
     * @param other The other object we are comparing to.
     */
    @Test
    @UseDataProvider("equalsData")
    public void testHashCode(GraphEntity entity, Object other, boolean value) {
        System.out.println("hashCode");
        if(value) {
            assertEquals(entity.hashCode(), other.hashCode());
        }
        else {
            assertNotEquals(entity.hashCode(), other.hashCode());
        }
    }

    public static class GraphEntityImpl extends GraphEntity {

        public String getPropertyName() {
            return "name";
        }

        public String getPropertyValue() {
            return null;
        }

        public boolean hasUrl() {
            return false;
        }

        public String getUrl() {
            return null;
        }
    }

}
