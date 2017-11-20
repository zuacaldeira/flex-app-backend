/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.common.collect.Lists;
import db.Neo4jSessionFactory;
import db.GraphEntity;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.neo4j.ogm.session.Session;
import utils.DatabaseUtils;

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
    public final List<T> findAllAsc() {
        String query = Neo4jQueries.getFindAllQuery(getClassType(), null, null, null, getSortOrderAsc(), LIMIT);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAllDesc() {
        String query = Neo4jQueries.getFindAllQuery(getClassType(), null, null, null, getSortOrderDesc(), LIMIT);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAllWithLimit(int limit) {
        return findAllDescWithLimit(limit);
    }

    @Override
    public final List<T> findAllAscWithLimit(int limit) {
        String query = Neo4jQueries.getFindAllQuery(getClassType(), null, null, null, getSortOrderAsc(), limit);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAllDescWithLimit(int limit) {
        String query = Neo4jQueries.getFindAllQuery(getClassType(), null, null, null, getSortOrderDesc(), limit);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAll(String username) {
        return findAllDesc(username);
    }

    @Override
    public final List<T> findAllAsc(String username) {
        String query = Neo4jQueries.getFindAllQuery(getClassType(), username, null, null, getSortOrderAsc(), LIMIT);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAllDesc(String username) {
        String query = Neo4jQueries.getFindAllQuery(getClassType(), username, null, null, getSortOrderDesc(), LIMIT);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAll(String username, int limit) {
        return findAllDesc(username, limit);
    }

    @Override
    public final List<T> findAllAsc(String username, int limit) {
        String query = Neo4jQueries.getFindAllQuery(getClassType(), username, null, null, getSortOrderAsc(), limit);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAllDesc(String username, int limit) {
        String query = Neo4jQueries.getFindAllQuery(getClassType(), username, null, null, getSortOrderDesc(), limit);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAll(String property, Object value) {
        return findAllDesc(property, value);
    }

    @Override
    public final List<T> findAllAsc(String property, Object value) {
        String query = Neo4jQueries.getFindAllQuery(getClassType(), null, property, value, getSortOrderAsc(), LIMIT);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAllDesc(String property, Object value) {
        String query = Neo4jQueries.getFindAllQuery(getClassType(), null, property, value, getSortOrderDesc(), LIMIT);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAll(String property, Object value, int limit) {
        return findAllDesc(property, value, limit);
    }

    @Override
    public final List<T> findAllAsc(String property, Object value, int limit) {
        String query = Neo4jQueries.getFindAllQuery(getClassType(), null, property, value, getSortOrderAsc(), limit);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAllDesc(String property, Object value, int limit) {
        String query = Neo4jQueries.getFindAllQuery(getClassType(), null, property, value, getSortOrderDesc(), limit);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAll(String username, String property, Object value) {
        return findAllDesc(username, property, value);
    }

    @Override
    public final List<T> findAllAsc(String username, String property, Object value) {
        String query = Neo4jQueries.getFindAllQuery(getClassType(), username, property, value, getSortOrderAsc(), LIMIT);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAllDesc(String username, String property, Object value) {
        String query = Neo4jQueries.getFindAllQuery(getClassType(), username, property, value, getSortOrderDesc(), LIMIT);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAll(String username, String property, Object value, int limit) {
        return findAllDesc(username, property, value, limit);
    }

    @Override
    public final List<T> findAllAsc(String username, String property, Object value, int limit) {
        String query = Neo4jQueries.getFindAllQuery(getClassType(), username, property, value, getSortOrderAsc(), limit);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAllDesc(String username, String property, Object value, int limit) {
        String query = Neo4jQueries.getFindAllQuery(getClassType(), username, property, value, getSortOrderDesc(), limit);
        return executeQuery(query);
    }

    @Override
    public final T find(String id) {
        try {
            Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
            return session.load(getClassType(), id, 2);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public final T find(T object) {
        try {
            String classname = getClassType().getSimpleName();
            String query = "MATCH (n:" + classname + ")";
            query += " WHERE n." + object.getPropertyName() + "=" + DatabaseUtils.getInstance().wrapUp(object.getPropertyValue());
            query += " RETURN n";
            Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
            return session.queryForObject(getClassType(), query, new HashMap<String, Object>());
        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public final void save(T object) {
        try {
            Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
            session.save(object);
        } catch(Exception e) {}
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
    public LinkedList<T> executeQuery(String query) {
        return Lists.newLinkedList(getSession().query(getClassType(), query, new HashMap<String, Object>()));
    }

    protected <U> LinkedList<U> executeQuery(Class<U> type, String query) {
        return Lists.newLinkedList(getSession().query(type, query, new HashMap<String, Object>()));
    }
}
