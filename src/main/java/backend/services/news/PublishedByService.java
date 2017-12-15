/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.services.news;

import backend.queries.PublishedByQueries;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.SortOrder;
import backend.services.AbstractDBService;
import db.relationships.PublishedBy;
import java.util.Collections;

/**
 *
 * @author zua
 */
@Stateless
public class PublishedByService extends AbstractDBService<PublishedBy> {

    @Override
    public Class<PublishedBy> getClassType() {
        return PublishedBy.class;
    }

    @Override
    public SortOrder getSortOrderAsc() {
        return new SortOrder().add(SortOrder.Direction.ASC, "publishedAt");
    }

    @Override
    public SortOrder getSortOrderDesc() {
        return new SortOrder().add(SortOrder.Direction.DESC, "publishedAt");
    }

    public Iterable<PublishedBy> findLatest() {
        String query = PublishedByQueries.findLatest(LIMIT);
        return getSession().query(PublishedBy.class, query, Collections.emptyMap());
    }

    public Iterable<PublishedBy> findOldest() {
        String query = PublishedByQueries.findOldest(LIMIT);
        return getSession().query(PublishedBy.class, query, Collections.emptyMap());
    }

    public Iterable<PublishedBy> findLatest(String username) {
        String query = PublishedByQueries.findLatest(username, LIMIT);
        return getSession().query(PublishedBy.class, query, Collections.emptyMap());
    }

    public Iterable<PublishedBy> findFake(String username) {
        String query = PublishedByQueries.findFake(username, LIMIT);
        return getSession().query(PublishedBy.class, query, Collections.emptyMap());
    }

    public Iterable<PublishedBy> findFavorite(String username) {
        String query = PublishedByQueries.findFavorite(username, LIMIT);
        return getSession().query(PublishedBy.class, query, Collections.emptyMap());
    }

    public Iterable<PublishedBy> findRead(String username) {
        String query = PublishedByQueries.findRead(username, LIMIT);
        return getSession().query(PublishedBy.class, query, Collections.emptyMap());
    }

    public Iterable<PublishedBy> findOldest(String username) {
        String query = PublishedByQueries.findOldest(username, LIMIT);
        return getSession().query(PublishedBy.class, query, Collections.emptyMap());
    }

    public Iterable<PublishedBy> findArticlesWithText(String value) {
        String query = PublishedByQueries.findArticlesWithText(value, LIMIT);
        return getSession().query(PublishedBy.class, query, Collections.emptyMap());
    }

    public Iterable<PublishedBy> findArticlesWithCountry(String countryCode) {
        String query = PublishedByQueries.findArticlesWithCountry(countryCode, LIMIT);
        return getSession().query(PublishedBy.class, query, Collections.emptyMap());
    }

    public Iterable<PublishedBy> findArticlesWithLanguage(String languageCode) {
        String query = PublishedByQueries.findArticlesWithLanguage(languageCode, LIMIT);
        return getSession().query(PublishedBy.class, query, Collections.emptyMap());
    }

    public Iterable<PublishedBy> findArticlesPublishedBy(String sourceId) {
        String query = PublishedByQueries.findArticlesPublishedBy(sourceId);
        return getSession().query(PublishedBy.class, query, Collections.emptyMap());
    }

    public Iterable<PublishedBy> findArticlesTaggedAs(String category) {
        String query = PublishedByQueries.findArticlesTaggedAs(category);
        return getSession().query(PublishedBy.class, query, Collections.emptyMap());
    }

    public Iterable<PublishedBy> findArticlesTaggedAs(String username, String category) {
        String query = PublishedByQueries.findArticlesTaggedAs(username, category);
        return getSession().query(PublishedBy.class, query, Collections.emptyMap());
    }

    public Iterable<PublishedBy> findArticlesPublishedBy(String username, String sourceId) {
        String query = PublishedByQueries.findArticlesPublishedBy(username, sourceId);
        return getSession().query(PublishedBy.class, query, Collections.emptyMap());
    }

    public Iterable<PublishedBy> findArticlesWithLanguage(String username, String language) {
        String query = PublishedByQueries.findArticlesWithLanguage(username, language, LIMIT);
        return getSession().query(PublishedBy.class, query, Collections.emptyMap());
    }

    public Iterable<PublishedBy> findArticlesWithCountry(String username, String country) {
        String query = PublishedByQueries.findArticlesWithCountry(username, country, LIMIT);
        return getSession().query(PublishedBy.class, query, Collections.emptyMap());
    }

}
