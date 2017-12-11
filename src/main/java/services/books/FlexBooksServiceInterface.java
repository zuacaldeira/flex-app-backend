/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.books;

import db.books.Book;
import java.util.Collection;
import javax.ejb.Remote;
import services.DBService;

/**
 *
 * @author zua
 */
@Remote
public interface FlexBooksServiceInterface extends DBService<Book>{
    public Book findBook(String ISBN);
    public Book findBookNamed(String title);
    public Collection<Book> findAll(int i, int i0);
}
