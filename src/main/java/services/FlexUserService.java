/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.FlexUser;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.SortOrder;

/**
 *
 * @author zua
 */
@Stateless
public class FlexUserService extends AbstractDBService<FlexUser> implements FlexUserServiceInterface {

    @Override
    public FlexUser update(FlexUser dbEntity, FlexUser newEntity) {
        if(newEntity.getUsername().equals(dbEntity.getUsername())) {
            if(newEntity.getPassword() != null) {
                dbEntity.setPassword(newEntity.getPassword());
            }
            
            if(!newEntity.getRead().isEmpty()) {
                dbEntity.setRead(newEntity.getRead());
            }
            if(!newEntity.getFavorite().isEmpty()) {
                dbEntity.setFavorite(newEntity.getFavorite());
            }
            if(!newEntity.getFake().isEmpty()) {
                dbEntity.setFake(newEntity.getFake());
            }

        }
        
        return dbEntity;
    }

    @Override
    public Class<FlexUser> getClassType() {
        return FlexUser.class;
    }

    @Override
    public FlexUser login(FlexUser user) {
        System.out.println("INSIDE FLEX USER SERVICE ");
        FlexUser u = find(user);
        if(u != null && u.getPassword().equals(user.getPassword())) {
            return u;
        }
        
        return null;
    }

    @Override
    public FlexUser register(FlexUser user) {
        return save(user);
    }

    
    @Override
    public SortOrder getSortOrderAsc() {
        return new SortOrder().add(SortOrder.Direction.ASC, "username");
    }

    @Override
    public SortOrder getSortOrderDesc() {
        return new SortOrder().add(SortOrder.Direction.DESC, "username");
    }
    
}
