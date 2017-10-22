/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import db.NewsSource;
import javax.ejb.ConcurrencyManagement;
import static javax.ejb.ConcurrencyManagementType.CONTAINER;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.interceptor.Interceptors;
import services.DatabaseExceptionsInterceptor;

/**
 *
 * @author zua
 */
@Singleton
@Interceptors(DatabaseExceptionsInterceptor.class)
@ConcurrencyManagement(CONTAINER)
@Lock(LockType.WRITE)
public class IOLNewsZACrawler extends AbstactIOLNewsCrawler {

    public IOLNewsZACrawler() {
        super();
    }

    @Override
    protected String getUrl() {
        return "https://www.iol.co.za/news";
    }
    
    @Override
    public NewsSource getMySource() {
        String sourceId = "iol-news-za";
        String name = "IOL News South Africa";
        String description = "";
        String url = getUrl();
        String category = "General";
        String language = "en";
        String country = "ZA";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(Logos.getLogo(sourceId));

        return source;
    }
    

}
