/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zua
 */
public class DateUtils {

    private static DateUtils instance;

    private DateUtils() {
    }

    public static DateUtils getInstance() {
        if (instance == null) {
            instance = new DateUtils();
        }
        return instance;
    }

    public SimpleDateFormat getBasicSimpleDateFormat() {
        return new SimpleDateFormat("dd/MM/YYY HH:mm");
    }

    public SimpleDateFormat getDefaultSimpleDateFormat(String language) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    }

    /**
     * Formats a date using the basic time pattern.
     *
     * @param date
     * @return A formated string representing the date. If the date is null the
     * method also return null.
     */
    public String formatDate(Date date) {
        if (date != null) {
            SimpleDateFormat format = getBasicSimpleDateFormat();
            String t = format.format(date);
            return t;
        }
        return null;
    }

    public Date parseDate(String dateString, String language) {
        if (dateString == null) {
            return null;
        } else {
            try {
                SimpleDateFormat format = getDefaultSimpleDateFormat(language);
                return format.parse(dateString);
            } catch (ParseException ex) {
                Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
    }
    
    public String normalizeTime(String dateString, String language) {
        String[] formats = {
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd HH:mm:ssZ",
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            "yyyy-MM-dd'T'HH:mm:ssZ",
            "yyyy-MM-dd'T'HH:mm:ssSSSZ",
            "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
            "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        };
        
        String result = null;
        for(String format: formats) {
            result = normalizeTimeWithPattern(format, dateString, language);
            if (result != null) {
                //System.out.println("Converted " + dateString + " into " + result);
                return result;
            }
        }
        
        return null;
    }
    
    private String normalizeTimeWithPattern(String datePattern, String dateString, String language) {
        if(dateString == null) {
            return null;
        }
        try {
            SimpleDateFormat formatIn = new SimpleDateFormat(datePattern);
            Date date = formatIn.parse(dateString);

            SimpleDateFormat formatOut = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", new Locale(language));
            return formatOut.format(date);
        } catch (ParseException px) {
            return null;
        }
    }
   
}
