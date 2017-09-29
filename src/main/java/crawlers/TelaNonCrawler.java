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
public class TelaNonCrawler extends FlexNewsCrawler {

    public TelaNonCrawler() {
    }

    @Schedule(hour="*", minute="*/10")
    public void crawl() {
        crawlWebsite(getMySource().getUrl(), getMySource());
    }

    @Override
    public NewsSource getMySource() {
        NewsSource source = new NewsSource("tela-non", "Téla Nón", "", "http://www.telanon.info/", "geral", "pt", "ST");
        source.setLogoUrl(Logos.getLogo("tela-non"));
        return source;
    }

    @Override
    protected Elements getArticles(Document document) {
        Elements articles = document.select("body div.blog-item-holder div.blog-content-wrapper");
        getLogger().info("%s %d %s", "Found ", articles.size(), " articles");
        return articles;
    }

    @Override
    protected String getUrlValue(Element article) {
        Elements urls = article.select("div > a");
        String url = urls.attr("href");
        getLogger().info("%s %s", "Found url: ", url);
        return url;
    }

    @Override
    protected String getTitleValue(Document document) {
        Elements titles = document.select("body h1 a");
        String title = titles.first().text();
        getLogger().info("%s %s", "Found title: ", title);
        return title;
    }

    @Override
    protected String getImageUrlValue(Document document) {
        Elements images = document.select("body div.gdl-blog-full > div.blog-content > div.blog-media-wrapper.gdl-image > a > img");
        String image = images.attr("src");
        getLogger().info("%s %s", "Found image: ", image);
        return image;
    }

    @Override
    protected String getContentValue(Document document) {
        Elements contents = document.select("body div.gdl-blog-full > div.blog-content > p");
        String content = contents.first().text();
        getLogger().info("%s %s", "Found content: ", content);
        return content;
    }

    @Override
    protected String getAuthorsValue(Document document) {
        return getMySource().getName();
    }

    @Override
    protected String getTimeValue(Document document) {
        Elements paragraphs = document.select("body div.blog-date > a");
        String time = paragraphs.first().text();
        getLogger().info("%s %s", "Found time: ", time);
        return time;
    }

    

}