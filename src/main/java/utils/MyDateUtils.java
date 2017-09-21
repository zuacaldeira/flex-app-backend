/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Locale;

/**
 *
 * @author zua
 */
public class MyDateUtils {

    public static String[] getParsePatterns() {
        return new String[]{
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

    public static Locale getLocale(String localeString) {
        return new Locale(getLanguage(localeString), getCountry(localeString));
    }

    public static String getLanguage(String localeString) {
        if (localeString != null && localeString.contains("_")) {
            return localeString.split("_")[0];
        } else {
            throw new IllegalArgumentException("Expected data in format 'language_country', but found " + localeString);
        }
    }

    public static String getCountry(String localeString) {
        if (localeString != null && localeString.contains("_")) {
            return localeString.split("_")[1].toUpperCase();
        } else {
            throw new IllegalArgumentException("Expected data in format 'language_country', but found " + localeString);
        }
    }

}
