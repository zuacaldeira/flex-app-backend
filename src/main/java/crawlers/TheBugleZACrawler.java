/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import db.NewsSource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author zua
 */
@Singleton
public class TheBugleZACrawler extends FlexNewsCrawler {

    public TheBugleZACrawler() {
        super();
    }

    private String getUrl() {
        return "http://thebugle.co.za/home.php";
    }
   @Schedule(hour = "*", minute = "*/25")
    public void crawl() {
        crawlWebsite(getUrl(), getMySource());
    }

    @Override
    public NewsSource getMySource() {
        String sourceId = "the-bugle";
        String name = "The Bugle";
        String description
                = "The Bugle is a tabloid format weekly community magazine that has been in circulation for 17 years and has a print order of 40 000 copies per week.\n"
                + "\n"
                + "The Bugle is distributed house to house between Umgeni River in Durban North and Tugela River on the North Coast, comprising the Sugar Coast and Dolphin Coast. Including Westville, Morningside, Durban Central, Berea, Durban North, Morningside, Berea, Upper and Lower La Lucia, Upper and Lower Umhlanga, Sunningdale, Prestondale, Glen Anil, Mt. Edgecombe, Glenashley, La Mercy, Mt Moreland, Umdloti, Tongaat, Salt Rock, Ballito,Umhlali, Shakaskraal, Stanger, Zinkwazi and Blythedale.";
        String url = getUrl();
        String category = "lifestyle";
        String language = "en";
        String country = "ZA";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(Logos.getLogo(sourceId));

        return source;
    }

    @Override
    protected Elements getArticles(Document document) {
        return document.select("div.post");
    }

    @Override
    protected String getUrlValue(Element article) {
        Element link = article.select("a").first();
        if (link != null) {
            return link.absUrl("href");
        }
        return null;
    }

    @Override
    protected String getTitleValue(Document document) {
        Elements elements = document.select("body h1.title");
        return elements.text().trim();
    }

    @Override
    protected String getImageUrlValue(Document document) {
        Element image = document.select("div.top > div.image > a > img[src]").first();
        if (image != null) {
            getLogger().info("%s", "Found image " + image.absUrl("src"));
            return image.absUrl("src");
        }
        return null;
    }

    @Override
    protected String getContentValue(Document document) {
        Element content = document.select("div.body.wrap-text > p").first();
        if (content != null) {
            return content.text();
        }
        return null;
    }

    @Override
    protected String getAuthorsValue(Document document) {
        Elements authors = document.select("div.info > b:nth-child(1)");
        if (!authors.isEmpty() && !authors.text().isEmpty()) {
            return authors.text();
        }
        return "The Bugle";
    }

    @Override
    protected String getTimeValue(Document document) {
        Elements time = document.select("div.info > b:nth-child(2)");
        if (time != null) {
            return time.text();
        }
        return null;
    }
}
