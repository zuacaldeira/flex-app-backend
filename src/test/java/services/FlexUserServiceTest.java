/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.FlexUser;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.neo4j.ogm.cypher.query.SortOrder;
import org.neo4j.ogm.session.Neo4jException;

/**
 *
 * @author zua
 */
public class FlexUserServiceTest extends Neo4jTest {

    public FlexUserServiceTest() {
    }

    /**
     * Test of findAll method, of class FlexUserService.
     *
     * @throws java.lang.Exception Possible runtime exception that may occur.
     */
    @Test
    @Ignore
    public void testFindAll_0args() throws Exception {
        System.out.println("testFindAll_0args");
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAll();
        assertTrue(result.size() == 0);
    }

    /**
     * Test of findAllAsc method, of class FlexUserService.
     */
    @Test
    public void testFindAllAsc_0args() throws Exception {
        System.out.println("testFindAllAsc_0args");
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAllAsc();
        assertTrue(result.size() == 0);
    }

    /**
     * Test of findAllDesc method, of class FlexUserService.
     */
    @Test
    public void testFindAllDesc_0args() throws Exception {
        System.out.println("testFindAllDesc_0args");
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAllDesc();
        if(result.size() > 0) {
            System.out.println("!!!!!!!! FOUND " + result.get(0).getUsername());
        }
        assertTrue(result.size() == 0);
    }

    /**
     * Test of findAll method, of class FlexUserService.
     */
    @Test
    public void testFindAll_int() throws Exception {
        System.out.println("testFindAll_int");
        int limit = 0;
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAll(limit);
        assertTrue(result.size() == 0);
    }

    /**
     * Test of findAllAsc method, of class FlexUserService.
     */
    @Test
    public void testFindAllAsc_int() throws Exception {
        System.out.println("testFindAllAsc_int");
        int limit = 0;
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAllAsc(0);
        assertTrue(result.size() == 0);
    }

    /**
     * Test of findAllDesc method, of class FlexUserService.
     */
    @Test
    public void testFindAllDesc_int() throws Exception {
        System.out.println("testFindAllDesc_int");
        int limit = 0;
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAllDesc(0);
        assertTrue(result.size() == 0);
    }

    /**
     * Test of findAll method, of class FlexUserService.
     */
    @Test
    public void testFindAll_String() throws Exception {
        System.out.println("testFindAll_String");
        String username = "test:username";
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAll(username);
        assertTrue(result.size() == 0);
    }

    /**
     * Test of findAllAsc method, of class FlexUserService.
     */
    @Test
    public void testFindAllAsc_String() throws Exception {
        System.out.println("testFindAllAsc_String");
        String username = "test:username";
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAllAsc(username);
        assertTrue(result.size() == 0);
    }

    /**
     * Test of findAllDesc method, of class FlexUserService.
     */
    @Test
    public void testFindAllDesc_String() throws Exception {
        System.out.println("testFindAllDesc_String");
        String username = "test:username";
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAllDesc(username);
        assertTrue(result.size() == 0);
    }

    /**
     * Test of findAll method, of class FlexUserService.
     */
    @Test
    public void testFindAll_String_int() throws Exception {
        System.out.println("testFindAll_String_int");
        String username = "test:username";
        int limit = 0;
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAll(username, 0);
        assertTrue(result.size() == 0);
    }

    /**
     * Test of findAllAsc method, of class FlexUserService.
     */
    @Test
    public void testFindAllAsc_String_int() throws Exception {
        System.out.println("testFindAllAsc_String_int");
        String username = "test:username";
        int limit = 0;
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAllAsc(username, 0);
        assertTrue(result.size() == 0);
    }

    /**
     * Test of findAllDesc method, of class FlexUserService.
     */
    @Test
    public void testFindAllDesc_String_int() throws Exception {
        System.out.println("testFindAllDesc_String_int");
        String username = "test:username";
        int limit = 0;
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAllDesc(username, 0);
        assertTrue(result.size() == 0);
    }

    /**
     * Test of findAll method, of class FlexUserService.
     */
    @Test
    public void testFindAll_String_Object() throws Exception {
        System.out.println("testFindAll_String_Object");
        String property = "username";
        Object value = "test:username";
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAll(property, value);
        assertTrue(result.size() == 0);
    }

