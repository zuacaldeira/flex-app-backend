/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.FlexUser;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author zua
 */
@Remote
public interface FlexUserServiceInterface {
    public Collection<FlexUser> findAllUsers();
    public FlexUser findUserNamed(String username);
    public FlexUser login(String username, String password);
    public FlexUser register(String username, String password);
}
