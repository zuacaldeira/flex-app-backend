/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zua
 */
public final class NewsSourceLogos {

    private static Map<String, String> logos;
    
    static {
        logos = new HashMap<>();
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
        logos.put("", "");
    }

    public static String getLogo(String sourceId) {
        return logos.get(sourceId);
    }
    
}
