/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import org.junit.Test;
import services.Neo4jTest;
import services.NewsSourceService;

/**
 *
 * @author zua
 */
public class DBCompletionCrawlerTest extends Neo4jTest {


    public DBCompletionCrawlerTest() {
    }
    
   
    /**
     * Test of crawlWebsite method, of class TheBugleZACrawler.
     */
    @Test
    public void testCrawl() throws Exception {
        System.out.println("crawl");
        DBCompletionCrawler crawler = new DBCompletionCrawler();
        crawler.setSourcesService(new NewsSourceService());
        crawler.crawl();
    }




}
