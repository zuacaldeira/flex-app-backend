/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

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
        Configuration configuration = new Configuration();
        configuration.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.bolt.driver.BoltDriver")
                .setURI(System.getenv("GRAPHENEDB_BOLT_URL"))
                .setCredentials(System.getenv("GRAPHENEDB_BOLT_USER"), 
                        System.getenv("GRAPHENEDB_BOLT_PASSWORD"));

        // We pass it as the first argument to a SessionFactory instance
        sessionFactory = new SessionFactory(configuration, "db");

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
