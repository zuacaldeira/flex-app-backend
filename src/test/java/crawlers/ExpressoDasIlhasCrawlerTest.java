/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import db.NewsSource;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author zua
 */
@Ignore
public class ExpressoDasIlhasCrawlerTest extends AbstractCrawlerTest {


    public ExpressoDasIlhasCrawlerTest() {
    }
    
    @Test
    @Override
    public void testGetMySource() {
        System.out.println("getMySource");
        FlexNewsCrawler crawler = getCrawler();
        NewsSource source = crawler.getMySource();
        assertEquals("expresso-das-ilhas", source.getSourceId());        
        assertEquals("Expresso das Ilhas", source.getName());        
        assertEquals("pt", source.getLanguage());        
        assertEquals("CV", source.getCountry());        
        assertEquals("http://www.expressodasilhas.sapo.cv/", source.getUrl());        
        assertEquals("geral", source.getCategory());        
    }

    @Override
    protected FlexNewsCrawler getCrawler() {
        return new ExpressoDasIlhasCrawler();
    }

    

}
