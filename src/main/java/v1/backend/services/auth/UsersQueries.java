/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v1.backend.services.auth;

import v1.backend.utils.DatabaseUtils;

/**
 *
 * @author zua
 */
public class UsersQueries {

    public static String findUser(String username, String password) {
        StringBuilder builder = new StringBuilder();
        builder.append("MATCH (u:FlexUser) WHERE u.username=");
        builder.append(DatabaseUtils.getInstance().wrapUp(username));
        if(password != null) {
            builder.append(" AND u.password=");
            builder.append(DatabaseUtils.getInstance().wrapUp(password));
            
        } else {
            builder.append(" AND u.password IS NULL");
        }
        builder.append(" RETURN u");
        String query = builder.toString();
        System.out.println("FIND_USER_QUERY = " + query);
        return query;
    }
}
