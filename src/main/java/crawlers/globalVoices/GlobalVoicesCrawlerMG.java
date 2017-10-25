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
public class GlobalVoicesCrawlerMG extends GlobalVoicesAbstractCrawler {

    public GlobalVoicesCrawlerMG() {
    }

    @Override
    public NewsSource getMySource() {
        String sourceId = "global-voices-mg";
        String name = "Global Voices (MG)";
        String description = "";
        String url = "https://mg.globalvoices.org";
        String category = "general";
        String language = "mg";
        String country = "MG";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(Logos.getLogo("global-voices"));

        return source;
    }

}
