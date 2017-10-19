/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import db.NewsSource;
import javax.ejb.Asynchronous;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 *
 * @author zua
 */
@Singleton
public class GlobalVoicesCrawler extends GlobalVoicesAbstractCrawler {

    public GlobalVoicesCrawler() {
    }

    @Override
    public void crawl() {
        //getLogger().on();
        crawlWebsite(getUrl("am"), getMySource("am", "ET"));
        crawlWebsite(getUrl("ar"), getMySource("ar", "SD"));
        crawlWebsite(getUrl("aym"), getMySource("aym", "PE"));
        crawlWebsite(getUrl("bg"), getMySource("bg", "BG"));
        crawlWebsite(getUrl("bn"), getMySource("bn", "BD"));
        crawlWebsite(getUrl("ca"), getMySource("ca", "ES"));
        crawlWebsite(getUrl("cs"), getMySource("cs", "CZ"));
        crawlWebsite(getUrl("da"), getMySource("da", "DK"));
        crawlWebsite(getUrl("de"), getMySource("de"));
        crawlWebsite(getUrl("el"), getMySource("el", "GR"));
        crawlWebsite(getUrl("en"), getMySource("en", "GB"));
        crawlWebsite(getUrl("eo"), getMySource("eo", "001"));
        crawlWebsite(getUrl("es"), getMySource("es", "ES"));
        crawlWebsite(getUrl("fa"), getMySource("fa", "IR"));
        crawlWebsite(getUrl("fil"), getMySource("fil", "PH"));
        crawlWebsite(getUrl("fr"), getMySource("fr", "FR"));
        crawlWebsite(getUrl("he"), getMySource("he", "IL"));
        crawlWebsite(getUrl("hi"), getMySource("hi", "IN"));
        crawlWebsite(getUrl("hu"), getMySource("hu", "HU"));
        crawlWebsite(getUrl("id"), getMySource("in", "ID"));
        crawlWebsite(getUrl("it"), getMySource("it", "IT"));
        crawlWebsite(getUrl("jp"), getMySource("ja", "jp"));
        crawlWebsite(getUrl("km"), getMySource("km", "KH"));
        crawlWebsite(getUrl("ko"), getMySource("ko", "KR"));
        crawlWebsite(getUrl("mg"), getMySource("mg", "MG"));
        crawlWebsite(getUrl("mk"), getMySource("mk", "MK"));
        //crawlWebsite(getUrl("my"), getMySource("my", "MM"));
        crawlWebsite(getUrl("ne"), getMySource("ne", "NP"));
        crawlWebsite(getUrl("or"), getMySource("or", "IN"));
        crawlWebsite(getUrl("pa"), getMySource("pa", "PK"));
        crawlWebsite(getUrl("pl"), getMySource("pl", "PL"));
        crawlWebsite(getUrl("ps"), getMySource("ps", "AF"));
        crawlWebsite(getUrl("pt"), getMySource("pt", "PT"));
        crawlWebsite(getUrl("ro"), getMySource("ro", "RO"));
        crawlWebsite(getUrl("ru"), getMySource("ru", "RU"));
        crawlWebsite(getUrl("sq"), getMySource("sq", "AL"));
        crawlWebsite(getUrl("sr"), getMySource("sr", "RS"));
        crawlWebsite(getUrl("sv"), getMySource("sv", "SE"));
        crawlWebsite(getUrl("sw"), getMySource("sw", "TZ"));
        crawlWebsite(getUrl("tet"), getMySource("tet", "TL"));
        crawlWebsite(getUrl("tr"), getMySource("tr", "TR"));
        crawlWebsite(getUrl("ur"), getMySource("ur", "IN"));
        crawlWebsite(getUrl("zhs"), getMySource("zhs", "CN"));
        crawlWebsite(getUrl("zht"), getMySource("zht", "CN"));
        //getLogger().off();
    }

    @Override
    public NewsSource getMySource() {
        String sourceId = "global-voices";
        String name = "Global Voices";
        String description = "";
        String url = "https://globalvoices.org";
        String category = "general";
        String language = "en";
        String country = "GB";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(Logos.getLogo("global-voices"));

        return source;
    }

    protected String getUrl(String language) {
        return "https://" + language + ".globalvoices.org";
    }

    protected NewsSource getMySource(String language) {
        NewsSource source = getMySource();
        source.setSourceId(source.getSourceId() + "-" + language);
        source.setName(source.getName() + " (" + language + ")");
        source.setDescription(source.getDescription());
        source.setLanguage(language);
        source.setCountry(language.toUpperCase());
        source.setUrl(getUrl(language));
        return source;
    }

    protected NewsSource getMySource(String language, String country) {
        NewsSource source = getMySource();
        source.setSourceId(source.getSourceId() + "-" + language);
        source.setName(source.getName() + " (" + language + ")");
        source.setUrl(getUrl(language));
        source.setDescription(source.getDescription());
        if (language.startsWith("zh")) {
            source.setLanguage("zh");
        } else {
            source.setLanguage(language);
        }
        source.setCountry(country);
        return source;
    }
}
