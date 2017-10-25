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
public class GlobalVoicesCrawlerSV extends GlobalVoicesAbstractCrawler {

    public GlobalVoicesCrawlerSV() {
    }

    @Override
    public NewsSource getMySource() {
        String sourceId = "global-voices-sv";
        String name = "Global Voices (SV)";
        String description = "";
        String url = "https://sv.globalvoices.org";
        String category = "general";
        String language = "sv";
        String country = "SE";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(Logos.getLogo("global-voices"));

        return source;
    }

}
