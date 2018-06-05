/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.services.news;

import db.news.NewsArticle;
import java.util.Collection;
import java.util.HashMap;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.Pagination;
import backend.queries.StatsQueries;
import backend.session.Neo4jSessionFactory;
import backend.utils.MyDateUtils;
import db.news.NewsSource;
import db.news.Tag;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author zua
 */
@Stateless
public class StatsService {

    public long countArticleWithTitle(String title) {
        return getSession().queryForObject(Long.class, StatsQueries.countArticleWithTitle(title), new HashMap<>());
    }

    public long countArticlesWithUrl(String url) {
        return getSession().queryForObject(Long.class, StatsQueries.countArticleWithUrl(url), new HashMap<>());
    }

    public long countArticlesWithoutShortUrl() {
        String query = "MATCH (n:NewsArticle) WHERE n.shortUrl IS NULL RETURN count(n)";
        System.out.println(query);
        return getSession().queryForObject(Long.class, query, new HashMap<>());
    }

    public Collection<NewsArticle> countAll(int page, int pageSize) {
        Pagination paging = new Pagination(page, pageSize);
        return getSession().loadAll(NewsArticle.class, paging);
    }

    public long countArticlesTaggedAs(String tag) {
        String cypher = StatsQueries.countArticlesTaggedAs(tag);
        return getSession().queryForObject(Long.class, cypher, new HashMap<>());
    }

    public long countArticlesTaggedAs(String username, String tag) {
        String cypher = StatsQueries.countArticlesTaggedAs(username, tag);
        return getSession().queryForObject(Long.class, cypher, new HashMap<>());
    }

    public long countArticlesPublishedBy(String sourceName) {
        String cypher = StatsQueries.countArticlesPublishedBy(sourceName);
        return getSession().queryForObject(Long.class, cypher, new HashMap<>());
    }

    public long countArticlesPublishedBy(String username, String sourceName) {
        String cypher = StatsQueries.countArticlesPublishedBy(username, sourceName);
        return getSession().queryForObject(Long.class, cypher, new HashMap<>());
    }

    public long countArticlesWithLanguage(String language) {
        String cypher = StatsQueries.countArticlesWithLanguage(language);
        return getSession().queryForObject(Long.class, cypher, new HashMap<>());
    }

    public long countArticlesWithLanguage(String username, String language) {
        String cypher = StatsQueries.countArticlesWithLanguage(username, language);
        return getSession().queryForObject(Long.class, cypher, new HashMap<>());
    }

    public long countArticlesWithCountry(String country) {
        String cypher = StatsQueries.countArticlesWithCountry(country);
        return getSession().queryForObject(Long.class, cypher, new HashMap<>());
    }

    public long countArticlesWithCountry(String username, String country) {
        String cypher = StatsQueries.countArticlesWithCountry(username, country);
        return getSession().queryForObject(Long.class, cypher, new HashMap<>());
    }

    public long countArticlesWithText(String text) {
        String cypher = StatsQueries.countArticlesWithText(text);
        return getSession().queryForObject(Long.class, cypher, new HashMap<>());
    }

    public long countLatest(String username) {
        String cypher = StatsQueries.countLatest(username);
        return getSession().queryForObject(Long.class, cypher, new HashMap<>());
    }

    public long countLatest() {
        String cypher = StatsQueries.countLatest();
        return getSession().queryForObject(Long.class, cypher, new HashMap<>());
    }

    public long countOldest(String username) {
        String cypher = StatsQueries.countOldest(username);
        return getSession().queryForObject(Long.class, cypher, new HashMap<>());
    }

    public long countOldest() {
        String cypher = StatsQueries.countOldest();
        return getSession().queryForObject(Long.class, cypher, new HashMap<>());
    }

    public long countRead(String username) {
        String cypher = StatsQueries.countRead(username);
        return getSession().queryForObject(Long.class, cypher, new HashMap<>());
    }

    public long countFavorite(String username) {
        String cypher = StatsQueries.countFavorite(username);
        return getSession().queryForObject(Long.class, cypher, new HashMap<>());
    }

    public long countFake(String username) {
        String cypher = StatsQueries.countFake(username);
        return getSession().queryForObject(Long.class, cypher, new HashMap<>());
    }

    public long countAllArticles() {
        return getSession().countEntitiesOfType(NewsArticle.class);
    }

    public long countAllPublishers() {
        return getSession().countEntitiesOfType(NewsSource.class);
    }

    public long countAllCategories() {
        return getSession().countEntitiesOfType(Tag.class);
    }

    public long countAllLanguages() {
        return getSession().queryForObject(Long.class, StatsQueries.countAllLanguages(), new HashMap<>());
    }

    public long countAllCountries() {
        return getSession().queryForObject(Long.class, StatsQueries.countAllCountries(), new HashMap<>());
    }

    public Session getSession() {
        return Neo4jSessionFactory.getInstance().getNeo4jSession();
    }

    public InitialAppData getInitialAppData() {
        System.out.println("START: GET INITIAL APP DATA");
        InitialAppData data = new InitialAppData();

        System.out.println("GET INITIAL APP DATA: set counts info");
        data.setArticlesCount(countAllArticles());
        data.setPublishersCount(countAllPublishers());
        data.setCategoriesCount(countAllCategories());
        data.setLanguagesCount(countAllLanguages());
        data.setCountriesCount(countAllCountries());

        Iterable<NewsSource> publishers = new NewsSourceService().findAllSources();

        System.out.println("GET INITIAL APP DATA: add publishers info");
        publishers.forEach(pub -> {
            String sourceId = pub.getSourceId();
            String name = pub.getName();
            if (sourceId != null && name != null) {
                data.addPublisherInfo(sourceId, name, countArticlesPublishedBy(name));
            }

            String tag = pub.getCategory().getTag();
            String category = getCategoryCaption(tag);
            if (tag != null && category != null && !data.getCategoriesInfo().containsKey(category)) {
                data.addCategoryInfo(tag, category, countArticlesTaggedAs(tag));
            }

            String languageCode = pub.getLanguage();
            String language = MyDateUtils.getLanguage(languageCode);
            if (languageCode != null && language != null && !data.getLanguagesInfo().containsKey(language)) {
                data.addLanguageInfo(languageCode, language, countArticlesWithLanguage(languageCode));
            }

            String countryCode = pub.getCountry();
            String country = MyDateUtils.getCountry(countryCode);
            if (countryCode != null && country != null && !data.getCountriesInfo().containsKey(country)) {
                data.addCountryInfo(countryCode, country, countArticlesWithCountry(countryCode));
            }
        });

        return data;
    }

    private String getCategoryCaption(String cat) {
        char c = cat.charAt(0);
        return cat.replaceFirst(
                String.valueOf(c),
                String.valueOf(Character.toUpperCase(c)))
                .replace("-", " ");
    }

}
