/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v1.db.news;

import v1.db.GraphEntity;
import java.util.Objects;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author zua
 */
@NodeEntity
public class Tag extends GraphEntity {

    private static final long serialVersionUID = -2218568840405529695L;

    @Index(unique = true, primary = true)
    private String tag;

    public Tag() {
    }

    
    public Tag(String category) {
        this.tag = category;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.tag);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tag other = (Tag) obj;
        if (!Objects.equals(this.tag, other.tag)) {
            return false;
        }
        return true;
    }
    
    
}
