/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers.iolNews;

import crawlers.Logos;
import db.NewsSource;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import services.DatabaseExceptionsInterceptor;

/**
 *
 * @author zua
 */
@Stateless
@Interceptors(DatabaseExceptionsInterceptor.class)
public class IOLNewsIsolezweCrawler extends AbstactIOLNewsCrawler {

    public IOLNewsIsolezweCrawler() {
    }

    @Override
    public NewsSource getMySource() {
        String sourceId = "iol-news-isolezwe";
        String name = "IOL News Isolezwe";
        String description = "";
        String url = getUrl();
        String category = "General";
        String language = "sw";
        String country = "ZA";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(Logos.getLogo("iol-news-za"));

        return source;
    }

    @Override
    protected String getUrl() {
        return "https://www.iol.co.za/isolezwe";
    }
    
    
    
}
