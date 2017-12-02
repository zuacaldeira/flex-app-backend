/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.Advertises;
import db.NewsArticle;
import io.reactivex.Observable;
import javax.ejb.Remote;

/**
 *
 * @author zua
 */
@Remote
public interface FlexAdvertisementServiceInterface {
    public Observable<Advertises> findAll(NewsArticle article);
}
