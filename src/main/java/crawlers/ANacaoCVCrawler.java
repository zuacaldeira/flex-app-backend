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
public class ANacaoCVCrawler extends FlexNewsCrawler {

    public ANacaoCVCrawler() {
    }

    
    @Schedule(hour="*", minute="*/13")
    public void crawl() {
        crawlWebsite(getMySource().getUrl(), getMySource());
    }
    
    @Override
    public NewsSource getMySource() {
        String sourceId = "a-nacao";
        String name = "A Nação";
        String description = "";
        String url = "http://anacao.cv/";
        String category = "geral";
        String language = "pt";
        String country = "CV";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(Logos.getLogo(sourceId));
        
        return source;
    }

    @Override
    protected Elements getArticles(Document document) {
        Elements articles = document.select("div.masonry_post");
        getLogger().log("%s %d %s", "Found ", articles.size(), " articles");
        return articles;
    }

    @Override
    protected String getUrlValue(Element article) {
        Elements urls = article.select("a.link_title");
        getLogger().log("%s %s", "Found url ", urls.attr("href"));
        return urls.attr("href");
    }

    @Override
    protected String getTitleValue(Document document) {
        Elements titles = document.select("div.single_title > h1");
        getLogger().log("%s %s", "Found Titles ", titles.text());
        return titles.text();
    }

    @Override
    protected String getImageUrlValue(Document document) {
        Elements images = document.select("div.single_thumbnail > a");
        getLogger().log("%s %s", "Found Images ", images.attr("href"));
        return images.attr("href");
    }

    @Override
    protected String getContentValue(Document document) {
        Elements contents = document.select("#single_excerpt_post_title");
        getLogger().log("%s %s", "Found content ", contents.text());
        return contents.text();
    }

    @Override
    protected String getAuthorsValue(Document document) {
        Elements paragraphs = document.select("div.single_text > p");
        getLogger().log("%s %s", "Found author", paragraphs.last().text());
        return paragraphs.last().text();
    }

    @Override
    protected String getTimeValue(Document document) {
        Elements times = document.select("div.post_meta_line div.post_time");
        getLogger().log("%s %s", "Found time ", times.first().text());
        return times.first().text();
    }
    
}
