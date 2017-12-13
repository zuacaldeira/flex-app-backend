/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.services.books;

import backend.utils.DatabaseUtils;

/**
 *
 * @author zua
 */
public class BooksQueries {
    
    public static String findBookByISBN(String ISBN) {
        String query = "MATCH (b:Book) WHERE b.ISBN=" 
                + DatabaseUtils.getInstance().wrapUp(ISBN)
                + " RETURN b";
        return query;
    }
    
    public static String findBookByTitle(String title) {
        String query = "MATCH (b:Book) WHERE b.title=" 
                + DatabaseUtils.getInstance().wrapUp(title)
                + " RETURN b";
        return query;
    }
    
}
