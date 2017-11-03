/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import crawlers.dbCompletion.LogoCompletionWorker;
import db.AdsProvider;
import db.Advertises;
import db.Neo4jSessionFactory;
import db.NewsArticle;
import java.util.HashMap;
import services.NewsSourceService;
import utils.ShortUrlUtils;

/**
 *
 * @author zua
 */
public class Main {

    public static void main(String... args) {
        completeLogos();
        completeShortest();
    }

    private static void completeLogos() {
        LogoCompletionWorker dbCrawler = new LogoCompletionWorker();
        dbCrawler.setSourcesService(new NewsSourceService());
        dbCrawler.crawl();
    }

    private static void completeShortest() {
        String cypher = "MATCH (n:NewsArticle) WHERE NOT (n)--(:AdsProvider) RETURN n LIMIT 100";
        Iterable<NewsArticle> iterable = Neo4jSessionFactory.getInstance().getNeo4jSession().query(NewsArticle.class, cypher, new HashMap<>());
        iterable.forEach(article -> {
            System.out.printf("Need short url for article %s\n", article.getTitle());
            if (article.getUrl() != null) {
                String shortUrl = new ShortUrlUtils().getShortUrl(article.getUrl());
                if (shortUrl != null && !shortUrl.isEmpty()) {
                    AdsProvider provider = Neo4jSessionFactory.
                            getInstance().
                            getNeo4jSession().
                            queryForObject(AdsProvider.class,
                                    "MATCH (a:AdsProvider) WHERE a.name='shorte.st' RETURN a",
                                    new HashMap<>());
                    Advertises advertises = new Advertises(provider, article);
                    advertises.setAdvertisementUrl(shortUrl);
                    Neo4jSessionFactory.getInstance().getNeo4jSession().save(advertises);
                }
                else {
                    System.out.printf("Call to shorten failed\n");
                }
            }
        });
    }
}
