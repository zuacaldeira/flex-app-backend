/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author zua
 */
public class MyDateUtils {

    public static String[] getParsePatterns() {
        return new String[]{
            "yyyy-MM-dd",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd HH:mm:ssZ",
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd'T'HH:mm:ssZ",
            "yyyy-MM-dd'T'HH:mm:ss.SZ",
            "yyyy-MM-dd'T'HH:mm:ss.SSZ",
            "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ",
            "yyyy-MM-dd'T'HH:mm:ssXXX",
            "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
        };
    }

    public static String extractLanguage(String localeString) {
        if (localeString != null && localeString.contains("_")) {
            return localeString.split("_")[0];
        } else {
            throw new IllegalArgumentException("Expected data in format 'language_COUNTRY', but found " + localeString);
        }
    }

    public static String extractCountry(String localeString) {
        if (localeString != null && localeString.contains("_")) {
            String[] parts = localeString.split("_");
            if(parts.length > 1) {
                return parts[1];
            } else {
                return parts[0].toUpperCase();
            }
        } else {
            throw new IllegalArgumentException("Expected data in format 'language_COUNTRY', but found " + localeString);
        }
    }

    public static String getLanguageNameFromPattern(String localeString) {
        LanguageCode code = LanguageCode.getByCodeIgnoreCase(extractLanguage(localeString));
        if(code != null) {
            return code.getName();
        }
        else {
            System.err.println("Could not get the language name for " + localeString);
            return Locale.forLanguageTag(extractLanguage(localeString)).getDisplayLanguage();
        }
    }

    public static String getCountryNameFromPattern(String localeString) {
        CountryCode code = CountryCode.getByCodeIgnoreCase(extractCountry(localeString));
        if(code != null) {
            return code.getName();
        }
        else {
            System.err.println("Could not get the language name for " + localeString);
            return Locale.forLanguageTag(extractLanguage(localeString)).getDisplayCountry();
        }
    }

    public static String getLanguageCode(String displayLanguage) {
        List<LanguageCode> codes = LanguageCode.findByName(displayLanguage);
        
        return codes.get(0).name();
    }

    public static String getCountryCode(String displayCountry) {
        List<CountryCode> codes = CountryCode.findByName(displayCountry);
        return codes.get(0).name();
    }
    
    public static String getLanguage(String languageCode) {
        LanguageCode code = LanguageCode.getByCodeIgnoreCase(languageCode);
        return code.getName();
    }

    public static String getCountry(String countryCode) {
        CountryCode code = CountryCode.getByCodeIgnoreCase(countryCode);
        return code.getName();
    }
    
}
