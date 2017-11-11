/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.AmazonBook;
import com.ECS.client.jax.Items;
import de.malkusch.amazon.ecs.exception.RequestException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.SortOrder;
import utils.DatabaseUtils;

/**
 *
 * @author zua
 */
@Stateless
public class FlexAmazonService extends AbstractDBService<AmazonBook> implements FlexAmazonServiceInterface {
    
    @Override
    public Items searchAmazonItems(String searchIndex, String keywords) {
        try {
            return new AmazonWebServiceClient().search(searchIndex, keywords);
        } catch (UnsupportedEncodingException | RequestException ex) {
            Logger.getLogger(FlexAmazonService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Collection<AmazonBook> findFavoriteBooks(String name) {
        String query = "MATCH (n:FavoriteBook)--(p:Person) WHERE n.person=" 
                + DatabaseUtils.getInstance().wrapUp(name) 
                + " RETURN n"; 
        return executeQuery(query);
    }

    @Override
    public Class<AmazonBook> getClassType() {
        return AmazonBook.class;
    }

    @Override
    public SortOrder getSortOrderAsc() {
        return new SortOrder().add(SortOrder.Direction.ASC, "title");
    }

    @Override
    public SortOrder getSortOrderDesc() {
        return new SortOrder().add(SortOrder.Direction.DESC, "title");
    }
    
}
