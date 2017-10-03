/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.NewsAuthor;
import org.junit.Test;
import static org.junit.Assert.*;
import org.neo4j.ogm.cypher.query.SortOrder;

/**
 *
 * @author zua
 */
public class NewsAuthorServiceTest {

    public NewsAuthorServiceTest() {
    }

    /**
     * Test of getClassType method, of class NewsAuthorService.
     */
    @Test
    public void testGetClassType() throws Exception {
        System.out.println("getClassType");
        NewsAuthorService instance = new NewsAuthorService();
        assertEquals(NewsAuthor.class, instance.getClassType());
    }

    /**
     * Test of getSortOrderAsc method, of class NewsAuthorService.
     */
    @Test
    public void testGetSortOrderAsc() throws Exception {
        System.out.println("getSortOrderAsc");
        NewsAuthorService instance = new NewsAuthorService();
        assertEquals(new SortOrder().add(SortOrder.Direction.ASC, "name").toString(), instance.getSortOrderAsc().toString());
    }

    /**
     * Test of getSortOrderDesc method, of class NewsAuthorService.
     */
    @Test
    public void testGetSortOrderDesc() throws Exception {
        System.out.println("getSortOrderDesc");
        NewsAuthorService instance = new NewsAuthorService();
        assertEquals(new SortOrder().add(SortOrder.Direction.DESC, "name").toString(), instance.getSortOrderDesc().toString());
    }

}
