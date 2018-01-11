/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.services;

import backend.session.Neo4jSessionFactory;
import db.GraphEntity;
import java.util.Collection;
import java.util.HashMap;
import org.neo4j.ogm.cypher.query.SortOrder;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author zua
 * @param <T> A graph entity subtype.
 */
public abstract class AbstractDBService<T extends GraphEntity> {

    public final int LIMIT = 50;

    public Collection<T> findAll() {
        return getSession().loadAll(getClassType(), getSortOrderDesc(), LIMIT);
    }

    public T find(String id) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.load(getClassType(), id);
    }

    public  void save(T object) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        session.save(object);
    }

    public  void delete(String id) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        session.delete(find(id));
    }

    public  long count() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.countEntitiesOfType(getClassType());
    }

    protected Session getSession() {
        return Neo4jSessionFactory.getInstance().getNeo4jSession();
    }

    public Iterable<T> executeQuery(String query) {
        return getSession().query(getClassType(), query, new HashMap<>());
    }
    
    public <T> Iterable<T> executeQuery(Class<T> aClass, String query) {
        return getSession().query(aClass, query, new HashMap<>());
    }

    public T executeQueryForObject(String query) {
        return getSession().queryForObject(getClassType(), query, new HashMap<>());
    }
    
    public abstract Class<T> getClassType();

    public abstract SortOrder getSortOrderAsc();

    public abstract SortOrder getSortOrderDesc();
}
