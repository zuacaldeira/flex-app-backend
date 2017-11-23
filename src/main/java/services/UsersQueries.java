/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.DatabaseUtils;

/**
 *
 * @author zua
 */
public class UsersQueries {

    public static String findUser(String username, String password) {
        String query
                = "MATCH (u:FlexUser) WHERE "
                + "u.username=" + DatabaseUtils.getInstance().wrapUp(username)
                + " AND "
                + "u.password=" + DatabaseUtils.getInstance().wrapUp(password)
                + " RETURN u";
        return query;
    }
}
