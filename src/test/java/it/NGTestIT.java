/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it;

import backend.session.Neo4jSessionFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author zua
 */
public abstract class NGTestIT {

    private static Neo4jSessionFactory sessionFactory;
    
    public NGTestIT() {
    }

    /**
     * Initialize session factory before running any tests
     */
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Creating session factory...");
        sessionFactory = Neo4jSessionFactory.getInstance();
    }
    
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Purging database...");
        sessionFactory.getNeo4jSession().purgeDatabase();
    }

    public Neo4jSessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
