/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.Neo4jSessionFactory;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;

/**
 *
 * @author zua
 */
@Singleton
@LocalBean
@Startup
public class StarterSingletonBean {

    private Neo4jSessionFactory sessionFactory;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PostConstruct
    private void init() {
        sessionFactory = Neo4jSessionFactory.getInstance();
    }
}
