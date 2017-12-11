package services;

import org.neo4j.ogm.cypher.query.SortOrder;

/**
 * Created by zua on 15/04/17.
 *
 * @param <T> The type of managed domain entities
 */
public interface DBService<T> {

    public Iterable<T> findAll();

    public T find(String id);

    public void save(T object);

    public void delete(String id);

    public long count();

    public Class<T> getClassType();

    public SortOrder getSortOrderAsc();

    public SortOrder getSortOrderDesc();

    public Iterable<T> executeQuery(String query);

    public <U> Iterable<U> executeQuery(Class<U> aClass, String query);

    public T executeQueryForObject(String query);

}
