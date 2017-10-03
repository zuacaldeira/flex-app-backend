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
        FlexLogger logger = new FlexLogger(FlexLoggerTest.class);
        logger.on();
        logger.log("%s", "OK");
    }

    /**
     * Test of info method, of class FlexLogger.
     */
    @Test
    public void testInfo() {
        System.out.println("info");
        FlexLogger logger = new FlexLogger(FlexLoggerTest.class);
        logger.info("%s", "OK");
    }

    /**
     * Test of error method, of class FlexLogger.
     */
    @Test
    public void testError() {
        System.out.println("error");
        FlexLogger logger = new FlexLogger(FlexLoggerTest.class);
        logger.error("%s", "OK");
    }

    /**
     * Test of on method, of class FlexLogger.
     */
    @Test
    public void testOn() {
        System.out.println("on");
        FlexLogger logger = new FlexLogger(FlexLoggerTest.class);
        logger.on();
    }

    /**
     * Test of off method, of class FlexLogger.
     */
    @Test
    public void testOff() {
        System.out.println("off");
        FlexLogger logger = new FlexLogger(FlexLoggerTest.class);
        logger.off();
    }

    /**
     * Test of isOn method, of class FlexLogger.
     */
    @Test
    public void testIsOn() {
        System.out.println("isOn");
        FlexLogger logger = new FlexLogger(FlexLoggerTest.class);
        logger.on();
        assertTrue(logger.isOn());
    }
    
}
