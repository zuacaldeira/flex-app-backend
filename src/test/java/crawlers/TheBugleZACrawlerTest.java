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

/**
 *
 * @author zua
 */
public class TheBugleZACrawlerTest extends AbstractCrawlerTest {

    public TheBugleZACrawlerTest() {
    }

    @Override
    protected FlexNewsCrawler getCrawler() {
        return new TheBugleZACrawler();
    }

    @Test
    @Override
    public void testGetMySource() {
        System.out.println("getMySource");
        FlexNewsCrawler crawler = getCrawler();
        NewsSource source = crawler.getMySource();
        assertEquals("the-bugle", source.getSourceId());
        assertEquals("The Bugle", source.getName());
        assertEquals("en", source.getLanguage());
        assertEquals("ZA", source.getCountry());
        assertEquals("http://thebugle.co.za/home.php", source.getUrl());
        assertEquals("lifestyle", source.getCategory());
        assertEquals(Logos.getLogo("the-bugle"), source.getLogoUrl());
    }
    
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        FlexNewsCrawler crawler = getCrawler();
        Document document = getArticleDocumentPage(crawler);
        assertNotNull(document);
        
        String time = crawler.getTimeValue(document);
    }

}
