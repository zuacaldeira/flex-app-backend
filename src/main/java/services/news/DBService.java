package services.news;

import java.util.Collection;
import java.util.LinkedList;
import org.neo4j.ogm.cypher.query.SortOrder;

/**
 * Created by zua on 15/04/17.
 * @param <T> The type of  managed domain entities
 */
public interface DBService<T> {

    public Collection<T> findAll();
    public Collection<T> findAllAsc();
    public Collection<T> findAllDesc();

    public Collection<T> findAll(int limit);
    public Collection<T> findAllAsc(int limit);
    public Collection<T> findAllDesc(int limit);

    public Collection<T> findAll(String property, Object value);
    public Collection<T> findAllAsc(String property, Object value);
    public Collection<T> findAllDesc(String property, Object value);

    public Collection<T> findAll(String property, Object value, int limit);
    public Collection<T> findAllAsc(String property, Object value, int limit);
    public Collection<T> findAllDesc(String property, Object value, int limit);

    public Collection<T> findAll(String username);
    public Collection<T> findAllAsc(String username);
    public Collection<T> findAllDesc(String username);

    public Collection<T> findAll(String username, int limit);
    public Collection<T> findAllAsc(String username, int limit);
    public Collection<T> findAllDesc(String username, int limit);

    public Collection<T> findAll(String username, String property, Object value);
    public Collection<T> findAllAsc(String username, String property, Object value);
    public Collection<T> findAllDesc(String username, String property, Object value);
    
    public Collection<T> findAll(String username, String property, Object value, int limit);
    public Collection<T> findAllAsc(String username, String property, Object value, int limit);
    public Collection<T> findAllDesc(String username, String property, Object value, int limit);

  
    public T find(Long id);    
    public T find(T object);

    public T save(T object);

    public T update(T object);
    public T update(T dbObject, T object);    

    public void delete(Long id);

    public long count();
    
    public Class<T> getClassType();
    public SortOrder getSortOrderAsc();
    public SortOrder getSortOrderDesc();
    public LinkedList<T> executeQuery(String query);   
    
}
