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
public class GlobalVoicesCrawlerJA extends GlobalVoicesAbstractCrawler {

    public GlobalVoicesCrawlerJA() {
    }

    @Override
    public NewsSource getMySource() {
        String sourceId = "global-voices-ja";
        String name = "Global Voices (JA)";
        String description = "";
        String url = "https://jp.globalvoices.org";
        String category = "general";
        String language = "ja";
        String country = "JP";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(Logos.getLogo("global-voices"));

        return source;
    }

}
