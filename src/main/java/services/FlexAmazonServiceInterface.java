/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.Remote;

/**
 *
 * @author zua
 */
@Remote
public interface FlexAmazonServiceInterface {
    public String createSignedRequest(String searchIndex, String keywords);
}
