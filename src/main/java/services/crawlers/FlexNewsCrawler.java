/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.crawlers;

import db.news.NewsArticle;
import db.news.NewsAuthor;
import db.news.NewsSource;
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
import services.news.NewsArticleServiceInterface;
import services.news.NewsAuthorServiceInterface;
import services.news.NewsSourceServiceInterface;
import utils.FlexLogger;

/**
 *
 * @author zua
 */
public abstract class FlexNewsCrawler {

    @EJB private NewsArticleServiceInterface articlesService;
    @EJB private NewsAuthorServiceInterface authorsService;
    @EJB private NewsSourceServiceInterface sourcesService;

    private FlexLogger logger;
    
    public FlexNewsCrawler() {
        logger = new FlexLogger(getClass());
    }
    
    public void crawlWebsite(String url, NewsSource source) {
        try {
            logger.log("Loading articles from: %s", url);
            Set<String> visited = new HashSet<>();
            visited.add(url);
            crawlUrl(openDocument(url), source, visited);
            logger.log("Finished: %s", url);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error: %s -- %s", url, e.getMessage());
        }
    }

    /**
     * Connects to the web address.
     *
     * @param url A web address url, starting with http(s).
     * @return The top document representing the content of web address.
     */
    protected Document openDocument(String url) {
        try {
            return Jsoup.connect(url).userAgent("Mozilla").get();
        } catch (Exception e) {
            logger.log("\tERROR - Couldn't open document %s caused by: %s", url, e.getMessage());
            return null;
        }
    }

    private NewsSource getSource() {
        NewsSource source = getMySource();
        NewsSource dbSource = sourcesService.findSourceWithSourceId(source.getSourceId());
        return (dbSource != null) ? dbSource : source;
    }

    private void crawlUrl(Document document, final NewsSource source, Set<String> visitedUrls) {
        if (document != null) {
            Elements articles = getArticles(document);
            for (Element article : articles) {                
                importArticle(article, source);
            }
        }
    }

    private void importArticle(Element article, NewsSource source) {
        //prettyPrint(article);
        logger.log("Processing article: %s", article.text());

        String articleUrl = getUrl(article);
        if(articleUrl == null) {
            logger.log("\tMissing url: %s", article.text());
            return;
        }

        
        if(!articleUrl.endsWith(".pdf")) {
            Document document = openDocument(articleUrl);
            if(document == null) {
                logger.log("\tCould't open document: %s", articleUrl);
                return;
            }

            String title = getTitle(document);
            if(title == null) {
                logger.log("\tMissing title: %s", article.text());
                return;
            }

            String imageUrl = getImageUrl(document);
            if(imageUrl == null) {
                logger.log("\tMissing image url: %s", article.text());
                //return;
            }

            String description = getContent(document);
            if(description == null) {
                logger.log("\tMissing description: %s", article.text());
                return;
            }

            Date date = getPublishedAt(document);
            if(date == null) {
                logger.log("\tMissing published at: %s", article.text());
                return;
            }

            Set<NewsAuthor> authors = getNewsAuthors(getAuthors(document));
            if(authors == null) {
                logger.log("\tMissing authors: %s", article.text());
                return;
            }

            boolean inDb = !articlesService.findAll("title", title).isEmpty();
            if(inDb) {
                logger.log("\tIgnored old article: %s", title);
                return;
            }

            saveArticle(articleUrl, title, imageUrl, description, date, authors, source);
        }

    }
    
    private void saveArticle(String articleUrl, String title, String imageUrl, String description, Date date, Set<NewsAuthor> authors, NewsSource source) {
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
        
        NewsArticle dbArticle = articlesService.save(newsArticle);
        if(dbArticle.getId() != null) {
            logger.log("\tStored new article: %s", newsArticle.getTitle());
        } 
        else {
            logger.log("\tRepeated article: %s", newsArticle.getTitle());
        }
    }

    public abstract NewsSource getMySource();

    protected abstract Elements getArticles(Document document);

    public String getUrl(Element article) {
        UrlElement urlElement = getUrlElement(article);
        if (urlElement != null) {
            return urlElement.getValue();
        }
        return null;
    }

    public UrlElement getUrlElement(Element article) {
        return new UrlElement(getUrlValue(article));
    }

    protected abstract String getUrlValue(Element article);

    public String getTitle(Document document) {
        TitleElement titleElement = getTitleElement(document);
        if (titleElement != null) {
            return titleElement.getValue();
        }
        return null;
    }

    public TitleElement getTitleElement(Document document) {
        return new TitleElement(getTitleValue(document));
    }

    protected abstract String getTitleValue(Document document);

    public String getImageUrl(Document document) {
        ImageUrlElement imageUrlElement = getImageUrlElement(document);
        if (imageUrlElement != null) {
            return imageUrlElement.getValue();
        }
        return null;
    }

    public ImageUrlElement getImageUrlElement(Document document) {
        return new ImageUrlElement(getImageUrlValue(document));
    }

    protected abstract String getImageUrlValue(Document document);

    public String getContent(Document document) {
        ContentElement contentElement = getContentElement(document);
        if (contentElement != null) {
            return contentElement.getValue();
        }
        return null;
    }

    public ContentElement getContentElement(Document document) {
        return new ContentElement(getContentValue(document));
    }

    protected abstract String getContentValue(Document document);

    public Set<String> getAuthors(Document document) {
        AuthorsElement authorsElement = getAuthorsElement(document);
        if (authorsElement != null && !authorsElement.getAuthors().isEmpty()) {
            return authorsElement.getAuthors();
        }
        Set<String> result = new HashSet<>();
        result.add(getSource().getName());
        return result;
    }

    public AuthorsElement getAuthorsElement(Document document) {
        return new AuthorsElement(getAuthorsValue(document));
    }

    protected abstract String getAuthorsValue(Document document);

    protected String getFullImageUrl(String src) {
        if(!src.startsWith(getSource().getUrl())) {
            return getSource().getUrl() + src;
        }
        return src;
    }

    private Set<NewsAuthor> getNewsAuthors(Set<String> names) {
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
        NewsAuthor author = new NewsAuthor(name);
        NewsAuthor dbAuthor = authorsService.find(author);
        return (dbAuthor != null)? dbAuthor : author;
    }
    
    public Date getPublishedAt(Document document) {
        TimeElement timeElement = getTimeElement(document);
        if (timeElement != null) {
            return timeElement.getDate();
        }
        return new Date();
    }

    public TimeElement getTimeElement(Document document) {
        return new TimeElement(getTimeValue(document), getSource().getLanguage());
    }

    protected abstract String getTimeValue(Document document);

    protected String normalizeTime(String inputPattern, String dateString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(inputPattern, new Locale(getMySource().getLanguage()));
            Date date = format.parse(dateString);
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", new Locale(getMySource().getLanguage()));
            return format2.format(date);
        } catch (ParseException ex) {
            logger.error("Parsing error during time normalization: ", ex.getMessage());
        }
        return null;
    }

    public FlexLogger getLogger() {
        return logger;
    }


}
