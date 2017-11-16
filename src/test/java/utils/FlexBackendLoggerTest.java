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
public class FlexBackendLoggerTest {
    
    public FlexBackendLoggerTest() {
    }

    /**
     * Test of log method, of class FlexBackendLogger.
     */
    @Test
    public void testLog() {
        System.out.println("log");
        FlexBackendLogger logger = new FlexBackendLogger(FlexBackendLoggerTest.class);
        logger.on();
        logger.log("%s", "OK");
    }

    /**
     * Test of info method, of class FlexBackendLogger.
     */
    @Test
    public void testInfo() {
        System.out.println("info");
        FlexBackendLogger logger = new FlexBackendLogger(FlexBackendLoggerTest.class);
        logger.info("%s", "OK");
    }

    /**
     * Test of error method, of class FlexBackendLogger.
     */
    @Test
    public void testError() {
        System.out.println("error");
        FlexBackendLogger logger = new FlexBackendLogger(FlexBackendLoggerTest.class);
        logger.error("%s", "OK");
    }

    /**
     * Test of on method, of class FlexBackendLogger.
     */
    @Test
    public void testOn() {
        System.out.println("on");
        FlexBackendLogger logger = new FlexBackendLogger(FlexBackendLoggerTest.class);
        logger.on();
    }

    /**
     * Test of off method, of class FlexBackendLogger.
     */
    @Test
    public void testOff() {
        System.out.println("off");
        FlexBackendLogger logger = new FlexBackendLogger(FlexBackendLoggerTest.class);
        logger.off();
    }

    /**
     * Test of isOn method, of class FlexBackendLogger.
     */
    @Test
    public void testIsOn() {
        System.out.println("isOn");
        FlexBackendLogger logger = new FlexBackendLogger(FlexBackendLoggerTest.class);
        logger.on();
        assertTrue(logger.isOn());
    }
    
}
