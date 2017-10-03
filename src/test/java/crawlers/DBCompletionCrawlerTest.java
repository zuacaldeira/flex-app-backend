/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import db.NewsSource;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import services.NewsSourceService;

/**
 *
 * @author zua
 */
public class DBCompletionCrawlerTest {


    public DBCompletionCrawlerTest() {
    }
    
    /**
     * Test of crawlWebsite method, of class TheBugleZACrawler.
     */
    @Test
    public void testCrawl() throws Exception {
        System.out.println("crawl");
        NewsSourceService service = new NewsSourceService();
        NewsSource source = new NewsSource();
        source.setSourceId("maka-angola");
        service.save(source);
        assertNotNull(service.find(source));
        String logoUrl = service.find(source).getLogoUrl();
        assertTrue(logoUrl ==null || logoUrl.equals(""));
        DBCompletionCrawler crawler = new DBCompletionCrawler();
        crawler.setSourcesService(service);
        crawler.crawl();
        assertEquals(0, new NewsSourceService().findSourcesWithoutLogo().size());
    }




}
