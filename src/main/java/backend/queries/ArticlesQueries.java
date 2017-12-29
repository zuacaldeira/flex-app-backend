/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.queries;

import backend.utils.DatabaseUtils;

/**
 *
 * @author zua
 */
public class ArticlesQueries {

    private static int LIMIT = 25;

    public static String findArticleWithTitle(String title) {
        String query = "MATCH (n:NewsArticle) ";
        query += "WHERE n.title=" + DatabaseUtils.getInstance().wrapUp(title) + " ";
        query += "RETURN n ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticleWithUrl(String url) {
        String query = "MATCH (n:NewsArticle) ";
        query += "WHERE n.url=" + DatabaseUtils.getInstance().wrapUp(url) + " ";
        query += "RETURN n ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesTaggedAs(String tag) {
        String query = "MATCH (n:NewsArticle)--(s:NewsSource)--(t:Tag) ";
        query += "WHERE t.tag=" + DatabaseUtils.getInstance().wrapUp(tag) + " ";
        query += "RETURN n ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesTaggedAs(String username, String tag) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle)--(s:NewsSource)--(t:Tag) ";
        query += "WHERE t.tag=" + DatabaseUtils.getInstance().wrapUp(tag) + " ";
        query += "AND NOT (u:FlexUser)-[:READ|FAVORITE|FAKE]-(n) ";
        query += "RETURN n ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesPublishedBy(String sourceId) {
        String query = "MATCH (n:NewsArticle) ";
        query += "WHERE n.sourceId=" + DatabaseUtils.getInstance().wrapUp(sourceId) + " ";
        query += "RETURN n ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesPublishedBy(String username, String sourceId) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle) ";
        query += "WHERE n.sourceId=" + DatabaseUtils.getInstance().wrapUp(sourceId) + " ";
        query += "AND NOT (u:FlexUser)-[:READ|FAVORITE|FAKE]-(n) ";
        query += "RETURN n ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesWithLanguage(String username, String language, int LIMIT) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "AND n.language=" + DatabaseUtils.getInstance().wrapUp(language) + " ";
        query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "  RETURN n ";
        query += "  ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesWithLanguage(String language, int LIMIT) {
        String query = "MATCH (n:NewsArticle) ";
        query += "WHERE n.language=" + DatabaseUtils.getInstance().wrapUp(language) + " ";
        query += "RETURN n ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
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
        String query = "MATCH (u:FlexUser), (n:NewsArticle)--(s:NewsSource) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "AND n.country=" + DatabaseUtils.getInstance().wrapUp(country) + " ";
        query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "RETURN n ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findArticlesWithText(String value, int LIMIT) {
        String query = "MATCH (n:NewsArticle) WHERE ";
        query += "n.title CONTAINS " + DatabaseUtils.getInstance().wrapUp(value) + " OR ";
        query += "n.description CONTAINS " + DatabaseUtils.getInstance().wrapUp(value) + " ";
        query += "RETURN n LIMIT " + LIMIT;
        return query;
    }

    public static String findLatest(int LIMIT) {
        String query = "MATCH (n:NewsArticle) ";
        query += "RETURN n ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findLatest(String username, int LIMIT) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "RETURN n ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findOldest(int LIMIT) {
        String query = "MATCH (n:NewsArticle) ";
        query += "RETURN n ";
        query += "ORDER BY n.publishedAt ASC LIMIT " + LIMIT;
        return query;
    }

    public static String findOldest(String username, int LIMIT) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "RETURN n ";
        query += "ORDER BY n.publishedAt ASC LIMIT " + LIMIT;
        return query;
    }

    public static String findRead(String username, int LIMIT) {
        String query = "MATCH (u:FlexUser)-[r:READ]-(n:NewsArticle) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "RETURN n ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findFavorite(String username, int LIMIT) {
        String query = "MATCH (u:FlexUser)-[r:FAVORITE]-(n:NewsArticle) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "RETURN n ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

    public static String findFake(String username, int LIMIT) {
        String query = "MATCH (u:FlexUser)-[r:FAKE]-(n:NewsArticle) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "RETURN n ";
        query += "ORDER BY n.publishedAt DESC LIMIT " + LIMIT;
        return query;
    }

}
