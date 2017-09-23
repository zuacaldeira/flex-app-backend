/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.FlexUser;
import java.util.Collection;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.SortOrder;

/**
 *
 * @author zua
 */
@Stateless
public class FlexUserService extends AbstractDBService<FlexUser> implements FlexUserServiceInterface {

    @Override
    public Class<FlexUser> getClassType() {
        return FlexUser.class;
    }

    @Override
    public FlexUser login(String username, String password) {
        System.out.println("INSIDE FLEX USER SERVICE ");
        FlexUser user = findUserNamed(username);
        if(user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public FlexUser register(String username, String password) {
        getSession().save(new FlexUser(username, password));
        return findUserNamed(username);
    }

    
    @Override
    public SortOrder getSortOrderAsc() {
        return new SortOrder().add(SortOrder.Direction.ASC, "username");
    }

    @Override
    public SortOrder getSortOrderDesc() {
        return new SortOrder().add(SortOrder.Direction.DESC, "username");
    }

    @Override
    public Collection<FlexUser> findAllUsers() {
        return getSession().loadAll(getClassType(), getSortOrderDesc(), 2);
    }

    @Override
    public FlexUser findUserNamed(String username) {
        return getSession().load(getClassType(), username, 2);
    }
    
}
