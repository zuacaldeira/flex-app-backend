/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.util.Date;
import utils.DateUtils;

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
        if(getValue() != null) {
            return DateUtils.getInstance().parseDate(getValue(), language);
        }
        return null;
    }
    
}
