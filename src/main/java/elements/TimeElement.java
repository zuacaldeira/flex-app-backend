/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.time.DateUtils;
import utils.MyDateUtils;

/**
 *
 * @author zua
 */
public class TimeElement extends ArticleElement {

    private final String language;

    public TimeElement(String value, String language) {
        super(value);
        this.language = language;
    }

    public Date getDate() {
        if(getValue() != null && !getValue().isEmpty()) {
            try {
                return DateUtils.parseDate(getValue(), MyDateUtils.getParsePatterns());
            } catch (ParseException ex) {
            }
        }
        return new Date();
    }
    
}
