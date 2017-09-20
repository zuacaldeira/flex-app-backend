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
        String result = normalizeTime("yyyy-MM-dd HH:mm:ss", dateString, language);
        if (result == null) {
            result = normalizeTime("yyyy-MM-dd HH:mm:ssZ", dateString, language);
        }
        if (result == null) {
            result = normalizeTime("yyyy-MM-dd'T'HH:mm:ss", dateString, language);
        }
        if (result == null) {
            result = normalizeTime("yyyy-MM-dd'T'HH:mm:ss'Z'", dateString, language);
        }
        if (result == null) {
            result = normalizeTime("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", dateString, language);
        }
        if (result == null) {
            result = normalizeTime("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", dateString, language);
        }
        if (result == null) {
            result = normalizeTime("yyyy-MM-dd'T'HH:mm:ss.SSSZ", dateString, language);
        }

        System.out.println("Converted " + dateString + " into " + result);
        if (result != null) {
            return result;
        } else {
            return null;
        }
    }
    
    public String normalizeTime(String formatPattern, String dateString, String language) {
        try {
            
            SimpleDateFormat formatIn = new SimpleDateFormat(formatPattern, new Locale(language));
            Date date = formatIn.parse(dateString);

            SimpleDateFormat formatOut = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", new Locale(language));
            return formatOut.format(date);
            
        } catch (ParseException px) {
            return null;
        }
    }
}
