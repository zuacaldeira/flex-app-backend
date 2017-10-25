/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers.globalVoices;

import crawlers.Logos;
import db.NewsSource;
import javax.ejb.Singleton;

/**
 *
 * @author zua
 */
@Singleton
public class GlobalVoicesCrawlerAM extends GlobalVoicesAbstractCrawler {

    public GlobalVoicesCrawlerAM() {
    }

    @Override
    public NewsSource getMySource() {
        String sourceId = "global-voices-am";
        String name = "Global Voices (AM)";
        String description = "";
        String url = "https://am.globalvoices.org";
        String category = "general";
        String language = "am";
        String country = "ET";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(Logos.getLogo("global-voices"));

        return source;
    }

}
