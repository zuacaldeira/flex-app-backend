/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.auth;

import utils.DatabaseUtils;

/**
 *
 * @author zua
 */
public class UserRoleQueries {
    public static String findUserRole(String username) {
        String query = "MATCH (u:FlexUser)--(r:UserRole) WHERE u.username=" + DatabaseUtils.getInstance().wrapUp(username) + " RETURN r";
        System.out.println("Query = " + query);
        return query;
    }
}
