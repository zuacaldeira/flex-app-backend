/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.crawlers;

import services.news.FlexUserService;
import services.news.NewsArticleService;
import services.news.NewsAuthorService;
import services.news.NewsSourceService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author zua
 */
public class ServiceLocator {

    public static final String NEWS_API_SERVICE = "java:global/flex-app/NewsApiService!services.news.NewsApiService";
    public static final String NEWS_ARTICLE_SERVICE = "java:global/flex-app/NewsArticleService!services.news.NewsArticleService";
    public static final String NEWS_SOURCE_SERVICE = "java:global/flex-app/NewsSourceService!services.news.NewsSourceService";
    public static final String NEWS_AUTHOR_SERVICE = "java:global/flex-app/NewsAuthorService!services.news.NewsAuthorService";
    public static final String FLEX_USER_SERVICE = "java:global/flex-app/FlexUserService!services.news.FlexUserService";

    private static InitialContext context;

    private static ServiceLocator instance;

    private ServiceLocator() {
    }

    public static ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    private Object findService(String name) {
        try {
            if (context == null) {
                context = new InitialContext();
            }
            Object o = context.lookup(name);
            return o;
        } catch (NamingException ex) {
            System.out.println("NOT FOUND " + name);
            return null;
        }
    }

    public NewsArticleService findArticlesService() {
        return (NewsArticleService) findService(NEWS_ARTICLE_SERVICE);
    }

    public NewsSourceService findSourcesService() {
        return (NewsSourceService) findService(NEWS_SOURCE_SERVICE);
    }

    public NewsAuthorService findAuthorsService() {
        return (NewsAuthorService) findService(NEWS_AUTHOR_SERVICE);
    }

    public FlexUserService findUserService() {
        return (FlexUserService) findService(FLEX_USER_SERVICE);
    }

    public void setInitialContext(InitialContext aContext) {
        context = aContext;
    }

}
