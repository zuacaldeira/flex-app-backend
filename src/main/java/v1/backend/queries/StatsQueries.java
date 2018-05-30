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
public class StatsQueries {

    public static String countArticleWithTitle(String title) {
        String query = "MATCH (n:NewsArticle) ";
        query += "WHERE n.title=" + DatabaseUtils.getInstance().wrapUp(title) + " ";
        query += "RETURN count(n) ";
        return query;
    }

    public static String countArticleWithUrl(String url) {
        String query = "MATCH (n:NewsArticle) ";
        query += "WHERE n.url=" + DatabaseUtils.getInstance().wrapUp(url) + " ";
        query += "RETURN count(n) ";
        return query;
    }

    public static String countArticlesTaggedAs(String tag) {
        String query = "MATCH (n:NewsArticle)--(a:NewsAuthor)--(s:NewsSource)--(t:Tag) ";
        query += "WHERE t.tag=" + DatabaseUtils.getInstance().wrapUp(tag) + " ";
        query += "RETURN count(n) ";
        return query;
    }

    public static String countArticlesTaggedAs(String username, String tag) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle)--(a:NewsAuthor)--(s:NewsSource)--(t:Tag) ";
        query += "WHERE t.tag=" + DatabaseUtils.getInstance().wrapUp(tag) + " ";
        query += "AND NOT (u:FlexUser)-[:READ|FAVORITE|FAKE]-(n) ";
        query += "RETURN count(n) ";
        return query;
    }

    public static String countArticlesPublishedBy(String sourceName) {
        String query = "MATCH (n:NewsArticle)--(:NewsAuthor)--(s:NewsSource) ";
        query += "WHERE s.name=" + DatabaseUtils.getInstance().wrapUp(sourceName) + " ";
        query += "RETURN count(n) ";
        return query;
    }

    public static String countArticlesPublishedBy(String username, String sourceName) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle)--(:NewsAuthor)--(s:NewsSource) ";
        query += "WHERE s.name=" + DatabaseUtils.getInstance().wrapUp(sourceName) + " ";
        query += "AND NOT (u:FlexUser)-[:READ|FAVORITE|FAKE]-(n) ";
        query += "RETURN count(n) ";
        return query;
    }

    public static String countArticlesWithLanguage(String username, String language) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "AND n.language=" + DatabaseUtils.getInstance().wrapUp(language) + " ";
        query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "  RETURN count(n) ";
        return query;
    }

    public static String countArticlesWithLanguage(String language) {
        String query = "MATCH (n:NewsArticle) ";
        query += "WHERE n.language=" + DatabaseUtils.getInstance().wrapUp(language) + " ";
        query += "RETURN count(n) ";
        return query;
    }

    public static String countArticlesWithCountry(String country) {
        String query = "MATCH (n:NewsArticle) ";
        query += "WHERE n.country=" + DatabaseUtils.getInstance().wrapUp(country) + " ";
        query += "  RETURN count(n) ";
        return query;
    }

    public static String countArticlesWithCountry(String username, String country) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle)--(a:NewsAuthor)--(s:NewsSource) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "AND n.country=" + DatabaseUtils.getInstance().wrapUp(country) + " ";
        query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "RETURN count(n) ";
        return query;
    }

    public static String countArticlesWithText(String value) {
        String query = "MATCH (n:NewsArticle) WHERE ";
        query += "n.title CONTAINS " + DatabaseUtils.getInstance().wrapUp(value) + " OR ";
        query += "n.description CONTAINS " + DatabaseUtils.getInstance().wrapUp(value) + " ";
        query += "RETURN count(n)";
        return query;
    }

    public static String countLatest() {
        String query = "MATCH (n:NewsArticle) ";
        query += "RETURN count(n) ";
        return query;
    }

    public static String countLatest(String username) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "RETURN count(n) ";
        return query;
    }

    public static String countOldest() {
        String query = "MATCH (n:NewsArticle) ";
        query += "RETURN count(n) ";
        return query;
    }

    public static String countOldest(String username) {
        String query = "MATCH (u:FlexUser), (n:NewsArticle) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
        query += "RETURN count(n) ";
        return query;
    }

    public static String countRead(String username) {
        String query = "MATCH (u:FlexUser)-[r:READ]-(n:NewsArticle) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "RETURN count(n) ";
        return query;
    }

    public static String countFavorite(String username) {
        String query = "MATCH (u:FlexUser)-[r:FAVORITE]-(n:NewsArticle) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "RETURN count(n) ";
        return query;
    }

    public static String countFake(String username) {
        String query = "MATCH (u:FlexUser)-[r:FAKE]-(n:NewsArticle) ";
        query += "WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " ";
        query += "RETURN count(n) ";
        return query;
    }

    public static String countAllCategories() {
        return "MATCH (t:Tag) RETURN count(DISTINCT t)";
    }

    public static String countAllNames() {
        return "MATCH (s:NewsSource) RETURN count(DISTINCT s.name)";
    }

    public static String countAllLanguages() {
        return "MATCH (s:NewsSource) RETURN count(DISTINCT s.language)";
    }

    public static String countAllCountries() {
        return "MATCH (s:NewsSource) RETURN count(DISTINCT s.country)";
    }

    public static String countAllLocales() {
        return "MATCH (s:NewsSource) RETURN count(DISTINCT s.language+'_'+s.country)";
    }

}
