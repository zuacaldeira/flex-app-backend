/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import elements.TitleElement;
import elements.UrlElement;
import elements.AuthorsElement;
import elements.TimeElement;
import elements.ContentElement;
import elements.ImageUrlElement;
import db.NewsArticle;
import db.NewsAuthor;
import db.NewsSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.ejb.EJB;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import services.NewsArticleServiceInterface;
import services.NewsSourceServiceInterface;
import utils.FlexLogger;

/**
 *
 * @author zua
 */
public abstract class FlexNewsCrawler {

    @EJB private NewsArticleServiceInterface articlesService;
    @EJB private NewsSourceServiceInterface sourcesService;

    
    private FlexLogger logger;
    
    public FlexNewsCrawler() {
        logger = new FlexLogger(getClass());
    }
    
    public void crawlWebsite(String url, NewsSource source) {
        try {
            logger.info("Loading articles from: %s", url);
            Document document = openDocument(url);
            crawlUrl(document, source);
            sourcesService.save(source);
            logger.info("Finished: %s", url);
        } catch (Exception e) {
            logger.error("Exception loading %s %s", url, e.getClass().getSimpleName());
        }
    }

    /**
     * Connects to the web address.
     *
     * @param url A web address url, starting with http(s).
     * @return The top document representing the content of web address.
     */
    protected Document openDocument(String url) throws DocumentNotFoundException {
        try {
            return Jsoup.connect(url).userAgent("Mozilla").get();
        } catch (Exception e) {
            logger.error("%s %s: %s", "\tERROR - Couldn't open document ", url, e.getMessage());
            throw new DocumentNotFoundException();
        }
    }

    protected NewsSource getSource() {
        return getMySource();
    }

    private void crawlUrl(Document document, final NewsSource source) throws ArticlesNotFoundException {
        Elements articles = getArticles(document);
        for (Element article : articles) {   
            try {
                importArticle(article, source);
            } catch(Exception e) {
                logger.error("%s", e.getClass().getSimpleName());
            }
        }
    }

    protected void importArticle(Element article, NewsSource source) throws UrlNotFoundException, TitleNotFoundException, ImageNotFoundException, ContentNotFoundException, AuthorsNotFoundException, DocumentNotFoundException, TimeNotFoundException {
        //prettyPrint(article);
        logger.log("Processing article: %s", article.text());

        String articleUrl = getUrl(article);
        Document document = openDocument(articleUrl);
        String title = getTitle(document);
        String imageUrl = getImageUrl(document);
        String description = getContent(document);
        Date date = getPublishedAt(document);
        Set<NewsAuthor> authors = getNewsAuthors(getAuthors(document));
        saveArticle(articleUrl, title, imageUrl, description, date, authors, source);
    }
    
    private void saveArticle(String articleUrl, String title, String imageUrl, String description, Date date, Set<NewsAuthor> authors, NewsSource source) {
        boolean shouldSave = 
                title != null 
                && !title.isEmpty() 
                && (articlesService != null) 
                && (articlesService.findArticleWithTitle(title) == null);
        if(shouldSave) {
            NewsArticle newsArticle = new NewsArticle();
            newsArticle.setSourceId(source.getSourceId());
            newsArticle.setLanguage(source.getLanguage());
            newsArticle.setCountry(source.getCountry());
            newsArticle.getCategories().add(source.getCategory());

            newsArticle.setTitle(title);
            newsArticle.setUrl(articleUrl);

            newsArticle.setImageUrl(imageUrl);
            newsArticle.setPublishedAt(date);
            newsArticle.setDescription(description);

            newsArticle.setAuthors(authors);
            source.setCorrespondents(authors);

            articlesService.save(newsArticle);
            logger.info("\tSaved new article: %s", newsArticle.getTitle());
        } 
        
        else {
            logger.log("\tIgnored old article: %s", title);
        }
    }

    public abstract NewsSource getMySource();

    protected abstract Elements getArticles(Document document) throws ArticlesNotFoundException;

    public final String getUrl(Element article) throws UrlNotFoundException {
        return getUrlElement(article).getValue();
    }

    public final UrlElement getUrlElement(Element article) throws UrlNotFoundException {
        return new UrlElement(getUrlValue(article));
    }

    protected abstract String getUrlValue(Element article) throws UrlNotFoundException;

    public final String getTitle(Document document) throws TitleNotFoundException {
        return getTitleElement(document).getValue();
    }

    public final TitleElement getTitleElement(Document document) throws TitleNotFoundException {
        return new TitleElement(getTitleValue(document));
    }

    protected abstract String getTitleValue(Document document) throws TitleNotFoundException ;

    public final String getImageUrl(Document document) throws ImageNotFoundException {
        return getImageUrlElement(document).getValue();
    }

    public final ImageUrlElement getImageUrlElement(Document document) throws ImageNotFoundException {
        return new ImageUrlElement(getImageUrlValue(document));
    }

    protected abstract String getImageUrlValue(Document document) throws ImageNotFoundException;

    public final String getContent(Document document) throws ContentNotFoundException {
        return getContentElement(document).getValue();
    }

    public final ContentElement getContentElement(Document document) throws ContentNotFoundException {
        return new ContentElement(getContentValue(document));
    }

    protected abstract String getContentValue(Document document) throws ContentNotFoundException;

    public final Set<String> getAuthors(Document document) {
        try {
            AuthorsElement authorsElement = getAuthorsElement(document);
            if (authorsElement != null && !authorsElement.getAuthors().isEmpty()) {
                return authorsElement.getAuthors();
            }
        } catch(AuthorsNotFoundException ex) {}
        Set<String> result = new HashSet<>();
        result.add(getSource().getName());
        return result;
    }

    public final AuthorsElement getAuthorsElement(Document document) throws AuthorsNotFoundException {
        return new AuthorsElement(getAuthorsValue(document));
    }

    protected abstract String getAuthorsValue(Document document) throws AuthorsNotFoundException;

    protected Set<NewsAuthor> getNewsAuthors(Set<String> names) {
        Set<NewsAuthor> result = new HashSet<>();
        if(names.isEmpty()) {
            result.add(findAuthor(getMySource().getName()));
        }
        else {
            for(String name: names) {
                if(name != null && !name.isEmpty()) {
                    result.add(findAuthor(name));
                }
            }
        }
        return result;
    }

    private NewsAuthor findAuthor(String name) {
        return new NewsAuthor(name);
    }
    
    public final Date getPublishedAt(Document document) throws TimeNotFoundException {
        TimeElement timeElement = getTimeElement(document);
        timeElement.setLanguage(getSource().getLanguage());
        return timeElement.getDate();
    }

    public final TimeElement getTimeElement(Document document) throws TimeNotFoundException {
        return new TimeElement(getTimeValue(document), getSource().getLanguage());
    }

    protected abstract String getTimeValue(Document document) throws TimeNotFoundException;

    public FlexLogger getLogger() {
        return logger;
    }

    public void setArticlesService(NewsArticleServiceInterface articlesService) {
        this.articlesService = articlesService;
    }

    public void setSourcesService(NewsSourceServiceInterface sourcesService) {
        this.sourcesService = sourcesService;
    }

    public abstract void crawl();

}
