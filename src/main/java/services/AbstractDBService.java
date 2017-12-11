/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import session.Neo4jSessionFactory;
import db.GraphEntity;
import java.util.Collection;
import java.util.HashMap;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author zua
 * @param <T> A graph entity subtype.
 */
public abstract class AbstractDBService<T extends GraphEntity> implements DBService<T> {

    public final int LIMIT = 30;

    @Override
    public final Collection<T> findAll() {
        return getSession().loadAll(getClassType(), getSortOrderDesc(), LIMIT);
    }

    @Override
    public final T find(String id) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.load(getClassType(), id, 2);
    }

    @Override
    public final void save(T object) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        session.save(object);
    }

    @Override
    public final void delete(String id) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        session.delete(find(id));
    }

    @Override
    public final long count() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.countEntitiesOfType(getClassType());
    }

    protected Session getSession() {
        return Neo4jSessionFactory.getInstance().getNeo4jSession();
    }

    @Override
    public Iterable<T> executeQuery(String query) {
        return getSession().query(getClassType(), query, new HashMap<>());
    }
    
    @Override
    public <T> Iterable<T> executeQuery(Class<T> aClass, String query) {
        return getSession().query(aClass, query, new HashMap<>());
    }

    @Override
    public T executeQueryForObject(String query) {
        return getSession().queryForObject(getClassType(), query, new HashMap<>());
    }
}
