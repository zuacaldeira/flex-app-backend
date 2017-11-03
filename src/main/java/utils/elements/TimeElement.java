/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.elements;

import crawlers.exceptions.TimeNotFoundException;
import java.util.Date;
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

    public Date getDate() throws TimeNotFoundException {
        try {
            //System.out.println("LANGUAGE ================ " + (language!=null) );
            //System.out.println("VALUE    ================ " + getValue());
           
            Date date = null;
            try {
                date = MyDateUtils.parseDate(getValue(), language);
            } catch(Exception e) {
                date = MyDateUtils.parseDate(getValue());                
            }
            //System.out.println("DATE    ================ " + date);
            return date;
        } catch(Exception e) {
            System.err.println(e.getMessage());
            throw new TimeNotFoundException();
        }
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
}
