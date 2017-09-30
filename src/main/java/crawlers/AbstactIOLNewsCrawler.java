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
    @Override
    public void crawl() {
        crawlWebsite(getMySource().getUrl(), getMySource());
    }
    
    @Override
    protected Elements getArticles(Document document) {
        Elements article = document.select("article");
        if(article != null) {
            getLogger().log("%s %d %s", "Found ", article.size(), " articles");
            return article;
        }
        throw new ArticlesNotFoundException();
    }

    @Override
    protected String getUrlValue(Element article) {
        Element link = article.select("a").first();
        if (link != null) {
            getLogger().log("%s %s", "Found url ", link.absUrl("href"));
            return link.absUrl("href");
        }
        throw new UrlNotFoundException();
    }

    @Override
    protected String getTitleValue(Document document) {
        Elements title = document.select("main  article  header h1");
        if(title != null) {
            getLogger().log("%s %s", "Found title ", title.text().trim());
            return title.text().trim();
        }
        throw new TitleNotFoundException();
    }

    @Override
    protected String getImageUrlValue(Document document) {
        Elements images = document.select("#top > main > div.wrapper.white-bg.main-article > article > div:nth-child(1) > div.article-body > figure > meta:nth-child(1)");
        if (images != null) {
            getLogger().log("%s %s", "Found image ", images.attr("content"));
            return images.attr("content");
        }
        throw new ImageNotFoundException();
    }

    @Override
    protected String getContentValue(Document document) {
        Elements content = document.select("#article-more-body > p:nth-child(1)");
        if (content != null) {
            getLogger().log("%s %s", "Found content ", content.text());
            return content.text();
        }
        throw new ContentNotFoundException();
    }

    @Override
    protected String getAuthorsValue(Document document) {
        Elements authors = document.select("main  article  header p.meta span strong");
        if (!authors.isEmpty() && !authors.text().isEmpty()) {
            getLogger().log("%s", "Found authors " + authors.text());
            return authors.text();
        }
        throw new AuthorsNotFoundException();
    }

    @Override
    protected String getTimeValue(Document document) {
        Elements time = document.select("main  article  header p.meta span[itemprop=datePublished]");
        if (time != null) {
            getLogger().log("%s", "Found time " + time.attr("content"));
            return time.attr("content");
        }
        throw new TimeNotFoundException();
    }    

}
