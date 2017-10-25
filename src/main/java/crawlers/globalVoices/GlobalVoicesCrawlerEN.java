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
public class GlobalVoicesCrawlerEN extends GlobalVoicesAbstractCrawler {

    public GlobalVoicesCrawlerEN() {
    }

    @Override
    public NewsSource getMySource() {
        String sourceId = "global-voices-en";
        String name = "Global Voices (EN)";
        String description = "";
        String url = "https://en.globalvoices.org";
        String category = "general";
        String language = "en";
        String country = "GB";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(Logos.getLogo("global-voices"));

        return source;
    }

}
