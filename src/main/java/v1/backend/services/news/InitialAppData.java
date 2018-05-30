/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v1.backend.services.news;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author zua
 */
public class InitialAppData implements Serializable {

    private static final long serialVersionUID = 3719036601269993746L;

    private long allArticlesCount;
    private long allPublishersCount;
    private long categoriesCount;
    private long languagesCount;
    private long countriesCount;

    private final Map<String, PublisherInfo> publishersInfo;
    private final Map<String, CategoryInfo> categoriesInfo;
    private final Map<String, LanguageInfo> languagesInfo;
    private final Map<String, CountryInfo> countriesInfo;

    public InitialAppData() {
        this.publishersInfo = new TreeMap<>();
        this.categoriesInfo = new TreeMap<>();
        this.languagesInfo = new TreeMap<>();
        this.countriesInfo = new TreeMap<>();
    }

    public void setArticlesCount(long allArticlesCount) {
        this.allArticlesCount = allArticlesCount;
    }

    public void setPublishersCount(long allPublishersCount) {
        this.allPublishersCount = allPublishersCount;
    }

    public void setCategoriesCount(long categoriesCount) {
        this.categoriesCount = categoriesCount;
    }

    public void setLanguagesCount(long languagesCount) {
        this.languagesCount = languagesCount;
    }

    public void addPublisherInfo(String sourceId, String name, long count) {
        publishersInfo.put(name, new PublisherInfo(sourceId, name, count));
    }

    public void addCategoryInfo(String tag, String category, long count) {
        this.categoriesInfo.put(category, new CategoryInfo(tag, category, count));
    }

    public void addLanguageInfo(String languageCode, String language, long count) {
        this.languagesInfo.put(language, new LanguageInfo(languageCode, language, count));
    }

    public void addCountryInfo(String countryCode, String country, long count) {
        this.countriesInfo.put(country, new CountryInfo(countryCode, country, count));
    }


    public void setCountriesCount(long countriesCount) {
        this.countriesCount = countriesCount;
    }

    public long getAllArticlesCount() {
        return allArticlesCount;
    }

    public long getAllPublishersCount() {
        return allPublishersCount;
    }

    public long getCategoriesCount() {
        return categoriesCount;
    }

    public long getLanguagesCount() {
        return languagesCount;
    }

    public Map<String, PublisherInfo> getPublishersInfo() {
        return publishersInfo;
    }

    public Map<String, CategoryInfo> getCategoriesInfo() {
        return categoriesInfo;
    }

    public Map<String, LanguageInfo> getLanguagesInfo() {
        return languagesInfo;
    }

    public Map<String, CountryInfo> getCountriesInfo() {
        return countriesInfo;
    }

    public long getCountriesCount() {
        return countriesCount;
    }

}
