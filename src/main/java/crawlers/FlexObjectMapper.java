/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import json.MultipleSourcesResponse;
import json.MultipleArticlesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.NewsArticle;
import db.NewsAuthor;
import db.NewsSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import services.NewsArticleServiceInterface;
import services.NewsSourceServiceInterface;
import utils.FlexLogger;

/**
 *
 * @author zua
 */
@Singleton
public class FlexObjectMapper {

    private static final String API_KEY = "5b4e00f3046843138d8368211777a4f2";
    private static final String SOURCES_URL = "http://newsapi.org/v1/sources?";
    private static final String ARTICLES_URL = "http://newsapi.org/v1/articles?";

    private ObjectMapper objectMapper;
    @EJB
    private NewsArticleServiceInterface articlesService;
    @EJB
    private NewsSourceServiceInterface sourcesService;

    private FlexLogger logger;

    public FlexObjectMapper() {
        objectMapper = new ObjectMapper();
        logger = new FlexLogger(FlexObjectMapper.class);
    }

    public String getSourcesQuery(String category, String language2Letter, String country) {
        boolean hasCategory = category != null && !category.isEmpty();
        boolean haslanguage = language2Letter != null && !language2Letter.isEmpty();
        boolean hasCountry = country != null && !country.isEmpty();
        boolean hasApiKey = API_KEY != null && !API_KEY.isEmpty();

        StringBuilder builder = new StringBuilder(SOURCES_URL);

        if (hasCategory) {
            builder.append("category=");
            builder.append(category);
            builder.append("&");
        }

        if (haslanguage) {
            builder.append("language=");
            builder.append(language2Letter);
            builder.append("&");
        }

        if (hasCountry) {
            builder.append("country=");
            builder.append(country);
            builder.append("&");
        }

        if (hasApiKey) {
            builder.append("apiKey=");
            builder.append(API_KEY);
        }

        return builder.toString();
    }

    public String getArticlesQuery(String sourceId) {
        boolean hasSourceId = sourceId != null && !sourceId.isEmpty();
        boolean hasApiKey = API_KEY != null && !API_KEY.isEmpty();

        StringBuilder builder = new StringBuilder(ARTICLES_URL);

        if (hasSourceId) {
            builder.append("source=");
            builder.append(sourceId);
            builder.append("&");
        }

        if (hasApiKey) {
            builder.append("apiKey=");
            builder.append(API_KEY);
        }

        return builder.toString();
    }

    public String makeApiCall(String url) {
        try (InputStream is = new URL(url).openConnection().getInputStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            return readAllData(rd);
        } catch (Exception e) {
            logger.error("%s", "Error calling news api..." + e.getMessage());
            throw new services.NewsServiceException(e);
        }
    }

    @Schedule(hour = "*", minute = "7/10")
    public void loadAllData() {
        logger.info("%s", "Start loading data from " + SOURCES_URL);
        try {
            String result = makeApiCall(getSourcesQuery(null, null, null));

            MultipleSourcesResponse sourcesResponse = objectMapper.readValue(result, MultipleSourcesResponse.class);
            if ("ok".equals(sourcesResponse.getStatus())) {
                sourcesResponse.getSources().forEach(ssr -> {
                    NewsSource source = ssr.convert2NewsSource();
                    loadAllArticles(source);
                    saveReturnSource(source);
                });
            }
        } catch (IOException ex) {
            logger.error("%s", ex.getMessage());
        }
        logger.info("%s", "Finished: " + SOURCES_URL);
    }

    public void loadAllArticles(NewsSource source) {
        try {
            String result = makeApiCall(getArticlesQuery(source.getSourceId()));

            MultipleArticlesResponse articlesResponse = objectMapper.readValue(result, MultipleArticlesResponse.class);
            if ("ok".equals(articlesResponse.getStatus())) {
                logger.info("%s", "Processing source " + source.getName());
                articlesResponse.getArticles().forEach(sar -> {
                    NewsArticle article = sar.convert2NewsArticle(source);
                    boolean shouldSave = article.getTitle() != null
                            && !article.getTitle().isEmpty()
                            && articlesService.findArticleWithTitle(article.getTitle()) == null;
                    if (shouldSave) {
                        logger.info("%s", "\tSaved new article " + article.getTitle());
                        NewsAuthor author = sar.convert2NewsAuthor(source);
                        author.addArticle(article);
                        source.addCorrespondent(author);
                    }
                });
            }
        } catch (IOException ex) {
            logger.error("%s", ex.getMessage());
        }
    }

    protected void saveArticle(NewsArticle article) {
        if (articlesService != null) {
            articlesService.save(article);
        }
    }

    protected NewsSource saveReturnSource(NewsSource source) {
        if (sourcesService != null) {
            sourcesService.save(source);
            return sourcesService.findSourceWithSourceId(source.getSourceId());
        } else {
            return source;
        }
    }

    private String readAllData(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    void setNewsArticlesService(NewsArticleServiceInterface newsArticleService) {
        articlesService = newsArticleService;
    }

}
