/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import session.Neo4jSessionFactory;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Asynchronous;

/**
 *
 * @author zua
 */
//@Singleton
//@Startup
public class DBStarterSingletonBean {

    @PostConstruct
    @Asynchronous
    private void init() {
        try {
            Neo4jSessionFactory sessionFactory = Neo4jSessionFactory.getInstance();
        } catch(Exception ex) {
            
        }
    }
    
    @PreDestroy
    private void finish() {
        System.out.println("Destroying DBStarterSingletonBean");
    }
}
