/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.ads;

import db.GraphEntity;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author zua
 */
@NodeEntity
public class AdsProvider extends GraphEntity implements Comparable<AdsProvider> {

    private static final long serialVersionUID = -361708908491302783L;
    

    @GraphId    
    private String url;
    private String name;
    private String apiKey;

    public AdsProvider() {
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int compareTo(AdsProvider o) {
        return this.getName().compareTo(o.getName());
    }

}
