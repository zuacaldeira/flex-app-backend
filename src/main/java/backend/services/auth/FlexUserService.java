/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.services.auth;

import db.auth.FlexUser;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.neo4j.ogm.cypher.query.SortOrder;
import backend.services.AbstractDBService;

/**
 *
 * @author zua
 */

@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class FlexUserService extends AbstractDBService<FlexUser> implements FlexUserServiceInterface {

    @Override
    public Class<FlexUser> getClassType() {
        return FlexUser.class;
    }

    @Override
    public FlexUser login(String username, String password) {
        System.out.println("INSIDE FLEX USER SERVICE ");
        String query = UsersQueries.findUser(username, password);
        FlexUser user =  getSession().load(FlexUser.class, username, 2);
        if(Objects.equals(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public FlexUser register(String username, String password) {
        getSession().save(new FlexUser(username, password));
        return login(username, password);
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

    @Override
    public void delete(FlexUser user) {
        super.delete(user.getUsername());
    }
}
