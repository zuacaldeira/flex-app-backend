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
@Singleton
public class GlobalVoicesCrawlerZHS extends GlobalVoicesAbstractCrawler {

    public GlobalVoicesCrawlerZHS() {
    }

    @Override
    public NewsSource getMySource() {
        String sourceId = "global-voices-zhs";
        String name = "Global Voices (ZHS)";
        String description = "";
        String url = "https://zhs.globalvoices.org";
        String category = "general";
        String language = "zh";
        String country = "CN";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(Logos.getLogo("global-voices"));

        return source;
    }

    @Override
    @Schedule(hour="*", minute="*/10")
    public void crawl() {
        super.crawl(); //To change body of generated methods, choose Tools | Templates.
    }
}
