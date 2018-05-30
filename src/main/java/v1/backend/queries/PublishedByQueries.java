/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v1.backend.queries;

import v1.backend.utils.DatabaseUtils;

/**
 *
 * @author zua
 */
public class PublishedByQueries {

    private static int LIMIT = 25;

    public static String findArticleWithTitle(String title) {
        String query = "MATCH (n:NewsArticle)-[r:PUBLISHED_BY]-(s:NewsSource) ";
        query += "WHERE n.title=" + DatabaseUtils.getInstance().wrapUp(title) + " ";
        query += "RETURN r ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticleWithUrl(String url) {
        String query = "MATCH (n:NewsArticle)-[r:PUBLISHED_BY]-(s:NewsSource) ";
        query += "WHERE n.url=" + DatabaseUtils.getInstance().wrapUp(url) + " ";
        query += "RETURN r ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesTaggedAs(String tag) {
        String query = "MATCH (n:NewsArticle)-[r:PUBLISHED_BY]-(s:NewsSource)--(t:Tag)  ";
        query += "WHERE t.tag=" + DatabaseUtils.getInstance().wrapUp(tag) + " ";
        query += "RETURN r ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesTaggedAs(String username, String tag) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle)-[r:PUBLISHED_BY]-(s:NewsSource)--(t:Tag) ";
        query += "WHERE t.tag=" + DatabaseUtils.getInstance().wrapUp(tag) + " ";
        query += "AND NOT (u:FlexUser)-[:READ|FAVORITE|FAKE]-(n) ";
        query += "RETURN r ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesPublishedBy(String sourceId) {
        String query = "MATCH (n:NewsArticle)-[r:PUBLISHED_BY]-(s:NewsSource) ";
        query += "WHERE s.sourceId=" + DatabaseUtils.getInstance().wrapUp(sourceId) + " ";
        query += "RETURN r ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesPublishedBy(String username, String sourceId) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle)-[r:PUBLISHED_BY]-(s:NewsSource) ";
        query += "WHERE s.sourceId=" + DatabaseUtils.getInstance().wrapUp(sourceId) + " ";
        query += "AND NOT (u:FlexUser)-[:READ|FAVORITE|FAKE]-(n) ";
        query += "RETURN r ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesWithLanguage(String username, String language, int LIMIT) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle)-[r:PUBLISHED_BY]-(s:NewsSource) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "AND n.language=" + DatabaseUtils.getInstance().wrapUp(language) + " ";
        query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "  RETURN r ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesWithLanguage(String language, int LIMIT) {
        String query = "MATCH (n:NewsArticle)-[r:PUBLISHED_BY]-(s:NewsSource) ";
        query += "WHERE n.language=" + DatabaseUtils.getInstance().wrapUp(language) + " ";
        query += "RETURN r ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesWithCountry(String country, int LIMIT) {
        String query = "MATCH (n:NewsArticle)-[r:PUBLISHED_BY]-(s:NewsSource) ";
        query += "WHERE n.country=" + DatabaseUtils.getInstance().wrapUp(country) + " ";
        query += "  RETURN r ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesWithCountry(String username, String country, int LIMIT) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle)-[r:PUBLISHED_BY]-(s:NewsSource) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "AND n.country=" + DatabaseUtils.getInstance().wrapUp(country) + " ";
        query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "RETURN r ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesWithText(String value, int LIMIT) {
        String query = "MATCH (n:NewsArticle)-[r:PUBLISHED_BY]-(s:NewsSource) ";
        query += "WHERE n.title CONTAINS " + DatabaseUtils.getInstance().wrapUp(value) + " OR ";
        query += "n.description CONTAINS " + DatabaseUtils.getInstance().wrapUp(value) + " ";
        query += "RETURN r LIMIT " + LIMIT;
        return query;
    }

    public static String findLatest(String username, int LIMIT) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle)-[r:PUBLISHED_BY]-(s:NewsSource) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]-(n)) ";
        query += "RETURN r ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        System.out.println("QUERY = " + query);
        return query;
    }

    public static String findOldest(int LIMIT) {
        String query = "MATCH (n:NewsArticle)-[r:PUBLISHED_BY]-(s:NewsSource) ";
        query += "RETURN r ";
        query += "ORDER BY n.publishedAt ASC LIMIT " + LIMIT;
        return query;
    }

    public static String findOldest(String username, int LIMIT) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle)-[r:PUBLISHED_BY]-(s:NewsSource) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "RETURN r ";
        query += "ORDER BY n.publishedAt ASC LIMIT " + LIMIT;
        return query;
    }

    public static String findRead(String username, int LIMIT) {
        String query = "MATCH (u:FlexUser)-[f:READ]-(n:NewsArticle)-[r:PUBLISHED_BY]-(s:NewsSource) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "RETURN r ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findFavorite(String username, int LIMIT) {
        String query = "MATCH (u:FlexUser)-[f:FAVORITE]-(n:NewsArticle)-[r:PUBLISHED_BY]-(s:NewsSource) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "RETURN r ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findFake(String username, int LIMIT) {
        String query = "MATCH (u:FlexUser)-[f:FAKE]-(n:NewsArticle)-[r:PUBLISHED_BY]-(s:NewsSource) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "RETURN r ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findLatest(int LIMIT) {
        String query = "MATCH (n:NewsArticle)-[r:PUBLISHED_BY]-(s:NewsSource) ";
        query += "RETURN r ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

}
