package services;

import io.reactivex.Observable;
import org.neo4j.ogm.cypher.query.SortOrder;

/**
 * Created by zua on 15/04/17.
 * @param <T> The type of  managed domain entities
 */
public interface DBService<T> {

    public Observable<T> findAll();
    public Observable<T> findAllAsc();
    public Observable<T> findAllDesc();

    public Observable<T> findAllWithLimit(int limit);
    public Observable<T> findAllAscWithLimit(int limit);
    public Observable<T> findAllDescWithLimit(int limit);

    public Observable<T> findAll(String property, Object value);
    public Observable<T> findAllAsc(String property, Object value);
    public Observable<T> findAllDesc(String property, Object value);

    public Observable<T> findAll(String property, Object value, int limit);
    public Observable<T> findAllAsc(String property, Object value, int limit);
    public Observable<T> findAllDesc(String property, Object value, int limit);

    public Observable<T> findAll(String username);
    public Observable<T> findAllAsc(String username);
    public Observable<T> findAllDesc(String username);

    public Observable<T> findAll(String username, int limit);
    public Observable<T> findAllAsc(String username, int limit);
    public Observable<T> findAllDesc(String username, int limit);

    public Observable<T> findAll(String username, String property, Object value);
    public Observable<T> findAllAsc(String username, String property, Object value);
    public Observable<T> findAllDesc(String username, String property, Object value);
    
    public Observable<T> findAll(String username, String property, Object value, int limit);
    public Observable<T> findAllAsc(String username, String property, Object value, int limit);
    public Observable<T> findAllDesc(String username, String property, Object value, int limit);

  
    public T find(String id);    
    public T find(T object);

    public void save(T object);
    public void delete(String id);
    public long count();
    
    public Class<T> getClassType();
    public SortOrder getSortOrderAsc();
    public SortOrder getSortOrderDesc();
    public Observable<T> executeQuery(String query);   
    
}
