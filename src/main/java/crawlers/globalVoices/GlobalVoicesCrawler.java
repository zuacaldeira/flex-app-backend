/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers.globalVoices;

import crawlers.Logos;
import db.NewsSource;
import javax.ejb.Stateless;

/**
 *
 * @author zua
 */
@Stateless
public class GlobalVoicesCrawler extends GlobalVoicesAbstractCrawler {

    public GlobalVoicesCrawler() {
    }

    @Override
    public void crawl() {
        try {
            crawlWebsite(getUrl("am"), getMySource("am", "ET"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("ar"), getMySource("ar", "SD"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("aym"), getMySource("aym", "PE"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("bg"), getMySource("bg", "BG"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("bn"), getMySource("bn", "BD"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("ca"), getMySource("ca", "ES"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("cs"), getMySource("cs", "CZ"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("da"), getMySource("da", "DK"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("de"), getMySource("de"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("el"), getMySource("el", "GR"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("en"), getMySource("en", "GB"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("eo"), getMySource("eo", "001"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("es"), getMySource("es", "ES"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("fa"), getMySource("fa", "IR"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("fil"), getMySource("fil", "PH"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("fr"), getMySource("fr", "FR"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("he"), getMySource("he", "IL"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("hi"), getMySource("hi", "IN"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("hu"), getMySource("hu", "HU"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("id"), getMySource("in", "ID"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("it"), getMySource("it", "IT"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("jp"), getMySource("ja", "jp"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("km"), getMySource("km", "KH"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("ko"), getMySource("ko", "KR"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("mg"), getMySource("mg", "MG"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("mk"), getMySource("mk", "MK"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        //crawlWebsite(getUrl("my"), getMySource("my", "MM"));
        try {
            crawlWebsite(getUrl("ne"), getMySource("ne", "NP"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("or"), getMySource("or", "IN"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("pa"), getMySource("pa", "PK"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("pl"), getMySource("pl", "PL"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("ps"), getMySource("ps", "AF"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("pt"), getMySource("pt", "PT"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("ro"), getMySource("ro", "RO"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("ru"), getMySource("ru", "RU"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("sq"), getMySource("sq", "AL"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("sr"), getMySource("sr", "RS"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("sv"), getMySource("sv", "SE"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("sw"), getMySource("sw", "TZ"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("tet"), getMySource("tet", "TL"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("tr"), getMySource("tr", "TR"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("ur"), getMySource("ur", "IN"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("zhs"), getMySource("zhs", "CN"));
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }
        try {
            crawlWebsite(getUrl("zht"), getMySource("zht", "CN"));
            //getLogger().off();
        } catch (Exception e) {
            getLogger().error("Exception thrown %s", e.getMessage());
        }

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
