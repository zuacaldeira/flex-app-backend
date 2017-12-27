/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.services.auth;

import db.auth.FlexUser;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.neo4j.ogm.cypher.query.SortOrder;
import backend.services.AbstractDBService;
import db.auth.UserRole;
import java.util.HashMap;

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
    public FlexUser login(FlexUser user) {
        System.out.println("INSIDE FLEX USER SERVICE ");
        FlexUser dbUser = find(user.getUsername());
        if (dbUser != null) {
            UserRole role = findUserRole(user.getUsername());
            System.out.println("FOUND ROLE " + role.getName());
            dbUser.setRole(role);
            return dbUser;
        }
        else {
            return null;
        }
    }

    @Override
    public FlexUser register(FlexUser user) {
        if (find(user.getUsername()) == null) {
            user.setRole(new UserRole("User"));
            getSession().save(user);
        }
        return login(user);
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

    public UserRole findUserRole(String username) {
        return getSession().queryForObject(UserRole.class, UserRoleQueries.findUserRole(username), new HashMap<>());
    }

}
