/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news;

import queries.SourcesQueries;
import db.news.NewsSource;
import io.reactivex.Observable;
import java.util.HashMap;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.BooleanOperator;
import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.cypher.Filters;
import org.neo4j.ogm.cypher.query.SortOrder;
import services.AbstractDBService;
import utils.DatabaseUtils;

/**
 *
 * @author zua
 */
@Stateless
public class NewsSourceService extends AbstractDBService<NewsSource> {

    public Observable<NewsSource> findAllSources() {
        return Observable.fromIterable(getSession().loadAll(NewsSource.class, 2));
    }

    public Observable<NewsSource> findSourcesWithCategory(String category) {
        Filter fCategory = new Filter("category", ComparisonOperator.EQUALS, category);
        return Observable.fromIterable(getSession().loadAll(NewsSource.class, fCategory, 2));
    }

    public Observable<NewsSource> findSourcesWithoutLogo() {
        Filter nullLogoFilter = new Filter("logoUrl", ComparisonOperator.IS_NULL);
        Filter emptyLogoFilter = new Filter("logoUrl", ComparisonOperator.EQUALS, "");
        emptyLogoFilter.setBooleanOperator(BooleanOperator.OR);
        Filters filters = new Filters(nullLogoFilter, emptyLogoFilter);
        return Observable.fromIterable(getSession().loadAll(NewsSource.class, filters, 2));
    }

    public Observable<NewsSource> findSourcesWithLanguage(String language) {
        Filter fCategory = new Filter("language", ComparisonOperator.EQUALS, language);
        return Observable.fromIterable(getSession().loadAll(NewsSource.class, fCategory, 2));
    }

    public Observable<NewsSource> findSourcesWithCountry(String country) {
        Filter fCategory = new Filter("country", ComparisonOperator.EQUALS, country);
        return Observable.fromIterable(getSession().loadAll(NewsSource.class, fCategory, 2));
    }

    @Override
    public Class<NewsSource> getClassType() {
        return NewsSource.class;
    }

    @Override
    public SortOrder getSortOrderAsc() {
        return new SortOrder().add(SortOrder.Direction.ASC, "name");
    }

    @Override
    public SortOrder getSortOrderDesc() {
        return new SortOrder().add(SortOrder.Direction.DESC, "name");
    }

    public Iterable<String> findCategories() {
        String query = SourcesQueries.findAllCategoriesQuery();
        return super.executeQuery(String.class, query);
    }

    public Iterable<String> findNames() {
        String query = SourcesQueries.findAllNamesQuery();
        return executeQuery(String.class, query);
    }

    public Iterable<String> findLanguages() {
        String query = SourcesQueries.findAllLanguagesQuery();
        return executeQuery(String.class, query);
    }

    public Iterable<String> findCountries() {
        String query = SourcesQueries.findAllCountriesQuery();
        return executeQuery(String.class, query);
    }

    public Iterable<String> findLocales() {
        String query = SourcesQueries.findAllLocalesQuery();
        return executeQuery(String.class, query);
    }

    public NewsSource findSourceWithSourceId(String sourceId) {
        String query = "MATCH (n:NewsSource) ";
        query += "WHERE n.sourceId=" + DatabaseUtils.getInstance().wrapUp(sourceId) + " ";
        query += "  RETURN n ";
        return getSession().queryForObject(getClassType(), query, new HashMap<String, Object>());
    }

    public NewsSource findSourceNamed(String name) {
        String query = "MATCH (n:NewsSource) ";
        query += "WHERE n.name=" + DatabaseUtils.getInstance().wrapUp(name) + " ";
        query += "  RETURN n ";
        return getSession().queryForObject(getClassType(), query, new HashMap<String, Object>());
    }

}
