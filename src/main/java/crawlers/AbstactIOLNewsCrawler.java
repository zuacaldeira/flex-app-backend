/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import javax.ejb.Schedule;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author zua
 */
public abstract class AbstactIOLNewsCrawler extends FlexNewsCrawler {
    
    protected abstract String getUrl();
    
    @Schedule(hour="*", minute="*/10")
    protected void crawl() {
        crawlWebsite(getUrl(), getMySource());
    }
    
    @Override
    protected Elements getArticles(Document document) {
        Elements article = document.select("article");
        getLogger().log("%s %d %s", "Found ", article.size(), " articles");
        return article;
    }

    @Override
    protected String getUrlValue(Element article) {
        Element link = article.select("a").first();
        if (link != null) {
            getLogger().log("%s %s", "Found url ", link.absUrl("href"));
            return link.absUrl("href");
        }
        return null;
    }

    @Override
    protected String getTitleValue(Document document) {
        Elements title = document.select("main  article  header h1");
        if(title != null) {
            getLogger().log("%s %s", "Found title ", title.text().trim());
            return title.text().trim();
        }
        return null;
    }

    @Override
    protected String getImageUrlValue(Document document) {
        Elements images = document.select("#top > main > div.wrapper.white-bg.main-article > article > div:nth-child(1) > div.article-body > figure > meta:nth-child(1)");
        if (images != null) {
            getLogger().log("%s %s", "Found image ", images.attr("content"));
            return images.attr("content");
        }
        return null;
    }

    @Override
    protected String getContentValue(Document document) {
        Elements content = document.select("#article-more-body > p:nth-child(1)");
        if (content != null) {
            getLogger().log("%s %s", "Found content ", content.text());
            return content.text();
        }
        return null;
    }

    @Override
    protected String getAuthorsValue(Document document) {
        Elements authors = document.select("main  article  header p.meta span strong");
        if (!authors.isEmpty() && !authors.text().isEmpty()) {
            getLogger().log("%s", "Found authors " + authors.text());
            return authors.text();
        }
        return getMySource().getName();
    }

    @Override
    protected String getTimeValue(Document document) {
        Elements time = document.select("main  article  header p.meta span[itemprop=datePublished]");
        if (time != null) {
            getLogger().log("%s", "Found time " + time.attr("content"));
            return time.attr("content");
        }
        return null;
    }    

}
