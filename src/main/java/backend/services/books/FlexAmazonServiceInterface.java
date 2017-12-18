/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.services.books;

import com.ECS.client.jax.Items;
import db.books.AmazonBook;
import io.reactivex.Observable;
import javax.ejb.Remote;

/**
 *
 * @author zua
 */
@Remote
public interface FlexAmazonServiceInterface {
    public Items searchAmazonItems(String searchIndex, String keywords);
    public Observable<AmazonBook> findFavoriteBooks(String name);
    
}