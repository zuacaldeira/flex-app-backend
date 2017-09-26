/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class MyDateUtilsTest {
    
    public MyDateUtilsTest() {
    }

    @DataProvider
    public static Object[][] locale2LanguageNameData() {
        return new Object[][]{
            {"pt_AO", "Portuguese"},
            {"pt_MZ", "Portuguese"},
            {"pt_PT", "Portuguese"},
            {"pt_", "Portuguese"},
            {"fil_", "Filipino"}
        };
    }

    @DataProvider
    public static Object[][] locale2CountryNameData() {
        return new Object[][]{
            {"pt_AO", "Angola"},
            {"pt_MZ", "Mozambique"},
            {"pt_PT", "Portugal"},
            {"pt_", "Portugal"},
            {"fil_PH", "Philippines"}
            
        };
    }

    @DataProvider
    public static Object[][] locale2CountryCodeData() {
        return new Object[][]{
            {"pt_AO", "AO"},
            {"pt_MZ", "MZ"},
            {"pt_PT", "PT"},
            {"pt_", "PT"}
        };
    }

    @DataProvider
    public static Object[][] locale2LanguageCodeData() {
        return new Object[][]{
            {"pt_AO", "pt"},
            {"pt_MZ", "pt"},
            {"pt_PT", "pt"},
            {"pt_", "pt"}            
        };
    }

    @DataProvider
    public static Object[][] language2LanguageCodeData() {
        return new Object[][]{
            {"Portuguese", "pt"},
            {"English", "en"},
            {"German", "de"},
            {"Amharic", "am"},
        };
    }

    @DataProvider
    public static Object[][] country2CountryCodeData() {
        return new Object[][]{
            {"Portugal", "PT"},
            {"United Kingdom", "GB"},
            {"Germany", "DE"},
            {"Ethiopia", "ET"},
        };
    }

    @DataProvider
    public static Object[][] languageCode2LanguageNameData() {
        return new Object[][]{
            {"pt", "Portuguese"},
            {"en", "English"},
            {"de", "German"},
            {"am", "Amharic"}
        };
    }

    @DataProvider
    public static Object[][] countryCode2CountryNameData() {
        return new Object[][]{
            {"PT", "Portugal"},
            {"GB", "United Kingdom"},
            {"DE", "Germany"},
            {"ET", "Ethiopia"},
        };
    }

    @Test
    @UseDataProvider("locale2CountryNameData")
    public void testGetCountry(String localeString, String country) {
        assertEquals(country, MyDateUtils.getCountryNameFromPattern(localeString));
    }


    @Test
    @UseDataProvider("locale2LanguageNameData")
    public void testGetLanguage(String localeString, String language) {
        assertEquals(language, MyDateUtils.getLanguageNameFromPattern(localeString));
    }

    @Test
    @UseDataProvider("language2LanguageCodeData")
    public void testGetLanguageCode(String displayLanguage, String languageCode) {
        assertEquals(languageCode, MyDateUtils.getLanguageCode(displayLanguage));
    }

    @Test
    @UseDataProvider("country2CountryCodeData")
    public void testGetCountryCode(String displayCountry, String countryCode) {
        assertEquals(countryCode, MyDateUtils.getCountryCode(displayCountry));
    }
    
    @Test
    @UseDataProvider("languageCode2LanguageNameData")
    public void testGetLanguageName(String languageCode, String languageName) {
        assertEquals(languageName, MyDateUtils.getLanguage(languageCode));
    }

    @Test
    @UseDataProvider("countryCode2CountryNameData")
    public void testGetCountryName(String countryCode, String countryName) {
        assertEquals(countryName, MyDateUtils.getCountry(countryCode));
    }
    
}
