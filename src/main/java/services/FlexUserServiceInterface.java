/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.FlexUser;
import javax.ejb.Remote;

/**
 *
 * @author zua
 */
@Remote
public interface FlexUserServiceInterface extends DBService<FlexUser>{
    public FlexUser login(FlexUser user);
    public FlexUser register(FlexUser user);
}
