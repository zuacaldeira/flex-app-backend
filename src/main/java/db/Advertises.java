/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 *
 * @author zua
 */
@RelationshipEntity(type="ADVERTISES")
public class Advertises extends GraphRelationship {

    private static final long serialVersionUID = 4308653150737318550L;
    
    @StartNode
    private AdsProvider provider;
    
    @EndNode
    private NewsArticle news;
    
    private String advertisementUrl;

    public Advertises(AdsProvider provider, NewsArticle news) {
        this.provider = provider;
        this.news = news;
    }

    public Advertises() {
    }

    public AdsProvider getProvider() {
        return provider;
    }

    public void setProvider(AdsProvider provider) {
        this.provider = provider;
    }

    public NewsArticle getNews() {
        return news;
    }

    public void setNews(NewsArticle news) {
        this.news = news;
    }

    public String getAdvertisementUrl() {
        return advertisementUrl;
    }

    public void setAdvertisementUrl(String advertisementUrl) {
        this.advertisementUrl = advertisementUrl;
    }

    
}
