/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.NewsSource;
import java.util.Set;
import javax.ejb.Remote;

/**
 *
 * @author zua
 */
@Remote
public interface NewsSourceServiceInterface extends DBService<NewsSource> {
    public Set<String> findCategories();
    public Set<String> findNames();
    public Set<String> findLanguages();
    public Set<String> findCountries();
    public Set<String> findLocales();
    public NewsSource findSourceWithSourceId(String sourceId);
}
