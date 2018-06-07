/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news;

import java.io.Serializable;

/**
 *
 * @author zua
 */
public class CategoryInfo implements Serializable {

    private static final long serialVersionUID = 7115161112082158968L;

    private String tag;
    private String name;
    private long count;

    public CategoryInfo(String tag, String name, long count) {
        this.name = name;
        this.tag = tag;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    
}
