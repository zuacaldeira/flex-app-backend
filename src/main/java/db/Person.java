/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author zua
 */
@NodeEntity
public class Person extends GraphEntity {

    private static final long serialVersionUID = 5462730122562191198L;

    @GraphId    
    private String name;
    private String url;
    private String photoUrl;
    

    @Override
    public String getPropertyName() {
        return "name";
    }

    @Override
    public String getPropertyValue() {
        return name;
    }

    @Override
    public boolean hasUrl() {
        return true;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
    
}
