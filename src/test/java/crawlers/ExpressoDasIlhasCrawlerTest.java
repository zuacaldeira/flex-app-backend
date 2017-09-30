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

    @Override
    @Ignore
    public void testImportArticle() {
        super.testImportArticle(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void testGetSource() {
        super.testGetSource(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Ignore
    public void testGetTime() {
        super.testGetTime(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Ignore
    public void testGetAuthors() {
        super.testGetAuthors(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Ignore
    public void testGetContent() {
        super.testGetContent(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Ignore
    public void testGetImageUrl() {
        super.testGetImageUrl(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Ignore
    public void testGetTitle() {
        super.testGetTitle(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Ignore
    public void testGetUrl() {
        super.testGetUrl(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Ignore
    public void testGetArticles() {
        super.testGetArticles(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    

    

}