    /**
     * Test of findAllAsc method, of class FlexUserService.
     */
    @Test
    public void testFindAllAsc_String_Object() throws Exception {
        System.out.println("testFindAllAsc_String_Object");
        String property = "username";
        Object value = "test:username";
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAllAsc(property, value);
        //assertTrue(result.size() == 0);
    }

    /**
     * Test of findAllDesc method, of class FlexUserService.
     */
    @Test
    public void testFindAllDesc_String_Object() throws Exception {
        System.out.println("testFindAllDesc_String_Object");
        String property = "username";
        Object value = "test:username";
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAllDesc(property, value);
        //assertTrue(result.size() == 0);
    }

    /**
     * Test of findAll method, of class FlexUserService.
     */
    @Test
    public void testFindAll_3args_1() throws Exception {
        System.out.println("testFindAll_3args_1");
        int limit = 0;
        String property = "username";
        Object value = "test:username";
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAll(property, value, limit);
                assertTrue(result.size() == 0);;
    }

    /**
     * Test of findAllAsc method, of class FlexUserService.
     */
    @Test
    public void testFindAllAsc_3args_1() throws Exception {
        System.out.println("testFindAllAsc_3args_1");
        int limit = 0;
        String property = "username";
        Object value = "test:username";
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAllAsc(property, value, limit);
                assertTrue(result.size() == 0);;
    }

    /**
     * Test of findAllDesc method, of class FlexUserService.
     */
    @Test
    public void testFindAllDesc_3args_1() throws Exception {
        System.out.println("testFindAllDesc_3args_1");
        int limit = 0;
        String property = "username";
        Object value = "test:username";
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAllDesc(property, value, limit);
                assertTrue(result.size() == 0);;
    }

    /**
     * Test of findAll method, of class FlexUserService.
     */
    @Test
    public void testFindAll_3args_2() throws Exception {
        System.out.println("testFindAll_3args_2");
        int limit = 0;
        String property = "username";
        Object value = "test:username";
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAll(property, value, limit);
                assertTrue(result.size() == 0);;
    }

    /**
     * Test of findAllAsc method, of class FlexUserService.
     */
    @Test
    public void testFindAllAsc_3args_2() throws Exception {
        System.out.println("testFindAllAsc_3args_2");
        int limit = 0;
        String property = "username";
        Object value = "test:username";
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAllAsc(property, value, limit);
                assertTrue(result.size() == 0);;
    }

    /**
     * Test of findAllDesc method, of class FlexUserService.
     */
    @Test
    public void testFindAllDesc_3args_2() throws Exception {
        System.out.println("testFindAllDesc_3args_2");
        int limit = 0;
        String property = "username";
        Object value = "test:username";
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAllDesc(property, value, limit);
                assertTrue(result.size() == 0);;
    }

    /**
     * Test of findAll method, of class FlexUserService.
     */
    @Test
    public void testFindAll_4args() throws Exception {
        System.out.println("findAll");
        String username = "test:username";
        int limit = 0;
        String property = "username";
        Object value = "test:username";
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAll(username, property, value, limit);
        //assertTrue(result.size() == 0);
    }

    /**
     * Test of findAllAsc method, of class FlexUserService.
     */
    @Test
    public void testFindAllAsc_4args() throws Exception {
        System.out.println("findAllAsc");
        String username = "test:username";
        int limit = 0;
        String property = "username";
        Object value = "test:username";
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAllAsc(username, property, value, limit);
        assertTrue(result.size() == 0);
    }

    /**
     * Test of findAllDesc method, of class FlexUserService.
     */
    @Test
    public void testFindAllDesc_4args() throws Exception {
        System.out.println("findAllDesc");
        String username = "test:username";
        int limit = 0;
        String property = "username";
        Object value = "test:username";
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> result = flexUserService.findAllDesc(username, property, value, limit);
        assertTrue(result.size() == 0);
    }

