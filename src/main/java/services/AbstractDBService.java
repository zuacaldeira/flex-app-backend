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
    public final Collection<T> findAll() {
        return getSession().loadAll(getClassType(), getSortOrderDesc(), LIMIT);
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
    public final List<T> findAllWithLimit(int limit) {
        return findAllDescWithLimit(limit);
    }
    
    @Override
    public final List<T> findAllAscWithLimit(int limit) {
        String query = getFindAllQuery(null, null, null, getSortOrderAsc(), limit);
        return executeQuery(query);
    }

    @Override
    public final List<T> findAllDescWithLimit(int limit) {
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
    public final T find(String id) {
        if(id == null) {
            throw new IllegalArgumentException("Graph entity ID cannot be null");
        }
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.load(getClassType(), id, 2);
    }
    
    @Override
    public final T find(T object) {
        String classname = getClassType().getSimpleName();
        String query = "MATCH (n:" + classname + ")";
        query += " WHERE n." + object.getPropertyName() + "=" + DatabaseUtils.getInstance().wrapUp(object.getPropertyValue());
        query += " RETURN n";
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.queryForObject(getClassType(), query, new HashMap<String, Object>());
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
        StringBuffer buffer = new StringBuffer();
        buffer.append("MATCH ");
        if(username != null) {
            buffer.append("(u:FlexUser), ");
        }
        buffer.append("(n:");
        buffer.append(getClassType().getSimpleName());
        buffer.append(") ");
        
        if(username != null || property != null) {
            buffer.append("WHERE ");
        }
        
        if(username != null) {
            buffer.append("u.username=");           
            buffer.append(DatabaseUtils.getInstance().wrapUp(username));
            if(property != null && value != null) {
                buffer.append(" AND ");           
            }
        }
        
        if(property != null && value != null) {
            buffer.append("n.");
            buffer.append(property);
            buffer.append("=");
            buffer.append(DatabaseUtils.getInstance().wrapUp(value.toString()));
            buffer.append(" ");
        }
        
        if(username != null) {
            buffer.append(" AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ");
        }
        
        buffer.append(" RETURN n ");

        if(order != null) {
            buffer.append(order.toString().replace("$", "n"));
            buffer.append(" ");
        }

        if(limit > 0) {
            buffer.append(" LIMIT ");
            buffer.append(limit);
        }
        
        String query = buffer.toString();
        System.out.println("Query = " + query.toUpperCase());
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
