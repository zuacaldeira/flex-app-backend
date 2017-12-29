/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.services.news;

import java.io.Serializable;

/**
 *
 * @author zua
 */
public class LanguageInfo implements Serializable {

    private static final long serialVersionUID = -3202365785996720229L;

    private final String languageCode;
    private final String language;
    private final long count;

    public LanguageInfo(String languageCode, String language, long count) {
        this.languageCode = languageCode;
        this.language = language;
        this.count = count;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getLanguage() {
        return language;
    }

    public long getCount() {
        return count;
    }

}
