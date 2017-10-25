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
public class GlobalVoicesCrawlerBG extends GlobalVoicesAbstractCrawler {

    public GlobalVoicesCrawlerBG() {
    }

    @Override
    public NewsSource getMySource() {
        String sourceId = "global-voices-bg";
        String name = "Global Voices (BG)";
        String description = "";
        String url = "https://bg.globalvoices.org";
        String category = "general";
        String language = "bg";
        String country = "BG";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(Logos.getLogo("global-voices"));

        return source;
    }

}
