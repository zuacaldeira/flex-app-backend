package db;

import java.io.Serializable;
import java.util.Objects;
import org.neo4j.ogm.annotation.GraphId;

/**
 * Created by zua on 15/04/17.
 */
public abstract class GraphEntity implements Serializable {

    private static final long serialVersionUID = 842576810489298435L;

    @GraphId    
    private Long id;

    public GraphEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof GraphEntity) {
            GraphEntity otherEntity = (GraphEntity) other;
            return Objects.equals(id, otherEntity.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(id);
        return hash;
    }
}
