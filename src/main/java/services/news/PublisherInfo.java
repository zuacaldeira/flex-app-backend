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
public class PublisherInfo implements Serializable {

    private static final long serialVersionUID = -7805549552195855269L;

    private final String sourceId;
    private final String name;
    private final long count;

    public PublisherInfo(String sourceId, String name, long count) {
        this.sourceId = sourceId;
        this.name = name;
        this.count = count;
    }

    public String getSourceId() {
        return sourceId;
    }

    
    public String getName() {
        return name;
    }

    public long getCount() {
        return count;
    }

}
