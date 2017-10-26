/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers.globalVoices;

import crawlers.Logos;
import db.NewsSource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 *
 * @author zua
 */

public class GlobalVoicesCrawlerKM extends GlobalVoicesAbstractCrawler {

    public GlobalVoicesCrawlerKM() {
    }

    @Override
    public NewsSource getMySource() {
        String sourceId = "global-voices-km";
        String name = "Global Voices (KM)";
        String description = "";
        String url = "https://km.globalvoices.org";
        String category = "general";
        String language = "km";
        String country = "KH";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(Logos.getLogo("global-voices"));

        return source;
    }

    @Override
    
    public void crawl() {
        super.crawl(); //To change body of generated methods, choose Tools | Templates.
    }
}
