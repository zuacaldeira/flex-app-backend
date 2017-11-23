/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.Book;
import java.util.Collection;
import java.util.HashMap;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.Pagination;
import org.neo4j.ogm.cypher.query.SortOrder;


@Stateless
public class FlexBooksService extends AbstractDBService<Book> implements FlexBooksServiceInterface {

    @Override
    public Book findBook(String ISBN) {
        return getSession().queryForObject(Book.class, BooksQueries.findBookByISBN(ISBN), new HashMap<>());
    }

    @Override
    public Book findBookNamed(String title) {
        return getSession().queryForObject(Book.class, BooksQueries.findBookByTitle(title), new HashMap<>());
    }

    @Override
    public Class<Book> getClassType() {
        return Book.class;
    }

    @Override
    public SortOrder getSortOrderAsc() {
        return new SortOrder().add(SortOrder.Direction.ASC);
    }

    @Override
    public SortOrder getSortOrderDesc() {
        return new SortOrder().add(SortOrder.Direction.DESC);
    }

    @Override
    public Collection<Book> findAll(int page, int pageSize) {
        Pagination paging = new Pagination(page, pageSize);
        return getSession().loadAll(Book.class, paging);
    }
    
}
