/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.AdsProvider;
import db.Advertises;
import db.Neo4jSessionFactory;
import db.NewsArticle;
import java.util.HashMap;
import javax.ejb.Singleton;
import javax.ejb.Schedule;
import javax.ejb.Startup;
import utils.ShortUrlUtils;

/**
 *
 * @author zua
 */
@Singleton
@Startup
public class ShortestCompletionWorker {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Schedule(hour="*", minute = "*/10")
    public void completeWithShortestUrl() {
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
