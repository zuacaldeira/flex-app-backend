/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import db.NewsSource;
import org.jsoup.nodes.Document;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import services.Neo4jTest;
import services.NewsArticleService;
import services.NewsSourceService;

/**
 *
 * @author zua
 */
public class GlobalVoicesCrawlerTest extends Neo4jTest {


    public GlobalVoicesCrawlerTest() {
    }
    
    @Test
    public void testGetMySource() {
        System.out.println("getMySource");
        FlexNewsCrawler crawler = new GlobalVoicesCrawler();
        NewsSource source = crawler.getMySource();
        assertEquals("global-voices", source.getSourceId());        
        assertEquals("Global Voices", source.getName());        
        assertEquals("en", source.getLanguage());        
        assertEquals("GB", source.getCountry());        
        assertEquals("https://globalvoices.org", source.getUrl());        
        assertEquals("general", source.getCategory());        
        assertEquals(Logos.getLogo("global-voices"), source.getLogoUrl());        
    }

    
    @Test
    public void testGetArticles() {
        System.out.println("getArticles");
        FlexNewsCrawler crawler = new GlobalVoicesCrawler();
        Document document = crawler.openDocument(crawler.getMySource().getUrl());
        assertNotNull(crawler.getArticles(document));
    }
    
    /**
     * Test of crawlWebsite method, of class TheBugleZACrawler.
     */
    @Test
    public void testCrawl() throws Exception {
        System.out.println("crawl");
        GlobalVoicesCrawler crawler = new GlobalVoicesCrawler();
        crawler.setArticlesService(new NewsArticleService());
        crawler.setSourcesService(new NewsSourceService());
        crawler.crawl();
    }




}
