/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author zua
 */
@NodeEntity
public class AmazonBook extends GraphEntity {

    private static final long serialVersionUID = 590262573267973488L;

    private String apn;
    private String title;
    private String url;

    @Override
    public String getPropertyName() {
        return "APN";
    }

    @Override
    public String getPropertyValue() {
        return apn;
    }

    @Override
    public boolean hasUrl() {
        return true;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
    
}
