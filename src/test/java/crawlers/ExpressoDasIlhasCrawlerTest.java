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
import org.junit.Ignore;
import org.junit.Test;
import services.Neo4jTest;
import services.NewsArticleService;
import services.NewsSourceService;

/**
 *
 * @author zua
 */
@Ignore
public class ExpressoDasIlhasCrawlerTest extends Neo4jTest {


    public ExpressoDasIlhasCrawlerTest() {
    }
    
    @Test
    public void testGetMySource() {
        System.out.println("getMySource");
        FlexNewsCrawler crawler = new ExpressoDasIlhasCrawler();
        NewsSource source = crawler.getMySource();
        assertEquals("expresso-das-ilhas", source.getSourceId());        
        assertEquals("Expresso das Ilhas", source.getName());        
        assertEquals("pt", source.getLanguage());        
        assertEquals("CV", source.getCountry());        
        assertEquals("http://www.expressodasilhas.sapo.cv/", source.getUrl());        
        assertEquals("geral", source.getCategory());        
    }

    
    @Test
    public void testGetArticles() {
        System.out.println("getArticles");
        FlexNewsCrawler crawler = new ExpressoDasIlhasCrawler();
        Document document = crawler.openDocument("http://www.expressodasilhas.sapo.cv");
        assertNotNull(crawler.getArticles(document));
    }
    
    /**
     * Test of crawlWebsite method, of class TheBugleZACrawler.
     */
    @Test
    public void testCrawl() throws Exception {
        System.out.println("crawl");
        ExpressoDasIlhasCrawler crawler = new ExpressoDasIlhasCrawler();
        crawler.setArticlesService(new NewsArticleService());
        crawler.setSourcesService(new NewsSourceService());
        crawler.crawl();
    }




}
