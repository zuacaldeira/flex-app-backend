/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.auth;

import db.auth.FlexUser;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.neo4j.ogm.cypher.query.SortOrder;
import services.AbstractDBService;
import db.auth.UserRole;
import db.news.NewsArticle;
import java.util.HashMap;

/**
 *
 * @author zua
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class FlexUserService extends AbstractDBService<FlexUser> {

    @Override
    public Class<FlexUser> getClassType() {
        return FlexUser.class;
    }

    public FlexUser login(FlexUser user) {
        System.out.println("INSIDE FLEX USER SERVICE ");
        FlexUser dbUser = findByIndex(user.getUsername());
        System.out.printf("(READ, FAVORITE, FAKE) = (%d, %d, %d)",
                user.getRead().size(),
                user.getRead().size(),
                user.getRead().size());
        if (dbUser != null) {
            if (dbUser.getRole() == null) {
                UserRole role = findUserRole(user.getUsername());
                dbUser.setRole(role);
                System.out.println("SET ROLE " + role.getName());
            }
            return dbUser;
        } else {
            return null;
        }
    }

    public FlexUser register(FlexUser user) {
        if (findByIndex(user.getUsername()) == null) {
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

    public Collection<FlexUser> findAllUsers() {
        return getSession().loadAll(getClassType(), getSortOrderDesc());
    }

    public FlexUser findUserNamed(String username) {
        return getSession().load(getClassType(), username);
    }

    public UserRole findUserRole(String username) {
        return getSession().queryForObject(UserRole.class, UserRoleQueries.findUserRole(username), new HashMap<>());
    }

    public boolean hasFavorite(String username, String title) {
        String query = "MATCH (u:FlexUser)-[f:FAVORITE]-(n:NewsArticle) ";
        query += "WHERE u.username={0} AND n.title={1} RETURN n";
        HashMap<String, Object> map = new HashMap<>();
        map.put("0", username);
        map.put("1", title);
        return getSession().query(NewsArticle.class, query, map).iterator().hasNext();
    }

    public boolean hasFake(String username, String title) {
        String query = "MATCH (u:FlexUser)-[f:FAKE]-(n:NewsArticle) ";
        query += "WHERE u.username={0} AND n.title={1} RETURN n";
        HashMap<String, Object> map = new HashMap<>();
        map.put("0", username);
        map.put("1", title);
        return getSession().query(NewsArticle.class, query, map).iterator().hasNext();
    }

    public boolean hasRead(String username, String title) {
        String query = "MATCH (u:FlexUser)-[f:READ]-(n:NewsArticle) ";
        query += "WHERE u.username={0} AND n.title={1} RETURN n";
        HashMap<String, Object> map = new HashMap<>();
        map.put("0", username);
        map.put("1", title);
        return getSession().query(NewsArticle.class, query, map).iterator().hasNext();
    }

}
