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
public class ANacaoCVCrawlerTest extends Neo4jTest {


    public ANacaoCVCrawlerTest() {
    }
    
    @Test
    public void testGetMySource() {
        System.out.println("getMySource");
        FlexNewsCrawler crawler = new ANacaoCVCrawler();
        NewsSource source = crawler.getMySource();
        assertEquals("a-nacao", source.getSourceId());        
        assertEquals("A Nação", source.getName());        
        assertEquals("pt", source.getLanguage());        
        assertEquals("CV", source.getCountry());        
        assertEquals("http://anacao.cv/", source.getUrl());        
        assertEquals("geral", source.getCategory());        
        assertEquals(Logos.getLogo("a-nacao"), source.getLogoUrl());        
    }

    
    @Test
    public void testGetArticles() {
        System.out.println("getArticles");
        FlexNewsCrawler crawler = new ANacaoCVCrawler();
        Document document = crawler.openDocument(crawler.getMySource().getUrl());
        assertNotNull(crawler.getArticles(document));
    }
    
    /**
     * Test of crawlWebsite method, of class TheBugleZACrawler.
     */
    @Test
    public void testCrawl() throws Exception {
        System.out.println("crawl");
        ANacaoCVCrawler crawler = new ANacaoCVCrawler();
        crawler.setArticlesService(new NewsArticleService());
        crawler.setSourcesService(new NewsSourceService());
        crawler.crawl();
    }




}
