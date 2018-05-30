/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v1.backend.utils;

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
            throw new IllegalArgumentException();
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
}
