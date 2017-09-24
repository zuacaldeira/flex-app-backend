/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.NewsSource;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author zua
 */
@Remote
public interface NewsSourceServiceInterface extends DBService<NewsSource> {
    public Collection<NewsSource> findAllSources();
    public Collection<NewsSource> findSourcesWithCategory(String category);
    public Collection<NewsSource> findSourcesWithLanguage(String language);
    public Collection<NewsSource> findSourcesWithCountry(String country);    
    public Collection<String> findCategories();
    public Collection<String> findNames();
    public Collection<String> findLanguages();
    public Collection<String> findCountries();
    public Collection<String> findLocales();
    public NewsSource findSourceWithSourceId(String sourceId);
    public NewsSource findSourceNamed(String value);

}
