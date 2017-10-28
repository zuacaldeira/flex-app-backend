/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers.jornalDeAngola;

import crawlers.AbstractCrawlerTest;
import crawlers.FlexNewsCrawler;
import crawlers.Logos;
import crawlers.exceptions.ArticlesNotFoundException;
import crawlers.exceptions.AuthorsNotFoundException;
import crawlers.exceptions.DocumentNotFoundException;
import db.NewsSource;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author zua
 */
public class JornalDeAngolaCrawlerTest extends AbstractCrawlerTest {

    public JornalDeAngolaCrawlerTest() {
    }

    @Test
    @Override
    public void testGetMySource() {
        System.out.println("getMySource");
        FlexNewsCrawler crawler = getCrawler();
        NewsSource source = crawler.getMySource();
        assertEquals("jornal-de-angola", source.getSourceId());
        assertEquals("Jornal de Angola", source.getName());
        assertEquals("pt", source.getLanguage());
        assertEquals("AO", source.getCountry());
        assertEquals("http://jornaldeangola.sapo.ao", source.getUrl());
        assertEquals("geral", source.getCategory());
        assertEquals(Logos.getLogo("jornal-de-angola"), source.getLogoUrl());
    }

    @Override
    protected FlexNewsCrawler getCrawler() {
        return new JornalDeAngolaCrawler();
    }

    @Override
    @Test
    //@Test(expected = AuthorsNotFoundException.class)
    public void testGetAuthors() throws AuthorsNotFoundException, DocumentNotFoundException, ArticlesNotFoundException {
        super.testGetAuthors();
    }
    
    

}
