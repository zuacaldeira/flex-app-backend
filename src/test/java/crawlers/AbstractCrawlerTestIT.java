/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import org.junit.Test;
import services.NewsArticleService;
import services.NewsSourceService;

/**
 *
 * @author zua
 */
public abstract class AbstractCrawlerTestIT {
    
    protected abstract FlexNewsCrawler getCrawler();

    /**
     * Test of crawlWebsite method, of class TheBugleZACrawler.
     */
    @Test
    public void testCrawl() throws Exception {
        System.out.println("crawl");
        FlexNewsCrawler crawler = getCrawler();
        crawler.setArticlesService(new NewsArticleService());
        crawler.setSourcesService(new NewsSourceService());
        crawler.crawl();
    }
    
    @Test
    public void testCrawlWebsite() {
        FlexNewsCrawler crawler = getCrawler();
        crawler.setArticlesService(new NewsArticleService());
        crawler.setSourcesService(new NewsSourceService());
        crawler.crawlWebsite("", crawler.getMySource());
    }

}
