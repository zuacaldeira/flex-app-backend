/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.queries;

import org.neo4j.ogm.cypher.query.SortOrder;
import backend.utils.DatabaseUtils;

/**
 *
 * @author zua
 */
public class Neo4jQueries {

    public static String findArticlesWithCategory(String username, String category, int limit) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle)--(a:NewsAuthor)--(s:NewsSource) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "  AND (n.category = " + DatabaseUtils.getInstance().wrapUp(category) + " ";
        query += "  OR  s.category = " + DatabaseUtils.getInstance().wrapUp(category) + ") ";
        query += "  AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + limit;
        return query;
    }

    public static String findArticlesWithCategory(String category, int limit) {
        String query = "MATCH (n:NewsArticle)--(a:NewsAuthor)--(s:NewsSource) ";
        query += "WHERE (n.category = " + DatabaseUtils.getInstance().wrapUp(category) + " ";
        query += "  OR  s.category = " + DatabaseUtils.getInstance().wrapUp(category) + ") ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + limit;
        return query;
    }

    public static String findArticlesWithSource(String sourceId, int LIMIT) {
        String query = "MATCH (n:NewsArticle) ";
        query += "WHERE n.sourceId=" + DatabaseUtils.getInstance().wrapUp(sourceId) + " ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesWithSource(String username, String sourceId, int limit) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle)--(a:NewsAuthor)--(s:NewsSource) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "  AND (n.sourceId=" + DatabaseUtils.getInstance().wrapUp(sourceId) + " ";
        query += "  OR s.sourceId=" + DatabaseUtils.getInstance().wrapUp(sourceId) + ") ";
        query += "  AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + limit;
        System.out.println("QUERY = " + query);
        return query;
    }

    public static String findArticlesWithLanguage(String language, int LIMIT) {
        String query = "MATCH (n:NewsArticle) ";
        query += "WHERE n.language=" + DatabaseUtils.getInstance().wrapUp(language) + " ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesWithLanguage(String username, String language, int LIMIT) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "AND n.language=" + DatabaseUtils.getInstance().wrapUp(language) + " ";
        //query += "OR  s.language=" + DatabaseUtils.getInstance().wrapUp(language) + " ";
        query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesWithCountry(String country, int LIMIT) {
        String query = "MATCH (n:NewsArticle) ";
        query += "WHERE n.country=" + DatabaseUtils.getInstance().wrapUp(country) + " ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesWithCountry(String username, String country, int LIMIT) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle)--(a:NewsAuthor)--(s:NewsSource) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "AND n.country=" + DatabaseUtils.getInstance().wrapUp(country) + " ";
        query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String getCreateStateQuery(Class classType, String relationName, String userProperty, String userPropertyValue, String entityProperty, String entityPropertyValue) {
        String query = "MATCH (u:FlexUser),(n:" + classType.getSimpleName() + ") WHERE\n";
        query += "u." + userProperty + "=" + DatabaseUtils.getInstance().wrapUp(userPropertyValue);
        query += " AND ";
        query += "n." + entityProperty + "=" + DatabaseUtils.getInstance().wrapUp(entityPropertyValue);
        query += " CREATE (u)-[r:" + relationName + "]->(n) RETURN r";
        return query;
    }

    public static String getMatchStateQuery(Class classType, String relationName, String username, String property, String value, int limit) {
        String query = "MATCH (u:FlexUser)-[:" + relationName + "]->(n:" + classType.getSimpleName() + ") WHERE\n";
        query += "u.username=" + DatabaseUtils.getInstance().wrapUp(username);
        if (property != null) {
            query += " AND ";
            query += "n." + property + "=" + DatabaseUtils.getInstance().wrapUp(value);
        }
        query += " RETURN n";

        if (limit > 0) {
            query += " limit " + limit;
        }
        return query;
    }

    public static String getDeleteStateQuery(Class classType, String relationName, String userProperty, String userPropertyValue, String entityProperty, String entityPropertyValue) {
        String query = "MATCH (u:FlexUser)-[r:" + relationName + "]->(n:" + classType.getSimpleName() + ") WHERE\n";
        query += "u." + userProperty + "=" + DatabaseUtils.getInstance().wrapUp(userPropertyValue);
        query += " AND ";
        query += "n." + entityProperty + "=" + DatabaseUtils.getInstance().wrapUp(entityPropertyValue);
        query += " DELETE r";
        return query;
    }

    public static String getFindAllQuery(Class classType, String username, String property, Object value, SortOrder order, int limit) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("MATCH ");
        if (username != null) {
            buffer.append("(u:FlexUser), ");
        }
        buffer.append("(n:");
        buffer.append(classType.getSimpleName());
        buffer.append(") ");

        if (username != null || property != null) {
            buffer.append("WHERE ");
        }

        if (username != null) {
            buffer.append("u.username=");
            buffer.append(DatabaseUtils.getInstance().wrapUp(username));
            if (property != null && value != null) {
                buffer.append(" AND ");
            }
        }

        if (property != null && value != null) {
            buffer.append("n.");
            buffer.append(property);
            buffer.append("=");
            buffer.append(DatabaseUtils.getInstance().wrapUp(value.toString()));
            buffer.append(" ");
        }

        if (username != null) {
            buffer.append(" AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ");
        }

        buffer.append(" RETURN n ");

        if (order != null) {
            buffer.append(order.toString().replace("$", "n"));
            buffer.append(" ");
        }

        if (limit > 0) {
            buffer.append(" LIMIT ");
            buffer.append(limit);
        }

        String query = buffer.toString();
        System.out.println("Query = " + query.toUpperCase());
        return query;
    }

    public static String findArticlesWithText(String username, String value, int limit) {
        String query = "MATCH (u:FlexUser),(n:NewsArticle) WHERE ";
        query += "u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " AND ";
        query += "n.title CONTAINS " + DatabaseUtils.getInstance().wrapUp(value) + " OR ";
        query += "n.description CONTAINS " + DatabaseUtils.getInstance().wrapUp(value) + " ";
        query += "RETURN n LIMIT " + limit;
        return query;
    }
    
    public static String findArticlesWithText(String value, int limit) {
        String query = "MATCH (n:NewsArticle) WHERE ";
        query += "n.title CONTAINS " + DatabaseUtils.getInstance().wrapUp(value) + " OR ";
        query += "n.description CONTAINS " + DatabaseUtils.getInstance().wrapUp(value) + " ";
        query += "RETURN n LIMIT " + limit;
        return query;
    }

}
