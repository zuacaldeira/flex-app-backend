/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.ECS.client.jax.Items;
import de.malkusch.amazon.ecs.exception.RequestException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author zua
 */
@Stateless
public class FlexAmazonService implements FlexAmazonServiceInterface {

    @Override
    public Items searchAmazonItems(String searchIndex, String keywords) {
        try {
            return new AmazonWebServiceClient().search(searchIndex, keywords);
        } catch (UnsupportedEncodingException | RequestException ex) {
            Logger.getLogger(FlexAmazonService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
