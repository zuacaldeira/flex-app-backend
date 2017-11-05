/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import org.neo4j.ogm.authentication.UsernamePasswordCredentials;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;



/**
 *
 * @author zua
 */
public class Neo4jSessionFactory {

    private static Neo4jSessionFactory factory;

    private final SessionFactory sessionFactory;
    
    private Neo4jSessionFactory() {
        Configuration configuration = new Configuration("ogm.properties");
        UsernamePasswordCredentials userCredentials = (UsernamePasswordCredentials)configuration.driverConfiguration().getCredentials();
        System.out.println(configuration.driverConfiguration().getDriverClassName());
        System.out.println(userCredentials.getUsername());
        System.out.println(userCredentials.getPassword());
        sessionFactory = new SessionFactory(configuration, "db");
    }
    
    public static Neo4jSessionFactory getInstance() {
        if(factory == null) {
            factory = new Neo4jSessionFactory();
        }
        return factory;
    }
    
    public Session getNeo4jSession() {
	return sessionFactory.openSession();
    }

}
