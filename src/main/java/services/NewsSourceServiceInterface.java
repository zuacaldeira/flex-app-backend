/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.NewsSource;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author zua
 */
@Remote
public interface NewsSourceServiceInterface extends DBService<NewsSource> {
    public List<String> findCategories();
    public List<String> findNames();
    public List<String> findLanguages();
    public List<String> findCountries();
    public NewsSource findSourceWithSourceId(String sourceId);
}
