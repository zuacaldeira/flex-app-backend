/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import db.NewsSource;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author zua
 */
public class AVerdadeOnlineCrawlerTest extends AbstractCrawlerTest {


    public AVerdadeOnlineCrawlerTest() {
    }
    
    @Test
    @Override
    public void testGetMySource() {
        System.out.println("getMySource");
        FlexNewsCrawler crawler = getCrawler();
        NewsSource source = crawler.getMySource();
        assertEquals("verdade-online", source.getSourceId());        
        assertEquals("@Verdade Online", source.getName());        
        assertEquals("pt", source.getLanguage());        
        assertEquals("MZ", source.getCountry());        
        assertEquals("http://www.verdade.co.mz", source.getUrl());        
        assertEquals("geral", source.getCategory());        
        assertEquals(Logos.getLogo("verdade-online"), source.getLogoUrl());        
    }

    @Override
    protected FlexNewsCrawler getCrawler() {
        return new AVerdadeOnlineCrawler();
    }

}
