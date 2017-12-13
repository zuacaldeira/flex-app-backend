/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.queries;

/**
 *
 * @author zua
 */
public class SourcesQueries {

    public static String findAllCategoriesQuery() {
        return "MATCH (s:NewsSource) RETURN DISTINCT s.category ORDER BY s.category ASC";
    }

    public static String findAllNamesQuery() {
        return "MATCH (s:NewsSource) RETURN DISTINCT s.name ORDER BY s.name ASC";
    }

    public static String findAllLanguagesQuery() {
        return "MATCH (s:NewsSource) RETURN DISTINCT s.language ORDER BY s.language ASC";
    }

    public static String findAllCountriesQuery() {
        return "MATCH (s:NewsSource) RETURN DISTINCT s.country ORDER BY s.country ASC";
    }
    
    public static String findAllLocalesQuery() {
        return "MATCH (s:NewsSource) RETURN DISTINCT s.language+'_'+s.country";
    }
}
