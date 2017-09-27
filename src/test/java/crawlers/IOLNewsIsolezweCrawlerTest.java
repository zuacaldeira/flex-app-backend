/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import db.NewsSource;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import services.Neo4jTest;
import services.NewsArticleService;
import services.NewsSourceService;

/**
 *
 * @author zua
 */
public class IOLNewsIsolezweCrawlerTest extends Neo4jTest {


    public IOLNewsIsolezweCrawlerTest() {
    }

    
    @Test
    public void testGetUrl() {
        IOLNewsIsolezweCrawler crawler = new IOLNewsIsolezweCrawler();
        assertEquals("https://www.iol.co.za/isolezwe", crawler.getUrl());
    }
    
    @Test
    public void testGetMySource() {
        IOLNewsIsolezweCrawler crawler = new IOLNewsIsolezweCrawler();
        NewsSource source = crawler.getMySource();
        assertEquals("iol-news-isolezwe", source.getSourceId());
        assertEquals("https://www.iol.co.za/isolezwe", source.getUrl());
        assertEquals("sw", source.getLanguage());
        assertEquals("ZA", source.getCountry());
        assertEquals(Logos.getLogo("iol-news-isolezwe"), source.getLogoUrl());
    }
    /**
     * Test of crawlWebsite method, of class TheBugleZACrawler.
     */
    @Test
    public void testCrawl() throws Exception {
        System.out.println("crawl");
        IOLNewsIsolezweCrawler crawler = new IOLNewsIsolezweCrawler();
        crawler.setArticlesService(new NewsArticleService());
        crawler.setSourcesService(new NewsSourceService());
        crawler.crawl();
    }




}
