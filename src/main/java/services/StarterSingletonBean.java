/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import factory.Neo4jSessionFactory;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

/**
 *
 * @author zua
 */
@Singleton
public class StarterSingletonBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PostConstruct
    private void init() {
        Neo4jSessionFactory sessionFactory = Neo4jSessionFactory.getInstance();
    }
}
