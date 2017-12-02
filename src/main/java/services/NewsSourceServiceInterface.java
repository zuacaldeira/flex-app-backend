/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.NewsSource;
import io.reactivex.Observable;
import javax.ejb.Remote;

/**
 *
 * @author zua
 */
@Remote
public interface NewsSourceServiceInterface extends DBService<NewsSource> {
    public Observable<NewsSource> findAllSources();
    public Observable<NewsSource> findSourcesWithCategory(String category);
    public Observable<NewsSource> findSourcesWithLanguage(String language);
    public Observable<NewsSource> findSourcesWithCountry(String country);    
    public Observable<NewsSource> findSourcesWithoutLogo();
    public Observable<String> findCategories();
    public Observable<String> findNames();
    public Observable<String> findLanguages();
    public Observable<String> findCountries();
    public Observable<String> findLocales();
    public NewsSource findSourceWithSourceId(String sourceId);
    public NewsSource findSourceNamed(String value);

}
