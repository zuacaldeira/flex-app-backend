/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.common.collect.Lists;
import utils.Neo4jSessionFactory;
import db.GraphEntity;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.neo4j.ogm.cypher.query.SortOrder;
import org.neo4j.ogm.session.Session;
import utils.DatabaseUtils;

/**
 *
 * @author zua
 * @param <T>
 */
public abstract class AbstractDBService<T extends GraphEntity> implements DBService<T> {

    public final int LIMIT = 50;

    @Override
    public final List<T> findAll() {
        return findAllDesc();
    }
    
    @Override
    public final List<T> findAllAsc() {
        String query = getFindAllQuery(null, null, null, getSortOrderAsc(), LIMIT);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAllDesc() {
        String query = getFindAllQuery(null, null, null, getSortOrderDesc(), LIMIT);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAll(int limit) {
        return findAllDesc(limit);
    }
    
    @Override
    public final List<T> findAllAsc(int limit) {
        String query = getFindAllQuery(null, null, null, getSortOrderAsc(), limit);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAllDesc(int limit) {
        String query = getFindAllQuery(null, null, null, getSortOrderDesc(), limit);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAll(String username) {
        return findAllDesc(username);
    }
    @Override
    public final List<T> findAllAsc(String username) {
        String query = getFindAllQuery(username, null, null, getSortOrderAsc(), LIMIT);
        return executeQuery(query);
    }
    @Override
    public final List<T> findAllDesc(String username) {
        String query = getFindAllQuery(username, null, null, getSortOrderDesc(), LIMIT);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAll(String username, int limit) {
        return findAllDesc(username, limit);
    }
    @Override
    public final List<T> findAllAsc(String username, int limit) {
        String query = getFindAllQuery(username, null, null, getSortOrderAsc(), limit);
        return executeQuery(query);
    }
    @Override
    public final List<T> findAllDesc(String username, int limit) {
        String query = getFindAllQuery(username, null, null, getSortOrderDesc(), limit);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAll(String property, Object value) {
        return findAllDesc(property, value);
    }

    @Override
    public final List<T> findAllAsc(String property, Object value) {
        String query = getFindAllQuery(null, property, value, getSortOrderAsc(), LIMIT);
        return executeQuery(query);
    }
    @Override
    public final List<T> findAllDesc(String property, Object value) {
        String query = getFindAllQuery(null, property, value, getSortOrderDesc(), LIMIT);
        return executeQuery(query);
    }
    
    @Override
    public final List<T> findAll(String property, Object value, int limit) {
        return findAllDesc(property, value, limit);
    }

    @Override
    public final List<T> findAllAsc(String property, Object value, int limit) {
        String query = getFindAllQuery(null, property, value, getSortOrderAsc(), limit);
        return executeQuery(query);
    }
    @Override
    public final List<T> findAllDesc(String property, Object value, int limit) {
        String query = getFindAllQuery(null, property, value, getSortOrderDesc(), limit);
        return executeQuery(query);
    }
    
    @Override
    public final List<T> findAll(String username, String property, Object value) {
        return findAllDesc(username, property, value);
    }
    @Override
    public final List<T> findAllAsc(String username, String property, Object value) {
        String query = getFindAllQuery(username, property, value, getSortOrderAsc(), LIMIT);
        return executeQuery(query);
    }
    @Override
    public final List<T> findAllDesc(String username, String property, Object value) {
        String query = getFindAllQuery(username, property, value, getSortOrderDesc(), LIMIT);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAll(String username, String property, Object value, int limit) {
        return findAllDesc(username, property, value, limit);
    }
    @Override
    public final List<T> findAllAsc(String username, String property, Object value, int limit) {
        String query = getFindAllQuery(username, property, value, getSortOrderAsc(), limit);
        return executeQuery(query);
    }
    @Override
    public final List<T> findAllDesc(String username, String property, Object value, int limit) {
        String query = getFindAllQuery(username, property, value, getSortOrderDesc(), limit);
        return executeQuery(query);
    }
    
    @Override
    public final T find(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Graph entity ID cannot be null");
        }
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.load(getClassType(), id, 2);
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
            e.printStackTrace();
            return object;
        }
    }
    
    @Override
    public final T save(T object) {
        try {
            Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
            session.save(object);
            return find(object);
        } catch(Exception e) {
            return object;
        }
    }
    
    @Override
    public final void delete(Long id) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        session.delete(find(id));
    }
    
    @Override
    public final long count() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.countEntitiesOfType(getClassType());
    }

    @Override
    public T update(T object) {
        return save(update(find(object), object));
    }

    protected Session getSession() {
        return Neo4jSessionFactory.getInstance().getNeo4jSession();
    }
    
    
    protected String getCreateStateQuery(String relationName, String userProperty, String userPropertyValue, String entityProperty, String entityPropertyValue) {
            String query = "MATCH (u:FlexUser),(n:" + getClassType().getSimpleName() + ") WHERE\n";
            query += "u." + userProperty   + "=" + DatabaseUtils.getInstance().wrapUp(userPropertyValue);
            query += " AND ";
            query += "n." + entityProperty + "=" + DatabaseUtils.getInstance().wrapUp(entityPropertyValue);
            query += " CREATE (u)-[r:" + relationName + "]->(n) RETURN r";
            return query;
    }
    
    protected String getMatchStateQuery(String relationName, String username, String property, String value, int limit) {
            String query = "MATCH (u:FlexUser)-[:" + relationName + "]->(n:" + getClassType().getSimpleName() + ") WHERE\n";
            query += "u.username=" + DatabaseUtils.getInstance().wrapUp(username);
            if(property != null) {
                query += " AND ";
                query += "n." + property + "=" + DatabaseUtils.getInstance().wrapUp(value);
            }
            query += " RETURN n";
            
            if(limit > 0) {
                query += " limit " + limit;
            }
            return query;
    }

    protected String getDeleteStateQuery(String relationName, String userProperty, String userPropertyValue, String entityProperty, String entityPropertyValue) {
            String query = "MATCH (u:FlexUser)-[r:" + relationName + "]->(n:" + getClassType().getSimpleName() + ") WHERE\n";
            query += "u." + userProperty   + "=" + DatabaseUtils.getInstance().wrapUp(userPropertyValue);
            query += " AND ";
            query += "n." + entityProperty + "=" + DatabaseUtils.getInstance().wrapUp(entityPropertyValue);
            query += " DELETE r";
            return query;
    }
    
    protected String getFindAllQuery(String username, String property, Object value, SortOrder order, int limit) {
        String query = "MATCH (n:" + getClassType().getSimpleName() + "), (u:FlexUser)  ";
        if(username != null && property != null) {
            query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
            if(value != null) {
                query += "AND n." + property + "=" + DatabaseUtils.getInstance().wrapUp(value.toString()) + " ";
            }
            else {
                query += "AND n." + property + " IS NULL ";
            }
            query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
            query += "RETURN n ";
        }
        else if(username != null && property == null) {
            query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
            query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
            query += "RETURN n ";
        } 
        else if(username == null && property != null) {
            if(value != null) {
                query += "WHERE n." + property + "=" + DatabaseUtils.getInstance().wrapUp(value.toString()) + " ";
            }
            else {
                query += "WHERE n." + property + " IS NULL ";
            }
            query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
            query += "RETURN n ";
        }
        else if(username == null && property == null) {
            query += "WHERE NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
            query += "RETURN n ";
        }
        
        if(order != null) {
            query += order.toString().replace("$", "n") + " ";
        }

        if(limit > 0) {
            query += "LIMIT " + limit;
        }
        
        //System.out.println("Query = " + query);
        return query;
    }

   
    @Override
    public LinkedList<T> executeQuery(String query) {
        return Lists.newLinkedList(getSession().query(getClassType(), query, new HashMap<String, Object>()));
    }
    
    protected <U> LinkedList<U> executeQuery(Class<U> type, String query) {
        return Lists.newLinkedList(getSession().query(type, query, new HashMap<String, Object>()));
    }
}
