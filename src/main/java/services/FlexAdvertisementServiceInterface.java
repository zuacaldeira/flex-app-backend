/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.Advertises;
import db.NewsArticle;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author zua
 */
@Remote
public interface FlexAdvertisementServiceInterface {
    public Collection<Advertises> findAll(NewsArticle article);
}
