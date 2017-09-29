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
public class GlobalVoicesCrawlerTest extends AbstractCrawlerTest {

    public GlobalVoicesCrawlerTest() {
    }

    @Test
    public void testGetMySource() {
        System.out.println("getMySource");
        FlexNewsCrawler crawler = getCrawler();
        NewsSource source = crawler.getMySource();
        assertEquals("global-voices", source.getSourceId());
        assertEquals("Global Voices", source.getName());
        assertEquals("en", source.getLanguage());
        assertEquals("GB", source.getCountry());
        assertEquals("https://globalvoices.org", source.getUrl());
        assertEquals("general", source.getCategory());
        assertEquals(Logos.getLogo("global-voices"), source.getLogoUrl());
    }

    @Override
    protected FlexNewsCrawler getCrawler() {
        return new GlobalVoicesCrawler();
    }

}
