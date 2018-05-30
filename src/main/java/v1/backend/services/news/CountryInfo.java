/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v1.backend.services.news;

import java.io.Serializable;

/**
 *
 * @author zua
 */
public class CountryInfo implements Serializable {

    private static final long serialVersionUID = 28495202868370770L;

    private final String countryCode;
    private final String country;
    private final long count;

    public CountryInfo(String countryCode, String country, long count) {
        this.countryCode = countryCode;
        this.country = country;
        this.count = count;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountry() {
        return country;
    }

    public long getCount() {
        return count;
    }
    
    
}
