/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class FlexLoggerTest {
    
    public FlexLoggerTest() {
    }

    /**
     * Test of log method, of class FlexLogger.
     */
    @Test
    public void testLog() {
        System.out.println("log");
        FlexLogger instance = new FlexLogger(FlexLoggertest.class);
        instance.log(format, values);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of info method, of class FlexLogger.
     */
    @Test
    public void testInfo() {
        System.out.println("info");
        String format = "";
        Object[] values = null;
        FlexLogger instance = null;
        instance.info(format, values);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of error method, of class FlexLogger.
     */
    @Test
    public void testError() {
        System.out.println("error");
        String format = "";
        Object[] values = null;
        FlexLogger instance = null;
        instance.error(format, values);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of on method, of class FlexLogger.
     */
    @Test
    public void testOn() {
        System.out.println("on");
        FlexLogger instance = null;
        instance.on();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of off method, of class FlexLogger.
     */
    @Test
    public void testOff() {
        System.out.println("off");
        FlexLogger instance = null;
        instance.off();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isOn method, of class FlexLogger.
     */
    @Test
    public void testIsOn() {
        System.out.println("isOn");
        FlexLogger instance = null;
        boolean expResult = false;
        boolean result = instance.isOn();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
