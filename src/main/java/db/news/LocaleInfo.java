/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.news;

import db.GraphEntity;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author zua
 */
@NodeEntity
public class LocaleInfo extends GraphEntity {

    private static final long serialVersionUID = 3160650477025899785L;

    /* GraphId */
    @GraphId
    @Index(primary = true, unique = true)
    private String localeString;
    private String languageCode;
    private String countryCode;
    
    public LocaleInfo() {        
    }
    
    public LocaleInfo(String language, String country) {
        this.languageCode = language;
        this.countryCode = country;        
    }

    public String getLocaleString() {
        return localeString;
    }

    public void setLocaleString(String localeString) {
        this.localeString = localeString;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
