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
public class JornalDeAngolaCrawler extends FlexNewsCrawler {

    public JornalDeAngolaCrawler() {
        super();
    }

    private String getUrl() {
        return "http://jornaldeangola.sapo.ao";
    }

    
    @Schedule(hour = "*", minute = "4/10", persistent = false)
    public void crawl() {
        crawlWebsite(getUrl(), getMySource());
        crawlWebsite(getUrl() + "/cultura", getMySource());
        crawlWebsite(getUrl() + "/desporto", getMySource());
        crawlWebsite(getUrl() + "/economia", getMySource());
        crawlWebsite(getUrl() + "/gente", getMySource());
        crawlWebsite(getUrl() + "/mundo", getMySource());
        crawlWebsite(getUrl() + "/opiniao", getMySource());
        crawlWebsite(getUrl() + "/politica", getMySource());
        crawlWebsite(getUrl() + "/provincias", getMySource());
        crawlWebsite(getUrl() + "/reportagem", getMySource());
        crawlWebsite(getUrl() + "/sociedade", getMySource());
    }
    
    @Override
    public NewsSource getMySource() {
        String sourceId = "jornal-de-angola";
        String name = "Jornal de Angola";
        String description = "";
        String url = getUrl();
        String category = "geral";
        String language = "pt";
        String country = "AO";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(Logos.getLogo("jornal-de-angola"));
        
        return source;
    }

    
    
    private String getArticleSelector() {
        return "div.highlight-wrapper article";
    }
    
    private String getUrlSelector() {
        return "a";
    }  
    
    private String getTitleSelector() {
        return "main  article  h1";
    }
    
    private String getImageSelector() {
        return "main article img";
    }

    

    private String getDescriptionSelector() {
        return "main article p.lead ~ p";
    }
    
    
    private String getTimeSelector() {
        return "main  article  p > time.date-time";
    }
    
    private String getAuthorsSelector() {
        return "main article p.info-autor";
    }

    

    @Override
    protected String getTimeValue(Document document) throws TimeNotFoundException {
        if(document == null) {
            throw new IllegalArgumentException("Document cannot be null.");
        }
        Element first = document.select(getTimeSelector()).first();
        if(first!= null) {
            String time = first.attr("datetime") ;
            return time;
        }
        throw new TimeNotFoundException();
    }
    

    @Override
    protected String getUrlValue(Element article) throws UrlNotFoundException {
        if(article == null) {
            throw new IllegalArgumentException("Article cannot be null.");
        }
        Element url = article.select(getUrlSelector()).first();
        if(url != null) {
            return url.absUrl("href");
        }
        throw new UrlNotFoundException();
    }

    @Override
    protected String getTitleValue(Document document) throws TitleNotFoundException {
        if(document == null) {
            throw new IllegalArgumentException("Document cannot be null.");
        }
        Element first = document.select(getTitleSelector()).first();
        if(first != null) {
            String text = first.text();
            if(!text.isEmpty()) {
                return text;
            }
        }
        throw new TitleNotFoundException();
    }

    @Override
    protected String getImageUrlValue(Document document) throws ImageNotFoundException {
        if(document == null) {
            throw new IllegalArgumentException("Document cannot be null.");
        }
        Element first = document.select(getImageSelector()).first();
        if(first != null) {
            String value = first.attr("src");
            if(value != null && value.startsWith("http://imgs")) {
                return value;
            }
        }
        throw new ImageNotFoundException();
    }

    @Override
    protected String getContentValue(Document document) throws ContentNotFoundException {
        if(document == null) {
            throw new IllegalArgumentException("Document cannot be null.");
        }
        Element first = document.select(getDescriptionSelector()).first();
        if(first != null) {
            String text = first.text().trim();
            if(text != null && !text.isEmpty()) {
                return text;
            }
        }
        throw new ContentNotFoundException();
    }

    @Override
    protected String getAuthorsValue(Document document) throws AuthorsNotFoundException {
        if(document == null) {
            throw new IllegalArgumentException("Document cannot be null.");
        }
        Elements elements = document.select(getAuthorsSelector());
        String text = elements.text().trim();
        if(text != null && !text.isEmpty()) {
            if(text.contains("|")) {
                int i = text.indexOf('|');
                text = text.substring(0, i);
                return text;
            }
        }
        throw new AuthorsNotFoundException();
    }

    @Override
    protected Elements getArticles(Document document) throws ArticlesNotFoundException {
        if(document == null) {
            throw new IllegalArgumentException("Document cannot be null.");
        }
        Elements articles = document.select(getArticleSelector());
        if(!articles.isEmpty()) {
            return articles;
        }
        throw new ArticlesNotFoundException();
    }
}
