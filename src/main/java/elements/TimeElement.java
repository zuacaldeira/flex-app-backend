/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang3.time.DateUtils;
import utils.MyDateUtils;

/**
 *
 * @author zua
 */
public class TimeElement extends ArticleElement {

    private String language;

    public TimeElement(String value, String language) {
        super(value);
        this.language = language;
    }

    public Date getDate() {
        try {
            if(getValue() != null && !getValue().isEmpty()) {
                if(language != null) {
                    Locale locale = Locale.forLanguageTag(language);
                    return DateUtils.parseDate(getValue(), locale, MyDateUtils.getParsePatterns());
                }
                else {
                    return DateUtils.parseDate(getValue(), MyDateUtils.getParsePatterns());                
                }
            }
            else {
                return new Date();
            }
        } catch(Exception e) {
            return new Date();
        }
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
}