    /**
     * Test of find method, of class FlexUserService.
     */
    @Test(expected=Neo4jException.class)
    public void testFind_Long() throws Exception {
        System.out.println("find");
        FlexUserService flexUserService = new FlexUserService();
        List<FlexUser> all = flexUserService.findAll();
        if(all.size() > 0) {
            FlexUser input = all.get(0);
            FlexUser result = flexUserService.find(input.getId());
            assertNotNull(result);
        }
        else {
            Long id = 1L;
            FlexUser result = flexUserService.find(id);
            assertNull(result);
        }
    }

    /**
     * Test of find method, of class FlexUserService.
     */
    @Test
    public void testFind_GenericType() throws Exception {
        System.out.println("find");
        FlexUser object = new FlexUser("test:new", "test:new");
        FlexUserService instance = new FlexUserService();
        FlexUser result = instance.find(object);
        assertNull(result);
    }

    /**
     * Test of save method, of class FlexUserService.
     */
    @Test
    @Ignore
    public void testSave() throws Exception {
        System.out.println("save");
        FlexUserService instance = new FlexUserService();

        FlexUser object = new FlexUser("test:username", "test:password");
        instance.save(object);
        
        FlexUser expResult = instance.find(object);
        assertEquals(expResult, object);
    }

    /**
     * Test of delete method, of class FlexUserService.
     */
    @Test(expected=Neo4jException.class)
    public void testDelete() throws Exception {
        System.out.println("delete");
        FlexUserService instance = new FlexUserService();

        FlexUser object = new FlexUser("test:username", "test:password");
        instance.save(object);

        FlexUser savedUser = instance.find(object);
        instance.delete(savedUser.getId());

        assertEquals(savedUser, object);

        FlexUser expectedUser = instance.find(object);
        assertNull(expectedUser);
    }

    /**
     * Test of count method, of class FlexUserService.
     */
    @Test
    public void testCount() throws Exception {
        System.out.println("count");
        FlexUserService instance = new FlexUserService();
        long count1 = instance.count();
        //instance.save(new FlexUser("test:username", "test_password"));
        long count2 = instance.count();
        assertTrue(count2 >= count1);
    }

    /**
     * Test of executeQuery method, of class FlexUserService.
     */
    @Test
    @Ignore
    public void testExecuteQuery() throws Exception {
        System.out.println("executeQuery");
        String query = "MATCH (u:FlexUser) RETURN u";
        FlexUserService instance = new FlexUserService();
        LinkedList<FlexUser> result = instance.executeQuery(query);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of getClassType method, of class FlexUserService.
     */
    @Test
    public void testGetClassType() throws Exception {
        System.out.println("getClassType");
        FlexUserService instance = new FlexUserService();
        Class<FlexUser> expResult = FlexUser.class;
        Class<FlexUser> result = instance.getClassType();
        assertEquals(expResult, result);
    }

    /**
     * Test of login method, of class FlexUserService.
     */
    @Test
    @Ignore
    public void testLogin() throws Exception {
        System.out.println("login");
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        //FlexUserServiceInterface instance = (FlexUserServiceInterface) container.getContext().lookup("java:global/classes/FlexUserService");
        FlexUserService instance = new FlexUserService();
        FlexUser expResult = new FlexUser("test:username", "test:password");
        FlexUser result = instance.login(expResult);
        assertNotNull(result);
    }

    /**
     * Test of register method, of class FlexUserService.
     */
    @Test
    @Ignore
    public void testRegister() throws Exception {
        System.out.println("register");
        FlexUserService instance = new FlexUserService();
        FlexUser expResult = new FlexUser("test:username", "test:password");
        FlexUser result = instance.register(expResult);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSortOrderAsc method, of class FlexUserService.
     */
    @Test
    public void testGetSortOrderAsc() throws Exception {
        System.out.println("getSortOrderAsc");
        FlexUserService instance = new FlexUserService();
        SortOrder result = instance.getSortOrderAsc();
        assertEquals(new SortOrder().add(SortOrder.Direction.ASC, "username").toString(), result.toString());
    }

    /**
     * Test of getSortOrderDesc method, of class FlexUserService.
     */
    @Test
    public void testGetSortOrderDesc() throws Exception {
        System.out.println("getSortOrderDesc");
        FlexUserService instance = new FlexUserService();
        SortOrder result = instance.getSortOrderDesc();
        assertEquals(new SortOrder().add(SortOrder.Direction.DESC, "username").toString(), result.toString());
    }

}
