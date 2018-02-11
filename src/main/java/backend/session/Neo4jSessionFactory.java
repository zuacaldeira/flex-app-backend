/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.session;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

/**
 *
 * @author zua
 */
public class Neo4jSessionFactory {

    private static Neo4jSessionFactory factory;
    private SessionFactory sessionFactory;

    private Neo4jSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            sessionFactory = new SessionFactory(configuration, "db");
        } catch (Exception ex) {
            Configuration configuration = new Configuration();
            configuration.driverConfiguration().setURI(System.getenv("GRAPHENEDB_URL"));
            configuration.autoIndexConfiguration().setAutoIndex("assert");
            sessionFactory = new SessionFactory(configuration, "db");
        }
    }

    public static Neo4jSessionFactory getInstance() {
        if (factory == null) {
            factory = new Neo4jSessionFactory();
        }
        return factory;
    }

    public Session getNeo4jSession() {
        return sessionFactory.openSession();
    }

}
