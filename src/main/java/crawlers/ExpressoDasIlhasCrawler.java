/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import db.NewsSource;
import javax.ejb.Schedule;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author zua
 */
public class ExpressoDasIlhasCrawler extends FlexNewsCrawler {

    public ExpressoDasIlhasCrawler() {
    }

    @Override
    public NewsSource getMySource() {
        String sourceId = "expresso-das-ilhas";
        String name = "Expresso das Ilhas";
        String description = "";
        String url = "http://www.expressodasilhas.sapo.cv/";
        String category = "geral";
        String language = "pt";
        String country = "CV";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(Logos.getLogo(sourceId));
        
        return source;
    }

    @Override
    protected Elements getArticles(Document document) {
        Elements articles = document.select("div.nspArts.bottom > div > div > div > div > div");
        getLogger().info("%s %d %s", "Found ", articles.size(), " articles");
        return articles;
    }

    @Override
    protected String getUrlValue(Element article) {
        Elements urls = article.select("a");
        if(urls != null) {
            getLogger().info("%s %d %s", "Found ", urls.size(), " urls");
        }
        return urls.attr("href");
    }

    @Override
    protected String getTitleValue(Document document) {
        throw new TitleNotFoundException();
    }

    @Override
    protected String getImageUrlValue(Document document) {
        throw new ImageNotFoundException();
    }

    @Override
    protected String getContentValue(Document document) {
        throw new ContentNotFoundException();
    }

    @Override
    protected String getAuthorsValue(Document document) {
        throw new AuthorsNotFoundException();
    }

    @Override
    protected String getTimeValue(Document document) {
        throw new TimeNotFoundException();
    }

    @Schedule(hour="*", minute="*/30")
    public void crawl() {
        crawlWebsite(getMySource().getUrl(), getMySource());
    }
    
}
