/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author zua
 */
public class NamesUtils {

    private static NamesUtils instance;
    
    private NamesUtils() {
    }

    public static NamesUtils getInstance() {
        if (instance == null) {
            instance = new NamesUtils();
        }
        return instance;
    }

    
    public boolean isUrl(String value) {
        if(value != null) {
            return value.trim().toLowerCase().startsWith("http");
        }
        return false;
    }

    public Set<String> extractAuthors(String value) {
        Set<String> result = new HashSet<>();

        Set<String> names = extractAuthorsNames(value);
        for(String n: names) {
            String name = n.trim();
            if (isUrl(name)) {
                name = extractNameFromUrl(value);
            }
            result.add(name);
        };
        return result;
    }

    public Set<String> extractAuthorsNames(String value) {
        if(value == null || value.isEmpty()) {
            return new HashSet<>();
        }
        String[] parts = value.split(", ");
        Set<String> allParts = new HashSet<>(Arrays.asList(parts));
        for(String part: parts) {
            part = part.trim();
        }
        return allParts;
    }

    public String extractNameFromUrl(String authorName) {
        if(authorName == null) {
            return null;
        }
        if(authorName.contains("/")) {
            String name = authorName.trim();
            String[] parts = name.split("/");
            if(parts.length > 0) {
                String possibleName = parts[parts.length-1];
                return possibleName;
            }
        }
        return authorName;
    }

    private String toNameCase(String name) {
        String lowerCaseName = name.toLowerCase();
        if (lowerCaseName.length() > 1) {
            String c = lowerCaseName.substring(0, 1);
            return lowerCaseName.replaceFirst(c, c.toUpperCase());
        } else {
            return lowerCaseName;
        }
    }

    private String extractNameFromAbcNews(String authorName) {
        String rest = authorName.replace("http://www.abc.net.au/news/", "");
        String[] parts = rest.split("/");
        parts = parts[0].split("-");

        StringBuilder builder = new StringBuilder();
        if (parts.length >= 1 && parts[0] != null) {
            String r0 = toNameCase(parts[0]);
            builder.append(r0);
            if (parts.length >= 2 && parts[1] != null) {
                String r1 = toNameCase(parts[1]);
                builder.append(" ");
                builder.append(r1);
            }
        }
        return builder.toString();
    }

    private String extractNameFromDieZeit(String authorName) {
        String rest = authorName.replace("http://xml.zeit.de/autoren/", "");

        if (Character.isSpaceChar(rest.charAt(0))) {
            rest = rest.substring(3).trim();
        } else {
            rest = rest.substring(2).trim();
        }

        String[] parts = rest.split("/");

        if (parts[0].contains("-")) {
            parts = parts[0].split("-");
        }
        if (parts[0].contains("_")) {
            parts = parts[0].split("_");
        }

        StringBuilder builder = new StringBuilder();
        String r0 = toNameCase(parts[0]);
        builder.append(r0);

        if (parts.length >= 2) {
            String r1 = toNameCase(parts[1]);
            builder.append(" ");
            builder.append(r1);
        }

        String result = builder.toString();
        return result;
    }
}
