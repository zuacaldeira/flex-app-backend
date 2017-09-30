/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import db.NewsSource;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class GlobalVoicesCrawlerTest extends AbstractCrawlerTest {


    @DataProvider
    public static Object[][] languageProvider() {
        return new Object[][] {
            {"en"},
            {"pt"}
        };
    }
    
    @DataProvider
    public static Object[][] languageAndCountryProvider() {
        return new Object[][] {
            {"en", "GB"},
            {"pt", "PT"}
        };
    }

    public GlobalVoicesCrawlerTest() {
    }

    @Test
    @Override
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
    
    @Test
    @UseDataProvider("languageProvider")
    public void testGetMySource(String language) {
        GlobalVoicesCrawler crawler = (GlobalVoicesCrawler) getCrawler();
        NewsSource source = crawler.getMySource(language);
        assertEquals("global-voices-" + language, source.getSourceId());
        assertEquals("Global Voices (" + language + ")", source.getName());
        assertEquals(language, source.getLanguage());
        assertEquals(language.toUpperCase(), source.getCountry());
        assertEquals("https://" + language + ".globalvoices.org", source.getUrl());
        assertEquals("general", source.getCategory());
        assertEquals(Logos.getLogo("global-voices"), source.getLogoUrl());
    }
    
    @Test
    @UseDataProvider("languageAndCountryProvider")
    public void testGetMySource(String language, String country) {
        GlobalVoicesCrawler crawler = (GlobalVoicesCrawler) getCrawler();
        NewsSource source = crawler.getMySource(language, country);
        assertEquals("global-voices-" + language, source.getSourceId());
        assertEquals("Global Voices (" + language + ")", source.getName());
        assertEquals(language, source.getLanguage());
        assertEquals(country, source.getCountry());
        assertEquals("https://" + language + ".globalvoices.org", source.getUrl());
        assertEquals("general", source.getCategory());
        assertEquals(Logos.getLogo("global-voices"), source.getLogoUrl());
    }

    @Override
    protected FlexNewsCrawler getCrawler() {
        return new GlobalVoicesCrawler();
    }

}